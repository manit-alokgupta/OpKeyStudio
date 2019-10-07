package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtificateTreeItem;
import opkeystudio.featurecore.project.ProjectLoader;

public class TreeViewCompositeUI extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException 
	 */
	public TreeViewCompositeUI(Composite parent, int style) throws IOException {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Tree tree = new Tree(this, SWT.BORDER);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Tree tree = (Tree) arg0.getSource();
				TreeItem[] items = tree.getSelection();
				System.out.println(items.length);
				System.out.println(((ArtificateTreeItem) items[0]).getArtificate().getArtificatePath());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		new ProjectLoader().loadProjectInTree(tree, "E:\\Test\\TestProject");

		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setText("New");

		Menu menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

		MenuItem mntmTestcase = new MenuItem(menu_1, SWT.NONE);
		mntmTestcase.setText("TestCase");

		MenuItem mntmObjectr = new MenuItem(menu_1, SWT.NONE);
		mntmObjectr.setText("ObjectRepository");

		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("Rename");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
