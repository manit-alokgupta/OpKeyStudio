package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.apis.dto.AuthentcationData;
import opkeystudio.opkeystudiocore.core.apis.restapi.AuthenticateApi;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.ResourceManager;

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
	
	
	public static Shell getScreenCentredShell() {
		Display display = PlatformUI.getWorkbench().getDisplay();
		Shell centreShell = new Shell(display);
		Point size = centreShell.computeSize(-1, -1);
		Rectangle screen = display.getMonitors()[0].getBounds();
		centreShell.setBounds((screen.width - size.x) / 2, (screen.height - size.y) / 2, size.x, size.y);
		return centreShell;
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlLoginToOpkey = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlLoginToOpkey.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlLoginToOpkey.setSize(466, 333);
		shlLoginToOpkey.setText("Login to OpKey");
		
		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlLoginToOpkey.getBounds();
		int locationX = (parentSize.width - shellSize.width)/2+parentSize.x;
		int locationY = (parentSize.height - shellSize.height)/2+parentSize.y;
		shlLoginToOpkey.setLocation(new Point(locationX, locationY));
		
		Composite composite = new Composite(shlLoginToOpkey, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite.setBounds(10, 0, 434, 64);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setBounds(10, 10, 164, 23);
		lblNewLabel.setText("Login to OpKey");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 39, 164, 15);
		lblNewLabel_1.setText("Provide your OpKey credential to login");
		
		CLabel lblNewLabel_5 = new CLabel(composite, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel_5.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/logo.png"));
		lblNewLabel_5.setBounds(321, 10, 113, 44);
		
		Button loginButton = new Button(shlLoginToOpkey, SWT.NONE);
		loginButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
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
		loginButton.setBounds(254, 258, 75, 25);
		loginButton.setText("Login");

		Button closebutton = new Button(shlLoginToOpkey, SWT.NONE);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoginToOpkey.close();
			}
		});
		closebutton.setBounds(359, 258, 75, 25);
		closebutton.setText("Close");

		Composite composite_1 = new Composite(shlLoginToOpkey, SWT.BORDER);
		composite_1.setBounds(10, 70, 434, 167);

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("UserName");
		label.setBounds(42, 71, 84, 21);

		domainUrl = new Text(composite_1, SWT.BORDER);
		domainUrl.setToolTipText("OpKey Domain");
		domainUrl.setBounds(192, 24, 178, 24);

		userName = new Text(composite_1, SWT.BORDER);
		userName.setToolTipText("UserName");
		userName.setBounds(192, 68, 178, 24);

		passWord = new Text(composite_1, SWT.BORDER);
		passWord.setToolTipText("Password");
		passWord.setBounds(192, 110, 178, 24);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("OpKey Domain");
		label_1.setBounds(41, 27, 119, 21);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("Password");
		label_2.setBounds(43, 113, 105, 18);
		
		CLabel lblNewLabel_2 = new CLabel(composite_1, SWT.NONE);
		lblNewLabel_2.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/domain.png"));
		lblNewLabel_2.setBounds(163, 27, 23, 21);
		
		CLabel lblNewLabel_3 = new CLabel(composite_1, SWT.NONE);
		lblNewLabel_3.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/user.png"));
		lblNewLabel_3.setBounds(163, 71, 23, 21);
		
		CLabel lblNewLabel_4 = new CLabel(composite_1, SWT.NONE);
		lblNewLabel_4.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/password.png"));
		lblNewLabel_4.setBounds(163, 110, 23, 21);

	}
}
