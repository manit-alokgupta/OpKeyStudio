package opkeystudio.featurecore.ide.ui.ui;

import java.io.ByteArrayOutputStream;
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
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

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
	private boolean embeddedInsideTestCaseView = false;

	public CodedFunctionView(Composite parent, int style, TestCaseView parentTestCaseView) {
		super(parent, SWT.BORDER);
		setEmbeddedInsideTestCaseView(true);
		initArtifact();
		Utilities.getInstance().setPluginName("Web");
		setLayout(new GridLayout(1, false));
		initCFUI();
		initListeners();
	}

	public CodedFunctionView(Composite parent, int style) {
		super(parent, SWT.BORDER);
		initArtifact();
		Utilities.getInstance().setPluginName("Web");
		setLayout(new GridLayout(1, false));
		initCFUI();
		initListeners();
		renderCFLCode();
		bottomFactoryUi.getCFLInputTable().renderCFLInputParameters();
		bottomFactoryUi.getCFLOutputTable().renderCFLOutputParameters();
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

		editor = new JavaCodeEditor(this, this);
		editor.setArtifact(getArtifact());
		toggleSaveButton(false);

		editor.convertOpKeyVariablesToCode();
		if (isEmbeddedInsideTestCaseView() == false) {
			bottomFactoryUi = new CodedFunctionBottomFactoryUI(this, SWT.NONE, this);
			bottomFactoryUi.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			bottomFactoryUi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		}
	}

	private void initListeners() {

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
				msd.openProgressDialog_2(getParent().getShell(), "Please Wait Execution is on Progress...", true,
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
			String code = new CodedFunctionApi().getCodedFLCodeWithBody(getArtifact().getArtifactVariableName(),
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
			String code = new CodedFunctionApi().getCodedFLCodeWithBody(getArtifact().getArtifactVariableName(),
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
}
