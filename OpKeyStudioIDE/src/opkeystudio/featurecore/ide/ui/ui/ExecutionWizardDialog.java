package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.opkeystudiocore.core.execution.ArtifactExecutor;
import opkeystudio.opkeystudiocore.core.execution.ExecutionSession;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

public class ExecutionWizardDialog extends TitleAreaDialog {
	private TestCaseView parentTestCaseView;
	private TestSuiteView parentTestSuiteView;
	private boolean executingFromTestCaseView;
	private boolean executingFromTestSuiteView;
	private Text text_1;
	private Text text_2;

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
	}

	public ExecutionWizardDialog(Shell parentShell, TestSuiteView parentTestCaseView) {
		super(parentShell);
		this.setParentTestSuiteView(parentTestCaseView);
		this.setExecutingFromTestSuiteView(true);
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
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
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 20, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 20, 1));
		lblNewLabel_1.setText("Build Name:");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 20, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 20, 1));
		lblNewLabel_2.setText("Plugin:");
		new Label(container, SWT.NONE);
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 20, 1));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
		// setMessage("Select The Plugin, and Click \"Run\"");
		setTitle("Execution Wizard");
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button runButton = createButton(parent, IDialogConstants.OK_ID, "Run", true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		runButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isExecutingFromTestCaseView()) {
					new ArtifactExecutor().execute(new ExecutionSession("", "", getParentTestCaseView().getArtifact()));
					return;
				}
				if (isExecutingFromTestSuiteView()) {
					new ArtifactExecutor()
							.execute(new ExecutionSession("", "", getParentTestSuiteView().getArtifact()));
					return;
				}
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
}
