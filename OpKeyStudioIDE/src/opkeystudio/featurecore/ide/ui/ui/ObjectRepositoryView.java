package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTable;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.dtoMaker.ORObjectMaker;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class ObjectRepositoryView extends Composite {
	private ObjectAttributeTable objectAttributeTable;
	private ObjectRepositoryTree objectRepositoryTree;
	private ToolItem saveObject;
	private ToolItem renameObject;
	private ToolItem deleteObject;
	private ToolItem refreshObject;
	private ToolItem addObjectAttribute;
	private ToolItem deleteObjectAttribute;
	private MenuItem cutMenuItem;
	private MenuItem copyMenuItem;
	private MenuItem pasteMenuItem;
	private MenuItem renameMenuItem;
	private MenuItem deleteMenuItem;
	private ToolItem addParentObjectToolItem;
	private ToolItem addChildObjectToolItem;
	private ORObject obRepo;
	private String orId;
	private String[] parentObjectTypes = new String[] { "HTML PAGE", "FRAME" };
	private String[] childObjectTypes = new String[] { "", "" };

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		ObjectRepositoryUI();
		toggleSaveButton(false);
		toggleRenameButton(false);
		toggleDeleteButton(false);
		toggleAddAttributeButton(false);
		toggleDeleteAttributeButton(false);
	}

	public ObjectRepositoryTree getObjectRepositoryTree() {
		return this.objectRepositoryTree;
	}

	private Menu createParentObjectCreationMenu(Control parent) {
		Menu menu = new Menu(parent);
		for (String menuItem : parentObjectTypes) {
			MenuItem item = new MenuItem(menu, 0);
			item.setText(menuItem);
			item.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					ORObject orobject = new ORObjectMaker().getORObjectDTO(getOrId(), null,
							"New Node " + item.getText(), item.getText());
					objectRepositoryTree.getObjectRepositoriesData().add(orobject);
					toggleSaveButton(true);
					objectRepositoryTree.refreshObjectRepositories();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
		return menu;
	}

	private Menu createChildObjectCreationMenu(Control parent) {
		Menu menu = new Menu(parent);
		for (String menuItem : childObjectTypes) {
			MenuItem item = new MenuItem(menu, 0);
			item.setText(menuItem);
			item.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
		return menu;
	}

	public void ObjectRepositoryUI() {
		setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(this, SWT.BOTTOM);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Object Repository");

		SashForm sashForm = new SashForm(tabFolder, SWT.NONE);
		tabItem.setControl(sashForm);

		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_3.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite_3, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 87, 23);

		ToolItem septoolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		addParentObjectToolItem = new ToolItem(toolBar, SWT.DROP_DOWN);
		addParentObjectToolItem.setText("Add(HtmlPage)");

		ToolItem septoolItem2 = new ToolItem(toolBar, SWT.SEPARATOR);

		addChildObjectToolItem = new ToolItem(toolBar, SWT.DROP_DOWN);
		addChildObjectToolItem.setText("Add(Area)");

		Menu parentObjectMenu = createParentObjectCreationMenu(addParentObjectToolItem.getDisplay().getActiveShell());

		addParentObjectToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.ARROW) {
					Rectangle rect = addParentObjectToolItem.getBounds();
					Point pt = new Point(rect.x, rect.y + rect.height);
					pt = toolBar.toDisplay(pt);
					parentObjectMenu.setLocation(pt.x, pt.y);
					parentObjectMenu.setVisible(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Menu childObjectMenu = createChildObjectCreationMenu(addChildObjectToolItem.getDisplay().getActiveShell());

		addChildObjectToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.ARROW) {
					Rectangle rect = addChildObjectToolItem.getBounds();
					Point pt = new Point(rect.x, rect.y + rect.height);
					pt = toolBar.toDisplay(pt);
					childObjectMenu.setLocation(pt.x, pt.y);
					childObjectMenu.setVisible(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		saveObject = new ToolItem(toolBar, SWT.NONE);
		saveObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));
		saveObject.setText("Save");
		saveObject.setToolTipText("Save");

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		renameObject = new ToolItem(toolBar, SWT.NONE);
		renameObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameObject.setText("Rename");
		renameObject.setToolTipText("Rename");

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		deleteObject = new ToolItem(toolBar, SWT.NONE);
		deleteObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteObject.setText("Delete");
		deleteObject.setToolTipText("Delete");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		refreshObject = new ToolItem(toolBar, SWT.NONE);
		refreshObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshObject.setText("Refresh");
		refreshObject.setToolTipText("Refresh");

		objectRepositoryTree = new ObjectRepositoryTree(composite_3, SWT.BORDER, this);
//		Tree tree = new Tree(composite_3, SWT.BORDER);
		objectRepositoryTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectRepositoryTree.setBounds(0, 0, 85, 85);

		ServiceRepository.getInstance().setProjectTreeObject(objectRepositoryTree);
		Menu menu = new Menu(objectRepositoryTree);
		objectRepositoryTree.setMenu(menu);

		cutMenuItem = new MenuItem(menu, SWT.CASCADE);
		cutMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/cut.png"));
		cutMenuItem.setText("Cut");

		copyMenuItem = new MenuItem(menu, SWT.NONE);
		copyMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/copy.png"));
		copyMenuItem.setText("Copy");

		pasteMenuItem = new MenuItem(menu, SWT.NONE);
		pasteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/paste.png"));
		pasteMenuItem.setText("Paste");

		renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameMenuItem.setText("Rename");

		deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteMenuItem.setText("Delete");

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_1.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		addObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		addObjectAttribute.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addObjectAttribute.setText("Add");
		addObjectAttribute.setToolTipText("Add");

		ToolItem toolItem_3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		deleteObjectAttribute
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteObjectAttribute.setText("Delete");
		deleteObjectAttribute.setToolTipText("Delete");

		objectAttributeTable = new ObjectAttributeTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION, this);
		objectAttributeTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectAttributeTable.setHeaderVisible(true);
		objectAttributeTable.setLinesVisible(true);
		sashForm.setWeights(new int[] { 2, 1 });

		objectRepositoryTree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectRepositoryTreeItem item = (ObjectRepositoryTreeItem) objectRepositoryTree.getSelection()[0];
				renderObjectAttributeProperty(item);
				if (item.getObjectRepository() != null) {
					toggleDeleteButton(true);
					toggleRenameButton(true);
					toggleAddAttributeButton(true);
					toggleRenameMenuItem(true);
					toggleCopyMenuItem(true);
					togglePasteMenuItem(true);
					toggleCutMenuItem(true);
					toggleDeleteMenuItem(true);
				} else {
					toggleDeleteButton(false);
					toggleRenameButton(false);
					toggleAddAttributeButton(false);
					toggleRenameMenuItem(false);
					toggleCopyMenuItem(false);
					togglePasteMenuItem(false);
					toggleCutMenuItem(false);
					toggleDeleteMenuItem(false);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		objectAttributeTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectAttributeTableItem oati = (ObjectAttributeTableItem) objectAttributeTable.getSelection()[0];
				System.out.println(oati.getObjectAttributeData().getProperty());
				deleteObjectAttribute.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		saveObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Save",
						"Please press OK to Svae");
				if (!result) {
					return;
				}
				List<ORObject> allors = objectRepositoryTree.getObjectRepositoriesData();
				try {
					new ObjectRepositoryApi().saveORObjects(allors);
					toggleSaveButton(false);
					objectRepositoryTree.renderObjectRepositories();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		renameObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				renameFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				toggleSaveButton(false);
				objectRepositoryTree.renderObjectRepositories();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		deleteObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		deleteObjectAttribute.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Delete",
						"Please press OK to Delete");
				if (!result) {
					return;
				}
				ObjectAttributeProperty selectedProperty = objectAttributeTable.getSelectedObjectAttributeProperty();
				System.out.println("Deleting " + selectedProperty.getOr_id());
				selectedProperty.setDeleted(true);
				objectAttributeTable.renderObjectAttributes();
				toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		addObjectAttribute.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ORObject orobject = getObjectRepositoryTree().getSelectedORObject();
				if (orobject != null) {
					ObjectAttributeProperty attrProp = new ORObjectMaker().getNewObjectAttributeProperty(orobject);
					objectAttributeTable.getObjectPropertiesData().add(attrProp);
					objectAttributeTable.renderObjectAttributes();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		copyMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
				obRepo = selectedTreeItem.getObjectRepository();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		cutMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
				obRepo = selectedTreeItem.getObjectRepository();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		pasteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		renameMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				renameFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void toggleSaveButton(boolean status) {
		saveObject.setEnabled(status);
	}

	public void toggleRenameButton(boolean status) {
		renameObject.setEnabled(status);

	}

	public void toggleDeleteButton(boolean status) {
		deleteObject.setEnabled(status);

	}

	public void toggleAddAttributeButton(boolean status) {
		addObjectAttribute.setEnabled(status);
	}

	public void toggleDeleteAttributeButton(boolean status) {
		deleteObjectAttribute.setEnabled(status);
	}

	public void toggleCopyMenuItem(boolean status) {
		copyMenuItem.setEnabled(status);
	}

	public void toggleCutMenuItem(boolean status) {
		cutMenuItem.setEnabled(status);
	}

	public void toggleRenameMenuItem(boolean status) {
		renameMenuItem.setEnabled(status);
	}

	public void togglePasteMenuItem(boolean status) {
		pasteMenuItem.setEnabled(status);
	}

	public void toggleDeleteMenuItem(boolean status) {
		deleteMenuItem.setEnabled(status);
	}

	public void renameFunction() {
		ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
		ORObject obRepo = selectedTreeItem.getObjectRepository();
		InputDialog input = new InputDialog(Display.getCurrent().getActiveShell(), "Rename", "Enter name to rename",
				obRepo.getName(), null);

		if (input.open() != InputDialog.OK) {
			return;
		}
		if (input.getValue().trim().isEmpty()) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Invalid Input", "Please Enter Some Value");
			return;
		}
		obRepo.setName(input.getValue());
		obRepo.setModified(true);
		toggleSaveButton(true);
		objectRepositoryTree.refreshObjectRepositories();
	}

	public void deleteFunction() {
		boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Delete",
				"Please press OK to Delete");
		if (!result) {
			return;
		}
		ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
		obRepo = selectedTreeItem.getObjectRepository();
		System.out.println("Deleting.. " + obRepo.getObject_id());
		obRepo.setDeleted(true);
		toggleSaveButton(true);
		objectRepositoryTree.refreshObjectRepositories();
	}

	private void renderObjectAttributeProperty(ObjectRepositoryTreeItem item) {
		if (item.getObjectRepository() != null) {
			String objectId = item.getObjectRepository().getObject_id();
			try {
				if (item.getObjectRepository().getObjectAttributesProperty().size() == 0) {
					System.out.println("Executing Object Property Fetch");
					item.getObjectRepository().setObjectAttributesProperty(
							new ObjectRepositoryApi().getObjectAttributeProperty(objectId));
				}
				objectAttributeTable.setControlData(item.getObjectRepository().getObjectAttributesProperty());
				objectAttributeTable.renderObjectAttributes();
			} catch (JsonParseException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public String getOrId() {
		return orId;
	}

	public void setOrId(String orId) {
		this.orId = orId;
	}
}
