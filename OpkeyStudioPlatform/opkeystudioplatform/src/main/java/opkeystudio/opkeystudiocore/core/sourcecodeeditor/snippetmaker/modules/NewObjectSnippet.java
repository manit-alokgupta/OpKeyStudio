package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

public class NewObjectSnippet {
	private String START_DATA = "public class %s";
	private String BODY_DATA = "";
	private String END_DATA = "}";

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
}
