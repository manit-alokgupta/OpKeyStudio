package pcloudystudio.featurecore.ui.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import opkeystudio.core.utils.OpKeyStudioPreferences;
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
import opkeystudio.opkeystudiocore.core.dtoMaker.ORObjectMaker;
import pcloudystudio.appium.AndroidDriverObject;
import pcloudystudio.objectspy.MobileInspectorController;
import pcloudystudio.objectspy.element.MobileElement;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.BasicMobileElement;
import pcloudystudio.objectspy.element.tree.MobileElementLabelProvider;
import pcloudystudio.objectspy.element.tree.MobileElementTreeContentProvider;

public class MobileSpyDialog extends Dialog implements MobileElementInspectorDialog {
	private ObjectRepositoryView parentObjectRepositoryView;

	public static Point DIALOG_SIZE;
	private static String DIALOG_TITLE;

	protected Object result;
	protected Shell shlSpyMobile;
	private static Table objectPropertiesTable;
	private MobileInspectorController inspectorController;
	public static CheckboxTreeViewer allObjectsCheckboxTreeViewer;
	public TreeMobileElement appRootElement;

	private MobileDeviceDialog deviceView;
	private Button btnCapture;
	private Button btnHelp;
	private Button btnStop;
	public static Button btnAdd;
	private Label lblAllObjects;
	private Label lblAllObjectProperties;
	private Label lblObjectName;
	public static Text textObjectName;
	private ScrolledComposite allObjectsTreeScrolledComposite;
	private ScrolledComposite objectPropertiesScrolledComposite;
	private Composite compositeTreeHierarchy;
	private Composite compositeObjectProperties;

	private Map<String, String> mobileElementProps;
	private Map<String, String> mobileParentElementProps;

	public static Button btnClickAndMoveToNextScreen;

	static {
		DIALOG_SIZE = new Point(800, 728);
		DIALOG_TITLE = "Mobile Object Spy";
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public MobileSpyDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		this.inspectorController = new MobileInspectorController();
	}

	public MobileSpyDialog(Shell parent, int style, ObjectRepositoryView parentObjectRepositoryView) {
		super(parent, style);
		this.setParentObjectRepositoryView(parentObjectRepositoryView);
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

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlSpyMobile = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlSpyMobile.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"));
		shlSpyMobile.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlSpyMobile.setSize(new Point(800, 750));
		shlSpyMobile.setText(DIALOG_TITLE);
		shlSpyMobile.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlSpyMobile.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 4 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 4 + parentSize.y;
		shlSpyMobile.setLocation(new Point(locationX, locationY));
		shlSpyMobile.setLayout(new GridLayout(1, false));

		Composite toolsComposite = new Composite(shlSpyMobile, SWT.BORDER);
		GridData gd_toolsComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_toolsComposite.widthHint = 782;
		gd_toolsComposite.heightHint = 50;
		toolsComposite.setLayoutData(gd_toolsComposite);

		Composite spyContainerComposite = new Composite(shlSpyMobile, SWT.NONE);
		GridData gd_spyContainerComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_spyContainerComposite.heightHint = 599;
		gd_spyContainerComposite.widthHint = 786;
		spyContainerComposite.setLayoutData(gd_spyContainerComposite);
		spyContainerComposite.setLayout(new FillLayout());

		Composite bottomComposite = new Composite(shlSpyMobile, SWT.BORDER);
		GridData gd_bottomComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_bottomComposite.widthHint = 400;
		gd_bottomComposite.heightHint = 46;
		bottomComposite.setLayoutData(gd_bottomComposite);

		SashForm sashForm = new SashForm(spyContainerComposite, SWT.NONE);

		compositeTreeHierarchy = new Composite(sashForm, SWT.BORDER);
		compositeTreeHierarchy.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		compositeTreeHierarchy.setLayout(new GridLayout(1, true));

		lblAllObjects = new Label(compositeTreeHierarchy, SWT.NONE);
		lblAllObjects.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblAllObjects.setText("ALL OBJECTS");

		allObjectsTreeScrolledComposite = new ScrolledComposite(compositeTreeHierarchy,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_allObjectsTreeScrolledComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_allObjectsTreeScrolledComposite.widthHint = 390;
		gd_allObjectsTreeScrolledComposite.heightHint = 524;
		allObjectsTreeScrolledComposite.setLayoutData(gd_allObjectsTreeScrolledComposite);
		allObjectsTreeScrolledComposite.setExpandHorizontal(true);
		allObjectsTreeScrolledComposite.setExpandVertical(true);

		compositeObjectProperties = new Composite(sashForm, SWT.BORDER);
		compositeObjectProperties.setLayout(new GridLayout(1, true));

		lblAllObjectProperties = new Label(compositeObjectProperties, SWT.NONE);
		lblAllObjectProperties.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblAllObjectProperties.setText("OBJECT PROPERTIES");

		lblObjectName = new Label(compositeObjectProperties, SWT.NONE);
		lblObjectName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NONE));
		lblObjectName.setText("Object Name");

