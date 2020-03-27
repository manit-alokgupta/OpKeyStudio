package pcloudystudio.featurecore.ui.dialog;

import java.util.LinkedHashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

import pcloudystudio.appium.AppiumPortIpInfo;
import pcloudystudio.appium.AppiumServer;
import pcloudystudio.appium.MobileCapabilities;

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
		shlAppiumSettings.setSize(669, 612);
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
		compositeAppiumSettingHeading.setBounds(10, 63, 291, 32);
		compositeAppiumSettingHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		lblAppiumSettings = new Label(compositeAppiumSettingHeading, SWT.NONE);
		lblAppiumSettings.setText("Provide Appium Host and Port");
		lblAppiumSettings.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblAppiumSettings.setBounds(10, 10, 246, 22);

		compositeAppiumSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettings.setBounds(10, 101, 646, 135);

		Label labelServerAddress = new Label(compositeAppiumSettings, SWT.NONE);
		labelServerAddress.setText("Server Address");
		labelServerAddress.setBounds(30, 13, 128, 25);

		Label labelPort = new Label(compositeAppiumSettings, SWT.NONE);
		labelPort.setText("Port");
		labelPort.setBounds(28, 44, 60, 25);

		serverAddress = new Text(compositeAppiumSettings, SWT.BORDER);
		serverAddress.setToolTipText("Host");
		serverAddress.setBounds(205, 13, 309, 25);

		portNumber = new Text(compositeAppiumSettings, SWT.BORDER);
		portNumber.setToolTipText("Port");
		portNumber.setBounds(205, 41, 309, 25);

		appiumDirectory = new Text(compositeAppiumSettings, SWT.BORDER);
		appiumDirectory.setBounds(205, 72, 309, 25);

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
		label_2.setBounds(28, 70, 153, 25);

		Button btnBrowse = new Button(compositeAppiumSettings, SWT.NONE);
		btnBrowse.setBounds(520, 72, 75, 25);
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
				}
			}
		});

		compositeDeviceCapabilities = new Composite(shlAppiumSettings, SWT.NONE);
		compositeDeviceCapabilities.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeDeviceCapabilities.setBounds(10, 255, 200, 32);

		lblDeviceCapability = new Label(compositeDeviceCapabilities, SWT.NONE);
		lblDeviceCapability.setText("Appium Capabilities");
		lblDeviceCapability.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.BOLD));
		lblDeviceCapability.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceCapability.setBounds(10, 10, 180, 22);

		compositeCapabilitySettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeCapabilitySettings.setBounds(10, 293, 646, 235);

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
				capabilityTextValue.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						if (capabilityTextValue.getText().length() > 0
								&& capabilityNameCombo.getSelectionIndex() != -1) {
							btnAddToTable.setEnabled(true);
						}
					}
				});
				capabilityNameCombo.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						if (capabilityTextValue.getText().length() > 0
								&& capabilityNameCombo.getSelectionIndex() != -1) {
							btnAddToTable.setEnabled(true);
						}
					}
				});
			}
		});
		btnAdd.setToolTipText("Add Capability");
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
				btnAddToTable.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Delete Capability");
		btnDelete.setBounds(36, 10, 20, 22);
		btnDelete.setText("-");

		addCapabilityComposite = new Composite(compositeAddCapability, SWT.BORDER);
		addCapabilityComposite.setBounds(62, 0, 550, 42);

		capabilityNameCombo = new Combo(addCapabilityComposite, SWT.READ_ONLY);
		capabilityNameCombo.setBounds(10, 5, 154, 33);
		capabilityNameCombo.setItems(capabilityNameList);

		capabilityTextValue = new Text(addCapabilityComposite, SWT.BORDER);
		capabilityTextValue.setBounds(170, 5, 154, 27);

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
				// addCapabilityComposite.setVisible(false);
				// btnAddToTable.setEnabled(false);
			}
		});
		btnAddToTable.setBounds(330, 5, 124, 30);
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
				btnDelete.setEnabled(true);
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

		saveInfo = new Button(shlAppiumSettings, SWT.NONE);
		saveInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		saveInfo.setBounds(447, 544, 112, 25);
		saveInfo.setText("save");

		closebutton = new Button(shlAppiumSettings, SWT.NONE);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAppiumSettings.close();
			}
		});
		closebutton.setBounds(565, 544, 75, 25);
		closebutton.setText("Close");
	}

	private void addTableItemToCapabilityTableData(String key, String value) {
		TableItem item = new TableItem(capabilityTable, SWT.NONE);
		item.setText(new String[] { key, value });
	}

	public void validate() {
		if (serverAddress.getText().isEmpty()) {
			MessageDialog.openInformation(shlAppiumSettings, "Please Note", "ServerAddress cannot be empty");
		} else if (portNumber.getText().isEmpty()) {
			MessageDialog.openInformation(shlAppiumSettings, "Please Note", "Port Cannot Be Empty");
		} else if (appiumDirectory.getText().isEmpty()) {
			MessageDialog.openInformation(shlAppiumSettings, "Please Note", "AppiumDirectory Cannot Be Empty");
		} else if (capabilityTable.getItemCount() != 0) {
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

		} else {
			MessageDialog.openInformation(shlAppiumSettings, "Please Note", "You Have Not Provided Any Capability");
		}

	}
}
