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
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class ObjectRepositoryTree extends CustomTree {
	private TestCaseView parentTestCaseView;
	private ObjectRepositoryView parentORView;

	public ObjectRepositoryTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
	}

	public ObjectRepositoryTree(Composite parent, int style, ObjectRepositoryView orView) {
		super(parent, style);
		this.setParentORView(orView);
		renderObjectRepositories();
	}

	public void setObjectRepositoriesData(List<ORObject> objectRepository) {
		super.setControlData(objectRepository);
	}

	@SuppressWarnings("unchecked")
	public List<ORObject> getObjectRepositoriesData() {
		return (List<ORObject>) super.getControlData();
	}

	private void addIcon(ObjectRepositoryTreeItem artTreeItem) {
		ORObject orObject = artTreeItem.getObjectRepository();
		if (orObject == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/or.png"));
			return;
		}
		String objectType = orObject.getOpkeytype();
		if (objectType.toLowerCase().contains("page")) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/oricons/page2020.png"));
		} else if (objectType.toLowerCase().contains("frame")) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/oricons/iframe.png"));
		} else {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/oricons/controller.png"));
		}
	}

	private void renderAllArtifactTree(ObjectRepositoryTreeItem rootNode, List<ORObject> allArtifacts) {
		String artifactId = rootNode.getObjectRepository().getObject_id();
		for (ORObject artifact : allArtifacts) {
			if (artifact.getParent_object_id() != null) {
				if (artifact.getParent_object_id().equals(artifactId)) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(artifact.getName());
					orTreeItem.setArtifact(artifact);
					addIcon(orTreeItem);
					renderAllArtifactTree(orTreeItem, allArtifacts);
				}
			}
		}
	}

	private void refereshAllArtifactTree(ObjectRepositoryTreeItem rootNode, List<ORObject> allArtifacts) {
		String artifactId = rootNode.getObjectRepository().getObject_id();
		for (ORObject artifact : allArtifacts) {
			if (!artifact.isDeleted()) {
				if (artifact.getParent_object_id() != null) {
					if (artifact.getParent_object_id().equals(artifactId)) {
						ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
						orTreeItem.setText(artifact.getName());
						orTreeItem.setArtifact(artifact);
						addIcon(orTreeItem);
						refereshAllArtifactTree(orTreeItem, allArtifacts);
					}
				}
			}
		}
	}

	public void expandAll(ObjectRepositoryTreeItem treeItem) {
		treeItem.setExpanded(true);
		TreeItem items[] = treeItem.getItems();
		for (TreeItem item : items) {
			item.setExpanded(true);
			expandAll((ObjectRepositoryTreeItem) item);
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
		getParentORView().setOrId(artifact.getId());
		try {

			ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
			rootNode.setText(artifact.getName());
			rootNode.setExpanded(true);
			addIcon(rootNode);
			List<ORObject> objectRepositories = new ObjectRepositoryApi().getAllObjects(artifact.getId().trim());
			setObjectRepositoriesData(objectRepositories);
			List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
			for (ORObject objectRepository : objectRepositories) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					topMostNodes.add(orTreeItem);
					addIcon(orTreeItem);
				}
			}

			for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
				renderAllArtifactTree(topMostNode, objectRepositories);
			}
			expandAll(rootNode);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getParentORView().toggleDeleteButton(false);
		getParentORView().toggleChildObjectToolItem(false);
		getParentORView().toggleRenameButton(false);
	}

	public void refreshObjectRepositories() {
		this.removeAll();
		MPart mpart = Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		getParentORView().setOrId(artifact.getId());
		ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
		rootNode.setText(artifact.getName());
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<ORObject> objectRepositories = getObjectRepositoriesData();

		List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
		for (ORObject objectRepository : objectRepositories) {
			if (!objectRepository.isDeleted()) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					topMostNodes.add(orTreeItem);
					addIcon(orTreeItem);
				}
			}
		}

		for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
			refereshAllArtifactTree(topMostNode, objectRepositories);
		}
		expandAll(rootNode);
		getParentORView().toggleDeleteButton(false);
		getParentORView().toggleChildObjectToolItem(false);
		getParentORView().toggleRenameButton(false);
	}

	public void fetchAndRenderORTree() {
		this.removeAll();
		try {
			ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
			rootNode.setText("ObjectRepository");
			rootNode.setExpanded(true);
			addIcon(rootNode);
			List<Artifact> artifacts = new ArtifactApi().getAllArtificatesByType("ObjectRepository");
			for (Artifact artifact : artifacts) {
				renderObjectRepositories(rootNode, artifact.getName(), artifact.getId());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void renderObjectRepositories(ObjectRepositoryTreeItem mainRootNode, String name, String or_id) {
		ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(mainRootNode, 0);
		rootNode.setText(name);
		rootNode.setExpanded(true);
		try {

			addIcon(rootNode);
			List<ORObject> objectRepositories = new ObjectRepositoryApi().getAllObjects(or_id.trim());
			setObjectRepositoriesData(objectRepositories);
			List<ObjectRepositoryTreeItem> topMostNodes = new ArrayList<>();
			for (ORObject objectRepository : objectRepositories) {
				if (objectRepository.getParent_object_id() == null) {
					ObjectRepositoryTreeItem orTreeItem = new ObjectRepositoryTreeItem(rootNode, 0);
					orTreeItem.setText(objectRepository.getName());
					orTreeItem.setArtifact(objectRepository);
					topMostNodes.add(orTreeItem);
					addIcon(orTreeItem);
				}
			}

			for (ObjectRepositoryTreeItem topMostNode : topMostNodes) {
				renderAllArtifactTree(topMostNode, objectRepositories);
			}
			expandAll(mainRootNode);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public ObjectRepositoryView getParentORView() {
		return parentORView;
	}

	public void setParentORView(ObjectRepositoryView parentORView) {
		this.parentORView = parentORView;
	}
}
