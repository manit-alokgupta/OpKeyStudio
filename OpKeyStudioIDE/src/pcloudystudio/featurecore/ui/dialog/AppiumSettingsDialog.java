package pcloudystudio.featurecore.ui.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.OpKeyStudioPreferences;

public class AppiumSettingsDialog extends Dialog {

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
		shlAppiumSettings.setSize(672, 748);
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
		
		compositeAppiumSettingHeading = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettingHeading.setBounds(10, 95, 242, 32);
		compositeAppiumSettingHeading.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		lblAppiumSettings = new Label(compositeAppiumSettingHeading, SWT.NONE);
		lblAppiumSettings.setText("Provide Appium Host and Port for Appium server");
		lblAppiumSettings.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblAppiumSettings.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblAppiumSettings.setBounds(28, 10, 190, 15);

	    compositeAppiumSettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeAppiumSettings.setBounds(10, 133, 646, 181);

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
		
		Composite compositeDeviceCapabilities = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeDeviceCapabilities.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		compositeDeviceCapabilities.setBounds(10, 331, 143, 32);
		
		Label lblDeviceCapability = new Label(compositeDeviceCapabilities, SWT.NONE);
		lblDeviceCapability.setText("Device Capabilities");
		lblDeviceCapability.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblDeviceCapability.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblDeviceCapability.setBounds(33, 10, 78, 15);
		
		compositeCapabilitySettings = new Composite(shlAppiumSettings, SWT.BORDER);
		compositeCapabilitySettings.setBounds(10, 369, 646, 312);
		
		closebutton = new Button(shlAppiumSettings, SWT.NONE);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAppiumSettings.close();
			}
		});
		closebutton.setBounds(581, 687, 75, 25);
		closebutton.setText("Close");
	}
}
