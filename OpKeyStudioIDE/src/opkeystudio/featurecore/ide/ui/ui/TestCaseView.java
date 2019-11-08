package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.InputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTable;

public class TestCaseView extends Composite {
	private DataBindingContext m_bindingContext;
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
	private Text searchBox;
	private ToolItem toolItem;

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
		testCaseStepsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		testCaseStepsHolder.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(testCaseStepsHolder, SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));

		ToolItem itemAdd = new ToolItem(toolBar_1, SWT.NONE);
		itemAdd.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		itemAdd.setText("Add");
		
		toolItem = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemRecord = new ToolItem(toolBar_1, SWT.NONE);
		itemRecord.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/record_icon.png"));
		itemRecord.setText("Record");
		
		ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		ToolItem itemRunnow = new ToolItem(toolBar_1, SWT.NONE);
		itemRunnow.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/run_icon.png"));
		itemRunnow.setText("Run Now");
		
		ToolItem toolItem_2 = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemMoveup = new ToolItem(toolBar_1, SWT.NONE);
		itemMoveup.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		itemMoveup.setText("Move up");
		
		ToolItem toolItem_3 = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemMovedown = new ToolItem(toolBar_1, SWT.NONE);
		itemMovedown.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		itemMovedown.setText("Move Down");
		
		ToolItem toolItem_4 = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemRefresh = new ToolItem(toolBar_1, SWT.NONE);
		itemRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		itemRefresh.setText("Refresh");
		
		ToolItem toolItem_5 = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemSave = new ToolItem(toolBar_1, SWT.NONE);
		itemSave.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));
		itemSave.setText("Save");
		
		ToolItem toolItem_6 = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemDelete = new ToolItem(toolBar_1, SWT.NONE);
		itemDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		itemDelete.setText("Delete");
		
		ToolItem toolItem_7 = new ToolItem(toolBar_1, SWT.SEPARATOR);
		
		ToolItem itemSetting = new ToolItem(toolBar_1, SWT.NONE);
		itemSetting.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/setting_icon.png"));
		itemSetting.setText("Setting");

		table = new FlowStepTable(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite testCaseArgumentsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseArgumentsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseArgumentsHolder.setLayout(new GridLayout(1, false));

		TabFolder testCaseArgumentsTabFolder = new TabFolder(testCaseArgumentsHolder, SWT.NONE);
		testCaseArgumentsTabFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		testCaseArgumentsTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem stepDetailsTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setText("Step Details");
		stepDetailsTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/stepdetails.png"));

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
		
		Canvas canvas = new Canvas(composite_5, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
			}
		});
		canvas.setBounds(108, 0, 64, 64);

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
		addStepTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/addstep.png"));
		addStepTabItem.setText("Add Step");
		
		SashForm sashForm_3 = new SashForm(testCaseArgumentsTabFolder, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		addStepTabItem.setControl(sashForm_3);
		
		Composite composite_1 = new Composite(sashForm_3, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_10 = new Composite(sashForm_3, SWT.BORDER | SWT.H_SCROLL);
		composite_10.setLayout(new GridLayout(1, false));
		
		Composite composite_15 = new Composite(composite_10, SWT.NONE);
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_15.setBounds(0, 0, 64, 64);
		composite_15.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button keywordButton = new Button(composite_15, SWT.NONE);
		keywordButton.setBounds(0, 0, 75, 25);
		keywordButton.setText("Keyword");
		
		Button functionLibraryButton = new Button(composite_15, SWT.NONE);
		functionLibraryButton.setBounds(0, 0, 75, 25);
		functionLibraryButton.setText("Function Library");
		
		Button serviceRepoButton = new Button(composite_15, SWT.NONE);
		serviceRepoButton.setText("Service Repository");
		
		Button codedFunLibraryButton = new Button(composite_15, SWT.NONE);
		codedFunLibraryButton.setText("Coded Function Library");
		
		Composite composite_12 = new Composite(composite_10, SWT.NONE);
		composite_12.setLayout(new GridLayout(2, false));
		composite_12.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		searchBox = new Text(composite_12, SWT.BORDER);
		searchBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Button searchButton = new Button(composite_12, SWT.NONE);
		searchButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		searchButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		searchButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/search.png"));
		searchButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Tree tree = new Tree(composite_10, SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_3.setWeights(new int[] {0, 1});



		TabItem testObjectTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/object.png"));
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
		inputDataTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/inputdata.png"));
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

		TabItem dataOutputTabItem = new TabItem(tabFolder_1, SWT.NONE);
		dataOutputTabItem.setText("Data Output");

		table_2 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		dataOutputTabItem.setControl(table_2);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TabItem globalVariablesTabItem = new TabItem(tabFolder_1, SWT.NONE);
		globalVariablesTabItem.setText("Global Variable");

		table_3 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		globalVariablesTabItem.setControl(table_3);
		table_3.setHeaderVisible(true);
		table_3.setLinesVisible(true);

		TabItem autoDataGenTabItem = new TabItem(tabFolder_1, SWT.NONE);
		autoDataGenTabItem.setText("Auto Data Generation");

		table_4 = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		autoDataGenTabItem.setControl(table_4);
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);
		sashForm_2.setWeights(new int[] { 1, 1 });

		TabItem outputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/outputdata.png"));
		outputDataTabItem.setText("Output Data");

		Composite composite_4 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setControl(composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		outputDataTable = new OutputDataTable(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		outputDataTable.setHeaderVisible(true);
		outputDataTable.setLinesVisible(true);

		TableCursor cursor = new TableCursor(table, 0);
		testCaseSashForm.setWeights(new int[] {411, 213});
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
		m_bindingContext = initDataBindings();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		
		//
		return bindingContext;
	}
}
