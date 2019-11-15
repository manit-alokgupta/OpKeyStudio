package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
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
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.StepDetailsInputData;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowConstruct;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class TestCaseView extends Composite {
	private FlowStepTable flowStepTable;
	private OutputDataTable outputDataTable;
	private TestObjectTable testObjectTable;
	private InputDataTable inputDataTable;
	private StepDetailsInputData InputDataTable;
	private TestObjectTree testObjectTree;
	private Table mappedTable;
	private Table propertyTable;
	private Table dataOutputTable;
	private Table globalVariableTable;
	private Table autoDataGenTable;
	private Text searchBox;
	private ToolItem seperator1;
	private ToolItem seperator2;
	private ToolItem seperator3;
	private ToolItem seperator4;
	private ToolItem seperator5;
	private ToolItem seperator6;
	private ToolItem seperator7;
	private ToolItem itemRunnow;
	private ToolItem itemMoveup;
	private ToolItem itemAdd;
	private ToolItem itemRecord;
	private ToolItem itemMovedown;
	private ToolItem itemRefresh;
	private ToolItem itemSave;
	private ToolItem itemDelete;
	private ToolItem keywordButton;
	private ToolItem functionLibraryButton;
	private ToolItem serviceRepoButton;
	private ToolItem codedFunLibraryButton;
	private TabFolder testCaseArgumentsTabFolder;
	private TabItem stepDetailsTabItem;
	private ExpandItem expanditemStepIno;
	private ExpandItem expenditemInputData;
	private ExpandItem expanditemTestObject;
	private TabItem addStepTabItem;
	private Tree allDataTreeView;
	private Label stepInfoImage;
	private CLabel stepInfoLabel;
	private FlowStep selectedFlowStep;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestCaseView(Composite parent, int style) {
		super(parent, style);
		TestCaseUI();

	}

	public void TestCaseUI() {
		setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder mainTestCaseTabFolder = new TabFolder(this, SWT.BORDER | SWT.BOTTOM);
		mainTestCaseTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		TabItem testCaseTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		testCaseTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase.gif"));
		testCaseTabItem.setText("TestCase");

		Composite testCaseHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		testCaseHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		testCaseTabItem.setControl(testCaseHolder);
		testCaseHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm testCaseSashForm = new SashForm(testCaseHolder, SWT.NONE);

		testCaseSashForm.setSashWidth(6);

		Composite testCaseStepsHolder = new Composite(testCaseSashForm, SWT.NONE);

		testCaseStepsHolder.setLayout(new GridLayout(1, false));

		Composite composite_11 = new Composite(testCaseStepsHolder, SWT.NONE);
		composite_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_11.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite_11.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(composite_11, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		itemAdd = new ToolItem(toolBar_1, SWT.NONE);
		itemAdd.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		itemAdd.setText("Add");

		seperator1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemRecord = new ToolItem(toolBar_1, SWT.NONE);
		itemRecord.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/record_icon.png"));
		itemRecord.setText("Record");

		seperator2 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemRunnow = new ToolItem(toolBar_1, SWT.NONE);
		itemRunnow.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/run_icon.png"));
		itemRunnow.setText("Run Now");

		seperator3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemMoveup = new ToolItem(toolBar_1, SWT.NONE);
		itemMoveup.setEnabled(false);
		itemMoveup.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		itemMoveup.setText("Move up");

		seperator4 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemMovedown = new ToolItem(toolBar_1, SWT.NONE);
		itemMovedown.setEnabled(false);
		itemMovedown.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		itemMovedown.setText("Move Down");

		seperator5 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemDelete = new ToolItem(toolBar_1, SWT.NONE);
		itemDelete.setEnabled(false);
		itemDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		itemDelete.setText("Delete");

		seperator6 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemSave = new ToolItem(toolBar_1, SWT.NONE);
		itemSave.setEnabled(false);
		itemSave.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));
		itemSave.setText("Save");

		seperator7 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemRefresh = new ToolItem(toolBar_1, SWT.NONE);
		itemRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		itemRefresh.setText("Refresh");

		flowStepTable = new FlowStepTable(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION, this);
		flowStepTable.setLinesVisible(true);
		flowStepTable.setHeaderVisible(true);
		flowStepTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite testCaseArgumentsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseArgumentsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseArgumentsHolder.setLayout(new GridLayout(1, false));

		testCaseArgumentsTabFolder = new TabFolder(testCaseArgumentsHolder, SWT.NONE);
		testCaseArgumentsTabFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		testCaseArgumentsTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		stepDetailsTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setText("Step Details");
		stepDetailsTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/stepdetails.png"));

		Composite composite = new Composite(testCaseArgumentsTabFolder, SWT.BORDER);
		stepDetailsTabItem.setControl(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));

		expanditemStepIno = new ExpandItem(expandBar, SWT.NONE);
		expanditemStepIno.setExpanded(true);
		expanditemStepIno.setText("Step Information");
		expanditemStepIno.setHeight(150);

		Composite composite_5 = new Composite(expandBar, SWT.NONE);
		expanditemStepIno.setControl(composite_5);
		expanditemStepIno.setHeight(expanditemStepIno.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_5.setLayout(new GridLayout(2, false));

		stepInfoLabel = new CLabel(composite_5, SWT.NONE);
		stepInfoLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		stepInfoLabel.setText("New Label");

		stepInfoImage = new Label(composite_5, SWT.NONE);
		stepInfoImage.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/sample.png"));
		GridData gd_stepInfoImage = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_stepInfoImage.widthHint = 88;
		stepInfoImage.setLayoutData(gd_stepInfoImage);

		expanditemTestObject = new ExpandItem(expandBar, SWT.NONE);
		expanditemTestObject.setExpanded(true);
		expanditemTestObject.setText("Test Object");
		expanditemTestObject.setHeight(150);
		Composite composite_6 = new Composite(expandBar, SWT.NONE);
		expanditemTestObject.setControl(composite_6);
		expanditemTestObject.setHeight(expanditemTestObject.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
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

		expenditemInputData = new ExpandItem(expandBar, SWT.NONE);
		expenditemInputData.setExpanded(true);
		expenditemInputData.setText("Input Data");
		expenditemInputData.setHeight(150);

		Composite composite_7 = new Composite(expandBar, SWT.NONE);
		expenditemInputData.setControl(composite_7);
		expenditemInputData.setHeight(expenditemInputData.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));

		InputDataTable = new StepDetailsInputData(composite_7, SWT.BORDER | SWT.FULL_SELECTION, this);
		InputDataTable.setHeaderVisible(true);
		InputDataTable.setLinesVisible(true);

		addStepTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		addStepTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/addstep.png"));
		addStepTabItem.setText("Add Step");

		SashForm sashForm_3 = new SashForm(testCaseArgumentsTabFolder, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		addStepTabItem.setControl(sashForm_3);

		Composite composite_1 = new Composite(sashForm_3, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_10 = new Composite(sashForm_3, SWT.BORDER);
		composite_10.setLayout(new GridLayout(1, false));

		Composite composite_15 = new Composite(composite_10, SWT.NONE);
		composite_15.setLayout(new GridLayout(1, false));
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_15.setBounds(0, 0, 64, 64);

		ToolBar toolBar = new ToolBar(composite_15, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		keywordButton = new ToolItem(toolBar, SWT.NONE);
		keywordButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/keword.png"));
		keywordButton.setText("Keyword");

		ToolItem seperator9 = new ToolItem(toolBar, SWT.SEPARATOR);

		functionLibraryButton = new ToolItem(toolBar, SWT.NONE);
		functionLibraryButton
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/functionlibrary.png"));
		functionLibraryButton.setText("Function Library");

		ToolItem seperator10 = new ToolItem(toolBar, SWT.SEPARATOR);

		serviceRepoButton = new ToolItem(toolBar, SWT.NONE);
		serviceRepoButton
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/servicerepo.png"));
		serviceRepoButton.setText("Service Repositpry");

		ToolItem toolItem_10 = new ToolItem(toolBar, SWT.SEPARATOR);

		codedFunLibraryButton = new ToolItem(toolBar, SWT.NONE);
		codedFunLibraryButton.setImage(
				ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/coded_functionlibrary.png"));
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

		allDataTreeView = new Tree(composite_10, SWT.BORDER);
		allDataTreeView.setLinesVisible(true);
		allDataTreeView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_3.setWeights(new int[] { 0, 1 });

		TabItem testObjectTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/object.png"));
		testObjectTabItem.setText("Test Object");

		Composite composite_2 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		testObjectTable = new TestObjectTable(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION, this);
		testObjectTable.setLinesVisible(true);
		testObjectTable.setHeaderVisible(true);
		testObjectTable.setLinesVisible(true);
		testObjectTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		testObjectTree = new TestObjectTree(sashForm_1, SWT.BORDER, this);
		testObjectTree.setLinesVisible(true);
		testObjectTree.setHeaderVisible(true);
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

		inputDataTable = new InputDataTable(composite_13, SWT.BORDER | SWT.FULL_SELECTION, this);
		inputDataTable.setHeaderVisible(true);
		inputDataTable.setLinesVisible(true);

		Composite composite_14 = new Composite(sashForm_2, SWT.NONE);
		composite_14.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder_1 = new TabFolder(composite_14, SWT.NONE);

		TabItem dataOutputTabItem = new TabItem(tabFolder_1, SWT.NONE);
		dataOutputTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/dataout.png"));
		dataOutputTabItem.setText("Data Output");

		dataOutputTable = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		dataOutputTabItem.setControl(dataOutputTable);
		dataOutputTable.setHeaderVisible(true);
		dataOutputTable.setLinesVisible(true);

		TabItem globalVariablesTabItem = new TabItem(tabFolder_1, SWT.NONE);
		globalVariablesTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/global_variable.png"));
		globalVariablesTabItem.setText("Global Variable");

		globalVariableTable = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		globalVariablesTabItem.setControl(globalVariableTable);
		globalVariableTable.setHeaderVisible(true);
		globalVariableTable.setLinesVisible(true);

		TabItem autoDataGenTabItem = new TabItem(tabFolder_1, SWT.NONE);
		autoDataGenTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/autodata.png"));
		autoDataGenTabItem.setText("Auto Data Generation");

		autoDataGenTable = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		autoDataGenTabItem.setControl(autoDataGenTable);
		autoDataGenTable.setHeaderVisible(true);

		sashForm_2.setWeights(new int[] { 1, 1 });

		TabItem outputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/outputdata.png"));
		outputDataTabItem.setText("Output Data");

		Composite composite_4 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setControl(composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		outputDataTable = new OutputDataTable(composite_4, SWT.BORDER | SWT.FULL_SELECTION, this);
		outputDataTable.setHeaderVisible(true);
		outputDataTable.setLinesVisible(true);

		TableCursor cursor = new TableCursor(flowStepTable, 0);
		testCaseSashForm.setWeights(new int[] { 402, 222 });

		TabItem tbtmNewItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		tbtmNewItem.setText("Source Code");

		Composite composite_121 = new Composite(mainTestCaseTabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_121);
		composite_121.setLayout(new FillLayout(SWT.HORIZONTAL));

		StyledText styledText = new StyledText(composite_121, SWT.BORDER);
		styledText.setMouseNavigatorEnabled(true);
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.deselectAll();
				flowStepTable.setSelection(new TableItem[] { cursor.getRow() });
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

		flowStepTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = flowStepTable.getSelectedFlowStep();
				setSelectedFlowStep(flowStep);
				if (flowStep != null) {
					if (flowStep.getKeyword() != null) {
						outputDataTable.setKeyword(flowStep.getKeyword());
						inputDataTable.setKeyWordInputArgs(flowStep.getKeyword().getKeywordInputArguments());
					} else {
						outputDataTable.setKeyword(null);
						inputDataTable.setKeyWordInputArgs(null);
					}
					if (flowStep.getFunctionLibraryComponent() != null) {
						inputDataTable.setComponentInputArgs(
								flowStep.getFunctionLibraryComponent().getComponentInputArgument());
					} else {
						inputDataTable.setComponentInputArgs(null);
					}
					inputDataTable.setFlowInputArgs(flowStep.getFlowInputArgs());
					inputDataTable.renderInputTable();

					outputDataTable.setFlowOutputArgs(flowStep.getFlowOutputArgs());

					testObjectTable.setOrobject(flowStep.getOrObject());
					testObjectTable.renderORObjectTable();

					toggleDeleteButton(true);
					if (flowStepTable.getPrevFlowStep() == null) {
						toggleMoveupButton(false);
					} else {
						toggleMoveupButton(true);
					}

					if (flowStepTable.getNextFlowStep() == null) {
						toggleMovedownButton(false);
					} else {
						toggleMovedownButton(true);
					}
				} else {
					toggleDeleteButton(false);
					toggleMoveupButton(false);
					toggleMovedownButton(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		try {
			flowStepTable.renderFlowSteps();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addButtonListeners();
	}

	public void toggleAddButton(boolean status) {
		itemAdd.setEnabled(status);
	}

	public void toggleRecordButton(boolean status) {
		itemRecord.setEnabled(status);
	}

	public void toggleRunnowButton(boolean status) {
		itemRunnow.setEnabled(status);
	}

	public void toggleMoveupButton(boolean status) {
		itemMoveup.setEnabled(status);
	}

	public void toggleMovedownButton(boolean status) {
		itemMovedown.setEnabled(status);
	}

	public void toggleRefreshButton(boolean status) {
		itemRefresh.setEnabled(status);
	}

	public void toggleSaveButton(boolean status) {
		itemSave.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		itemDelete.setEnabled(status);
	}

	private void addButtonListeners() {
		itemAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemRecord.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemRunnow.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemMoveup.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.moveStepUp(flowStepTable.getSelectedFlowStep(), flowStepTable.getPrevFlowStep());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemMovedown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.moveStepDown(flowStepTable.getSelectedFlowStep(), flowStepTable.getNextFlowStep());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemDelete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					flowStepTable.deleteStep(flowStepTable.getSelectedFlowStep());
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemSave.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new FlowConstruct().saveAllFlowSteps(flowStepTable.getFlowStepsData());
				try {
					flowStepTable.renderFlowSteps();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				toggleSaveButton(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemRefresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					flowStepTable.renderFlowSteps();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public FlowStep getSelectedFlowStep() {
		return selectedFlowStep;
	}

	public void setSelectedFlowStep(FlowStep selectedFlowStep) {
		this.selectedFlowStep = selectedFlowStep;
	}
}
