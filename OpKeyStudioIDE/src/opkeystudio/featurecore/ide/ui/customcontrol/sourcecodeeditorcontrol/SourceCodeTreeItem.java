package opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;

public class SourceCodeTreeItem extends CustomTreeItem {
	public SourceCodeTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public SourceCodeTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	private FileNode fileNode;

	public FileNode getFileNode() {
		return fileNode;
	}

	public void setFileNode(FileNode fileNode) {
		this.fileNode = fileNode;
	}

}