		textObjectName = new Text(compositeObjectProperties, SWT.BORDER);
		GridData gd_textObjectName = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_textObjectName.widthHint = 322;
		textObjectName.setLayoutData(gd_textObjectName);
		textObjectName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));

		objectPropertiesScrolledComposite = new ScrolledComposite(compositeObjectProperties,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_objectPropertiesScrolledComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_objectPropertiesScrolledComposite.heightHint = 395;
		gd_objectPropertiesScrolledComposite.widthHint = 310;
		objectPropertiesScrolledComposite.setLayoutData(gd_objectPropertiesScrolledComposite);
		objectPropertiesScrolledComposite.setExpandHorizontal(true);
		objectPropertiesScrolledComposite.setExpandVertical(true);
		sashForm.setWeights(new int[] { 431, 352 });

		// -------------------------------------------------------------//
		btnCapture = new Button(toolsComposite, SWT.NONE);
		btnCapture.setToolTipText("Capture Object");
		btnCapture.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		btnCapture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				captureObjectAction();
			}
		});

		btnCapture.setBounds(10, 10, 151, 28);
		btnCapture.setText("Capture Object");

		// -------------------------------------------------------------//

		btnAdd = new Button(toolsComposite, SWT.NONE);
		btnAdd.setToolTipText("Add to Object Repository");
		btnAdd.setEnabled(false);
		btnAdd.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object element = allObjectsCheckboxTreeViewer.getCheckedElements();
				Widget item = CustomCheckBoxTree.getCheckedItem(element);
				TreeItem treeItem = (TreeItem) item;
				Object obj = treeItem.getData();
				setMobileElementProps(((BasicMobileElement) obj).getAttributes());
				Object parentObj = ((TreeMobileElement) obj).getParentElement();
				setMobileParentElementProps(((BasicMobileElement) parentObj).getAttributes());
				try {
					new ORObjectMaker().addMobileObject(getParentObjectRepositoryView().getArtifact(),
							getParentObjectRepositoryView().getArtifact().getId(), getMobileElementProps(),
							getMobileParentElementProps(), textObjectName.getText().toString(),
							getMobileParentElementProps().get("package")
							+ getMobileParentElementProps().get("activity"),
							"Custom", "HTML Page",
							getParentObjectRepositoryView().getObjectRepositoryTree().getObjectRepositoriesData());
					getParentObjectRepositoryView().getObjectRepositoryTree().renderObjectRepositories();
					MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Please Note",
							ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
							"Object has been added successifully!", 2, 0, "OK");
					mDialog.open();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAdd.setBounds(531, 10, 223, 28);
		btnAdd.setText("Add to Object Repository");

		btnClickAndMoveToNextScreen = new Button(toolsComposite, SWT.NONE);
		btnClickAndMoveToNextScreen.setEnabled(false);
		btnClickAndMoveToNextScreen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (AndroidDriverObject.getInstance() != null) {
					Object element = allObjectsCheckboxTreeViewer.getCheckedElements();
					Widget item = CustomCheckBoxTree.getCheckedItem(element);
					TreeItem treeItem = (TreeItem) item;
					Object obj = treeItem.getData();
					Map<String, String> mobileElementProps = ((BasicMobileElement) obj).getAttributes();
					try {
						WebElement foundElement = AndroidDriverObject.getDriver()
								.findElementByXPath(mobileElementProps.get("xpath"));
						if (foundElement != null) {
							foundElement.click();
							btnAdd.setEnabled(false);
							captureObjectAction();
						}
					} catch (Exception ex) {
						if (ex instanceof NoSuchElementException) {
							MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Error",
									ResourceManager.getPluginImage("OpKeyStudio",
											"icons/pcloudystudio/opkey-16x16.png"),
									"org.openqa.selenium.NoSuchElementException: An element could not be located on the page!",
									1, 0, "OK");
							mDialog.open();
						} else if (ex instanceof InvalidSelectorException) {
							MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Error",
									ResourceManager.getPluginImage("OpKeyStudio",
											"icons/pcloudystudio/opkey-16x16.png"),
									"org.openqa.selenium.InvalidSelectorException: An element could not be located on the page!",
									1, 0, "OK");
							mDialog.open();
						} else if (ex instanceof NoSuchSessionException) {
							MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Error",
									ResourceManager.getPluginImage("OpKeyStudio",
											"icons/pcloudystudio/opkey-16x16.png"),
									"org.openqa.selenium.NoSuchSessionException: A session is either terminated or not started!",
									1, 0, "OK");
							mDialog.open();
						} else {
							MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Error", ResourceManager
									.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
									ex.getMessage(), 1, 0, "OK");
							mDialog.open();
						}
					}
				}
			}
		});
		btnClickAndMoveToNextScreen.setBounds(178, 10, 188, 28);
		btnClickAndMoveToNextScreen.setText("Click and Update Spy");

		btnStop = new Button(toolsComposite, SWT.NONE);
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deviceView.close();
				btnStop.setEnabled(false);
				btnCapture.setEnabled(false);
				btnClickAndMoveToNextScreen.setEnabled(false);
				btnAdd.setEnabled(false);
				allObjectsCheckboxTreeViewer.getTree().removeAll();
				clearPropertiesTableData();
				textObjectName.setText("");
				AndroidDriverObject.getDriver().closeApp();
			}
		});
		btnStop.setBounds(374, 10, 58, 28);
		btnStop.setText("Stop");

		// -------------------------------------------------------------//

		btnHelp = new Button(bottomComposite, SWT.NONE);
		btnHelp.setToolTipText("Help");
		btnHelp.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Help",
						ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
						"Please contact support@opkey.com", 2, 0, "OK");
				mDialog.open();
			}
		});
		btnHelp.setBounds(10, 10, 54, 28);
		btnHelp.setText("Help");

		if (AndroidDriverObject.getDriver() == null) {
			btnCapture.setEnabled(false);
			btnStop.setEnabled(false);
		} else {
			captureObjectAction();
		}
	}

	static void setTreeRoot(MobileSpyDialog mobileSpyDialog, TreeMobileElement appRootElement) {
		mobileSpyDialog.appRootElement = appRootElement;
	}

	public static void addTableItemToPropertiesTableData(String key, String value) {
		TableItem item = new TableItem(objectPropertiesTable, SWT.NONE);
		item.setText(new String[] { key, value });
	}

	public static void clearPropertiesTableData() {
		objectPropertiesTable.removeAll();
	}

	private void createAllObjectsTreeHierarchy(ScrolledComposite allObjectsTreeScrolledComposite) {
		MobileElementTreeContentProvider contentProvider = new MobileElementTreeContentProvider();
		MobileElementLabelProvider labelProvider = new MobileElementLabelProvider();
		allObjectsCheckboxTreeViewer = new CustomCheckBoxTree(allObjectsTreeScrolledComposite, SWT.BORDER);
		Tree tree = allObjectsCheckboxTreeViewer.getTree();
		allObjectsCheckboxTreeViewer.setContentProvider(contentProvider);
		allObjectsCheckboxTreeViewer.setLabelProvider(labelProvider);

		allObjectsTreeScrolledComposite.setContent(tree);
		allObjectsTreeScrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	private void createObjectPropertiesTable(ScrolledComposite objectPropertiesScrolledComposite) {
		objectPropertiesTable = new Table(objectPropertiesScrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		objectPropertiesTable.setLinesVisible(true);
		objectPropertiesTable.setHeaderVisible(true);
		objectPropertiesTable.setHeaderBackground(SWTResourceManager.getColor(248, 248, 245));
		objectPropertiesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		objectPropertiesScrolledComposite.setContent(objectPropertiesTable);

		TableColumn tblclmnName = new TableColumn(objectPropertiesTable, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
		tblclmnName.setResizable(true);

		TableColumn tblclmnValue = new TableColumn(objectPropertiesTable, SWT.NONE);
		tblclmnValue.setWidth(220);
		tblclmnValue.setText("Value");
		tblclmnValue.setResizable(true);

		TableEditor editor = new TableEditor(objectPropertiesTable);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		objectPropertiesTable.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = objectPropertiesTable.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = objectPropertiesTable.getTopIndex();
				while (index < objectPropertiesTable.getItemCount()) {
					boolean visible = false;
					TableItem item = objectPropertiesTable.getItem(index);
					for (int i = 0; i < objectPropertiesTable.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(pt) && i == 1) {
							int column = i;
							Text text = new Text(objectPropertiesTable, SWT.NONE);
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

	private void openDeviceView() {
		if (this.deviceView != null && !this.deviceView.isDisposed()) {
			return;
		}
		(this.deviceView = new MobileDeviceDialog(shlSpyMobile, this, this.calculateInitPositionForDeviceViewDialog()))
		.open();
		setDeviceView(this.deviceView);
	}

	private void setDeviceView(MobileDeviceDialog deviceView) {
		this.deviceView = deviceView;
	}

	private int getDeviceViewStartXIfPlaceRight(Rectangle objectSpyViewBounds) {
		return objectSpyViewBounds.x + objectSpyViewBounds.width + 5;
	}

	private boolean isOutOfBound(Rectangle displayBounds, Point dialogSize, int startX) {
		return startX < 0 || startX + dialogSize.x > displayBounds.width + displayBounds.x;
	}

	private int getDeviceViewStartXIfPlaceLeft(Rectangle objectSpyViewBounds, Point dialogSize) {
		return objectSpyViewBounds.x - dialogSize.x - 5;
	}

	private int getDefaultDeviceViewDialogStartX(Rectangle displayBounds, Point dialogSize) {
		return displayBounds.width - dialogSize.x;
	}

	private Point calculateInitPositionForDeviceViewDialog() {
		Rectangle displayBounds = shlSpyMobile.getMonitor().getBounds();
		Point dialogSize = new Point(MobileDeviceDialog.DIALOG_WIDTH, MobileDeviceDialog.DIALOG_HEIGHT);
		Rectangle objectSpyViewBounds = shlSpyMobile.getBounds();
		int startX = this.getDeviceViewStartXIfPlaceRight(objectSpyViewBounds);
		if (this.isOutOfBound(displayBounds, dialogSize, startX)) {
			startX = this.getDeviceViewStartXIfPlaceLeft(objectSpyViewBounds, dialogSize);
			if (this.isOutOfBound(displayBounds, dialogSize, startX)) {
				startX = this.getDefaultDeviceViewDialogStartX(displayBounds, dialogSize);
			}
		}
		return new Point(startX, objectSpyViewBounds.y);
	}

	@Override
	public void setSelectedElementByLocation(int x, int y) {
		if (this.appRootElement == null) {
			return;
		}
		TreeMobileElement foundElement = this.recursivelyFindElementByLocation(this.appRootElement, x, y);
		if (foundElement == null) {
			return;
		}
		this.highlightObject(foundElement);
		getParent().getDisplay().syncExec(new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				shlSpyMobile.setFocus();
				allObjectsCheckboxTreeViewer.getTree().setFocus();
				allObjectsCheckboxTreeViewer.setAllChecked(false);
				allObjectsCheckboxTreeViewer.setSelection((ISelection) new StructuredSelection((Object) foundElement));
				btnAdd.setEnabled(false);
				btnClickAndMoveToNextScreen.setEnabled(false);
				MobileSpyDialog.clearPropertiesTableData();
				// CustomCheckBoxTree.fillDataInObjectPropertiesTable(foundElement);
				// allObjectsCheckboxTreeViewer.setChecked(foundElement, true);
			}
		});
	}

	private TreeMobileElement recursivelyFindElementByLocation(TreeMobileElement currentElement, int x, int y) {
		for (TreeMobileElement childElement : currentElement.getChildrenElement()) {
			Map<String, String> attributes = childElement.getAttributes();
			Double elementX = Double.parseDouble(attributes.get("x"));
			Double elementY = Double.parseDouble(attributes.get("y"));
			Double elementWidth = Double.parseDouble(attributes.get("width"));
			Double elementHeight = Double.parseDouble(attributes.get("height"));
			Rectangle rectangle = new Rectangle(MobileDeviceDialog.safeRoundDouble(elementX),
					MobileDeviceDialog.safeRoundDouble(elementY), MobileDeviceDialog.safeRoundDouble(elementWidth),
					MobileDeviceDialog.safeRoundDouble(elementHeight));
			if (rectangle.contains(x, y)) {
				return this.recursivelyFindElementByLocation(childElement, x, y);
			}
		}
		return currentElement;
	}

	private void highlightObject(MobileElement selectedElement) {
		if (selectedElement == null || this.deviceView == null || this.deviceView.isDisposed()) {
			return;
		}
		this.deviceView.highlightElement(selectedElement);
	}

	private void captureObjectAction() {
		try {
			AndroidDriverObject.getDriver().getSessionDetails();
		} catch (NoSuchSessionException ex) {
			deviceView.close();
			btnStop.setEnabled(false);
			btnCapture.setEnabled(false);
			btnClickAndMoveToNextScreen.setEnabled(false);
			btnAdd.setEnabled(false);
			allObjectsCheckboxTreeViewer.getTree().removeAll();
			clearPropertiesTableData();
			textObjectName.setText("");
			MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"org.openqa.selenium.NoSuchSessionException: A session is either terminated or not started!", 1, 0,
					"OK");
			mDialog.open();
			return;
		} catch (WebDriverException ex) {
			deviceView.close();
			btnStop.setEnabled(false);
			btnCapture.setEnabled(false);
			btnClickAndMoveToNextScreen.setEnabled(false);
			btnAdd.setEnabled(false);
			allObjectsCheckboxTreeViewer.getTree().removeAll();
			clearPropertiesTableData();
			textObjectName.setText("");
			MessageDialog mDialog = new MessageDialog(shlSpyMobile, "Error",
					ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/opkey-16x16.png"),
					"org.openqa.selenium.WebDriverException: Connection refused: connect!", 1, 0, "OK");
			mDialog.open();
			return;
		}

		if (textObjectName.getText().length() > 0)
			textObjectName.setText("");

		createAllObjectsTreeHierarchy(allObjectsTreeScrolledComposite);
		createObjectPropertiesTable(objectPropertiesScrolledComposite);

		String appFilePath = OpKeyStudioPreferences.getPreferences().getBasicSettings("application_name");
		String appName = this.getApplicationName(appFilePath);

		ProgressMonitorDialogWithThread dialog = new ProgressMonitorDialogWithThread(shlSpyMobile);
		IRunnableWithProgress runnable = (IRunnableWithProgress) new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				monitor.beginTask("Starting Application for Spy ...", -1);
				dialog.runAndWait((Callable<Object>) new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						Thread.sleep(2000);
						MobileSpyDialog.setTreeRoot(MobileSpyDialog.this,
								MobileSpyDialog.this.inspectorController.getMobileObjectRoot());
						if (MobileSpyDialog.this.appRootElement != null) {
							MobileSpyDialog.this.appRootElement.setName(appName);
						}
						return null;
					}
				});

				MobileSpyDialog.this.checkMonitorCanceled(monitor);
				this.refreshTreeElements(dialog);
				String imgPath = this.captureImage();
				MobileSpyDialog.this.checkMonitorCanceled(monitor);
				this.refreshDeviceView(imgPath);
				Display.getDefault().syncExec((Runnable) new Runnable() {
					@Override
					public void run() {
						MobileSpyDialog.this.deviceView.getShell().forceActive();
					}
				});
				monitor.done();
			}

			private void refreshTreeElements(ProgressMonitorDialogWithThread dialog) {
				Display.getDefault().syncExec((Runnable) new Runnable() {
					@Override
					public void run() {
						dialog.setCancelable(false);
						allObjectsCheckboxTreeViewer
						.setInput((Object) new Object[] { MobileSpyDialog.this.appRootElement });
						allObjectsCheckboxTreeViewer.refresh();
						allObjectsCheckboxTreeViewer.expandAll();
						dialog.setCancelable(true);
					}
				});
			}

			private void refreshDeviceView(String imgPath) {
				File imgFile = new File(imgPath);
				if (imgFile.exists()) {
					MobileSpyDialog.this.deviceView.refreshDialog(imgFile, MobileSpyDialog.this.appRootElement);
				}
			}

			private String captureImage() throws InvocationTargetException {
				try {
					return MobileSpyDialog.this.inspectorController.captureScreenshot();
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		try {
			this.btnCapture.setEnabled(false);
			this.btnStop.setEnabled(false);
			btnClickAndMoveToNextScreen.setEnabled(false);
			btnAdd.setEnabled(false);
			this.shlSpyMobile.setVisible(true);
			this.openDeviceView();
			dialog.run(true, true, runnable);
		} catch (InterruptedException ex) {
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			this.btnCapture.setEnabled(true);
			this.btnCapture.setFocus();
			this.btnStop.setEnabled(true);
		}
		this.btnCapture.setEnabled(true);
		this.btnStop.setEnabled(true);
		this.btnCapture.setFocus();
	}

	private void checkMonitorCanceled(IProgressMonitor monitor) throws InterruptedException {
		if (monitor.isCanceled()) {
			throw new InterruptedException("ERROR_MSG_OPERATION_CANCELED");
		}
	}

	private String getApplicationName(String appPath) {
		String appName = "";
		if (appPath != null) {
			appName = appPath.substring(appPath.lastIndexOf('\\') + 1);
		}
		return appName;
	}

	public Map<String, String> getMobileElementProps() {
		return mobileElementProps;
	}

	public void setMobileElementProps(Map<String, String> mobileElementProps) {
		this.mobileElementProps = mobileElementProps;
	}

	public Map<String, String> getMobileParentElementProps() {
		return mobileParentElementProps;
	}

	public void setMobileParentElementProps(Map<String, String> mobileParentElementProps) {
		this.mobileParentElementProps = mobileParentElementProps;
	}

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;
	}
}
