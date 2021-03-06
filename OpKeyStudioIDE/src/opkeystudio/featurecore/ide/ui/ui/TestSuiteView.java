package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
//import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.bottomfactory.ui.BottomFactoryTestSuiteUi;
import opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol.SuiteStepTable;
import opkeystudio.featurecore.ide.ui.customcontrol.suitecontrol.SuiteTestCaseTree;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.featurecore.ide.ui.ui.superview.events.ArtifactPersistListener;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite.TestSuiteApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;
import pcloudystudio.core.utils.notification.CustomNotificationUtil.DialogResult;

public class TestSuiteView extends SuperComposite {
	private SuiteTestCaseTree testCaseTree;
	private Text searchTextBox;
	private SuiteStepTable testSuiteTable;
	@SuppressWarnings("unused")
	private TestSuiteStep testSuite;
	private ToolItem runButton;
	private ToolItem deleteSuiteStepButton;
	private ToolItem moveUpButton;
	private ToolItem moveDownButton;
	private ToolItem saveButton;
	private ToolItem refreshButton;
	private ToolItem toolDropDown;
	private ToolBar toolBar_1;
	private Menu dropDownMenu;
	private MenuItem menuDraft;
	private MenuItem menuReview;
	private MenuItem menuApproved;
	private MenuItem menuPublished;
	private ToolItem refreshTestCaseTree;
	private Button searchTestCaseButton;
	private Button addTestCaseButton;
	private BottomFactoryTestSuiteUi bottomFactory;
	@SuppressWarnings("unused")
	private Display display;
	private ArtifactCodeView codedFunctionView;
	private Artifact artifact;
	private MPart currentMpart;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	public TestSuiteView(Composite parent, int style, MPart part) {
		super(parent, style);
		initArtifact();
		initTestSuiteUI();
		toggleSaveButton(false);
		toggleDeleteButton(false);
		toggleMoveUpButton(false);
		toggleMoveDownButton(false);
		addOpKeyGlobalListener();
		currentMpart = part;
	}

	private void addOpKeyGlobalListener() {
		this.addOpKeyGlobalEventListener(new ArtifactPersistListener() {

			@Override
			public void handleGlobalEvent() {
				System.out.println("Test Suite Global Listner Called");
				handleSaveOnRefresh();
			}
		});
	}

