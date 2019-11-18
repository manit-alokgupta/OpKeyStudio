package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ArtifactTree extends CustomTree {

	public ArtifactTree(Composite parent, int style) {
		super(parent, style);
		init();
		SystemRepository.getInstance().setArtifactTreeControl(this);
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
				ArtifactTree tree = (ArtifactTree) e.getSource();
				ArtifactTreeItem selectedTreeItem = (ArtifactTreeItem) tree.getSelection()[0];
				populateArtifact(selectedTreeItem);
			}

		});
	}

	private void populateArtifact(ArtifactTreeItem artifactTreeItem) {
		if (artifactTreeItem == null) {
			return;
		}
		if (artifactTreeItem.getArtifact() == null) {
			return;
		}
		if (artifactTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.ObjectRepository) {
			EPartService partService = Utilities.getInstance().getEpartService();
			MPart part = partService.createPart("opkeystudio.partdescriptor.objectRepository");
			part.setLabel(artifactTreeItem.getArtifact().getName());
			part.setTooltip(artifactTreeItem.getArtifact().getName());
			part.getTransientData().put("opkeystudio.artifactData", artifactTreeItem.getArtifact());
			partService.showPart(part, PartState.ACTIVATE);
		}
		if (artifactTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
			EPartService partService = Utilities.getInstance().getEpartService();
			MPart part = partService.createPart("opkeystudio.partdescriptor.testCaseViewer");
			part.setLabel(artifactTreeItem.getArtifact().getName());
			part.setTooltip(artifactTreeItem.getArtifact().getName());
			part.getTransientData().put("opkeystudio.artifactData", artifactTreeItem.getArtifact());
			partService.showPart(part, PartState.ACTIVATE);
		}
		if (artifactTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Component) {
			EPartService partService = Utilities.getInstance().getEpartService();
			MPart part = partService.createPart("opkeystudio.partdescriptor.testCaseViewer");
			part.setLabel(artifactTreeItem.getArtifact().getName());
			part.setTooltip(artifactTreeItem.getArtifact().getName());
			part.getTransientData().put("opkeystudio.artifactData", artifactTreeItem.getArtifact());
			partService.showPart(part, PartState.ACTIVATE);
		}
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
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Folder) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Flow) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/testcase.png"));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/object repo.png"));
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

	public void renderArtifacts() throws JsonParseException, JsonMappingException, SQLException, IOException {
		if (ServiceRepository.getInstance().getExportedDBFilePath() == null) {
			return;
		}
		this.removeAll();
		ArtifactTreeItem rootNode = new ArtifactTreeItem(this, 0);
		rootNode.setText("Project WorkSpace");
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<Artifact> artifacts = new ArtifactApi().getAllArtificates();
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
			renderAllArtifactTree(topMostNode, artifacts);
		}
		expandAll(rootNode);
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
			refreshAllArtifactTree(topMostNode, artifacts);
		}
		expandAll(rootNode);
	}

	public ArtifactTreeItem getSelectedArtifactTreeItem() {
		if (this.getSelection()[0] == null) {
			return null;
		}
		return (ArtifactTreeItem) this.getSelection()[0];
	}

	public Artifact getSelectedArtifact() {
		ArtifactTreeItem treeItem = getSelectedArtifactTreeItem();
		return treeItem.getArtifact();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
