package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.drapi.DataRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class GenericTree extends CustomTree {
	public enum TREETYPE {
		DATAREPOSITORYTREE
	};

	private TestCaseView parentTestCaseView;
	private GenericTree thisTable = this;
	private ArtifactTreeItem artifactTreeItem;
	private boolean treeExtended = false;
	private TREETYPE treeType;

	public GenericTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		init();
		initKeywords();
	}

	public GenericTree(Composite parent, int style, TestCaseView testCaseView, TREETYPE treetype) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		this.setTreeType(treetype);
		initByTreeType();
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
		MenuItem updateMenuItem = new MenuItem(menu, 0);
		updateMenuItem.setText("Update");
		this.setMenu(menu);

		addMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = getParentTestCaseView().getArtifact();
				FlowStep selectedFlowStep = getParentTestCaseView().getFlowStepTable().getSelectedFlowStep();
				Object selectedData = getSelectedData();
				if (selectedData instanceof Artifact) {
					try {
						FlowStep flowStep = new FlowMaker().getFlowStepDTOWithFunctionLibray(artifact, selectedFlowStep,
								(Artifact) selectedData, artifact.getId(),
								getParentTestCaseView().getFlowStepTable().getFlowStepsData());
						getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
						getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
						getParentTestCaseView().toggleSaveButton(true);
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				FlowStep flowStep = new FlowMaker().getFlowStepDTO(getParentTestCaseView().getArtifact(),
						selectedFlowStep, (Keyword) getSelectedData(), artifact.getId(),
						getParentTestCaseView().getFlowStepTable().getFlowStepsData());
				getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);
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
					try {
						FlowStep flowStep = new FlowMaker().getFlowStepDTOWithFunctionLibray(artifact, selectedFlowStep,
								(Artifact) selectedData, artifact.getId(),
								getParentTestCaseView().getFlowStepTable().getFlowStepsData());
						getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
						getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
						getParentTestCaseView().toggleSaveButton(true);
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				FlowStep flowStep = new FlowMaker().getFlowStepDTO(getParentTestCaseView().getArtifact(),
						selectedFlowStep, (Keyword) getSelectedData(), artifact.getId(),
						getParentTestCaseView().getFlowStepTable().getFlowStepsData());
				getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
				getParentTestCaseView().toggleSaveButton(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
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
					if (treeItem_1.getText().equals(keyword.getKeywordname())) {
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
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Folder) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/folder.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Flow) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testcase.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.ObjectRepository) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/or.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Suite) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/testsuite.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.DataRepository) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/note.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.Component) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/fl.png"));
			} else if (artifact.getFile_type_enum() == Artifact.MODULETYPE.CodedFunction) {
				artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_icons/fl.png"));
			}
		}
		if (artTreeItem.getControlData() instanceof DRColumnAttributes) {
			artTreeItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase.gif"));
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

	public void initKeywords() {
		this.removeAll();
		Set<String> groupNames = KeywordManager.getInstance().getAllGroupNames();
		for (String groupName : groupNames) {
			CustomTreeItem cti = new CustomTreeItem(this, 0);
			cti.setText(groupName);
			List<Keyword> keywords = KeywordManager.getInstance().getKeywordGroup(groupName);
			for (Keyword keyword : keywords) {
				CustomTreeItem keywItem = new CustomTreeItem(cti, 0);
				keywItem.setText(keyword.getKeywordname());
				keywItem.setControlData(keyword);
			}
		}
	}

	public void initFunctionLibraries() {
		this.removeAll();
		List<Artifact> artifacts = new ArtifactApi().getAllArtificatesByType("Component");
		CustomTreeItem rootNode = new CustomTreeItem(this, 0);
		rootNode.setText("Function Library");
		rootNode.setExpanded(true);
		for (Artifact artifact : artifacts) {
			CustomTreeItem keywItem = new CustomTreeItem(rootNode, 0);
			keywItem.setText(artifact.getName());
			keywItem.setControlData(artifact);
			addIcon(keywItem);
		}
		expandAll(rootNode);
	}

	public void initSeriveRepo() {

	}

	public void initCodedFunction() {

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
		addIcon(rootNode);
		List<Artifact> artifacts = new ArtifactApi().getAllArtificatesByType("DataRepository");
		for (Artifact drArtifact : artifacts) {
			CustomTreeItem drNode = new CustomTreeItem(rootNode, 0);
			drNode.setText(drArtifact.getName());
			drNode.setControlData(drArtifact);
			addIcon(drNode);
			List<DRColumnAttributes> drColumnAttributes = new DataRepositoryApi()
					.getAllColumnsValues(drArtifact.getId());
			for (DRColumnAttributes drColumn : drColumnAttributes) {
				CustomTreeItem drColumnNode = new CustomTreeItem(drNode, 0);
				drColumnNode.setText(drColumn.getName());
				drColumnNode.setControlData(drColumn);
				addIcon(drColumnNode);
			}
		}
		expandAll(rootNode);
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
}
