package pcloudystudio.featurecore.ui.dialog;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import opkeystudio.core.utils.OpKeyStudioPreferences;
import pcloudystudio.appiumserver.AppiumServer;
import pcloudystudio.capability.AndroidDefaultCapabilities;
import pcloudystudio.capability.AndroidDriverObject;
import pcloudystudio.core.mobile.AndroidDeviceUtil;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;

public class AppiumSettingsDialog extends Dialog {

	private String capabilityNameList[] = { "platformName", "automationName", "launchTimeout", "newCommandTimeout" };

	protected Object result;
	protected Shell shlAppiumSettings;
	private Text serverAddress;
	private Text portNumber;
	private Text appiumDirectory;

	private Composite compositeTopHeading;
	private Label lblHeading;
	private CLabel clblLogo;
	private Button closebutton;
	private Composite compositeAppiumSettingHeading;
	private Label lblAppiumSettings;
	private Composite compositeAppiumSettings;
	private Composite compositeCapabilitySettings;
	private Composite compositeConfiguration;
	private Label lblDeviceConfiguration;
	private Composite compositeDeviceCapabilities;
	private Label lblDeviceCapability;
	private Composite compositeConfigurationSettings;
	private Composite addCapabilityComposite;

	private Map<String, String> devicesList;
	private Combo devicesCombo;
	private Text applicationPathText;
	private Button btnStartServerAndLaunchApplication;
	private Table capabilityTable;

	private Composite compositeAddCapability;
	private Button btnDelete;
	private Button btnAdd;
	private Combo capabilityNameCombo;
	private Text capabilityTextValue;
	private Button btnAddToTable;

