package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.ui.LoginDialog;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ImportDBFromOpKeyCommand {

	@Execute
	public void execute(Shell shell) {
		Utilities.getInstance().setDefaultShell(shell);
		if (ServiceRepository.getInstance().getOpKeyHostAuthToken() != null
				&& ServiceRepository.getInstance().getOpKeyHostSessionId() != null) {
			boolean status = new MessageDialogs().openConfirmDialog("OpKey",
					"You are already logged in to OpKey. Click Ok to Relogin");
			if (status == false) {
				return;
			}
		}
		LoginDialog loginDialog = new LoginDialog(shell, 0);
		loginDialog.open();
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}

		new ProjectImporter().importProjectDB(ServiceRepository.getInstance().getExportedDBFilePath());
	}

}
