package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.apis.AuthenticateApi;
import opkeystudio.opkeystudiocore.core.apis.dto.AuthentcationData;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class LoginDialog extends Dialog {

	protected Object result;
	protected Shell shlLoginToOpkey;
	private Text domainUrl;
	private Text userName;
	private Text passWord;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public LoginDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLoginToOpkey.open();
		shlLoginToOpkey.layout();
		Display display = getParent().getDisplay();
		while (!shlLoginToOpkey.isDisposed()) {
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
		shlLoginToOpkey = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlLoginToOpkey.setSize(450, 300);
		shlLoginToOpkey.setText("Login to OpKey");
		Composite composite = new Composite(shlLoginToOpkey, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite.setBounds(0, 0, 444, 64);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setBounds(10, 10, 239, 15);
		lblNewLabel.setText("Login to OpKey");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 39, 306, 15);
		lblNewLabel_1.setText("Provide your OpKey credential to login");

		Button loginButton = new Button(shlLoginToOpkey, SWT.NONE);
		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = domainUrl.getText();
				String user = userName.getText();
				String passw = passWord.getText();
				ServiceRepository.getInstance().setOpKeyHostUrl(url);
				try {
					AuthentcationData authdata = new AuthenticateApi().loginToOpKey(user, passw);
					if (authdata.isStatus()) {
						shlLoginToOpkey.close();
						new ProjectDialog(shlLoginToOpkey).open();
					} else {
						Utilities.getInstance().showErrorDialog(shlLoginToOpkey, "Login Failed",
								"Please check your credential");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginButton.setBounds(253, 236, 75, 25);
		loginButton.setText("Login");

		Button closebutton = new Button(shlLoginToOpkey, SWT.NONE);
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoginToOpkey.close();
			}
		});
		closebutton.setBounds(348, 236, 75, 25);
		closebutton.setText("Close");

		Composite composite_1 = new Composite(shlLoginToOpkey, SWT.BORDER);
		composite_1.setBounds(10, 70, 424, 147);

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("UserName");
		label.setBounds(41, 61, 84, 15);

		domainUrl = new Text(composite_1, SWT.BORDER);
		domainUrl.setBounds(192, 10, 178, 21);

		userName = new Text(composite_1, SWT.BORDER);
		userName.setBounds(192, 55, 178, 21);

		passWord = new Text(composite_1, SWT.BORDER);
		passWord.setBounds(192, 101, 178, 21);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("OpKey Domain");
		label_1.setBounds(41, 16, 129, 15);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("Password");
		label_2.setBounds(41, 107, 105, 15);

	}
}
