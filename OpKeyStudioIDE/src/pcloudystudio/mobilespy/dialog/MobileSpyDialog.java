package pcloudystudio.mobilespy.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.Map;

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

import pcloudystudio.appiumserver.AppiumStarter;
import pcloudystudio.mobilespy.spytree.CustomCheckBoxTree;
import pcloudystudio.objectspy.dialog.MobileInspectorController;
import pcloudystudio.objectspy.element.MobileElement;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.CapturedMobileElement;
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
	public Object open() {   new AppiumStarter("Redmi Note 5","C:\\Users\\arjun.singh\\Desktop\\FollowMeInstrumentor_V3\\Original\\Alaska Airlines Travel_v3.31_apkpure.com.apk");
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

		setTreeRoot(MobileSpyDialog.this, this.inspectorController.getMobileObjectRoot());
		shlSpyMobile.setLayout(new GridLayout(1, false));

		Composite toolsComposite = new Composite(shlSpyMobile, SWT.NONE);
		toolsComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridData gd_toolsComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		toolsComposite.setLayoutData(gd_toolsComposite);

		Composite spyContainerComposite = new Composite(shlSpyMobile, SWT.NONE);
		GridData gd_spyContainerComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_spyContainerComposite.heightHint = 732;
		gd_spyContainerComposite.widthHint = 988;
		spyContainerComposite.setLayoutData(gd_spyContainerComposite);
		spyContainerComposite.setLayout(new FillLayout());

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

		sashForm.setWeights(new int[] { 3, 2 });
	}

	static void setTreeRoot(final MobileSpyDialog mobileSpyDialog, final TreeMobileElement appRootElement) {
		mobileSpyDialog.appRootElement = appRootElement;
	}

	public static void addTableItemToPropertiesTableData(String key, String value) {
		TableItem item1 = new TableItem(objectPropertiesTable, SWT.NONE);
		item1.setText(new String[] { key, value });
	}
	
	public static void highlightCheckedElement(String bounds) {
		final int left = Integer.parseInt(bounds.substring(1, bounds.indexOf(44)));
		final int top = Integer.parseInt(bounds.substring(bounds.indexOf(44) + 1, bounds.indexOf(93)));
		final int right = Integer.parseInt(bounds.substring(bounds.lastIndexOf(91) + 1, bounds.lastIndexOf(44)));
		final int bottom = Integer.parseInt(bounds.substring(bounds.lastIndexOf(44) + 1, bounds.lastIndexOf(93)));
	}

	public static void clearPropertiesTableData() {
		objectPropertiesTable.removeAll();
	}

	private void createAllObjectsTreeHierarchy(ScrolledComposite allObjectsTreeScrolledComposite) {
		MobileElementTreeContentProvider contentProvider = new MobileElementTreeContentProvider();
		MobileElementLabelProvider labelProvider = new MobileElementLabelProvider();

		setTreeRoot(MobileSpyDialog.this, this.inspectorController.getMobileObjectRoot());
		this.allObjectsCheckboxTreeViewer = new CustomCheckBoxTree(allObjectsTreeScrolledComposite, SWT.BORDER);
		Tree tree = this.allObjectsCheckboxTreeViewer.getTree();
		this.allObjectsCheckboxTreeViewer.setContentProvider(contentProvider);
		this.allObjectsCheckboxTreeViewer.setLabelProvider(labelProvider);
		this.allObjectsCheckboxTreeViewer.setInput((Object) new Object[] { this.appRootElement });
		this.allObjectsCheckboxTreeViewer.refresh();
		this.allObjectsCheckboxTreeViewer.expandAll();

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

		final TableEditor editor = new TableEditor(objectPropertiesTable);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		objectPropertiesTable.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = objectPropertiesTable.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = objectPropertiesTable.getTopIndex();
				while (index < objectPropertiesTable.getItemCount()) {
					boolean visible = false;
					final TableItem item = objectPropertiesTable.getItem(index);
					for (int i = 0; i < objectPropertiesTable.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(pt) && i == 1) {
							final int column = i;
							final Text text = new Text(objectPropertiesTable, SWT.NONE);
							Listener textListener = new Listener() {
								public void handleEvent(final Event e) {
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

	private void setDeviceView(final MobileDeviceDialog deviceView) {
		this.deviceView = deviceView;
	}

	private int getDeviceViewStartXIfPlaceRight(final Rectangle objectSpyViewBounds) {
		return objectSpyViewBounds.x + objectSpyViewBounds.width + 5;
	}

	private boolean isOutOfBound(final Rectangle displayBounds, final Point dialogSize, final int startX) {
		return startX < 0 || startX + dialogSize.x > displayBounds.width + displayBounds.x;
	}

	private int getDeviceViewStartXIfPlaceLeft(final Rectangle objectSpyViewBounds, final Point dialogSize) {
		return objectSpyViewBounds.x - dialogSize.x - 5;
	}

	private int getDefaultDeviceViewDialogStartX(final Rectangle displayBounds, final Point dialogSize) {
		return displayBounds.width - dialogSize.x;
	}

	private Point calculateInitPositionForDeviceViewDialog() {
		final Rectangle displayBounds = shlSpyMobile.getMonitor().getBounds();
		final Point dialogSize = new Point(400, 600);
		final Rectangle objectSpyViewBounds = shlSpyMobile.getBounds();
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
	public void setSelectedElementByLocation(final int x, final int y) {
		System.out.println(this.appRootElement.getXpath());
		if (this.appRootElement == null) {
			return;
		}
		final TreeMobileElement foundElement = this.recursivelyFindElementByLocation(this.appRootElement, x, y);
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

	private TreeMobileElement recursivelyFindElementByLocation(final TreeMobileElement currentElement, final int x,
			final int y) {
		for (final TreeMobileElement childElement : currentElement.getChildrenElement()) {
			final Map<String, String> attributes = childElement.getAttributes();
			final Double elementX = Double.parseDouble(attributes.get("x"));
			final Double elementY = Double.parseDouble(attributes.get("y"));
			final Double elementWidth = Double.parseDouble(attributes.get("width"));
			final Double elementHeight = Double.parseDouble(attributes.get("height"));
			final Rectangle rectangle = new Rectangle(MobileDeviceDialog.safeRoundDouble(elementX),
					MobileDeviceDialog.safeRoundDouble(elementY), MobileDeviceDialog.safeRoundDouble(elementWidth),
					MobileDeviceDialog.safeRoundDouble(elementHeight));
			if (rectangle.contains(x, y)) {
				return this.recursivelyFindElementByLocation(childElement, x, y);
			}
		}
		return currentElement;
	}

	private void highlightObject(final MobileElement selectedElement) {
		if (selectedElement == null || this.deviceView == null || this.deviceView.isDisposed()) {
			return;
		}
		this.deviceView.highlightElement(selectedElement);
	}

	private void captureObjectAction() {
		createAllObjectsTreeHierarchy(allObjectsTreeScrolledComposite);
		createObjectPropertiesTable(objectPropertiesScrolledComposite);
		openDeviceView();

	}

	private void verifyCapturedElementsStates(final CapturedMobileElement[] elements) {
		this.clearAllObjectState(elements);
		if (this.appRootElement != null) {
			for (final CapturedMobileElement needToVerify : elements) {
				final TreeMobileElement foundElement = this.appRootElement.findBestMatch(needToVerify);
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

	private void clearAllObjectState(final CapturedMobileElement[] elements) {
		for (final CapturedMobileElement captured : elements) {
			final TreeMobileElement treeElementLink = captured.getLink();
			if (treeElementLink != null) {
				treeElementLink.setCapturedElement(null);
				captured.setLink(null);
				final Tree elementTree = this.allObjectsCheckboxTreeViewer.getTree();
				if (elementTree != null && !elementTree.isDisposed()
						&& this.allObjectsCheckboxTreeViewer.getChecked((Object) treeElementLink)) {
					this.allObjectsCheckboxTreeViewer.setChecked((Object) treeElementLink, false);
				}
			}
		}
	}
}
