package pcloudystudio.mobilespy.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import java.util.Map;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import pcloudystudio.mobilespy.spytree.CustomCheckBoxTree;
import pcloudystudio.objectspy.element.MobileElement;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.CapturedMobileElement;
import pcloudystudio.objectspy.element.impl.dialog.ProgressMonitorDialogWithThread;
import pcloudystudio.objectspy.element.tree.MobileElementLabelProvider;
import pcloudystudio.objectspy.element.tree.MobileElementTreeContentProvider;
import pcloudystudio.objectspy.viewer.CapturedObjectTableViewer;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.GridLayout;

public class MobileSpyDialog extends Dialog implements MobileElementInspectorDialog {

	protected Object result;
	protected Shell shlSpyMobile;
	private static Table objectPropertiesTable;
	private MobileInspectorController inspectorController;
	private CheckboxTreeViewer allObjectsCheckboxTreeViewer;
	public TreeMobileElement appRootElement;

	private MobileDeviceDialog deviceView;
	private Button btnCapture;
	private Button btnHelp;

	private ScrolledComposite allObjectsTreeScrolledComposite;
	private ScrolledComposite objectPropertiesScrolledComposite;
	private CapturedObjectTableViewer capturedObjectsTableViewer;

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
		shlSpyMobile = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlSpyMobile.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/pcloudystudio/mobile_spy.png"));
		shlSpyMobile.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		shlSpyMobile.setSize(1000, 800);
		shlSpyMobile.setText("Mobile Object Spy");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlSpyMobile.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlSpyMobile.setLocation(new Point(locationX, locationY));
		shlSpyMobile.setLayout(new GridLayout(1, false));

		Composite toolsComposite = new Composite(shlSpyMobile, SWT.BORDER);
		toolsComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridData gd_toolsComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_toolsComposite.heightHint = 43;
		toolsComposite.setLayoutData(gd_toolsComposite);

		Composite spyContainerComposite = new Composite(shlSpyMobile, SWT.NONE);
		GridData gd_spyContainerComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_spyContainerComposite.heightHint = 670;
		gd_spyContainerComposite.widthHint = 985;
		spyContainerComposite.setLayoutData(gd_spyContainerComposite);
		spyContainerComposite.setLayout(new FillLayout());

		Composite bottomComposite = new Composite(shlSpyMobile, SWT.BORDER);
		bottomComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridData gd_bottomComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_bottomComposite.heightHint = 32;
		bottomComposite.setLayoutData(gd_bottomComposite);

		SashForm sashForm = new SashForm(spyContainerComposite, SWT.NONE);

		/*************************************************
		 * Snapshot section
		 ***************************************************************/

		/*************************************************
		 * All Objects Tree Hierarchy section
		 *********************************************/

		allObjectsTreeScrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		allObjectsTreeScrolledComposite.setExpandHorizontal(true);
		allObjectsTreeScrolledComposite.setExpandVertical(true);

		// this.createAllObjectsTreeHierarchy(allObjectsTreeScrolledComposite);

		/*************************************************
		 * Object Properties section
		 ******************************************************/

		objectPropertiesScrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		objectPropertiesScrolledComposite.setExpandHorizontal(true);
		objectPropertiesScrolledComposite.setExpandVertical(true);

		// this.createObjectPropertiesTable(objectPropertiesScrolledComposite);

