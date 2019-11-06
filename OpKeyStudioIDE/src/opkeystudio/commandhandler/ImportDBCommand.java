package opkeystudio.commandhandler;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
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
		ServiceRepository.getInstance().setExortedDBFilePath(filePath);
		ArtifactTree tree=(ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
		tree.renderArtifacts();
		
	}

}
