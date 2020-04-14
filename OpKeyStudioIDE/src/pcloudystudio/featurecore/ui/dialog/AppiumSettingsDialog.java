package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.OpKeyStudioPreferences;
import pcloudystudio.appium.AppiumPortIpInfo;
import pcloudystudio.appium.MobileCapabilities;

public class AppiumSettingsDialog extends Dialog {

	private String capabilityNameList[] = { "platformName", "automationName", "launchTimeout", "newCommandTimeout",
			"platformVersion", "appActivity", "appPackage", "enforceAppInstall", "noSign" };

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
	private Composite compositeDeviceCapabilities;
	private Label lblDeviceCapability;
	private Composite addCapabilityComposite;
	private Button saveInfo;
	private static Table capabilityTable;
	private Composite compositeAddCapability;
	private Button btnDelete;
	private Button btnAdd;
	private Combo capabilityNameCombo;
	private Text capabilityTextValue;
	private Button btnAddToTable;
	private Composite manuallyAddCapabilityComposite2;
	private Text manuallyCapabilityName;
	private Text manuallyCapabilityValue;
	private Button addToTable2;
	private Button manuallyCancel;
	private Combo combo_DataType;
	private Combo combo_ManualType;
	String Types[] = { "int", "String", "boolean" };

	public AppiumSettingsDialog(Shell parent, int style) {
		super(parent, SWT.DIALOG_TRIM);
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
		shlAppiumSettings
		.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		shlAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlAppiumSettings.setSize(669, 623);
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
		lblHeading.setBounds(29, 10, 209, 33);
		lblHeading.setText("Appium Settings");

		clblLogo = new CLabel(compositeTopHeading, SWT.NONE);
		clblLogo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clblLogo.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/appium_logo.jpg"));
		clblLogo.setBounds(519, 10, 113, 33);

		compositeAppiumSettingHeading = new Composite(shlAppiumSettings, SWT.NONE);
		compositeAppiumSettingHeading.setBounds(10, 63, 291, 45);
		compositeAppiumSettingHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		lblAppiumSettings = new Label(compositeAppiumSettingHeading, SWT.NONE);
		lblAppiumSettings.setText("Provide Appium Host and Port");
		lblAppiumSettings.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblAppiumSettings.setBounds(10, 10, 246, 33);

		compositeAppiumSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettings.setBounds(10, 114, 646, 145);

		Label labelServerAddress = new Label(compositeAppiumSettings, SWT.NONE);
		labelServerAddress.setText("Server Address");
		labelServerAddress.setBounds(30, 13, 128, 33);

		Label labelPort = new Label(compositeAppiumSettings, SWT.NONE);
		labelPort.setText("Port");
		labelPort.setBounds(30, 52, 60, 33);

		serverAddress = new Text(compositeAppiumSettings, SWT.BORDER);
		serverAddress.setToolTipText("Please Provide LocalHost Address");
		serverAddress.setBounds(205, 10, 309, 33);

		portNumber = new Text(compositeAppiumSettings, SWT.BORDER);
		portNumber.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				restrictInputOnPort(e);
			}
		});
		portNumber.setToolTipText("Please Provide Port ");
		portNumber.setBounds(205, 49, 309, 33);

		appiumDirectory = new Text(compositeAppiumSettings, SWT.BORDER);
		appiumDirectory.setBounds(205, 88, 309, 33);

		AppiumPortIpInfo.getInstance();
		if (AppiumPortIpInfo.getHostAddress() != null) {
			serverAddress.setText(AppiumPortIpInfo.getHostAddress());
			portNumber.setText(AppiumPortIpInfo.getPort());
			appiumDirectory.setText(AppiumPortIpInfo.getAppiumDirectory());
		} else {
			if (OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address") != null) {
				AppiumPortIpInfo.getInstance()
				.setHostAddress(OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address"));
				serverAddress.setText(OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address"));
				AppiumPortIpInfo.getInstance()
				.setPort(OpKeyStudioPreferences.getPreferences().getBasicSettings("port_number"));
				portNumber.setText(OpKeyStudioPreferences.getPreferences().getBasicSettings("port_number"));
				AppiumPortIpInfo.getInstance().setAppiumDirectory(
						OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory"));
				appiumDirectory.setText(OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory"));
			}
		}

		Label label_2 = new Label(compositeAppiumSettings, SWT.NONE);
		label_2.setText("Appium Directory");
		label_2.setBounds(30, 91, 153, 33);

		Button btnBrowse = new Button(compositeAppiumSettings, SWT.NONE);
		btnBrowse.setBounds(520, 87, 75, 33);
		btnBrowse.setText("Browse");

		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dirDialog = new DirectoryDialog(shlAppiumSettings);
				dirDialog.setFilterPath(appiumDirectory.getText());
				dirDialog.setMessage("Select a directory");
				String dir = dirDialog.open();
				if (dir != null) {
					appiumDirectory.setText(dir);
					if (!appiumDirectory.getText()
							.contains("npm" + File.separator + "node_modules" + File.separator + "appium")) {

						MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
								ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
								"Invalid Appium Directory! Please select valid Appium Directory.", 1, 0, "OK");
						mDialog.open();
						appiumDirectory.setText("");
					}

				}

				if (dir == null) {
					appiumDirectory.setFocus();
				}

			}
		});

		compositeDeviceCapabilities = new Composite(shlAppiumSettings, SWT.NONE);
		compositeDeviceCapabilities.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeDeviceCapabilities.setBounds(10, 265, 200, 44);

		lblDeviceCapability = new Label(compositeDeviceCapabilities, SWT.NONE);
		lblDeviceCapability.setText("Appium Capabilities");
		lblDeviceCapability.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceCapability.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceCapability.setBounds(10, 10, 180, 33);

		compositeCapabilitySettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeCapabilitySettings.setBounds(10, 315, 646, 219);

		compositeAddCapability = new Composite(compositeCapabilitySettings, SWT.NONE);
		compositeAddCapability.setBounds(20, 5, 612, 42);

		btnAdd = new Button(compositeAddCapability, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addCapabilityComposite.setVisible(false);
				manuallyAddCapabilityComposite2.setVisible(true);
				capabilityNameCombo.removeAll();
				capabilityNameCombo.setItems(capabilityNameList);
				capabilityTextValue.setText("");
			}
		});
		btnAdd.setToolTipText("Add Capability");
		btnAdd.setBounds(10, 10, 20, 22);
		btnAdd.setText("+");

		Composite compositeForDeleteButton = new Composite(compositeAddCapability, SWT.NONE);
		compositeForDeleteButton.setBounds(36, 10, 20, 22);

		btnDelete = new Button(compositeForDeleteButton, SWT.PUSH);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (capabilityTable.getItemCount() >= 1) {
					while (capabilityTable.getSelectionIndex() != -1)
						capabilityTable.remove(capabilityTable.getSelectionIndex());
				}
				btnDelete.setEnabled(false);
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Delete Capability");
		btnDelete.setBounds(0, 0, 20, 22);
		btnDelete.setText("-");
		compositeForDeleteButton.setToolTipText("Delete Capability");

		addCapabilityComposite = new Composite(compositeAddCapability, SWT.NONE);
		addCapabilityComposite.setBounds(62, 0, 550, 42);
		addCapabilityComposite.setVisible(true);

		capabilityNameCombo = new Combo(addCapabilityComposite, SWT.READ_ONLY);
		capabilityNameCombo.setToolTipText("Please Select DataType");
		capabilityNameCombo.setBounds(10, 5, 154, 33);
		capabilityNameCombo.setItems(capabilityNameList);

		capabilityNameCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (capabilityTextValue.getText().length() > 0 && capabilityNameCombo.getSelectionIndex() != -1) {
					btnAddToTable.setEnabled(true);
				}
			}
		});

		capabilityTextValue = new Text(addCapabilityComposite, SWT.BORDER);
		capabilityTextValue.setToolTipText("Please Enter Capability Value");
		capabilityTextValue.setBounds(310, 5, 127, 28);

		capabilityTextValue.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (capabilityTextValue.getText().length() > 0 && capabilityNameCombo.getSelectionIndex() != -1) {
					btnAddToTable.setEnabled(true);
				}
			}
		});
		capabilityTextValue.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String type = combo_DataType.getText();
				btnAddToTable.setEnabled(true);
				restrictInputString(e, type);
			}
		});

		btnAddToTable = new Button(addCapabilityComposite, SWT.NONE);
		btnAddToTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String capabilityName = capabilityNameCombo.getText();
				String capabilityType = combo_DataType.getText();
				String capabilityValue = capabilityTextValue.getText();

				if (validateCapabilityNameAndValue(capabilityName, capabilityValue, capabilityType)) {
					addTableItemToCapabilityTableData(capabilityName, capabilityType, capabilityValue);

				}
				btnAddToTable.setEnabled(false);
				capabilityTextValue.setText("");
				capabilityNameCombo.setText("");
			}
		});
		btnAddToTable.setBounds(443, 3, 97, 30);
		btnAddToTable.setText("Add to Table");
		btnAddToTable.setEnabled(false);

		combo_DataType = new Combo(addCapabilityComposite, SWT.READ_ONLY); // change to if want to modifiable
		combo_DataType.setToolTipText("Please Select DataType ");
		combo_DataType.setBounds(170, 5, 134, 28);
		combo_DataType.setItems(Types);
		combo_DataType.setText("String");

		manuallyAddCapabilityComposite2 = new Composite(compositeAddCapability, SWT.NONE);
		manuallyAddCapabilityComposite2.setBounds(62, 0, 550, 42);
		manuallyAddCapabilityComposite2.setVisible(false);

		manuallyCapabilityName = new Text(manuallyAddCapabilityComposite2, SWT.BORDER);
		manuallyCapabilityName.setToolTipText("Please Enter Capability Name ");
		manuallyCapabilityName.setBounds(10, 5, 102, 28);

		combo_ManualType = new Combo(manuallyAddCapabilityComposite2, SWT.READ_ONLY);
		combo_ManualType.setToolTipText("Please Select DataType ");
		combo_ManualType.setBounds(120, 5, 102, 28);
		combo_ManualType.setItems(Types);
		combo_ManualType.setText("String");

		manuallyCapabilityValue = new Text(manuallyAddCapabilityComposite2, SWT.BORDER);
		manuallyCapabilityValue.setToolTipText("Please Enter Capability Value ");
		manuallyCapabilityValue.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				addToTable2.setEnabled(true);
			}
		});
		manuallyCapabilityValue.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String type = combo_ManualType.getText();
				addToTable2.setEnabled(true);
				restrictInputString(e, type);
			}
		});
		manuallyCapabilityValue.setBounds(230, 5, 137, 28);

		addToTable2 = new Button(manuallyAddCapabilityComposite2, SWT.NONE);
		addToTable2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String cap_name = manuallyCapabilityName.getText();
				String cap_value = manuallyCapabilityValue.getText();
				String cap_type = combo_ManualType.getText();

				if (validateCapabilityNameAndValue(cap_name, cap_value, cap_type)) {
					addTableItemToCapabilityTableData(cap_name, cap_type, cap_value);
				}
				manuallyCapabilityName.setText("");
				manuallyCapabilityValue.setText("");
				manuallyAddCapabilityComposite2.setVisible(false);
				addToTable2.setEnabled(false);
				addCapabilityComposite.setVisible(true);
			}
		});
		addToTable2.setBounds(379, 4, 86, 30);
		addToTable2.setText("Add To Table");
		addToTable2.setEnabled(false);

		manuallyCancel = new Button(manuallyAddCapabilityComposite2, SWT.NONE);
		manuallyCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				manuallyCapabilityName.setText("");
				manuallyCapabilityValue.setText("");
				manuallyAddCapabilityComposite2.setVisible(false);
				addCapabilityComposite.setVisible(true);
			}
		});
		manuallyCancel.setBounds(469, 4, 81, 30);
		manuallyCancel.setText("Cancel");

		ScrolledComposite scrolledComposite = new ScrolledComposite(compositeCapabilitySettings,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(20, 53, 612, 138);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		capabilityTable = new Table(scrolledComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		capabilityTable.setLinesVisible(true);
		capabilityTable.setBounds(0, 0, 612, 135);
		capabilityTable.setHeaderVisible(true);
		TableColumn columnName = new TableColumn(capabilityTable, 0);
		columnName.setText("Name");
		columnName.setWidth(201);
		TableColumn columnType = new TableColumn(capabilityTable, 0);
		columnType.setText("Data Type");
		columnType.setWidth(227);
		TableColumn columnValue = new TableColumn(capabilityTable, 0);
		columnValue.setText("Value");
		columnValue.setWidth(178);

		fillDataInCapabilityTable();

		TableEditor editor = new TableEditor(capabilityTable);
		saveInfo = new Button(shlAppiumSettings, SWT.NONE);
		saveInfo.setBounds(465, 540, 91, 33);
		saveInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Boolean status = validate();
				if (status) {
					OpKeyStudioPreferences.getPreferences().addBasicSettings("host_address", serverAddress.getText());
					AppiumPortIpInfo.getInstance().setHostAddress(serverAddress.getText());
					OpKeyStudioPreferences.getPreferences().addBasicSettings("port_number", portNumber.getText());
					AppiumPortIpInfo.getInstance().setPort(portNumber.getText());
					OpKeyStudioPreferences.getPreferences().addBasicSettings("appium_directory",
							appiumDirectory.getText());
					AppiumPortIpInfo.getInstance().setAppiumDirectory(appiumDirectory.getText());
					if (capabilityTable.getItemCount() >= 0) {
						try {
							MobileCapabilities.getinstance();
							if (MobileCapabilities.getMapOfCapabilities() != null) {
								MobileCapabilities.getinstance().setMapOfCapabilities(null);

							}
							if (MobileCapabilities.getMapOfCapabilityNameType() != null) {
								MobileCapabilities.setMapOfCapabilityNameType(null);

							}
							LinkedHashMap<String, String> mapOfCapabilities = new LinkedHashMap<String, String>();
							LinkedHashMap<String, String> mapOfCapabilityValueType = new LinkedHashMap<String, String>();
							for (TableItem row : capabilityTable.getItems()) {
								mapOfCapabilities.put(row.getText(0), row.getText(2));
								mapOfCapabilityValueType.put(row.getText(0), row.getText(1));
							}

							MobileCapabilities.getinstance().setMapOfCapabilities(mapOfCapabilities);
							MobileCapabilities.setMapOfCapabilityNameType(mapOfCapabilityValueType);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Please Note",
							ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
							"Settings Saved Successfully!", 2, 0, "OK");
					mDialog.open();
				}
			}
		});
		saveInfo.setText("Save");

		closebutton = new Button(shlAppiumSettings, SWT.NONE);
		closebutton.setBounds(565, 540, 91, 33);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAppiumSettings.close();
			}
		});
		closebutton.setText("Close");
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		capabilityTable.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				btnDelete.setEnabled(true);
				Rectangle clientArea = capabilityTable.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = capabilityTable.getTopIndex();
				while (index < capabilityTable.getItemCount()) {
					boolean visible = false;
					TableItem item = capabilityTable.getItem(index);
					for (int i = 0; i < capabilityTable.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(pt) && i == 2) {
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
	}

	private void addTableItemToCapabilityTableData(String key, String type, String value) {
		TableItem item = new TableItem(capabilityTable, SWT.NONE);
		item.setText(0, key);
		item.setText(1, type);
		item.setText(2, value);
		btnAddToTable.setEnabled(false);
		// item.setText(new String[] { key, value });
	}

	public Boolean validate() {
		if (serverAddress.getText().isEmpty()) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Please Note",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Host Address can't be empty!", 2, 0, "OK");
			mDialog.open();
			serverAddress.setFocus();
			return false;
		} else if (!validateLocalHost(serverAddress.getText())) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Invalid Host Address!", 1, 0, "OK");
			mDialog.open();
			serverAddress.setFocus();
			return false;
		} else if (portNumber.getText().isEmpty()) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Please Note",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Port can't be empty!", 2, 0, "OK");
			mDialog.open();
			portNumber.setFocus();
			return false;
		} else if (!validatePort(portNumber.getText())) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Invalid Port!", 1, 0, "OK");
			mDialog.open();
			portNumber.setText("");
			portNumber.setFocus();
			return false;
		} else if (appiumDirectory.getText().isEmpty() || appiumDirectory.getText().equals("")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Please Note",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Appium Directory can't be empty!", 2, 0, "OK");
			mDialog.open();
			return false;
		} else if (!appiumDirectory.getText()
				.contains("npm" + File.separator + "node_modules" + File.separator + "appium")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Invalid Appium Directory! Please select valid Appium Directory.", 1, 0, "OK");
			mDialog.open();
			return false;
		}

		else if (capabilityTable.getItemCount() == 0) {
			return true;
		} else {
			return true;
		}
	}

	public Boolean validatePort(String str) {
		final String portRegex = "^(6553[0-5]|655[0-2]\\d|65[0-4]\\d\\d|6[0-4]\\d{3}|[1-5]\\d{4}|[2-9]\\d{3}|1[1-9]\\d{2}|10[3-9]\\d|102[4-9])$";
		Pattern VALID_PORT_PATTERN = Pattern.compile(portRegex, Pattern.CASE_INSENSITIVE);
		return VALID_PORT_PATTERN.matcher(str).matches();
	}

	public Boolean validateLocalHost(String str) {
		final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
		Pattern VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
		if (str.trim().equals("localhost")) // driver throwing exception invalid host and port address.
			return true;
		return VALID_IPV4_PATTERN.matcher(str).matches();
	}

	public Boolean validateCapabilityNameAndValue(String capName, String capValue, String capType) {
		if (capName.isEmpty() || capName.equals("")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Capability Name Cannot Be Empty", 1, 0, "OK");
			mDialog.open();
			return false;
		}
		if (capValue.isEmpty() || capValue.equals("")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Capability Value Cannot Be Empty", 1, 0, "OK");
			mDialog.open();
			return false;
		}
		if (capType.isEmpty() || capType.equals("")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Capability Type Cannot Be Empty", 1, 0, "OK");
			mDialog.open();
			return false;
		}

		return true;
	}

	private void fillDataInCapabilityTable() {
		LinkedHashMap<String, String> mapOfCapabilities = MobileCapabilities.getMapOfCapabilities();
		String udid = "udid";
		String app = "app";
		String deviceName = "deviceName";
		if (mapOfCapabilities != null) {
			if (mapOfCapabilities.containsKey(deviceName))
				mapOfCapabilities.remove(deviceName);
			if (mapOfCapabilities.containsKey(app))
				mapOfCapabilities.remove(app);
			if (mapOfCapabilities.containsKey(udid))
				mapOfCapabilities.remove(udid);

			for (String capabilityName : mapOfCapabilities.keySet()) {
				String capabilityType = getType(capabilityName).trim();
				String capabilityValue = mapOfCapabilities.get(capabilityName).trim();
				addTableItemToCapabilityTableData(capabilityName, capabilityType, capabilityValue);

			}
		}
	}

	protected void restrictInputOnPort(VerifyEvent event) {
		String allowedCharactersInteger = "0123456789";
		String text = event.text;

		for (int index = 0; index < text.length(); index++) {
			char character = text.charAt(index);
			boolean isAllowed = allowedCharactersInteger.indexOf(character) > -1;
			if (portNumber.getText().length() > 4) {
				event.doit = false;
				return;
			}
			if (!isAllowed) {
				event.doit = false;
				return;
			}
		}

	}

	protected void restrictInputString(VerifyEvent event, String type) {
		String allowedCharactersInteger = "1234567890";
		String allowedCharactersBoolean = "truefalse01";
		String allowedCharactersString = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890.\\-+/@$%^&*(){}<>";
		String text = event.text;

		switch (type) {
		case "int":
			for (int index = 0; index < text.length(); index++) {
				char character = text.charAt(index);
				boolean isAllowed = allowedCharactersInteger.indexOf(character) > -1;
				if (!isAllowed) {
					event.doit = false;
					return;
				}
			}
			break;

		case "boolean":
			for (int index = 0; index < text.length(); index++) {
				char character = text.charAt(index);
				boolean isAllowed = allowedCharactersBoolean.indexOf(character) > -1;
				if (!isAllowed) {
					event.doit = false;
					return;
				}
			}

			break;

		default:
			if (capabilityTextValue.getText().length() >= 0) {
				for (int index = 0; index < text.length(); index++) {
					char character = text.charAt(index);
					boolean isAllowed = allowedCharactersString.indexOf(character) > -1;
					if (!isAllowed) {
						event.doit = false;
						return;
					}
				}

			}
		}

	}

	private String getType(String capName) {

		LinkedHashMap<String, String> mapOfCapabilityNameType = new LinkedHashMap<String, String>();
		mapOfCapabilityNameType = MobileCapabilities.getMapOfCapabilityNameType();
		if (mapOfCapabilityNameType != null) {
			if (mapOfCapabilityNameType.containsKey(capName)) {
				return mapOfCapabilityNameType.get(capName);
			}
		}

		return "String";
	}

}
