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
			// Set cross-platform Java L&F (also called "Metal")
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
		RTextScrollPane textScrollPane = new RTextScrollPane(this);
		mainEditorPanel.add(textScrollPane);
		frame.add(mainEditorPanel);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
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
			Token token = this.getTokenListFor(i, this.getLineEndOffset(i));
			while (token.getNextToken() != null) {
				System.out.println("Token " + new String(token.getTextArray()));
				String tokenText = new String(token.getTextArray());
				CodeCompletionProvider.getInstance().addBasicCompletion(tokenText);
				token = token.getNextToken();
			}
		}
	}

	public List<CompileError> compileAndCheck() {
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

		String classPathString = EditorTools.getClassPathOFAllAssociatedLibs("Web");
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
		EditorTools.executeCodedFl(compiledCodedFLPath, "Web");
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
}
