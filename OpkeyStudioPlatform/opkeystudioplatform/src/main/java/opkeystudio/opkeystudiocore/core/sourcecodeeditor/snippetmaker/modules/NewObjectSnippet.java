package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

import java.util.ArrayList;
import java.util.List;

public class NewObjectSnippet {
	private String START_DATA = "%s %s = new %s();";
	private String BODY_DATA = "";
	private String END_DATA = "";
	private String OBJECT_NAME = "";
	private String OBJECT_REF_NAME = "";
	private List<MethodCallSnippet> methodCallSnippets = new ArrayList<>();
	public NewObjectSnippet() {
		
	}
	public NewObjectSnippet(String objectName, String objectrefName) {
		this.OBJECT_NAME = objectName;
		this.OBJECT_REF_NAME = objectrefName;
		setSTART_DATA(String.format(getSTART_DATA(), objectName, objectrefName, objectName));
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
		String data = System.lineSeparator() + System.lineSeparator() + getSTART_DATA() + getBODY_DATA()
				+ getEND_DATA();
		for (MethodCallSnippet methodCallSnippet : getMethodCallSnippets()) {
			data += methodCallSnippet.toString();
		}
		return data;
	}

	public void addMethodCallSnippet(MethodCallSnippet methodCallSnippet) {
		this.methodCallSnippets.add(methodCallSnippet);
	}

	public List<MethodCallSnippet> getMethodCallSnippets() {
		return methodCallSnippets;
	}

	public void setMethodCallSnippets(List<MethodCallSnippet> methodCallSnippets) {
		this.methodCallSnippets = methodCallSnippets;
	}
}
