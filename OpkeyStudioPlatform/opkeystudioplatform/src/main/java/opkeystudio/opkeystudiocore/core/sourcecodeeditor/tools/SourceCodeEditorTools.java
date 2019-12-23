package opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools;

import java.util.List;

public class SourceCodeEditorTools {
	private String[] defaultTokens = { "new", "public", "private", "protected", "void", "import", "class" };
	private static SourceCodeEditorTools sourceCodeEditor;

	public static SourceCodeEditorTools getInstance() {
		if (sourceCodeEditor == null) {
			sourceCodeEditor = new SourceCodeEditorTools();
		}
		return sourceCodeEditor;
	}

	public List<Token> getTokens(String sourcecode) {
		String tokenTag = "";
		int startIndex = -1;
		for (int i = 0; i < sourcecode.length(); i++) {
			if (startIndex == -1) {
				startIndex = i;
			}
		}
		return null;
	}
}
