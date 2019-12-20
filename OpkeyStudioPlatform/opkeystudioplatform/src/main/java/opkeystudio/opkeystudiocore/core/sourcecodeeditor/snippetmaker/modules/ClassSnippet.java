package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

import java.util.ArrayList;
import java.util.List;

public class ClassSnippet {

	private String START_DATA = "public class %s{";
	private String BODY_DATA = "";
	private String END_DATA = "}";
	private List<MethodSnippet> methodSnippets = new ArrayList<MethodSnippet>();

	public ClassSnippet(String className) {
		setSTART_DATA(String.format(START_DATA, className));
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

	public List<MethodSnippet> getMethodSnippets() {
		return methodSnippets;
	}

	public void addMethodSnippet(MethodSnippet snippet) {
		getMethodSnippets().add(snippet);
	}

	public void setMethodSnippets(List<MethodSnippet> methodSnippets) {
		this.methodSnippets = methodSnippets;
	}

	public String toString() {
		String bodyData = "";
		for (MethodSnippet methodSnippet : getMethodSnippets()) {
			bodyData += methodSnippet.toString();
		}
		if (!bodyData.isEmpty()) {
			setBODY_DATA(bodyData);
		}
		return getSTART_DATA() + System.lineSeparator() + getBODY_DATA() + System.lineSeparator() + getEND_DATA();
	}
}
