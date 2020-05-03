package opkeystudio.featurecore.ide.ui.customcontrol.generic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.ui.superview.events.GlobalLoadListener;

public class CustomTree extends Tree {
	private TreeItem defaultSelectedItem;
	private List<GlobalLoadListener> listeners = new ArrayList<>();
	
	public CustomTree(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		this.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setDefaultSelectedItem(getSelectedItem());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void selectDefaultTreeItem() {
		TreeItem item = getDefaultSelectedItem();
		if (item == null) {
			item = this.getItem(0);
			if (item == null) {
				return;
			}
			selectTreeItem(item);
			return;
		}
		selectTreeItem(item);
	}

	private void selectTreeItem(TreeItem item) {
		this.notifyListeners(SWT.Selection, null);
	}

	private TreeItem getSelectedItem() {
		if (this.getSelection() == null) {
			return null;
		}
		if (this.getSelection().length == 0) {
			return null;
		}
		if (this.getSelection()[0] == null) {
			return null;
		}
		return (TreeItem) this.getSelection()[0];
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

	public TreeItem getDefaultSelectedItem() {
		return defaultSelectedItem;
	}

	public void setDefaultSelectedItem(TreeItem defaultSelectedItem) {
		this.defaultSelectedItem = defaultSelectedItem;
	}
	
	public void addOpKeyGlobalLoadListener(GlobalLoadListener listener) {
		listeners.add(listener);
	}

	public void removeOpKeyGlobalLoadListener(GlobalLoadListener listener) {
		listeners.remove(listener);
	}

	public void fireGlobalListener() {
		for (GlobalLoadListener listener : this.listeners) {
			listener.handleGlobalEvent();
		}
	}
}
