package pcloudystudio.objectspy.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.OpKeyStudioPreferences;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.dialog.ProgressMonitorDialogWithThread;
import pcloudystudio.objectspy.treeviewer.TreeViewer;

public class MobileObjectSpyDialog extends Dialog {

	public static TreeMobileElement appRootElement;
	protected Object result;
	protected Shell shlSpyMobile;
	private Button btnCapture;
	private Text applicationPathText;
	private Text androidSdkPathText;
	private Button installButton;
	private Map<String, String> devicesList;
	private Combo devicesCombo;

	private MobileInspectorController inspectorController;

	public MobileObjectSpyDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		this.inspectorController = new MobileInspectorController();
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlSpyMobile.open();
		shlSpyMobile.layout();
		Display display = getParent().getDisplay();
		while (!shlSpyMobile.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void createContents() {
		shlSpyMobile = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlSpyMobile.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/mobile_spy.png"));
		shlSpyMobile.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlSpyMobile.setSize(1000, 700);
		shlSpyMobile.setText("Mobile Object Spy");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlSpyMobile.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlSpyMobile.setLocation(new Point(locationX, locationY));

		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		shlSpyMobile.setLayout((Layout) layout);

		SashForm sashForm = new SashForm(shlSpyMobile, 0);
		sashForm.setSashWidth(3);
		sashForm.setLayout((Layout) new FillLayout());
		sashForm.setLayoutData((Object) new GridData(4, 4, true, true));

		ScrolledComposite leftSashForm = new ScrolledComposite((Composite) sashForm, 768);
		leftSashForm.setExpandHorizontal(true);
		leftSashForm.setExpandVertical(true);

		Composite explorerComposite = new Composite(leftSashForm, SWT.BORDER);
		explorerComposite.setForeground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		leftSashForm.setContent(explorerComposite);
		leftSashForm.setMinSize(explorerComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		leftSashForm.setMinSize(180, 400);

		ScrolledComposite rightSashForm = new ScrolledComposite((Composite) sashForm, 768);
		rightSashForm.setExpandHorizontal(true);
		rightSashForm.setExpandVertical(true);

		Composite contentComposite = new Composite(rightSashForm, SWT.BORDER);
		explorerComposite.setForeground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		rightSashForm.setContent(contentComposite);
		rightSashForm.setMinSize(contentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		rightSashForm.setMinSize(280, 200);

		Label lblConfigurations = new Label(contentComposite, SWT.NONE);
		lblConfigurations.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblConfigurations.setBounds(10, 16, 118, 17);
		lblConfigurations.setText("CONFIGURATIONS");

		Composite configurationComposite = new Composite(contentComposite, SWT.BORDER);
		configurationComposite.setBounds(10, 39, 500, 149);

		Label lblDeviceName = new Label(configurationComposite, SWT.NONE);
		lblDeviceName.setBounds(10, 22, 76, 15);
		lblDeviceName.setText("Device Name");

		devicesCombo = new Combo(configurationComposite, SWT.READ_ONLY);
		devicesCombo.setBounds(137, 19, 249, 23);

		Button btnRefresh = new Button(configurationComposite, SWT.NONE);
		btnRefresh.setBounds(407, 17, 75, 25);
		btnRefresh.setText("Refresh");

		btnRefresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					getAndroidDevices();
					devicesCombo.removeAll();
					System.out.println(devicesList.size());
					for (Map.Entry<String, String> deviceEntry : devicesList.entrySet()) {
						System.out.println("Key = " + deviceEntry.getKey() + ", Value = " + deviceEntry.getValue());
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

		Label lblStartWith = new Label(configurationComposite, SWT.NONE);
		lblStartWith.setBounds(10, 59, 107, 15);
		lblStartWith.setText("Android SDK Path");

		androidSdkPathText = new Text(configurationComposite, SWT.BORDER);
		androidSdkPathText.setBounds(137, 56, 249, 21);

		Button btnSDKBrowse = new Button(configurationComposite, SWT.NONE);
		btnSDKBrowse.setBounds(407, 54, 75, 25);
		btnSDKBrowse.setText("Browse");

		btnSDKBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dirDialog = new DirectoryDialog(shlSpyMobile);

				// Set the initial filter path according to anything they've selected or typed
				// in
				dirDialog.setFilterPath(androidSdkPathText.getText());

				// Change the title bar text
				dirDialog.setText("SWT's Directory Dialog");

				// Customizable message displayed in the dialog
				dirDialog.setMessage("Select a directory");

				// Calling open() will open and run the dialog. It will return the selected
				// directory, or null if user cancels
				String dir = dirDialog.open();
				if (dir != null) {
					// Set the text box to the new selection
					androidSdkPathText.setText(dir);
				}
			}
		});

		String androidSdkPath = OpKeyStudioPreferences.getPreferences().getBasicSettings("android_sdk_path");
		if (androidSdkPath != null) {
			androidSdkPathText.setText(androidSdkPath);
		}

		Label lblApplicationFile = new Label(configurationComposite, SWT.NONE);
		lblApplicationFile.setBounds(10, 91, 87, 15);
		lblApplicationFile.setText("Application File");

		applicationPathText = new Text(configurationComposite, SWT.BORDER);
		applicationPathText.setBounds(137, 83, 249, 21);

		Button btnBrowse = new Button(configurationComposite, SWT.NONE);
		btnBrowse.setBounds(407, 81, 75, 25);
		btnBrowse.setText("Browse");
		leftSashForm.setContent(contentComposite);
		leftSashForm.setMinSize(contentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlSpyMobile, SWT.NULL);
				String path = dialog.open();
				if (path != null) {

					File file = new File(path);
					if (file.isFile())
						displayFiles(new String[] { file.toString() });
					else
						displayFiles(file.list());
				}
			}
		});

		String appFilePath = OpKeyStudioPreferences.getPreferences().getBasicSettings("application_name");
		if (appFilePath != null) {
			applicationPathText.setText(appFilePath);
		}

		leftSashForm.setMinSize(180, 400);

		/*
		 * ScrolledComposite scrolledComposite = new ScrolledComposite(contentComposite,
		 * SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		 * scrolledComposite.setMinSize(scrolledComposite.computeSize(SWT.DEFAULT,
		 * SWT.DEFAULT)); scrolledComposite.setBounds(10, 221, 323, 17);
		 * scrolledComposite.setExpandHorizontal(true);
		 * scrolledComposite.setExpandVertical(true);
		 */
		Composite allObjectTreeComposite = new Composite(contentComposite, SWT.BORDER);
		allObjectTreeComposite.setBounds(10, 217, 500, 25);

		Button saveButton = new Button(configurationComposite, SWT.NONE);
		saveButton.setToolTipText("Save");
		saveButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String appiumDirectoryPath = applicationPathText.getText();
				String androidSDKpath = androidSdkPathText.getText();
				OpKeyStudioPreferences.getPreferences().addBasicSettings("application_name", appiumDirectoryPath);
				OpKeyStudioPreferences.getPreferences().addBasicSettings("android_sdk_path", androidSDKpath);
				if (appiumDirectoryPath.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlSpyMobile, "Invalid Application file path",
							"Please browse Application file");
				}
				if (androidSDKpath.trim().equalsIgnoreCase("")) {
					MessageDialog.openInformation(shlSpyMobile, "Invalid Directory!",
							"Please browse Directory of SDK!");
				}
			}
		});

		saveButton.setBounds(311, 110, 75, 25);
		saveButton.setText("Save");

		installButton = new Button(configurationComposite, SWT.NONE);
		installButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		installButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedDevice = devicesCombo.getText();
				installApplicationToAndroidDevice(selectedDevice);
			}
		});
		installButton.setBounds(407, 112, 75, 25);
		installButton.setText("Install");

		this.btnCapture = new Button(contentComposite, SWT.NONE);
		btnCapture.setBounds(319, 8, 75, 25);
		btnCapture.setText("Capture");
		sashForm.setWeights(new int[] { 469, 522 });
		btnCapture.addSelectionListener((SelectionListener) new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				MobileObjectSpyDialog.this.captureObjectAction();
			}
		});

		inspectorController.getMobileObjectRoot();
		MobileObjectSpyDialog.access$13(MobileObjectSpyDialog.this,
				MobileObjectSpyDialog.this.inspectorController.getMobileObjectRoot());
		TreeViewer layoutTree = new TreeViewer(getParent().getDisplay(), shlSpyMobile);
		allObjectTreeComposite.setLayoutData(layoutTree);

	}

	static /* synthetic */ void access$13(final MobileObjectSpyDialog mobileObjectSpyDialog,
			final TreeMobileElement appRootElement) {
		mobileObjectSpyDialog.appRootElement = appRootElement;
	}

	private void captureObjectAction() {
		final ProgressMonitorDialogWithThread dialog = new ProgressMonitorDialogWithThread(shlSpyMobile);
		final IRunnableWithProgress runnable = (IRunnableWithProgress) new IRunnableWithProgress() {
			@SuppressWarnings("unchecked")
			public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				monitor.beginTask("DIA_JOB_TASK_CAPTURING_OBJECTS", -1);
				dialog.runAndWait((Callable) new Callable<Object>() {
					public Object call() throws Exception {
						MobileObjectSpyDialog.this.inspectorController.getMobileObjectRoot();
						return null;
					}
				});
			}
		};

		try {
			this.btnCapture.setEnabled(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.btnCapture.setEnabled(true);
		}
		this.btnCapture.setEnabled(true);
	}

	public void displayFiles(String[] files) {
		for (int i = 0; files != null && i < files.length; i++) {
			applicationPathText.setText(files[i]);
			applicationPathText.setEditable(true);
		}
	}

	private Font getFontBold(final Label label) {
		final FontDescriptor boldDescriptor = FontDescriptor.createFrom(label.getFont()).setStyle(1);
		return boldDescriptor.createFont((Device) label.getDisplay());
	}

	/*
	 * private void createAllObjectsComposite(final Composite parentComposite) {
	 * final Composite allObjectsComposite = new Composite(parentComposite,
	 * SWT.BORDER); allObjectsComposite.setLayoutData((Object) new GridData(4, 4,
	 * true, true, 1, 1)); allObjectsComposite.setLayout((Layout) new GridLayout());
	 * final Label lblAllObjects = new Label(allObjectsComposite, 0);
	 * lblAllObjects.setText("ALL OBJECTS"); lblAllObjects.setLayoutData((Object)
	 * new GridData(4, 16777216, false, false, 1, 1));
	 * lblAllObjects.setFont(this.getFontBold(lblAllObjects));
	 * 
	 * this.allElementTreeViewer = new CheckboxTreeViewer(allObjectsComposite,
	 * SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL) { public boolean
	 * setSubtreeChecked(final Object element, final boolean state) { final Widget
	 * widget = this.internalExpand(element, false); if (widget instanceof TreeItem)
	 * { final TreeItem item = (TreeItem)widget; item.setChecked(state); return
	 * true; } return false; } };
	 * 
	 * final Tree tree = this.allElementTreeViewer.getTree();
	 * tree.setLayoutData((Object) new GridData(GridData.FILL_BOTH));
	 * this.allElementTreeViewer.setLabelProvider((IBaseLabelProvider) new
	 * MobileElementLabelProvider());
	 * this.allElementTreeViewer.setContentProvider((IContentProvider) new
	 * MobileElementTreeContentProvider()); tree.setToolTipText("");
	 * ColumnViewerToolTipSupport.enableFor((ColumnViewer)
	 * this.allElementTreeViewer, 2); tree.addMouseListener((MouseListener) new
	 * MouseAdapter() { public void mouseDown(final MouseEvent e) { if (e.button !=
	 * 1) { return; } final Point pt = new Point(e.x, e.y); final TreeItem item =
	 * tree.getItem(pt); if (item != null) {
	 * 
	 * } } }); this.allElementTreeViewer.addCheckStateListener((ICheckStateListener)
	 * new ICheckStateListener() { public void checkStateChanged(final
	 * CheckStateChangedEvent event) { final TreeMobileElement selectedElement =
	 * (TreeMobileElement) event.getElement(); if (event.getChecked()) { final
	 * List<CapturedMobileElement> mobileElements = new
	 * ArrayList<CapturedMobileElement>();
	 * mobileElements.add(selectedElement.newCapturedElement()); //
	 * MobileObjectSpyDialog.this.capturedObjectsTableViewer.addMobileElements(
	 * mobileElements); MobileObjectSpyDialog.this.propertiesComposite.
	 * focusAndEditCapturedElementName(); } else {
	 * 
	 * } MobileObjectSpyDialog.this.allElementTreeViewer.refresh((Object)
	 * selectedElement); } });
	 * 
	 * MobileObjectSpyDialog.this.allElementTreeViewer.setInput((Object)new Object[]
	 * { inspectorController.getMobileObjectRoot() }); }
	 */

	private void getAndroidDevices() throws Exception {
		String adbPath = System.getenv("ANDROID_SDK_HOME");
		if (adbPath == null)
			System.getenv("ANDROID_HOME");
		if (adbPath != null) {
			final List<String> deviceIds = new ArrayList<String>();
			adbPath = String.valueOf(adbPath) + File.separator + "platform-tools" + File.separator + "adb";
			String[] cmd = { adbPath, "devices" };
			final ProcessBuilder pb = new ProcessBuilder(cmd);
			Process process = pb.start();
			process.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.toLowerCase().trim().contains("list of devices")
						&& line.toLowerCase().trim().contains("device")) {
					final String deviceId = line.split("\\s")[0];
					deviceIds.add(deviceId);
				}
			}
			br.close();
			this.devicesList = new HashMap<String, String>();
			for (final String id : deviceIds) {
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", "ro.product.manufacturer" };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String deviceName = br.readLine();
				br.close();
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", "ro.product.model" };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceName = String.valueOf(deviceName) + " " + br.readLine();
				br.close();
				cmd = new String[] { adbPath, "-s", id, "shell", "getprop", "ro.build.version.release" };
				pb.command(cmd);
				process = pb.start();
				process.waitFor();
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				deviceName = String.valueOf(deviceName) + " " + br.readLine();
				br.close();
				deviceName = String.valueOf(deviceName) + " (Android)";
				this.devicesList.put(id, deviceName);
			}
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

	private void installApplicationToAndroidDevice(String selectedDevice) {
		String deviceid = getSelectedAndroidDeviceId(selectedDevice);

		String adbPath = System.getenv("ANDROID_SDK_HOME");
		if (adbPath != null) {
			adbPath = String.valueOf(adbPath) + File.separator + "platform-tools" + File.separator + "adb";
			String[] cmd = new String[] { adbPath, "-s", deviceid, "install",
					OpKeyStudioPreferences.getPreferences().getBasicSettings("application_name") };
			final ProcessBuilder pb = new ProcessBuilder(cmd);
			try {
				Process process = pb.start();
				process.waitFor();
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					if (!line.toLowerCase().trim().contains("Success")) {
						break;
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
