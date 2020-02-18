package opkeystudio.featurecore.ide.ui.ui;

import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.commandhandler.RefreshArtifactTree;
import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.Utilities;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTree;
import opkeystudio.featurecore.ide.ui.customcontrol.ArtifactTreeItem;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ArtifactTreeUI extends Composite {
	MenuItem mntmNew;
	private ToolItem toolbarRename;
	private ToolItem toolbarDelete;
	private ToolItem toolbarNew;
	private ToolItem toolbarRefresh;
	String[] items = {};
	private Menu menu_1;
	private Menu newMenu;
	private MenuItem toolbarFolder;
	private MenuItem toolbarDR;
	private MenuItem toolbarTestCase;
	private MenuItem toolbarObjectRepository;
	private MenuItem toolbarFunctionLibrary;
	private MenuItem toolbarTestSuite;
	private MenuItem folderMenuItem;
	private MenuItem testcaseMenuItem;
	private MenuItem objectRepositoryMenuItem;
	private MenuItem functionLibraryMenuItem;
	private MenuItem testSuiteMenuItem;
	private MenuItem codedFLMenuItem;
	private MenuItem drMenuItem;
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
	ArtifactTree artifactTree;
	private Text txtSearch;

	@SuppressWarnings("unused")
	public ArtifactTreeUI(Composite parent, int style) {

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
					artifactTree.filterArtifactTree(searchValue);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

//		  Hiding Search button as searching is working without it

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		toolBar.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1));

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.SEPARATOR);

		Button clearArtifactTreeButton = new Button(composite, SWT.NONE);
		clearArtifactTreeButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		clearArtifactTreeButton.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/erase.png"));
		clearArtifactTreeButton.setToolTipText("Clear Text");

		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 30;
		clearArtifactTreeButton.setLayoutData(gd_btnNewButton);

		Composite composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, true));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
//		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
//		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
//		gd_composite_1.heightHint = 34;
//		gd_composite_1.widthHint = 110; 
//		composite_1.setLayoutData(gd_composite_1);

		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT | SWT.WRAP);
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		toolbarNew = new ToolItem(toolBar_1, SWT.DROP_DOWN);
		toolbarNew.setEnabled(false);
		toolbarNew.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ICON));
		// toolbarNew.setText("New");
		toolbarNew.setToolTipText("New");

		// ToolItem toolItem = new ToolItem(toolBar_1, SWT.SEPARATOR);
//		toolbarNew.setDropDownMenu(menu_1);

		toolbarRename = new ToolItem(toolBar_1, SWT.NONE);
		// toolbarRename.setText("Rename");
		toolbarRename.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		toolbarRename.setEnabled(false);
		toolbarRename.setToolTipText("Rename");

		// ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolbarDelete = new ToolItem(toolBar_1, SWT.NONE);
		// toolbarDelete.setText("Delete");
		toolbarDelete.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_TOOL_ICON));
		toolbarDelete.setEnabled(false);
		toolbarDelete.setToolTipText("Delete");

