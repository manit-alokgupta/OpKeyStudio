package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectRepository;

public class ObjectRepositoryTree extends CustomTree {

	public ObjectRepositoryTree(Composite parent, int style) {
		super(parent, style);
		renderObjectRepositories();
	}

	public void setObjectRepositoriesData(List<ObjectRepository> objectRepository) {
		super.setControlData(objectRepository);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectRepository> getObjectRepositoriesData() {
		return (List<ObjectRepository>) super.getControlData();
	}

	private void addIcon(ArtifactTreeItem artTreeItem) {
		if (artTreeItem.getArtifact() == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Folder) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Flow) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/testcase.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/object repo.png"));
		}
	}

	private void renderAllArtifactTree(ObjectRepositoryTreeItem rootNode, List<ObjectRepository> allArtifacts) {
		String artifactId = rootNode.getObjectRepository().getObject_id();
		for (ObjectRepository artifact : allArtifacts) {
			System.out.println(artifact.getName());
			if (artifact.getParent_object_id() != null) {
				if (artifact.getParent_object_id().equals(artifactId)) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(artifact.getName());
					orTreeItem.setArtifact(artifact);
					orTreeItem.setExpanded(true);
					// addIcon(artitreeitem);
					renderAllArtifactTree(orTreeItem, allArtifacts);
				}
			}
		}
	}

	public void renderObjectRepositories() {
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.objectrepositoryArtifact");
		System.out.println("Neon " + artifact.getId());
		try {

			ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
			rootNode.setText(artifact.getName());
			rootNode.setExpanded(true);
			// addIcon(rootNode);
			List<ObjectRepository> objectRepositories = new ObjectRepositoryApi()
					.getAllObjects(artifact.getId().trim());

			List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
			for (ObjectRepository objectRepository : objectRepositories) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					orTreeItem.setExpanded(true);
					topMostNodes.add(orTreeItem);
					// addIcon(artitreeitem);
				}
			}

			for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
				renderAllArtifactTree(topMostNode, objectRepositories);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
