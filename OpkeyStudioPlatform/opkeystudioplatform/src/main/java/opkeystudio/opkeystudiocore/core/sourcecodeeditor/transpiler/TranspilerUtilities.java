package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.io.File;
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
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.ClassSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.MethodCallSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.MethodSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewObjectSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewStaticObjectDeclarationSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewStaticObjectSnippet;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

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

	public String transpileGlobalVariables(List<GlobalVariable> globalVariables, FileNode fileNode) {
		ClassSnippet classSnippet = new ClassSnippet("GlobalVariables", fileNode, getTranspiler());
		for (GlobalVariable gv : globalVariables) {
			String value = gv.getValue();
			if (value != null) {
				value = "\"" + value + "\"";
			}
			NewStaticObjectSnippet nsos = new NewStaticObjectSnippet("public", "String",
					gv.getVariableName() + "=" + value);
			classSnippet.addStaticObject(nsos);
		}
		return classSnippet.toString();
	}

	public String transpileORObjects(List<ORObject> orobjects, FileNode fileNode) {
		ClassSnippet classSnippet = new ClassSnippet("ORObjects", fileNode, getTranspiler());
		MethodSnippet methodSnippet = new MethodSnippet("void", "initORObjects", "");
		MethodCallSnippet callmethodCallSnippet = new MethodCallSnippet("new ORObjects()", "initORObjects", "");
		getTranspiler().addMainTestCaseMethods(callmethodCallSnippet);
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

	public String transpileTestCaseSteps(Artifact artifact, List<FlowStep> flowSteps, FileNode fileNode) {
		ClassSnippet classSnippet = new ClassSnippet(fileNode.getFileName().replaceAll(".java", ""), fileNode,
				getTranspiler());
		MethodSnippet methodSnippet = new MethodSnippet("void", "execute", "");
		MethodCallSnippet callmethodCallSnippet = new MethodCallSnippet(
				"new " + fileNode.getFileName().replaceAll(".java", "") + "()", "execute", "");
		getTranspiler().addMainTestCaseMethods(callmethodCallSnippet);
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

	public String createMainClassBody(FileNode fileNode) {
		ClassSnippet classSnippet = new ClassSnippet("Main", fileNode, getTranspiler());
		MethodSnippet methodSnippet = new MethodSnippet("static void", "main", "String[] args");
		classSnippet.addMethodSnippet(methodSnippet);
		MethodCallSnippet callmethodCallSnippet = new MethodCallSnippet("System", "setProperty",
				"\"webdriver.chrome.driver\"",
				"\"" + Utilities.getInstance().getSeleniumDriverFolder() + File.separator + "chromedriver.exe" + "\"");
		methodSnippet.addMethodCallSnippet(callmethodCallSnippet);
		for (MethodCallSnippet mcs : getTranspiler().getMainTestCaseMethods()) {
			methodSnippet.addMethodCallSnippet(mcs);
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
