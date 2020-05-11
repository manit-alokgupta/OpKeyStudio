package pcloudystudio.featurecore.ui.dialog;

import java.awt.image.BufferedImage;

// Created by Alok Gupta on 20/02/2020.
// Copyright � 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
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
import org.eclipse.wb.swt.SWTResourceManager;
import org.openqa.selenium.WebElement;

import opkeystudio.core.utils.OpKeyStudioPreferences;
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.dtoMaker.ORObjectMaker;
import pcloudystudio.appium.MobileDriverObject;
import pcloudystudio.core.utils.notifier.CustomNotificationUtil;
import pcloudystudio.resources.constant.ImageConstants;
import pcloudystudio.spytreecomponents.BasicMobileElement;
import pcloudystudio.spytreecomponents.MobileElement;
import pcloudystudio.spytreecomponents.MobileElementInspectorController;
import pcloudystudio.spytreecomponents.MobileElementLabelProvider;
import pcloudystudio.spytreecomponents.MobileElementTreeContentProvider;
import pcloudystudio.spytreecomponents.TreeMobileElement;

public class MobileElementSpyDialog extends Dialog implements MobileElementInspectorDialog {
	private ObjectRepositoryView parentObjectRepositoryView;

	public static Point DIALOG_SIZE;
	private static String DIALOG_TITLE;

	protected Object result;
	protected Shell shlSpyMobile;
	private static Table objectPropertiesTable;
	private MobileElementInspectorController inspectorController;
	public static CheckboxTreeViewer allObjectsCheckboxTreeViewer;
	public TreeMobileElement appRootElement;
	private File currentScreenshot;
	private File croppedObjectImage;

	private MobileScreenshotDialog deviceView;
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

	public static Button btnClickAndMoveToNextScreen;
	static Label lblAddToORConfirmation;
	private Composite compositeLeftToolBar;
	private Composite compositeRightToolBar;
	private List<TreeMobileElement> allElements;

	static {
		DIALOG_TITLE = "Mobile Object Spy";
	}

	public MobileElementSpyDialog(Shell parent, int style, ObjectRepositoryView parentObjectRepositoryView) {
		super(parent, style);
		this.setParentObjectRepositoryView(parentObjectRepositoryView);
		setText("SWT Dialog");
		this.inspectorController = new MobileElementInspectorController();
	}

