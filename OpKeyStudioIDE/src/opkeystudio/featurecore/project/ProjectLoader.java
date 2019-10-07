package opkeystudio.featurecore.project;

import java.util.List;

import org.eclipse.swt.widgets.Tree;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtificateTreeItem;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;
import opkeystudio.opkeystudiocore.core.project.artificates.RootFolder;
import opkeystudio.opkeystudiocore.core.project.projects.ProjectParser;

public class ProjectLoader {
	public void loadProjectInTree(Tree tree, String projectPath) {
		List<RootFolder> topNodes = new ProjectParser().parseProjects(projectPath);
		for (RootFolder topNode : topNodes) {
			ArtificateTreeItem menuItem = new ArtificateTreeItem(tree, topNode);
			menuItem.setText(topNode.getName());
			populateProjectTreeItem(menuItem, topNode);
		}
	}

	private void populateProjectTreeItem(ArtificateTreeItem item, Artificate artificateobject) {
		List<Artificate> artificates = artificateobject.getArtificates();
		for (Artificate artificate : artificates) {
			ArtificateTreeItem menuItem = new ArtificateTreeItem(item, artificate);
			menuItem.setText(artificate.getName());
			populateProjectTreeItem(menuItem, artificate);
		}
	}
}
