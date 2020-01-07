package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.ClassSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.MethodCallSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.MethodSnippet;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules.NewObjectSnippet;

@SuppressWarnings("unused")
public class TranspilerUtilities {
	private static TranspilerUtilities transpiler;

	public static TranspilerUtilities getTranspiler() {
		if (transpiler == null) {
			transpiler = new TranspilerUtilities();
		}
		return transpiler;
	}

	private String formatVariableName(String varName) {
		return varName.replaceAll("-", "");
	}

	private List<TranspiledField> getTranspiledFields(Object object)
			throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> _class = object.getClass();
		Field[] fields = _class.getDeclaredFields();
		List<TranspiledField> transpiledFields = new ArrayList<TranspiledField>();
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation annotation = field.getAnnotation(DBField.class);
			if (annotation instanceof DBField) {
				String fieldName = field.getName();
				Object fieldValue = field.get(object);
				TranspiledField transField = new TranspiledField();
				transField.setField(field);
				transField.setName(fieldName);
				transField.setValue(fieldValue);
				transpiledFields.add(transField);
			}
		}
		return transpiledFields;
	}

	public String transpileGlobalVariables(List<GlobalVariable> globalVariables)
			throws IllegalArgumentException, IllegalAccessException {
		MethodSnippet methodSnippet = new MethodSnippet("void", "init", "");

		ClassSnippet classSnippet = new ClassSnippet("GlobalVariables");
		classSnippet.addMethodSnippet(methodSnippet);
		for (GlobalVariable globalVariable : globalVariables) {
			List<TranspiledField> transpiledFields = getTranspiledFields(globalVariable);
			NewObjectSnippet newGlobalVariable = new NewObjectSnippet("GlobalVariable",
					"gv_" + formatVariableName(globalVariable.getGv_id()));
			for (TranspiledField transpiledField : transpiledFields) {
				MethodCallSnippet newMethodCalledSnippet = new MethodCallSnippet(
						"gv_" + formatVariableName(globalVariable.getGv_id()), "addProperty",
						"\"" + transpiledField.getName() + "\"", "\"" + transpiledField.getValue() + "\"");
				newGlobalVariable.addMethodCallSnippet(newMethodCalledSnippet);
				methodSnippet.addNewObjectSnippet(newGlobalVariable);
			}
		}
		return classSnippet.toString();
	}

	public String transpileORObjects(List<ORObject> orobjects) throws IllegalArgumentException, IllegalAccessException {
		ClassSnippet classSnippet = new ClassSnippet("ORObjects");
		MethodSnippet methodSnippet = new MethodSnippet("void", "init", "");
		classSnippet.addMethodSnippet(methodSnippet);
		for (ORObject orobject : orobjects) {
			NewObjectSnippet newObjectSnippet = new NewObjectSnippet("ORObject",
					"or_" + formatVariableName(orobject.getObject_id()));
			List<ObjectAttributeProperty> objectAttributeProperties = orobject.getObjectAttributesProperty();
			for (ObjectAttributeProperty objectAttributeProperty : objectAttributeProperties) {
				MethodCallSnippet methodCallSnippet = new MethodCallSnippet(
						"or_" + formatVariableName(orobject.getObject_id()), "addProperty",
						"\"" + objectAttributeProperty.getProperty() + "\"",
						"\"" + objectAttributeProperty.getValue() + "\"");
				newObjectSnippet.addMethodCallSnippet(methodCallSnippet);
			}
			methodSnippet.addNewObjectSnippet(newObjectSnippet);
		}
		return classSnippet.toString();
	}

}
