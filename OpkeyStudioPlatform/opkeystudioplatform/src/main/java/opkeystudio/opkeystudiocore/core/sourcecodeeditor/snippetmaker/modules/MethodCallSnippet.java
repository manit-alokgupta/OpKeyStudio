package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

public class MethodCallSnippet {
	private String START_DATA = "";
	private String BODY_DATA = "%s(%s);";
	private String END_DATA = "";

	@SuppressWarnings("unused")
	public MethodCallSnippet(String objectRefName, String methodName, String... valueParams) {
		String values = String.join(",", valueParams);
		String prefix = "";
		if (objectRefName != null) {
			if (!objectRefName.trim().isEmpty()) {
				prefix += objectRefName + ".";
			}
		}
		setBODY_DATA(prefix + String.format(getBODY_DATA(), methodName, values));
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
		return getSTART_DATA() + getBODY_DATA() + getEND_DATA();
	}
}
