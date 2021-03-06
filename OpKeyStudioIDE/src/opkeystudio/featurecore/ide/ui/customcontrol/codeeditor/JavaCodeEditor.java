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
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.featurecore.ide.ui.ui.CodedFunctionView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompilerTools;
import opkeystudio.opkeystudiocore.core.transpiler.GlobalTranspiler;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class JavaCodeEditor extends RSyntaxTextArea {

	/**
	 * 
	 */

	private CFLCode cflCode;
	private static final long serialVersionUID = 1L;
	private List<Object> highlightedLines = new ArrayList<>();
	private Artifact artifact;
	private CodedFunctionArtifact codedFunctionArtifact;
	private CodedFunctionView codeFunctionView;
	private JavaAutoCompletion autoCompletion;

	public JavaCodeEditor(Composite parent, CodedFunctionView parentView, boolean enableIntellisense) {

		super();
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
			initWithIntellisense(SWT_AWT.new_Frame(composite));
		} else {
			initWithoutIntellisense(SWT_AWT.new_Frame(composite));
		}
	}

	public JavaAutoCompletion getAutoCompletion() {
		return this.autoCompletion;
	}

	public void setArtifactJavaCode(String javaCode) {
		String formatedCode = new EditorTools(getCodeFunctionView()).formatJavaSourceCode(javaCode);
		this.setText(formatedCode);
	}

	public void setJavaCode(String javaCode) {
		String formatedCode = new EditorTools(getCodeFunctionView()).formatJavaSourceCode(javaCode);
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

	static boolean saveToggled = false;

	private void initWithIntellisense(Frame frame) {
		JPanel mainEditorPanel = new JPanel(new BorderLayout());
		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		this.setCodeFoldingEnabled(true);
		this.setAutoIndentEnabled(true);
		this.setMarkAllOnOccurrenceSearches(true);
		this.setMarkOccurrences(true);

		CompletionProvider provider = CodeCompletionProvider.getInstance(getCodeFunctionView()).getCompletionProvider();
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
					VariableToken varToken = CodeCompletionProvider.getInstance(getCodeFunctionView())
							.findVariableToken(tokenData);
					if (varToken != null) {
						tokenData = varToken.getClassName();
					}
					AutoCompleteToken autocompletetoken = CodeCompletionProvider.getInstance(getCodeFunctionView())
							.findAutoCompleteToken(tokenData);
					if (autocompletetoken != null) {
						JavaCompletionProvider provider = CodeCompletionProvider.getInstance(getCodeFunctionView())
								.getClassMethodsCompletionProvider(autocompletetoken);
						autoCompletion.setCompletionProvider(provider);
						autoCompletion.doCompletion();
					}
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
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					saveToggled = true;
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							getCodeFunctionView().saveAllCFL();
						}
					});
					return;
				}
			}
		});
		includeOpKeyRuntimeLib();
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
				CodeCompletionProvider.getInstance(getCodeFunctionView()).addVariableToken(varToken);
				CodeCompletionProvider.getInstance(getCodeFunctionView()).addBasicCompletion(varName);
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
		String codeFilePath = sourceCodeDirPath + File.separator + getArtifact().getVariableName() + ".java";
		String compiledCodedFLPath = sourceCodeDirPath + File.separator + getArtifact().getVariableName() + ".class";
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

		String classPathString = new EditorTools(getCodeFunctionView())
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

	private void includeOpKeyRuntimeLib() {
		File runtimeJar = new File(
				Utilities.getInstance().getDefaultPluginBaseDir() + File.separator + "opkeyeruntimeJar.jar");
		new CodedFunctionApi().addLibraryFileInDb(getCodeFunctionView().getArtifact(), runtimeJar);
	}

	public void convertOpKeyVariablesToCode() {
		Artifact artifact = getArtifact();
		String defaultSourceCodeLibsPath = Utilities.getInstance().getDefaultCodedFLOpKeyLibrariesDirPath();
		String defaultAssociatedLibsPath = Utilities.getInstance().getDefaultCodedFLAssociatedLibrariesDirPath();
		String defArtifactSourceCodeLibsPath = defaultSourceCodeLibsPath + File.separator + artifact.getVariableName();
		String defArtifactassolibsPath = defaultAssociatedLibsPath + File.separator + artifact.getVariableName();

		File file = new File(defArtifactSourceCodeLibsPath);
		if (!file.exists()) {
			file.mkdir();
		}

		File file1 = new File(defArtifactassolibsPath);
		if (!file1.exists()) {
			file1.mkdir();
		}

		getCodeFunctionView().setArtifactOpkeyDataLibraryPath(file.getAbsolutePath());
		getCodeFunctionView().setArtifactAssociatedLibraryPath(file1.getAbsolutePath());
		JavaClassSource gvClassSource = new GlobalTranspiler().getJavaClassOfGlobalVariables();
		File gvJavaFile = new File(file.getAbsolutePath() + File.separator + gvClassSource.getName() + ".java");
		try {
			writeToFile(gvJavaFile, gvClassSource.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compileAllOpKeyLibs();
	}

	private void writeToFile(File infile, String dataToWrite) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(infile));
		bw.write(dataToWrite);
		bw.flush();
		bw.close();
	}

	public List<CompileError> compileAllOpKeyLibs() {
		String classPathString = new EditorTools(getCodeFunctionView())
				.getClassPathOFAllAssociatedLibs(opkeystudio.core.utils.Utilities.getInstance().getPluginName());
		List<File> files = new EditorTools(getCodeFunctionView())
				.getAllFiles(new File(getCodeFunctionView().getArtifactOpkeyDataLibraryPath()), ".java");
		List<CompileError> compileErrors = new CompilerTools().compileFiles(files, classPathString);
		List<File> compiledClasses = new EditorTools(getCodeFunctionView())
				.getAllFiles(new File(getCodeFunctionView().getArtifactOpkeyDataLibraryPath()), ".class");
		try {
			File compiledJARFile = Utilities.getInstance().createZip(compiledClasses);
			new CodedFunctionApi().addLibraryFileInDb(getCodeFunctionView().getArtifact(), compiledJARFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compileErrors;
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
