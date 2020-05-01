package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.ui.ProjectOpenDialog;

public class OpenProjectHandler {

	String[] filterExt = { "*.db;*.sql" };

	@Execute
	public void execute(Shell shell) {
		try {
			Utilities.getInstance().setDefaultShell(shell);
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			new ProjectOpenDialog(shell).open();
		}
		finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}
		
	}

}
