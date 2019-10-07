
package opkeystudio.commandhandler;

import java.io.IOException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import opkeystudio.featurecore.project.ProjectLoader;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class RefreshProject {
	@Execute
	public void execute(Shell shell) {
		refreshProjectTree();
	}

	public void refreshProjectTree() {
		Tree projectTree = (Tree) ServiceRepository.getInstance().getProjectTreeObject();
		projectTree.removeAll();
		try {
			new ProjectLoader().loadProjectInTree(projectTree, ServiceRepository.getInstance().getDefaultProjectPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}