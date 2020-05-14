package opkeystudio.commandhandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.CodeViewTree;
import opkeystudio.opkeystudiocore.core.apis.dto.project.Project;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class BlankProjectCreation {

	String[] filterExt = { "*.db;*.sql" };

	@Execute
	public void execute(Shell shell) throws IOException {
		try {
			Utilities.getInstance().setShellCursor(shell, SWT.CURSOR_WAIT);
			Utilities.getInstance().setDefaultShell(shell);
			String projectName = new MessageDialogs().openInputDialogAandGetValue("New Project",
					"Please Provide The Project Name", "");
			if (projectName == null) {
				return;
			}
			if (projectName.isEmpty()) {
				new MessageDialogs().openErrorDialog("OpKey", "Project name Should not be blank.");
				return;
			}
			
			
			ServiceRepository.getInstance().setProjectName(projectName);
			Utilities.getInstance().getDefaultShell().setText("OpKey Studio - [" + ServiceRepository.getInstance().getProjectPath()+"] v" + Platform.getProduct().getDefiningBundle().getVersion());
			
			String blankDbFile = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
					.getCommons_DBStructureFolder() + File.separator + "artifact_blankdb.db";

			String filePath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getProjectsFolder()
					+ File.separator + ServiceRepository.getInstance().getProjectName();

			if (!new File(filePath).exists()) {
				new File(filePath).mkdir();
			} else {
				new MessageDialogs().openErrorDialog("OpKey",
						String.format("Project name '%s' already exists. Please provide a different name.",
								ServiceRepository.getInstance().getProjectName()));
				return;
			}

			filePath = filePath + File.separator + "artifacts.db";
			FileUtils.copyFile(new File(blankDbFile), new File(filePath));
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
					e.printStackTrace();
				}
				SQLiteCommunicator.getOpKeyDBCommunicator(sqlComm);

				String userId = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getUniqueUUID("");
				Project project = new Project();
				project.setP_id(opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getUniqueUUID(""));
				project.setName(ServiceRepository.getInstance().getProjectName());
				project.setCreatedby(userId);
				project.setCreatedon(
						opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getCurrentDateTime());
				project.setCreatedon_tz(
						opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance().getCurrentTimeZone());

				ServiceRepository.getInstance().setDefaultProject(project);
				ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
				tree.renderArtifacts(true);
				CodeViewTree ctree = (CodeViewTree) SystemRepository.getInstance().getCodeViewTreeControl();
				if (ctree != null) {
					ctree.renderCodeViewTree();
				}
			}
			new Utilities().closeAllMparts();
		} finally {
			Utilities.getInstance().setShellCursor(shell, SWT.CURSOR_ARROW);
		}

	}

}
