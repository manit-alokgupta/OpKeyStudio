package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
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
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.ComponentArgumentInputTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.GenericTree;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.GenericTree.TREETYPE;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.InputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable.TABLE_TYPE;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTable;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowConstruct;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryConstruct;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class TestCaseView extends Composite {
	private FlowStepTable flowStepTable;
	private OutputDataTable outputDataTable;
	private OutputDataTable outputVariableTable;
	private TestObjectTable testObjectTable;
	private InputDataTable inputDataTable;
	private ObjectRepositoryTree testObjectTree;
	private ComponentArgumentInputTable componentArgumentInputTable;
	private GenericTree drTree;
	private GlobalVariableTable globalVariableTable;
	private Text searchBox;
	private ToolItem itemRun;
	private ToolItem itemMoveup;
	private ToolItem itemAdd;
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
	private Label stepInfoImage;
	private StyledText stepInfoLabel;
	private FlowStep selectedFlowStep;

	@SuppressWarnings("unused")
	private String code;
	private BottomFactoryFLUi bottomFactory;
	@SuppressWarnings("unused")
	private FlowStep flowStep;
	@SuppressWarnings("unused")
	private Display display;
	@SuppressWarnings("unused")
	private ArtifactTreeItem artifactTreeItem;
	private TabFolder datasTabHolder;
	private Artifact artifact;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private ArtifactCodeView codedFunctionView;

	public TestCaseView(Composite parent, int style) {
		super(parent, style);
		initArtifact();
		initTestCaseUI();
		toggleMovedownButton(false);
		toggleMoveupButton(false);
		toggleDeleteButton(false);
		toggleAddButton(true);

	}

	private void initArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		this.setArtifact(artifact);
	}

	@SuppressWarnings("unused")
	public void initTestCaseUI() {
		setLayout(new FillLayout(SWT.HORIZONTAL));
		display = getParent().getDisplay();
		TabFolder mainTestCaseTabFolder = new TabFolder(this, SWT.BORDER | SWT.BOTTOM);
		mainTestCaseTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		TabItem testCaseTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		if (getArtifact().getFile_type_enum() == MODULETYPE.Component) {
			testCaseTabItem.setText("Function Library");
			testCaseTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));
		} else {
			testCaseTabItem.setText("TestCase");
			testCaseTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));
		}
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

		itemRun = new ToolItem(toolBar_1, SWT.NONE);
		itemRun.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RUN_ICON));
		itemRun.setToolTipText("Run");

		new ToolItem(toolBar_1, SWT.SEPARATOR);
		itemAdd = new ToolItem(toolBar_1, SWT.NONE);
		itemAdd.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_NEW_ICON));
		itemAdd.setToolTipText("Add Test Step");

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemMoveup = new ToolItem(toolBar_1, SWT.NONE);
		itemMoveup.setEnabled(false);
		itemMoveup.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_UP_ICON));
		itemMoveup.setToolTipText("Move Step Up");

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemMovedown = new ToolItem(toolBar_1, SWT.NONE);
		itemMovedown.setEnabled(false);
		itemMovedown.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_DOWN_ICON));
		itemMovedown.setToolTipText("Move Step Down");

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemDelete = new ToolItem(toolBar_1, SWT.NONE);
		itemDelete.setEnabled(false);
		itemDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		itemDelete.setToolTipText(" Delete Test Step");

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemSave = new ToolItem(toolBar_1, SWT.NONE);
		itemSave.setEnabled(false);
		itemSave.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_ICON));
		itemSave.setToolTipText("Save");

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		itemRefresh = new ToolItem(toolBar_1, SWT.NONE);
		itemRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));
		itemRefresh.setToolTipText("Refresh");

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
		testCaseSashForm.setWeights(new int[] { 7, 3 });
		stepDetailsTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setToolTipText("Step Details");
		stepDetailsTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.STEP_DETAILS_ICON));

		Composite composite = new Composite(testCaseArgumentsTabFolder, SWT.BORDER);
		stepDetailsTabItem.setControl(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));

		expanditemStepIno = new ExpandItem(expandBar, SWT.NONE);
		expanditemStepIno.setExpanded(true);
		expanditemStepIno.setText("Step Information");
		expanditemStepIno.setHeight(500);

		Composite composite_5 = new Composite(expandBar, SWT.NONE);
		expanditemStepIno.setControl(composite_5);
		expanditemStepIno.setHeight(expanditemStepIno.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_5.setLayout(new GridLayout(2, false));
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		stepInfoLabel = new StyledText(composite_5, SWT.V_SCROLL);
		stepInfoLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		stepInfoLabel.setText("");
		stepInfoLabel.setEditable(false);

		stepInfoImage = new Label(composite_5, SWT.NONE);
		stepInfoImage.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/sample.png"));
		GridData gd_stepInfoImage = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_stepInfoImage.widthHint = 88;
		stepInfoImage.setLayoutData(gd_stepInfoImage);

		addStepTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		addStepTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_NEW_ICON));
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
		keywordButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TEXT_KEYWORD_ICON));
		keywordButton.setText("Keyword");
		keywordButton.setToolTipText("Keyword");
		ToolItem seperator9 = new ToolItem(toolBar, SWT.SEPARATOR);

		functionLibraryButton = new ToolItem(toolBar, SWT.NONE);
		functionLibraryButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));
		functionLibraryButton.setText("Function Library");
		functionLibraryButton.setToolTipText("Function Library");

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
				if (allDataTreeView.isKeywordTree()) {
					allDataTreeView.initKeywords(searchBox.getText());
				} else if (allDataTreeView.isFlTree()) {
					allDataTreeView.initFunctionLibraries(searchBox.getText());
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Button clearButton = new Button(composite_12, SWT.NONE);
		clearButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clearButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		clearButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ERASER_ICON));
		clearButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		clearButton.setToolTipText("Clear");
		clearButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String textToSearch = searchBox.getText();
				searchBox.setText("");
				if (allDataTreeView.isKeywordTree()) {
					allDataTreeView.initKeywords("");
				} else if (allDataTreeView.isFlTree()) {
					allDataTreeView.initFunctionLibraries("");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		allDataTreeView = new GenericTree(composite_10, SWT.BORDER, this);
		allDataTreeView.setLinesVisible(true);
		allDataTreeView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_3.setWeights(new int[] { 0, 1 });

		TabItem testObjectTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		testObjectTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TESTOBJECT_ICON));
		testObjectTabItem.setToolTipText("Test Object");
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

		testObjectTree = new ObjectRepositoryTree(sashForm_1, SWT.BORDER, this);
		testObjectTree.setLinesVisible(true);
		testObjectTree.setHeaderVisible(true);
		sashForm_1.setWeights(new int[] { 1, 1 });

		TabItem inputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		inputDataTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.INPUTDATA_ICON));
		inputDataTabItem.setToolTipText("Input Data");
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

		datasTabHolder = new TabFolder(composite_14, SWT.NONE);

		if (getArtifact().getFile_type_enum() != MODULETYPE.Component) {
			TabItem drVariableTabItem = new TabItem(datasTabHolder, SWT.NONE);
			drVariableTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
			drVariableTabItem.setText("Global DR");
			drVariableTabItem.setToolTipText("Global DR");
			drTree = new GenericTree(datasTabHolder, SWT.BORDER | SWT.FULL_SELECTION, this,
					TREETYPE.DATAREPOSITORYTREE);
			drVariableTabItem.setControl(drTree);
			drTree.setHeaderVisible(true);
			drTree.setLinesVisible(true);
		} else {
			TabItem componentArgInputTable = new TabItem(datasTabHolder, SWT.NONE);
			componentArgInputTable
					.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
			componentArgInputTable.setText("Data Input");
			componentArgInputTable.setToolTipText("Data Input");
			componentArgumentInputTable = new ComponentArgumentInputTable(datasTabHolder,
					SWT.BORDER | SWT.FULL_SELECTION, this);
			componentArgInputTable.setControl(componentArgumentInputTable);
			componentArgumentInputTable.setHeaderVisible(true);
			componentArgumentInputTable.setLinesVisible(true);
		}

		TabItem dataOutPutTabItem = new TabItem(datasTabHolder, SWT.NONE);
		dataOutPutTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
		dataOutPutTabItem.setText("Data Output");
		dataOutPutTabItem.setToolTipText("Data Output");
		outputVariableTable = new OutputDataTable(datasTabHolder, SWT.BORDER | SWT.FULL_SELECTION, this,
				TABLE_TYPE.SELECTIONTABLE);
		dataOutPutTabItem.setControl(outputVariableTable);
		outputVariableTable.setHeaderVisible(true);
		outputVariableTable.setLinesVisible(true);

		TabItem globalVariablesTabItem = new TabItem(datasTabHolder, SWT.NONE);
		globalVariablesTabItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.GLOBAL_VARIABLE_ICON));
		globalVariablesTabItem.setText("Global Variable");
		globalVariablesTabItem.setToolTipText("Global Variable");
		globalVariableTable = new GlobalVariableTable(datasTabHolder, SWT.BORDER | SWT.FULL_SELECTION, this);
		globalVariablesTabItem.setControl(globalVariableTable);
		globalVariableTable.setHeaderVisible(true);
		globalVariableTable.setLinesVisible(true);

		sashForm_2.setWeights(new int[] { 1, 1 });

		TabItem outputDataTabItem = new TabItem(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OUTPUTDATA_ICON));
		outputDataTabItem.setToolTipText("Output Data");

		Composite composite_4 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		outputDataTabItem.setControl(composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		outputDataTable = new OutputDataTable(composite_4, SWT.BORDER | SWT.FULL_SELECTION, this,
				TABLE_TYPE.INPUTTABLE);
		outputDataTable.setHeaderVisible(true);
		outputDataTable.setLinesVisible(true);

		TableCursor cursor = new TableCursor(flowStepTable, 0);

		if (getArtifact().getFile_type_enum() == MODULETYPE.Component) {
			bottomFactory = new BottomFactoryFLUi(testCaseStepsHolder, SWT.NONE, this);
			bottomFactory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			bottomFactory.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		}

		// Source Code Editor Will Be added here
		// sourceCodeTabItem.setControl(sourceCodeEditor);

		TabItem sourceCodeTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeTabItem.setText("SourceCode");
		sourceCodeTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		Composite sourceCodeHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sourceCodeTabItem.setControl(sourceCodeHolder);
		sourceCodeHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		ArtifactCodeView codedFunctionView = new ArtifactCodeView(sourceCodeHolder, SWT.NONE, this, true);
		setCodedFunctionView(codedFunctionView);

		cursor.setMenu(flowStepTable.getMenu());
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
					toggleAddButton(true);
					testCaseArgumentsTabFolder.setSelection(2);
				} else if (selectedColumn == 3) {
					toggleAddButton(true);
					testCaseArgumentsTabFolder.setSelection(3);
				} else if (selectedColumn == 4) {
					toggleAddButton(true);
					testCaseArgumentsTabFolder.setSelection(4);
				} else {
					toggleAddButton(true);
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

		testObjectTree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = flowStepTable.getSelectedFlowStep();
				if (flowStep != null) {
					ORObject orobject = testObjectTree.getSelectedORObject();
					if (orobject == null) {
						return;
					}
					boolean keywordHasORObject = false;
					if (flowStep.getKeyword() != null) {
						if (flowStep.getKeyword().isKeywordContainsORObject()) {
							keywordHasORObject = true;
						}
					}
					if (flowStep.getOrObject().size() > 0 || keywordHasORObject) {
						List<ORObject> orobjects = new ArrayList<>();
						orobjects.add(orobject);
						flowStep.setOrObject(orobjects);
						flowStep.setModified(true);
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
		flowStepTable.renderFlowSteps();

		itemRun.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openExecutionWizard();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

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

	private void openExecutionWizard() {
		ExecutionWizardDialog executionWizard = new ExecutionWizardDialog(getShell(), this);
		executionWizard.open();
	}

	private void populateFlowStepsData(FlowStep flowStep) throws JsonParseException, JsonMappingException, IOException {
		if (flowStep != null) {
			setSelectedFlowStep(flowStep);
			String stepDetails = "";
			if (flowStep.getComment() != null) {
				stepDetails = flowStep.getComment();
			} else if (flowStep.getComments() != null) {
				stepDetails = flowStep.getComments();
			}
			getStepDetailLabel().setText(stepDetails);
			inputDataTable.renderInputTable(flowStep);
			outputDataTable.renderOutPutTableFlowStep(flowStep);
			outputVariableTable.renderOutPutTableAll();
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

		return this.artifact;
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
		testObjectTable.renderORObjectTable(flowStep);
		if (renderTreeAlso) {
			testObjectTree.removeAll();
			testObjectTree.fetchAndRenderORTree();
			if (flowStep.getKeyword() != null) {
				if (!flowStep.getKeyword().isKeywordContainsORObject()) {
					testObjectTree.setEnabled(false);
					return;
				}
			}
			testObjectTree.setEnabled(true);
		}
	}

	public void toggleAddButton(boolean status) {
		flowStepTable.toggleAddStepMenuItem(status);
		itemAdd.setEnabled(status);
	}

	public void toggleMoveupButton(boolean status) {
		flowStepTable.toggleMoveUpMenuItem(status);
		itemMoveup.setEnabled(status);
	}

	public void toggleMovedownButton(boolean status) {
		flowStepTable.toggleMoveDownMenuItem(status);
		itemMovedown.setEnabled(status);
	}

	public void toggleRefreshButton(boolean status) {
		itemRefresh.setEnabled(status);
	}

	public void toggleSaveButton(boolean status) {
		itemSave.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		flowStepTable.toggleDeleteMenuItem(status);
		itemDelete.setEnabled(status);
	}

	private void addButtonListeners() {
		itemAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Add Button pressed");
				flowStepTable.addStep();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemMoveup.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.moveStepUp();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemMovedown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.moveStepDown();
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
				try {
					toggleSaveButton(true);
					flowStepTable.deleteStep();
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
				getCodedFunctionView().refreshTCFLCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		itemRefresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (itemSave.isEnabled()) {
					toggleSaveButton(false);
					boolean status = new MessageDialogs().openConfirmDialog("OpKey", "Do you want to Save changes?");
					if (status) {
						new FlowConstruct().saveAllFlowSteps(getArtifact(), flowStepTable.getFlowStepsData());
					}
				}
				flowStepTable.renderFlowSteps();
				getCodedFunctionView().refreshTCFLCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		keywordButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				allDataTreeView.initKeywords("");

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		functionLibraryButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				allDataTreeView.initFunctionLibraries("");
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
				new FlowApiUtilities().setFlowInputData(getArtifact(), flowInputArgument, globalVar.getGv_id(),
						DataSource.ValueFromGlobalVariable);
				toggleSaveButton(true);
				try {
					getInputDataTable().renderInputTable(getInputDataTable().getFlowStep());
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

	}

	public void saveAll() {
		if (getArtifact().getFile_type_enum() == MODULETYPE.Component) {
			List<ComponentInputArgument> componentInputArgs = bottomFactory.getInputTable().getComponentInputData();
			List<ComponentOutputArgument> componentOutputArgs = bottomFactory.getOutputTable().getComponentOutputData();
			new FunctionLibraryConstruct().saveComponentInputArguments(componentInputArgs);
			new FunctionLibraryConstruct().saveComponentOutputArguments(componentOutputArgs);
		}
		new FlowConstruct().saveAllFlowSteps(getArtifact(), flowStepTable.getFlowStepsData());
		flowStepTable.renderFlowSteps();
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

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}

	public StyledText getStepDetailLabel() {
		return this.stepInfoLabel;
	}

	public ArtifactCodeView getCodedFunctionView() {
		return codedFunctionView;
	}

	public void setCodedFunctionView(ArtifactCodeView codedFunctionView) {
		this.codedFunctionView = codedFunctionView;
	}
}
