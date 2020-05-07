package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.OpKeyStudioPreferences;
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
import pcloudystudio.appium.MobileDriverObject;
import pcloudystudio.appium.AppiumConfiguration;
import pcloudystudio.appium.AppiumMobileServer;
import pcloudystudio.appium.MobileDesiredCapabilities;
import pcloudystudio.core.utils.MobileDeviceUtil;
import pcloudystudio.core.utils.CustomMessageDialogUtil;

public class DeviceConfigurationDialog extends Dialog {

	private String[] ANDROID_FILTER_NAMES;
	private String[] ANDROID_FILTER_EXTS;
	protected Object result;
	protected Shell shlDeviceConfiguration;
	private ObjectRepositoryView parentObjectRepositoryView;
	private Composite compositeConfigurationSettings;
	private CCombo devicesCombo;
	private Text applicationPathText;
	private Map<String, String> devicesList;
	private LinkedHashMap<String, String> mapOfCapabilities = new LinkedHashMap<String, String>();
	private Label lblApplicationIsRequiredMessage;
	private Label lblNoDeviceConnected;
	private Button btnHelp;

	public DeviceConfigurationDialog(Shell parent, int style, ObjectRepositoryView objectRepositoryView) {
		super(parent, style);
		this.setParentObjectRepositoryView(objectRepositoryView);
		setText("SWT Dialog");
		this.ANDROID_FILTER_NAMES = new String[] { "Android Application (*.apk)" };
		this.ANDROID_FILTER_EXTS = new String[] { "*.apk" };
	}

