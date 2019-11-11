package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ArtifactTreeUI extends Composite {
	MenuItem mntmNew;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException
	 */
	ArtifactTree artifactTree;
	private Text txtSearch;

	public ArtifactTreeUI(Composite parent, int style) throws IOException {

		super(parent, style);
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new GridLayout(3, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite.heightHint = 33;
		composite.setLayoutData(gd_composite);

		txtSearch = new Text(composite, SWT.BORDER);
		GridData gd_txtSearch = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_txtSearch.widthHint = 154;
		txtSearch.setLayoutData(gd_txtSearch);
		txtSearch.setToolTipText("Search");
		txtSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				Text text = (Text) e.getSource();
				String searchValue = text.getText();
				if (searchValue.length() > 2 || searchValue.trim().isEmpty()) {
					filterArtifactTree(searchValue);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmNewItem.setText("New Item");

		Button searchArtifactTreeButton = new Button(composite, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton.widthHint = 60;
		searchArtifactTreeButton.setLayoutData(gd_btnNewButton);
		searchArtifactTreeButton.setText("Search");

		artifactTree = new ArtifactTree(this, SWT.BORDER);
		artifactTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		ServiceRepository.getInstance().setProjectTreeObject(artifactTree);
		ServiceRepository.getInstance().setDefaultProjectPath("E:\\Test\\TestProject");
		Menu menu = new Menu(artifactTree);
		artifactTree.setMenu(menu);

		mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/create.png"));
		mntmNew.setText("New");
		mntmNew.setEnabled(false);

		Menu menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

		MenuItem folderMenuItem = new MenuItem(menu_1, SWT.NONE);
		folderMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		folderMenuItem.setText("Folder");

		MenuItem testcaseMenuItem = new MenuItem(menu_1, SWT.NONE);
		testcaseMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/testcase.png"));
		testcaseMenuItem.setText("TestCase");

		MenuItem objectRepositoryMenuItem = new MenuItem(menu_1, SWT.NONE);
		objectRepositoryMenuItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/object repo.png"));
		objectRepositoryMenuItem.setText("ObjectRepository");

		MenuItem functionLibraryMenuItem = new MenuItem(menu_1, SWT.NONE);
		functionLibraryMenuItem.setText("Function Library");

		MenuItem renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
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

			}
		});

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ArtifactTreeItem treeItem = artifactTree.getSelectedArtifactTreeItem();
				new ArtifactApi().deleteArtifact(treeItem.getArtifact());
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

		searchArtifactTreeButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String textToSearch = txtSearch.getText();
				filterArtifactTree(textToSearch);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		try {

			artifactTree.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					if (e.button == 3) {
						System.out.println("Right clicked event");
						ArtifactTree tree = (ArtifactTree) e.getSource();
						ArtifactTreeItem selectedTreeItem = tree.getSelectedArtifactTreeItem();
						if (selectedTreeItem == null) {
							return;
						}
						if (selectedTreeItem.getArtifact() == null) {
							return;
						}
						if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Folder) {
							mntmNew.setEnabled(true);
						}
						if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.ObjectRepository) {
							mntmNew.setEnabled(false);
						}
						if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
							mntmNew.setEnabled(false);
						}
					}
				}

				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});

			artifactTree.renderArtifacts();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void filterArtifactTree(String searchValue) {
		List<Artifact> artifacts = artifactTree.getArtifactsData();
		for (Artifact artifact : artifacts) {
			if (artifact.getFile_type_enum() != MODULETYPE.Folder) {
				if (artifact.getName().trim().toLowerCase().contains(searchValue.trim().toLowerCase())) {
					artifact.setVisible(true);
				} else {
					artifact.setVisible(false);
				}
			}
		}
		artifactTree.refereshArtifacts();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
