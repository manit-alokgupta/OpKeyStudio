package opkeystudio.featurecore.project;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtificateTreeItem;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate.ArtificateType;
import opkeystudio.opkeystudiocore.core.project.artificates.RootFolder;
import opkeystudio.opkeystudiocore.core.project.projects.ProjectParser;

public class ProjectLoader {
	public void loadProjectInTree(Tree tree, String projectPath) throws IOException {
		List<RootFolder> topNodes = new ProjectParser().parseProjects(projectPath);
		for (RootFolder topNode : topNodes) {
			ArtificateTreeItem menuItem = new ArtificateTreeItem(tree, topNode);
			menuItem.setText(topNode.getArtificateName());
			menuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/folder.gif"));
			populateProjectTreeItem(menuItem, topNode);
		}
	}

	private void populateProjectTreeItem(ArtificateTreeItem item, Artificate artificateobject) {
		List<Artificate> artificates = artificateobject.getArtificates();
		for (Artificate artificate : artificates) {
			ArtificateTreeItem menuItem = new ArtificateTreeItem(item, artificate);
			menuItem.setText(artificate.getArtificateName());
			if (artificate.getArtificateType() == ArtificateType.FOLDER) {
				menuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/folder.gif"));
			}
			if (artificate.getArtificateType() == ArtificateType.TESTCASE) {
				menuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase.gif"));
			}
			populateProjectTreeItem(menuItem, artificate);
		}
	}
}
