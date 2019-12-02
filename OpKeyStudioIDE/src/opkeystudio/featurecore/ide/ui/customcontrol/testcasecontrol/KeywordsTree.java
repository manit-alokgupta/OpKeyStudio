package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.featurecore.ide.ui.ui.TestCaseView;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class KeywordsTree extends CustomTree {
	private TestCaseView parentTestCaseView;

	public KeywordsTree(Composite parent, int style, TestCaseView testCaseView) {
		super(parent, style);
		this.setParentTestCaseView(testCaseView);
		initKeywords();
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
		for(TreeItem item:items) {
			if(item.getText().toLowerCase().contains(query.toLowerCase())) {
				//item.
			}
		}
	//	List<TreeItem> treeItems = getAllItems(treeItem);
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
}
