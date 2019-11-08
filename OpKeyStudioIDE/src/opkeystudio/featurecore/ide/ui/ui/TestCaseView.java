package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.InputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTable;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TestCaseView extends Composite {
	private FlowStepTable table;
	private OutputDataTable outputDataTable;
	private TestObjectTable testObjectTable;
	private InputDataTable inputDataTable;
	private Table InputDataTable;
	private Table mappedTable;
	private Table propertyTable;
	private Table table_2;
	private Table table_3;
	private Table table_4;
	private Text text;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestCaseView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder mainTestCaseTabFolder = new TabFolder(this, SWT.BORDER | SWT.BOTTOM);
		mainTestCaseTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		TabItem testCaseTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		testCaseTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase.gif"));
		testCaseTabItem.setText("TestCase");

		Composite testCaseHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		testCaseHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		testCaseTabItem.setControl(testCaseHolder);
		testCaseHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm testCaseSashForm = new SashForm(testCaseHolder, SWT.NONE);
		testCaseSashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		testCaseSashForm.setSashWidth(6);

		Composite testCaseStepsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseStepsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseStepsHolder.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(testCaseStepsHolder, SWT.RIGHT);

		ToolItem tltmNewItem = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem.setText("New Item");

		ToolItem tltmNewItem_1 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_1.setText("New Item");

		ToolItem tltmNewItem_2 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_2.setText("New Item");

		table = new FlowStepTable(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite testCaseArgumentsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseArgumentsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseArgumentsHolder.setLayout(new GridLayout(1, false));

		TabFolder testCaseArgumentsTabFolder = new TabFolder(testCaseArgumentsHolder, SWT.NONE);
		testCaseArgumentsTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem stepDetailsTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setImage(null);
		stepDetailsTabItem.setText("Step Details");

		Composite composite = new Composite(testCaseArgumentsTabFolder, SWT.BORDER);
		stepDetailsTabItem.setControl(composite);
		composite.setLayout(new GridLayout(1, false));

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("Step Information");
		xpndtmNewExpanditem.setHeight(80);

		Composite composite_5 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setControl(composite_5);
		xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setExpanded(true);
		xpndtmNewExpanditem_1.setText("Test Object");
		xpndtmNewExpanditem_1.setHeight(80);

		Composite composite_6 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setControl(composite_6);
		xpndtmNewExpanditem_1.setHeight(xpndtmNewExpanditem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite_6, SWT.NONE);
		sashForm.setSashWidth(1);

		Composite composite_8 = new Composite(sashForm, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));

		mappedTable = new Table(composite_8, SWT.BORDER | SWT.FULL_SELECTION);
		mappedTable.setHeaderVisible(true);
		mappedTable.setLinesVisible(true);

		TableCursor tableCursor = new TableCursor(mappedTable, SWT.NONE);

		Composite composite_9 = new Composite(sashForm, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));

		propertyTable = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION);
		propertyTable.setHeaderVisible(true);
		propertyTable.setLinesVisible(true);

		TableCursor tableCursor_1 = new TableCursor(propertyTable, SWT.NONE);
		sashForm.setWeights(new int[] { 1, 2 });

		ExpandItem xpndtmNewExpanditem_2 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_2.setExpanded(true);
		xpndtmNewExpanditem_2.setText("Input Data");
		xpndtmNewExpanditem_2.setHeight(80);

		Composite composite_7 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem_2.setControl(composite_7);
		xpndtmNewExpanditem_2.setHeight(xpndtmNewExpanditem_2.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));

		InputDataTable = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		InputDataTable.setHeaderVisible(true);
		InputDataTable.setLinesVisible(true);

		TabItem addStepTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		addStepTabItem.setText("Add Step");
		
		SashForm sashForm_3 = new SashForm(testCaseArgumentsTabFolder, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		addStepTabItem.setControl(sashForm_3);
		
		Composite composite_1 = new Composite(sashForm_3, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));



		TabItem testObjectTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setText("Test Object");

		Composite composite_2 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		testObjectTable = new TestObjectTable(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		testObjectTable.setLinesVisible(true);
		testObjectTable.setHeaderVisible(true);
		testObjectTable.setLinesVisible(true);
		testObjectTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Tree testObbjectTree = new Tree(sashForm_1, SWT.BORDER);
		testObbjectTree.setLinesVisible(true);
		testObbjectTree.setHeaderVisible(true);
		sashForm_1.setWeights(new int[] { 1, 1 });

		TabItem inputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setText("Input Data");

		Composite composite_3 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setControl(composite_3);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(composite_3, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);

		Composite composite_13 = new Composite(sashForm_2, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));

		inputDataTable = new InputDataTable(composite_13, SWT.BORDER | SWT.FULL_SELECTION);
		inputDataTable.setHeaderVisible(true);
		inputDataTable.setLinesVisible(true);

		Composite composite_14 = new Composite(sashForm_2, SWT.NONE);
		composite_14.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder_1 = new TabFolder(composite_14, SWT.NONE);

		TabItem tbtmNewItem_4 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_4.setText("Data Output");

		table_2 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmNewItem_4.setControl(table_2);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TabItem tbtmGlobalVariables = new TabItem(tabFolder_1, SWT.NONE);
		tbtmGlobalVariables.setText("Global Variable");

		table_3 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmGlobalVariables.setControl(table_3);
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);

		TabItem tbtmNewItem_5 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_5.setText("Auto Data Generation");

		table_4 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmNewItem_5.setControl(table_4);
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);
		sashForm_2.setWeights(new int[] { 1, 1 });

		TabItem outputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setText("Output Data");

		Composite composite_4 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setControl(composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		outputDataTable = new OutputDataTable(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		outputDataTable.setHeaderVisible(true);
		outputDataTable.setLinesVisible(true);

		TableCursor cursor = new TableCursor(table, 0);
		testCaseSashForm.setWeights(new int[] {416, 208});
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				table.deselectAll();
				table.setSelection(new TableItem[] { cursor.getRow() });
				int selectedColumn = cursor.getColumn();
				System.out.println("Column " + selectedColumn);
				if (selectedColumn == 1) {
					testCaseArgumentsTabFolder.setSelection(1);
				} else if (selectedColumn == 2) {
					testCaseArgumentsTabFolder.setSelection(2);
				} else if (selectedColumn == 3) {
					testCaseArgumentsTabFolder.setSelection(3);
				} else if (selectedColumn == 4) {
					testCaseArgumentsTabFolder.setSelection(4);
				} else {
					testCaseArgumentsTabFolder.setSelection(0);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		try {
			table.renderFlowSteps();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
