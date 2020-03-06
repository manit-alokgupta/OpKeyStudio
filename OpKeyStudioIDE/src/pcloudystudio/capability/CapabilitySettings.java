package pcloudystudio.capability;

import java.net.URL;
import java.util.LinkedHashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import pcloudystudio.appiumserver.AppiumServer;

public class CapabilitySettings extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_Application;
	private Table table_Capability;
	private Text text_CapabilityName;
	private Text text_CapabilityValue;
	private Composite composite_1;
    private Combo combo_DeviceName;
	private LinkedHashMap<String, String> capabilityMap = new LinkedHashMap<String, String>();


	public CapabilitySettings(Shell parent, int style) {
		super(parent, style);
		setText("Capability Settings");
	}

	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	private void createContents() {
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shell.setSize(988, 505);
		shell.setText(getText());

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 972, 96);

		Label lblConfiguration = new Label(composite, SWT.NONE);
		lblConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblConfiguration.setBounds(0, 0, 126, 25);
		lblConfiguration.setText("Configuration");

		Label DeviceName = new Label(composite, SWT.NONE);
		DeviceName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		DeviceName.setBounds(66, 31, 78, 25);
		DeviceName.setText("DeviceName");

		combo_DeviceName = new Combo(composite, SWT.NONE);
		combo_DeviceName.setBounds(199, 31, 581, 23);
		combo_DeviceName.setText("Redmi Note 5");

		Label lblApplication = new Label(composite, SWT.NONE);
		lblApplication.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		lblApplication.setBounds(66, 72, 68, 25);
		lblApplication.setText("Application");

		text_Application = new Text(composite, SWT.BORDER);
		text_Application.setBounds(199, 71, 581, 21);

		Button btnBrowse = new Button(composite, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dialog = new FileDialog(shell);
				String path = dialog.open();
				if (path != null) {

					path = path.replace("\\\\", "\\");

					path = path.replace("\\", "/");
					text_Application.setText(path);
					TableItem row = new TableItem(table_Capability, 0);
					row.setText(0, "app");
					row.setText(1, path);
				}
			}
		});
		btnBrowse.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		btnBrowse.setBounds(841, 60, 75, 31);
		btnBrowse.setText("Browse");

		Composite composite_Capabilities = new Composite(shell, SWT.NONE);
		composite_Capabilities.setBounds(10, 124, 972, 311);

		Label lblCapabilities = new Label(composite_Capabilities, SWT.NONE);
		lblCapabilities.setAlignment(SWT.CENTER);
		lblCapabilities.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.ITALIC));
		lblCapabilities.setBounds(40, 10, 146, 27);
		lblCapabilities.setText("Capabilities");

		Button btnNewButton = new Button(composite_Capabilities, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				composite_1.setVisible(true);

			}

		});
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.ITALIC));
		btnNewButton.setBounds(59, 50, 310, 25);
		btnNewButton.setText("Add New Capability");

		Button btnNewButton_1 = new Button(composite_Capabilities, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (table_Capability.getItemCount() >= 1) {
					table_Capability.remove(table_Capability.getSelectionIndex());
				}
			}
		});
		btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.ITALIC));
		btnNewButton_1.setBounds(475, 50, 310, 25);
		btnNewButton_1.setText("DeleteCapability");

		table_Capability = new Table(composite_Capabilities, SWT.BORDER | SWT.FULL_SELECTION);
		table_Capability.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		table_Capability.setHeaderBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		table_Capability.setForeground(SWTResourceManager.getColor(0, 0, 0));
		table_Capability.setBounds(59, 159, 726, 152);
		table_Capability.setHeaderVisible(true);
		table_Capability.setLinesVisible(true);
		TableColumn column_Name = new TableColumn(table_Capability, 0);
		column_Name.setText("Name");
		column_Name.setWidth(362);
		TableColumn column_Value = new TableColumn(table_Capability, 0);
		column_Value.setText("Value");
		column_Value.setWidth(360);

		composite_1 = new Composite(composite_Capabilities, SWT.NONE);
		composite_1.setVisible(false);
		composite_1.setBounds(59, 81, 726, 76);

		Label lblName = new Label(composite_1, SWT.NONE);
		lblName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		lblName.setBounds(10, 10, 41, 17);
		lblName.setText("Name");

		text_CapabilityName = new Text(composite_1, SWT.BORDER);
		text_CapabilityName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {

			}
		});
		text_CapabilityName.setBounds(71, 9, 157, 21);

		Combo combo_CapabilityName = new Combo(composite_1, SWT.NONE);
		combo_CapabilityName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				text_CapabilityName.setText(combo_CapabilityName.getText());
			}
		});
		combo_CapabilityName.setBounds(359, 9, 157, 23);
		String arrayOftext_CapabilityName[] = { "platformName", "automationName", "launchTimeout",
				"newCommandTimeout" };
		combo_CapabilityName.setItems(arrayOftext_CapabilityName);
		Label lblValue = new Label(composite_1, SWT.NONE);
		lblValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		lblValue.setBounds(10, 51, 41, 15);
		lblValue.setText("Value");

		text_CapabilityValue = new Text(composite_1, SWT.BORDER);
		text_CapabilityValue.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {

			}
		});
		text_CapabilityValue.setBounds(71, 50, 157, 21);

		Combo combo_CapabilityValue = new Combo(composite_1, SWT.NONE);
		combo_CapabilityValue.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		combo_CapabilityValue.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				text_CapabilityValue.setText(combo_CapabilityValue.getText());
			}
		});
		combo_CapabilityValue.setBounds(359, 50, 157, 23);
		String arrayOftext_CapabilityValue[] = { "Android", "IOS", "uiautomator2", "90000", "6000" };
		combo_CapabilityValue.setItems(arrayOftext_CapabilityValue);

		Button btn_SubmitCapability = new Button(composite_1, SWT.NONE);
		btn_SubmitCapability.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String CapabilityName = text_CapabilityName.getText();
				String CapabilityValue = text_CapabilityValue.getText();
				TableItem row = new TableItem(table_Capability, 0);
				if ((CapabilityName != "" || CapabilityName != null)
						&& (CapabilityValue != null || CapabilityValue != "")) {

					row.setText(0, CapabilityName);
					row.setText(1, CapabilityValue);
				}
				// composite_1.setVisible(false);
			}
		});
		btn_SubmitCapability.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		btn_SubmitCapability.setBounds(641, 23, 75, 31);
		btn_SubmitCapability.setText("Submit");

		Button btnFinalSubmit = new Button(composite_Capabilities, SWT.NONE);
		btnFinalSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (combo_DeviceName.getText() == "" || text_Application.getText() == "") {

					MessageDialog.openInformation(shell, "Informatio", "DeviceName or Application cannot be empty");

				}

				for (int i = 0; i < table_Capability.getItemCount(); i++) {
					TableItem row = table_Capability.getItem(i);
					capabilityMap.put(row.getText(0), row.getText(1));
					System.out.println("capabilityName" + " " + row.getText(0));
					System.out.println("capabilityValue" + " " + row.getText(1));
				}

				MobileCapabilities.getinstance().setMapOfCapabilities(capabilityMap);
				// MobileCapabilities.getCapabilities();
			}
		});
		btnFinalSubmit.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		btnFinalSubmit.setBounds(845, 220, 80, 31);
		btnFinalSubmit.setText("Final Submit");

		Composite composite_LaunchAnd_GotoSpy = new Composite(shell, SWT.NONE);
		composite_LaunchAnd_GotoSpy.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.ITALIC));
		composite_LaunchAnd_GotoSpy.setBounds(9, 443, 973, 29);

		Button btnLaunchapplication = new Button(composite_LaunchAnd_GotoSpy, SWT.NONE);
		btnLaunchapplication.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AppiumServer.startServer();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
				
					e1.printStackTrace();
				}

				DesiredCapabilities mobile_capability = MobileCapabilities.getCapabilities();
				mobile_capability.setCapability("deviceName", "Redmi Note 5");

				try {
					AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
							new URL("http://" + "127.0.0.1" + ":" + "4723" + "/wd/hub"), mobile_capability);
					AndroidDriverObject.getInstance().setDriver(driver);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnLaunchapplication.setBounds(352, 0, 112, 25);
		btnLaunchapplication.setText("Start Server");

	}

}
