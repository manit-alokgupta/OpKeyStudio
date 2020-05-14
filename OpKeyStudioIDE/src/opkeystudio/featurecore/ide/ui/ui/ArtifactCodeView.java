package opkeystudio.featurecore.ide.ui.ui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.tools.Diagnostic.Kind;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory.CodeEditorBottomFactory;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.bottomfactory.CodedFunctionBottomFactoryUI;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.GenericEditorIntellisense;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.featurecore.ide.ui.ui.superview.events.ArtifactPersistListener;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.compiler.ArtifactCompiler;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.CFLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.DRTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.FLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.ORTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.SuiteTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.TCTranspiler;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;

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
	private CodeEditorBottomFactory codeEditorBottomFactory;
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
	private List<Object> highlightedLines = new ArrayList<>();
	private ToolItem pluginNames;

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
		initCodeViewFile();
		File file = getCodeViewFile();
		String code = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().readTextFile(file);
		Object classObject = Roaster.parse(code);
		if (classObject instanceof JavaClassSource) {
			JavaClassSource classSource = (JavaClassSource) classObject;
			List<MethodSource<JavaClassSource>> methods = classSource.getMethods();
			for (MethodSource<JavaClassSource> method : methods) {
				if (method != null) {
					if (method.getName().equals("main")) {
						if (method.isPublic()) {
							if (method.isStatic()) {
								runButton.setEnabled(true);
								return;
							}
						}
					}
				}
			}
		}
		runButton.setEnabled(false);
	}

	public Artifact getCurrentArtifact() {
		return GlobalLoader.getInstance().getArtifactById(getArtifact().getId());
	}

	private void displayCodeViewFileData() {
		initCodeViewFile();
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
		Artifact artifact = getCurrentArtifact();
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

		codeEditorBottomFactory = new CodeEditorBottomFactory(this, SWT.NONE, this);
		codeEditorBottomFactory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		codeEditorBottomFactory.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

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
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 87, 23);

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
		refreshButton.setToolTipText("Refresh");
		new ToolItem(toolBar, SWT.SEPARATOR);
		ToolItem label = new ToolItem(toolBar, SWT.READ_ONLY);
		label.setSelection(false);
		label.setText("Target Plugin: ");
		pluginNames = new ToolItem(toolBar, SWT.DROP_DOWN);
		pluginNames.setWidth(100);
		pluginNames.setText("Web");
		Menu pluginMenu = new Menu(pluginNames.getDisplay().getActiveShell());

		String pluginDir = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getDefaultPluginsDir();
		File pluginDirObject = new File(pluginDir);

		for (File file : pluginDirObject.listFiles()) {
			if (file.isDirectory()) {
				String pluginXmlFile = file.getAbsolutePath() + File.separator + "plugin.xml";
				if (!new File(pluginXmlFile).exists()) {
					continue;
				}
				MenuItem item = new MenuItem(pluginMenu, 0);
				item.setText(file.getName());
				item.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						System.out.println(">>Item Text " + item.getText());
						pluginNames.setText(item.getText());
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}
				});
			}
		}

		pluginNames.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.ARROW) {
					Rectangle rect = pluginNames.getBounds();
					Point pt = new Point(rect.x, rect.y + rect.height);
					pt = toolBar.toDisplay(pt);
					pluginMenu.setLocation(pt.x, pt.y);
					pluginMenu.setVisible(true);
					return;
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		editor = new ArtifactCodeEditor(this, this, true, true, true);
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
		for (Object highLightedLines : getHighlightedLines()) {
			editor.removeLineHighlight(highLightedLines);
		}
		String code = editor.getText();
		String rootCodeDirpath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectArtifactCodesFolder();
		File cflArtifact = getCodeViewFile();
		opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(cflArtifact, code);
		String cflArtifactPath = cflArtifact.getAbsolutePath();
		List<CompileError> compileErrors = new ArtifactCompiler().compileCFLArtifact(rootCodeDirpath, cflArtifactPath,
				"Web");

		compileErrors = new CompilerUtilities().filterErrors(compileErrors, Kind.ERROR);
		bottomFactoryUi.getCompilationResultTable().renderCompilingError(new ArrayList<CompileError>());
		if (compileErrors.size() > 0) {
			for (CompileError error : compileErrors) {
				Object lineHighlightObject;
				try {
					editor.setHighlightCurrentLine(false);
					lineHighlightObject = editor.addLineHighlight(((int) error.getLineNumber()) - 1, Color.RED);
					addHighlightedLines(lineHighlightObject);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			toggleSaveButton(false);
			bottomFactoryUi.getCompilationResultTable().renderCompilingError(compileErrors);
			return;
		}
		editor.setHighlightCurrentLine(true);
		List<CFLInputParameter> inputParams = bottomFactoryUi.getCFLInputTable().getCflInputParameters();
		List<CFLOutputParameter> outputParams = bottomFactoryUi.getCFLOutputTable().getCflOutputParameters();
		new CodedFunctionApi().saveAllCFLInputParams(inputParams);
		new CodedFunctionApi().saveAllCFLOutputParams(outputParams);
		try {
			JavaClassSource classSource = (JavaClassSource) Roaster.parse(code);
			List<MethodSource<JavaClassSource>> methods = classSource.getMethods();
			String privateFunctions = "";
			for (MethodSource<JavaClassSource> method : methods) {
				if (!method.getName().equals("run") && !method.getName().equals("executeDefault")) {
					if (method.getBody() != null) {
						privateFunctions += method.getBody();
					}
				}
			}
			for (MethodSource<JavaClassSource> method : methods) {
				if (method.getName().equals("run")) {
					if (method.getBody() != null) {
						CFLCode cflCode = getCodedFunctionArtifact().getCflCode();
						cflCode.setUsercode(method.getBody());
						cflCode.setPrivateuserfunctions(privateFunctions);
						cflCode.setAdded(true);
						cflCode.setModified(true);
						System.out.println("Method Body " + method.getBody());
						new CodedFunctionApi().saveCFLCode(getArtifact(), cflCode);
						Utilities.getInstance().refreshArtifactTree();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bottomFactoryUi.getCFLInputTable().renderCFLInputParameters();
		bottomFactoryUi.getCFLOutputTable().renderCFLOutputParameters();
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
		for (Object highLightedLines : getHighlightedLines()) {
			editor.removeLineHighlight(highLightedLines);
		}
		initCodeViewFile();
		String code = getJavaEditor().getText();
		File file = getCodeViewFile();
		opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().writeToFile(file, code);

		String rootCodeDirpath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
				.getProjectArtifactCodesFolder();
		String cflArtifactPath = file.getAbsolutePath();
		List<CompileError> compileErrors = new ArtifactCompiler().compileCFLArtifact(rootCodeDirpath, cflArtifactPath,
				"Web");

		compileErrors = new CompilerUtilities().filterErrors(compileErrors, Kind.ERROR);
		codeEditorBottomFactory.getCompilationResultTable().renderCompilingError(new ArrayList<CompileError>());
		if (compileErrors.size() > 0) {
			for (CompileError error : compileErrors) {
				Object lineHighlightObject;
				try {
					editor.setHighlightCurrentLine(false);
					lineHighlightObject = editor.addLineHighlight(((int) error.getLineNumber()) - 1, Color.RED);
					addHighlightedLines(lineHighlightObject);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			toggleSaveButton(false);
			codeEditorBottomFactory.getCompilationResultTable().renderCompilingError(compileErrors);
			return;
		}
		GenericEditorIntellisense.getCodeEditorInstance().addOpKeyTranspiledClassInformation();
		saveButton.setEnabled(false);
		checkClassIsRunnable();
	}

	private void handleRefreshOnSave() {
		initCodeViewFile();
		if (saveButton.getEnabled() == true) {
			int status1 = CustomNotificationUtil.openConfirmDialog(this.getShell(), "OpKey",
					String.format("Do you want to save '%s'?", getCodeViewFile().getName()));
			if (status1 == 2) {
				return;
			}
			if (status1 == 0) {
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

	public List<Object> getHighlightedLines() {
		return highlightedLines;
	}

	public void addHighlightedLines(Object highlightedLines) {
		this.highlightedLines.add(highlightedLines);
	}
}
