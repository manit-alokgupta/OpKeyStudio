package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.io.IOException;
import java.util.ArrayList;
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
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class KeywordsTree extends CustomTree {
	private TestCaseView parentTestCaseView;
	private KeywordsTree thisTable = this;
	private ArtifactTreeItem artifactTreeItem;

	public KeywordsTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		init();
		initKeywords();
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
				FlowStep flowStep = new FlowMaker().getFlowStepDTO(getParentTestCaseView().getArtifact(),
						selectedFlowStep, getSelectedKeyword(), artifact.getId(),
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
				TreeItem[] selectedItems = getSelection();
				if (selectedItems == null) {
					return;
				}
				if (selectedItems.length == 0) {
					return;
				}

				CustomTreeItem selectedKeywordItem = (CustomTreeItem) getSelection()[0];
				if (selectedKeywordItem == null) {
					return;
				}

				Keyword keyWord = (Keyword) selectedKeywordItem.getControlData();
				System.out.println(keyWord.getKeywordname());
				FlowStepTable flowStepTable = getParentTestCaseView().getFlowStepTable();
				if (flowStepTable != null) {
					FlowStep flowStep = flowStepTable.getSelectedFlowStep();
					flowStep.setKeyword(keyWord);
					flowStep.setKeywordid(keyWord.getKeywordid());
					flowStep.setModified(true);
					getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
					getParentTestCaseView().toggleSaveButton(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	public Keyword getSelectedKeyword() {
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

			Keyword keyWord = (Keyword) selectedKeywordItem.getControlData();

			return keyWord;
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
		try {
			List<Artifact> artifacts = new ArtifactApi().getAllArtificatesByType("Component");
			CustomTreeItem cti = new CustomTreeItem(this, 0);
			cti.setText("Function Library");
			cti.setExpanded(true);
			for (Artifact artifact : artifacts) {
				CustomTreeItem keywItem = new CustomTreeItem(cti, 0);
				keywItem.setText(artifact.getName());
				keywItem.setControlData(artifact);
				addIcon(keywItem);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initSeriveRepo() {

	}

	public void initCodedFunction() {

	}
}
