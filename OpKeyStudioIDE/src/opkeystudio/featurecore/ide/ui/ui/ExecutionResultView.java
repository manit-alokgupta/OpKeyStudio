package opkeystudio.featurecore.ide.ui.ui;

import java.io.ByteArrayOutputStream;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSessionExecutor;
import opkeystudio.opkeystudiocore.core.execution.ArtifactExecutor;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;

public class ExecutionResultView extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private ToolItem runButton;
	private ToolItem saveButton;
	private ToolItem refreshButton;
	private ExecutionSession executionSession;

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
		ExecutionSessionExecutor exeutor = new ExecutionSessionExecutor();
		ArtifactExecutor executorUtilities = exeutor.execute(getExecutionSession());
		startExecutionLogsFetch(executorUtilities);
	}

	private void startExecutionLogsFetch(ArtifactExecutor executor) {
		new MessageDialogs().executeDisplayAsync(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ByteArrayOutputStream standardOutPut = executor.getStandardOutput();
					ByteArrayOutputStream standardErrorOutput = executor.getStandardErrorOutput();
					System.out.println(">>Execution Status " + executor.isExecutionCompleted());
					if (executor.isExecutionCompleted()) {
						String consoleOutPut = standardOutPut.toString() + System.lineSeparator()
								+ standardErrorOutput.toString();
						System.out.println(">>Logs " + consoleOutPut);
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
	}

	private void initUI() {
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
	}

	public ExecutionResultView getInstance() {
		return this;
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
}
