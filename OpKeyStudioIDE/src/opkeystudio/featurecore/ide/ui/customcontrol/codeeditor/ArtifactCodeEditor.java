package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.ArtifactCodeCompletionProvider;
import opkeystudio.featurecore.ide.ui.ui.ArtifactCodeView;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;

public class ArtifactCodeEditor extends RSyntaxTextArea {

	/**
	 * 
	 */

	private CFLCode cflCode;
	private static final long serialVersionUID = 1L;
	private List<Object> highlightedLines = new ArrayList<>();
	private Artifact artifact;
	private CodedFunctionArtifact codedFunctionArtifact;
	private ArtifactCodeView codeFunctionView;
	private JavaAutoCompletion autoCompletion;

	public ArtifactCodeEditor(Composite parent, ArtifactCodeView parentView, boolean enableIntellisense) {

		super();
		System.out.println("JC 1");
		this.setCodeFunctionView(parentView);
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		if (enableIntellisense) {
			init(SWT_AWT.new_Frame(composite));
		} else {
			initWithoutIntellisense(SWT_AWT.new_Frame(composite));
		}
	}

	public JavaAutoCompletion getAutoCompletion() {
		return this.autoCompletion;
	}

	public void setArtifactJavaCode(String javaCode) {
		String formatedCode = new EditorTools().formatJavaSourceCode(javaCode);
		this.setText(formatedCode);
	}

	public JTextComponent getTextComponent() {
		return this;
	}

	static boolean saveToggled = false;

	private void init(Frame frame) {
		JPanel mainEditorPanel = new JPanel(new BorderLayout());
		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		this.setCodeFoldingEnabled(true);
		this.setAutoIndentEnabled(true);
		this.setMarkAllOnOccurrenceSearches(true);
		this.setMarkOccurrences(true);
		CompletionProvider provider = ArtifactCodeCompletionProvider.getInstance(getCodeFunctionView())
				.getCompletionProvider();
		autoCompletion = new JavaAutoCompletion(provider);
		autoCompletion.setListCellRenderer(new JavaCellRenderer());
		autoCompletion.setChoicesWindowSize(350, 200);
		autoCompletion.setDescriptionWindowSize(350, 250);
		autoCompletion.setAutoActivationDelay(10);
		// autoCompletion.setShowDescWindow(true);
		autoCompletion.setAutoCompleteSingleChoices(false);
		autoCompletion.setAutoActivationEnabled(true);
		autoCompletion.setHideOnNoText(false);
		autoCompletion.install(this);
		RTextScrollPane textScrollPane = new RTextScrollPane(this);
		mainEditorPanel.add(textScrollPane);
		frame.add(mainEditorPanel);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (saveToggled) {
					saveToggled = false;
					return;
				}
				try {
					createIntellisenseDataFromCurrentText();
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

				char keyChar = e.getKeyChar();
				if (keyChar == '.') {
					Token lastToken = getRecentToken();
					String tokenData = lastToken.getLexeme();
					System.out.println(">>Last Token "+tokenData);
					VariableToken varToken = ArtifactCodeCompletionProvider.getInstance(getCodeFunctionView())
							.findVariableToken(tokenData);
					if (varToken != null) {
						tokenData = varToken.getClassName();
					}
					AutoCompleteToken autocompletetoken = ArtifactCodeCompletionProvider.getInstance(getCodeFunctionView())
							.findAutoCompleteToken(tokenData);
					if (autocompletetoken != null) {
						JavaCompletionProvider provider = ArtifactCodeCompletionProvider.getInstance(getCodeFunctionView())
								.getClassMethodsCompletionProvider(autocompletetoken);
						autoCompletion.setCompletionProvider(provider);
						autoCompletion.doCompletion();
					}
				}
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						
					}
				});
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					System.out.println("Saving");
					saveToggled = true;
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {

						}
					});
					return;
				}
			}
		});
	}

	private void initWithoutIntellisense(Frame frame) {
		JPanel mainEditorPanel = new JPanel(new BorderLayout());
		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		this.setCodeFoldingEnabled(true);
		this.setAutoIndentEnabled(true);
		this.setMarkAllOnOccurrenceSearches(true);
		this.setMarkOccurrences(true);
		RTextScrollPane textScrollPane = new RTextScrollPane(this);
		mainEditorPanel.add(textScrollPane);
		frame.add(mainEditorPanel);
	}

	private void createIntellisenseDataFromCurrentText() throws BadLocationException {
		try {
			int lineCount = this.getLineCount();
			for (int i = 0; i < lineCount; i++) {
				Token token = this.getTokenListForLine(i);

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

	private Token getRecentToken() {
		int caretLineNumber = getCaretLineNumber();
		Token tokens = getTokenListFor(caretLineNumber, getCaretPosition());
		List<Token> alltokens = new ArrayList<Token>();
		while (tokens.getNextToken() != null) {
			String tokenData = tokens.getLexeme().trim();
			if (!tokenData.isEmpty()) {
				if (!tokenData.equals("(") && !tokenData.equals(")")) {
					alltokens.add(tokens);
				}
			}
			tokens = tokens.getNextToken();
		}
		Token lastToken = alltokens.get(alltokens.size() - 1);
		return lastToken;
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
				ArtifactCodeCompletionProvider.getInstance(getCodeFunctionView()).addVariableToken(varToken);
				ArtifactCodeCompletionProvider.getInstance(getCodeFunctionView()).addBasicCompletion(varName);
			}
		}
	}
	public Artifact getArtifact() {
		return artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}

	public List<Object> getHighlightedLines() {
		return highlightedLines;
	}

	public void addHighlightedLines(Object highlightedLines) {
		this.highlightedLines.add(highlightedLines);
	}

	public ArtifactCodeView getCodeFunctionView() {
		return codeFunctionView;
	}

	public void setCodeFunctionView(ArtifactCodeView codeFunctionView) {
		this.codeFunctionView = codeFunctionView;
	}

	public CFLCode getCflCode() {
		return cflCode;
	}

	public void setCflCode(CFLCode cflCode) {
		this.cflCode = cflCode;
	}

	public CodedFunctionArtifact getCodedFunctionArtifact() {
		return codedFunctionArtifact;
	}

	public void setCodedFunctionArtifact(CodedFunctionArtifact codedFunctionArtifact) {
		this.codedFunctionArtifact = codedFunctionArtifact;
	}
}
