package opkeystudio.featurecore.ide.ui.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.execution.ArtifactExecutor;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSessionExecutor;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

public class ExecutionResultView extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private ToolItem pauseButton;
	private ToolItem stopButton;
	private ToolItem refreshButton;
	private StyledText logTextView;
	private ExecutionSession executionSession;
	private ArtifactExecutor artifactExecutor;

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
							new MessageDialogs().openErrorDialog("OpKey",
									"Artifacts has Compiling Errors. Please check the execution logs for errors.");
							return;
						}
						setArtifactExecutor(executorExecutor);
						startExecutionLogsFetch(executorExecutor);

					}
				});
		msd.closeProgressDialog();
	}

	private void displayLogs(String text) {
		new MessageDialogs().executeDisplayAsync(new Runnable() {

			@Override
			public void run() {
				if (logTextView.isDisposed()) {
					return;
				}
				logTextView.setForeground(new Color(logTextView.getDisplay(), 0, 0, 255));
				logTextView.setText(text);
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
					errorLogs += error.getMessage() + System.lineSeparator();
				}
				logTextView.setForeground(new Color(logTextView.getDisplay(), 255, 0, 0));
				logTextView.setText(errorLogs);
			}
		});
	}

	private void startExecutionLogsFetch(ArtifactExecutor executor) {
		Thread logThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					ByteArrayOutputStream standardOutPut = executor.getStandardOutput();
					ByteArrayOutputStream standardErrorOutput = executor.getStandardErrorOutput();
					String consoleOutPut = standardOutPut.toString() + System.lineSeparator()
							+ standardErrorOutput.toString();
					try {
						standardOutPut.flush();
						standardErrorOutput.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					displayLogs(consoleOutPut);
					if (executor.isExecutionCompleted()) {
						break;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		logThread.start();
	}

	private void initUI() {
		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		stopButton = new ToolItem(toolBar, SWT.NONE);
		// stopButton.setText("Save");
		stopButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.EXIT_ICON));
		stopButton.setToolTipText("Stop Execution");

		logTextView = new StyledText(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		logTextView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		logTextView.setEditable(true);
		logTextView.setAlwaysShowScrollBars(true);

		stopButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				getArtifactExecutor().stopExecutionSession();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

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