//		ToolItem toolItem_2 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		toolbarRefresh = new ToolItem(toolBar_1, SWT.NONE);
		// toolbarDelete.setText("Delete");
		toolbarRefresh.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_TOOL_ICON));
		// toolbarRefresh.setEnabled(false);
		toolbarRefresh.setToolTipText("Refresh");

		artifactTree = new ArtifactTree(this, SWT.BORDER);
		artifactTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ServiceRepository.getInstance().setProjectTreeObject(artifactTree);
		Menu menu = new Menu(artifactTree);

		artifactTree.setMenu(menu);

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

		toolbarTestCase = new MenuItem(newMenu, SWT.PUSH);
		toolbarTestCase.setText("TestCase");
		toolbarTestCase.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));

		toolbarFunctionLibrary = new MenuItem(newMenu, SWT.PUSH);
		toolbarFunctionLibrary.setText("Function Library");
		toolbarFunctionLibrary.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));

		toolbarObjectRepository = new MenuItem(newMenu, SWT.PUSH);
		toolbarObjectRepository.setText("Object Repository");
		toolbarObjectRepository.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));

		toolbarTestSuite = new MenuItem(newMenu, SWT.PUSH);
		toolbarTestSuite.setText("Test Suite");
		toolbarTestSuite.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SUITE_ICON));

		toolbarDR = new MenuItem(newMenu, SWT.PUSH);
		toolbarDR.setText("DR");
		toolbarDR.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_ICON));

		folderMenuItem = new MenuItem(menu_1, SWT.NONE);
		folderMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FOLDER_ICON));
		folderMenuItem.setText("Folder");

		testcaseMenuItem = new MenuItem(menu_1, SWT.NONE);
		testcaseMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.TC_ICON));
		testcaseMenuItem.setText("TestCase");

		objectRepositoryMenuItem = new MenuItem(menu_1, SWT.NONE);
		objectRepositoryMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));
		objectRepositoryMenuItem.setText("ObjectRepository");

		functionLibraryMenuItem = new MenuItem(menu_1, SWT.NONE);
		functionLibraryMenuItem.setText("Function Library");
		functionLibraryMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.FL_ICON));

		testSuiteMenuItem = new MenuItem(menu_1, SWT.PUSH);
		testSuiteMenuItem.setText("Test Suite");
		testSuiteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SUITE_ICON));

		drMenuItem = new MenuItem(menu_1, SWT.PUSH);
		drMenuItem.setText("DR");
		drMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DR_ICON));

		codedFLMenuItem = new MenuItem(menu_1, SWT.PUSH);
		codedFLMenuItem.setText("Coded FL");
		codedFLMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));

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

		testcaseMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Case",
						"TestCase Name", "Test Case " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Name", "Name " + artifact.getName(),
							artifact.getName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.Flow);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		testSuiteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Suite",
						"Test Suite Name", "Test Suite " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Suite",
							"Test Suite Name ", "Test Suite " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				createArtifact(artifact, inputValue, MODULETYPE.Suite);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		folderMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Folder", "Folder Name",
						"Folder " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Folder", "Folder Name ",
							"Folder " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.Folder);
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
						"Object Repository Name", "Object Repository " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Object Repository",
							"Object Repository Name ", "Object Repository " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.ObjectRepository);
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
						"Function Library Name", "Function Library " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Function Library",
							"Function Library Name ", "Function Library " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.Component);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		openMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				artifactTree.openSelectedArtifact();
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
				boolean isused = new ArtifactApiUtilities().isArtifactIsUsed(artifact);
				if (isused) {
					new MessageDialogs().openInformationDialog("Unable to delete " + artifact.getFile_type_enum(),
							"Unable to delete " + artifact.getFile_type_enum() + " '" + artifact.getName()
									+ "' as it is being used:");
					return;
				}
				new ArtifactApi().deleteArtifact(artifact);
				Utilities.getInstance().closeArtifactPart(artifact);
				artifactTree.renderArtifacts();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		renameMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String renamedText = new MessageDialogs().openInputDialogAandGetValue("Rename",
						"Rename " + artifact.getName(), artifact.getName());
				if (renamedText == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (renamedText.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					renamedText = new MessageDialogs().openInputDialogAandGetValue("Rename",
							"Rename " + artifact.getName(), artifact.getName());
					if (renamedText == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				artifact.setName(renamedText);
				new ArtifactApi().updateArtifact(artifact);
				Utilities.getInstance().renameArtifactLabel(artifact, renamedText);
				artifactTree.renderArtifacts();
				renameMenuItem.setEnabled(false);
				renameMenuItem.setEnabled(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				artifactTree.refereshArtifacts();

				System.out.println("Refresh Success");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		drMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New DR", "DR Name",
						"DR " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New DR", "DR Name ",
							"DR " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.DataRepository);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		codedFLMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New CodedFL",
						"CodedFL Name", "NEW CFL " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New CodedFL", "CodedFL Name ",
							"NEW CFL " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.CodedFunction);

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
				Artifact artifact = artifactTree.getSelectedArtifact();
				String renamedText = new MessageDialogs().openInputDialogAandGetValue("Rename",
						"Rename " + artifact.getName(), artifact.getName());
				if (renamedText == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (renamedText.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					renamedText = new MessageDialogs().openInputDialogAandGetValue("Rename",
							"Rename " + artifact.getName(), artifact.getName());
					if (renamedText == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				artifact.setName(renamedText);
				new ArtifactApi().updateArtifact(artifact);
				toggleRenameToolbarItem(false);
				toogleDeleteToolbarItem(false);
				toogleNewToolbarMenuItem(false);
				toogleNewToolbarItem(false);
				artifactTree.renderArtifacts();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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
				toggleRenameToolbarItem(false);
				toogleDeleteToolbarItem(false);
				toogleNewToolbarMenuItem(false);
				toogleNewToolbarItem(false);
				artifactTree.renderArtifacts();

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
				// TODO Auto-generated method stub

			}
		});
		toolbarFolder.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Folder", "Folder Name",
						"Folder " + getVarName());

				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Folder", "Enter Name ",
							"Folder " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				String artifactId = null;
				if (artifact != null) {
					if (artifact.getId() != null) {
						artifactId = artifact.getId();
					}
				}
				toogleNewToolbarItem(false);
				createArtifact(artifact, inputValue, MODULETYPE.Folder);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarTestCase.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Case",
						"TestCase Name", "Test Case " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Case",
							"Test Case Name ", "Test Case " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				toogleNewToolbarItem(false);
				createArtifact(artifact, inputValue, MODULETYPE.Flow);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarFunctionLibrary.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Function Library",
						"Function Library Name", "Function Library " + getVarName());

				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}

				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Function Library",
							"Function Library Name ", "Function Library " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				toogleNewToolbarItem(false);
				createArtifact(artifact, inputValue, MODULETYPE.Component);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarObjectRepository.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Object Repository",
						"Object Repository Name", "Object Repository " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Object Repository",
							"Object Repository Name ", "Object Repository " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				toogleNewToolbarItem(false);
				createArtifact(artifact, inputValue, MODULETYPE.ObjectRepository);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarTestSuite.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New Test Suite",
						"TestSuite Name", "");
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Name", "Test Suite Name ",
							"Test Suite " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}
				toogleNewToolbarItem(false);
				createArtifact(artifact, inputValue, MODULETYPE.Suite);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolbarDR.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Artifact artifact = artifactTree.getSelectedArtifact();
				String inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New DR", "DR Name",
						"DR " + getVarName());
				if (inputValue == null) {
					System.out.println("cancel pressed ");
					return;
				}
				while (inputValue.trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Name can not be empty");
					inputValue = new MessageDialogs().openInputDialogAandGetValue("Create New DR", "DR Name ",
							"DR " + getVarName());
					if (inputValue == null) {
						System.out.println("cancel pressed inside while loop");
						return;
					}
				}

				createArtifact(artifact, inputValue, MODULETYPE.DataRepository);

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

		artifactTree.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				ArtifactTree tree = (ArtifactTree) e.getSource();
				ArtifactTreeItem selectedTreeItem = tree.getSelectedArtifactTreeItem();

				System.out.println("Mouse clicked event");

				if (selectedTreeItem == null) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(false);
					toogleDeleteToolbarItem(false);
					openMenuItem.setEnabled(false);
					deleteMenuItem.setEnabled(false);
					renameMenuItem.setEnabled(false);
					toggleRefreshMenuItem(false);

					return;
				}
				if (selectedTreeItem.getArtifact() == null) {
					toogleNewToolbarMenuItem(true);
					toogleNewToolbarItem(true);
					toggleRenameToolbarItem(false);
					toogleDeleteToolbarItem(false);
					openMenuItem.setEnabled(false);
					deleteMenuItem.setEnabled(false);
					renameMenuItem.setEnabled(false);
					toggleRefreshMenuItem(true);
					return;
				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Folder) {
					toogleNewToolbarMenuItem(true);
					toogleNewToolbarItem(true);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(false);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);

				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.ObjectRepository) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);

				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Flow) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);

				}
				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Suite) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);
				}

				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.Component) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);
				}

				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.DataRepository) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);
				}

				if (selectedTreeItem.getArtifact().getFile_type_enum() == MODULETYPE.CodedFunction) {
					toogleNewToolbarMenuItem(false);
					toogleNewToolbarItem(false);
					toggleRenameToolbarItem(true);
					toogleDeleteToolbarItem(true);
					openMenuItem.setEnabled(true);
					deleteMenuItem.setEnabled(true);
					renameMenuItem.setEnabled(true);
					toggleRefreshMenuItem(true);
				}
			}

			@Override
			public void mouseDown(MouseEvent e) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				return;
			}
		});

		artifactTree.renderArtifacts();

//		  Search button listener

		clearArtifactTreeButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				txtSearch.setText("");
				String textToSearch = txtSearch.getText();
				artifactTree.filterArtifactTree(textToSearch);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	private void createArtifact(Artifact parentArtifact, String artifactName, MODULETYPE moduleType) {
		new ArtifactApi().createArtifact(parentArtifact, artifactName, moduleType);
		artifactTree.renderArtifacts();
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

	private String getVarName() {
		return String.valueOf(System.currentTimeMillis());
	}
}
