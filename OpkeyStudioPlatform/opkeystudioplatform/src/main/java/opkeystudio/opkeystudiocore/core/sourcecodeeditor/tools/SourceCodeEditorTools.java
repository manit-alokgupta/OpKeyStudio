package opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SourceCodeEditorTools {
	private String[] defaultTokens = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "const", "continue", "default", "do", "double", "else", "enum", "exports", "extends", "final",
			"finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
			"module", "native", "new", "open", "opens", "package", "private", "protected", "provides", "public",
			"requires", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw",
			"throws", "to", "transient", "transitive", "try", "uses", "void", "volatile", "while", "with" };
	private static SourceCodeEditorTools sourceCodeEditor;

	public static SourceCodeEditorTools getInstance() {
		if (sourceCodeEditor == null) {
			sourceCodeEditor = new SourceCodeEditorTools();
		}
		return sourceCodeEditor;
	}

	public List<Token> getTokens(String sourcecode) {
		System.out.println(">>Code Length "+sourcecode.length());
		List<Token> tokens = new ArrayList<Token>();
		StringTokenizer tokenizer=new StringTokenizer(sourcecode);
		return tokens;
	}

	private boolean ifTokenTagContains(String[] tokenArray, String tokenTag) {
		for (String token : tokenArray) {
			if (token.equals(tokenTag)) {
				return true;
			}
		}
		return false;
	}
}
