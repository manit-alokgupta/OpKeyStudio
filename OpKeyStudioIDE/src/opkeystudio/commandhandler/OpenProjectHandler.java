package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.ui.ProjectOpenDialog;

public class OpenProjectHandler {

	String[] filterExt = { "*.db;*.sql" };

	@Execute
	public void execute(Shell shell) {
		Utilities.getInstance().setDefaultShell(shell);
		new ProjectOpenDialog(shell).open();
	}

}
