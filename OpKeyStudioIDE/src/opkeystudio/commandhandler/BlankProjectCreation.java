package opkeystudio.commandhandler;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.project.ProjectDataApi;
import opkeystudio.opkeystudiocore.core.apis.dto.project.Project;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class BlankProjectCreation {

	String[] filterExt = { "*.db;*.sql" };

	@Execute
	public void execute(Shell shell) {
		Utilities.getInstance().setDefaultShell(shell);
		String projectName = new MessageDialogs().openInputDialogAandGetValue("OpKey",
				"Please Provide The Project Name", "");
		if (projectName.isEmpty()) {
			new MessageDialogs().openErrorDialog("OpKey", "Project name Should not be blank.");
			return;
		}
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setFilterExtensions(filterExt);
		dialog.open();
		String filePath = dialog.getFilterPath() + "\\" + dialog.getFileName();
		if (filePath != null) {
			File file = new File(filePath);
			if (!file.exists()) {
				return;
			}
			if (!file.isFile()) {
				return;
			}
			ServiceRepository.getInstance().setExortedDBFilePath(filePath);
			SQLiteCommunicator sqlComm = new SQLiteCommunicator(filePath);
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
		SQLiteCommunicator sqlComm = new SQLiteCommunicator(ServiceRepository.getInstance().getExportedDBFilePath());
		try {
			sqlComm.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new Utilities().closeAllMparts();
		SQLiteCommunicator.getOpKeyDBCommunicator(sqlComm);
		List<Project> projects = new ProjectDataApi().getProjectList();
		ServiceRepository.getInstance().setDefaultProject(projects.get(0));
	}

}
