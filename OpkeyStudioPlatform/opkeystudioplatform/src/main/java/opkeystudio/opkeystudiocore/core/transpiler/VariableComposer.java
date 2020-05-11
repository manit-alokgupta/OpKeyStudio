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
		return opkeyDataType;
	}
}
