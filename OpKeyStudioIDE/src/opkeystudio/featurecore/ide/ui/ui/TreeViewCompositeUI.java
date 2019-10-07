package opkeystudio.featurecore.ide.ui.ui;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import opkeystudio.commandhandler.RefreshProject;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtificateTreeItem;
import opkeystudio.featurecore.project.ProjectLoader;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate.ArtificateType;
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
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		ServiceRepository.getInstance().setProjectTreeObject(tree);
		ServiceRepository.getInstance().setDefaultProjectPath("E:\\Test\\TestProject");
		new ProjectLoader().loadProjectInTree(tree, ServiceRepository.getInstance().getDefaultProjectPath());

		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setText("New");

		Menu menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

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

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artificate selectArtificate = ServiceRepository.getInstance().getDefaultArtificate();
				boolean option = MessageDialog.openQuestion(parent.getShell(), "Delete Confirmation",
						"Do you really want to delete " + selectArtificate.getArtificateName() + " ?");
				if (option) {
					selectArtificate.getFile().delete();
					try {
						new RefreshProject().refreshProjectTree();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		renameMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artificate selectArtificate = ServiceRepository.getInstance().getDefaultArtificate();
				InputDialog renameDialog = new InputDialog(parent.getShell(), "Rename",
						"Rename " + selectArtificate.getArtificateName(), selectArtificate.getArtificateName(), null);
				renameDialog.open();
				String inputValue = renameDialog.getValue();
				System.out.println("Input Value is "+inputValue);
				if (inputValue.trim().isEmpty()) {
					return;
				}
				selectArtificate.getFile()
						.renameTo(new File(selectArtificate.getParentPath() + File.separator + inputValue));
				try {
					new RefreshProject().refreshProjectTree();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Tree tree = (Tree) arg0.getSource();
				TreeItem[] items = tree.getSelection();
				Artificate selectedArtificate = ((ArtificateTreeItem) items[0]).getArtificate();
				ServiceRepository.getInstance().setDefaultArtificate(selectedArtificate);
				if (selectedArtificate.getArtificateType() == ArtificateType.FOLDER
						|| selectedArtificate.getArtificateType() == ArtificateType.ROOTFOLDER) {
					System.out.println("Clicked");
					menu_1.dispose();
				} else {

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
