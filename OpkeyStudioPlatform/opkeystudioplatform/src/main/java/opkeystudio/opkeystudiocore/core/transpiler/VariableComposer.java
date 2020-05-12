package opkeystudio.opkeystudiocore.core.transpiler;

public class VariableComposer {
	public String convertOpKeyDataTypeToJavaDataType(String opkeyDataType) {
		if (opkeyDataType.equals("String")) {
			return "String";
		}
		if (opkeyDataType.equals("Integer")) {
			return "int";
		}
		if (opkeyDataType.equals("Double")) {
			return "double";
		}
		if (opkeyDataType.equals("Boolean")) {
			return "boolean";
		}
		if (opkeyDataType.equals("DateTime")) {
			return "Date";
		}
		if (opkeyDataType.equals("File")) {
			return "File";
		}

		if (opkeyDataType.equals("CollectionOfString")) {
			return "String[]";
		}

		if (opkeyDataType.equals("CollectionOfInteger")) {
			return "Integer[]";
		}

		if (opkeyDataType.equals("CollectionOfDouble")) {
			return "Double[]";
		}

		if (opkeyDataType.equals("CollectionOfDateTime")) {
			return "Date[]";
		}

		if (opkeyDataType.equals("CollectionOfKeyValuePair")) {
			return "KeyValuePair[]";
		}

		return opkeyDataType;
	}

	public String getDataAsDataType(String dataType, String data) {
		if (data != null) {
			if (data.trim().isEmpty()) {
				data = null;
			}
		}
		if (dataType.equals("int")) {
			if (data == null) {
				return "0";
			}
			return data;
		}
		if (dataType.equals("double")) {
			if (data == null) {
				return "0";
			}
			return data;
		}
		if (dataType.equals("float")) {
			if (data == null) {
				return "0";
			}
			return data;
		}

		if (dataType.equals("boolean")) {
			if (data == null) {
				return "false";
			}
			return data;
		}

		if (dataType.equals("String")) {
			if (data == null) {
				data = "";
			}
			return "\"" + data + "\"";
		}

		if (dataType.equals("File")) {
			if (data == null) {
				return "null";
			}
			return data;
		}

		if (dataType.equals("Date")) {
			if (data == null) {
				return "null";
			}
			return data;
		}

		if (dataType.equals("String[]")) {
			if (data == null) {
				return "null";
			}
			return data;
		}

		if (dataType.equals("Integer[]")) {
			if (data == null) {
				return "null";
			}
			return data;
		}
		if (dataType.equals("Double[]")) {
			if (data == null) {
				return "null";
			}
			return data;
		}
		if (dataType.equals("Date[]")) {
			if (data == null) {
				return "null";
			}
			return data;
		}
		if (dataType.equals("KeyValuePair[]")) {
			if (data == null) {
				return "null";
			}
			return data;
		}
		return data;
	}
}
