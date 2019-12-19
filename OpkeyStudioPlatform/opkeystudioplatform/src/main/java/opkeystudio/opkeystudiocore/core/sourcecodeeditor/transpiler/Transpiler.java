package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class Transpiler {
	private static Transpiler transpiler;

	public static Transpiler getTranspiler() {
		if (transpiler == null) {
			transpiler = new Transpiler();
		}
		return transpiler;
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

	public void transpileGlobalVariables(List<GlobalVariable> globalVariables)
			throws IllegalArgumentException, IllegalAccessException {
		for (GlobalVariable globalVariable : globalVariables) {
			transpileGlobalVariable(globalVariable);
		}
	}

	private void transpileGlobalVariable(GlobalVariable globalVariable)
			throws IllegalArgumentException, IllegalAccessException {
		System.out.println(">>Transpiling Data");
		List<TranspiledField> transpiledFields = getTranspiledFields(globalVariable);
		for (TranspiledField transpiledField : transpiledFields) {
			System.out.println(transpiledField.getName() + "    " + transpiledField.getValue());
		}
	}
}
