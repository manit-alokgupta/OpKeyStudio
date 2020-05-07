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
import javax.swing.text.JTextComponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;
import org.fife.ui.autocomplete.AutoCompletionEvent;
import org.fife.ui.autocomplete.AutoCompletionListener;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.CodeParser;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.GenericEditorIntellisense;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.components.TranspiledClassInfo;
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
	private boolean cflEditor;

	public ArtifactCodeEditor() {
		super();
	}

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

	public ArtifactCodeEditor(Composite parent, ArtifactCodeView parentView, boolean enableIntellisense,
			boolean isgenericEditor) {

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

	public ArtifactCodeEditor(Composite parent, ArtifactCodeView parentView, boolean enableIntellisense,
			boolean isgenericEditor, boolean cflEditor) {

		super();
		System.out.println("JC 1");
		this.setCodeFunctionView(parentView);
		this.setCflEditor(cflEditor);
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
		if (isCflEditor()) {
			CompletionProvider provider = GenericEditorIntellisense.getCFLInstance();
			autoCompletion = new JavaAutoCompletion(provider);
		} else {
			CompletionProvider provider = GenericEditorIntellisense.getInstance();
			autoCompletion = new JavaAutoCompletion(provider);
		}
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
		autoCompletion.addAutoCompletionListener(new AutoCompletionListener() {

			@Override
			public void autoCompleteUpdate(AutoCompletionEvent arg0) {
				System.out.println(arg0.getEventType().toString());
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (arg0.getEventType().toString().equals("POPUP_HIDDEN")) {
							System.out.println("Resetting Intellisense Data");
							if (isCflEditor()) {
								CompletionProvider provider = GenericEditorIntellisense.getCFLInstance();
								autoCompletion.setCompletionProvider(provider);
							} else {
								CompletionProvider provider = GenericEditorIntellisense.getInstance();
								autoCompletion.setCompletionProvider(provider);
							}
						}
					}
				}).start();

			}
		});

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				new CodeParser(isCflEditor()).createIntellisenseDataFromCurrentText(getArtifactCodeEditorInstance());

				char keyChar = e.getKeyChar();
				if (keyChar == '.') {
					if (isCflEditor()) {
						Token lastToken = new CodeParser(isCflEditor()).getRecentToken(getArtifactCodeEditorInstance());
						String tokenData = lastToken.getLexeme();
						System.out.println(">>Last Token " + tokenData);
						VariableToken varToken = GenericEditorIntellisense.getCFLInstance()
								.findVariableToken(tokenData);
						if (varToken != null) {
							tokenData = varToken.getClassName();
						}
						TranspiledClassInfo autocompletetoken = GenericEditorIntellisense.getCFLInstance()
								.findAutoCompleteToken(tokenData);
						if (autocompletetoken != null) {
							JavaCompletionProvider provider = GenericEditorIntellisense.getCFLInstance()
									.getClassMethodsCompletionProvider(autocompletetoken);
							autoCompletion.setCompletionProvider(provider);
						}
					} else {
						Token lastToken = new CodeParser(isCflEditor()).getRecentToken(getArtifactCodeEditorInstance());
						String tokenData = lastToken.getLexeme();
						System.out.println(">>Last Token " + tokenData);
						VariableToken varToken = GenericEditorIntellisense.getInstance().findVariableToken(tokenData);
						if (varToken != null) {
							tokenData = varToken.getClassName();
						}
						TranspiledClassInfo autocompletetoken = GenericEditorIntellisense.getInstance()
								.findAutoCompleteToken(tokenData);
						if (autocompletetoken != null) {
							JavaCompletionProvider provider = GenericEditorIntellisense.getInstance()
									.getClassMethodsCompletionProvider(autocompletetoken);
							autoCompletion.setCompletionProvider(provider);
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					System.out.println("Saving");
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

	private ArtifactCodeEditor getArtifactCodeEditorInstance() {
		return this;
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

	public boolean isCflEditor() {
		return cflEditor;
	}

	public void setCflEditor(boolean cflEditor) {
		this.cflEditor = cflEditor;
	}
}