	private void initTestSuiteUI() {
		display = getParent().getDisplay();
		setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder mainTestCaseTabFolder = new TabFolder(this, SWT.BORDER | SWT.BOTTOM);
		mainTestCaseTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));

		TabItem testCaseTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		testCaseTabItem.setText("Test Suite");
		testCaseTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SUITE_ICON));
		Composite testCaseHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		testCaseHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		testCaseTabItem.setControl(testCaseHolder);
		testCaseHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(testCaseHolder, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);

		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_1.setLayout(new GridLayout(1, false));

		toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBounds(0, 0, 87, 23);

		runButton = new ToolItem(toolBar_1, SWT.NONE);
		runButton.setToolTipText("Run Now");
		runButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RUN_ICON));
		new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteSuiteStepButton = new ToolItem(toolBar_1, SWT.NONE);
		deleteSuiteStepButton.setToolTipText("Delete Suite Step");
		deleteSuiteStepButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		moveUpButton = new ToolItem(toolBar_1, SWT.NONE);
		moveUpButton.setToolTipText("Move UP");
		moveUpButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_UP_ICON));

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		moveDownButton = new ToolItem(toolBar_1, SWT.NONE);
		moveDownButton.setToolTipText("Move Down");
		moveDownButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_DOWN_ICON));

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		saveButton = new ToolItem(toolBar_1, SWT.NONE);
		saveButton.setToolTipText("Save");
		saveButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_ICON));

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		refreshButton = new ToolItem(toolBar_1, SWT.NONE);
		refreshButton.setToolTipText("Refresh");
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolDropDown = new ToolItem(toolBar_1, SWT.DROP_DOWN);
		toolDropDown.setText("More");

		dropDownMenu = new Menu(toolBar_1);
		toolBar_1.setMenu(dropDownMenu);

		menuDraft = new MenuItem(dropDownMenu, SWT.NONE);
		menuDraft.setText("Draft");

		new MenuItem(dropDownMenu, SWT.SEPARATOR);

		menuReview = new MenuItem(dropDownMenu, SWT.NONE);
		menuReview.setText("Review");

		new MenuItem(dropDownMenu, SWT.SEPARATOR);

		menuApproved = new MenuItem(dropDownMenu, SWT.NONE);
		menuApproved.setText("Approved");

		new MenuItem(dropDownMenu, SWT.SEPARATOR);

		menuPublished = new MenuItem(dropDownMenu, SWT.NONE);
		menuPublished.setText("Published");

		// testSuiteTable = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		testSuiteTable = new SuiteStepTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION, this);
		testSuiteTable.setHeaderVisible(true);
		testSuiteTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		testSuiteTable.setBounds(0, 0, 85, 45);
		testSuiteTable.setLinesVisible(true);

		TableCursor tableCursor = new TableCursor(testSuiteTable, SWT.NONE);
		tableCursor.setMenu(testSuiteTable.getMenu());
		bottomFactory = new BottomFactoryTestSuiteUi(composite_1, SWT.NONE);
		bottomFactory.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Composite composite_2 = new Composite(sashForm, SWT.BORDER);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_2.setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_3.setLayout(new GridLayout(1, false));

		SashForm sashForm_1 = new SashForm(composite_3, SWT.NONE);
		sashForm_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sashForm_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		sashForm_1.setLocation(0, 0);

		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_5.setLayout(new GridLayout(3, false));
		composite_5.setBounds(0, 0, 64, 64);

		addTestCaseButton = new Button(composite_5, SWT.NONE);
		addTestCaseButton.setToolTipText("Add Available Test Cases/Sparkin/Gherkin Features");
		addTestCaseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		addTestCaseButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		addTestCaseButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOVE_LEFT_ICON));
		addTestCaseButton.setEnabled(false);

		searchTextBox = new Text(composite_5, SWT.BORDER);
		searchTextBox.setToolTipText("Available Test Cases/Sparkin/Gherkin Features");
		searchTextBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		searchTextBox.setMessage("Available Test Cases");

		searchTestCaseButton = new Button(composite_5, SWT.NONE);
		searchTestCaseButton.setToolTipText("Search");
		searchTestCaseButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		searchTestCaseButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SEARCH_ICON));
		searchTestCaseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Composite composite_6 = new Composite(sashForm_1, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_6.setOrientation(SWT.RIGHT_TO_LEFT);
		composite_6.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite_6, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		refreshTestCaseTree = new ToolItem(toolBar, SWT.NONE);
		refreshTestCaseTree.setToolTipText("Refresh");
		refreshTestCaseTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		refreshTestCaseTree.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));
		sashForm_1.setWeights(new int[] { 4, 1 });

		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_4.setLayout(new GridLayout(1, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		testCaseTree = new SuiteTestCaseTree(composite_4, SWT.BORDER, this);
		testCaseTree.setLinesVisible(true);
		testCaseTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 2, 1 });

		// Sorce Code will be here

		TabItem sourceCodeTabItem = new TabItem(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeTabItem.setText("Source Code");
		sourceCodeTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		Composite sourceCodeHolder = new Composite(mainTestCaseTabFolder, SWT.NONE);
		sourceCodeHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sourceCodeTabItem.setControl(sourceCodeHolder);
		sourceCodeHolder.setLayout(new FillLayout(SWT.HORIZONTAL));

		ArtifactCodeView codedFunctionView = new ArtifactCodeView(sourceCodeHolder, SWT.NONE, this, false);
		setCodedFunctionView(codedFunctionView);
		toolDropDown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Arrow Pressed");
				Rectangle rect = toolDropDown.getBounds();
				Point pt = new Point(rect.x, rect.y + rect.height);
				pt = toolBar_1.toDisplay(pt);
				dropDownMenu.setLocation(pt.x, pt.y);
				dropDownMenu.setVisible(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		addButtonListeners();
		testSuiteTable.renderAllTestSuites();
	}

	public void openExecutionWizard() {
		ExecutionWizardDialog executionWizard = new ExecutionWizardDialog(getShell(), this);
		executionWizard.open();
	}

	public void populateTestSuiteData(TestSuiteStep testSuite) {
		if (testSuite != null) {
			setSelectedTEstSuite(testSuite);
			if (testSuiteTable.getSelectedTestSuite() == null) {
				toggleDeleteButton(false);
			} else {
				toggleDeleteButton(true);
			}
			if (testSuiteTable.getPrevTestSuite() == null) {
				moveUpButton.setEnabled(false);
			} else {
				moveUpButton.setEnabled(true);
			}
			if (testSuiteTable.getNextTestSuite() == null) {
				moveDownButton.setEnabled(false);
			} else {
				moveDownButton.setEnabled(true);
			}
		} else {
			toggleSaveButton(false);
			toggleDeleteButton(false);
			toggleMoveUpButton(false);
			toggleMoveDownButton(false);
		}

	}

	public void toggleSaveButton(boolean status) {
		getCurrentMpart().setDirty(status);
		saveButton.setEnabled(status);
	}

	public void toggleMoveUpButton(boolean status) {
		testSuiteTable.toggleMoveUpMenuItem(status);
		moveUpButton.setEnabled(status);
	}

	public void toggleMoveDownButton(boolean status) {
		testSuiteTable.toggleMoveDownMenuItem(status);
		moveDownButton.setEnabled(status);
	}

	public void toggleDeleteButton(boolean status) {
		testSuiteTable.toggleDeleteMenuItem(status);
		deleteSuiteStepButton.setEnabled(status);
	}

	private void addButtonListeners() {

		searchTextBox.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				getAddTestCaseButton().setEnabled(false);
				Text text = (Text) e.getSource();
				String searchValue = text.getText();
				if (searchValue.length() >= 1 || searchValue.trim().isEmpty()) {
					testCaseTree.filterArtifactTree(searchValue);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		searchTestCaseButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addTestCaseButton.setEnabled(false);
				String textToSearch = searchTextBox.getText();
				testCaseTree.filterArtifactTree(textToSearch);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshTestCaseTree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				testCaseTree.renderArtifacts(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean status = handleSaveOnRefresh();
				if (status == false) {
					new MessageDialogs().openErrorDialog(testSuiteTable.getShell(), "OpKey", "Unable to Execute. Please save your data first.");
					return;
				}
				List<TestSuiteStep> flowSteps = testSuiteTable.getTestSuiteData();
				if (flowSteps.size() == 0) {
					new MessageDialogs().openErrorDialog(testSuiteTable.getShell(), "OpKey", "Nothing to execute. Please add atleast one TC");
					return;
				}
				boolean shouldrun = false;
				for (TestSuiteStep flowStep : flowSteps) {
					if (flowStep.isShouldrun()) {
						shouldrun = true;
					}
				}
				if (shouldrun == false) {
					new MessageDialogs().openErrorDialog(testSuiteTable.getShell(), "OpKey", "Nothing to execute. Please add atleast one TC");
					return;
				}
				openExecutionWizard();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		deleteSuiteStepButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					testSuiteTable.deleteSuiteStep();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		moveUpButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				testSuiteTable.moveStepUp();
				CustomNotificationUtil.openInformationNotification("OpKey", "Selected TC moved up!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		moveDownButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				testSuiteTable.moveStepDown();
				toggleSaveButton(true);
				CustomNotificationUtil.openInformationNotification("OpKey", "Selected TC moved down!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saveSuiteSteps();
				OpKeyGlobalLoadListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
				CustomNotificationUtil.openInformationNotification("OpKey", "Saved!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSaveOnRefresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private boolean handleSaveOnRefresh() {
		if (saveButton.isEnabled()) {
			Utilities.getInstance().activateMpart(getCurrentMpart());
			DialogResult status = CustomNotificationUtil.openConfirmDialog(testSuiteTable.getShell(), "OpKey", "Do you want to Save changes?");
			System.out.println("Result " + status);
			if (status == DialogResult.Cancel) {
				return false;
			}
			if (status == DialogResult.Yes) {
				new TestSuiteApi().saveAllTestSuite(getArtifact(), testSuiteTable.getTestSuiteData());
			}
		}
		toggleSaveButton(false);
		toggleDeleteButton(false);
		toggleMoveUpButton(false);
		toggleMoveDownButton(false);
		testSuiteTable.renderAllTestSuites();
		bottomFactory.refreshBottomFactory();
		CustomNotificationUtil.openInformationNotification("OpKey", "Refreshed!");
		return true;
	}

	public void saveSuiteSteps() {
		new TestSuiteApi().saveAllTestSuite(getArtifact(), testSuiteTable.getTestSuiteData());
		testSuiteTable.renderAllTestSuites();
		getCodedFunctionView().refreshTSCode();
		saveButton.setEnabled(false);
	}

	private void setSelectedTEstSuite(TestSuiteStep testSuite) {
		this.testSuite = testSuite;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public SuiteTestCaseTree getTestCaseTree() {
		return testCaseTree;
	}

	public void setTestCaseTree(SuiteTestCaseTree testCaseTree) {
		this.testCaseTree = testCaseTree;
	}

	public SuiteStepTable getSuiteStepTable() {
		return this.testSuiteTable;
	}

	private void initArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		this.artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		this.setCurrentMpart(mpart);
	}

	public Artifact getCurrentArtifact() {
		return GlobalLoader.getInstance().getArtifactById(getArtifact().getId());
	}

	public Artifact getArtifact() {
		return this.artifact;
	}

	public Button getAddTestCaseButton() {
		return this.addTestCaseButton;
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
