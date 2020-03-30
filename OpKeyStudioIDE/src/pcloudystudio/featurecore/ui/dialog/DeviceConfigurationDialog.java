package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

import org.eclipse.swt.widgets.FileDialog;

import pcloudystudio.appium.AndroidDriverObject;
import pcloudystudio.appium.AppiumPortIpInfo;
import pcloudystudio.appium.AppiumServer;
import pcloudystudio.appium.MobileCapabilities;
import pcloudystudio.core.mobile.util.AndroidDeviceUtil;

public class DeviceConfigurationDialog extends Dialog {

	protected Object result;
	protected Shell shlDeviceConfiguration;

	private Composite compositeConfiguration;
	private Label lblDeviceConfiguration;
	private Composite compositeConfigurationSettings;
	private Combo devicesCombo;
	private Text applicationPathText;
	private Map<String, String> devicesList;
	private LinkedHashMap<String, String> mapOfCapabilities = new LinkedHashMap<String, String>();

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
		shlDeviceConfiguration = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL);
		shlDeviceConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlDeviceConfiguration.setSize(672, 260);
		shlDeviceConfiguration.setText("Configuration Dashboard");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlDeviceConfiguration.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlDeviceConfiguration.setLocation(new Point(locationX, locationY));

		if (MobileCapabilities.getMapOfCapabilities() == null) {
			MobileCapabilities.getinstance().setMapOfCapabilities(mapOfCapabilities);

		}
		compositeConfiguration = new Composite(shlDeviceConfiguration, SWT.NONE);
		compositeConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeConfiguration.setBounds(10, 10, 315, 38);

		lblDeviceConfiguration = new Label(compositeConfiguration, SWT.NONE);
		lblDeviceConfiguration.setText("Device Configuration");
		lblDeviceConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceConfiguration.setBounds(10, 10, 228, 22);

		compositeConfigurationSettings = new Composite(shlDeviceConfiguration, SWT.BORDER);
		compositeConfigurationSettings.setBounds(10, 58, 646, 92);

		Label lblDeviceName = new Label(compositeConfigurationSettings, SWT.NONE);
		lblDeviceName.setBounds(44, 10, 121, 25);
		lblDeviceName.setText("Device");

		devicesCombo = new Combo(compositeConfigurationSettings, SWT.READ_ONLY);
		devicesCombo.setBounds(203, 7, 309, 25);

		Button btnRefresh = new Button(compositeConfigurationSettings, SWT.NONE);
		btnRefresh.setBounds(529, 6, 75, 33);
		btnRefresh.setText("Refresh");

		devicesCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				btnRefresh.setFocus();
			}
		});

		btnRefresh.addSelectionListener(new SelectionAdapter() {

			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String previousDevice = devicesCombo.getText();
					devicesList = AndroidDeviceUtil.getAndroidDevices();
					devicesCombo.removeAll();
					for (Map.Entry<String, String> deviceEntry : devicesList.entrySet()) {
						devicesCombo.add(deviceEntry.getValue());
					}
					devicesCombo.select(0);

					String selectedDeviceDetails = devicesCombo.getText();
					String newDeviceUDID = AndroidDeviceUtil.getSelectedAndroidDeviceId(selectedDeviceDetails);
					String newDeviceModelName = AndroidDeviceUtil.getDeviceName(newDeviceUDID);

					MobileCapabilities.getinstance();

					if (previousDevice.trim() != "") {
						String previousDeviceModelName = AndroidDeviceUtil
								.getDeviceName(AndroidDeviceUtil.getSelectedAndroidDeviceId(previousDevice));
						MobileCapabilities.getinstance().getMapOfCapabilities().replace("deviceName",
								previousDeviceModelName);
					} else {
						MobileCapabilities.getinstance().getMapOfCapabilities().put("deviceName", newDeviceModelName);
					}

					if (previousDevice.trim() != "") {
						String previousDeviceUDID = AndroidDeviceUtil.getSelectedAndroidDeviceId(previousDevice);
						MobileCapabilities.getinstance().getMapOfCapabilities().replace("udid", previousDeviceUDID);
					} else {
						MobileCapabilities.getinstance().getMapOfCapabilities().put("udid", newDeviceUDID);
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
		lblApplicationFile.setBounds(44, 49, 121, 25);
		lblApplicationFile.setText("Application");

		applicationPathText = new Text(compositeConfigurationSettings, SWT.BORDER);
		applicationPathText.setBounds(203, 46, 309, 33);

		Button btnBrowseAPK = new Button(compositeConfigurationSettings, SWT.NONE);
		btnBrowseAPK.setBounds(529, 44, 75, 33);
		btnBrowseAPK.setText("Browse");

		Button btnNext = new Button(shlDeviceConfiguration, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (devicesCombo.getText().isEmpty()) {
					MessageDialog.openInformation(shlDeviceConfiguration, "Please Note", "Device Cannot Be Empty");
				} else if (applicationPathText.getText().isEmpty()) {
					MessageDialog.openInformation(shlDeviceConfiguration, "Please Note", "Application Cannot Be Empty");
				} else {
					try {
						AppiumServer.stopServer();
						Thread.sleep(4000);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
					java.util.concurrent.Executors.newSingleThreadExecutor().execute(new Runnable() {

						@Override
						public void run() {
							AppiumServer.startServer();

							DesiredCapabilities mobileCapability = (MobileCapabilities.getCapabilities());

							try {
								AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
										new URL("http://" + AppiumPortIpInfo.getHostAddress() + ":"
												+ AppiumPortIpInfo.getPort() + "/wd/hub"),
										mobileCapability);
								AndroidDriverObject.getInstance().setDriver(driver);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					});
				}
			}
		});
		btnNext.setBounds(438, 175, 105, 33);
		btnNext.setText("Next");

		Button btnCancel = new Button(shlDeviceConfiguration, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDeviceConfiguration.close();
			}
		});
		btnCancel.setBounds(551, 175, 105, 33);
		btnCancel.setText("Cancel");
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
						MessageDialog.openInformation(shlDeviceConfiguration, "Please Note",
								"Application APK file you provided doesn't exist!");
					}
				} else {
					MessageDialog.openInformation(shlDeviceConfiguration, "Please Note",
							"Application APK file you provided doesn't exist!");
				}
			}
		});

	}
}
