package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

import opkeystudio.opkeystudiocore.core.codeIde.CodeCompletionProvider;

public class JavaCodeEditor extends RSyntaxTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JavaCodeEditor(Composite parent) {
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
		this.setText(javaCode);
		this.setAutoIndentEnabled(true);
	}

	private void init(Frame frame) {
		JPanel mainEditorPanel = new JPanel(new BorderLayout());
		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		this.setCodeFoldingEnabled(true);
		this.setAutoIndentEnabled(true);
		CompletionProvider provider = CodeCompletionProvider.getInstance().getCompletionProvider();
		AutoCompletion autoCompletion = new AutoCompletion(provider);
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

			}

			@Override
			public void keyReleased(KeyEvent e) {
				Token token = getTokenListForLine(getCaretLineNumber());
				while (token.getNextToken() != null) {
					System.out.println("Token " + new String(token.getTextArray()));
					token = token.getNextToken();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
