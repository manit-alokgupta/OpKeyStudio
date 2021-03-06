package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.featurecore.ide.ui.ui.superview.events.GlobalLoadListener;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;

public class GenericTree extends CustomTree {
	public enum TREETYPE {
		DATAREPOSITORYTREE
	};

	private TestCaseView parentTestCaseView;
	private boolean treeExtended = false;
	private TREETYPE treeType;
	private boolean keywordTree = false;
	private boolean flTree = false;
	private boolean cflTree = false;

	public GenericTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		init();
		initKeywords("");
		addGlobalLoadListener();
	}

	public GenericTree(Composite parent, int style, TestCaseView testCaseView, TREETYPE treetype) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		this.setTreeType(treetype);
		initByTreeType();
		intDRGlobalListener();
	}

	private void addGlobalLoadListener() {
		this.addOpKeyGlobalLoadListener(new GlobalLoadListener() {

			@Override
			public void handleGlobalEvent() {
				if (isFlTree()) {
					initFunctionLibraries("");
					return;
				}
				if (isCflTree()) {
					initCodedFunctionLibraries("");
					return;
				}
			}
		});
	}

	private void intDRGlobalListener() {
		this.addOpKeyGlobalLoadListener(new GlobalLoadListener() {

			@Override
			public void handleGlobalEvent() {
				initByTreeType();

			}
		});
	}

	private void initByTreeType() {
		if (getTreeType() == TREETYPE.DATAREPOSITORYTREE) {
			initDataRepositories();
			initDREvents();
		}
	}

	private void init() {
		Menu menu = new Menu(this);
		MenuItem addMenuItem = new MenuItem(menu, 0);
		addMenuItem.setText("Add");
		addMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ICON));
		MenuItem updateMenuItem = new MenuItem(menu, 0);
		updateMenuItem.setText("Update");
		updateMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.UPDATE_ICON));
		this.setMenu(menu);

		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Object selectedData = getSelectedData();
				if (selectedData == null) {
					addMenuItem.setEnabled(false);
					updateMenuItem.setEnabled(false);
					return;
				}
				if (selectedData instanceof Artifact) {
					Artifact artifact = (Artifact) selectedData;
					if (artifact.getFile_type_enum() == MODULETYPE.Folder) {
						addMenuItem.setEnabled(false);
						updateMenuItem.setEnabled(false);
						return;
					}
				}
				addMenuItem.setEnabled(true);
				updateMenuItem.setEnabled(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				addStepInTable();
			}
		});

		addMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addStepInTable();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		updateMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = getParentTestCaseView().getArtifact();
				FlowStep selectedFlowStep = getParentTestCaseView().getFlowStepTable().getSelectedFlowStep();
				if (selectedFlowStep != null) {
					selectedFlowStep.setDeleted(true);
				}
				Object selectedData = getSelectedData();
				if (selectedData instanceof Artifact) {
					Artifact selectedArtifact = (Artifact) selectedData;
					if (selectedArtifact.getFile_type_enum() == MODULETYPE.Component) {
						if (artifact.getId().equals(selectedArtifact.getId())) {
							new MessageDialogs().openErrorDialog("OpKey",
									"Function Library Recursion Call Not Allowed");
							refreshAllDataTree();
							return;
						}
						FlowStep flowStep = new FlowMaker().getFlowStepDTOWithFunctionLibray(artifact, selectedFlowStep,
								selectedArtifact, artifact.getId(),
								getParentTestCaseView().getFlowStepTable().getFlowStepsData());
						getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
						getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
						getParentTestCaseView().toggleSaveButton(true);
						refreshAllDataTree();
						return;
					}

					if (selectedArtifact.getFile_type_enum() == MODULETYPE.CodedFunction) {
						FlowStep flowStep = new FlowMaker().getFlowStepDTOWithCodedFunctionLibray(artifact,
								selectedFlowStep, selectedArtifact, artifact.getId(),
								getParentTestCaseView().getFlowStepTable().getFlowStepsData());
						getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
						getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
						getParentTestCaseView().toggleSaveButton(true);
						refreshAllDataTree();
						return;
					}
				}
				FlowStep flowStep = new FlowMaker().getFlowStepDTO(getParentTestCaseView().getArtifact(),
						selectedFlowStep, (Keyword) getSelectedData(), artifact.getId(),
						getParentTestCaseView().getFlowStepTable().getFlowStepsData());
				getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
				refreshAllDataTree();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private void addStepInTable() {
		Artifact artifact = getParentTestCaseView().getArtifact();
		FlowStep selectedFlowStep = getParentTestCaseView().getFlowStepTable().getSelectedFlowStep();
		if (selectedFlowStep != null) {
			boolean isblank = true;
			if (selectedFlowStep.getKeyword() != null) {
				isblank = false;
			}
			if (selectedFlowStep.getFunctionLibraryComponent() != null) {
				isblank = false;
			}
			if (selectedFlowStep.getCodedFunctionArtifact() != null) {
				isblank = false;
			}
			if (isblank) {
				selectedFlowStep.setModified(false);
				selectedFlowStep.setAdded(false);
				selectedFlowStep.setDeleted(true);
			}
		}
		Object selectedData = getSelectedData();
		if (selectedData instanceof Artifact) {
			Artifact selectedArtifact = (Artifact) selectedData;
			if (selectedArtifact.getFile_type_enum() == MODULETYPE.Component) {
				if (artifact.getId().equals(selectedArtifact.getId())) {
					new MessageDialogs().openErrorDialog("OpKey", "Function Library Recursion Call Not Allowed");
					refreshAllDataTree();
					return;
				}
				FlowStep flowStep = new FlowMaker().getFlowStepDTOWithFunctionLibray(artifact, selectedFlowStep,
						selectedArtifact, artifact.getId(),
						getParentTestCaseView().getFlowStepTable().getFlowStepsData());
				getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
				CustomNotificationUtil.openInformationNotification("OpKey",
						flowStep.getFunctionLibraryComponent().getName() + " added!");
				refreshAllDataTree();
				return;
			}

			if (selectedArtifact.getFile_type_enum() == MODULETYPE.CodedFunction) {
				FlowStep flowStep = new FlowMaker().getFlowStepDTOWithCodedFunctionLibray(artifact, selectedFlowStep,
						selectedArtifact, artifact.getId(),
						getParentTestCaseView().getFlowStepTable().getFlowStepsData());
				getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
				CustomNotificationUtil.openInformationNotification("OpKey",
						flowStep.getCodedFunctionArtifact().getName() + " added!");
				refreshAllDataTree();
				return;
			}

		}
		FlowStep flowStep = new FlowMaker().getFlowStepDTO(getParentTestCaseView().getArtifact(), selectedFlowStep,
				(Keyword) getSelectedData(), artifact.getId(),
				getParentTestCaseView().getFlowStepTable().getFlowStepsData());
		getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
		getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
		getParentTestCaseView().toggleSaveButton(true);
		CustomNotificationUtil.openInformationNotification("OpKey", flowStep.getKeyword().getName() + " added!");
		refreshAllDataTree();

	}

	private void refreshAllDataTree() {
		getParentTestCaseView().getSearchBox().setText("");
		if (isKeywordTree()) {
			initKeywords("");
		}
		if (isFlTree()) {
			initFunctionLibraries("");
		}
		if (isCflTree()) {
			initCodedFunctionLibraries("");
		}
	}

	public Object getSelectedData() {
		TreeItem[] selectedItems = getSelection();
		if (selectedItems == null) {
			return null;
		}
		if (selectedItems.length == 0) {
			return null;
		}

		CustomTreeItem selectedKeywordItem = (CustomTreeItem) getSelection()[0];
		if (selectedKeywordItem == null) {
			return null;
		}

		else {
			return selectedKeywordItem.getControlData();
		}
	}

	public TestCaseView getParentTestCaseView() {
		return parentTestCaseView;
	}

	public void setParentTestCaseView(TestCaseView parentTestCaseView) {
		this.parentTestCaseView = parentTestCaseView;
	}

	public void selectKeyword(Keyword keyword) {
		TreeItem[] treeItems = this.getItems();
		for (TreeItem treeItem : treeItems) {
			if (treeItem.getText().equals(keyword.getPluginName())) {
				treeItem.setExpanded(true);
				TreeItem[] treeItems_1 = treeItem.getItems();
				for (TreeItem treeItem_1 : treeItems_1) {
					if (treeItem_1.getText().equals(keyword.getName())) {
						this.setSelection(treeItem_1);
						this.setFocus();
					}
				}
			}
		}
	}

	private void addIcon(CustomTreeItem artTreeItem) {
		if (artTreeItem.getControlData() instanceof Artifact) {
			Artifact artifact = (Artifact) artTreeItem.getControlData();
			if (artifact == null) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Folder) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Flow) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Suite) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SUITE_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.DataRepository) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Component) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.CodedFunction) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
			}
		}
		if (artTreeItem.getControlData() instanceof DRColumnAttributes) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_COLUMN_ICON));
		}
	}

	public void expandAll(CustomTreeItem treeItem) {
		treeItem.setExpanded(true);
		TreeItem items[] = treeItem.getItems();
		for (TreeItem item : items) {
			item.setExpanded(true);
			expandAll((CustomTreeItem) item);
		}
		this.setRedraw(true);
	}

	public void initKeywords(String keywordName) {
		setFlTree(false);
		setCflTree(false);
		setKeywordTree(true);
		this.removeAll();
		Set<String> groupNames = KeywordManager.getInstance().getAllGroupNames();
		for (String groupName : groupNames) {
			CustomTreeItem cti = new CustomTreeItem(this, 0);
			cti.setText(groupName);
			cti.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DARK_GREEN_FLAG_ICON));
			List<Keyword> keywords = KeywordManager.getInstance().getKeywordGroup(groupName);
			for (Keyword keyword : keywords) {
				if (keyword.getName().toLowerCase().contains(keywordName.toLowerCase())) {
					CustomTreeItem keywItem = new CustomTreeItem(cti, 0);
					keywItem.setText(keyword.getName());
					keywItem.setControlData(keyword);
					keywItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.GREEN_FLAG_ICON));
				}
			}
			if (!keywordName.trim().isEmpty()) {
				expandAll(cti);
			}
		}
	}

	public void initFunctionLibraries(String flName) {
		setFlTree(true);
		setKeywordTree(false);
		setCflTree(false);
		this.removeAll();
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifactByType("Component");
		artifacts.addAll(GlobalLoader.getInstance().getAllArtifactByType("Folder"));

		List<Artifact> filteredArtifacts = new ArrayList<Artifact>();
		for (Artifact artifact : artifacts) {
			if (artifact.getFile_type_enum() == MODULETYPE.Folder) {
				filteredArtifacts.add(artifact);
			} else {
				if (artifact.getName().toLowerCase().contains(flName.toLowerCase())) {
					filteredArtifacts.add(artifact);
				}
			}
		}
		CustomTreeItem rootNode = new CustomTreeItem(this, 0);
		rootNode.setText("Function Library");
		rootNode.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));

		List<CustomTreeItem> topMostNodes = new ArrayList<>();
		for (Artifact artifact : filteredArtifacts) {
			if (artifact.getParentid() == null) {
				CustomTreeItem artitreeitem = new CustomTreeItem(rootNode, 0);
				artitreeitem.setText(artifact.getName());
				artitreeitem.setControlData(artifact);
				topMostNodes.add(artitreeitem);
				addIcon(artitreeitem);
			}
		}
		for (CustomTreeItem topMostNode : topMostNodes) {
			renderAllArtifactTree(topMostNode, filteredArtifacts);
		}

		rootNode.setExpanded(true);
		this.setRedraw(true);
		// expandAll(rootNode);
	}

	private void renderAllArtifactTree(CustomTreeItem rootNode, List<Artifact> allArtifacts) {
		Artifact nodeartifact = (Artifact) rootNode.getControlData();
		String artifactId = nodeartifact.getId();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				if (artifact.getParentid().equals(artifactId)) {
					CustomTreeItem artitreeitem = new CustomTreeItem(rootNode, 0);
					artitreeitem.setText(artifact.getName());
					artitreeitem.setControlData(artifact);
					addIcon(artitreeitem);
					renderAllArtifactTree(artitreeitem, allArtifacts);
				}
			}
		}
	}

	public void initCodedFunctionLibraries(String flName) {
		setFlTree(false);
		setKeywordTree(false);
		setCflTree(true);
		this.removeAll();
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifactByType("CodedFunction");
		artifacts.addAll(GlobalLoader.getInstance().getAllArtifactByType("Folder"));

		List<Artifact> filteredArtifacts = new ArrayList<Artifact>();
		for (Artifact artifact : artifacts) {
			if (artifact.getFile_type_enum() == MODULETYPE.Folder) {
				filteredArtifacts.add(artifact);
			} else {
				if (artifact.getName().toLowerCase().contains(flName.toLowerCase())) {
					filteredArtifacts.add(artifact);
				}
			}
		}

		CustomTreeItem rootNode = new CustomTreeItem(this, 0);
		rootNode.setText("Coded Function Library");
		rootNode.setExpanded(true);
		rootNode.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		List<CustomTreeItem> topMostNodes = new ArrayList<>();
		for (Artifact artifact : filteredArtifacts) {
			if (artifact.getParentid() == null) {
				CustomTreeItem artitreeitem = new CustomTreeItem(rootNode, 0);
				artitreeitem.setText(artifact.getName());
				artitreeitem.setControlData(artifact);
				topMostNodes.add(artitreeitem);
				addIcon(artitreeitem);
			}
		}
		for (CustomTreeItem topMostNode : topMostNodes) {
			renderAllArtifactTree(topMostNode, filteredArtifacts);
		}

		rootNode.setExpanded(true);
		this.setRedraw(true);

		// expandAll(rootNode);
	}

	private void initDREvents() {
		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Object selectedData = getSelectedData();
				if (selectedData instanceof DRColumnAttributes) {
					DRColumnAttributes drcolumnAttribute = (DRColumnAttributes) selectedData;
					FlowInputArgument selectedFlowInputArgument = getParentTestCaseView().getInputDataTable()
							.getSelectedFlowInputArgument();
					if (selectedFlowInputArgument == null) {
						return;
					}
					new FlowApiUtilities().setFlowInputData(getParentTestCaseView().getArtifact(),
							selectedFlowInputArgument, drcolumnAttribute.getColumn_id(),
							DataSource.ValueFromDataRepository);
					selectedFlowInputArgument.setModified(true);
					getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
					getParentTestCaseView().toggleSaveButton(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void initDataRepositories() {
		this.removeAll();
		CustomTreeItem rootNode = new CustomTreeItem(this, 0);
		rootNode.setText("Data Repositories");
		rootNode.setExpanded(true);
		rootNode.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		addIcon(rootNode);
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifactByType("DataRepository");
		artifacts.addAll(GlobalLoader.getInstance().getAllArtifactByType("Folder"));
		List<CustomTreeItem> topMostNodes = new ArrayList<>();
		for (Artifact artifact : artifacts) {
			if (artifact.getParentid() == null) {
				CustomTreeItem artitreeitem = new CustomTreeItem(rootNode, 0);
				artitreeitem.setText(artifact.getName());
				artitreeitem.setControlData(artifact);
				topMostNodes.add(artitreeitem);
				addIcon(artitreeitem);

				if (artifact.getFile_type_enum() == MODULETYPE.DataRepository) {
					List<DRColumnAttributes> drColumnAttributes = new DataRepositoryApi()
							.getAllColumnsValues(artifact.getId());
					for (DRColumnAttributes drColumn : drColumnAttributes) {
						CustomTreeItem drColumnNode = new CustomTreeItem(artitreeitem, 0);
						drColumnNode.setText(drColumn.getName());
						drColumnNode.setControlData(drColumn);
						addIcon(drColumnNode);
					}
				}
			}
		}
		for (CustomTreeItem topMostNode : topMostNodes) {
			renderAllDRArtifactTree(topMostNode, artifacts);
		}
		expandAll(rootNode);
	}

	private void renderAllDRArtifactTree(CustomTreeItem rootNode, List<Artifact> allArtifacts) {
		Artifact nodeartifact = (Artifact) rootNode.getControlData();
		String artifactId = nodeartifact.getId();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getParentid() != null) {
				if (artifact.getParentid().equals(artifactId)) {
					CustomTreeItem artitreeitem = new CustomTreeItem(rootNode, 0);
					artitreeitem.setText(artifact.getName());
					artitreeitem.setControlData(artifact);
					addIcon(artitreeitem);
					if (artifact.getFile_type_enum() == MODULETYPE.DataRepository) {
						List<DRColumnAttributes> drColumnAttributes = new DataRepositoryApi()
								.getAllColumnsValues(artifact.getId());
						for (DRColumnAttributes drColumn : drColumnAttributes) {
							CustomTreeItem drColumnNode = new CustomTreeItem(artitreeitem, 0);
							drColumnNode.setText(drColumn.getName());
							drColumnNode.setControlData(drColumn);
							addIcon(drColumnNode);
						}
					}
					renderAllDRArtifactTree(artitreeitem, allArtifacts);
				}
			}
		}
	}

	public boolean isTreeExtended() {
		return treeExtended;
	}

	public void setTreeExtended(boolean treeExtended) {
		this.treeExtended = treeExtended;
	}

	public TREETYPE getTreeType() {
		return treeType;
	}

	public void setTreeType(TREETYPE treeType) {
		this.treeType = treeType;
	}

	public boolean isFlTree() {
		return flTree;
	}

	public void setFlTree(boolean flTree) {
		this.flTree = flTree;
	}

	public boolean isKeywordTree() {
		return keywordTree;
	}

	public void setKeywordTree(boolean keywordTree) {
		this.keywordTree = keywordTree;
	}

	public boolean isCflTree() {
		return cflTree;
	}

	public void setCflTree(boolean cflTree) {
		this.cflTree = cflTree;
	}
}
