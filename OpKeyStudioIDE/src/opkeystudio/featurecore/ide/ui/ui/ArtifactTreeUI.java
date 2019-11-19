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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ArtifactTreeUI extends Composite {
	MenuItem mntmNew;
	private ToolItem toolbarRename;
	private ToolItem toolbarDelete;
	private ToolItem toolbarNew;
	String[] items = {};
	private Menu menu_1;
	private Menu newMenu;
	private MenuItem toolbarFolder;
	private MenuItem toolbarTestCase;
	private MenuItem toolbarObjectRepository;
	private MenuItem toolbarFunctionLibrary;
	private MenuItem folderMenuItem;
	private MenuItem testcaseMenuItem;
	private MenuItem objectRepositoryMenuItem;
	private MenuItem functionLibraryMenuItem;
	private MenuItem renameMenuItem;
	private MenuItem deleteMenuItem;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException
	 */
	ArtifactTree artifactTree;
	private Text txtSearch;

	public ArtifactTreeUI(Composite parent, int style) {

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

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		toolbarNew = new ToolItem(toolBar_1, SWT.DROP_DOWN);
		toolbarNew.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		toolbarNew.setText("New");
		toolbarNew.setEnabled(false);

		ToolItem toolItem = new ToolItem(toolBar_1, SWT.SEPARATOR);
//		toolbarNew.setDropDownMenu(menu_1);

		toolbarRename = new ToolItem(toolBar_1, SWT.NONE);
		toolbarRename.setText("Rename");
		toolbarRename.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		toolbarRename.setEnabled(false);

		ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolbarDelete = new ToolItem(toolBar_1, SWT.NONE);
		toolbarDelete.setText("Delete");
		toolbarDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		toolbarDelete.setEnabled(false);

		artifactTree = new ArtifactTree(this, SWT.BORDER);
		artifactTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ServiceRepository.getInstance().setProjectTreeObject(artifactTree);
		Menu menu = new Menu(artifactTree);

		artifactTree.setMenu(menu);

		mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		mntmNew.setText("New");
		mntmNew.setEnabled(false);

		menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

		newMenu = new Menu(toolBar_1);
		toolBar_1.setMenu(newMenu);
		toolbarFolder = new MenuItem(newMenu, SWT.PUSH);
		toolbarFolder.setText("Folder");
		toolbarFolder.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));

		toolbarTestCase = new MenuItem(newMenu, SWT.PUSH);
		toolbarTestCase.setText("TestCase");
		toolbarTestCase.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/testcase.png"));

		toolbarFunctionLibrary = new MenuItem(newMenu, SWT.PUSH);
		toolbarFunctionLibrary.setText("Function Library");

		toolbarObjectRepository = new MenuItem(newMenu, SWT.PUSH);
		toolbarObjectRepository.setText("Object Repository");
		toolbarObjectRepository
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/object repo.png"));

		folderMenuItem = new MenuItem(menu_1, SWT.NONE);
		folderMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/folder.png"));
		folderMenuItem.setText("Folder");

		testcaseMenuItem = new MenuItem(menu_1, SWT.NONE);
		testcaseMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/testcase.png"));
		testcaseMenuItem.setText("TestCase");

		objectRepositoryMenuItem = new MenuItem(menu_1, SWT.NONE);
		objectRepositoryMenuItem
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/artifact/object repo.png"));
		objectRepositoryMenuItem.setText("ObjectRepository");

		functionLibraryMenuItem = new MenuItem(menu_1, SWT.NONE);
		functionLibraryMenuItem.setText("Function Library");

		renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameMenuItem.setText("Rename");
		renameMenuItem.setEnabled(false);

		deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteMenuItem.setText("Delete");
		deleteMenuItem.setEnabled(false);

		testcaseMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Case",
						"TestCase Name", "");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.Flow);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		folderMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Folder", "Folder Name",
						"");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.Folder);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		objectRepositoryMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Object Repository",
						"Object Repository Name", "");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.ObjectRepository);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		functionLibraryMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Function Library",
						"Function Library Name", "");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.Component);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();

				boolean status = new MessageDialogs().openConfirmDialog("Delete",
						"Do you want to delete " + artifact.getName() + "?");
				if (!status) {
					return;
				}
				new ArtifactApi().deleteArtifact(artifact);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
				Artifact artifact = artifactTree.getSelectedArtifact();
				String renamedText = new MessageDialogs().openInputDialogAandGetValue("Rename",
						"Rename " + artifact.getName(), artifact.getName());
				if (renamedText == null) {
					return;
				}
				artifact.setName(renamedText);
				new ArtifactApi().renameArtifact(artifact);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolbarNew.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Arrow Pressed");
				Rectangle rect = toolbarNew.getBounds();
				Point pt = new Point(rect.x, rect.y + rect.height);
				pt = toolBar_1.toDisplay(pt);
				newMenu.setLocation(pt.x, pt.y);
				newMenu.setVisible(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolbarRename.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String renamedText = new MessageDialogs().openInputDialogAandGetValue("Rename",
						"Rename " + artifact.getName(), artifact.getName());
				if (renamedText == null) {
					return;
				}
				artifact.setName(renamedText);
				new ArtifactApi().renameArtifact(artifact);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		toolbarDelete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				boolean status = new MessageDialogs().openConfirmDialog("Delete",
						"Do you want to delete " + artifact.getName() + "?");
				if (!status) {
					return;
				}
				new ArtifactApi().deleteArtifact(artifact);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolbarFolder.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Folder", "Folder Name",
						"");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.Folder);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolbarTestCase.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Case",
						"TestCase Name", "");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.Flow);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolbarFunctionLibrary.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Function Library",
						"Function Library Name", "");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.Component);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolbarObjectRepository.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Object Repository",
						"Object Repository Name", "");
				if (inputValue == null) {
					return;
				}

				new ArtifactApi().createArtifact(artifact.getId(), inputValue, MODULETYPE.ObjectRepository);
				try {
					artifactTree.renderArtifacts();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		toolBar_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {
					newMenu.setVisible(false);
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

		artifactTree.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				ArtifactTree tree = (ArtifactTree) e.getSource();
				ArtifactTreeItem selectedTreeItem = tree.getSelectedArtifactTreeItem();
//				if (e.button == 3) {
				System.out.println("Right clicked event");

				if (selectedTreeItem == null) {
					return;
				}
				if (selectedTreeItem.getArtifact() == null) {
					return;
				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Folder) {
					mntmNew.setEnabled(true);
					toolbarNew.setEnabled(true);
					toolbarRename.setEnabled(true);
					toolbarDelete.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);

				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.ObjectRepository) {
					mntmNew.setEnabled(false);
					toolbarNew.setEnabled(false);
					toolbarRename.setEnabled(true);
					toolbarDelete.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);

				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
					mntmNew.setEnabled(false);
					toolbarNew.setEnabled(false);
					toolbarRename.setEnabled(true);
					toolbarDelete.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);

//					}
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

		try {
			artifactTree.renderArtifacts();
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
