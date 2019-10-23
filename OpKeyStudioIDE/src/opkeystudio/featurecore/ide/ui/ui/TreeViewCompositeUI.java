package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;

import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

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
		ServiceRepository.getInstance().setProjectTreeObject(tree);
		ServiceRepository.getInstance().setDefaultProjectPath("E:\\Test\\TestProject");
		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setText("New");

		Menu menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

		MenuItem folderMenuItem = new MenuItem(menu_1, SWT.NONE);
		folderMenuItem.setText("Folder");

		MenuItem testcaseMenuItem = new MenuItem(menu_1, SWT.NONE);
		testcaseMenuItem.setText("TestCase");

		MenuItem objectRepositoryMenuItem = new MenuItem(menu_1, SWT.NONE);
		objectRepositoryMenuItem.setText("ObjectRepository");

		MenuItem functionLibraryMenuItem = new MenuItem(menu_1, SWT.NONE);
		functionLibraryMenuItem.setText("Function Library");

		MenuItem renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setText("Rename");

		MenuItem deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setText("Delete");

		testcaseMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		folderMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		renameMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
