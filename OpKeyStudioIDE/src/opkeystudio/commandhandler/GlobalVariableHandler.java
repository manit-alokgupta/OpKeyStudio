package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.featurecore.ide.ui.ui.GlobalVariableDialog;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class GlobalVariableHandler {
	@Execute
	public void execute(Shell shell) {
		GlobalVariableDialog gdialog = new GlobalVariableDialog(shell, SWT.CENTER);
		gdialog.open();
	}

	@CanExecute
	public boolean canExecute() {
		if (ServiceRepository.getInstance().getDefaultProject() != null)
			return true;
		return false;
	}
}