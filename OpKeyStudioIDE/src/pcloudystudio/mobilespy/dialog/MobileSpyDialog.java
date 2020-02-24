package pcloudystudio.mobilespy.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import pcloudystudio.mobilespy.spytree.CustomCheckBoxTree;
import pcloudystudio.objectspy.dialog.MobileInspectorController;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.tree.MobileElementLabelProvider;
import pcloudystudio.objectspy.element.tree.MobileElementTreeContentProvider;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

public class MobileSpyDialog extends Dialog {

	protected Object result;
	protected Shell shlSpyMobile;
	private static Table objectPropertiesTable;
	private MobileInspectorController inspectorController;
	private CheckboxTreeViewer allObjectsCheckboxTreeViewer;
	public TreeMobileElement appRootElement;

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
		shlSpyMobile.setSize(1200, 700);
		shlSpyMobile.setText("Mobile Object Spy");

		Rectangle parentSize = getParent().getBounds();
		Rectangle shellSize = shlSpyMobile.getBounds();
		int locationX = (parentSize.width - shellSize.width) / 2 + parentSize.x;
		int locationY = (parentSize.height - shellSize.height) / 2 + parentSize.y;
		shlSpyMobile.setLocation(new Point(locationX, locationY));
		shlSpyMobile.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shlSpyMobile, SWT.NONE);

		/*************************************************
		 * Snapshot section
		 ***************************************************************/

		ScrolledComposite mobileSnapshotScrolledComposite = new ScrolledComposite(sashForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		mobileSnapshotScrolledComposite.setExpandHorizontal(true);
		mobileSnapshotScrolledComposite.setExpandVertical(true);

		/*************************************************
		 * All Objects Tree Hierarchy section
		 *********************************************/

		ScrolledComposite allObjectsTreeScrolledComposite = new ScrolledComposite(sashForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		allObjectsTreeScrolledComposite.setExpandHorizontal(true);
		allObjectsTreeScrolledComposite.setExpandVertical(true);

		this.createAllObjectsTreeHierarchy(allObjectsTreeScrolledComposite);

		/*************************************************
		 * Object Properties section
		 ******************************************************/

		ScrolledComposite objectPropertiesScrolledComposite = new ScrolledComposite(sashForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		objectPropertiesScrolledComposite.setExpandHorizontal(true);
		objectPropertiesScrolledComposite.setExpandVertical(true);

		this.createObjectPropertiesTable(objectPropertiesScrolledComposite);

		sashForm.setWeights(new int[] { 2, 3, 2 });
	}

	static void setTreeRoot(final MobileSpyDialog mobileSpyDialog, final TreeMobileElement appRootElement) {
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
}
