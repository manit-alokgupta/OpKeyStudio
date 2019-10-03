package opkeystudio.featurecore.project.ui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.opkeystudiocore.core.exceptions.ValidationExceptions;
import opkeystudio.opkeystudiocore.core.project.projects.GenericWebProject;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ProjectCreationWizard extends Dialog {

	protected Object result;
	protected Shell shlCreateYourProject;
	private Text projectNameTextField;
	private Text workspaceLocationTextField;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ProjectCreationWizard(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlCreateYourProject.open();
		shlCreateYourProject.layout();
		Display display = getParent().getDisplay();
		while (!shlCreateYourProject.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlCreateYourProject = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlCreateYourProject.setSize(450, 300);
		shlCreateYourProject.setText("Create Your Project");
		shlCreateYourProject.setLayout(null);

		ProgressBar projectCreationProgressBar = new ProgressBar(shlCreateYourProject, SWT.NONE);
		projectCreationProgressBar.setBounds(10, 244, 234, 17);

		Button btnNewButton = new Button(shlCreateYourProject, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GenericWebProject gWebProj;
				try {
					gWebProj = new GenericWebProject(workspaceLocationTextField.getText(),
							projectNameTextField.getText());
					gWebProj.populateProject();
					for (int i = 0; i < 100; i++) {
						projectCreationProgressBar.setSelection(i);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					shlCreateYourProject.close();
				} catch (ValidationExceptions e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(263, 244, 75, 17);
		btnNewButton.setText("Create");

		Button btnClose = new Button(shlCreateYourProject, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlCreateYourProject.close();
			}
		});
		btnClose.setText("Close");
		btnClose.setBounds(359, 244, 75, 17);

		projectNameTextField = new Text(shlCreateYourProject, SWT.BORDER);
		projectNameTextField.setBounds(144, 45, 181, 21);

		Label lblNewLabel = new Label(shlCreateYourProject, SWT.NONE);
		lblNewLabel.setBounds(22, 51, 99, 15);
		lblNewLabel.setText("ProjectName");

		workspaceLocationTextField = new Text(shlCreateYourProject, SWT.BORDER);
		workspaceLocationTextField.setBounds(144, 98, 181, 21);
		workspaceLocationTextField.setText(Utilities.getInstance().getDefaultWorkSpacePath());

		Label lblNewLabel_1 = new Label(shlCreateYourProject, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lblNewLabel_1.setBounds(22, 102, 116, 15);
		lblNewLabel_1.setText("WorkSpace Location");

		Button btnNewButton_1 = new Button(shlCreateYourProject, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shlCreateYourProject);
				directoryDialog.open();
			}
		});
		btnNewButton_1.setBounds(334, 98, 75, 21);
		btnNewButton_1.setText("Browse");

	}
}
