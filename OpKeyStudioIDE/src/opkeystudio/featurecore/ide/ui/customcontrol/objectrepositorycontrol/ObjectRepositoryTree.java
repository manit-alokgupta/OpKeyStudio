package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

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
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class ObjectRepositoryTree extends CustomTree {
	private TestCaseView parentTestCaseView;
	private ObjectRepositoryView parentORView;
	private List<ORObject> objectRepository = new ArrayList<ORObject>();

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
		this.objectRepository = objectRepository;
	}

	@SuppressWarnings("unchecked")
	public List<ORObject> getObjectRepositoriesData() {
		return this.objectRepository;
	}

	private void addIcon(ObjectRepositoryTreeItem artTreeItem) {
		ORObject orObject = artTreeItem.getObjectRepository();
		if (orObject == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));
			return;
		}
		String objectType = orObject.getOpkeytype();
		if (objectType.toLowerCase().contains("page")) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.PAGECONTROL_ICON));
		} else if (objectType.toLowerCase().contains("frame")) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.IFRAME_ICON));
		} else {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CONTROLLER_ICON));
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
		Artifact artifact = getParentORView().getArtifact();
		getParentORView().setOrId(artifact.getId());
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
		getParentORView().toggleDeleteButton(false);
		getParentORView().toggleChildObjectToolItem(false);
		getParentORView().toggleRenameButton(false);
	}

	public void refreshObjectRepositories() {
		this.removeAll();
		Artifact artifact = getParentORView().getArtifact();
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
		ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(this, 0);
		rootNode.setText("ObjectRepository");
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifactByType("ObjectRepository");
		for (Artifact artifact : artifacts) {
			renderObjectRepositories(rootNode, artifact.getName(), artifact.getId());
		}
	}

	public void renderObjectRepositories(ObjectRepositoryTreeItem mainRootNode, String name, String or_id) {
		ObjectRepositoryTreeItem rootNode = new ObjectRepositoryTreeItem(mainRootNode, 0);
		rootNode.setText(name);
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<ORObject> objectRepositories = new ArrayList<ORObject>();
		List<ORObject> allOrObjects = GlobalLoader.getInstance().getAllORObjects();
		for (ORObject orobject : allOrObjects) {
			if (orobject.getOr_id().equals(or_id.trim())) {
				objectRepositories.add(orobject);
			}
		}
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
