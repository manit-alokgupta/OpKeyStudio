package opkeystudio.featurecore.ide.ui.ui;

import javax.swing.table.TableColumn;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;

public class ProjectDialog extends TitleAreaDialog {
	private Text text;
	private Table table;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ProjectDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		String[] headers = { "Mode", "Project" };
		setMessage("Choose the OpKey Project");
		setTitle("Choose Project");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		text = new Text(container, SWT.BORDER);
		text.setBounds(10, 10, 424, 17);

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 27, 424, 115);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		for(String header:headers) {
			
		}
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.setText("Go");
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
