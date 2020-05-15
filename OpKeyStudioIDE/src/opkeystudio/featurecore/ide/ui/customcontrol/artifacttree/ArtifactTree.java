package opkeystudio.featurecore.ide.ui.customcontrol.artifacttree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.ArtifactTranspilerAsync;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.ui.ArtifactTreeUI;
import opkeystudio.featurecore.ide.ui.ui.TestSuiteView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.repositories.repository.SystemRepository;
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;

public class ArtifactTree extends CustomTree {
	private TestSuiteView parentTestSuiteView;
	private boolean attachedinTestSuite = false;
	private List<Artifact> artifacts = new ArrayList<Artifact>();
	private ArtifactTreeUI parentArtifactTreeUI;
	private String artifactId = null;

	public ArtifactTree(Composite parent, int style, ArtifactTreeUI parentUI) {
		super(parent, style);
		this.setParentArtifactTreeUI(parentUI);
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
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Suite) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SUITE_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.DataRepository) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.Component) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));
		} else if (artTreeItem.getArtifact().getFile_type_enum() == Artifact.MODULETYPE.CodedFunction) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		}
	}

	private void renderAllArtifactTree(ArtifactTreeItem rootNode, List<Artifact> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (Artifact artifact : allArtifacts) {
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

	private void refreshAllArtifactTree(ArtifactTreeItem rootNode, List<Artifact> allArtifacts) {
		String artifactId = rootNode.getArtifact().getId();
		for (Artifact artifact : allArtifacts) {
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

	public void renderArtifacts(boolean fireGlobalEvent) {
		if (isAttachedinTestSuite() == false) {
			this.setEnabled(false);
			getParentArtifactTreeUI().getSearchBox().setEnabled(false);
			getParentArtifactTreeUI().getClearSearchBoxButton().setEnabled(false);
		}
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
		addIcon(rootNode);
		List<Artifact> artifacts = new ArrayList<>();
		if (isAttachedinTestSuite()) {
			artifacts = GlobalLoader.getInstance().getAllArtifactByType("Flow");
			artifacts.addAll(GlobalLoader.getInstance().getAllArtifactByType("Folder"));
		} else {
			artifacts = GlobalLoader.getInstance().getAllArtifacts();
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
			}
		}

		for (ArtifactTreeItem topMostNode : topMostNodes) {
			renderAllArtifactTree(topMostNode, artifacts);
		}
		if (isAttachedinTestSuite()) {
			rootNode.setExpanded(true);
			this.setRedraw(true);
		} else {
			expandAll(rootNode);
		}
		if (isAttachedinTestSuite() == false) {
			ArtifactTranspiler.getInstance().setPackageProperties();
			new ArtifactTranspilerAsync().executeArtifactTranspilerAsync(this.getShell());
		}
		selectTreeItem(rootNode);
		if (fireGlobalEvent) {
			OpKeyGlobalLoadListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
		}
		if (isAttachedinTestSuite() == false) {
			this.setEnabled(true);
			getParentArtifactTreeUI().getSearchBox().setEnabled(true);
			getParentArtifactTreeUI().getClearSearchBoxButton().setEnabled(true);
			highlightArtifact(getSelectedArtifactId());
		}
	}

	public void highlightArtifact(String artifactId) {
		if (artifactId == null) {
			if (this.getItemCount() > 0) {
				ArtifactTreeItem item = (ArtifactTreeItem) this.getItem(0);
				if (item == null) {
					return;
				}
				highLightTreeItem(item);
				return;
			}
		}
		List<TreeItem> titems = this.getAllTreeItems();
		for (TreeItem item : titems) {
			ArtifactTreeItem ati = (ArtifactTreeItem) item;
			if (ati.getArtifact() != null) {
				if (ati.getArtifact().getId().equals(artifactId)) {
					highLightTreeItem(ati);
					break;
				}
			}
		}
	}

	private void highLightTreeItem(ArtifactTreeItem item) {
		try {
			this.setSelection(item);
			this.notifyListeners(SWT.FocusIn, null);
			this.notifyListeners(SWT.MouseUp, null);
			this.notifyListeners(SWT.MouseDown, null);
			this.notifyListeners(SWT.Selection, null);
		} catch (Exception e) {
			// TODO: handle exception
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
		selectTreeItem(rootNode);
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
		if (artifacts.size() == 0) {
			return;
		}
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

	public ArtifactTreeUI getParentArtifactTreeUI() {
		return parentArtifactTreeUI;
	}

	public void setParentArtifactTreeUI(ArtifactTreeUI parentArtifactTreeUI) {
		this.parentArtifactTreeUI = parentArtifactTreeUI;
	}

	public String getSelectedArtifactId() {
		return artifactId;
	}

	public void setSelectedArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
}
