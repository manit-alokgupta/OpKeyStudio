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
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class CodeViewTree extends CustomTree {
	private List<Artifact> artifacts = new ArrayList<Artifact>();

	public CodeViewTree(Composite parent, int style) {
		super(parent, style);
		init();
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
		this.artifacts = artifacts;
	}

	public List<Artifact> getArtifactsData() {
		return this.artifacts;
	}

	private void addIcon(ArtifactTreeItem artTreeItem) {
		if (artTreeItem.getArtifact() == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Folder) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Flow) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Suite) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.DataRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Component) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.CodedFunction) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		}
	}

	private void renderCodeViewTree(ArtifactTreeItem rootNode, List<Artifact> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				if (artifact.getParentid().equals(artifactId)) {
					artifact.setParentArtifact(rootNode.getArtifact());
					ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
					artitreeitem.setText(artifact.getVariableName()+".java");
					artitreeitem.setArtifact(artifact);
					addIcon(artitreeitem);
					renderCodeViewTree(artitreeitem, allArtifacts);
				}
			}
		}
	}

	private void refreshCodeViewTree(ArtifactTreeItem rootNode, List<Artifact> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				artifact.setParentArtifact(rootNode.getArtifact());
				if (artifact.isVisible()) {
					if (artifact.getParentid().equals(artifactId)) {
						ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
						artitreeitem.setText(artifact.getVariableName()+".java");
						artitreeitem.setArtifact(artifact);
						addIcon(artitreeitem);
						refreshCodeViewTree(artitreeitem, allArtifacts);
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
		rootNode.setText("Code WorkSpace");
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<Artifact> artifacts = new ArrayList<>();
		artifacts = new ArtifactApi().getAllArtificates();
		setArtifactsData(artifacts);
		List<ArtifactTreeItem> topMostNodes = new ArrayList<>();
		for (Artifact artifact : artifacts) {
			if (artifact.getParentid() == null) {
				ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
				artitreeitem.setText(artifact.getVariableName());
				artitreeitem.setArtifact(artifact);
				topMostNodes.add(artitreeitem);
				addIcon(artitreeitem);
			}
		}

		for (ArtifactTreeItem topMostNode : topMostNodes) {
			renderCodeViewTree(topMostNode, artifacts);
		}
		expandAll(rootNode);
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
		rootNode.setText("Project WorkSpace");
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
			}
		}

		for (ArtifactTreeItem topMostNode : topMostNodes) {
			refreshCodeViewTree(topMostNode, artifacts);
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