		// -------------------------------------------------------------//
		btnCapture = new Button(toolsComposite, SWT.NONE);
		btnCapture.setToolTipText("Capture");
		btnCapture.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		btnCapture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				captureObjectAction();
			}
		});

		btnCapture.setBounds(10, 10, 75, 25);
		btnCapture.setText("Capture");

		// -------------------------------------------------------------//
		btnHelp = new Button(bottomComposite, SWT.NONE);
		btnHelp.setToolTipText("Help");
		btnHelp.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});

		btnHelp.setBounds(10, 5, 45, 22);
		btnHelp.setText("Help");

		sashForm.setWeights(new int[] { 3, 2 });
	}

	static void setTreeRoot(MobileSpyDialog mobileSpyDialog, TreeMobileElement appRootElement) {
		mobileSpyDialog.appRootElement = appRootElement;
	}

	public static void addTableItemToPropertiesTableData(String key, String value) {
		TableItem item1 = new TableItem(objectPropertiesTable, SWT.NONE);
		item1.setText(new String[] { key, value });
	}

	public static void clearPropertiesTableData() {
		objectPropertiesTable.removeAll();
	}

	private void createAllObjectsTreeHierarchy(ScrolledComposite allObjectsTreeScrolledComposite) {
		MobileElementTreeContentProvider contentProvider = new MobileElementTreeContentProvider();
		MobileElementLabelProvider labelProvider = new MobileElementLabelProvider();
		this.allObjectsCheckboxTreeViewer = new CustomCheckBoxTree(allObjectsTreeScrolledComposite, SWT.BORDER);
		Tree tree = this.allObjectsCheckboxTreeViewer.getTree();
		this.allObjectsCheckboxTreeViewer.setContentProvider(contentProvider);
		this.allObjectsCheckboxTreeViewer.setLabelProvider(labelProvider);

		allObjectsTreeScrolledComposite.setContent(tree);
		allObjectsTreeScrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	private void createObjectPropertiesTable(ScrolledComposite objectPropertiesScrolledComposite) {
		objectPropertiesTable = new Table(objectPropertiesScrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		objectPropertiesTable.setHeaderVisible(true);
		objectPropertiesTable.setLinesVisible(true);
		objectPropertiesScrolledComposite.setContent(objectPropertiesTable);

		TableColumn tblclmnName = new TableColumn(objectPropertiesTable, SWT.NONE);
		tblclmnName.setWidth(96);
		tblclmnName.setText("Name");

		TableColumn tblclmnValue = new TableColumn(objectPropertiesTable, SWT.NONE);
		tblclmnValue.setWidth(239);
		tblclmnValue.setText("Value");

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
		Point dialogSize = new Point(400, 600);
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
			@Override
			public void run() {
				shlSpyMobile.setFocus();
				allObjectsCheckboxTreeViewer.getTree().setFocus();
				allObjectsCheckboxTreeViewer.setSelection((ISelection) new StructuredSelection((Object) foundElement));
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
		createAllObjectsTreeHierarchy(allObjectsTreeScrolledComposite);
		createObjectPropertiesTable(objectPropertiesScrolledComposite);

		String appName = "Demo App Name";
		ProgressMonitorDialogWithThread dialog = new ProgressMonitorDialogWithThread(shlSpyMobile);
		IRunnableWithProgress runnable = (IRunnableWithProgress) new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				monitor.beginTask("Starting Application for Spy ...", -1);
				dialog.runAndWait((Callable<Object>) new Callable<Object>() {
					@Override
					public Object call() throws Exception {
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
						MobileSpyDialog.this.allObjectsCheckboxTreeViewer
						.setInput((Object) new Object[] { MobileSpyDialog.this.appRootElement });
						MobileSpyDialog.this.allObjectsCheckboxTreeViewer.refresh();
						MobileSpyDialog.this.allObjectsCheckboxTreeViewer.expandAll();
						MobileSpyDialog.this
						.verifyCapturedElementsStates(MobileSpyDialog.this.capturedObjectsTableViewer
								.getCapturedElements().toArray(new CapturedMobileElement[0]));
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
			this.openDeviceView();
			dialog.run(true, true, runnable);
		} catch (InterruptedException ex) {
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			this.btnCapture.setEnabled(true);
		}
		this.btnCapture.setEnabled(true);
	}

	private void checkMonitorCanceled(IProgressMonitor monitor) throws InterruptedException {
		if (monitor.isCanceled()) {
			throw new InterruptedException("ERROR_MSG_OPERATION_CANCELED");
		}
	}

	private void verifyCapturedElementsStates(CapturedMobileElement[] elements) {
		this.clearAllObjectState(elements);
		if (this.appRootElement != null) {
			for (CapturedMobileElement needToVerify : elements) {
				TreeMobileElement foundElement = this.appRootElement.findBestMatch(needToVerify);
				if (foundElement != null) {
					needToVerify.setLink(foundElement);
					foundElement.setCapturedElement(needToVerify);
					this.allObjectsCheckboxTreeViewer.setChecked((Object) foundElement, true);
				}
			}
		}
		this.allObjectsCheckboxTreeViewer.refresh();
		this.capturedObjectsTableViewer.refresh();
	}

	private void clearAllObjectState(CapturedMobileElement[] elements) {
		for (CapturedMobileElement captured : elements) {
			TreeMobileElement treeElementLink = captured.getLink();
			if (treeElementLink != null) {
				treeElementLink.setCapturedElement(null);
				captured.setLink(null);
				Tree elementTree = this.allObjectsCheckboxTreeViewer.getTree();
				if (elementTree != null && !elementTree.isDisposed()
						&& this.allObjectsCheckboxTreeViewer.getChecked((Object) treeElementLink)) {
					this.allObjectsCheckboxTreeViewer.setChecked((Object) treeElementLink, false);
				}
			}
		}
	}
}
