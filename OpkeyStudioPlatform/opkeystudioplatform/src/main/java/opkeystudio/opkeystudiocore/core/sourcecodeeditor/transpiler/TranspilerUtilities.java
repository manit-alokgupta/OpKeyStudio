package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.ClassSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.MethodCallSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.MethodSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewObjectSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewStaticObjectDeclarationSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewStaticObjectSnippet;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

@SuppressWarnings("unused")
public class TranspilerUtilities {
	private Transpiler transpiler;

	public TranspilerUtilities(Transpiler transpiler) {
		this.setTranspiler(transpiler);
	}

	private String formatVariableName(String varName) {
		return varName.replaceAll("-", "");
	}

	private List<TranspiledField> getTranspiledFields(Object object) {
		Class<? extends Object> _class = object.getClass();
		Field[] fields = _class.getDeclaredFields();
		List<TranspiledField> transpiledFields = new ArrayList<TranspiledField>();
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation annotation = field.getAnnotation(DBField.class);
			if (annotation instanceof DBField) {
				String fieldName = field.getName();
				Object fieldValue = null;
				try {
					fieldValue = field.get(object);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TranspiledField transField = new TranspiledField();
				transField.setField(field);
				transField.setName(fieldName);
				transField.setValue(fieldValue);
				transpiledFields.add(transField);
			}
		}
		return transpiledFields;
	}

	public String transpileGlobalVariables(List<GlobalVariable> globalVariables) {
		MethodSnippet methodSnippet = new MethodSnippet("void", "init", "");

		ClassSnippet classSnippet = new ClassSnippet("GlobalVariables");
		classSnippet.addMethodSnippet(methodSnippet);
		for (GlobalVariable gv : globalVariables) {
			NewStaticObjectSnippet nsos = new NewStaticObjectSnippet("public", "GlobalVariable", gv.getVariableName());
			classSnippet.addStaticObject(nsos);
		}
		for (GlobalVariable globalVariable : globalVariables) {
			List<TranspiledField> transpiledFields = getTranspiledFields(globalVariable);
			NewStaticObjectDeclarationSnippet newGlobalVariable = new NewStaticObjectDeclarationSnippet(
					"GlobalVariable", globalVariable.getVariableName());
			for (TranspiledField transpiledField : transpiledFields) {
				MethodCallSnippet newMethodCalledSnippet = new MethodCallSnippet(globalVariable.getVariableName(),
						"addProperty", "\"" + transpiledField.getName() + "\"",
						"\"" + transpiledField.getValue() + "\"");
				newGlobalVariable.addMethodCallSnippet(newMethodCalledSnippet);

			}
			methodSnippet.addNewObjectSnippet(newGlobalVariable);
		}
		return classSnippet.toString();
	}

	public String transpileORObjects(List<ORObject> orobjects) {
		ClassSnippet classSnippet = new ClassSnippet("ORObjects");
		MethodSnippet methodSnippet = new MethodSnippet("void", "init", "");
		classSnippet.addMethodSnippet(methodSnippet);
		for (ORObject orobject : orobjects) {
			NewStaticObjectSnippet nsos = new NewStaticObjectSnippet("public", "ORObject", orobject.getVariableName());
			classSnippet.addStaticObject(nsos);
		}
		for (ORObject orobject : orobjects) {
			NewStaticObjectDeclarationSnippet newObjectSnippet = new NewStaticObjectDeclarationSnippet("ORObject",
					orobject.getVariableName());
			List<ObjectAttributeProperty> objectAttributeProperties = orobject.getObjectAttributesProperty();
			for (ObjectAttributeProperty objectAttributeProperty : objectAttributeProperties) {
				if (objectAttributeProperty.getProperty().toLowerCase().equals("objectimage")
						|| objectAttributeProperty.getProperty().toLowerCase().equals("image")) {
					continue;
				}
				MethodCallSnippet methodCallSnippet = new MethodCallSnippet(orobject.getVariableName(), "addProperty",
						"\"" + objectAttributeProperty.getProperty() + "\"",
						"\"" + objectAttributeProperty.getValue() + "\"");
				newObjectSnippet.addMethodCallSnippet(methodCallSnippet);
			}
			methodSnippet.addNewObjectSnippet(newObjectSnippet);
		}
		return classSnippet.toString();
	}

	public String transpileTestCaseSteps(Artifact artifact, List<FlowStep> flowSteps) {
		ClassSnippet classSnippet = new ClassSnippet(artifact.getName());
		MethodSnippet methodSnippet = new MethodSnippet("void", "execute", "");
		classSnippet.addMethodSnippet(methodSnippet);
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.getKeyword() != null) {
				MethodCallSnippet methodCallSnippet = new MethodCallSnippet("new OpKeyGenericKeyword()",
						flowStep.getKeyword().getKeywordname(),
						new TranspileProcessingUtilities(getTranspiler()).getFlowStepInputDatas(flowStep));
				methodSnippet.addMethodCallSnippet(methodCallSnippet);
			}
		}
		return classSnippet.toString();
	}

	public Transpiler getTranspiler() {
		return transpiler;
	}

	public void setTranspiler(Transpiler transpiler) {
		this.transpiler = transpiler;
	}

}
