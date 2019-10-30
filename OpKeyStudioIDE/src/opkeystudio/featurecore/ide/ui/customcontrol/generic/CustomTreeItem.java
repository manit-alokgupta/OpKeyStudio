package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class CustomTreeItem extends TreeItem {
	private Object data;

	public CustomTreeItem(Tree parent, int style) {
		super(parent, style);
	}

	public CustomTreeItem(TreeItem parent, int style) {
		super(parent, style);
	}

	public Object getOpKeyData() {
		return this.data;
	}

	public void setOpKeyData(Object data) {
		this.data = data;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
