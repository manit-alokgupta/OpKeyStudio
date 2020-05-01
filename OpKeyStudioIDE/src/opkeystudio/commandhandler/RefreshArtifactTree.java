
package opkeystudio.commandhandler;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTree;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class RefreshArtifactTree {
	@Execute
	public void execute(Shell shell) throws JsonParseException, JsonMappingException, SQLException, IOException {

	}

	public void refreshArtifactTree() {
		try {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_WAIT);
			ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
			tree.renderArtifacts();
		} finally {
			Utilities.getInstance().setShellCursor(SWT.CURSOR_ARROW);
		}
		
	}
}