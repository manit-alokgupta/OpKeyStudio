package opkeystudio.featurecore.ide.ui.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.tools.Diagnostic.Kind;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.operation.IRunnableWithProgress;
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
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.CodeCompletionProvider;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.EditorTools;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaAutoCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaCodeEditor;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory.CodedFunctionBottomFactoryUI;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.DRTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.FLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.ORTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.SuiteTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.TCTranspiler;

public class CodedFunctionView extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private JavaCodeEditor editor;
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

	public CodedFunctionView(Composite parent, int style, TestCaseView parentTestCaseView, boolean editable) {
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

	public CodedFunctionView(Composite parent, int style, TestSuiteView parentTestCaseView, boolean editable) {
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

	public CodedFunctionView(Composite parent, int style, ObjectRepositoryView parentObjectRepositoryView,
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

	public CodedFunctionView(Composite parent, int style, DataRepositoryView parentDataRepositoryView,
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

	public CodedFunctionView(Composite parent, int style) {
		super(parent, SWT.BORDER);
		initArtifact();
		setLayout(new GridLayout(1, false));
		initCFUI();
		renderCFLCode();
		bottomFactoryUi.getCFLInputTable().renderCFLInputParameters();
		bottomFactoryUi.getCFLOutputTable().renderCFLOutputParameters();
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

	private void initCFUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		runButton = new ToolItem(toolBar, SWT.NONE);
		runButton.setText("Run");
		runButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RUN_ICON));
		runButton.setToolTipText("Run");

		saveButton = new ToolItem(toolBar, SWT.NONE);
		saveButton.setText("Save");
		saveButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_TOOL_ICON));
		saveButton.setToolTipText("Save");

		refreshButton = new ToolItem(toolBar, SWT.NONE);
		refreshButton.setText("Refresh");
		refreshButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		refreshButton.setToolTipText("Refresh");

		editor = new JavaCodeEditor(this, this, true);
		editor.setArtifact(getArtifact());
		toggleSaveButton(false);

		editor.convertOpKeyVariablesToCode();
		if (isEmbeddedInsideTestCaseView() == false && isEmbeddedInsideDataRepositoryView() == false
				&& isEmbeddedInsideObjectRepositoryView() == false) {
			bottomFactoryUi = new CodedFunctionBottomFactoryUI(this, SWT.NONE, this);
			bottomFactoryUi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			bottomFactoryUi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		}

		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (saveButton.getEnabled() == true) {
					saveCFL(true);
				}
				List<CompileError> errors = editor.compileAndCheck();
				List<CompileError> filteredErrors = new EditorTools(getInstance()).filterErrors(errors, Kind.ERROR);
				if (filteredErrors.size() > 0) {
					new MessageDialogs().openErrorDialog("Coded FL Compilation Error",
							"Unable to Execute Coded FL has compilation error");
					return;
				}

				MessageDialogs msd = new MessageDialogs();
				msd.openProgressDialogOnBackgroundThread(getParent().getShell(), "Please Wait Execution is on Progress...", true,
						new IRunnableWithProgress() {

							@Override
							public void run(IProgressMonitor monitor)
									throws InvocationTargetException, InterruptedException {
								monitor.setTaskName("Please Wait Execution is on Progress...");
								EditorTools tools = new EditorTools(getInstance());
								tools.executeCodedFl(getCodedFLClassPath(),
										opkeystudio.core.utils.Utilities.getInstance().getPluginName());
								Display.getDefault().asyncExec(new Runnable() {

									@Override
									public void run() {
										toggleRunButton(false);
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e1) {

											e1.printStackTrace();
										}
										while (true) {
											ByteArrayOutputStream errorOutPut = tools.getStandardErrorOutput();
											ByteArrayOutputStream standardOutPut = tools.getStandardOutput();
											String consoleOutPut = standardOutPut.toString() + System.lineSeparator()
													+ errorOutPut.toString();
											getCodedFunctionBottomFactoryUi().getConsoleLogTextView()
													.setText(consoleOutPut);
											boolean executionCompleted = tools.isExecutionCompleted();
											if (executionCompleted) {
												break;
											}
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {

												e.printStackTrace();
											}
										}
										toggleRunButton(true);
									}
								});
							}
						});
				msd.closeProgressDialog();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAllCFL();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (saveButton.getEnabled() == true) {
					saveCFL(true);
				}
				editor.convertOpKeyVariablesToCode();
				refreshIntellisense(true);
				renderCFLCode();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
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

		editor = new JavaCodeEditor(this, this, false);
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
				// TODO Auto-generated method stub

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

		editor = new JavaCodeEditor(this, this, false);
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
				// TODO Auto-generated method stub

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

		editor = new JavaCodeEditor(this, this, false);
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
		editor = new JavaCodeEditor(this, this, false);
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
		Artifact artifact = getParentTestCaseView().getArtifact();
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			new FLTranspiler().transpile(artifact);
		} else if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
			new TCTranspiler().transpile(artifact);
		}
		initTestCaseCode();
	}

	public void refreshTSCode() {
		Artifact artifact = getParentTestSuiteView().getArtifact();
		new SuiteTranspiler().transpile(artifact);
		initTestSuiteCode();
	}

	public void refreshORCode() {
		Artifact artifact = getParentObjectRepositoryView().getArtifact();
		new ORTranspiler().transpile(artifact);
		initObjectRepositoryCode();
	}

	public void refreshDRCode() {
		Artifact artifact = getParentDataRepositoryView().getArtifact();
		new DRTranspiler().transpile(artifact);
		initDataRepositoryCode();
	}

	public void refreshIntellisense(boolean reinit) {
		new EditorTools(this).initIntellisense(reinit);
	}

	public CodedFunctionView getInstance() {
		return this;
	}

	public void saveAllCFL() {
		if (saveButton.getEnabled() == false) {
			return;
		}
		List<CFLInputParameter> inputParameters=bottomFactoryUi.getCFLInputTable().getCflInputParameters();
		List<CFLOutputParameter> outPutParameters=bottomFactoryUi.getCFLOutputTable().getCflOutputParameters();
		new CodedFunctionApi().saveAllCFLInputParams(inputParameters);
		new CodedFunctionApi().saveAllCFLOutputParams(outPutParameters);
		JavaAutoCompletion completion = editor.getAutoCompletion();
		completion.setCompletionProvider(CodeCompletionProvider.getInstance(this).getCompletionProvider());
		completion.install(editor);
		saveCFL(false);
	}

	public JavaCodeEditor getJavaEditor() {
		return this.editor;
	}

	private void saveCFL(boolean showconfirmation) {
		if (showconfirmation) {
			boolean status = new MessageDialogs().openConfirmDialog("OpKey",
					"Do you want to save " + getArtifact().getName() + "?");
			if (!status) {
				return;
			}
		}
		List<CompileError> errors = editor.compileAndCheck();
		List<CompileError> filteredErrors = new EditorTools(
				getCodedFunctionBottomFactoryUi().getParentCodedFunctionView()).filterErrors(errors, Kind.ERROR);

		if (filteredErrors.size() > 0) {
			toggleSaveButton(false);
			return;
		}

		String code = editor.getText();

		JavaClassSource classSource = Roaster.parse(JavaClassSource.class, code);
		MethodSource<JavaClassSource> methodSource = classSource.getMethod("run");
		if (methodSource != null) {
			if (methodSource.getBody() != null) {
				editor.getCflCode().setUsercode(methodSource.getBody());
				editor.getCflCode().setAdded(true);
				editor.getCflCode().setModified(true);
				new CodedFunctionApi().saveCFLCode(getArtifact(), editor.getCflCode());
				toggleSaveButton(false);
				renderCFLCode();
			}
		}
		toggleSaveButton(false);
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

	public void renderCFLCode() {
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
			editor.setJavaCode(code);
			editor.setCflCode(cflcode);
			CodedFunctionArtifact cfa = new CodedFunctionArtifact();
			cfa.setCflCode(editor.getCflCode());
			cfa.setCflInputParameters(inputParams);
			cfa.setCflOutputParameters(outputParams);
			editor.setCodedFunctionArtifact(cfa);
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
			editor.setJavaCode(code);
			editor.setCflCode(cflcode);
			CodedFunctionArtifact cfa = new CodedFunctionArtifact();
			cfa.setCflCode(editor.getCflCode());
			cfa.setCflInputParameters(inputParams);
			cfa.setCflOutputParameters(outputParams);
			editor.setCodedFunctionArtifact(cfa);
		}
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
}
