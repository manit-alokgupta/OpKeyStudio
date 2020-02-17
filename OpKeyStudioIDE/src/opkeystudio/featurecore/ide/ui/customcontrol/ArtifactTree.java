package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ArtifactTree extends CustomTree {
	private TestSuiteView parentTestSuiteView;
	private boolean attachedinTestSuite = false;

	public ArtifactTree(Composite parent, int style) {
		super(parent, style);
		init();
		SystemRepository.getInstance().setArtifactTreeControl(this);
	}

	public ArtifactTree(Composite parent, int style, TestSuiteView parentTestSuiteView) {
		super(parent, style);
		this.setParentTestSuiteView(parentTestSuiteView);
		this.setAttachedinTestSuite(true);
	}

	private void init() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				openSelectedArtifact();
			}

		});
	}

	public void openSelectedArtifact() {
		if (this.getSelection() == null) {
			return;
		}
		if (this.getSelection().length == 0) {
			return;
		}
		ArtifactTreeItem selectedTreeItem = (ArtifactTreeItem) this.getSelection()[0];
		System.out.println("selectedTreeItem");
		populateArtifact(selectedTreeItem);
	}

	private void populateArtifact(ArtifactTreeItem artifactTreeItem) {
		if (artifactTreeItem == null) {
			return;
		}
		if (artifactTreeItem.getArtifact() == null) {
			return;
		}
		Artifact artifact = artifactTreeItem.getArtifact();
		Utilities.getInstance().openArtifacts(artifact);
	}

	public void setArtifactsData(List<Artifact> artifacts) {
		super.setControlData(artifacts);
	}

	@SuppressWarnings("unchecked")
	public List<Artifact> getArtifactsData() {
		return (List<Artifact>) super.getControlData();
	}

	private void addIcon(ArtifactTreeItem artTreeItem) {
		if (artTreeItem.getArtifact() == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Folder) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Flow) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testcase.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/or.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Suite) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testsuite.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.DataRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/note.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Component) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/fl.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.CodedFunction) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/cfl.png"));
		}
	}

	private void renderAllArtifactTree(ArtifactTreeItem rootNode, List<Artifact> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				if (artifact.getParentid().equals(artifactId)) {
					ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
					artitreeitem.setText(artifact.getName());
					artitreeitem.setArtifact(artifact);
					addIcon(artitreeitem);
					renderAllArtifactTree(artitreeitem, allArtifacts);
				}
			}
		}
	}

	private void refreshAllArtifactTree(ArtifactTreeItem rootNode, List<Artifact> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				if (artifact.isVisible()) {
					if (artifact.getParentid().equals(artifactId)) {
						ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
						artitreeitem.setText(artifact.getName());
						artitreeitem.setArtifact(artifact);
						addIcon(artitreeitem);
						refreshAllArtifactTree(artitreeitem, allArtifacts);
					}
				}
			}
		}
	}

	public void expandAll(ArtifactTreeItem treeItem) {
		treeItem.setExpanded(true);
		TreeItem items[] = treeItem.getItems();
		for (TreeItem item : items) {
			item.setExpanded(true);
			expandAll((ArtifactTreeItem) item);
		}
		this.setRedraw(true);
	}

	public void renderArtifacts() {
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}
		this.removeAll();
		ArtifactTreeItem rootNode = new ArtifactTreeItem(this, 0);
		if (isAttachedinTestSuite()) {
			rootNode.setText("Gherkin and Test Case");
		} else {
			rootNode.setText("Project WorkSpace");
		}
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<Artifact> artifacts = new ArrayList<>();
		if (isAttachedinTestSuite()) {
			artifacts = new ArtifactApi().getAllArtificatesByType("Flow");
		} else {
			artifacts = new ArtifactApi().getAllArtificates();
			GlobalLoader.getInstance().setAllArtifacts(artifacts);
		}
		setArtifactsData(artifacts);
		List<ArtifactTreeItem> topMostNodes = new ArrayList<>();
		for (Artifact artifact : artifacts) {
			if (artifact.getParentid() == null) {
				ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
				artitreeitem.setText(artifact.getName());
				artitreeitem.setArtifact(artifact);
				topMostNodes.add(artitreeitem);
				addIcon(artitreeitem);
			} else {
				if (isAttachedinTestSuite()) {
					ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
					artitreeitem.setText(artifact.getName());
					artitreeitem.setArtifact(artifact);
					addIcon(artitreeitem);
				}
			}
		}

		for (ArtifactTreeItem topMostNode : topMostNodes) {
			renderAllArtifactTree(topMostNode, artifacts);
		}
		expandAll(rootNode);
		GlobalLoader.getInstance().initAllArguments();
	}

	public void highlightArtifact(String artifactId) {
		List<TreeItem> titems = this.getAllTreeItems();
		for (TreeItem item : titems) {
			ArtifactTreeItem ati = (ArtifactTreeItem) item;
			if (ati.getArtifact() != null) {
				if (ati.getArtifact().getId().equals(artifactId)) {
					this.setSelection(item);
					this.notifyListeners(SWT.FocusIn, null);
					break;
				}
			}
		}
	}

	public void refereshArtifacts() {
		this.removeAll();
		ArtifactTreeItem rootNode = new ArtifactTreeItem(this, 0);
		if (isAttachedinTestSuite()) {
			rootNode.setText("Gherkin and Test Case");
		} else {
			rootNode.setText("Project WorkSpace");
		}
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<Artifact> artifacts = getArtifactsData();
		setArtifactsData(artifacts);
		List<ArtifactTreeItem> topMostNodes = new ArrayList<>();
		for (Artifact artifact : artifacts) {
			if (artifact.getParentid() == null) {
				ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
				artitreeitem.setText(artifact.getName());
				artitreeitem.setArtifact(artifact);
				topMostNodes.add(artitreeitem);
				addIcon(artitreeitem);
			} else {
				if (isAttachedinTestSuite()) {
					if (artifact.isVisible()) {
						ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
						artitreeitem.setText(artifact.getName());
						artitreeitem.setArtifact(artifact);
						addIcon(artitreeitem);
					}
				}
			}
		}

		for (ArtifactTreeItem topMostNode : topMostNodes) {
			refreshAllArtifactTree(topMostNode, artifacts);
		}
		expandAll(rootNode);
	}

	public ArtifactTreeItem getSelectedArtifactTreeItem() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		return (ArtifactTreeItem) this.getSelection()[0];
	}

	public Artifact getSelectedArtifact() {
		ArtifactTreeItem treeItem = getSelectedArtifactTreeItem();
		if (treeItem == null) {
			return null;
		}
		return treeItem.getArtifact();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public TestSuiteView getParentTestSuiteView() {
		return parentTestSuiteView;
	}

	public void setParentTestSuiteView(TestSuiteView parentTestSuiteView) {
		this.parentTestSuiteView = parentTestSuiteView;
	}

	public boolean isAttachedinTestSuite() {
		return attachedinTestSuite;
	}

	public void setAttachedinTestSuite(boolean attachedinTestSuite) {
		this.attachedinTestSuite = attachedinTestSuite;
	}

	public void filterArtifactTree(String searchValue) {
		List<Artifact> artifacts = this.getArtifactsData();
		for (Artifact artifact : artifacts) {
			if (artifact.getFile_type_enum() != MODULETYPE.Folder) {
				if (artifact.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
					artifact.setVisible(true);
				} else {
					artifact.setVisible(false);
				}
			}
		}
		this.refereshArtifacts();
	}
}
