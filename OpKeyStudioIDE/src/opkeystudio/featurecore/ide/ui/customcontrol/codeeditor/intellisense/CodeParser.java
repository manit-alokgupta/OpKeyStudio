package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense;

import java.util.ArrayList;
import java.util.List;

import org.fife.ui.rsyntaxtextarea.Token;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.ArtifactCodeEditor;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.VariableToken;

public class CodeParser {
	private boolean cflEditor;

	public CodeParser(boolean cflEditor) {
		this.setCflEditor(cflEditor);
	}

	public Token getRecentToken(ArtifactCodeEditor codeEditor) {
		int caretLineNumber = codeEditor.getCaretLineNumber();
		Token tokens = codeEditor.getTokenListFor(caretLineNumber, codeEditor.getCaretPosition());
		List<Token> alltokens = new ArrayList<Token>();
		while (tokens.getNextToken() != null) {
			String tokenData = tokens.getLexeme().trim();
			if (!tokenData.isEmpty()) {
				if (!tokenData.equals("(") && !tokenData.equals(")") && !tokenData.equals(".") && !tokenData.equals(" ")) {
					alltokens.add(tokens);
				}
			}
			tokens = tokens.getNextToken();
		}
		Token lastToken = alltokens.get(alltokens.size() - 1);
		return lastToken;
	}

	public void createIntellisenseDataFromCurrentText(ArtifactCodeEditor codeEditor) {
		try {
			int lineCount = codeEditor.getLineCount();
			for (int i = 0; i < lineCount; i++) {
				Token token = codeEditor.getTokenListForLine(i);

				List<Token> lineTokens = new ArrayList<Token>();
				if (token != null) {
					while (token.getNextToken() != null) {
						String tokenText = token.getLexeme();
						if (tokenText != null) {
							if (!tokenText.trim().isEmpty()) {
								lineTokens.add(token);
							}
						}
						token = token.getNextToken();
					}
				}
				parseTokens(lineTokens);
			}
		} catch (Error e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void parseTokens(List<Token> lineTokens) {
		for (int i = 0; i < lineTokens.size(); i++) {
			Token token = lineTokens.get(i);
			if (token.getLexeme().equals("=")) {
				String varName = lineTokens.get(i - 1).getLexeme();
				String varClassName = "";
				if (lineTokens.get(i - 2) != null) {
					varClassName = lineTokens.get(i - 2).getLexeme();
				}
				VariableToken varToken = new VariableToken(varName, varClassName);
				if (isCflEditor()) {
					GenericEditorIntellisense.getCFLInstance().addVariableToken(varToken);
					GenericEditorIntellisense.getCFLInstance().addBasicCompletion(varName);
				} else {
					GenericEditorIntellisense.getCodeEditorInstance().addVariableToken(varToken);
					GenericEditorIntellisense.getCodeEditorInstance().addBasicCompletion(varName);
				}
			}
		}
	}

	public boolean isCflEditor() {
		return cflEditor;
	}

	public void setCflEditor(boolean cflEditor) {
		this.cflEditor = cflEditor;
	}
}
