package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.AuditTrailsTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.BackupTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.ExecutionStatusTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.TagsTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.TestCaseDocumentTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol.UsedByTable;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

public class BottomFactoryUI extends Composite {
	private UsedByTable usedByTable;
	private AuditTrailsTable auditTrailsTable;
	private TagsTable tagsTable;
	private ExecutionStatusTable executionStatusTable;
	private BackupTable backupTable;
	private TestCaseDocumentTable testCaseDocTable;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public BottomFactoryUI(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

		ExpandItem item = new ExpandItem(expandBar, SWT.NONE);
		item.setText("Bottom Factory");
		item.setHeight(400);
		item.setExpanded(true);

		Group grpMenu = new Group(expandBar, SWT.NONE);
		grpMenu.setText("Menu");
		item.setControl(grpMenu);
		grpMenu.setLayout(new GridLayout(1, false));

		TabFolder tabFolder = new TabFolder(grpMenu, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setBounds(0, 0, 120, 43);

		TabItem detailsTabItem = new TabItem(tabFolder, SWT.NONE);
		detailsTabItem.setText("Details");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		detailsTabItem.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		SashForm sashForm = new SashForm(composite_2, SWT.NONE);

		Composite composite_3 = new Composite(sashForm, SWT.BORDER);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		Composite composite_4 = new Composite(sashForm, SWT.BORDER);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sashForm.setWeights(new int[] { 1, 1 });

		TabItem usedByTabItem = new TabItem(tabFolder, SWT.NONE);
		usedByTabItem.setText("Used By");

		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		usedByTabItem.setControl(composite_5);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		usedByTable = new UsedByTable(composite_5, SWT.BORDER | SWT.FULL_SELECTION, this);
		usedByTable.setHeaderVisible(true);
		usedByTable.setLinesVisible(true);

		TableCursor tableCursor = new TableCursor(usedByTable, SWT.NONE);

		TabItem auditTrailsTabItem = new TabItem(tabFolder, SWT.NONE);
		auditTrailsTabItem.setText("Audit Trails");

		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		auditTrailsTabItem.setControl(composite_6);
		composite_6.setLayout(new GridLayout(1, false));
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem exportToolItem = new ToolItem(toolBar, SWT.NONE);
		exportToolItem.setWidth(27);
		exportToolItem.setToolTipText("Export Audit Trails");
		exportToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/export.png"));

		auditTrailsTable = new AuditTrailsTable(composite_6, SWT.BORDER | SWT.FULL_SELECTION, this);
		auditTrailsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		auditTrailsTable.setHeaderVisible(true);
		auditTrailsTable.setLinesVisible(true);

		TabItem tagsTabItem = new TabItem(tabFolder, SWT.NONE);
		tagsTabItem.setText("Tags");

		Composite composite_7 = new Composite(tabFolder, SWT.NONE);
		tagsTabItem.setControl(composite_7);
		composite_7.setLayout(new GridLayout(1, false));
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_1 = new ToolBar(composite_7, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem addToolItem = new ToolItem(toolBar_1, SWT.NONE);
		addToolItem.setWidth(27);
		addToolItem.setEnabled(true);
		addToolItem.setSelection(false);
		addToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addToolItem.setToolTipText("Add");
		addToolItem.setText("");

		ToolItem deleteToolItem = new ToolItem(toolBar_1, SWT.NONE);
		deleteToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteToolItem.setToolTipText("Delete");

		ToolItem copyToolItem = new ToolItem(toolBar_1, SWT.NONE);
		copyToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/copy.png"));
		copyToolItem.setToolTipText("copy");

		ToolItem pasteToolItem = new ToolItem(toolBar_1, SWT.NONE);
		pasteToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/paste.png"));
		pasteToolItem.setToolTipText("Paste");

		ToolItem moveUpToolItem = new ToolItem(toolBar_1, SWT.NONE);
		moveUpToolItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		moveUpToolItem.setToolTipText("Move Up");

		ToolItem moveDownToolItem = new ToolItem(toolBar_1, SWT.NONE);
		moveDownToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		moveDownToolItem.setToolTipText("Move Down");

		tagsTable = new TagsTable(composite_7, SWT.BORDER | SWT.FULL_SELECTION, this);
		tagsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tagsTable.setHeaderVisible(true);
		tagsTable.setLinesVisible(true);

		TabItem executionStatusTabItem = new TabItem(tabFolder, SWT.NONE);
		executionStatusTabItem.setText("Execution Status");

		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
		executionStatusTabItem.setControl(composite_8);
		composite_8.setLayout(new GridLayout(1, false));
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_2 = new ToolBar(composite_8, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem refreshExecutionStatusToolItem = new ToolItem(toolBar_2, SWT.NONE);
		refreshExecutionStatusToolItem.setWidth(27);
		refreshExecutionStatusToolItem.setEnabled(true);
		refreshExecutionStatusToolItem.setSelection(false);
		refreshExecutionStatusToolItem.setToolTipText("Refresh");
		refreshExecutionStatusToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshExecutionStatusToolItem.setText("");

		executionStatusTable = new ExecutionStatusTable(composite_8, SWT.BORDER | SWT.FULL_SELECTION, this);
		executionStatusTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		executionStatusTable.setHeaderVisible(true);
		executionStatusTable.setLinesVisible(true);

		TabItem backupTabItem = new TabItem(tabFolder, SWT.NONE);
		backupTabItem.setText("Backup");

		Composite composite_9 = new Composite(tabFolder, SWT.NONE);
		backupTabItem.setControl(composite_9);
		composite_9.setLayout(new GridLayout(1, false));
		composite_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_3 = new ToolBar(composite_9, SWT.FLAT | SWT.RIGHT);
		toolBar_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem tltmNewItem_3 = new ToolItem(toolBar_3, SWT.NONE);
		tltmNewItem_3.setText("New Item");

		backupTable = new BackupTable(composite_9, SWT.BORDER | SWT.FULL_SELECTION, this);
		backupTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		backupTable.setHeaderVisible(true);
		backupTable.setLinesVisible(true);

		TabItem testCaseDocTabItem = new TabItem(tabFolder, SWT.NONE);
		testCaseDocTabItem.setText("Test Case Document");

		Composite composite_10 = new Composite(tabFolder, SWT.NONE);
		testCaseDocTabItem.setControl(composite_10);
		composite_10.setLayout(new GridLayout(1, false));
		composite_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		ToolBar toolBar_4 = new ToolBar(composite_10, SWT.FLAT | SWT.RIGHT);
		toolBar_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem refreshTestCaseDocToolItem = new ToolItem(toolBar_4, SWT.NONE);
		refreshTestCaseDocToolItem.setToolTipText("Refresh");
		refreshTestCaseDocToolItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));

		testCaseDocTable = new TestCaseDocumentTable(composite_10, SWT.BORDER | SWT.FULL_SELECTION, this);
		testCaseDocTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		testCaseDocTable.setHeaderVisible(true);
		testCaseDocTable.setLinesVisible(true);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
