package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

import java.util.ArrayList;
import java.util.List;

public class MethodSnippet {
	private String START_DATA = "public %s %s(%s) {";
	private String BODY_DATA = "";
	private String END_DATA = "}";
	private List<NewObjectSnippet> newObjectSnippets = new ArrayList<>();
	private List<MethodCallSnippet> methodCallSnippets = new ArrayList<MethodCallSnippet>();

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
		String bodyData = "";
		for (NewObjectSnippet newObjectSnippet : getNewObjectSnippets()) {
			bodyData += newObjectSnippet.toString();
		}

		for (MethodCallSnippet methodcallSnippet : getMethodCallSnippets()) {
			bodyData += methodcallSnippet.toString();
		}
		if (!bodyData.isEmpty()) {
			setBODY_DATA(bodyData);
		}
		return getSTART_DATA() + getBODY_DATA() + getEND_DATA();
	}

	public void addNewObjectSnippet(NewObjectSnippet newObjectSnippet) {
		getNewObjectSnippets().add(newObjectSnippet);
	}

	public List<NewObjectSnippet> getNewObjectSnippets() {
		return newObjectSnippets;
	}

	public void setNewObjectSnippets(List<NewObjectSnippet> newObjectSnippets) {
		this.newObjectSnippets = newObjectSnippets;
	}

	public List<MethodCallSnippet> getMethodCallSnippets() {
		return methodCallSnippets;
	}

	public void addMethodCallSnippet(MethodCallSnippet methodCallSnippet) {
		this.methodCallSnippets.add(methodCallSnippet);
	}

	public void setMethodCallSnippets(List<MethodCallSnippet> methodCallSnippets) {
		this.methodCallSnippets = methodCallSnippets;
	}

}
