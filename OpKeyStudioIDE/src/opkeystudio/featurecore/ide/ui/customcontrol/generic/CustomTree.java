package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class CustomTree extends Tree {

	public CustomTree(Composite parent, int style) {
		super(parent, style);
	}

	public List<TreeItem> getAllTreeItems() {
		List<TreeItem> allitems = new ArrayList<TreeItem>();
		if (this.isDisposed()) {
			return allitems;
		}
		TreeItem items[] = this.getItems();
		for (TreeItem item : items) {
			allitems.add(item);
			allitems.addAll(getTreeItems(item));
		}
		return allitems;
	}

	public List<TreeItem> getTreeItems(TreeItem titem) {
		List<TreeItem> allitems = new ArrayList<TreeItem>();
		TreeItem items[] = titem.getItems();
		for (TreeItem item : items) {
			allitems.add(item);
			allitems.addAll(getTreeItems(item));
		}
		return allitems;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
