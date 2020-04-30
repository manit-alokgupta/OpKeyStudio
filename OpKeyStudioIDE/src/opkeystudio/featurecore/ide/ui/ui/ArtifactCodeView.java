package opkeystudio.featurecore.ide.ui.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.ArtifactCodeEditor;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory.CodedFunctionBottomFactoryUI;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.DRTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.FLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.ORTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.SuiteTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.TCTranspiler;

public class ArtifactCodeView extends SuperComposite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private ArtifactCodeEditor editor;
	private ToolItem runButton;
	private ToolItem saveButton;
	private ToolItem refreshButton;
	private CodedFunctionBottomFactoryUI bottomFactoryUi;
	private String codedFLClassPath;
	private String artifactOpkeyDataLibraryPath;

	private String artifactAssociatedLibraryPath;
	private Artifact artifact;
	private TestCaseView parentTestCaseView;
	private ObjectRepositoryView parentObjectRepositoryView;
	private DataRepositoryView parentDataRepositoryView;
	private TestSuiteView parentTestSuiteView;
	private boolean embeddedInsideTestCaseView = false;
	private boolean embeddedInsideObjectRepositoryView = false;
	private boolean embeddedInsideDataRepositoryView = false;
	private boolean embeddedInsideTestSuiteView = false;
	private boolean genericEditor = false;

	private File codeViewFile;

	public ArtifactCodeView(Composite parent, int style, boolean isGenericEditor) {
		super(parent, style);
		this.setGenericEditor(isGenericEditor);
		setLayout(new GridLayout(1, false));
		initGenericEditorUI();
		initCodeViewFile();
		displayCodeViewFileData();
	}

	public ArtifactCodeView(Composite parent, int style, TestCaseView parentTestCaseView, boolean editable) {
		super(parent, SWT.BORDER);
		setParentTestCaseView(parentTestCaseView);
		setEmbeddedInsideTestCaseView(true);
		initArtifact();
		Utilities.getInstance().setPluginName("Web");
		setLayout(new GridLayout(1, false));
		initTCFLUI();
		initTestCaseCode();
		getJavaEditor().setEditable(editable);
	}

	public ArtifactCodeView(Composite parent, int style, TestSuiteView parentTestCaseView, boolean editable) {
		super(parent, SWT.BORDER);
		setParentTestSuiteView(parentTestCaseView);
		setEmbeddedInsideTestSuiteView(true);
		initArtifact();
		Utilities.getInstance().setPluginName("Web");
		setLayout(new GridLayout(1, false));
		initTSUI();
		initTestSuiteCode();
		getJavaEditor().setEditable(editable);
	}

	public ArtifactCodeView(Composite parent, int style, ObjectRepositoryView parentObjectRepositoryView,
			boolean editable) {
		super(parent, SWT.BORDER);
		setParentObjectRepositoryView(parentObjectRepositoryView);
		setEmbeddedInsideObjectRepositoryView(true);
		initArtifact();
		setLayout(new GridLayout(1, false));
		initORUI();
		initObjectRepositoryCode();
		getJavaEditor().setEditable(editable);
	}

	public ArtifactCodeView(Composite parent, int style, DataRepositoryView parentDataRepositoryView,
			boolean editable) {
		super(parent, SWT.BORDER);
		setParentDataRepositoryView(parentDataRepositoryView);
		setEmbeddedInsideDataRepositoryView(true);
		initArtifact();
		setLayout(new GridLayout(1, false));
		initDRUI();
		initDataRepositoryCode();
		getJavaEditor().setEditable(editable);
	}

	private void initCodeViewFile() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		File codeViewFile = (File) mpart.getTransientData().get("opkeystudio.codeFile");
		setCodeViewFile(codeViewFile);
	}

	private void displayCodeViewFileData() {
		File file = getCodeViewFile();
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().readTextFile(file);
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initTestCaseCode() {
		Artifact artifact = getParentTestCaseView().getArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initTestSuiteCode() {
		Artifact artifact = getParentTestSuiteView().getArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initObjectRepositoryCode() {
		Artifact artifact = getParentObjectRepositoryView().getArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initDataRepositoryCode() {
		Artifact artifact = getParentDataRepositoryView().getArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initGenericEditorUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		runButton = new ToolItem(toolBar, SWT.NONE);
		runButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RUN_ICON));
		runButton.setToolTipText("Run");
		new ToolItem(toolBar, SWT.SEPARATOR);
		saveButton = new ToolItem(toolBar, SWT.NONE);
		saveButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_ICON));
		saveButton.setToolTipText("Save");
		saveButton.setEnabled(false);
		new ToolItem(toolBar, SWT.SEPARATOR);
		refreshButton = new ToolItem(toolBar, SWT.NONE);
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		refreshButton.setToolTipText("Refresh Source Code");

		editor = new ArtifactCodeEditor(this, this, true, true);
		editor.setArtifact(getArtifact());

		editor.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						saveButton.setEnabled(true);
					}
				});
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openExecutionWizard();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String code = getJavaEditor().getText();
				File file = getCodeViewFile();
				opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(file, code);
				saveButton.setEnabled(false);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				displayCodeViewFileData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	public void openExecutionWizard() {
		ExecutionWizardDialog executionWizard = new ExecutionWizardDialog(getShell(), this);
		executionWizard.open();
	}

	private void initTCFLUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		runButton = new ToolItem(toolBar, SWT.NONE);
		runButton.setText("Run");
		runButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RUN_ICON));
		runButton.setToolTipText("Run");

		refreshButton = new ToolItem(toolBar, SWT.NONE);
		refreshButton.setText("Refresh Code");
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		refreshButton.setToolTipText("Refresh TC/FL Code");

		editor = new ArtifactCodeEditor(this, this, true);
		editor.setArtifact(getArtifact());
		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshTCFLCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isEmbeddedInsideTestCaseView()) {
					getParentTestCaseView().openExecutionWizard();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initTSUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		runButton = new ToolItem(toolBar, SWT.NONE);
		runButton.setText("Run");
		runButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RUN_ICON));
		runButton.setToolTipText("Run");

		refreshButton = new ToolItem(toolBar, SWT.NONE);
		refreshButton.setText("Refresh Code");
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		refreshButton.setToolTipText("Refresh Suite Code");

		editor = new ArtifactCodeEditor(this, this, false);
		editor.setArtifact(getArtifact());
		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshTSCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isEmbeddedInsideTestSuiteView()) {
					getParentTestSuiteView().openExecutionWizard();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initORUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		refreshButton = new ToolItem(toolBar, SWT.NONE);
		refreshButton.setText("Refresh Code");
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		refreshButton.setToolTipText("Refresh Object Repository Code");

		editor = new ArtifactCodeEditor(this, this, false);
		editor.setArtifact(getArtifact());
		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshORCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private void initDRUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		refreshButton = new ToolItem(toolBar, SWT.NONE);
		refreshButton.setText("Refresh Code");
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		refreshButton.setToolTipText("Refresh Data Repository Code");
		editor = new ArtifactCodeEditor(this, this, false);
		editor.setArtifact(getArtifact());
		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshDRCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	public void refreshTCFLCode() {
		getParentTestCaseView().initArtifact();
		Artifact artifact = getParentTestCaseView().getArtifact();
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			new FLTranspiler().transpile(artifact);
		} else if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
			new TCTranspiler().transpile(artifact);
		}
		initTestCaseCode();
	}

	public void refreshTSCode() {
		getParentTestSuiteView().initArtifact();
		Artifact artifact = getParentTestSuiteView().getArtifact();
		new SuiteTranspiler().transpile(artifact);
		initTestSuiteCode();
	}

	public void refreshORCode() {
		getParentObjectRepositoryView().initArtifact();
		Artifact artifact = getParentObjectRepositoryView().getArtifact();
		new ORTranspiler().transpile(artifact);
		initObjectRepositoryCode();
	}

	public void refreshDRCode() {
		getParentDataRepositoryView().initArtifact();
		Artifact artifact = getParentDataRepositoryView().getArtifact();
		new DRTranspiler().transpile(artifact);
		initDataRepositoryCode();
	}

	public ArtifactCodeView getInstance() {
		return this;
	}

	public ArtifactCodeEditor getJavaEditor() {
		return this.editor;
	}

	public void toggleRunButton(boolean status) {
		this.runButton.setEnabled(status);
	}

	public void toggleSaveButton(boolean status) {
		this.saveButton.setEnabled(status);
	}

	public void toggleRefreshButton(boolean status) {
		this.refreshButton.setEnabled(status);
	}

	private void initArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		this.artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
	}

	public Artifact getArtifact() {
		return this.artifact;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public String getCodedFLClassPath() {
		return codedFLClassPath;
	}

	public void setCodedFLClassPath(String codedFLClassPath) {
		this.codedFLClassPath = codedFLClassPath;
	}

	public CodedFunctionBottomFactoryUI getCodedFunctionBottomFactoryUi() {
		return this.bottomFactoryUi;
	}

	public String getArtifactOpkeyDataLibraryPath() {
		return artifactOpkeyDataLibraryPath;
	}

	public void setArtifactOpkeyDataLibraryPath(String artifactOpkeyDataLibraryPath) {
		this.artifactOpkeyDataLibraryPath = artifactOpkeyDataLibraryPath;
	}

	public String getArtifactAssociatedLibraryPath() {
		return artifactAssociatedLibraryPath;
	}

	public void setArtifactAssociatedLibraryPath(String artifactAssociatedLibraryPath) {
		this.artifactAssociatedLibraryPath = artifactAssociatedLibraryPath;
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public boolean isEmbeddedInsideTestCaseView() {
		return embeddedInsideTestCaseView;
	}

	public void setEmbeddedInsideTestCaseView(boolean embeddedInsideTestCaseView) {
		this.embeddedInsideTestCaseView = embeddedInsideTestCaseView;
	}

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;
	}

	public DataRepositoryView getParentDataRepositoryView() {
		return parentDataRepositoryView;
	}

	public void setParentDataRepositoryView(DataRepositoryView parentDataRepositoryView) {
		this.parentDataRepositoryView = parentDataRepositoryView;
	}

	public boolean isEmbeddedInsideObjectRepositoryView() {
		return embeddedInsideObjectRepositoryView;
	}

	public void setEmbeddedInsideObjectRepositoryView(boolean embeddedInsideObjectRepositoryView) {
		this.embeddedInsideObjectRepositoryView = embeddedInsideObjectRepositoryView;
	}

	public boolean isEmbeddedInsideDataRepositoryView() {
		return embeddedInsideDataRepositoryView;
	}

	public void setEmbeddedInsideDataRepositoryView(boolean embeddedInsideDataRepositoryView) {
		this.embeddedInsideDataRepositoryView = embeddedInsideDataRepositoryView;
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

	public boolean isEmbeddedInsideTestSuiteView() {
		return embeddedInsideTestSuiteView;
	}

	public void setEmbeddedInsideTestSuiteView(boolean embeddedInsideTestSuiteView) {
		this.embeddedInsideTestSuiteView = embeddedInsideTestSuiteView;
	}

	public boolean isGenericEditor() {
		return genericEditor;
	}

	public void setGenericEditor(boolean genericEditor) {
		this.genericEditor = genericEditor;
	}

	public void setCodeViewFile(File codeViewFile) {
		this.codeViewFile = codeViewFile;
	}

	public File getCodeViewFile() {
		return this.codeViewFile;
	}
}
