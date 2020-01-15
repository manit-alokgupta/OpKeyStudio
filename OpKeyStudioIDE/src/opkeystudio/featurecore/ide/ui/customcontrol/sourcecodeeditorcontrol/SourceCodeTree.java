package opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTree;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;

public class SourceCodeTree extends CustomTree {

	public SourceCodeTree(Composite parent, int style) {
		super(parent, style);
	}

	public FileNode getSelectedFileNode() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		SourceCodeTreeItem scti = (SourceCodeTreeItem) this.getSelection()[0];
		if (scti.getFileNode() == null) {
			return null;
		}
		return scti.getFileNode();
	}

	private ArrayList<TreeItem> getAllTreeItems(TreeItem item) {
		ArrayList<TreeItem> itemslist = new ArrayList<TreeItem>();
		TreeItem[] items = item.getItems();
		for (TreeItem treeitem : items) {
			itemslist.add(treeitem);
			itemslist.addAll(getAllTreeItems(treeitem));
		}
		return itemslist;
	}

	public List<FileNode> getAllFileNodes() {
		List<FileNode> allNodes = new ArrayList<FileNode>();
		TreeItem[] items = this.getItems();
		for(TreeItem item:items) {
			ArrayList<TreeItem> treeItems=getAllTreeItems(item);
			for (TreeItem treeItem : treeItems) {
				SourceCodeTreeItem scti = (SourceCodeTreeItem) treeItem;
				if (scti.getFileNode() != null) {
					allNodes.add(scti.getFileNode());
				}
			}
		}
		return allNodes;
	}
}
