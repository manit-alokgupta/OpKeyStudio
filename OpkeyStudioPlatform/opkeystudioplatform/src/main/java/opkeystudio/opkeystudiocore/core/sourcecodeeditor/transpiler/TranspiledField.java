package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.lang.reflect.Field;

public class TranspiledField {
	private Field field;
	private String name;
	private Object value;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
