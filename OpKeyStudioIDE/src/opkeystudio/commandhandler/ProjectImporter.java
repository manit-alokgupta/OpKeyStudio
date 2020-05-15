package opkeystudio.commandhandler;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.CodeViewTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.project.ProjectDataApi;
import opkeystudio.opkeystudiocore.core.apis.dto.project.Project;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ProjectImporter {
	public void importProjectDB(String dbFilePath) {
		if (dbFilePath != null) {
			File file = new File(dbFilePath);
			if (!file.exists()) {
				return;
			}
			if (!file.isFile()) {
				return;
			}
			String projectName = file.getParentFile().getName();
			ServiceRepository.getInstance().setProjectName(projectName);
			Utilities.getInstance().getDefaultShell().setText("OpKey Studio - [" + ServiceRepository.getInstance().getProjectPath()+"] v" + Utilities.getInstance().getVersion());
			
			ServiceRepository.getInstance().setExortedDBFilePath(dbFilePath);
			SQLiteCommunicator sqlComm = new SQLiteCommunicator(dbFilePath);
			try {
				sqlComm.connect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SQLiteCommunicator.getOpKeyDBCommunicator(sqlComm);
			List<Project> projects = new ProjectDataApi().getProjectList();
			ServiceRepository.getInstance().setDefaultProject(projects.get(0));
			ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
			tree.renderArtifacts(true);
		}
		new Utilities().closeAllMparts();
	}
}
