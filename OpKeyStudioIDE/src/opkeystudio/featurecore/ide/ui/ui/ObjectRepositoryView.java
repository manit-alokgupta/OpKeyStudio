package opkeystudio.featurecore.ide.ui.ui;

import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTable;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTreeItem;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.dtoMaker.ORObjectMaker;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

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
	private String[] parentObjectTypes = new String[] { "Html Page", "Frame", "Page" };
	private String[] childObjectTypes = new String[] { "Area", "Base", "Button", "Checkbox", "Clickable Image Map",
			"Color Picker", "Custom", "Custom Object", "Datetime Picker", "Div", "Drop Down List", "Edit Field",
			"Email Address", "File Field", "Form", "Frame", "Frameset", "Heading 1", "Heading 2", "Heading 3",
			"Heading 4", "Heading 5", "Heading 6", "Hidden", "IFrame", "Image", "Input Button", "Input Image", "Label",
			"Li", "Link", "List", "Meta", "Number Picker", "Object", "Option", "Paragraph", "Password",
			"Preformated Text", "Radio", "Reset", "Search", "Span", "Submit", "Table", "Table Cell", "Table Row",
			"Telephone", "Text Field", "Textarea", "Time Picker", "UI", "Url", "Webelement", "Week Picker" };

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */

	private CodedFunctionView codedFunctionView;

	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		ObjectRepositoryUI();
		toggleSaveButton(false);
		toggleRenameButton(false);
		toggleDeleteButton(false);
		toggleAddAttributeButton(false);
		toggleDeleteAttributeButton(false);
		toggleParentObjectToolItem(true);
		toggleChildObjectToolItem(false);
	}

	public ObjectRepositoryTree getObjectRepositoryTree() {
		return this.objectRepositoryTree;
	}

	public void toggleParentObjectToolItem(boolean status) {
		addParentObjectToolItem.setEnabled(status);
	}

	public void toggleChildObjectToolItem(boolean status) {
		addChildObjectToolItem.setEnabled(status);
	}

	private Menu createParentObjectCreationMenu(Control parent) {
		Menu menu = new Menu(parent);
		for (String menuItem : parentObjectTypes) {
			MenuItem item = new MenuItem(menu, 0);
			item.setText(menuItem);
			item.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					String name = new Utilities().getRandomVariableName("New Node " + item.getText());
					ORObject orobject = new ORObjectMaker().getORObjectDTO(getArtifact(), getOrId(), null, name,
							item.getText(), objectRepositoryTree.getObjectRepositoriesData());
					objectRepositoryTree.getObjectRepositoriesData().add(orobject);
					toggleSaveButton(true);
					objectRepositoryTree.refreshObjectRepositories();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

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
					String name = new Utilities().getRandomVariableName("New Node " + item.getText());
					ObjectRepositoryTreeItem treeItem = objectRepositoryTree.getSelectedTreeItem();
					ORObject selectedobject = treeItem.getObjectRepository();
					ORObject orobject = new ORObjectMaker().getORObjectDTO(getArtifact(), getOrId(),
							selectedobject.getObject_id(), name, item.getText(),
							objectRepositoryTree.getObjectRepositoriesData());
					objectRepositoryTree.getObjectRepositoriesData().add(orobject);
					toggleSaveButton(true);
					objectRepositoryTree.refreshObjectRepositories();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
		}
		return menu;
	}

	@SuppressWarnings("unused")
	public void ObjectRepositoryUI() {
		setLayout(new FillLayout(SWT.HORIZONTAL));
		TabFolder tabFolder = new TabFolder(this, SWT.BOTTOM);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Object Repository");
		tabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.OR_ICON));
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

			}
		});

		saveObject = new ToolItem(toolBar, SWT.NONE);
		saveObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_ICON));
		saveObject.setToolTipText("Save");

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		renameObject = new ToolItem(toolBar, SWT.NONE);
		renameObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		renameObject.setToolTipText("Rename");

		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.SEPARATOR);

		deleteObject = new ToolItem(toolBar, SWT.NONE);
		deleteObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteObject.setToolTipText("Delete");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		refreshObject = new ToolItem(toolBar, SWT.NONE);
		refreshObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));
		refreshObject.setToolTipText("Refresh");

		objectRepositoryTree = new ObjectRepositoryTree(composite_3, SWT.BORDER, this);
		objectRepositoryTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectRepositoryTree.setBounds(0, 0, 85, 85);

		ServiceRepository.getInstance().setProjectTreeObject(objectRepositoryTree);
		Menu menu = new Menu(objectRepositoryTree);
		objectRepositoryTree.setMenu(menu);

		cutMenuItem = new MenuItem(menu, SWT.CASCADE);
		cutMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CUT_ICON));
		cutMenuItem.setText("Cut");

		copyMenuItem = new MenuItem(menu, SWT.NONE);
		copyMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.COPY_ICON));
		copyMenuItem.setText("Copy");

		pasteMenuItem = new MenuItem(menu, SWT.NONE);
		pasteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.PASTE_ICON));
		pasteMenuItem.setText("Paste");

		renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		renameMenuItem.setText("Rename");

		deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteMenuItem.setText("Delete");

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_1.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		addObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		addObjectAttribute.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.ADD_ICON));
		addObjectAttribute.setText("Add");
		addObjectAttribute.setToolTipText("Add Property");

		ToolItem toolItem_6 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		deleteObjectAttribute.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteObjectAttribute.setText("Delete");
		deleteObjectAttribute.setToolTipText("Delete Property");

		objectAttributeTable = new ObjectAttributeTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION, this);
		objectAttributeTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectAttributeTable.setHeaderVisible(true);
		objectAttributeTable.setLinesVisible(true);
		sashForm.setWeights(new int[] { 2, 1 });

		TabItem sourceCodeTabItem = new TabItem(tabFolder, SWT.NONE);
		sourceCodeTabItem.setText("SourceCode");
		sourceCodeTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		Composite sourceCodeHolder = new Composite(tabFolder, SWT.NONE);
		sourceCodeHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sourceCodeTabItem.setControl(sourceCodeHolder);
		sourceCodeHolder.setLayout(new FillLayout(SWT.HORIZONTAL));
		CodedFunctionView codedFunctionView = new CodedFunctionView(sourceCodeHolder, SWT.NONE, this, false);
		setCodedFunctionView(codedFunctionView);

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

					toggleDeleteMenuItem(true);
					if (item.getExpanded()) {
						toggleCutMenuItem(false);
					} else {
						toggleCutMenuItem(true);
					}
					if (item.getObjectRepository().getParent_object_id() == null) {
						toggleChildObjectToolItem(true);
					} else {
						toggleChildObjectToolItem(false);
					}
				} else {
					toggleDeleteButton(false);
					toggleRenameButton(false);
					toggleAddAttributeButton(false);
					toggleRenameMenuItem(false);
					toggleCopyMenuItem(false);
					togglePasteMenuItem(false);
					toggleCutMenuItem(false);
					toggleDeleteMenuItem(false);
					toggleChildObjectToolItem(false);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		toolBar.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {
				}

			}

			@Override
			public void mouseDown(MouseEvent e) {

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {

			}
		});

		objectAttributeTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectAttributeTableItem oati = (ObjectAttributeTableItem) objectAttributeTable.getSelection()[0];
				System.out.println(oati.getObjectAttributeData().getProperty());
				toggleDeleteAttributeButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		saveObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				saving();
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
				if (saveObject.isEnabled()) {

					toggleDeleteAttributeButton(false);
					toggleAddAttributeButton(false);
					boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "OpKey",
							"Do you want to save changes?");
					if (!result) {
						toggleSaveButton(false);
						objectRepositoryTree.renderObjectRepositories();
						return;
					}
					List<ORObject> allors = objectRepositoryTree.getObjectRepositoriesData();
					new ObjectRepositoryApi().saveORObjects(getArtifact(), allors);
					toggleSaveButton(false);
					objectRepositoryTree.renderObjectRepositories();
					toggleSaveButton(false);
				}

				toggleDeleteAttributeButton(false);
				toggleAddAttributeButton(false);
				objectRepositoryTree.renderObjectRepositories();
				getCodedFunctionView().refreshORCode();

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
				boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "OpKey",
						"Do you want to Delete");
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
					ObjectAttributeProperty attrProp = new ORObjectMaker().getNewObjectAttributeProperty(orobject,
							objectAttributeTable.getObjectPropertiesData());
					objectAttributeTable.getObjectPropertiesData().add(attrProp);
					objectAttributeTable.renderObjectAttributes();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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

			}
		});

		pasteMenuItem.addSelectionListener(new SelectionListener() {

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
				deleteFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		renameMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				renameFunction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

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
		InputDialog input = new InputDialog(Display.getCurrent().getActiveShell(), "OpKey", "Enter name to rename",
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
		boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "OpKey",
				"Do you want to delete '" + objectRepositoryTree.getSelectedTreeItem().getText() + "'?");
		if (!result) {
			return;
		}

		ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
		obRepo = selectedTreeItem.getObjectRepository();
		boolean isUsed = new ObjectRepositoryApiUtilities().isORObjectUsed(obRepo);
		if (isUsed) {
			new MessageDialogs().openInformationDialog("Can't delete ORObject",
					"Unable to delete " + obRepo.getName() + " as it is being used in some higher components");
			return;
		}
		if (obRepo.getParent_object_id() == null) {
			TreeItem[] treeItems = selectedTreeItem.getItems();
			for (TreeItem treeItem : treeItems) {
				ObjectRepositoryTreeItem item = (ObjectRepositoryTreeItem) treeItem;
				ORObject orobject = item.getObjectRepository();
				boolean isused = new ObjectRepositoryApiUtilities().isORObjectUsed(orobject);
				if (isused) {
					new MessageDialogs().openInformationDialog("Can't delete ORObject",
							"Unable to delete " + obRepo.getName() + " as it is being used in some higher components");
					return;
				}
			}
		}
		System.out.println("Deleting.. " + obRepo.getObject_id());
		obRepo.setDeleted(true);
		toggleSaveButton(true);
		objectRepositoryTree.refreshObjectRepositories();
	}

	private void renderObjectAttributeProperty(ObjectRepositoryTreeItem item) {
		if (item.getObjectRepository() != null) {
			String objectId = item.getObjectRepository().getObject_id();
			if (item.getObjectRepository().getObjectAttributesProperty().size() == 0) {
				System.out.println("Executing Object Property Fetch");
				item.getObjectRepository()
						.setObjectAttributesProperty(new ObjectRepositoryApi().getObjectAttributeProperty(objectId));
			}
			objectAttributeTable.setControlData(item.getObjectRepository().getObjectAttributesProperty());
			objectAttributeTable.renderObjectAttributes();
		}
	}

	public void saving() {
		List<ORObject> allors = objectRepositoryTree.getObjectRepositoriesData();
		new ObjectRepositoryApi().saveORObjects(getArtifact(), allors);
		toggleSaveButton(false);
		objectRepositoryTree.renderObjectRepositories();
		getCodedFunctionView().refreshORCode();
	}

	public Artifact getArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		Artifact artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
		return artifact;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public String getOrId() {
		return orId;
	}

	public ToolItem getSaveButton() {
		return this.saveObject;
	}

	public void setOrId(String orId) {
		this.orId = orId;
	}

	public CodedFunctionView getCodedFunctionView() {
		return codedFunctionView;
	}

	public void setCodedFunctionView(CodedFunctionView codedFunctionView) {
		this.codedFunctionView = codedFunctionView;
	}
}
