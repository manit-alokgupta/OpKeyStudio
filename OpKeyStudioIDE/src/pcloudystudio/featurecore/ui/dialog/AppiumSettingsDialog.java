package pcloudystudio.featurecore.ui.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import pcloudystudio.appium.AndroidDriverObject;
import pcloudystudio.appium.AppiumPortIpInfo;
import pcloudystudio.appium.AppiumServer;
import pcloudystudio.appium.MobileCapabilities;
import pcloudystudio.core.mobile.util.AndroidDeviceUtil;

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
	private static Table capabilityTable;

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
		shlAppiumSettings = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.SYSTEM_MODAL);
		shlAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlAppiumSettings.setSize(672, 660);
		shlAppiumSettings.setText("Appium Settings");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlAppiumSettings.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlAppiumSettings.setLocation(new Point(locationX, locationY));

		compositeTopHeading = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeTopHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeTopHeading.setBounds(10, 10, 646, 47);

		lblHeading = new Label(compositeTopHeading, SWT.NONE);
		lblHeading.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblHeading.setBounds(29, 10, 164, 23);
		lblHeading.setText("Appium Settings");

		clblLogo = new CLabel(compositeTopHeading, SWT.NONE);
		clblLogo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clblLogo.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/appium_logo.jpg"));
		clblLogo.setBounds(519, 10, 113, 33);

		compositeAppiumSettingHeading = new Composite(shlAppiumSettings, SWT.NONE);
		compositeAppiumSettingHeading.setBounds(10, 63, 244, 32);
		compositeAppiumSettingHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		lblAppiumSettings = new Label(compositeAppiumSettingHeading, SWT.NONE);
		lblAppiumSettings.setText("Provide Appium Host and Port");
		lblAppiumSettings.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblAppiumSettings.setBounds(10, 10, 200, 15);

		compositeAppiumSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettings.setBounds(10, 101, 646, 133);

		Label labelServerAddress = new Label(compositeAppiumSettings, SWT.NONE);
		labelServerAddress.setText("Server Address");
		labelServerAddress.setBounds(30, 13, 80, 21);

		Label labelPort = new Label(compositeAppiumSettings, SWT.NONE);
		labelPort.setText("Port");
		labelPort.setBounds(30, 40, 60, 21);

		serverAddress = new Text(compositeAppiumSettings, SWT.BORDER);
		serverAddress.setToolTipText("Host");
		serverAddress.setBounds(150, 10, 309, 24);

		portNumber = new Text(compositeAppiumSettings, SWT.BORDER);
		portNumber.setToolTipText("Port");
		portNumber.setBounds(150, 37, 309, 24);

		appiumDirectory = new Text(compositeAppiumSettings, SWT.BORDER);
		appiumDirectory.setBounds(150, 67, 309, 24);

		if (AppiumPortIpInfo.getHostAddress() != null) {
			serverAddress.setText(AppiumPortIpInfo.getHostAddress());
		}

		if (AppiumPortIpInfo.getPort() != null) {
			portNumber.setText(AppiumPortIpInfo.getPort());
		}

		if (AppiumPortIpInfo.getAppiumDirectory() != null) {
			appiumDirectory.setText(AppiumPortIpInfo.getAppiumDirectory());
		}

		Label label_2 = new Label(compositeAppiumSettings, SWT.NONE);
		label_2.setText("Appium Directory");
		label_2.setBounds(29, 67, 100, 21);

		Button btnBrowse = new Button(compositeAppiumSettings, SWT.NONE);
		btnBrowse.setBounds(465, 67, 75, 25);
		btnBrowse.setText("Browse");

		Button saveButton = new Button(compositeAppiumSettings, SWT.NONE);
		saveButton.setBounds(384, 97, 75, 25);
		saveButton.setToolTipText("Save");
		saveButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AppiumPortIpInfo.getInstance();
				String host = serverAddress.getText();
				if (host.trim() != "") {
					AppiumPortIpInfo.getInstance().setHostAddress(host);
				}
				String port = portNumber.getText();
				if (port.trim() != "") {
					AppiumPortIpInfo.setPort(port);
				}
				String appiumDirectoryPath = appiumDirectory.getText();
				if (appiumDirectoryPath.trim() != "") {
					AppiumPortIpInfo.setAppiumDirectory(appiumDirectoryPath);
				}
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
		compositeConfiguration.setBounds(10, 240, 119, 32);

		lblDeviceConfiguration = new Label(compositeConfiguration, SWT.NONE);
		lblDeviceConfiguration.setText("Configuration");
		lblDeviceConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceConfiguration.setBounds(10, 10, 83, 15);

		compositeConfigurationSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeConfigurationSettings.setBounds(10, 278, 646, 71);

		Label lblDeviceName = new Label(compositeConfigurationSettings, SWT.NONE);
		lblDeviceName.setBounds(29, 10, 76, 15);
		lblDeviceName.setText("Device Name");

		devicesCombo = new Combo(compositeConfigurationSettings, SWT.READ_ONLY);
		devicesCombo.setBounds(150, 7, 309, 23);

		Button btnRefresh = new Button(compositeConfigurationSettings, SWT.NONE);
		btnRefresh.setBounds(465, 5, 75, 25);
		btnRefresh.setText("Refresh");

		btnRefresh.addSelectionListener(new SelectionAdapter() {

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
					if (capabilityTable.getItemCount() >= 0) {
						for (int row = capabilityTable.getItemCount() - 1; row >= 0; row--) {
							if (capabilityTable.getItem(row).getText(0).equalsIgnoreCase("deviceName")
									|| (capabilityTable.getItem(row).getText(0).equalsIgnoreCase("udid")))
								capabilityTable.remove(row);
						}
					}

					if (previousDevice.trim() != "") {
						String previousDeviceModelName = AndroidDeviceUtil
								.getDeviceName(AndroidDeviceUtil.getSelectedAndroidDeviceId(previousDevice));
						addTableItemToCapabilityTableData("deviceName", previousDeviceModelName);
					} else {
						addTableItemToCapabilityTableData("deviceName", newDeviceModelName);
					}

					if (previousDevice.trim() != "") {
						String previousDeviceUDID = AndroidDeviceUtil.getSelectedAndroidDeviceId(previousDevice);
						addTableItemToCapabilityTableData("udid", previousDeviceUDID);
					} else {
						addTableItemToCapabilityTableData("udid", newDeviceUDID);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label lblApplicationFile = new Label(compositeConfigurationSettings, SWT.NONE);
		lblApplicationFile.setBounds(29, 31, 87, 15);
		lblApplicationFile.setText("Application File");

		applicationPathText = new Text(compositeConfigurationSettings, SWT.BORDER);
		applicationPathText.setBounds(150, 36, 309, 21);

		Button btnBrowseAPK = new Button(compositeConfigurationSettings, SWT.NONE);
		btnBrowseAPK.setBounds(465, 36, 75, 25);
		btnBrowseAPK.setText("Browse");
		btnBrowseAPK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlAppiumSettings, SWT.NULL);
				dialog.setFilterExtensions(new String[] { "*.apk" });
				String path = dialog.open();
				int rowNumber = 0;
				if (path != null) {
					for (TableItem item : capabilityTable.getItems()) {
						if (item.getText(0).equalsIgnoreCase("app")) {
							MessageDialog.openInformation(shlAppiumSettings, "Please Note",
									"Application file will be overrided");
							capabilityTable.remove(rowNumber);
							break;
						} else
							rowNumber++;
					}

					TableItem row = new TableItem(capabilityTable, 0);
					row.setText(0, "app");
					row.setText(1, path);
					File file = new File(path);
					if (file.isFile())
						displayFiles(new String[] { file.toString() });
					else
						displayFiles(file.list());
				}
			}
		});

		compositeDeviceCapabilities = new Composite(shlAppiumSettings, SWT.NONE);
		compositeDeviceCapabilities.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeDeviceCapabilities.setBounds(10, 355, 135, 32);

		lblDeviceCapability = new Label(compositeDeviceCapabilities, SWT.NONE);
		lblDeviceCapability.setText("Device Capabilities");
		lblDeviceCapability.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceCapability.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceCapability.setBounds(10, 10, 103, 15);

		compositeCapabilitySettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeCapabilitySettings.setBounds(10, 393, 646, 201);

		compositeAddCapability = new Composite(compositeCapabilitySettings, SWT.NONE);
		compositeAddCapability.setBounds(20, 5, 612, 42);

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
		btnAdd.setBounds(10, 10, 20, 22);
		btnAdd.setText("+");

		btnDelete = new Button(compositeAddCapability, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addCapabilityComposite.setVisible(false);
				if (capabilityTable.getItemCount() >= 1) {
					if (capabilityTable.getSelectionIndex() != -1)
						capabilityTable.remove(capabilityTable.getSelectionIndex());
				}
			}
		});
		btnDelete.setBounds(36, 10, 20, 22);
		btnDelete.setText("-");

		addCapabilityComposite = new Composite(compositeAddCapability, SWT.BORDER);
		addCapabilityComposite.setBounds(62, 0, 550, 42);
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
				if ((capabilityName != "" && capabilityName != null)
						&& (capabilityValue != null && capabilityValue != "")) {
					addTableItemToCapabilityTableData(capabilityName, capabilityValue);
				}
				addCapabilityComposite.setVisible(false);
			}
		});
		btnAddToTable.setBounds(330, 8, 78, 25);
		btnAddToTable.setText("Add to Table");

		ScrolledComposite scrolledComposite = new ScrolledComposite(compositeCapabilitySettings,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(20, 53, 612, 135);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		capabilityTable = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		capabilityTable.setBounds(0, 0, 612, 135);
		capabilityTable.setHeaderVisible(true);
		capabilityTable.setLinesVisible(true);
		TableColumn columnName = new TableColumn(capabilityTable, 0);
		columnName.setText("Name");
		columnName.setWidth(139);
		TableColumn columnValue = new TableColumn(capabilityTable, 0);
		columnValue.setText("Value");
		columnValue.setWidth(467);

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
					if (capabilityTable.getItemCount() > 0) {
						LinkedHashMap<String, String> mapOfCapabilities = new LinkedHashMap<String, String>();
						for (TableItem row : capabilityTable.getItems()) {

							mapOfCapabilities.put(row.getText(0), row.getText(1));

						}
						MobileCapabilities.getinstance();
						MobileCapabilities.getinstance().setMapOfCapabilities(mapOfCapabilities);
					}
					AppiumServer.stopServer();
					Thread.sleep(4000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				AppiumServer.startServer();

				DesiredCapabilities mobileCapability = (MobileCapabilities.getCapabilities());
				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://"
							+ AppiumPortIpInfo.getHostAddress() + ":" + AppiumPortIpInfo.getPort() + "/wd/hub"),
							mobileCapability);
					AndroidDriverObject.getInstance().setDriver(driver);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnStartServerAndLaunchApplication.setBounds(466, 600, 112, 25);
		btnStartServerAndLaunchApplication.setText("Start Server");

		closebutton = new Button(shlAppiumSettings, SWT.NONE);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAppiumSettings.close();
			}
		});
		closebutton.setBounds(581, 600, 75, 25);
		closebutton.setText("Close");
	}

	private void displayFiles(String[] files) {
		for (int i = 0; files != null && i < files.length; i++) {
			applicationPathText.setText(files[i]);
			applicationPathText.setEditable(true);
		}
	}

	private void addTableItemToCapabilityTableData(String key, String value) {
		TableItem item = new TableItem(capabilityTable, SWT.NONE);
		item.setText(new String[] { key, value });
	}
}
