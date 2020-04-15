package opkeystudio.featurecore.ide.ui.ui;

import java.io.File;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ExecutionWizardDialog extends TitleAreaDialog {
	private Combo pluginSelectionDropDown;
	private Button runButton;
	private TestCaseView parentTestCaseView;
	private TestSuiteView parentTestSuiteView;
	private boolean executingFromTestCaseView;
	private boolean executingFromTestSuiteView;
	private Text sessionNameTextField;
	private Text buildNameTextField;

	private ExecutionSession executionSession;
	private Composite container;
	private Composite area;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public ExecutionWizardDialog(Shell parentShell, TestCaseView parentTestCaseView) {
		super(parentShell);
		this.setParentTestCaseView(parentTestCaseView);
		this.setExecutingFromTestCaseView(true);
		setHelpAvailable(false);
		initExecutionSession(parentTestCaseView.getArtifact());
	}

	public ExecutionWizardDialog(Shell parentShell, TestSuiteView parentTestSuiteView) {
		super(parentShell);
		this.setParentTestSuiteView(parentTestSuiteView);
		this.setExecutingFromTestSuiteView(true);
		setHelpAvailable(false);
		initExecutionSession(parentTestSuiteView.getArtifact());
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		area = (Composite) super.createDialogArea(parent);
		container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(22, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 20, 1));
		lblNewLabel.setText("Session Name:");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		sessionNameTextField = new Text(container, SWT.BORDER);
		sessionNameTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 20, 1));
		sessionNameTextField.setText(getExecutionSession().getSessionName());
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 20, 1));
		lblNewLabel_1.setText("Build Name:");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		buildNameTextField = new Text(container, SWT.BORDER);
		buildNameTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 20, 1));
		buildNameTextField.setText(getExecutionSession().getBuildName());
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 20, 1));
		lblNewLabel_2.setText("Plugin:");
		new Label(container, SWT.NONE);

		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));

		pluginSelectionDropDown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		pluginSelectionDropDown.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 20, 1));
		pluginSelectionDropDown.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = pluginSelectionDropDown.getSelectionIndex();
				if (index == 0) {
					runButton.setEnabled(false);
					return;
				}
				String pluginName = pluginSelectionDropDown.getItem(index);
				getExecutionSession().setPluginName(pluginName);
				runButton.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
		// setMessage("Select The Plugin, and Click \"Run\"");
		setTitle("Execution Wizard");
		initPluginNames();
		return area;
	}

	private void initExecutionSession(Artifact artifact) {
		ExecutionSession eSession = new ExecutionSession(artifact.getName() + "_", "Build_");
		eSession.setArtifact(artifact);
		setExecutionSession(eSession);
	}

	private void initPluginNames() {
		pluginSelectionDropDown.removeAll();
		pluginSelectionDropDown.add("Select Plugin");
		String pluginDir = Utilities.getInstance().getDefaultPluginsDir();
		File pluginDirObject = new File(pluginDir);
		for (File file : pluginDirObject.listFiles()) {
			if (file.isDirectory()) {
				String pluginXmlFile = file.getAbsolutePath() + File.separator + "plugin.xml";
				if (!new File(pluginXmlFile).exists()) {
					continue;
				}
				pluginSelectionDropDown.add(file.getName());
			}
		}
		pluginSelectionDropDown.select(0);
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		runButton = createButton(parent, IDialogConstants.OK_ID, "Run", true);
		runButton.setEnabled(false);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new MessageDialogs().executeDisplayAsync(new Runnable() {

					@Override
					public void run() {
						executeSession();
					}
				});
				container.dispose();
				area.dispose();
				close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		cancelButton.addSelectionListener(new SelectionListener() {

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

	private void executeSession() {
		ExecutionSession executionSession = getExecutionSession();
		EPartService partService = opkeystudio.core.utils.Utilities.getInstance().getEpartService();
		MPart part = partService.createPart("opkeystudio.partdescriptor.executionResultPart");
		part.setLabel(executionSession.getSessionName());
		part.setTooltip(executionSession.getSessionName());
		part.getTransientData().put("opkeystudio.executionSessionData", executionSession);
		partService.showPart(part, PartState.ACTIVATE);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(680, 413);
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public boolean isExecutingFromTestCaseView() {
		return executingFromTestCaseView;
	}

	public void setExecutingFromTestCaseView(boolean executingFromTestCaseView) {
		this.executingFromTestCaseView = executingFromTestCaseView;
	}

	public boolean isExecutingFromTestSuiteView() {
		return executingFromTestSuiteView;
	}

	public void setExecutingFromTestSuiteView(boolean executingFromTestSuiteView) {
		this.executingFromTestSuiteView = executingFromTestSuiteView;
	}

	public ExecutionSession getExecutionSession() {
		return executionSession;
	}

	public void setExecutionSession(ExecutionSession executionSession) {
		this.executionSession = executionSession;
	}
}
