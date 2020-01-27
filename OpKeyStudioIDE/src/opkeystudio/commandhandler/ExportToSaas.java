package opkeystudio.commandhandler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.ui.LoginDialog;
import opkeystudio.opkeystudiocore.core.apis.restapi.ArtifactUpload;

public class ExportToSaas {
	@Execute
	public void execute(Shell shell) {
		Utilities.getInstance().setDefaultShell(shell);
		boolean loginRequired = new ArtifactUpload().isLoginToSaasRequired();
		if (true) {
			LoginDialog dialog = new LoginDialog(shell, 0);
			dialog.open();

		}
		new ArtifactUpload().uploadCurrentUsedArtifact();
	}

}
