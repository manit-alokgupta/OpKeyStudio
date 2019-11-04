package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;

public class DemoTestCaseView extends Composite {
	private Table table;
	private Table table_3;
	private Table table_1;
	private Table table_2;
	private Table table_4;
	private Table table_5;
	private Table table_6;
	private Table table_7;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DemoTestCaseView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		CTabFolder tabFolder = new CTabFolder(this, SWT.BORDER);
		tabFolder.setTabPosition(SWT.BOTTOM);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
		
		SashForm sashForm = new SashForm(tabFolder, SWT.SMOOTH);
		tabItem.setControl(sashForm);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginHeight = 1;
		gl_composite.marginWidth = 1;
		gl_composite.verticalSpacing = 1;
		composite.setLayout(gl_composite);
		
		ToolBar toolBar = new ToolBar(composite, SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 210, 23);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setBounds(0, 0, 4, 45);
		composite_2.setLayout(new TreeColumnLayout());
		
		TreeViewer treeViewer_1 = new TreeViewer(composite_2, SWT.BORDER);
		Tree tree_1 = treeViewer_1.getTree();
		tree_1.setHeaderVisible(true);
		tree_1.setLinesVisible(true);
		
		TabFolder tabFolder_1 = new TabFolder(sashForm, SWT.NONE );
		tabFolder_1.setOrientation(SWT.VERTICAL);
		
		
		TabItem tabItem_1 = new TabItem(tabFolder_1, SWT.NONE);
		
		tabItem_1.setText("New Item");
		
		table = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		tabItem_1.setControl(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText("New TableItem");
		
		TableItem tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText("New TableItem");
		
		TabItem tabItem_2 = new TabItem(tabFolder_1, SWT.NONE);
		tabItem_2.setText("New Item");
		
		table_3 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		tabItem_2.setControl(table_3);
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);
		
		TableItem tableItem_6 = new TableItem(table_3, SWT.NONE);
		tableItem_6.setText("New TableItem");
		
		TableItem tableItem_7 = new TableItem(table_3, SWT.NONE);
		tableItem_7.setText("New TableItem");
		
		table_1 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		
		table_2 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		
		table_4 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		
		table_5 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		
		Composite composite_1 = new Composite(tabFolder_1, SWT.NONE);
		
		table_7 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_7.setHeaderVisible(true);
		table_7.setLinesVisible(true);
		composite_1.setLayout(new TableColumnLayout());
		
		TableViewer tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_6 = tableViewer.getTable();
		table_6.setHeaderVisible(true);
		table_6.setLinesVisible(true);
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		sashForm.setWeights(new int[] {3, 1});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
