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
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
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
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTreeItem;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryFLUi;
import opkeystudio.featurecore.ide.ui.customcontrol.globalvariable.GlobalVariableTable;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.ComponentArgumentInputTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.FlowStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.GenericTree;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.GenericTree.TREETYPE;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.InputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.OutputDataTable.TABLE_TYPE;
import opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol.TestObjectTable;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.featurecore.ide.ui.ui.superview.events.ArtifactPersistListener;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowConstruct;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryConstruct;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;

public class TestCaseView extends SuperComposite {
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
	private ToolItem codedFunctionLibraryButton;
	private TabFolder testCaseArgumentsTabFolder;
	private TabItem stepDetailsTabItem;
	private TabItem addStepTabItem;
	private GenericTree allDataTreeView;
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
	private MPart currentMpart;

	public TestCaseView(Composite parent, int style) {
		super(parent, style);
		initArtifact();
		initTestCaseUI();
		toggleMovedownButton(false);
		toggleMoveupButton(false);
		toggleDeleteButton(false);
		toggleAddButton(true);
		addOpKeyGlobalListener();

	}

	private void initArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		this.setArtifact(artifact);
		this.setCurrentMpart(mpart);
	}

	public Artifact getCurrentArtifact() {
		return GlobalLoader.getInstance().getArtifactById(getArtifact().getId());
	}

	private void addOpKeyGlobalListener() {
		this.addOpKeyGlobalEventListener(new ArtifactPersistListener() {

			@Override
			public void handleGlobalEvent() {
				System.out.println("Test Case Global Listner Called");
				handleSaveOnRefresh();
			}
		});
	}

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

		Composite composite_5 = new Composite(testCaseArgumentsTabFolder, SWT.NONE);
		stepDetailsTabItem.setControl(composite_5);
		composite_5.setLayout(new GridLayout(2, false));
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		stepInfoLabel = new StyledText(composite_5, SWT.WRAP);
		stepInfoLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		stepInfoLabel.setText("");
		stepInfoLabel.setEditable(false);

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

		keywordButton = new ToolItem(toolBar, SWT.CHECK);
		keywordButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TEXT_KEYWORD_ICON));
		keywordButton.setText("Keyword");
		keywordButton.setToolTipText("Keyword");
		keywordButton.setSelection(true);
		new ToolItem(toolBar, SWT.SEPARATOR);

		functionLibraryButton = new ToolItem(toolBar, SWT.CHECK);
		functionLibraryButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));
		functionLibraryButton.setText("Function Library");
		functionLibraryButton.setToolTipText("Function Library");

		new ToolItem(toolBar, SWT.SEPARATOR);

		codedFunctionLibraryButton = new ToolItem(toolBar, SWT.CHECK);
		codedFunctionLibraryButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		codedFunctionLibraryButton.setText("Coded Function Library");
		codedFunctionLibraryButton.setToolTipText("Coded Function Library");

		Composite composite_12 = new Composite(composite_10, SWT.NONE);
		composite_12.setLayout(new GridLayout(2, false));
		composite_12.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		searchBox = new Text(composite_12, SWT.BORDER);
		searchBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		searchBox.setToolTipText("Search Keyword");
		searchBox.setMessage("Search Keyword");
		searchBox.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (allDataTreeView.isKeywordTree()) {
					allDataTreeView.initKeywords(searchBox.getText());
				} else if (allDataTreeView.isFlTree()) {
					allDataTreeView.initFunctionLibraries(searchBox.getText());
				} else if (allDataTreeView.isCflTree()) {
					allDataTreeView.initCodedFunctionLibraries(searchBox.getText());
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

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
				searchBox.setText("");
				if (allDataTreeView.isKeywordTree()) {
					allDataTreeView.initKeywords("");
				} else if (allDataTreeView.isFlTree()) {
					allDataTreeView.initFunctionLibraries("");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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

		TabItem sourceCodeTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeTabItem.setText("SourceCode");
		sourceCodeTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		Composite sourceCodeHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sourceCodeTabItem.setControl(sourceCodeHolder);
		sourceCodeHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		ArtifactCodeView codedFunctionView = new ArtifactCodeView(sourceCodeHolder, SWT.NONE, this, false);
		setCodedFunctionView(codedFunctionView);

		cursor.setMenu(flowStepTable.getMenu());
		flowStepTable.setTableCursor(cursor);
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

			}
		});

		flowStepTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FlowStep flowStep = flowStepTable.getSelectedFlowStep();
				try {
					populateFlowStepsData(flowStep);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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
						flowStepTable.refreshFlowSteps();
						toggleSaveButton(true);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemRun.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean status = handleSaveOnRefresh();
				if (status == false) {
					new MessageDialogs().openErrorDialog(flowStepTable.getShell(), "OpKey",
							"Unable to Execute. Please save your data first.");
					return;
				}
				List<FlowStep> flowSteps = flowStepTable.getFlowStepsData();
				if (flowSteps.size() == 0) {
					new MessageDialogs().openErrorDialog(flowStepTable.getShell(), "OpKey",
							"Nothing to execute. Please add atleast one step");
					return;
				}
				boolean shouldrun = false;
				for (FlowStep flowStep : flowSteps) {
					if (flowStep.isShouldrun()) {
						shouldrun = true;
					}
				}
				if (shouldrun == false) {
					new MessageDialogs().openErrorDialog(flowStepTable.getShell(), "OpKey",
							"Nothing to execute. Please add atleast one step");
					return;
				}
				openExecutionWizard();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemAdd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		flowStepTable.renderFlowSteps();
		addButtonListeners();
		populateInputTabData();
		goToTab(1);
	}

	private void goToTab(int tabno) {
		testCaseArgumentsTabFolder.setSelection(tabno);
	}

	public void openExecutionWizard() {
		try {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			ExecutionWizardDialog executionWizard = new ExecutionWizardDialog(getShell(), this);
			executionWizard.open();
		} finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}

	}

	private void populateFlowStepsData(FlowStep flowStep) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Called populateFlowStepsData");
		if (flowStep == null) {
			toggleDeleteButton(false);
			toggleMoveupButton(false);
			toggleMovedownButton(false);
			System.out.println("2 Table Items Count " + flowStepTable.getItemCount());
			if (flowStepTable.getItemCount() == 0) {
				toggleDeleteButton(false);
				toggleMoveupButton(false);
				toggleMovedownButton(false);
			}
			return;
		}

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
			outputVariableTable.renderOutPutTableAll(flowStep);
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
			System.out.println("1 Table Items Count " + flowStepTable.getItemCount());
			if (flowStepTable.getItemCount() == 0) {
				toggleDeleteButton(false);
				toggleMoveupButton(false);
				toggleMovedownButton(false);
			}
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

	public void toggleRunButton(boolean status) {
		itemRun.setEnabled(status);
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
				CustomNotificationUtil.openInformationNotification("OpKey", "New step is added!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemMoveup.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				flowStepTable.moveStepUp();
				CustomNotificationUtil.openInformationNotification("OpKey", "Moved Step Up!");
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
				CustomNotificationUtil.openInformationNotification("OpKey", "Moved Step Down!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemDelete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
					toggleSaveButton(true);
					flowStepTable.deleteStep();
					inputDataTable.clearAllData();
					outputDataTable.clearAllData();
					testObjectTable.clearAllData();
					CustomNotificationUtil.openInformationNotification("OpKey", "Selected Step Deleted!");
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				} finally {
					getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemSave.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAll();
				CustomNotificationUtil.openInformationNotification("OpKey", "Saved!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		itemRefresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSaveOnRefresh();
				CustomNotificationUtil.openInformationNotification("Opkey", "Test Case Saved");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		keywordButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				keywordButton.setSelection(true);
				functionLibraryButton.setSelection(false);
				codedFunctionLibraryButton.setSelection(false);
				searchBox.setMessage("Search Keyword");
				searchBox.setToolTipText("Search Keyword");
				allDataTreeView.initKeywords("");

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		functionLibraryButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				keywordButton.setSelection(false);
				functionLibraryButton.setSelection(true);
				codedFunctionLibraryButton.setSelection(false);
				searchBox.setMessage("Search Function Library");
				searchBox.setToolTipText("Search Function Library");
				allDataTreeView.initFunctionLibraries("");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		codedFunctionLibraryButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				keywordButton.setSelection(false);
				functionLibraryButton.setSelection(false);
				codedFunctionLibraryButton.setSelection(true);
				searchBox.setMessage("Search Coded Function Library");
				searchBox.setToolTipText("Search Coded Function Library");
				allDataTreeView.initCodedFunctionLibraries("");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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
					getInputDataTable().renderInputTable(getSelectedFlowStep());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	private boolean handleSaveOnRefresh() {
		try {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			if (itemSave.isEnabled()) {
				Utilities.getInstance().activateMpart(getCurrentMpart());
				int status = CustomNotificationUtil.openConfirmDialog("OpKey", "Do you want to Save changes?");
				if (status == 0) {
					saveAll();
					toggleSaveButton(false);
					CustomNotificationUtil.openInformationNotification("OpKey", "Refreshed!");
					return true;
				}
				int revertChangesStatus = CustomNotificationUtil.openConfirmDialog("OpKey",
						"Do you want to Revert changes?");
				if (revertChangesStatus == 0) {
					flowStepTable.revertAll();
					flowStepTable.renderFlowSteps();
					getCodedFunctionView().refreshTCFLCode();
					CustomNotificationUtil.openInformationNotification("OpKey", "Changes reverted!");
					return false;
				}
				return false;
			}
			flowStepTable.renderFlowSteps();
			getCodedFunctionView().refreshTCFLCode();
			return true;
		} finally {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}
	}

	public void saveAll() {
		try {

			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			if (getArtifact().getFile_type_enum() == MODULETYPE.Component) {
				List<ComponentInputArgument> componentInputArgs = bottomFactory.getInputTable().getComponentInputData();
				List<ComponentOutputArgument> componentOutputArgs = bottomFactory.getOutputTable()
						.getComponentOutputData();
				boolean isUniqueInputName = isComponentInputArgumentNameAreUnique(componentInputArgs);
				boolean isUniqueOutputName = isComponentOutputArgumentNameAreUnique(componentOutputArgs);
				if (isUniqueInputName == false) {
					toggleSaveButton(false);
					flowStepTable.renderFlowSteps();
					getCodedFunctionView().refreshTCFLCode();
					bottomFactory.getInputTable().renderAllBottomFactoryInputData();
					new MessageDialogs().openErrorDialog("OpKey", "Name of input parameters must be unique");
					return;
				}
				if (isUniqueOutputName == false) {
					toggleSaveButton(false);
					flowStepTable.renderFlowSteps();
					getCodedFunctionView().refreshTCFLCode();
					bottomFactory.getOutputTable().renderAllBottomFactoryOutputData();
					new MessageDialogs().openErrorDialog("OpKey", "Name of output parameters must be unique");
					return;
				}
				new FunctionLibraryConstruct().saveComponentInputArguments(componentInputArgs);
				new FunctionLibraryConstruct().saveComponentOutputArguments(componentOutputArgs);
				bottomFactory.getInputTable().renderAllBottomFactoryInputData();
				bottomFactory.getOutputTable().renderAllBottomFactoryOutputData();
			}
			new FlowConstruct().saveAllFlowSteps(getArtifact(), flowStepTable.getFlowStepsData());
			flowStepTable.renderFlowSteps();
			getCodedFunctionView().refreshTCFLCode();
			OpKeyGlobalLoadListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
			toggleSaveButton(false);
		} finally {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}
	}

	private boolean isComponentInputArgumentNameAreUnique(List<ComponentInputArgument> componentInputArguments) {
		List<String> variableNames = new ArrayList<String>();
		for (ComponentInputArgument ci : componentInputArguments) {
			if (ci.isDeleted()) {
				continue;
			}
			if (variableNames.contains(ci.getName().toLowerCase())) {
				return false;
			}
			variableNames.add(ci.getName().toLowerCase());
		}
		return true;
	}

	private boolean isComponentOutputArgumentNameAreUnique(List<ComponentOutputArgument> componentInputArguments) {
		List<String> variableNames = new ArrayList<String>();
		for (ComponentOutputArgument co : componentInputArguments) {
			if (co.isDeleted()) {
				continue;
			}
			if (variableNames.contains(co.getName().toLowerCase())) {
				return false;
			}
			variableNames.add(co.getName().toLowerCase());
		}
		return true;
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

	public MPart getCurrentMpart() {
		return currentMpart;
	}

	public void setCurrentMpart(MPart currentMpart) {
		this.currentMpart = currentMpart;
	}
}
