package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
import pcloudystudio.appium.AndroidDriverObject;
import pcloudystudio.appium.AppiumPortIpInfo;
import pcloudystudio.appium.AppiumServer;
import pcloudystudio.appium.MobileCapabilities;
import pcloudystudio.core.mobile.util.AndroidDeviceUtil;

public class DeviceConfigurationDialog extends Dialog {

	protected Object result;
	protected Shell shlDeviceConfiguration;
	private ObjectRepositoryView parentObjectRepositoryView;
	private Composite compositeConfigurationSettings;
	private Combo devicesCombo;
	private Text applicationPathText;
	private Map<String, String> devicesList;
	private LinkedHashMap<String, String> mapOfCapabilities = new LinkedHashMap<String, String>();
	private Label lblDeviceRequiredMessage;
	private Label lblApplicationIsRequiredMessage;
	private Label lblNoDeviceConnected;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public DeviceConfigurationDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	public DeviceConfigurationDialog(Shell parent, int style, ObjectRepositoryView objectRepositoryView) {
		super(parent, style);
		this.setParentObjectRepositoryView(objectRepositoryView);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDeviceConfiguration.open();
		shlDeviceConfiguration.layout();
		Display display = getParent().getDisplay();
		while (!shlDeviceConfiguration.isDisposed()) {
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
		shlDeviceConfiguration = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL | SWT.BORDER);
		shlDeviceConfiguration
		.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		shlDeviceConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlDeviceConfiguration.setSize(624, 262);
		shlDeviceConfiguration.setText("Configuration Dashboard");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlDeviceConfiguration.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlDeviceConfiguration.setLocation(new Point(locationX, locationY));

		if (MobileCapabilities.getMapOfCapabilities() == null) {
			MobileCapabilities.getinstance().setMapOfCapabilities(mapOfCapabilities);
		}

		compositeConfigurationSettings = new Composite(shlDeviceConfiguration, SWT.BORDER);
		compositeConfigurationSettings.setBounds(10, 10, 598, 166);

		Label lblDeviceName = new Label(compositeConfigurationSettings, SWT.NONE);
		lblDeviceName.setBounds(44, 29, 105, 25);
		lblDeviceName.setText("Device *");
		lblDeviceName.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));

		lblNoDeviceConnected = new Label(compositeConfigurationSettings, SWT.NONE);
		lblNoDeviceConnected.setVisible(false);
		lblNoDeviceConnected.setText("No device connected!");
		lblNoDeviceConnected.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNoDeviceConnected.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblNoDeviceConnected.setBounds(155, 62, 230, 21);
		lblNoDeviceConnected.setVisible(false);

		devicesCombo = new Combo(compositeConfigurationSettings, SWT.READ_ONLY);
		devicesCombo.setBounds(155, 23, 309, 25);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		devicesCombo.setLayoutData(gd_combo);

		try {
			devicesList = AndroidDeviceUtil.getAndroidDevices();
			if (devicesList.size() == 0) {
				lblNoDeviceConnected.setVisible(true);
				devicesCombo.removeAll();
			} else {
				lblNoDeviceConnected.setVisible(false);
				devicesCombo.removeAll();
				for (Map.Entry<String, String> deviceEntry : devicesList.entrySet())
					devicesCombo.add(deviceEntry.getValue());
				devicesCombo.select(0);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		Button btnRefresh = new Button(compositeConfigurationSettings, SWT.NONE);
		btnRefresh.setBounds(470, 22, 75, 33);
		btnRefresh.setText("Refresh");

		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					devicesList.clear();
					devicesList = AndroidDeviceUtil.getAndroidDevices();

					if (devicesList.size() == 0) {
						devicesCombo.removeAll();
						lblNoDeviceConnected.setVisible(true);
					} else {
						lblNoDeviceConnected.setVisible(false);
						devicesCombo.removeAll();
						for (Map.Entry<String, String> deviceEntry : devicesList.entrySet()) {
							devicesCombo.add(deviceEntry.getValue());
						}
						devicesCombo.select(0);
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

		Label lblApplicationFile = new Label(compositeConfigurationSettings, SWT.NONE);
		lblApplicationFile.setBounds(44, 95, 105, 25);
		lblApplicationFile.setText("Application *");
		lblApplicationFile.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));

		applicationPathText = new Text(compositeConfigurationSettings, SWT.BORDER);
		applicationPathText.setBounds(155, 89, 309, 33);

		Button btnBrowseAPK = new Button(compositeConfigurationSettings, SWT.NONE);
		btnBrowseAPK.setBounds(470, 88, 75, 33);
		btnBrowseAPK.setText("Browse");

		lblDeviceRequiredMessage = new Label(compositeConfigurationSettings, SWT.NONE);
		lblDeviceRequiredMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDeviceRequiredMessage.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblDeviceRequiredMessage.setBounds(155, 62, 230, 21);
		lblDeviceRequiredMessage.setText("Device is required!");
		lblDeviceRequiredMessage.setVisible(false);

		lblApplicationIsRequiredMessage = new Label(compositeConfigurationSettings, SWT.NONE);
		lblApplicationIsRequiredMessage.setText("Application is required!");
		lblApplicationIsRequiredMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblApplicationIsRequiredMessage.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblApplicationIsRequiredMessage.setBounds(155, 128, 230, 21);
		lblApplicationIsRequiredMessage.setVisible(false);

		Button btnNext = new Button(shlDeviceConfiguration, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File apkFile = new File(applicationPathText.getText());
				boolean exists = apkFile.exists();
				if (devicesCombo.getText().isEmpty() || applicationPathText.getText().isEmpty()) {
					lblDeviceRequiredMessage.setVisible(devicesCombo.getText().isEmpty() ? true : false);
					lblApplicationIsRequiredMessage.setVisible(applicationPathText.getText().isEmpty() ? true : false);
				} else if (AppiumPortIpInfo.getPort() == null || AppiumPortIpInfo.getPort().length() == 0
						|| AppiumPortIpInfo.getHostAddress() == null || AppiumPortIpInfo.getHostAddress().length() == 0
						|| AppiumPortIpInfo.getAppiumDirectory() == null
						|| AppiumPortIpInfo.getAppiumDirectory().length() == 0) {
					MessageDialog.openInformation(shlDeviceConfiguration, "Please Note",
							"Appium Settings are not configured! Go-To: Tools->Appium Settings.");
				} else if (!exists) {
					MessageDialog.openError(shlDeviceConfiguration, "Error",
							"Application file you entered is not valid!");
					lblApplicationIsRequiredMessage.setVisible(false);
				} else {
					try {
						String selectedDevice = devicesCombo.getText();
						String selectedDeviceUDID = AndroidDeviceUtil.getSelectedAndroidDeviceId(selectedDevice);
						if (MobileCapabilities.getMapOfCapabilities().containsKey("udid")
								&& MobileCapabilities.getMapOfCapabilities().containsKey("deviceName")) {
							MobileCapabilities.getMapOfCapabilities().replace("udid", selectedDeviceUDID);
							String previousDeviceModelName = AndroidDeviceUtil
									.getDeviceName(AndroidDeviceUtil.getSelectedAndroidDeviceId(selectedDevice));
							MobileCapabilities.getMapOfCapabilities().replace("deviceName", previousDeviceModelName);
						} else {
							MobileCapabilities.getMapOfCapabilities().put("udid", selectedDeviceUDID);
							String previousDeviceModelName = AndroidDeviceUtil
									.getDeviceName(AndroidDeviceUtil.getSelectedAndroidDeviceId(selectedDevice));
							MobileCapabilities.getMapOfCapabilities().put("deviceName", previousDeviceModelName);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					OpKeyStudioPreferences.getPreferences().addBasicSettings("application_name",
							applicationPathText.getText());

					shlDeviceConfiguration.setVisible(false);
					showProgressDialog();

					if (AndroidDriverObject.getDriver() == null) {
						MessageDialog.openInformation(shlDeviceConfiguration, "Information",
								"Error In Application Installation.Please Try Again");
					} else {
						if ((AndroidDriverObject.getDriver() != null)
								&& (AndroidDriverObject.getDriver().getSessionId() != null)) {
							shlDeviceConfiguration.close();
							new MobileSpyDialog(getParent(), SWT.NONE, getParentObjectRepositoryView()).open();
						}
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
		btnBrowseAPK.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlDeviceConfiguration);
				dialog.setFilterExtensions(new String[] { "*.apk" });
				dialog.setFilterNames(new String[] { "APK File" });
				dialog.setFilterPath(applicationPathText.getText());
				String path = dialog.open();
				if (path != null) {
					File file = new File(path);
					if (file.exists()) {
						if (MobileCapabilities.getinstance().getMapOfCapabilities().containsKey("app")) {
							MobileCapabilities.getinstance().getMapOfCapabilities().replace("app", path);
						} else {
							MobileCapabilities.getinstance().getMapOfCapabilities().put("app", path);
						}
						applicationPathText.setText(file.toString());
						applicationPathText.setEditable(true);
					} else {
						MessageDialog.openError(shlDeviceConfiguration, "Error",
								"Application APK file you provided doesn't exist!");
					}
				}
				shlDeviceConfiguration.setFocus();
			}
		});

	}

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;

	}

	public void startServer() {
		if (AndroidDriverObject.getDriver() == null) {
			Boolean serverStatus = AppiumServer.isServerRunning(Integer.parseInt(AppiumPortIpInfo.getPort()));
			if (serverStatus) {
				DesiredCapabilities mobileCapability = (MobileCapabilities.getCapabilities());
				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumPortIpInfo.getHostAddress() + ":" + AppiumPortIpInfo.getPort() + "/wd/hub"),
							mobileCapability);

					AndroidDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				AppiumServer.startServer();
				DesiredCapabilities mobileCapability = (MobileCapabilities.getCapabilities());
				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumPortIpInfo.getHostAddress() + ":" + AppiumPortIpInfo.getPort() + "/wd/hub"),
							mobileCapability);
					AndroidDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {

			try {
				AndroidDriverObject.getDriver().quit();
				Thread.sleep(2000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Boolean serverStatus = AppiumServer.isServerRunning(Integer.parseInt(AppiumPortIpInfo.getPort()));
			if (serverStatus) {
				DesiredCapabilities mobileCapability = (MobileCapabilities.getCapabilities());
				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumPortIpInfo.getHostAddress() + ":" + AppiumPortIpInfo.getPort() + "/wd/hub"),
							mobileCapability);

					AndroidDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				try {

					Thread.sleep(10000);
					AppiumServer.startServer();

					DesiredCapabilities mobileCapability = (MobileCapabilities.getCapabilities());

					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumPortIpInfo.getHostAddress() + ":" + AppiumPortIpInfo.getPort() + "/wd/hub"),
							mobileCapability);
					AndroidDriverObject.getInstance().setDriver(driver);
					driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void showProgressDialog() {

		MessageDialogs msd = new MessageDialogs();
		msd.openProgressDialog(getParent(), "Launching Application, Please Wait ...", true,
				new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					startServer();
				} catch (Exception e) {
					MessageDialog.openError(shlDeviceConfiguration, "Error", e.getMessage());
				}
			}
		});
		msd.closeProgressDialog();

	}
}