	public Object open() {
		Cursor waitCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT);
		getParent().setCursor(waitCursor);
		createContents();
		shlDeviceConfiguration.open();
		shlDeviceConfiguration.layout();
		Display display = getParent().getDisplay();
		Cursor arrow = new Cursor(display, SWT.CURSOR_ARROW);
		getParent().setCursor(arrow);
		while (!shlDeviceConfiguration.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void createContents() {
		shlDeviceConfiguration = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL | SWT.BORDER);
		shlDeviceConfiguration
		.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		shlDeviceConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlDeviceConfiguration.setSize(624, 262);
		shlDeviceConfiguration.setText("Configuration Dashboard");

		Label label = new Label(shlDeviceConfiguration, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 0, 624, 2);

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlDeviceConfiguration.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlDeviceConfiguration.setLocation(new Point(locationX, locationY));

		if (MobileDesiredCapabilities.getMapOfCapabilities() == null) {
			MobileDesiredCapabilities.getinstance().setMapOfCapabilities(mapOfCapabilities);
		}

		compositeConfigurationSettings = new Composite(shlDeviceConfiguration, SWT.BORDER);
		compositeConfigurationSettings.setBounds(10, 10, 598, 166);

		Label lblDeviceName = new Label(compositeConfigurationSettings, SWT.NONE);
		lblDeviceName.setBounds(44, 29, 105, 25);
		lblDeviceName.setText("Device *");
		lblDeviceName.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));

		lblNoDeviceConnected = new Label(compositeConfigurationSettings, SWT.NONE);
		lblNoDeviceConnected.setVisible(false);
		lblNoDeviceConnected.setText("No device connected?");
		lblNoDeviceConnected.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNoDeviceConnected.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblNoDeviceConnected.setBounds(155, 62, 150, 21);
		lblNoDeviceConnected.setVisible(false);

		btnHelp = new Button(compositeConfigurationSettings, SWT.NONE);
		btnHelp.setToolTipText("Help");
		btnHelp.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnHelp.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/help_13.png"));
		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomMessageDialogUtil.openInformationDialog("Help", "Please contact support@opkey.com");
			}
		});
		btnHelp.setBounds(305, 64, 18, 18);
		btnHelp.setVisible(false);

		devicesCombo = new CCombo(compositeConfigurationSettings, SWT.BORDER | SWT.READ_ONLY | SWT.FLAT);
		devicesCombo.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		devicesCombo.setBounds(155, 23, 309, 33);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		devicesCombo.setLayoutData(gd_combo);

		lblApplicationIsRequiredMessage = new Label(compositeConfigurationSettings, SWT.NONE);
		lblApplicationIsRequiredMessage.setText("Application is required!");
		lblApplicationIsRequiredMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblApplicationIsRequiredMessage.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblApplicationIsRequiredMessage.setBounds(155, 128, 230, 21);
		lblApplicationIsRequiredMessage.setVisible(false);

		try {
			devicesList = MobileDeviceUtil.getAndroidDevices();
			if (devicesList.size() == 0) {
				lblNoDeviceConnected.setVisible(true);
				btnHelp.setVisible(true);
				devicesCombo.removeAll();
			} else {
				lblNoDeviceConnected.setVisible(false);
				btnHelp.setVisible(false);
				devicesCombo.removeAll();
				for (Map.Entry<String, String> deviceEntry : devicesList.entrySet())
					devicesCombo.add(deviceEntry.getValue());
				devicesCombo.select(0);
				devicesCombo.addSelectionListener(selectionAdapter);
				devicesCombo.addFocusListener(focusAdapter);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		Button btnRefresh = new Button(compositeConfigurationSettings, SWT.NONE);
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/refreshicon.png"));
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					devicesList.clear();
					devicesList = MobileDeviceUtil.getAndroidDevices();

					if (devicesList.size() == 0) {
						devicesCombo.removeAll();
						lblNoDeviceConnected.setVisible(true);
						btnHelp.setVisible(true);
					} else {
						devicesCombo.removeAll();
						for (Map.Entry<String, String> deviceEntry : devicesList.entrySet()) {
							devicesCombo.add(deviceEntry.getValue());
						}
						devicesCombo.select(0);
						devicesCombo.addSelectionListener(selectionAdapter);
						devicesCombo.addFocusListener(focusAdapter);
						lblNoDeviceConnected.setVisible(false);
						btnHelp.setVisible(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				shlDeviceConfiguration.setFocus();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		btnRefresh.setBounds(470, 30, 20, 20);

		Label lblApplicationFile = new Label(compositeConfigurationSettings, SWT.NONE);
		lblApplicationFile.setBounds(44, 95, 105, 25);
		lblApplicationFile.setText("Application *");
		lblApplicationFile.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));

		applicationPathText = new Text(compositeConfigurationSettings, SWT.BORDER);
		applicationPathText.setBounds(155, 89, 309, 33);

		Button btnBrowseAPK = new Button(compositeConfigurationSettings, SWT.NONE);
		btnBrowseAPK.setToolTipText("Browse Application");
		btnBrowseAPK.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnBrowseAPK.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/browse.png"));
		btnBrowseAPK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlDeviceConfiguration);
				dialog.setFilterExtensions(ANDROID_FILTER_EXTS);
				dialog.setFilterNames(ANDROID_FILTER_NAMES);
				dialog.setFilterPath(applicationPathText.getText());
				String path = dialog.open();
				if (path != null) {
					File file = new File(path);
					if (file.exists() && getFileExtension(file).equals("apk")) {
						applicationPathText.setText(file.toString());
						applicationPathText.setEditable(true);
					} else {
						CustomMessageDialogUtil.openErrorDialog("Error",
								"Application APK file you provided doesn't exist!");
					}
				}
				shlDeviceConfiguration.setFocus();
			}
		});
		btnBrowseAPK.setBounds(470, 96, 20, 20);

		Button btnNext = new Button(shlDeviceConfiguration, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File apkFile = new File(applicationPathText.getText());
				boolean exists = apkFile.exists();

				AppiumConfiguration.getInstance();
				if (AppiumConfiguration.getHostAddress() == null
						&& OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address") != null) {
					AppiumConfiguration
					.setHostAddress(OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address"));
					AppiumConfiguration
					.setPort(OpKeyStudioPreferences.getPreferences().getBasicSettings("port_number"));
					AppiumConfiguration.setAppiumDirectory(
							OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory"));
				}

				if (devicesCombo.getText().isEmpty() || applicationPathText.getText().isEmpty()) {
					lblNoDeviceConnected.setVisible(devicesCombo.getText().isEmpty() ? true : false);
					if (devicesCombo.getText().isEmpty())
						btnHelp.setVisible(true);
					else
						btnHelp.setVisible(false);
					lblApplicationIsRequiredMessage.setVisible(applicationPathText.getText().isEmpty() ? true : false);
				} else if (AppiumConfiguration.getPort() == null || AppiumConfiguration.getHostAddress() == null
						|| AppiumConfiguration.getAppiumDirectory() == null) {
					CustomMessageDialogUtil.openInformationDialog("Please Note",
							"Appium Settings are not configured! Go-To: Tools->Appium Settings.");
				} else if (!exists) {
					CustomMessageDialogUtil.openErrorDialog("Error", "Application file you entered is not valid!");
					lblApplicationIsRequiredMessage.setVisible(false);
				} else {
					try {
						String selectedDevice = devicesCombo.getText();
						String selectedDeviceUDID = MobileDeviceUtil.getSelectedAndroidDeviceId(selectedDevice);
						if (MobileDesiredCapabilities.getMapOfCapabilities().containsKey("udid")
								&& MobileDesiredCapabilities.getMapOfCapabilities().containsKey("deviceName")) {
							MobileDesiredCapabilities.getMapOfCapabilities().replace("udid", selectedDeviceUDID);
							String previousDeviceModelName = MobileDeviceUtil.getDeviceProperty(
									MobileDeviceUtil.getSelectedAndroidDeviceId(selectedDevice),
									MobileDeviceUtil.ANDROID_DEVICE_NAME_PROPERTY);
							MobileDesiredCapabilities.getMapOfCapabilities().replace("deviceName",
									previousDeviceModelName);
						} else {
							MobileDesiredCapabilities.getMapOfCapabilities().put("udid", selectedDeviceUDID);
							String previousDeviceModelName = MobileDeviceUtil.getDeviceProperty(
									MobileDeviceUtil.getSelectedAndroidDeviceId(selectedDevice),
									MobileDeviceUtil.ANDROID_DEVICE_NAME_PROPERTY);
							MobileDesiredCapabilities.getMapOfCapabilities().put("deviceName", previousDeviceModelName);
						}

						OpKeyStudioPreferences.getPreferences().addBasicSettings("application_name",
								applicationPathText.getText());
						String path = applicationPathText.getText();
						if (MobileDesiredCapabilities.getMapOfCapabilities().containsKey("app")) {
							MobileDesiredCapabilities.getMapOfCapabilities().replace("app", path);
						} else {
							MobileDesiredCapabilities.getMapOfCapabilities().put("app", path);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					shlDeviceConfiguration.setVisible(false);

					try {
						showProgressDialog();
					} catch (Exception ex) {
						CustomMessageDialogUtil.openErrorDialog("Error",
								"Unable to start Application: Please check the Appium Server logs for more ... \n");
					}

					if (MobileDriverObject.getDriver() != null
							&& MobileDriverObject.getDriver().getSessionId() != null) {
						shlDeviceConfiguration.close();
						new MobileElementSpyDialog(getParent(), SWT.NONE, getParentObjectRepositoryView()).open();
					} else {
						CustomMessageDialogUtil.openErrorDialog("Error",
								"Unable to start Application: Please check the Appium Server logs for more ... \n"
										+ "org.openqa.selenium.SessionNotCreatedException: Unable to create a new remote session.");
					}
				}
			}
		});
		btnNext.setBounds(452, 182, 75, 33);
		btnNext.setText("Next");

		Button btnClose = new Button(shlDeviceConfiguration, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDeviceConfiguration.close();
			}
		});
		btnClose.setBounds(533, 182, 75, 33);
		btnClose.setText("Close");
	}

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;
	}

	public void startServer() {
		if (MobileDriverObject.getDriver() == null) {
			Boolean serverStatus = AppiumMobileServer.isServerRunning(Integer.parseInt(AppiumConfiguration.getPort()));
			if (serverStatus) {
				DesiredCapabilities mobileCapability = (MobileDesiredCapabilities.getCapabilities());
				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumConfiguration.getHostAddress() + ":" + AppiumConfiguration.getPort() + "/wd/hub"),
							mobileCapability);

					MobileDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					CustomMessageDialogUtil.openErrorDialog("Error",
							"Unable to start Application: Please check the Appium Server logs for more ... \n"
									+ ex.getMessage());
				}
			} else {
				try {
					AppiumMobileServer.startServer();
					DesiredCapabilities mobileCapability = (MobileDesiredCapabilities.getCapabilities());
					Thread.sleep(5000);
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumConfiguration.getHostAddress() + ":" + AppiumConfiguration.getPort() + "/wd/hub"),
							mobileCapability);
					MobileDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					CustomMessageDialogUtil.openErrorDialog("Error",
							"Unable to start Application: Please check the Appium Server logs for more ... \n"
									+ ex.getMessage());
				}
			}
		} else {

			try {
				MobileDriverObject.getInstance().setDriver(null);
				Thread.sleep(2000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Boolean serverStatus = AppiumMobileServer.isServerRunning(Integer.parseInt(AppiumConfiguration.getPort()));
			if (serverStatus) {
				DesiredCapabilities mobileCapability = (MobileDesiredCapabilities.getCapabilities());
				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumConfiguration.getHostAddress() + ":" + AppiumConfiguration.getPort() + "/wd/hub"),
							mobileCapability);

					MobileDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					CustomMessageDialogUtil.openErrorDialog("Error",
							"Unable to start Application: Please check the Appium Server logs for more ... \n"
									+ ex.getMessage());
				}
			} else {
				try {
					Thread.sleep(5000);
					AppiumMobileServer.startServer();
					DesiredCapabilities mobileCapability = (MobileDesiredCapabilities.getCapabilities());

					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumConfiguration.getHostAddress() + ":" + AppiumConfiguration.getPort() + "/wd/hub"),
							mobileCapability);
					MobileDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					CustomMessageDialogUtil.openErrorDialog("Error",
							"Unable to start Application: Please check the Appium Server logs for more ... \n"
									+ ex.getMessage());
				}
			}
		}
	}

	private void showProgressDialog() {
		MessageDialogs msd = new MessageDialogs();
		msd.openProgressDialog(getParent(), "Launching Application! - Please Wait ...", true,
				new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				monitor.beginTask("Launching Application! - Please Wait ...", -1);
				startServer(); // Dont open any dailog here ,in background thread we cannot open ui or main
				// thread component .
				monitor.done();
			}
		});
		msd.closeProgressDialog();
	}

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	static void selectionAtEnd(CCombo c) {
		String text = c.getText();
		int endSelection = text.length();
		c.setSelection(new Point(endSelection, endSelection));
	}

	static SelectionAdapter selectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			selectionAtEnd((CCombo) e.getSource());
		}
	};

	static FocusAdapter focusAdapter = new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			selectionAtEnd((CCombo) e.getSource());
		}
	};
}
