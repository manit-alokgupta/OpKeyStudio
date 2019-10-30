package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import org.eclipse.wb.swt.ResourceManager;

public class ArtifactTreeUI extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException
	 */
	ArtifactTree artifactTree;

	public ArtifactTreeUI(Composite parent, int style) throws IOException {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		artifactTree = new ArtifactTree(this, SWT.BORDER);
		ServiceRepository.getInstance().setProjectTreeObject(artifactTree);
		ServiceRepository.getInstance().setDefaultProjectPath("E:\\Test\\TestProject");
		Menu menu = new Menu(artifactTree);
		artifactTree.setMenu(menu);

		MenuItem mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setText("New");

		Menu menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

		MenuItem folderMenuItem = new MenuItem(menu_1, SWT.NONE);
		folderMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		folderMenuItem.setText("Folder");

		MenuItem testcaseMenuItem = new MenuItem(menu_1, SWT.NONE);
		testcaseMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/testcase.png"));
		testcaseMenuItem.setText("TestCase");

		MenuItem objectRepositoryMenuItem = new MenuItem(menu_1, SWT.NONE);
		objectRepositoryMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/object repo.png"));
		objectRepositoryMenuItem.setText("ObjectRepository");

		MenuItem functionLibraryMenuItem = new MenuItem(menu_1, SWT.NONE);
		functionLibraryMenuItem.setText("Function Library");

		MenuItem renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setText("Rename");

		MenuItem deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/delete.png"));
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

		try {
			artifactTree.renderArtifacts();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
