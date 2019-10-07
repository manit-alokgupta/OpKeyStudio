
package opkeystudio.commandhandler;

import java.io.IOException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import opkeystudio.featurecore.project.ProjectLoader;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class RefreshProject {
	@Execute
	public void execute(Shell shell) throws IOException {
		refreshProjectTree();
	}

	public void refreshProjectTree() throws IOException {
		Tree projectTree = (Tree) ServiceRepository.getInstance().getProjectTreeObject();
		projectTree.removeAll();
		new ProjectLoader().loadProjectInTree(projectTree, ServiceRepository.getInstance().getDefaultProjectPath());
	}

}