package opkeystudio.parts.dialogui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class AuthenticateUI extends Dialog {

	protected Object result;
	protected Shell shlLoginToYour;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AuthenticateUI(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLoginToYour.open();
		shlLoginToYour.layout();
		Display display = getParent().getDisplay();
		while (!shlLoginToYour.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlLoginToYour = new Shell(getParent(), SWT.CLOSE | SWT.APPLICATION_MODAL);
		shlLoginToYour.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlLoginToYour.setImage(null);
		shlLoginToYour.setSize(403, 260);
		shlLoginToYour.setText("Login To Your OpKey Account");
		
		text = new Text(shlLoginToYour, SWT.BORDER);
		text.setBounds(145, 36, 166, 21);
		
		text_1 = new Text(shlLoginToYour, SWT.BORDER);
		text_1.setBounds(145, 77, 166, 21);
		
		text_2 = new Text(shlLoginToYour, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(145, 120, 166, 21);
		
		Button btnLogin = new Button(shlLoginToYour, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnLogin.setBounds(145, 175, 75, 25);
		btnLogin.setText("Login");
		
		Label lblUrl = new Label(shlLoginToYour, SWT.NONE);
		lblUrl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblUrl.setBounds(43, 36, 55, 15);
		lblUrl.setText("URL");
		
		Label lblUsername = new Label(shlLoginToYour, SWT.NONE);
		lblUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblUsername.setText("UserName:");
		lblUsername.setBounds(43, 77, 75, 15);
		
		Label lblPassword = new Label(shlLoginToYour, SWT.NONE);
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPassword.setText("Password:");
		lblPassword.setBounds(43, 120, 75, 15);

	}
}
