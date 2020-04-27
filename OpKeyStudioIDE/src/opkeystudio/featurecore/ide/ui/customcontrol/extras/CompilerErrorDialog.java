package opkeystudio.featurecore.ide.ui.customcontrol.extras;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;

public class CompilerErrorDialog extends TitleAreaDialog {
	private List<CompileError> compileErrors=new ArrayList<CompileError>();
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public CompilerErrorDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Unable to procced due to following Compilation Error");
		setTitle("Compile Error");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		StyledText styledText = new StyledText(composite, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setEnabled(true);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CANCEL_ID, "Close", false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public List<CompileError> getCompileErrors() {
		return compileErrors;
	}

	public void setCompileErrors(List<CompileError> compileErrors) {
		this.compileErrors = compileErrors;
	}
}
