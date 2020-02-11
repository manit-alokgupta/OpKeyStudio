package opkeystudio.commandhandler;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.ui.LoginDialog;
import opkeystudio.opkeystudiocore.core.apis.restapi.ArtifactUpload;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ExportToSaas {
	@Execute
	public void execute(Shell shell) {
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}
		Utilities.getInstance().setDefaultShell(shell);
		boolean loginRequired = new ArtifactUpload().isLoginToSaasRequired();
		if (loginRequired) {
			LoginDialog dialog = new LoginDialog(shell, 0);
			dialog.open();

		}
		MessageDialogs msd = new MessageDialogs();
		msd.openProgressDialog(shell, "Exporting Artifact to SAAS. Please wait....", true, new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				String retdata = new ArtifactUpload().uploadCurrentUsedArtifact();
				if (retdata == null) {
					new MessageDialogs().openErrorDialog("OpKey", "Unable to perform Export to SAAS");
				}
				else if (retdata.contains("[{")) {
					new MessageDialogs().openInformationDialog("OpKey", "Export to SAAS Finished");
				}
				else {
					new MessageDialogs().openErrorDialog("OpKey", retdata);
				}
			}
		});
		msd.closeProgressDialog();
	}

}
