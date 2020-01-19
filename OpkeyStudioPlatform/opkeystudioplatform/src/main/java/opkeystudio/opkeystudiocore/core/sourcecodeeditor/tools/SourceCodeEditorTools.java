package opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Token.TOKEN_TYPE;

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

	public String[] getDefaultTokens() {
		return this.defaultTokens;
	}

	public List<Token> getTokens(String sourcecode) {
		List<Token> tokens = new ArrayList<Token>();
		for (String tokem : this.defaultTokens) {
			List<Integer> indexes = findWord(sourcecode, tokem);
			for (int index : indexes) {
				int endindex = index + tokem.length();
				char prevChar = 0;
				if (index > 0) {
					prevChar = sourcecode.charAt(index - 1);
				}
				char nextchar = sourcecode.charAt(endindex);
				if (prevChar == '"' && nextchar == '"') {
					Token token = new Token(tokem, index, endindex, TOKEN_TYPE.STRING);
					tokens.add(token);
				} else {
					Token token = new Token(tokem, index, endindex, TOKEN_TYPE.GENERIC);
					tokens.add(token);
				}

			}
		}
		return tokens;
	}

	private List<Integer> findWord(String textString, String word) {
		List<Integer> indexes = new ArrayList<Integer>();
		String lowerCaseTextString = textString;
		String lowerCaseWord = word.toLowerCase();

		int index = 0;
		while (index != -1) {
			index = lowerCaseTextString.indexOf(lowerCaseWord, index);
			if (index != -1) {
				indexes.add(index);
				index++;
			}
		}
		return indexes;
	}
}
