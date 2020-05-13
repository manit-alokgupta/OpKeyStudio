
package opkeystudio.commandhandler;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.ArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.CodeViewTree;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class RefreshArtifactTree {
	@Execute
	public void execute(Shell shell) throws JsonParseException, JsonMappingException, SQLException, IOException {

	}

	public void refreshArtifactTree() {
		ArtifactTree tree = (ArtifactTree) SystemRepository.getInstance().getArtifactTreeControl();
		tree.renderArtifacts(true);
		CodeViewTree ctree = (CodeViewTree) SystemRepository.getInstance().getCodeViewTreeControl();
		if (ctree != null) {
			ctree.renderCodeViewTree();
		}

	}
}