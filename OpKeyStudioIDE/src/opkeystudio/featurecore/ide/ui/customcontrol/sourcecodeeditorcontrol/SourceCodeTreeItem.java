package opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTreeItem;

public class SourceCodeTreeItem extends CustomTreeItem {
	private String fileName;
	private String bodyData;

	public SourceCodeTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public SourceCodeTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBodyData() {
		return bodyData;
	}

	public void setBodyData(String bodyData) {
		this.bodyData = bodyData;
	}

}
