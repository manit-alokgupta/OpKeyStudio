package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;

public class BottomFactory extends Composite {
	private Table table;
	private Table table_1;
	private Table table_2;
	private Table table_3;
	private Table table_4;
	private Table table_5;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public BottomFactory(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		
		ExpandItem item = new ExpandItem(expandBar, SWT.NONE);
		item.setText("Bottom Factory");
		item.setHeight(300);
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
		
		SashForm sashForm = new SashForm(composite_2, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm, SWT.BORDER);
		
		Composite composite_4 = new Composite(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] {1, 1});
		
		TabItem usedByTabItem = new TabItem(tabFolder, SWT.NONE);
		usedByTabItem.setText("Used By");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		usedByTabItem.setControl(composite_5);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_5, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TabItem auditTrailsTabItem = new TabItem(tabFolder, SWT.NONE);
		auditTrailsTabItem.setText("Audit Trails");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		auditTrailsTabItem.setControl(composite_6);
		composite_6.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		table_1 = new Table(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TabItem tagsTabItem = new TabItem(tabFolder, SWT.NONE);
		tagsTabItem.setText("Tags");
		
		Composite composite_7 = new Composite(tabFolder, SWT.NONE);
		tagsTabItem.setControl(composite_7);
		composite_7.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar_1 = new ToolBar(composite_7, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		table_2 = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		TabItem executionStatusTabItem = new TabItem(tabFolder, SWT.NONE);
		executionStatusTabItem.setText("Execution Status");
		
		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
		executionStatusTabItem.setControl(composite_8);
		composite_8.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar_2 = new ToolBar(composite_8, SWT.FLAT | SWT.RIGHT);
		toolBar_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		table_3 = new Table(composite_8, SWT.BORDER | SWT.FULL_SELECTION);
		table_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);
		
		TabItem backupTabItem = new TabItem(tabFolder, SWT.NONE);
		backupTabItem.setText("Backup");
		
		Composite composite_9 = new Composite(tabFolder, SWT.NONE);
		backupTabItem.setControl(composite_9);
		composite_9.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar_3 = new ToolBar(composite_9, SWT.FLAT | SWT.RIGHT);
		toolBar_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		table_4 = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION);
		table_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);
		
		TabItem testCaseDocTabItem = new TabItem(tabFolder, SWT.NONE);
		testCaseDocTabItem.setText("Test Case Document");
		
		Composite composite_10 = new Composite(tabFolder, SWT.NONE);
		testCaseDocTabItem.setControl(composite_10);
		composite_10.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar_4 = new ToolBar(composite_10, SWT.FLAT | SWT.RIGHT);
		toolBar_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		table_5 = new Table(composite_10, SWT.BORDER | SWT.FULL_SELECTION);
		table_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);

	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
