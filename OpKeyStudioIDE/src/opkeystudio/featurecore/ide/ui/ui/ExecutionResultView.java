package opkeystudio.featurecore.ide.ui.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.execution.ArtifactExecutor;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSessionExecutor;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

public class ExecutionResultView extends SuperComposite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private ToolItem pauseButton;
	private ToolItem stopButton;
	private ToolItem refreshButton;
	private ToolItem showLogView;
	private StyledText logTextView;
	private ExecutionSession executionSession;
	private ArtifactExecutor artifactExecutor;

	private Thread logOutThread = null;
	private Thread logErrThread = null;

	public ExecutionResultView(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setLayout(new GridLayout(1, false));
		initExecutionSession();
		initUI();
		startExecutionSession();
	}

	private void initExecutionSession() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		this.executionSession = (ExecutionSession) mpart.getTransientData().get("opkeystudio.executionSessionData");
	}

	private void startExecutionSession() {
		MessageDialogs msd = new MessageDialogs();
		msd.openProgressDialogOnBackgroundThread(getParent().getShell(), "Please Wait Execution is on Progress...",
				true, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						ExecutionSessionExecutor exeutor = new ExecutionSessionExecutor();
						ArtifactExecutor executorExecutor = exeutor.execute(getExecutionSession());
						if (executorExecutor.isContainsErrors()) {
							displayCompileErrors(executorExecutor.getCompileErrors());
							return;
						}
						setArtifactExecutor(executorExecutor);
						startExecutionLogsFetch(executorExecutor);

					}
				});
		msd.closeProgressDialog();
	}

	private void displayLogs(String text, Color color) {

		Color white = new Color(logTextView.getDisplay(), 255, 255, 255);

		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {

				if (logTextView.isDisposed()) {
					return;
				}

				if (logTextView.getTextLimit() - logTextView.getText().length() > 100)
					logTextView.setText("");

// 				logTextView.setForeground(new Color(logTextView.getDisplay(), 0, 0, 255));

				if (text != null) {
					int start = logTextView.getText().length();
					logTextView.append(text);
					logTextView.setStyleRange(new StyleRange(start, text.length(), color, white));
				}

			}

		});

	}

	private void displayCompileErrors(List<CompileError> errors) {
		new MessageDialogs().executeDisplayAsync(new Runnable() {

			@Override
			public void run() {
				if (logTextView.isDisposed()) {
					return;
				}
				String errorLogs = "Errors while Compiling Artifacts";
				for (CompileError error : errors) {
					errorLogs += System.lineSeparator() + error.getSource().getName() + System.lineSeparator()
							+ error.getMessage() + System.lineSeparator();
				}
				logTextView.setForeground(new Color(logTextView.getDisplay(), 255, 0, 0));
				logTextView.setText(errorLogs);
				new MessageDialogs().openErrorDialog("OpKey",
						"Artifacts has Compiling Errors. Please check the execution logs for errors.");
			}
		});
	}

	private Runnable getLogRunnable(File logFile, Color logColor, Color warningColor) {
		Runnable r = new Runnable() {

			private File logFile = null;
			private Color logColor = null;
			private Color warningColor = null;

			public Runnable setFile(File logFile, Color logColor, Color warningColor) {
				this.logFile = logFile;
				this.logColor = logColor;
				this.warningColor = warningColor;
				return this;
			}

			@Override
			public void run() {

				try {
					if (showLogView.isDisposed())
						return;

					/*
					 * try (BufferedReader br = new BufferedReader( new InputStreamReader(new
					 * FileInputStream(logFile), StandardCharsets.UTF_8))) {
					 */

					try (FileInputStream fis = new FileInputStream(logFile)) {

						while (getArtifactExecutor().isExecutionCompleted() == false) {
							// String aLine = br.readLine();

							byte[] buffer = new byte[2048];
							int bytesRead = fis.read(buffer, 0, fis.available());
							if (bytesRead < 1) {
								Thread.sleep(10);
								continue;
							}

							String aLine = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);

							if (aLine == null || aLine.isEmpty())
								Thread.sleep(100);
							else if (aLine.toLowerCase().contains("warning"))
								displayLogs(aLine, warningColor);
							else
								displayLogs(aLine, logColor);

							if (getArtifactExecutor().isExecutionCompleted())
								break;
						}

					}

				} catch (InterruptedException e1) {
					displayLogs("Logger Thread Interupted", warningColor);

				} catch (IOException e21) {
					displayLogs(e21.toString(), warningColor);
					e21.printStackTrace();
				}

				// if (executor.isExecutionCompleted()) {
				new MessageDialogs().executeDisplayAsync(new Runnable() {

					@Override
					public void run() {
						if (showLogView.isDisposed()) {
							return;
						}
						stopButton.setEnabled(false);
						showLogView.setEnabled(true);
					}
				});

				// }

			}

		}.setFile(logFile, logColor, warningColor);

		return r;

	}

	private void startExecutionLogsFetch(ArtifactExecutor executor) throws InterruptedException {
		Color red = new Color(logTextView.getDisplay(), 255, 0, 0);
		Color black = new Color(logTextView.getDisplay(), 0, 0, 0);
		Color orange = new Color(logTextView.getDisplay(), 255, 165, 0);

		while (getArtifactExecutor().getErrLogFile() == null
				|| getArtifactExecutor().getErrLogFile().exists() == false) {
			Thread.sleep(200);
		}

		logErrThread = new Thread(getLogRunnable(getArtifactExecutor().getErrLogFile(), red, orange));
		logErrThread.start();

		while (getArtifactExecutor().getOutLogFile() == null
				|| getArtifactExecutor().getOutLogFile().exists() == false) {
			Thread.sleep(200);
		}

		logOutThread = new Thread(getLogRunnable(getArtifactExecutor().getOutLogFile(), black, orange));
		logOutThread.start();

	}

	private void initUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		stopButton = new ToolItem(toolBar, SWT.NONE);
		// stopButton.setText("Save");
		stopButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.EXIT_ICON));
		stopButton.setToolTipText("Stop Execution");
		new ToolItem(toolBar, SWT.SEPARATOR);
		showLogView = new ToolItem(toolBar, SWT.NONE);
		showLogView.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		showLogView.setToolTipText("Show Report");
		showLogView.setEnabled(false);
		logTextView = new StyledText(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		logTextView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		logTextView.setEditable(false);
		logTextView.setAlwaysShowScrollBars(true);

		logTextView.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.stateMask == SWT.CTRL && e.keyCode == 'a') {
					logTextView.selectAll();
					e.doit = false;
				}
			}
		});

		stopButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (logOutThread != null)
					logOutThread.interrupt();

				if (logErrThread != null)
					logErrThread.interrupt();

				displayLogs("Stopping Execution...", new Color(logTextView.getDisplay(), 255, 165, 0));
				getArtifactExecutor().getExecutionThread().interrupt();
				getArtifactExecutor().stopExecutionSession();

				if (getArtifactExecutor().isExecutionCompleted())
					stopButton.setEnabled(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		showLogView.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ExecutionSession esession = getExecutionSession();
				EPartService partService = opkeystudio.core.utils.Utilities.getInstance().getEpartService();
				MPart part = partService.createPart("opkeystudio.partdescriptor.reportViewer");
				part.setLabel(esession.getSessionName());
				part.setTooltip(esession.getSessionName());
				part.getTransientData().put("opkeystudio.executionSessionData", esession);
				partService.showPart(part, PartState.ACTIVATE);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	public ExecutionResultView getInstance() {
		return this;
	}

	public void toggleRunButton(boolean status) {
		this.pauseButton.setEnabled(status);
	}

	public void toggleSaveButton(boolean status) {
		this.stopButton.setEnabled(status);
	}

	public void toggleRefreshButton(boolean status) {
		this.refreshButton.setEnabled(status);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ExecutionSession getExecutionSession() {
		return executionSession;
	}

	public void setExecutionSession(ExecutionSession executionSession) {
		this.executionSession = executionSession;
	}

	public ArtifactExecutor getArtifactExecutor() {
		return artifactExecutor;
	}

	public void setArtifactExecutor(ArtifactExecutor artifactExecutor) {
		this.artifactExecutor = artifactExecutor;
	}
}
