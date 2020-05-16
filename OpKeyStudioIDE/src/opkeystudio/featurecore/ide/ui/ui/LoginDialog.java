package opkeystudio.featurecore.ide.ui.ui;

import java.net.URL;
import java.net.URLConnection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.OpKeyStudioPreferences;
import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.apis.dto.AuthentcationData;
import opkeystudio.opkeystudiocore.core.apis.restapi.AuthenticateApi;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;

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
		Cursor waitCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT);
		getParent().setCursor(waitCursor);
		createContents();
		shlLoginToOpkey.open();
		shlLoginToOpkey.layout();
		Display display = getParent().getDisplay();
		Cursor arrow = new Cursor(display, SWT.CURSOR_ARROW);
		getParent().setCursor(arrow);
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
		shlLoginToOpkey = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlLoginToOpkey.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		shlLoginToOpkey.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlLoginToOpkey.setSize(466, 333);
		shlLoginToOpkey.setText("Login to OpKey");

		Label labelSeparator = new Label(shlLoginToOpkey, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelSeparator.setBounds(0, 0, 460, 2);

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlLoginToOpkey.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlLoginToOpkey.setLocation(new Point(locationX, locationY));

		Composite composite = new Composite(shlLoginToOpkey, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite.setBounds(10, 10, 440, 69);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setBounds(0, 0, 204, 38);
		lblNewLabel.setText("Login to OpKey");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblNewLabel_1.setBounds(0, 44, 267, 25);
		lblNewLabel_1.setText("Provide your OpKey credential to login");

		CLabel lblNewLabel_5 = new CLabel(composite, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel_5.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/logo.png"));
		lblNewLabel_5.setBounds(321, 10, 113, 44);

		Button loginButton = new Button(shlLoginToOpkey, SWT.NONE);
		loginButton.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		loginButton.setToolTipText("Login");
		loginButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = domainUrl.getText();
				String user = userName.getText();
				String passw = passWord.getText();
				OpKeyStudioPreferences.getPreferences().addBasicSettings("opkey_url", url);
				OpKeyStudioPreferences.getPreferences().addBasicSettings("opkey_username", user);
				OpKeyStudioPreferences.getPreferences().addBasicSettings("opkey_password", passw);
				if (url.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlLoginToOpkey, "Invalid URL", "Please enter Domain URL");
				}
				if (user.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlLoginToOpkey, "Invalid Username", "Please enter Username");
				}

				ServiceRepository.getInstance().setOpKeyHostUrl(url);
				if (checkIfInternetIsConnected()) {
					try {
						AuthentcationData authdata = new AuthenticateApi().loginToOpKey(user, passw);
						System.out.println(">> " + authdata.getAuthenticationToken() + " " + authdata.getMessage() + " "
								+ authdata.getSessionId());
						System.out.println(">> " + authdata.isStatus());
						if (authdata.isStatus()) {
							shlLoginToOpkey.close();
							new ArtifactImportDialog(shlLoginToOpkey).open();
						} else {
							CustomNotificationUtil.openErrorNotification("OpKey", "Please check your credential!");
						}
					} catch (Exception ex) {
						Utilities.getInstance().showErrorDialog(shlLoginToOpkey, "Login Failed",
								"Wrong Domain Url provided. Please check.");
					}
				} else {
					CustomNotificationUtil.openErrorNotification("OpKey", "Please check your internet connectivity!");
				}
			}
		});
		loginButton.setBounds(254, 258, 75, 27);
		loginButton.setText("Login");

		Button closebutton = new Button(shlLoginToOpkey, SWT.NONE);
		closebutton.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoginToOpkey.close();
			}
		});
		closebutton.setBounds(359, 258, 75, 27);
		closebutton.setText("Close");

		Composite composite_1 = new Composite(shlLoginToOpkey, SWT.BORDER);
		composite_1.setBounds(10, 85, 440, 167);

		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		label.setText("UserName");
		label.setBounds(42, 71, 84, 21);

		domainUrl = new Text(composite_1, SWT.BORDER);
		domainUrl.setToolTipText("OpKey Domain");
		domainUrl.setBounds(192, 24, 199, 24);

		userName = new Text(composite_1, SWT.BORDER);
		userName.setToolTipText("UserName");
		userName.setBounds(192, 68, 199, 24);

		passWord = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		passWord.setToolTipText("Password");
		passWord.setBounds(192, 110, 199, 24);

		String domainURL = OpKeyStudioPreferences.getPreferences().getBasicSettings("opkey_url");
		String username = OpKeyStudioPreferences.getPreferences().getBasicSettings("opkey_username");
		String password = OpKeyStudioPreferences.getPreferences().getBasicSettings("opkey_password");

		if (domainURL != null) {
			domainUrl.setText(domainURL);
		}

		if (username != null) {
			userName.setText(username);
		}

		if (password != null) {
			passWord.setText(password);
		}
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		label_1.setText("OpKey Domain");
		label_1.setBounds(41, 27, 119, 21);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
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

	private static boolean checkIfInternetIsConnected() {
		try {
			URL url = new URL("https://www.geeksforgeeks.org/");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("Connection Successful!");
			return true;
		} catch (Exception e) {
			System.out.println("Internet not connected!");
			return false;
		}
	}
}
