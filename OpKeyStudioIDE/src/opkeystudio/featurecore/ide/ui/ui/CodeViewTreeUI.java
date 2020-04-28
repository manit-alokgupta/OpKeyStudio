package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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

import opkeystudio.commandhandler.RefreshArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.artifacttree.CodeViewTree;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class CodeViewTreeUI extends SuperComposite {
	MenuItem mntmNew;
	private ToolItem toolbarRename;
	private ToolItem toolbarDelete;
	private ToolItem toolbarNew;
	private ToolItem toolbarRefresh;
	String[] items = {};
	private Menu menu_1;
	private Menu newMenu;
	private MenuItem toolbarFolder;
	private MenuItem toolJavaFile;
	private MenuItem folderMenuItem;
	private MenuItem javaFileMenuItem;
	private MenuItem openMenuItem;
	private MenuItem renameMenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem refreshMenuItem;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException
	 */
	private CodeViewTree codeViewTree;
	private Text txtSearch;

	@SuppressWarnings("unused")
	public CodeViewTreeUI(Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayout(new GridLayout(3, false));

		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite.heightHint = 39;
		composite.setLayoutData(gd_composite);

		txtSearch = new Text(composite, SWT.BORDER);
		GridData gd_txtSearch = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		gd_txtSearch.widthHint = 154;
		txtSearch.setLayoutData(gd_txtSearch);
		txtSearch.setToolTipText("Search");
		txtSearch.setMessage("Search");
		txtSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				Text text = (Text) e.getSource();
				String searchValue = text.getText();
				if (searchValue.length() >= 1 || searchValue.trim().isEmpty()) {
					codeViewTree.filterArtifactTree(searchValue);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1));

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.SEPARATOR);

		Button clearArtifactTreeButton = new Button(composite, SWT.NONE);
		clearArtifactTreeButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clearArtifactTreeButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ERASER_ICON));
		clearArtifactTreeButton.setToolTipText("Clear Text");

		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 30;
		clearArtifactTreeButton.setLayoutData(gd_btnNewButton);

		Composite composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, true));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT | SWT.WRAP);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		toolbarNew = new ToolItem(toolBar_1, SWT.DROP_DOWN);
		toolbarNew.setEnabled(false);
		toolbarNew.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ICON));
		toolbarNew.setToolTipText("New");
		toolbarRename = new ToolItem(toolBar_1, SWT.NONE);
		toolbarRename.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		toolbarRename.setEnabled(false);
		toolbarRename.setToolTipText("Rename");
		toolbarDelete = new ToolItem(toolBar_1, SWT.NONE);
		toolbarDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_TOOL_ICON));
		toolbarDelete.setEnabled(false);
		toolbarDelete.setToolTipText("Delete");
		toolbarRefresh = new ToolItem(toolBar_1, SWT.NONE);
		toolbarRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		toolbarRefresh.setToolTipText("Refresh");

		codeViewTree = new CodeViewTree(this, SWT.BORDER);
		codeViewTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ServiceRepository.getInstance().setProjectTreeObject(codeViewTree);
		Menu menu = new Menu(codeViewTree);

		codeViewTree.setMenu(menu);

		mntmNew = new MenuItem(menu, SWT.CASCADE);
		mntmNew.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ICON));
		mntmNew.setText("New");
		mntmNew.setEnabled(false);

		menu_1 = new Menu(mntmNew);
		mntmNew.setMenu(menu_1);

		newMenu = new Menu(toolBar_1);
		toolBar_1.setMenu(newMenu);
		toolbarFolder = new MenuItem(newMenu, SWT.PUSH);
		toolbarFolder.setText("Folder");
		toolbarFolder.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));

		toolJavaFile = new MenuItem(newMenu, SWT.PUSH);
		toolJavaFile.setText("TestCase");
		toolJavaFile.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));

		folderMenuItem = new MenuItem(menu_1, SWT.NONE);
		folderMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		folderMenuItem.setText("Folder");

		javaFileMenuItem = new MenuItem(menu_1, SWT.NONE);
		javaFileMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));
		javaFileMenuItem.setText("TestCase");

		openMenuItem = new MenuItem(menu, SWT.NONE);
		openMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OPEN_ICON));
		openMenuItem.setText("Open");
		openMenuItem.setEnabled(false);

		renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		renameMenuItem.setText("Rename");
		renameMenuItem.setEnabled(false);

		deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteMenuItem.setText("Delete");
		deleteMenuItem.setEnabled(false);

		refreshMenuItem = new MenuItem(menu, SWT.NONE);
		refreshMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));
		refreshMenuItem.setText("Refresh");
		refreshMenuItem.setEnabled(false);

		javaFileMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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

		openMenuItem.addSelectionListener(new SelectionListener() {

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

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		renameMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Refresh Success");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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

			}
		});

		toolbarRename.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarDelete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarRefresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new RefreshArtifactTree().refreshArtifactTree();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		toolbarFolder.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolJavaFile.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {

			}
		});

		codeViewTree.renderArtifacts();

//		  Search button listener

		clearArtifactTreeButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				txtSearch.setText("");
				String textToSearch = txtSearch.getText();
				codeViewTree.filterArtifactTree(textToSearch);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	public void toggleRenameToolbarItem(boolean status) {
		toolbarRename.setEnabled(status);
	}

	public void toogleNewToolbarItem(boolean status) {
		toolbarNew.setEnabled(status);
	}

	public void toogleDeleteToolbarItem(boolean status) {
		toolbarDelete.setEnabled(status);
	}

	public void toogleNewToolbarMenuItem(boolean status) {
		mntmNew.setEnabled(status);
	}

	public void toggleRefreshMenuItem(boolean status) {
		refreshMenuItem.setEnabled(status);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void keyPressed(KeyEvent event) {
		if (event.keyCode == SWT.ESC) {
			System.out.println("Escape pressed");
		}
	}
}
