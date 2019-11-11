package opkeystudio.commandhandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.project.ProjectDataApi;
import opkeystudio.opkeystudiocore.core.apis.dto.project.Project;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ImportDBCommand {

	String[] filterExt = { "*.db;*.sql" };

	@Execute
	public void execute(Shell shell) throws JsonParseException, JsonMappingException, SQLException, IOException {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setFilterExtensions(filterExt);
		dialog.open();
		String filePath = dialog.getFilterPath() + "\\" + dialog.getFileName();
		if (filePath != null) {
			ServiceRepository.getInstance().setExortedDBFilePath(filePath);
			List<Project> projects = new ProjectDataApi().getProjectList();
			ServiceRepository.getInstance().setDefaultProject(projects.get(0));
			ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
			tree.renderArtifacts();
		}

	}

}
