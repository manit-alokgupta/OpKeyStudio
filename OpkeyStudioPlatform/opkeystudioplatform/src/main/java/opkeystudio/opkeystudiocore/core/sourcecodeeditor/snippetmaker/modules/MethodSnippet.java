package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

public class MethodSnippet {
	private String START_DATA = "public %s %s(%s) {";
	private String BODY_DATA = "";
	private String END_DATA = "}";

	public MethodSnippet(String returntype, String methodName, String... parameters) {
		String params = String.join(",", parameters);
		setSTART_DATA(String.format(START_DATA, returntype, methodName, params));
	}

	public String getSTART_DATA() {
		return START_DATA;
	}

	public void setSTART_DATA(String sTART_DATA) {
		START_DATA = sTART_DATA;
	}

	public String getBODY_DATA() {
		return BODY_DATA;
	}

	public void setBODY_DATA(String bODY_DATA) {
		BODY_DATA = bODY_DATA;
	}

	public String getEND_DATA() {
		return END_DATA;
	}

	public void setEND_DATA(String eND_DATA) {
		END_DATA = eND_DATA;
	}

	public String toString() {
		return getSTART_DATA() + System.lineSeparator() + getBODY_DATA() + System.lineSeparator() + getEND_DATA();
	}
	
}
