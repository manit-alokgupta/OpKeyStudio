package opkeystudio.commandhandler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ImportLocalDB {

	String[] filterExt = { "*.db;*.sql" };

	@Execute
	public void execute(Shell shell) throws IOException {
		try {
			Utilities.getInstance().setShellCursor(shell, SWT.CURSOR_WAIT);
			Utilities.getInstance().setDefaultShell(shell);
			FileDialog dialog = new FileDialog(shell, SWT.OPEN);
			dialog.setFilterExtensions(filterExt);
			dialog.open();
			String filePath = dialog.getFilterPath() + "\\" + dialog.getFileName();
			if (filePath != null) {
				if (!new File(filePath).isFile()) {
					return;
				}
				String projectName = new MessageDialogs().openInputDialogAandGetValue("OpKey",
						"Please Provide The Project Name", "");
				if (projectName == null) {
					return;
				}
				if (projectName.isEmpty()) {
					new MessageDialogs().openErrorDialog("OpKey", "Project name Should not be blank.");
					return;
				}
				ServiceRepository.getInstance().setProjectName(projectName);
				String projectFolderPath = opkeystudio.opkeystudiocore.core.utils.Utilities.getInstance()
						.getProjectsFolder() + File.separator + ServiceRepository.getInstance().getProjectName();

				if (!new File(projectFolderPath).exists()) {
					new File(projectFolderPath).mkdir();
				} else {
					new MessageDialogs().openErrorDialog("OpKey",
							String.format("Project name '%s' already exists. Please provide a different name.",
									ServiceRepository.getInstance().getProjectName()));
					return;
				}

				projectFolderPath = projectFolderPath + File.separator + "artifacts.db";
				FileUtils.copyFile(new File(filePath), new File(projectFolderPath));
				new ProjectImporter().importProjectDB(projectFolderPath);
			}

		} finally {
			Utilities.getInstance().setShellCursor(shell, SWT.CURSOR_ARROW);
		}

	}

}
