package opkeystudio.opkeystudiocore.core.sourcecodeeditor.snippetmaker.modules;

import java.util.ArrayList;
import java.util.List;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Tools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler.Transpiler;

public class ClassSnippet {

	private String START_DATA = "public class %s{";
	private String BODY_DATA = "";
	private String END_DATA = "}";
	private Transpiler transpiler;
	private List<NewStaticObjectSnippet> staticObject = new ArrayList<NewStaticObjectSnippet>();
	private List<MethodSnippet> methodSnippets = new ArrayList<MethodSnippet>();

	public ClassSnippet(String className, FileNode fileNode, Transpiler transpiler) {
		FileNode rootNode = transpiler.getRootNode();
		String localImports = "";
		ArrayList<FileNode> sourceFiles = new Tools().getAllFileNodes(rootNode);
		for (FileNode sf : sourceFiles) {
			localImports += "import " + sf.getImportName() + ".*";
			if (!localImports.isEmpty()) {
				localImports += ";";
			}
		}
		setSTART_DATA("package " + fileNode.getParentPackageName() + ";" + new Tools().getOpKeyRuntimeImportHeaders()
				+ localImports + " " + String.format(START_DATA, className));
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
		for (NewStaticObjectSnippet staticObject : getStaticObject()) {
			bodyData += staticObject.toString();
		}
		for (MethodSnippet methodSnippet : getMethodSnippets()) {
			bodyData += methodSnippet.toString();
		}
		if (!bodyData.isEmpty()) {
			setBODY_DATA(bodyData);
		}
		String data = getSTART_DATA() + getBODY_DATA() + getEND_DATA();
		try {
			return new Formatter().formatSource(data);
		} catch (FormatterException e) {
			e.printStackTrace();
			return data;
		}
	}

	public List<NewStaticObjectSnippet> getStaticObject() {
		return staticObject;
	}

	public void setStaticObject(List<NewStaticObjectSnippet> staticObject) {
		this.staticObject = staticObject;
	}

	public void addStaticObject(NewStaticObjectSnippet staticObject) {
		this.staticObject.add(staticObject);
	}

	public Transpiler getTranspiler() {
		return transpiler;
	}

	public void setTranspiler(Transpiler transpiler) {
		this.transpiler = transpiler;
	}
}
