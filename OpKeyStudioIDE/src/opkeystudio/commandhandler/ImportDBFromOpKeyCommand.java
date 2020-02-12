package opkeystudio.commandhandler;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.ui.LoginDialog;
import opkeystudio.opkeystudiocore.core.apis.dbapi.project.ProjectDataApi;
import opkeystudio.opkeystudiocore.core.apis.dto.project.Project;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ImportDBFromOpKeyCommand {

	@Execute
	public void execute(Shell shell) {
		Utilities.getInstance().setDefaultShell(shell);
		if (ServiceRepository.getInstance().getOpKeyHostAuthToken() != null
				&& ServiceRepository.getInstance().getOpKeyHostSessionId() != null) {
			boolean status = new MessageDialogs().openConfirmDialog("OpKey",
					"You are already login to OpKey. Click Ok to Relogin");
			if (status == false) {
				return;
			}
		}
		LoginDialog loginDialog = new LoginDialog(shell, 0);
		loginDialog.open();
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}
		SQLiteCommunicator sqlComm = new SQLiteCommunicator(ServiceRepository.getInstance().getExportedDBFilePath());
		try {
			sqlComm.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SQLiteCommunicator.getOpKeyDBCommunicator(sqlComm);
		List<Project> projects = new ProjectDataApi().getProjectList();
		ServiceRepository.getInstance().setDefaultProject(projects.get(0));
		ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
		tree.renderArtifacts();

	}

}
