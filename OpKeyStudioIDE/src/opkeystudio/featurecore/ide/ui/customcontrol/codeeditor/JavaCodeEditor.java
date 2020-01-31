package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.core.utils.DtoToCodeConverter;
import opkeystudio.featurecore.ide.ui.ui.CodedFunctionView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompilerTools;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class JavaCodeEditor extends RSyntaxTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Object> highlightedLines = new ArrayList<>();
	private Artifact artifact;
	private CodedFunctionView codeFunctionView;

	public JavaCodeEditor(Composite parent) {
		super(new RSyntaxDocument(SyntaxConstants.SYNTAX_STYLE_JAVA));
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		init(SWT_AWT.new_Frame(composite));
	}

	public void setJavaCode(String javaCode) {
		String formatedCode = EditorTools.formatJavaSourceCode(javaCode);
		this.setText(formatedCode);
		compileAndCheck();
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

	public JTextComponent getTextComponent() {
		return this;
	}

	private void init(Frame frame) {
		JPanel mainEditorPanel = new JPanel(new BorderLayout());
		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		this.setCodeFoldingEnabled(true);
		this.setAutoIndentEnabled(true);
		CompletionProvider provider = CodeCompletionProvider.getInstance().getCompletionProvider();
		JavaAutoCompletion autoCompletion = new JavaAutoCompletion(provider);
		autoCompletion.setAutoActivationDelay(10);
		autoCompletion.setShowDescWindow(true);
		autoCompletion.setAutoCompleteSingleChoices(false);
		autoCompletion.setAutoActivationEnabled(true);
		autoCompletion.install(this);
		autoCompletion.setHideOnNoText(false);

		RTextScrollPane textScrollPane = new RTextScrollPane(this);
		mainEditorPanel.add(textScrollPane);
		frame.add(mainEditorPanel);

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == '.') {
					Token lastToken = getRecentToken();
					System.out.println("Current token data " + lastToken.getLexeme());
					AutoCompleteToken autocompletetoken = CodeCompletionProvider.getInstance()
							.findAutoCompleteToken(lastToken.getLexeme());
					JavaCompletionProvider provider = CodeCompletionProvider.getInstance()
							.getClassMethodsCompletionProvider(autocompletetoken);
					autoCompletion.setCompletionProvider(provider);
					autoCompletion.doCompletion();
				}

				try {
					createIntellisenseDataFromCurrentText();
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						getCodeFunctionView().toggleSaveButton(true);
					}
				});
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void createIntellisenseDataFromCurrentText() throws BadLocationException {
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
				CodeCompletionProvider.getInstance().addVariableToken(varToken);
				System.out.println("Var Name " + varName + "   ClassName " + varClassName);
				CodeCompletionProvider.getInstance().addBasicCompletion(varName);
			}
		}
	}

	public List<CompileError> compileAndCheck() {
		try {
			createIntellisenseDataFromCurrentText();
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Object highLightedLines : getHighlightedLines()) {
			this.removeLineHighlight(highLightedLines);
		}
		String codes = this.getText();
		String sourceCodeDirPath = Utilities.getInstance().getDefaultSourceCodeDirPath();
		String codeFilePath = sourceCodeDirPath + File.separator + getArtifact().getArtifactVariableName() + ".java";
		String compiledCodedFLPath = sourceCodeDirPath + File.separator + getArtifact().getArtifactVariableName()
				+ ".class";
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(codeFilePath)));
			bw.write(codes);
			bw.flush();
			bw.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String classPathString = EditorTools
				.getClassPathOFAllAssociatedLibs(opkeystudio.core.utils.Utilities.getInstance().getPluginName());
		List<CompileError> compileErrors = new CompilerTools().compileCodeFl(codeFilePath, classPathString);
		for (CompileError compileError : compileErrors) {
			Object lineHighlightObject;
			try {
				lineHighlightObject = this.addLineHighlight(((int) compileError.getLineNumber()) - 1, Color.RED);
				addHighlightedLines(lineHighlightObject);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getCodeFunctionView().setCodedFLClassPath(compiledCodedFLPath);
		getCodeFunctionView().getCodedFunctionBottomFactoryUi().getCompilationResultTable()
				.renderCompilingError(compileErrors);
		return compileErrors;
	}

	public void convertOpKeyVariablesToCode() {
		Artifact artifact = getArtifact();
		String defaultSourceCodeLibsPath = Utilities.getInstance().getDefaultSourceCodeLibrariesDirPath();
		String defArtifactSourceCodeLibsPath = defaultSourceCodeLibsPath + File.separator
				+ artifact.getArtifactVariableName();
		File file = new File(defArtifactSourceCodeLibsPath);
		if (!file.exists()) {
			file.mkdir();
		}

		getCodeFunctionView().setArtifactOpkeyDataLibraryPath(file.getAbsolutePath());
		JavaClassSource gvClassSource = new DtoToCodeConverter().getJavaClassOfGlobalVariables();
		File gvJavaFile = new File(file.getAbsolutePath() + File.separator + gvClassSource.getName() + ".java");
		try {
			writeToFile(gvJavaFile, gvClassSource.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeToFile(File infile, String dataToWrite) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(infile));
		bw.write(dataToWrite);
		bw.flush();
		bw.close();
	}

	public List<CompileError> compileAllOpKeyLibs() {
		String classPathString = EditorTools
				.getClassPathOFAllAssociatedLibs(opkeystudio.core.utils.Utilities.getInstance().getPluginName());
		List<File> files = EditorTools.getAllFiles(new File(getCodeFunctionView().getArtifactOpkeyDataLibraryPath()),
				".java");
		return new CompilerTools().compileFiles(files, classPathString);
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

	public CodedFunctionView getCodeFunctionView() {
		return codeFunctionView;
	}

	public void setCodeFunctionView(CodedFunctionView codeFunctionView) {
		this.codeFunctionView = codeFunctionView;
	}
}
