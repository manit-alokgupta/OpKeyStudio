package opkeystudio.featurecore.ide.ui.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

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
import org.eclipse.wb.swt.SWTResourceManager;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.ArtifactCodeEditor;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory.CodedFunctionBottomFactoryUI;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.GenericEditorIntellisense;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.featurecore.ide.ui.ui.superview.events.ArtifactPersistListener;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.CFLTranspiler;
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

	private CodedFunctionView parentCodedFunctionView;
	private CodedFunctionArtifact codedFunctionArtifact;
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
		addGenericListner();
		displayCodeViewFileData();
		checkClassIsRunnable();
	}

	public ArtifactCodeView(Composite parent, int style, boolean isGenericEditor, boolean iscfleditor) {
		super(parent, style);
		this.setGenericEditor(isGenericEditor);
		setLayout(new GridLayout(1, false));
		initArtifact();
		initCFLEditorUI();
		initCodedFunctionArtifact();
		initCFLCode();
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

	private void addGenericListner() {
		this.addOpKeyGlobalEventListener(new ArtifactPersistListener() {

			@Override
			public void handleGlobalEvent() {
				handleRefreshOnSave();
			}
		});
	}

	private void initCodedFunctionArtifact() {
		CodedFunctionArtifact cartifact = FlowApi.getInstance().getCodedFunctionArtifact(getArtifact().getId()).get(0);
		List<CFLCode> cflcodes = new CodedFunctionApi().getCodedFLCodeData(getArtifact());
		List<CFLInputParameter> inputParams = GlobalLoader.getInstance().getCFLInputParameters(getArtifact());
		List<CFLOutputParameter> outputParams = GlobalLoader.getInstance().getCFLOutputParameters(getArtifact());
		if (cflcodes.size() > 0) {
			CFLCode cflcode = cflcodes.get(0);
			String imports = "";
			if (cflcode.getImportpackages() != null) {
				imports = cflcode.getImportpackages();
			}
			String code = new CodedFunctionApi().getCodedFLCodeWithBody(getArtifact().getVariableName(),
					cflcode.getUsercode(), cflcode.getPrivateuserfunctions(), imports, inputParams, outputParams);
			cartifact.setCflCode(cflcode);
			cartifact.setCflInputParameters(inputParams);
			cartifact.setCflOutputParameters(outputParams);
		}
		if (cflcodes.size() == 0) {
			CFLCode cflcode = new CFLCode();
			cflcode.setAdded(true);
			cflcode.setCf_id(getArtifact().getId());
			cflcode.setUsercode("");
			cflcode.setLanguage("JAVA");
			cflcode.setPluginid("2626b33a-a06c-408c-8f69-f8f1490a49bb");
			String code = new CodedFunctionApi().getCodedFLCodeWithBody(getArtifact().getVariableName(),
					cflcode.getUsercode(), cflcode.getPrivateuserfunctions(), "", inputParams, outputParams);
			cartifact.setCflCode(cflcode);
			cartifact.setCflInputParameters(inputParams);
			cartifact.setCflOutputParameters(outputParams);
		}

		setCodedFunctionArtifact(cartifact);
	}

	private void initCodeViewFile() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		File codeViewFile = (File) mpart.getTransientData().get("opkeystudio.codeFile");
		setCodeViewFile(codeViewFile);
	}

	private void checkClassIsRunnable() {
		File file = getCodeViewFile();
		String code = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().readTextFile(file);
		JavaClassSource classSource = (JavaClassSource) Roaster.parse(code);
		List<MethodSource<JavaClassSource>> methods = classSource.getMethods();
		for (MethodSource<JavaClassSource> method : methods) {
			if (method != null) {
				System.out.println("Method Name " + method.getName());
				if (method.isPublic()) {
					if (method.isStatic()) {
						runButton.setEnabled(true);
						return;
					}
				}
			}
		}
		runButton.setEnabled(false);
	}

	private void displayCodeViewFileData() {
		File file = getCodeViewFile();
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().readTextFile(file);
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initTestCaseCode() {
		Artifact artifact = getParentTestCaseView().getCurrentArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initCFLCode() {
		Artifact artifact = getArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		setCodeViewFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initTestSuiteCode() {
		Artifact artifact = getParentTestSuiteView().getCurrentArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initObjectRepositoryCode() {
		Artifact artifact = getParentObjectRepositoryView().getCurrentArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + ".java";
		String codeData = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.readTextFile(new File(codeFilePath));
		getJavaEditor().setArtifactJavaCode(codeData);
	}

	private void initDataRepositoryCode() {
		Artifact artifact = getParentDataRepositoryView().getCurrentArtifact();
		String codeFilePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectTranspiledArtifactsFolder() + File.separator + artifact.getPackagePath() + File.separator
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
		runButton.setEnabled(false);
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

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					System.out.println("Saving");
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							saveGenericCodeEditorFile();
							saveButton.setEnabled(false);
						}
					});
					return;
				}
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						saveButton.setEnabled(true);
					}
				});
			}
		});
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openGenericEditorExecutionWizard();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saveGenericCodeEditorFile();
				checkClassIsRunnable();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handleRefreshOnSave();
				displayCodeViewFileData();
				checkClassIsRunnable();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private void initCFLEditorUI() {
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

		bottomFactoryUi = new CodedFunctionBottomFactoryUI(this, SWT.NONE, this);
		bottomFactoryUi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		bottomFactoryUi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		bottomFactoryUi.getCFLInputTable().renderCFLInputParameters();
		bottomFactoryUi.getCFLOutputTable().renderCFLOutputParameters();

		editor.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					System.out.println("Saving");
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							saveCFLCode();
							saveButton.setEnabled(false);
						}
					});
					return;
				}
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						saveButton.setEnabled(true);
					}
				});
			}
		});
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openCFLEditorExecutionWizard();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saveCFLCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSaveOnRefreshCFL();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private void saveCFLCode() {
		List<CFLInputParameter> inputParams = bottomFactoryUi.getCFLInputTable().getCflInputParameters();
		List<CFLOutputParameter> outputParams = bottomFactoryUi.getCFLOutputTable().getCflOutputParameters();
		new CodedFunctionApi().saveAllCFLInputParams(inputParams);
		new CodedFunctionApi().saveAllCFLOutputParams(outputParams);
		String code = editor.getText();
		try {
			JavaClassSource classSource = (JavaClassSource) Roaster.parse(code);
			List<MethodSource<JavaClassSource>> methods = classSource.getMethods();
			for (MethodSource<JavaClassSource> method : methods) {
				if (method.getName().equals("run")) {
					if (method.getBody() != null) {
						CFLCode cflCode = getCodedFunctionArtifact().getCflCode();
						cflCode.setUsercode(method.getBody());
						new CodedFunctionApi().saveCFLCode(getArtifact(), cflCode);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		new CFLTranspiler().transpile(getArtifact());

		initCFLCode();
		toggleSaveButton(false);
	}

	private void handleSaveOnRefreshCFL() {
		if (saveButton.getEnabled() == true) {
			boolean status = new MessageDialogs().openConfirmDialog("OpKey",
					String.format("Do you want to save '%s'?", getArtifact().getName()));
			if (status) {
				saveCFLCode();
			}
		}
	}

	private void saveGenericCodeEditorFile() {
		String code = getJavaEditor().getText();
		File file = getCodeViewFile();
		opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(file, code);
		GenericEditorIntellisense.getInstance().addOpKeyTranspiledClassInformation();
		saveButton.setEnabled(false);
	}

	private void handleRefreshOnSave() {
		if (saveButton.getEnabled() == true) {
			boolean status = new MessageDialogs().openConfirmDialog("OpKey",
					String.format("Do you want to save '%s'?", getCodeViewFile().getName()));
			if (status == true) {
				saveGenericCodeEditorFile();
			}
		}
	}

	public void openGenericEditorExecutionWizard() {
		ExecutionWizardDialog executionWizard = new ExecutionWizardDialog(getShell(), this, true);
		executionWizard.open();
	}

	public void openCFLEditorExecutionWizard() {
		ExecutionWizardDialog executionWizard = new ExecutionWizardDialog(getShell(), this, true, true);
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

		editor = new ArtifactCodeEditor(this, this, false);
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
		Artifact artifact = getParentTestCaseView().getCurrentArtifact();
		if (artifact == null) {
			return;
		}
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			new FLTranspiler().transpile(artifact);
		} else if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
			new TCTranspiler().transpile(artifact);
			new ArtifactTranspiler().transpileAllFl();
		}
		initTestCaseCode();
	}

	public void refreshTSCode() {
		Artifact artifact = getParentTestSuiteView().getCurrentArtifact();
		if (artifact == null) {
			return;
		}
		new SuiteTranspiler().transpile(artifact);
		initTestSuiteCode();
	}

	public void refreshORCode() {
		Artifact artifact = getParentObjectRepositoryView().getCurrentArtifact();
		if (artifact == null) {
			return;
		}
		new ORTranspiler().transpile(artifact);
		initObjectRepositoryCode();
	}

	public void refreshDRCode() {
		Artifact artifact = getParentDataRepositoryView().getCurrentArtifact();
		if (artifact == null) {
			return;
		}
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

	public CodedFunctionView getParentCodedFunctionView() {
		return parentCodedFunctionView;
	}

	public void setParentCodedFunctionView(CodedFunctionView parentCodedFunctionView) {
		this.parentCodedFunctionView = parentCodedFunctionView;
	}

	public CodedFunctionArtifact getCodedFunctionArtifact() {
		return codedFunctionArtifact;
	}

	public void setCodedFunctionArtifact(CodedFunctionArtifact codedFunctionArtifact) {
		this.codedFunctionArtifact = codedFunctionArtifact;
	}
}
