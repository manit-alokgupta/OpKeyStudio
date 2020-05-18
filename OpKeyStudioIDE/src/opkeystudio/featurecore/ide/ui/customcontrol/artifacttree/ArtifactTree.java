package opkeystudio.featurecore.ide.ui.customcontrol.artifacttree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.ArtifactTranspilerAsync;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;

public class ArtifactTree extends CustomTree {
	private TestSuiteView parentTestSuiteView;
	private boolean attachedinTestSuite = false;
	private List<ArtifactDTO> artifacts = new ArrayList<ArtifactDTO>();

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
		ArtifactDTO artifact = artifactTreeItem.getArtifact();
		Utilities.getInstance().openArtifacts(artifact);
	}

	public void setArtifactsData(List<ArtifactDTO> artifacts) {
		this.artifacts = artifacts;
	}

	public List<ArtifactDTO> getArtifactsData() {
		return this.artifacts;
	}

	private void addIcon(ArtifactTreeItem artTreeItem) {
		if (artTreeItem.getArtifact() == null) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.Folder) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.Flow) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.Suite) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SUITE_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.DataRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.Component) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == ArtifactDTO.MODULETYPE.CodedFunction) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		}
	}

	private void renderAllArtifactTree(ArtifactTreeItem rootNode, List<ArtifactDTO> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (ArtifactDTO artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				if (artifact.getParentid().equals(artifactId)) {
					artifact.setParentArtifact(rootNode.getArtifact());
					ArtifactTreeItem artitreeitem = new ArtifactTreeItem(rootNode, 0);
					artitreeitem.setText(artifact.getName());
					artitreeitem.setArtifact(artifact);
					addIcon(artitreeitem);
					renderAllArtifactTree(artitreeitem, allArtifacts);
				}
			}
		}
	}

	private void refreshAllArtifactTree(ArtifactTreeItem rootNode, List<ArtifactDTO> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (ArtifactDTO artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				artifact.setParentArtifact(rootNode.getArtifact());
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
		GlobalLoader.getInstance().initAllArguments();
		this.removeAll();
		ArtifactTreeItem rootNode = new ArtifactTreeItem(this, 0);
		if (isAttachedinTestSuite()) {
			rootNode.setText("Gherkin and Test Case");
		} else {
			rootNode.setText("Project WorkSpace");
		}
		rootNode.setExpanded(true);
		addIcon(rootNode);
		List<ArtifactDTO> artifacts = new ArrayList<>();
		if (isAttachedinTestSuite()) {
			artifacts = GlobalLoader.getInstance().getAllArtifactByType("Flow");
		} else {
			artifacts = GlobalLoader.getInstance().getAllArtifacts();
		}
		setArtifactsData(artifacts);
		List<ArtifactTreeItem> topMostNodes = new ArrayList<>();
		for (ArtifactDTO artifact : artifacts) {
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
		new ArtifactTranspilerAsync().executeArtifactTranspilerAsync(this.getShell());
		OpKeyGlobalLoadListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
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
		try {
			getParent().setCursor(new Cursor(Display.getCurrent(),SWT.CURSOR_WAIT));
			this.removeAll();
			ArtifactTreeItem rootNode = new ArtifactTreeItem(this, 0);
			if (isAttachedinTestSuite()) {
				rootNode.setText("Gherkin and Test Case");
			} else {
				rootNode.setText("Project WorkSpace");
			}
			rootNode.setExpanded(true);
			addIcon(rootNode);
			List<ArtifactDTO> artifacts = getArtifactsData();
			List<ArtifactTreeItem> topMostNodes = new ArrayList<>();
			for (ArtifactDTO artifact : artifacts) {
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
		finally {
			getParent().setCursor(new Cursor(Display.getCurrent(),SWT.CURSOR_ARROW));
		}
		
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

	public ArtifactDTO getSelectedArtifact() {
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
		List<ArtifactDTO> artifacts = this.getArtifactsData();
		for (ArtifactDTO artifact : artifacts) {
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
