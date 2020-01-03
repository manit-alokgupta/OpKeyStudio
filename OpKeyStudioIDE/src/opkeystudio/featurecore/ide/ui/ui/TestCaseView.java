package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
//import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.featurecore.ide.ui.customcontrol.GlobalVariableTable;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestCaseUi;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.InputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.GenericTree;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.GenericTree.TREETYPE;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.StepDetailsInputData;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTable;
import opkeystudio.featurecore.ide.ui.ui.sourcecodeeditor.SourceCodeEditor;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowConstruct;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class TestCaseView extends Composite {
	private FlowStepTable flowStepTable;
	private OutputDataTable outputDataTable;
	private TestObjectTable testObjectTable;
	private InputDataTable inputDataTable;
	private StepDetailsInputData InputDataTable;
	private ObjectRepositoryTree testObjectTree;
	private Table mappedTable;
	private Table propertyTable;
	private GenericTree dataOutputTable;
	private GlobalVariableTable globalVariableTable;
	private Table autoDataGenTable;
	private Text searchBox;
	@SuppressWarnings("unused")
	private ToolItem seperator1;
	@SuppressWarnings("unused")
	private ToolItem seperator2;
	@SuppressWarnings("unused")
	private ToolItem seperator3;
	@SuppressWarnings("unused")
	private ToolItem seperator4;
	@SuppressWarnings("unused")
	private ToolItem seperator5;
	@SuppressWarnings("unused")
	private ToolItem seperator6;
	@SuppressWarnings("unused")
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
	private GenericTree allDataTreeView;
//	private Tree allDataTreeView;
	private Label stepInfoImage;
	private CLabel stepInfoLabel;
	private FlowStep selectedFlowStep;

	@SuppressWarnings("unused")
	private String code;
	private MenuItem testObjectMenu1;
	private MenuItem testObjectMenu2;
	private MenuItem testObjectMenu3;
	private MenuItem testObjectMenu4;
	private Keyword keyWord;
	private BottomFactoryTestCaseUi bottomFactory;
	@SuppressWarnings("unused")
	private FlowStep flowStep;
	@SuppressWarnings("unused")
	private Display display;
	@SuppressWarnings("unused")
	private ArtifactTreeItem artifactTreeItem;
	private TabFolder datasTabHolder;
	private SourceCodeEditor sourceCodeEditor;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestCaseView(Composite parent, int style) {
		super(parent, style);

		TestCaseUI();
		toggleMovedownButton(false);
		toggleMoveupButton(false);
		toggleDeleteButton(false);
		toggleAddButton(true);

	}

	@SuppressWarnings("unused")
	public void TestCaseUI() {
		setLayout(new FillLayout(SWT.HORIZONTAL));
		display = getParent().getDisplay();
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

		itemRecord = new ToolItem(toolBar_1, SWT.NONE);
		itemRecord.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/record_icon.png"));
		itemRecord.setText("Record");
		itemRecord.setToolTipText("Record");

		seperator1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemAdd = new ToolItem(toolBar_1, SWT.NONE);
		itemAdd.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		itemAdd.setText("Add");
		itemAdd.setToolTipText("Add Test Step");

		seperator2 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemRunnow = new ToolItem(toolBar_1, SWT.NONE);
		itemRunnow.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/run_icon.png"));
		itemRunnow.setText("Run Now");
		itemRunnow.setToolTipText("Run Now");

		seperator3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemMoveup = new ToolItem(toolBar_1, SWT.NONE);
		itemMoveup.setEnabled(false);
		itemMoveup.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/moveup_icon.png"));
		itemMoveup.setText("Move up");
		itemMoveup.setToolTipText("Move Step Up");

		seperator4 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemMovedown = new ToolItem(toolBar_1, SWT.NONE);
		itemMovedown.setEnabled(false);
		itemMovedown.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/movedown_icon.png"));
		itemMovedown.setText("Move Down");
		itemMovedown.setToolTipText("Move Step Down");

		seperator5 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemDelete = new ToolItem(toolBar_1, SWT.NONE);
		itemDelete.setEnabled(false);
		itemDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		itemDelete.setText("Delete");
		itemDelete.setToolTipText(" Delete Test Step");

		seperator6 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemSave = new ToolItem(toolBar_1, SWT.NONE);
		itemSave.setEnabled(false);
		itemSave.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/save.png"));
		itemSave.setText("Save");
		itemSave.setToolTipText("Save");

		seperator7 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemRefresh = new ToolItem(toolBar_1, SWT.NONE);
		itemRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		itemRefresh.setText("Refresh");
		itemRefresh.setToolTipText("Refresh");

		flowStepTable = new FlowStepTable(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION, this);
//		flowStepTable = new Table(testCaseStepsHolder, SWT.BORDER | SWT.FULL_SELECTION);
		flowStepTable.setLinesVisible(true);
		flowStepTable.setHeaderVisible(true);
		flowStepTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite testCaseArgumentsHolder = new Composite(testCaseSashForm, SWT.NONE);
		testCaseArgumentsHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		testCaseArgumentsHolder.setLayout(new GridLayout(1, false));

		testCaseArgumentsTabFolder = new TabFolder(testCaseArgumentsHolder, SWT.NONE);
		testCaseArgumentsTabFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		testCaseArgumentsTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		testCaseSashForm.setWeights(new int[] { 4, 3 });
		stepDetailsTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setText("Step Details");
		stepDetailsTabItem.setToolTipText("Step Details");
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
//		InputDataTable = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		InputDataTable.setHeaderVisible(true);
		InputDataTable.setLinesVisible(true);

		addStepTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		addStepTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/addstep.png"));
		addStepTabItem.setText("Add Step");
		addStepTabItem.setToolTipText("Add Step");
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

		ToolBar toolBar = new ToolBar(composite_15, SWT.FLAT | SWT.RIGHT | SWT.WRAP);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 64, 64);

		keywordButton = new ToolItem(toolBar, SWT.NONE);
		keywordButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/keword.png"));
		keywordButton.setText("Keyword");
		keywordButton.setToolTipText("Keyword");
		ToolItem seperator9 = new ToolItem(toolBar, SWT.SEPARATOR);

		functionLibraryButton = new ToolItem(toolBar, SWT.NONE);
		functionLibraryButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/fl.png"));
		functionLibraryButton.setText("Function Library");
		functionLibraryButton.setToolTipText("Function Library");
		ToolItem seperator10 = new ToolItem(toolBar, SWT.SEPARATOR);

		serviceRepoButton = new ToolItem(toolBar, SWT.NONE);
		serviceRepoButton
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/servicerepo.png"));
		serviceRepoButton.setText("Service Repository");
		serviceRepoButton.setToolTipText("Service Repository");
		ToolItem toolItem_10 = new ToolItem(toolBar, SWT.SEPARATOR);

		codedFunLibraryButton = new ToolItem(toolBar, SWT.NONE);
		codedFunLibraryButton.setImage(
				ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/coded_functionlibrary.png"));
		codedFunLibraryButton.setText("Coded Function Library");
		codedFunLibraryButton.setToolTipText("Coded Function Library");
		Composite composite_12 = new Composite(composite_10, SWT.NONE);
		composite_12.setLayout(new GridLayout(2, false));
		composite_12.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		searchBox = new Text(composite_12, SWT.BORDER);
		searchBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		searchBox.setToolTipText("Search");
		searchBox.setMessage("Search Keyword");
		searchBox.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Button clearButton = new Button(composite_12, SWT.NONE);
		clearButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clearButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		clearButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/search.png"));
		clearButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		clearButton.setToolTipText("Clear");
		clearButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String textToSearch = searchBox.getText();
				searchBox.setText("");
//				allDataTreeView.filterDataInTree(textToSearch);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		allDataTreeView = new GenericTree(composite_10, SWT.BORDER, this);
//		allDataTreeView = new Tree(composite_10, SWT.BORDER);
		allDataTreeView.setLinesVisible(true);
		allDataTreeView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_3.setWeights(new int[] { 0, 1 });

		TabItem testObjectTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/object.png"));
		testObjectTabItem.setText("Test Object");
		testObjectTabItem.setToolTipText("Test Object");
		Composite composite_2 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		sashForm_1.setOrientation(SWT.VERTICAL);

		testObjectTable = new TestObjectTable(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION, this);
//		testObjectTable = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		testObjectTable.setLinesVisible(true);
		testObjectTable.setHeaderVisible(true);
		testObjectTable.setLinesVisible(true);
		testObjectTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Menu menu = new Menu(testObjectTable);
		testObjectTable.setMenu(menu);

		testObjectMenu1 = new MenuItem(menu, SWT.NONE);
		testObjectMenu1.setText("Open in New Tab");
		testObjectMenu1.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/open.png"));

		testObjectMenu2 = new MenuItem(menu, SWT.NONE);
		testObjectMenu2.setText("Menu New Item2");

		testObjectMenu3 = new MenuItem(menu, SWT.NONE);
		testObjectMenu3.setText("Menu New Item3");

		testObjectMenu4 = new MenuItem(menu, SWT.NONE);
		testObjectMenu4.setText("Menu New Item4");

		testObjectTree = new ObjectRepositoryTree(sashForm_1, SWT.BORDER, this);
//		testObjectTree = new Tree(sashForm_1, SWT.BORDER);
		testObjectTree.setLinesVisible(true);
		testObjectTree.setHeaderVisible(true);
		sashForm_1.setWeights(new int[] { 1, 1 });

		TabItem inputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/inputdata.png"));
		inputDataTabItem.setText("Input Data");
		inputDataTabItem.setToolTipText("Input Data");
		Composite composite_3 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setControl(composite_3);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(composite_3, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);

		Composite composite_13 = new Composite(sashForm_2, SWT.NONE);
		composite_13.setLayout(new FillLayout(SWT.HORIZONTAL));

		inputDataTable = new InputDataTable(composite_13, SWT.BORDER | SWT.FULL_SELECTION, this);
//		inputDataTable = new Table(composite_13, SWT.BORDER | SWT.FULL_SELECTION);
		inputDataTable.setHeaderVisible(true);
		inputDataTable.setLinesVisible(true);

		Composite composite_14 = new Composite(sashForm_2, SWT.NONE);
		composite_14.setLayout(new FillLayout(SWT.HORIZONTAL));

		datasTabHolder = new TabFolder(composite_14, SWT.NONE);

		TabItem dataOutputTabItem = new TabItem(datasTabHolder, SWT.NONE);
		dataOutputTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/dataout.png"));
		dataOutputTabItem.setText("Data Output");
		dataOutputTabItem.setToolTipText("Data Output");
		dataOutputTable = new GenericTree(datasTabHolder, SWT.BORDER | SWT.FULL_SELECTION, this,
				TREETYPE.DATAREPOSITORYTREE);
		dataOutputTabItem.setControl(dataOutputTable);
		dataOutputTable.setHeaderVisible(true);
		dataOutputTable.setLinesVisible(true);

		TabItem globalVariablesTabItem = new TabItem(datasTabHolder, SWT.NONE);
		globalVariablesTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/global_variable.png"));
		globalVariablesTabItem.setText("Global Variable");
		globalVariablesTabItem.setToolTipText("Global Variable");
		globalVariableTable = new GlobalVariableTable(datasTabHolder, SWT.BORDER | SWT.FULL_SELECTION, this);
		globalVariablesTabItem.setControl(globalVariableTable);
		globalVariableTable.setHeaderVisible(true);
		globalVariableTable.setLinesVisible(true);

		TabItem autoDataGenTabItem = new TabItem(datasTabHolder, SWT.NONE);
		autoDataGenTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/autodata.png"));
		autoDataGenTabItem.setText("Auto Data Generation");
		autoDataGenTabItem.setToolTipText("Auto Data Generation");

		autoDataGenTable = new Table(datasTabHolder, SWT.BORDER | SWT.FULL_SELECTION);
		autoDataGenTabItem.setControl(autoDataGenTable);
		autoDataGenTable.setHeaderVisible(true);

		sashForm_2.setWeights(new int[] { 1, 1 });

		TabItem outputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/outputdata.png"));
		outputDataTabItem.setText("Output Data");
		outputDataTabItem.setToolTipText("Output Data");

		Composite composite_4 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setControl(composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		outputDataTable = new OutputDataTable(composite_4, SWT.BORDER | SWT.FULL_SELECTION, this);
//		outputDataTable = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		outputDataTable.setHeaderVisible(true);
		outputDataTable.setLinesVisible(true);

		TableCursor cursor = new TableCursor(flowStepTable, 0);

		bottomFactory = new BottomFactoryTestCaseUi(testCaseStepsHolder, SWT.NONE);
		bottomFactory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		bottomFactory.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		TabItem sourceCodeTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeTabItem.setText("Source Code");
		sourceCodeTabItem.setToolTipText("Source Code");

		sourceCodeEditor = new SourceCodeEditor(mainTestCaseTabFolder, SWT.NONE, this);
		sourceCodeTabItem.setControl(sourceCodeEditor);
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.deselectAll();
				toggleAddButton(true);
				flowStepTable.setSelection(new TableItem[] { cursor.getRow() });
				int selectedColumn = cursor.getColumn();
				System.out.println("Column " + selectedColumn);
				if (selectedColumn == 1) {
					toggleAddButton(true);
					testCaseArgumentsTabFolder.setSelection(1);
					FlowStep flowStep = flowStepTable.getSelectedFlowStep();
					if (flowStep.getKeyword() != null) {
						allDataTreeView.selectKeyword(flowStep.getKeyword());
					}
				} else if (selectedColumn == 2) {
					toggleAddButton(false);
					testCaseArgumentsTabFolder.setSelection(2);
				} else if (selectedColumn == 3) {
					toggleAddButton(false);
					testCaseArgumentsTabFolder.setSelection(3);
				} else if (selectedColumn == 4) {
					toggleAddButton(false);
					testCaseArgumentsTabFolder.setSelection(4);
				} else {
					toggleAddButton(false);
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
				try {
					populateFlowStepsData(flowStep);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		testObjectMenu1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		testObjectMenu2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		testObjectTree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = flowStepTable.getSelectedFlowStep();
				if (flowStep != null) {
					ORObject orobject = testObjectTree.getSelectedORObject();
					if (orobject == null) {
						return;
					}
					if (flowStep.getOrObject().size() > 0) {
						List<ORObject> orobjects = new ArrayList<>();
						orobjects.add(orobject);
						flowStep.setOrObject(orobjects);
						flowStep.isModified();
						List<FlowInputArgument> finpargs = flowStep.getFlowInputArgs();
						for (FlowInputArgument finparg : finpargs) {
							finparg.setModified(true);
							finparg.setStaticobjectid(orobject.getObject_id());
							break;
						}
						renderTestObjectTable(flowStep, false);
						toggleSaveButton(true);
					}
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

		itemAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addButtonListeners();
		populateInputTabData();
	}

	private void populateFlowStepsData(FlowStep flowStep) throws JsonParseException, JsonMappingException, IOException {
		if (flowStep != null) {
			setSelectedFlowStep(flowStep);
			if (flowStep.getKeyword() != null) {
				outputDataTable.setKeyword(flowStep.getKeyword());
				inputDataTable.setKeyWordInputArgs(flowStep.getKeyword().getKeywordInputArguments());
			} else {
				outputDataTable.setKeyword(null);
				inputDataTable.setKeyWordInputArgs(new ArrayList<>());
			}
			if (flowStep.getFunctionLibraryComponent() != null) {
				inputDataTable
						.setComponentInputArgs(flowStep.getFunctionLibraryComponent().getComponentInputArgument());
			} else {
				inputDataTable.setComponentInputArgs(new ArrayList<>());
			}
			inputDataTable.setFlowInputArgs(flowStep.getFlowInputArgs());
			inputDataTable.renderInputTable();

			outputDataTable.setFlowOutputArgs(flowStep.getFlowOutputArgs());

			renderTestObjectTable(flowStep, true);

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

	public Artifact getArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		return artifact;
	}

	private void populateInputTabData() {
		inputDataTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowInputArgument fia = inputDataTable.getSelectedFlowInputArgument();
				if (fia == null) {
					return;
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void renderTestObjectTable(FlowStep flowStep, boolean renderTreeAlso) {
		testObjectTable.setOrobject(flowStep.getOrObject());
		testObjectTable.renderORObjectTable();
		if (renderTreeAlso) {
			if (flowStep.getOrObject().size() > 0) {
				testObjectTree.fetchAndRenderORTree();
			}
		}
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
				System.out.println("Add Button pressed");
				Artifact artifact = getArtifact();
				FlowStep selectedFlowStep = getFlowStepTable().getSelectedFlowStep();
				FlowStep flowStep = new FlowMaker().getFlowStepDTO(getArtifact(), selectedFlowStep, null,
						artifact.getId(), getFlowStepTable().getFlowStepsData());
				getFlowStepTable().getFlowStepsData().add(flowStep);
				getFlowStepTable().refreshFlowSteps();
				toggleSaveButton(true);
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
				toggleSaveButton(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemMovedown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.moveStepDown(flowStepTable.getSelectedFlowStep(), flowStepTable.getNextFlowStep());
				toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemDelete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean status = new MessageDialogs().openConfirmDialog("OpKey",
						"Do you want to delete '" + flowStepTable.getSelectedFlowStep().getKeyword() + "'?");
				if (!status) {
					return;
				}

				// AbstractNotificationPopup notification = new
				// DeleteNotificationPopup(display);
				// notification.setDelayClose(200L);
				// notification.open();

				try {
					toggleSaveButton(true);
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
				saveAll();
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
					if (itemSave.isEnabled()) {
						toggleSaveButton(false);
						boolean status = new MessageDialogs().openConfirmDialog("OpKey",
								"Do you want to Save changes?");
						if (!status) {
							flowStepTable.renderFlowSteps();
							bottomFactory.refreshBottomFactory();
							return;
						}

						// AbstractNotificationPopup notification = new SaveNotificationPopup(display);
						// notification.setDelayClose(200L);
						// notification.open();

						new FlowConstruct().saveAllFlowSteps(getArtifact(), flowStepTable.getFlowStepsData());
						try {
							flowStepTable.renderFlowSteps();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						toggleSaveButton(false);
					}

					flowStepTable.renderFlowSteps();
					bottomFactory.refreshBottomFactory();

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

		keywordButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				allDataTreeView.initKeywords();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		functionLibraryButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				allDataTreeView.initFunctionLibraries();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		serviceRepoButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				allDataTreeView.initSeriveRepo();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		codedFunLibraryButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				allDataTreeView.initCodedFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		dataOutputTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		globalVariableTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				GlobalVariable globalVar = globalVariableTable.getSelectedGlobalVariable();
				FlowInputArgument flowInputArgument = getInputDataTable().getSelectedFlowInputArgument();
				if (flowInputArgument == null) {
					return;
				}
				if (getArtifact().getFile_type_enum() == MODULETYPE.Component) {
					flowInputArgument.setArg_datasource(DataSource.ValueFromGlobalVariable);
				} else {
					flowInputArgument.setDatasource(DataSource.ValueFromGlobalVariable);
				}
				flowInputArgument.setGlobalvariable_id(globalVar.getGv_id());
				flowInputArgument.setModified(true);
				toggleSaveButton(true);
				try {
					getInputDataTable().renderInputTable();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		autoDataGenTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void saveAll() {

		// AbstractNotificationPopup notification = new SaveNotificationPopup(display);
		// notification.setDelayClose(200L);
		// notification.open();

		new FlowConstruct().saveAllFlowSteps(getArtifact(), flowStepTable.getFlowStepsData());
		try {
			flowStepTable.renderFlowSteps();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toggleSaveButton(false);
	}

	public FlowStepTable getFlowStepTable() {
		return this.flowStepTable;
	}

	public FlowStep getSelectedFlowStep() {
		return selectedFlowStep;
	}

	public void setSelectedFlowStep(FlowStep selectedFlowStep) {
		this.selectedFlowStep = selectedFlowStep;
	}

	public SourceCodeEditor getSourceCodeEditor() {
		return this.sourceCodeEditor;
	}

	public InputDataTable getInputDataTable() {
		return this.inputDataTable;
	}

	public ToolItem getSaveButton() {
		return this.itemSave;
	}

	public TabFolder getDatasTabHolder() {
		return this.datasTabHolder;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
