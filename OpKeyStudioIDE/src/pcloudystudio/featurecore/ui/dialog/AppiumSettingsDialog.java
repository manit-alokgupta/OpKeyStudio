package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
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
	private CCombo capabilityNameCombo;
	private Text capabilityTextValue;
	private Button btnAddToTable;
	private Composite manuallyAddCapabilityComposite2;
	private Text manuallyCapabilityName;
	private Text manuallyCapabilityValue;
	private Button addToTable2;
	private Button manuallyCancel;
	private CCombo combo_DataType;
	private CCombo combo_ManualType;
	private Button manuallybtnRefresh;
	String Types[] = { "int", "String", "boolean" };

	private ObjectRepositoryView parentObjectRepositoryView;
	private Button btnRefresh;

	public AppiumSettingsDialog(Shell parent, int style) {
		super(parent, SWT.DIALOG_TRIM);
		setText("SWT Dialog");
	}

	public AppiumSettingsDialog(Shell parent, int style, ObjectRepositoryView objectRepositoryView) {
		super(parent, style);
		this.setParentObjectRepositoryView(objectRepositoryView);
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

		compositeTopHeading = new Composite(shlAppiumSettings, SWT.NONE);
		compositeTopHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeTopHeading.setBounds(10, 10, 646, 81);

		lblHeading = new Label(compositeTopHeading, SWT.NONE);
		lblHeading.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblHeading.setBounds(10, 10, 209, 33);
		lblHeading.setText("Appium Settings");

		clblLogo = new CLabel(compositeTopHeading, SWT.NONE);
		clblLogo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clblLogo.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/appium_logo.jpg"));
		clblLogo.setBounds(519, 10, 113, 33);

		Label lblProvideAppiumHost = new Label(compositeTopHeading, SWT.NONE);
		lblProvideAppiumHost.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblProvideAppiumHost.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblProvideAppiumHost.setBounds(10, 49, 239, 25);
		lblProvideAppiumHost.setText("Provide Appium Host and Port");

		compositeAppiumSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettings.setBounds(10, 97, 646, 148);

		Label labelServerAddress = new Label(compositeAppiumSettings, SWT.NONE);
		labelServerAddress.setText("Server Address");
		labelServerAddress.setBounds(30, 13, 128, 33);

		Label labelPort = new Label(compositeAppiumSettings, SWT.NONE);
		labelPort.setText("Port");
		labelPort.setBounds(30, 52, 60, 33);

		serverAddress = new Text(compositeAppiumSettings, SWT.BORDER);
		serverAddress.setToolTipText("Please Provide LocalHost Address");
		serverAddress.setBounds(205, 10, 309, 33);
		serverAddress.setText("127.0.0.1");

		portNumber = new Text(compositeAppiumSettings, SWT.BORDER);
		portNumber.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				restrictInputOnPort(e);
			}
		});
		portNumber.setToolTipText("Please Provide Port ");
		portNumber.setBounds(205, 49, 309, 33);
		portNumber.setText("4723");

		appiumDirectory = new Text(compositeAppiumSettings, SWT.BORDER);
		appiumDirectory.setBounds(205, 88, 309, 33);

		AppiumPortIpInfo.getInstance();
		if (AppiumPortIpInfo.getHostAddress() != null) {
			serverAddress.setText(AppiumPortIpInfo.getHostAddress());
			portNumber.setText(AppiumPortIpInfo.getPort());
			appiumDirectory.setText(AppiumPortIpInfo.getAppiumDirectory());
		} else {
			if (OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address") != null) {
				AppiumPortIpInfo
						.setHostAddress(OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address"));
				serverAddress.setText(OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address"));
				AppiumPortIpInfo.setPort(OpKeyStudioPreferences.getPreferences().getBasicSettings("port_number"));
				portNumber.setText(OpKeyStudioPreferences.getPreferences().getBasicSettings("port_number"));
				AppiumPortIpInfo.setAppiumDirectory(
						OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory"));
				appiumDirectory.setText(OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory"));
			}
		}

		Label label_2 = new Label(compositeAppiumSettings, SWT.NONE);
		label_2.setText("Appium Directory");
		label_2.setBounds(30, 91, 153, 33);

		Button btnBrowse = new Button(compositeAppiumSettings, SWT.NONE);
		btnBrowse.setBounds(520, 97, 18, 18);
		btnBrowse.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/browse.png"));
		btnBrowse.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
		btnBrowse.setToolTipText("Browse");

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
		compositeDeviceCapabilities.setBounds(10, 251, 200, 44);

		lblDeviceCapability = new Label(compositeDeviceCapabilities, SWT.NONE);
		lblDeviceCapability.setText("Appium Capabilities");
		lblDeviceCapability.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceCapability.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceCapability.setBounds(10, 10, 180, 33);

		compositeCapabilitySettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeCapabilitySettings.setBounds(10, 301, 646, 233);

		compositeAddCapability = new Composite(compositeCapabilitySettings, SWT.NONE);
		compositeAddCapability.setBounds(20, 5, 612, 42);

		btnAdd = new Button(compositeAddCapability, SWT.NONE);
		btnAdd.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/add_icon.png"));
		btnAdd.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
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
		btnAdd.setBounds(21, 12, 18, 18);

		Composite compositeForDeleteButton = new Composite(compositeAddCapability, SWT.NONE);
		compositeForDeleteButton.setBounds(46, 5, 33, 33);

		btnDelete = new Button(compositeForDeleteButton, SWT.PUSH);
		btnDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/delete_icon.png"));
		btnDelete.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
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
		btnDelete.setBounds(0, 7, 18, 18);

		compositeForDeleteButton.setToolTipText("Delete Capability");

		addCapabilityComposite = new Composite(compositeAddCapability, SWT.NONE);
		addCapabilityComposite.setBounds(75, 0, 550, 42);
		addCapabilityComposite.setVisible(true);

		capabilityNameCombo = new CCombo(addCapabilityComposite, SWT.BORDER | SWT.READ_ONLY | SWT.FLAT);
		capabilityNameCombo.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		capabilityNameCombo.setToolTipText("Please Select Capability Name");
		capabilityNameCombo.setBounds(10, 5, 135, 33);
		capabilityNameCombo.setItems(capabilityNameList);

		capabilityNameCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (capabilityNameCombo.getText().equals("") || capabilityTextValue.getText().equals("")) {
					btnAddToTable.setEnabled(false);
				}
				if (capabilityTextValue.getText().length() > 0) {
					btnAddToTable.setEnabled(true);
				}
			}
		});

		capabilityTextValue = new Text(addCapabilityComposite, SWT.BORDER);
		capabilityTextValue.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		capabilityTextValue.setToolTipText("Please Enter Capability Value");
		capabilityTextValue.setBounds(295, 5, 135, 33);

		capabilityTextValue.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (capabilityTextValue.getText().equals("") || capabilityNameCombo.getText().equals("")) {
					btnAddToTable.setEnabled(false);
				}
				if (capabilityTextValue.getText().length() > 0 && capabilityNameCombo.getSelectionIndex() != -1) {
					btnAddToTable.setEnabled(true);
				}
			}
		});
		capabilityTextValue.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				if (capabilityTextValue.getText().length() > 0 && capabilityNameCombo.getSelectionIndex() != -1) {
					String type = combo_DataType.getText();
					btnAddToTable.setEnabled(true);
					restrictInputString(e, type);
				}
			}
		});

		btnAddToTable = new Button(addCapabilityComposite, SWT.NONE);
		btnAddToTable.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/addtotableicon.png"));
		btnAddToTable.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
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
		btnAddToTable.setBounds(445, 13, 18, 18);
		btnAddToTable.setToolTipText("Add to table");
		btnAddToTable.setEnabled(false);

		combo_DataType = new CCombo(addCapabilityComposite, SWT.BORDER | SWT.READ_ONLY | SWT.FLAT); // change to if want
		// to modifiable
		combo_DataType.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		combo_DataType.setToolTipText("Please Select DataType ");
		combo_DataType.setBounds(154, 5, 135, 33);
		combo_DataType.setItems(Types);
		combo_DataType.setText("String");

		btnRefresh = new Button(addCapabilityComposite, SWT.NONE);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (capabilityTable.getItemCount() >= 1) {
					for (int i = 0; i <= capabilityTable.getItemCount(); i++) {
						capabilityTable.remove(0);

					}
				}
				capabilityTextValue.setText("");
				fillDataInCapabilityTable();
			}
		});
		btnRefresh.setBounds(470, 13, 18, 18);
		btnRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/refreshicon.png"));
		btnRefresh.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
		btnRefresh.setToolTipText("Refresh Table");

		manuallyAddCapabilityComposite2 = new Composite(compositeAddCapability, SWT.NONE);
		manuallyAddCapabilityComposite2.setBounds(75, 0, 550, 42);
		manuallyAddCapabilityComposite2.setVisible(false);

		manuallyCapabilityName = new Text(manuallyAddCapabilityComposite2, SWT.BORDER);
		manuallyCapabilityName.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		manuallyCapabilityName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (manuallyCapabilityName.getText().equals("") || manuallyCapabilityValue.getText().equals("")) {
					addToTable2.setEnabled(false);
				}

				if (manuallyCapabilityValue.getText().length() > 0 && manuallyCapabilityName.getText().length() > 0) {
					addToTable2.setEnabled(true);
				}
			}
		});
		manuallyCapabilityName.setToolTipText("Please Enter Capability Name ");
		manuallyCapabilityName.setBounds(10, 5, 135, 33);

		combo_ManualType = new CCombo(manuallyAddCapabilityComposite2, SWT.BORDER | SWT.READ_ONLY | SWT.FLAT);
		combo_ManualType.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		combo_ManualType.setToolTipText("Please Select DataType ");
		combo_ManualType.setBounds(160, 5, 135, 33);
		combo_ManualType.setItems(Types);
		combo_ManualType.setText("String");

		manuallyCapabilityValue = new Text(manuallyAddCapabilityComposite2, SWT.BORDER);
		manuallyCapabilityValue.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		manuallyCapabilityValue.setToolTipText("Please Enter Capability Value ");
		manuallyCapabilityValue.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (manuallyCapabilityValue.getText().equals("") || manuallyCapabilityName.getText().equals("")) {
					addToTable2.setEnabled(false);
				}
				if (manuallyCapabilityValue.getText().length() > 0 && manuallyCapabilityName.getText().length() > 0) {
					addToTable2.setEnabled(true);
				}
			}
		});
		manuallyCapabilityValue.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				if (manuallyCapabilityValue.getText().length() > 0 && manuallyCapabilityName.getText().length() > 0) {
					String type = combo_ManualType.getText();
					addToTable2.setEnabled(true);
					restrictInputString(e, type);
				}
			}
		});
		manuallyCapabilityValue.setBounds(310, 5, 135, 33);

		addToTable2 = new Button(manuallyAddCapabilityComposite2, SWT.NONE);
		addToTable2.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/addtotableicon.png"));
		addToTable2.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
		addToTable2.setToolTipText("Add to table");
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
				addToTable2.setEnabled(false);

			}
		});
		addToTable2.setBounds(450, 13, 18, 18);
		addToTable2.setEnabled(false);

		manuallyCancel = new Button(manuallyAddCapabilityComposite2, SWT.NONE);
		manuallyCancel.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/cancelicon.png"));
		manuallyCancel.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
		manuallyCancel.setToolTipText("Cancel");
		manuallyCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				manuallyCapabilityName.setText("");
				manuallyCapabilityValue.setText("");
				manuallyAddCapabilityComposite2.setVisible(false);
				addCapabilityComposite.setVisible(true);
			}
		});
		manuallyCancel.setBounds(496, 13, 18, 18);

		manuallybtnRefresh = new Button(manuallyAddCapabilityComposite2, SWT.NONE);
		manuallybtnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (capabilityTable.getItemCount() >= 1) {
					for (int i = 0; i <= capabilityTable.getItemCount(); i++) {
						capabilityTable.remove(0);

					}
				}
				manuallyCapabilityName.setText("");
				manuallyCapabilityValue.setText("");
				fillDataInCapabilityTable();
			}
		});
		manuallybtnRefresh.setBounds(475, 13, 18, 18);
		manuallybtnRefresh
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/refreshicon.png"));
		manuallybtnRefresh.setCursor(ResourceManager.getCursor(SWT.CURSOR_HAND));
		manuallybtnRefresh.setToolTipText("Refresh Table");

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

		Label lblPressCtrlTo = new Label(compositeCapabilitySettings, SWT.NONE);
		lblPressCtrlTo.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblPressCtrlTo.setBounds(20, 197, 283, 25);
		lblPressCtrlTo.setText("Press CTRL to select multiple capabilities");
		saveInfo = new Button(shlAppiumSettings, SWT.NONE);
		saveInfo.setBounds(465, 540, 91, 33);
		saveInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Boolean status = validate();
				AppiumPortIpInfo.getInstance();
				if (status) {
					OpKeyStudioPreferences.getPreferences().addBasicSettings("host_address", serverAddress.getText());
					AppiumPortIpInfo.setHostAddress(serverAddress.getText());
					OpKeyStudioPreferences.getPreferences().addBasicSettings("port_number", portNumber.getText());
					AppiumPortIpInfo.setPort(portNumber.getText());
					OpKeyStudioPreferences.getPreferences().addBasicSettings("appium_directory",
							appiumDirectory.getText());
					AppiumPortIpInfo.setAppiumDirectory(appiumDirectory.getText());
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

					if (getParentObjectRepositoryView() != null) {
						shlAppiumSettings.close();
						openDeviceConfigurationDialog();
					} else {
						MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Please Note",
								ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
								"Settings Saved Successfully!", 2, 0, "OK");
						mDialog.open();
					}
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
					"Capability Name can't be empty!", 1, 0, "OK");
			mDialog.open();
			return false;
		}
		if (capValue.isEmpty() || capValue.equals("")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Capability Value can't be empty! \nProvide capability value first.", 1, 0, "OK");
			mDialog.open();
			return false;
		}
		if (capType.isEmpty() || capType.equals("")) {
			MessageDialog mDialog = new MessageDialog(shlAppiumSettings, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"Capability data type can't be empty!", 1, 0, "OK");
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

			/*
			 * for (int index = 0; index < text.length(); index++) { char character =
			 * text.charAt(index); boolean isAllowed =
			 * allowedCharactersString.indexOf(character) > -1; if (!isAllowed) { event.doit
			 * = false; return;
			 * 
			 * } }
			 */
                     return ;
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

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;
	}

	private void openDeviceConfigurationDialog() {
		new DeviceConfigurationDialog(this.getParentObjectRepositoryView().getShell(), SWT.NONE,
				this.parentObjectRepositoryView).open();
	}

	protected void restrictInputForManullyCapabilityName(VerifyEvent event) {
		String allowedCharactersString = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		String text = event.text;
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
