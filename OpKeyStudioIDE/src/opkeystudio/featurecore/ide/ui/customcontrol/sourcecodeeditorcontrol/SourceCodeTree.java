package opkeystudio.featurecore.ide.ui.customcontrol.sourcecodeeditorcontrol;

import org.eclipse.swt.widgets.Composite;

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
}