	public Object open() {
		Cursor waitCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT);
		getParent().setCursor(waitCursor);
		createContents();
		shlSpyMobile.open();
		shlSpyMobile.layout();
		Display display = getParent().getDisplay();
		Cursor arrow = new Cursor(display, SWT.CURSOR_ARROW);
		getParent().setCursor(arrow);
		while (!shlSpyMobile.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void createContents() {
		shlSpyMobile = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlSpyMobile.setImage(ImageConstants.IMG_16_OPKEY_LOGO);
		shlSpyMobile.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlSpyMobile.setSize(new Point(800, 700));
		shlSpyMobile.setText(DIALOG_TITLE);
		shlSpyMobile.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		shlSpyMobile.setMinimumSize(new Point(650, 400));

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlSpyMobile.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 4 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 4 + parentSize.y;
		shlSpyMobile.setLocation(new Point(locationX, locationY));
		shlSpyMobile.setLayout(new GridLayout(1, false));

		Composite toolsComposite = new Composite(shlSpyMobile, SWT.BORDER);
		GridData gd_toolsComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_toolsComposite.heightHint = 38;
		gd_toolsComposite.widthHint = 785;
		toolsComposite.setLayoutData(gd_toolsComposite);
		toolsComposite.setLayout(new FillLayout());

		SashForm sashFormToolsComposite = new SashForm(toolsComposite, SWT.NONE);

		compositeLeftToolBar = new Composite(sashFormToolsComposite, SWT.NONE | SWT.LEFT_TO_RIGHT);
		GridData gd_compositeToolBarLeft = new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1);
		compositeLeftToolBar.setLayoutData(gd_compositeToolBarLeft);

		btnCapture = new Button(compositeLeftToolBar, SWT.NONE);
		btnCapture.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NONE));
		btnCapture.setLocation(5, 5);
		btnCapture.setSize(131, 28);
		btnCapture.setToolTipText("Capture Object");
		btnCapture.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnCapture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				captureObjectAction();
			}
		});
		btnCapture.setText("Capture Object");

		btnClickAndMoveToNextScreen = new Button(compositeLeftToolBar, SWT.NONE);
		btnClickAndMoveToNextScreen.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NONE));
		btnClickAndMoveToNextScreen.setLocation(142, 5);
		btnClickAndMoveToNextScreen.setSize(181, 28);
		btnClickAndMoveToNextScreen.setEnabled(false);
		btnClickAndMoveToNextScreen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblAddToORConfirmation.setVisible(false);
				if (MobileDriverObject.getInstance() != null) {
					Object element = allObjectsCheckboxTreeViewer.getCheckedElements();
					Widget item = CustomCheckBoxTree.getCheckedItem(element);
					TreeItem treeItem = (TreeItem) item;
					Object obj = treeItem.getData();
					Map<String, String> mobileElementProps = ((BasicMobileElement) obj).getAttributes();
					try {
						WebElement foundElement = MobileDriverObject.getDriver()
								.findElementByXPath(mobileElementProps.get("xpath"));
						if (foundElement != null) {
							foundElement.click();
							btnAdd.setEnabled(false);
							captureObjectAction();
						}
					} catch (Exception ex) {
						CustomNotificationUtil.openErrorNotification("Error", ex.getMessage());
					}
				}
			}
		});
		btnClickAndMoveToNextScreen.setText("Click and Update Spy");

		btnStop = new Button(compositeLeftToolBar, SWT.NONE);
		btnStop.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NONE));
		btnStop.setLocation(329, 5);
		btnStop.setSize(49, 28);
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					btnCapture.setEnabled(false);
					lblAddToORConfirmation.setVisible(false);
					btnClickAndMoveToNextScreen.setEnabled(false);
					btnStop.setEnabled(false);
					btnAdd.setEnabled(false);
					allObjectsCheckboxTreeViewer.getTree().removeAll();
					clearPropertiesTableData();
					textObjectName.setText("");
					deviceView.close();
					MobileDriverObject.getDriver().quit();
				} catch (Exception ex) {
					CustomNotificationUtil.openErrorNotification("Error", ex.getMessage());
				}
			}
		});
		btnStop.setText("Stop");

		compositeRightToolBar = new Composite(sashFormToolsComposite, SWT.NONE | SWT.RIGHT_TO_LEFT);
		GridData gd_compositeToolBarRight = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		compositeRightToolBar.setLayoutData(gd_compositeToolBarRight);

		btnAdd = new Button(compositeRightToolBar, SWT.NONE);
		btnAdd.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NONE));
		btnAdd.setLocation(10, 5);
		btnAdd.setSize(220, 28);
		btnAdd.setToolTipText("Add to Object Repository");
		btnAdd.setEnabled(false);
		btnAdd.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textObjectName.getText().length() > 0) {
					lblAddToORConfirmation.setVisible(false);
					btnClickAndMoveToNextScreen.setEnabled(false);
					btnStop.setEnabled(false);
					btnCapture.setEnabled(false);

					String objectName = textObjectName.getText().toString();
					LinkedHashMap<String, String> mapOfObjectProperties = new LinkedHashMap<String, String>();
					for (TableItem row : objectPropertiesTable.getItems())
						mapOfObjectProperties.put(row.getText(0), row.getText(1));

					String parentActivityPathName = mapOfObjectProperties.get("package")
							+ mapOfObjectProperties.get("activity");
					LinkedHashMap<String, String> parentActivityPathProperties = new LinkedHashMap<String, String>();
					try {
						BufferedImage bimg = ImageIO.read(currentScreenshot);
						parentActivityPathProperties.put("class", parentActivityPathName);
						parentActivityPathProperties.put("width", Integer.toString(bimg.getWidth()));
						parentActivityPathProperties.put("height", Integer.toString(bimg.getHeight()));
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					List<ORObject> allORObjects = getParentObjectRepositoryView().getObjectRepositoryTree()
							.getAllORObjects();
					ORObject foundParentObject = null;
					for (ORObject foundParentObjectInOR : allORObjects) {
						if (foundParentObjectInOR.getName().equals(parentActivityPathName)) {
							foundParentObject = foundParentObjectInOR;
							break;
						}
					}

					String renamedText = null;

					// SQLITE_CONSTRAINT_UNIQUE: next time add map of name to id for continuous
					// check
					if (foundParentObject != null) {
						for (ORObject obj : allORObjects) {
							if ((obj.getParent_object_id() != null)
									&& obj.getParent_object_id().equals(foundParentObject.getObject_id())
									&& obj.getName().equals(objectName)) {

								renamedText = CustomNotificationUtil.openInputDialog("Rename",
										"One object is already added with the same name in the OR. \nRename Object: "
												+ objectName,
												objectName);
								if (renamedText == null)
									return;

								while (renamedText.trim().isEmpty()) {
									CustomNotificationUtil.openErrorNotification("Error", "Name can't be empty!");
									renamedText = CustomNotificationUtil.openInputDialog("Rename",
											"One object is already added with the same name in the OR. \nRename Object: "
													+ objectName,
													objectName);
									if (renamedText == null)
										return;
								}
							}
						}
					}

					try {
						new ORObjectMaker().addMobileObject(getParentObjectRepositoryView().getArtifact(),
								(renamedText != null) ? renamedText : objectName, mapOfObjectProperties,
										parentActivityPathName, parentActivityPathProperties, "Custom", "HTML Page",
										foundParentObject, allORObjects);
						getParentObjectRepositoryView().getObjectRepositoryTree().renderObjectRepositories();
						lblAddToORConfirmation.setVisible(true);
						btnClickAndMoveToNextScreen.setEnabled(true);
						btnStop.setEnabled(true);
						btnCapture.setEnabled(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					CustomNotificationUtil.openErrorNotification("Error", "Object Name field can't be empty!");
				}
			}
		});

		btnAdd.setText("Add to Object Repository");
		sashFormToolsComposite.setWeights(new int[] { 475, 286 });

		Composite spyContainerComposite = new Composite(shlSpyMobile, SWT.NONE);
		GridData gd_spyContainerComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_spyContainerComposite.heightHint = 600;
		gd_spyContainerComposite.widthHint = 785;
		spyContainerComposite.setLayoutData(gd_spyContainerComposite);
		spyContainerComposite.setLayout(new FillLayout());

		Composite bottomComposite = new Composite(shlSpyMobile, SWT.BORDER);
		GridData gd_bottomComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_bottomComposite.widthHint = 400;
		gd_bottomComposite.heightHint = 30;
		bottomComposite.setLayoutData(gd_bottomComposite);

		lblAddToORConfirmation = new Label(bottomComposite, SWT.NONE);
		lblAddToORConfirmation.setVisible(false);
		lblAddToORConfirmation.setText("Object has been added successifully!");
		lblAddToORConfirmation.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblAddToORConfirmation.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblAddToORConfirmation.setBounds(489, 6, 265, 20);
		lblAddToORConfirmation.setVisible(false);

		btnHelp = new Button(bottomComposite, SWT.NONE);
		btnHelp.setToolTipText("Help");
		btnHelp.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnHelp.setImage(ImageConstants.IMG_16_HELP);
		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomNotificationUtil.openInformationNotification("Help", "Please contact support@opkey.com");
			}
		});
		btnHelp.setBounds(5, 5, 20, 20);

		SashForm sashForm = new SashForm(spyContainerComposite, SWT.NONE);

		compositeTreeHierarchy = new Composite(sashForm, SWT.BORDER);
		compositeTreeHierarchy.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		compositeTreeHierarchy.setLayout(new GridLayout(1, true));

		lblAllObjects = new Label(compositeTreeHierarchy, SWT.NONE);
		lblAllObjects.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblAllObjects.setText("ALL OBJECTS");

		allObjectsTreeScrolledComposite = new ScrolledComposite(compositeTreeHierarchy,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		allObjectsTreeScrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
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
		GridData gd_textObjectName = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_textObjectName.widthHint = 322;
		textObjectName.setLayoutData(gd_textObjectName);
		textObjectName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));

		objectPropertiesScrolledComposite = new ScrolledComposite(compositeObjectProperties,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		objectPropertiesScrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectPropertiesScrolledComposite.setExpandHorizontal(true);
		objectPropertiesScrolledComposite.setExpandVertical(true);
		sashForm.setWeights(new int[] { 2, 1 });

		if (MobileDriverObject.getDriver() == null) {
			btnCapture.setEnabled(false);
			btnStop.setEnabled(false);
		} else {
			captureObjectAction();
		}
	}

	static void setTreeRoot(MobileElementSpyDialog mobileSpyDialog, TreeMobileElement appRootElement) {
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
		allObjectsCheckboxTreeViewer = new CustomCheckBoxTree(allObjectsTreeScrolledComposite, SWT.NONE);
		Tree tree = allObjectsCheckboxTreeViewer.getTree();
		allObjectsCheckboxTreeViewer.setContentProvider(contentProvider);
		allObjectsCheckboxTreeViewer.setLabelProvider(labelProvider);

		allObjectsTreeScrolledComposite.setContent(tree);
		allObjectsTreeScrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		tree.setLayoutData((Object) new GridData(4, 4, true, true, 1, 1));
		allObjectsCheckboxTreeViewer.setLabelProvider((IBaseLabelProvider) new MobileElementLabelProvider());
		allObjectsCheckboxTreeViewer.setContentProvider((IContentProvider) new MobileElementTreeContentProvider());
		tree.setToolTipText("");
		ColumnViewerToolTipSupport.enableFor((ColumnViewer) allObjectsCheckboxTreeViewer, 2);
		tree.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.button != 1) {
					return;
				}
				Point pt = new Point(e.x, e.y);
				TreeItem item = tree.getItem(pt);
				if (item != null) {
					MobileElementSpyDialog.this.highlightObject((MobileElement) item.getData());
				}
			}
		});
	}

	private void createObjectPropertiesTable(ScrolledComposite objectPropertiesScrolledComposite) {
		objectPropertiesTable = new Table(objectPropertiesScrolledComposite, SWT.NONE | SWT.FULL_SELECTION);
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
		(this.deviceView = new MobileScreenshotDialog(shlSpyMobile, this,
				this.calculateInitPositionForDeviceViewDialog())).open();
		setDeviceView(this.deviceView);
	}

	private void setDeviceView(MobileScreenshotDialog deviceView) {
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
		Point dialogSize = new Point(MobileScreenshotDialog.DIALOG_WIDTH, MobileScreenshotDialog.DIALOG_HEIGHT);
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

	public void setAllElements(TreeMobileElement xmlElement) {
		if (xmlElement == null) {
			return;
		}
		if (xmlElement.getChildrenElement().size() == 0) {
			return;
		}
		if (xmlElement.getParentElement() != null)
			allElements.add(xmlElement);
		for (int count = xmlElement.getChildrenElement().size(), i = 0; i < count; ++i) {
			TreeMobileElement node = xmlElement.getChildrenElement().get(i);
			allElements.add(node);
			setAllElements(node);
		}
	}

	@Override
	public void setSelectedElementByLocation(int x, int y) {
		if (this.appRootElement == null) {
			return;
		}

		this.allElements = new ArrayList<TreeMobileElement>();
		this.setAllElements(this.appRootElement);

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
				MobileElementSpyDialog.clearPropertiesTableData();
				textObjectName.setText("");
			}
		});
	}

	private TreeMobileElement recursivelyFindElementByLocation(TreeMobileElement currentElement, int x, int y) {
		TreeMobileElement smallestElement = allElements.get(0);
		List<TreeMobileElement> elementsLiesIN = new ArrayList<TreeMobileElement>();
		for (TreeMobileElement childElement : allElements) {
			Map<String, String> attributes = childElement.getAttributes();
			Double elementX = Double.parseDouble(attributes.get("x"));
			Double elementY = Double.parseDouble(attributes.get("y"));
			Double elementWidth = Double.parseDouble(attributes.get("width"));
			Double elementHeight = Double.parseDouble(attributes.get("height"));
			Rectangle rectangle = new Rectangle(MobileScreenshotDialog.safeRoundDouble(elementX),
					MobileScreenshotDialog.safeRoundDouble(elementY),
					MobileScreenshotDialog.safeRoundDouble(elementWidth),
					MobileScreenshotDialog.safeRoundDouble(elementHeight));
			if (rectangle.contains(x, y)) {
				elementsLiesIN.add(childElement);
				if (rectangle.width * rectangle.height < MobileScreenshotDialog
						.safeRoundDouble(Double.parseDouble(smallestElement.getAttributes().get("height")))
						* MobileScreenshotDialog
						.safeRoundDouble(Double.parseDouble(smallestElement.getAttributes().get("width")))) {
					smallestElement = childElement;
				}
			}
		}
		return (elementsLiesIN.size() > 0) ? elementsLiesIN.get(elementsLiesIN.size() - 1) : this.appRootElement;
	}

	private void highlightObject(MobileElement selectedElement) {
		if (selectedElement == null || this.deviceView == null || this.deviceView.isDisposed()) {
			return;
		}
		this.deviceView.highlightElement(selectedElement);
	}

	private void captureObjectAction() {
		lblAddToORConfirmation.setVisible(false);
		if (textObjectName.getText().length() > 0)
			textObjectName.setText("");

		createAllObjectsTreeHierarchy(allObjectsTreeScrolledComposite);
		createObjectPropertiesTable(objectPropertiesScrolledComposite);

		try {
			MobileDriverObject.getDriver().getSessionDetails();
		} catch (Exception ex) {
			btnStop.setEnabled(false);
			btnCapture.setEnabled(false);
			btnClickAndMoveToNextScreen.setEnabled(false);
			btnAdd.setEnabled(false);
			allObjectsCheckboxTreeViewer.getTree().removeAll();
			clearPropertiesTableData();
			textObjectName.setText("");
			deviceView.close();
			CustomNotificationUtil.openErrorNotification("Error", ex.getMessage());
			return;
		}

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
						MobileElementSpyDialog.setTreeRoot(MobileElementSpyDialog.this,
								MobileElementSpyDialog.this.inspectorController.getMobileObjectRoot());
						if (MobileElementSpyDialog.this.appRootElement != null) {
							MobileElementSpyDialog.this.appRootElement.setName(appName);
						}
						return null;
					}
				});

				MobileElementSpyDialog.this.checkMonitorCanceled(monitor);
				this.refreshTreeElements(dialog);
				String imgPath = this.captureImage();
				MobileElementSpyDialog.this.checkMonitorCanceled(monitor);
				this.refreshDeviceView(imgPath);
				Display.getDefault().syncExec((Runnable) new Runnable() {
					@Override
					public void run() {
						MobileElementSpyDialog.this.deviceView.getShell().forceActive();
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
						.setInput((Object) new Object[] { MobileElementSpyDialog.this.appRootElement });
						allObjectsCheckboxTreeViewer.refresh();
						allObjectsCheckboxTreeViewer.expandAll();
						dialog.setCancelable(true);
					}
				});
			}

			private void refreshDeviceView(String imgPath) {
				currentScreenshot = new File(imgPath);
				if (currentScreenshot.exists()) {
					MobileElementSpyDialog.this.deviceView.refreshDialog(currentScreenshot,
							MobileElementSpyDialog.this.appRootElement);
				}
			}

			private String captureImage() throws InvocationTargetException {
				try {
					return MobileElementSpyDialog.this.inspectorController.captureScreenshot();
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
			throw new InterruptedException("Operation Canceled!");
		}
	}

	private String getApplicationName(String appPath) {
		String appName = "";
		if (appPath != null) {
			appName = appPath.substring(appPath.lastIndexOf('\\') + 1);
		}
		return appName;
	}

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;
	}

	@SuppressWarnings("unused")
	private String croppedObjectImage(TreeMobileElement selectedElement) {
		BufferedImage bufferedImage = null;
		String encodstring = null;
		try {
			bufferedImage = ImageIO.read(currentScreenshot);
			int x = Integer.parseInt((selectedElement.getAttributes().get("x")));
			int y = Integer.parseInt((selectedElement.getAttributes().get("y")));
			int width = Integer.parseInt((selectedElement.getAttributes().get("width")));
			int height = Integer.parseInt((selectedElement.getAttributes().get("height")));
			BufferedImage image = bufferedImage.getSubimage(x, y, width, height);

			croppedObjectImage = new File(
					currentScreenshot.getPath().substring(0, currentScreenshot.getPath().lastIndexOf('\\')) + "\\"
							+ currentScreenshot.getName().substring(0, currentScreenshot.getName().lastIndexOf('.'))
							+ "-cropped-object.jpeg");
			System.out.println(croppedObjectImage.getAbsolutePath());
			ImageIO.write(image, "jpg", croppedObjectImage);
			encodstring = encodeFileToBase64Binary(croppedObjectImage);
			System.out.println(encodstring);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encodstring;
	}

	@SuppressWarnings("resource")
	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		return new String(Base64.encodeBase64(bytes), "UTF-8");
	}
}
