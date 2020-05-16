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
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyIntellisenseListenerDispatcher;

public class CustomTree extends Tree {
	private TreeItem defaultSelectedItem;
	private List<GlobalLoadListener> listeners = new ArrayList<>();
	private List<GlobalLoadListener> intellisenseListners = new ArrayList<GlobalLoadListener>();

	public CustomTree(Composite parent, int style) {
		super(parent, style);
		init();
		OpKeyGlobalLoadListenerDispatcher.getInstance().addSuperComposite(this);
		OpKeyIntellisenseListenerDispatcher.getInstance().addSuperComposite(this);
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

	public void selectTreeItem(TreeItem item) {
		try {
			this.setSelection(item);
			this.notifyListeners(SWT.Selection, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
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

	public boolean isNameIsUnique(TreeItem rootitem, String name) {
		TreeItem[] items = rootitem.getItems();
		for (TreeItem item : items) {
			if (item.getText().toLowerCase().equals(name.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public String getUniqueTreeItemName(TreeItem rootitem, String name) {
		boolean unique = isNameIsUnique(rootitem, name);
		if (unique) {
			return name;
		}
		for (int i = 1; i < 1000; i++) {
			boolean unique1 = isNameIsUnique(rootitem, name + "(" + i + ")");
			if (unique1) {
				return name + "(" + i + ")";
			}
		}
		return name;
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

	public void addIntellisenseLoadListener(GlobalLoadListener listener) {
		intellisenseListners.add(listener);
	}

	public void removeIntellisenseLoadListener(GlobalLoadListener listener) {
		intellisenseListners.remove(listener);
	}

	public void fireIntellisenseListener() {
		for (GlobalLoadListener listener : this.intellisenseListners) {
			listener.handleGlobalEvent();
		}
	}

	public void fireGlobalListener() {
		for (GlobalLoadListener listener : this.listeners) {
			listener.handleGlobalEvent();
		}
	}
}
