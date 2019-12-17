package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.featurecore.ide.ui.ui.FLView;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class KeywordsTree extends CustomTree {
	private TestCaseView parentTestCaseView;
	private FLView parentFLView;
	private KeywordsTree thisTable = this;
	private FlowStep flowStep;
	private ArtifactTreeItem artifactTreeItem;

	public KeywordsTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		init();
		initKeywords();
	}

	public KeywordsTree(Composite parent, int style, FLView parentView) {
		super(parent, style);
		init();
		this.setParentFLView(parentView);
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
				System.out.println(selectedKeyword().getKeywordname());
				FlowStep flowStep = new FlowMaker().getFlowStepDTO(selectedKeyword(), "");
				getParentTestCaseView().getFlowStepTable().getFlowStepsData().add(flowStep);
				getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
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
//				if (artifactTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.DataRepository) {
//				FlowStepTable flowStepTableFL = getParentFLView().getFlowStepTable();
//				if (flowStepTableFL != null) {
//					FlowStep flowStep = flowStepTableFL.getSelectedFlowStep();
//					flowStep.setKeyword(keyWord);
//					flowStep.setKeywordid(keyWord.getKeywordid());
//					flowStep.setModified(true);
//					getParentFLView().getFlowStepTable().refreshFLFlowSteps();
//					getParentFLView().toggleSaveButton(true);
//
//				}
//				}

//				if (flowStep.isIstestcase()) {
				FlowStepTable flowStepTable = getParentTestCaseView().getFlowStepTable();
				if (flowStepTable != null) {
					flowStep = flowStepTable.getSelectedFlowStep();
					flowStep.setKeyword(keyWord);
					flowStep.setKeywordid(keyWord.getKeywordid());
					flowStep.setModified(true);
					getParentTestCaseView().getFlowStepTable().refreshFlowSteps();
					getParentTestCaseView().toggleSaveButton(true);

				}
//				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	public Keyword selectedKeyword() {
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

	private List<TreeItem> getAllItems(TreeItem treeItem) {
		List<TreeItem> allTreeItems = new ArrayList<TreeItem>();
		TreeItem[] items = treeItem.getItems();
		for (TreeItem item : items) {
			allTreeItems.add(item);
			allTreeItems.addAll(getAllItems(item));
		}
		return allTreeItems;
	}

	public void filterDataInTree(String query) {
		TreeItem[] items = this.getItems();
		for (TreeItem item : items) {
			if (item.getText().toLowerCase().contains(query.trim().toLowerCase())) {
//				 item.
			}
		}
		// List<TreeItem> treeItems = getAllItems(treeItem);
	}

	public void initKeywords() {
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

	public FLView getParentFLView() {
		return parentFLView;
	}

	public void setParentFLView(FLView parentFLView) {
		this.parentFLView = parentFLView;
	}
}
