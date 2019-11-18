package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class ObjectRepositoryTree extends CustomTree {
	private TestCaseView parentTestCaseView;

	public ObjectRepositoryTree(Composite parent, int style) {
		super(parent, style);
		renderObjectRepositories();
	}

	public ObjectRepositoryTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
	}

	public void setObjectRepositoriesData(List<ORObject> objectRepository) {
		super.setControlData(objectRepository);
	}

	@SuppressWarnings("unchecked")
	public List<ORObject> getObjectRepositoriesData() {
		return (List<ORObject>) super.getControlData();
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

	private void renderAllArtifactTree(ObjectRepositoryTreeItem rootNode, List<ORObject> allArtifacts) {
		String artifactId = rootNode.getObjectRepository().getObject_id();
		for (ORObject artifact : allArtifacts) {
			System.out.println(artifact.getName());
			if (artifact.getParent_object_id() != null) {
				if (artifact.getParent_object_id().equals(artifactId)) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(artifact.getName());
					orTreeItem.setArtifact(artifact);
					// addIcon(artitreeitem);
					renderAllArtifactTree(orTreeItem, allArtifacts);
				}
			}
		}
	}

	private void refereshAllArtifactTree(ObjectRepositoryTreeItem rootNode, List<ORObject> allArtifacts) {
		String artifactId = rootNode.getObjectRepository().getObject_id();
		for (ORObject artifact : allArtifacts) {
			if (!artifact.isDeleted()) {
				System.out.println(artifact.getName());
				if (artifact.getParent_object_id() != null) {
					if (artifact.getParent_object_id().equals(artifactId)) {
						ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
						orTreeItem.setText(artifact.getName());
						orTreeItem.setArtifact(artifact);
						// addIcon(artitreeitem);
						refereshAllArtifactTree(orTreeItem, allArtifacts);
					}
				}
			}
		}
	}

	public void expandAll() {
		TreeItem items[] = this.getItems();
		for (TreeItem item : items) {
			item.setExpanded(true);
		}
		this.setRedraw(true);
	}

	public ObjectRepositoryTreeItem getSelectedTreeItem() {
		return (ObjectRepositoryTreeItem) this.getSelection()[0];
	}

	public ORObject getSelectedORObject() {
		ObjectRepositoryTreeItem treeItem = getSelectedTreeItem();
		return treeItem.getObjectRepository();
	}

	public void renderObjectRepositories() {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		System.out.println("Neon " + artifact.getId());
		try {

			ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
			rootNode.setText(artifact.getName());
			rootNode.setExpanded(true);
			// addIcon(rootNode);
			List<ORObject> objectRepositories = new ObjectRepositoryApi().getAllObjects(artifact.getId().trim());
			setObjectRepositoriesData(objectRepositories);
			List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
			for (ORObject objectRepository : objectRepositories) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					topMostNodes.add(orTreeItem);
					// addIcon(artitreeitem);
				}
			}

			for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
				renderAllArtifactTree(topMostNode, objectRepositories);
			}
			expandAll();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refreshObjectRepositories() {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		System.out.println("Neon " + artifact.getId());
		ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
		rootNode.setText(artifact.getName());
		rootNode.setExpanded(true);
		// addIcon(rootNode);
		List<ORObject> objectRepositories = getObjectRepositoriesData();

		List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
		for (ORObject objectRepository : objectRepositories) {
			if (!objectRepository.isDeleted()) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					topMostNodes.add(orTreeItem);
					// addIcon(artitreeitem);
				}
			}
		}

		for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
			refereshAllArtifactTree(topMostNode, objectRepositories);
		}
		expandAll();
	}
	
	public void renderObjectRepositories(String or_id) {
		this.removeAll();
		try {

			ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
			rootNode.setText("");
			rootNode.setExpanded(true);
			// addIcon(rootNode);
			List<ORObject> objectRepositories = new ObjectRepositoryApi().getAllObjects(or_id.trim());
			setObjectRepositoriesData(objectRepositories);
			List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
			for (ORObject objectRepository : objectRepositories) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					topMostNodes.add(orTreeItem);
					// addIcon(artitreeitem);
				}
			}

			for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
				renderAllArtifactTree(topMostNode, objectRepositories);
			}
			expandAll();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}
}
