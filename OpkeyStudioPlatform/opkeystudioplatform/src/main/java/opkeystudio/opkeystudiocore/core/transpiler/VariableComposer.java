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
			return "java.util.Date";
		}
		if (opkeyDataType.equals("File")) {
			return "java.io.File";
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
}
