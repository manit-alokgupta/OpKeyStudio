package opkeystudio.parts.compositeui;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TreeViewCompositeUI extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TreeViewCompositeUI(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(this, SWT.BORDER);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/java_app.gif"));
		trtmNewTreeitem.setText("TestCases");
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/new_con@2x.png"));
		trtmNewTreeitem_1.setText("Object Repository");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_2.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/class_obj.gif"));
		trtmNewTreeitem_2.setText("TestSuites");
		
		Menu menu = new Menu(tree);
		tree.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected");
			}
		});
		mntmNewItem.setText("Create");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("Rename");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