	public AppiumSettingsDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	public Object open() {

		createContents();
		shlAppiumSettings.open();
		shlAppiumSettings.layout();
		Display display = getParent().getDisplay();
		while (!shlAppiumSettings.isDisposed()) {
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

	private void createContents() {
		shlAppiumSettings = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlAppiumSettings.setSize(672, 803);
		shlAppiumSettings.setText("Appium Settings");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlAppiumSettings.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlAppiumSettings.setLocation(new Point(locationX, locationY));

		compositeTopHeading = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeTopHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeTopHeading.setBounds(10, 10, 646, 64);

		lblHeading = new Label(compositeTopHeading, SWT.NONE);
		lblHeading.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblHeading.setBounds(31, 11, 164, 23);
		lblHeading.setText("Appium Settings");

		clblLogo = new CLabel(compositeTopHeading, SWT.NONE);
		clblLogo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clblLogo.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/appium_logo.jpg"));
		clblLogo.setBounds(523, 10, 113, 44);

		compositeAppiumSettingHeading = new Composite(shlAppiumSettings, SWT.NONE);
		compositeAppiumSettingHeading.setBounds(10, 80, 244, 32);
		compositeAppiumSettingHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		lblAppiumSettings = new Label(compositeAppiumSettingHeading, SWT.NONE);
		lblAppiumSettings.setText("Provide Appium Host and Port");
		lblAppiumSettings.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblAppiumSettings.setBounds(10, 10, 200, 15);

		compositeAppiumSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettings.setBounds(10, 118, 646, 181);

		Label labelServerAddress = new Label(compositeAppiumSettings, SWT.NONE);
		labelServerAddress.setText("Server Address");
		labelServerAddress.setBounds(30, 27, 80, 21);

		Label labelPort = new Label(compositeAppiumSettings, SWT.NONE);
		labelPort.setText("Port");
		labelPort.setBounds(30, 71, 60, 21);

		serverAddress = new Text(compositeAppiumSettings, SWT.BORDER);
		serverAddress.setToolTipText("Host");
		serverAddress.setBounds(150, 24, 309, 24);

		portNumber = new Text(compositeAppiumSettings, SWT.BORDER);
		portNumber.setToolTipText("Port");
		portNumber.setBounds(150, 68, 309, 24);

		appiumDirectory = new Text(compositeAppiumSettings, SWT.BORDER);
		appiumDirectory.setBounds(150, 115, 309, 24);

		String server = OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_host");
		String port = OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_port");
		String appiumDir = OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory");

		if (server != null) {
			serverAddress.setText(server);
		}

		if (port != null) {
			portNumber.setText(port);
		}

		if (appiumDir != null) {
			appiumDirectory.setText(appiumDir);
		}

		Label label_2 = new Label(compositeAppiumSettings, SWT.NONE);
		label_2.setText("Appium Directory");
		label_2.setBounds(30, 115, 100, 21);

		Button btnBrowse = new Button(compositeAppiumSettings, SWT.NONE);
		btnBrowse.setBounds(465, 113, 75, 25);
		btnBrowse.setText("Browse");

		Button saveButton = new Button(compositeAppiumSettings, SWT.NONE);
		saveButton.setBounds(384, 145, 75, 25);
		saveButton.setToolTipText("Save");
		saveButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String host = serverAddress.getText();
				String port = portNumber.getText();
				String appiumDirectoryPath = appiumDirectory.getText();
				OpKeyStudioPreferences.getPreferences().addBasicSettings("appium_host", host);
				OpKeyStudioPreferences.getPreferences().addBasicSettings("appium_port", port);
				OpKeyStudioPreferences.getPreferences().addBasicSettings("appium_directory", appiumDirectoryPath);
				if (host.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlAppiumSettings, "Invalid Host", "Please enter Host URL");
				}
				if (port.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlAppiumSettings, "Invalid Port", "Please enter Port");
				}
				if (appiumDirectoryPath.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlAppiumSettings, "Invalid Directory",
							"Please browse Appium Directory");
				}
			}
		});
		saveButton.setText("Save");

		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dirDialog = new DirectoryDialog(shlAppiumSettings);
				dirDialog.setFilterPath(appiumDirectory.getText());
				dirDialog.setText("SWT's DirectoryDialog");
				dirDialog.setMessage("Select a directory");
				String dir = dirDialog.open();
				if (dir != null) {
					appiumDirectory.setText(dir);
				}
			}
		});

		compositeConfiguration = new Composite(shlAppiumSettings, SWT.NONE);
		compositeConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeConfiguration.setBounds(10, 305, 119, 32);

		lblDeviceConfiguration = new Label(compositeConfiguration, SWT.NONE);
		lblDeviceConfiguration.setText("Configuration");
		lblDeviceConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceConfiguration.setBounds(10, 10, 99, 15);

		compositeConfigurationSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeConfigurationSettings.setBounds(10, 343, 646, 145);

		Label lblDeviceName = new Label(compositeConfigurationSettings, SWT.NONE);
		lblDeviceName.setBounds(29, 25, 76, 15);
		lblDeviceName.setText("Device Name");

		devicesCombo = new Combo(compositeConfigurationSettings, SWT.READ_ONLY);
		devicesCombo.setBounds(150, 22, 309, 23);

		Button btnRefresh = new Button(compositeConfigurationSettings, SWT.NONE);
		btnRefresh.setBounds(465, 20, 75, 25);
		btnRefresh.setText("Refresh");

		btnRefresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					devicesList = AndroidDeviceUtil.getAndroidDevices();
					devicesCombo.removeAll();
					for (Map.Entry<String, String> deviceEntry : devicesList.entrySet()) {
						devicesCombo.add(deviceEntry.getValue());
					}
					devicesCombo.select(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label lblApplicationFile = new Label(compositeConfigurationSettings, SWT.NONE);
		lblApplicationFile.setBounds(29, 64, 87, 15);
		lblApplicationFile.setText("Application File");

		applicationPathText = new Text(compositeConfigurationSettings, SWT.BORDER);
		applicationPathText.setBounds(150, 61, 309, 21);

		String appFilePath = OpKeyStudioPreferences.getPreferences().getBasicSettings("application_name");
		if (appFilePath != null) {
			applicationPathText.setText(appFilePath);
		}
		Button btnBrowseAPK = new Button(compositeConfigurationSettings, SWT.NONE);
		btnBrowseAPK.setBounds(465, 59, 75, 25);
		btnBrowseAPK.setText("Browse");
		btnBrowseAPK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlAppiumSettings, SWT.NULL);
				String path = dialog.open();
				if (path != null) {
					TableItem row=new TableItem(capabilityTable, 0);
					row.setText(0,"app");
					row.setText(1,path);
					File file = new File(path);
					if (file.isFile())
						displayFiles(new String[] { file.toString() });
					else
						displayFiles(file.list());
				}
			}
		});

		Button saveButtonConfiguration = new Button(compositeConfigurationSettings, SWT.NONE);
		saveButtonConfiguration.setBounds(384, 88, 75, 25);
		saveButtonConfiguration.setToolTipText("Save");
		saveButtonConfiguration.setText("Save");
		saveButtonConfiguration.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		saveButtonConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String applicationDirPathText = applicationPathText.getText();
				String selectedDeviceID = getSelectedAndroidDeviceId(devicesCombo.getText());
				OpKeyStudioPreferences.getPreferences().addBasicSettings("selected_applucation_apk_path",
						applicationDirPathText);
				OpKeyStudioPreferences.getPreferences().addBasicSettings("selected_device_id", selectedDeviceID);
				if (applicationDirPathText.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlAppiumSettings, "Invalid Application file path",
							"Please browse Application file");
				}
				if (selectedDeviceID.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlAppiumSettings, "Invalid Device selection!",
							"Please Connect device to system!");
				}
			}
		});

		compositeDeviceCapabilities = new Composite(shlAppiumSettings, SWT.NONE);
		compositeDeviceCapabilities.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeDeviceCapabilities.setBounds(10, 494, 135, 32);

		lblDeviceCapability = new Label(compositeDeviceCapabilities, SWT.NONE);
		lblDeviceCapability.setText("Device Capabilities");
		lblDeviceCapability.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceCapability.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceCapability.setBounds(10, 10, 115, 15);

		compositeCapabilitySettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeCapabilitySettings.setBounds(10, 532, 646, 201);

		compositeAddCapability = new Composite(compositeCapabilitySettings, SWT.NONE);
		compositeAddCapability.setBounds(10, 5, 622, 42);

		btnAdd = new Button(compositeAddCapability, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addCapabilityComposite.setVisible(true);
				capabilityNameCombo.removeAll();
				capabilityNameCombo.setItems(capabilityNameList);
				capabilityTextValue.setText("");
			}
		});
		btnAdd.setBounds(71, 10, 20, 22);
		btnAdd.setText("+");

		btnDelete = new Button(compositeAddCapability, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addCapabilityComposite.setVisible(false);
				if (capabilityTable.getItemCount() >= 1) {
					capabilityTable.remove(capabilityTable.getSelectionIndex());
				}
			}
		});
		btnDelete.setBounds(97, 10, 20, 22);
		btnDelete.setText("-");

		addCapabilityComposite = new Composite(compositeAddCapability, SWT.BORDER);
		addCapabilityComposite.setBounds(129, 0, 428, 42);
		addCapabilityComposite.setVisible(false);

		capabilityNameCombo = new Combo(addCapabilityComposite, SWT.READ_ONLY);
		capabilityNameCombo.setBounds(10, 10, 154, 28);
		capabilityNameCombo.setItems(capabilityNameList);

		capabilityTextValue = new Text(addCapabilityComposite, SWT.BORDER);
		capabilityTextValue.setBounds(170, 10, 154, 23);

		btnAddToTable = new Button(addCapabilityComposite, SWT.NONE);
		btnAddToTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String capabilityName = capabilityNameCombo.getText();
				String capabilityValue = capabilityTextValue.getText();
				TableItem row = new TableItem(capabilityTable, 0);
				if ((capabilityName != "" || capabilityName != null)
						&& (capabilityValue != null || capabilityValue != "")) {
					row.setText(0, capabilityName);
					row.setText(1, capabilityValue);
				}
				addCapabilityComposite.setVisible(false);
			}
		});
		btnAddToTable.setBounds(330, 8, 78, 25);
		btnAddToTable.setText("Add to Table");

		ScrolledComposite scrolledComposite = new ScrolledComposite(compositeCapabilitySettings,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(72, 53, 495, 135);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		capabilityTable = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		capabilityTable.setBounds(0, 0, 495, 135);
		capabilityTable.setHeaderVisible(true);
		capabilityTable.setLinesVisible(true);
		TableColumn columnName = new TableColumn(capabilityTable, 0);
		columnName.setText("Name");
		columnName.setWidth(139);
		TableColumn columnValue = new TableColumn(capabilityTable, 0);
		columnValue.setText("Value");
		columnValue.setWidth(350);

		TableEditor editor = new TableEditor(capabilityTable);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		capabilityTable.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = capabilityTable.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = capabilityTable.getTopIndex();
				while (index < capabilityTable.getItemCount()) {
					boolean visible = false;
					TableItem item = capabilityTable.getItem(index);
					for (int i = 0; i < capabilityTable.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(pt) && i == 1) {
							int column = i;
							Text text = new Text(capabilityTable, SWT.NONE);
							Listener textListener = new Listener() {
								public void handleEvent(Event e) {
									switch (e.type) {
									case SWT.FocusOut:
										item.setText(column, text.getText());
										text.dispose();
										break;
									case SWT.Traverse:
										switch (e.detail) {
										case SWT.TRAVERSE_RETURN:
											item.setText(column, text.getText());
											// FALL THROUGH
										case SWT.TRAVERSE_ESCAPE:
											text.dispose();
											e.doit = false;
										}
										break;
									}
								}
							};
							text.addListener(SWT.FocusOut, textListener);
							text.addListener(SWT.Traverse, textListener);
							editor.setEditor(text, item, i);
							text.setText(item.getText(i));
							text.selectAll();
							text.setFocus();
							return;
						}
						if (!visible && rect.intersects(clientArea)) {
							visible = true;
						}
					}
					if (!visible)
						return;
					index++;
				}
			}
		});

		btnStartServerAndLaunchApplication = new Button(shlAppiumSettings, SWT.NONE);
		btnStartServerAndLaunchApplication.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					AppiumServer.stopServer();
					Thread.sleep(4000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				AppiumServer.startServer();

				DesiredCapabilities mobileCapability = (new AndroidDefaultCapabilities().getCapabilities());
				String selectedDeviceID = OpKeyStudioPreferences.getPreferences()
						.getBasicSettings("selected_device_id");
				String selectedApplucationAPKPath = OpKeyStudioPreferences.getPreferences()
						.getBasicSettings("selected_applucation_apk_path");
				if (selectedDeviceID != null) {
					String deviceModel = null;
					try {
						deviceModel = AndroidDeviceUtil.getDeviceName(selectedDeviceID);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (deviceModel != null)
						mobileCapability.setCapability("deviceName", deviceModel);
				}
				if (selectedApplucationAPKPath != null) {
					mobileCapability.setCapability("app", selectedApplucationAPKPath);
				}

				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
							new URL("http://" + "127.0.0.1" + ":" + "4723" + "/wd/hub"), mobileCapability);
					AndroidDriverObject.getInstance().setDriver(driver);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnStartServerAndLaunchApplication.setBounds(462, 739, 112, 25);
		btnStartServerAndLaunchApplication.setText("Start Server");

		closebutton = new Button(shlAppiumSettings, SWT.NONE);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAppiumSettings.close();
			}
		});
		closebutton.setBounds(581, 739, 75, 25);
		closebutton.setText("Close");
	}

	public void displayFiles(String[] files) {
		for (int i = 0; files != null && i < files.length; i++) {
			applicationPathText.setText(files[i]);
			applicationPathText.setEditable(true);
		}
	}

	private String getSelectedAndroidDeviceId(String selectedDeviceName) {
		String deviceID = null;
		if (selectedDeviceName != null || selectedDeviceName != "") {
			for (Entry<String, String> entry : this.devicesList.entrySet()) {
				if (entry.getValue().equals(selectedDeviceName)) {
					deviceID = entry.getKey();
					break;
				}
			}
		}
		return deviceID;
	}
}
