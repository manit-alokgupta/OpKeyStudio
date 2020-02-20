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
		shlAppiumSettings.setSize(466, 333);
		shlAppiumSettings.setText("Appium Settings");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlAppiumSettings.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlAppiumSettings.setLocation(new Point(locationX, locationY));

		Composite composite = new Composite(shlAppiumSettings, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite.setBounds(10, 0, 434, 64);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setBounds(15, 10, 164, 23);
		lblNewLabel.setText("Appium Settings");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 40, 185, 15);
		lblNewLabel_1.setText("Provide Appium Host and Port for Appium server");

		CLabel lblNewLabel_5 = new CLabel(composite, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNewLabel_5.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/appium_logo.jpg"));
		lblNewLabel_5.setBounds(321, 10, 113, 44);

		Button saveButton = new Button(shlAppiumSettings, SWT.NONE);
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

		saveButton.setBounds(254, 258, 75, 25);
		saveButton.setText("Save");

		Button closebutton = new Button(shlAppiumSettings, SWT.NONE);
		closebutton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		closebutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAppiumSettings.close();
			}
		});
		closebutton.setBounds(359, 258, 75, 25);
		closebutton.setText("Close");

		Composite composite_1 = new Composite(shlAppiumSettings, SWT.BORDER);
		composite_1.setBounds(10, 70, 434, 167);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("Server Address");
		label_1.setBounds(30, 27, 80, 21);

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("Port");
		label.setBounds(30, 71, 60, 21);

		serverAddress = new Text(composite_1, SWT.BORDER);
		serverAddress.setToolTipText("Host");
		serverAddress.setBounds(150, 24, 178, 24);

		portNumber = new Text(composite_1, SWT.BORDER);
		portNumber.setToolTipText("Port");
		portNumber.setBounds(150, 68, 178, 24);

		appiumDirectory = new Text(composite_1, SWT.BORDER);
		appiumDirectory.setBounds(150, 115, 178, 24);

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

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("Appium Directory");
		label_2.setBounds(30, 115, 100, 21);

		Button btnBrowse = new Button(composite_1, SWT.NONE);
		btnBrowse.setBounds(350, 115, 75, 25);
		btnBrowse.setText("Browse");

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
	}
}
