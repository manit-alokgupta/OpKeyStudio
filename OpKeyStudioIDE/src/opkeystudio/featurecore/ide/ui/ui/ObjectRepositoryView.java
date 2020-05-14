package opkeystudio.featurecore.ide.ui.ui;

import java.util.List;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
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

import opkeystudio.core.utils.CopyPasteOperation;
import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.core.utils.OpKeyStudioPreferences;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTable;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectAttributeTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol.ObjectRepositoryTreeItem;
import opkeystudio.featurecore.ide.ui.ui.superview.SuperComposite;
import opkeystudio.featurecore.ide.ui.ui.superview.events.ArtifactPersistListener;
import opkeystudio.featurecore.ide.ui.ui.superview.events.OpKeyGlobalLoadListenerDispatcher;
import opkeystudio.iconManager.OpKeyStudioIcons;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.dtoMaker.ORObjectMaker;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;
import pcloudystudio.appium.AppiumConfiguration;
import pcloudystudio.core.utils.notification.CustomNotificationUtil;
import pcloudystudio.featurecore.ui.dialog.AppiumSettingsDialog;
import pcloudystudio.featurecore.ui.dialog.DeviceConfigurationDialog;

public class ObjectRepositoryView extends SuperComposite {
	private Artifact artifact;
	private String objectId;
	private ObjectAttributeTable objectAttributeTable;
	private ObjectRepositoryTree objectRepositoryTree;
	private ToolItem saveObject;
	private ToolItem renameObject;
	private ToolItem deleteObject;
	private ToolItem refreshObject;
	private ToolItem intelliChooseObject;
	private ToolItem intelliChooseObjectAttribute;
	private ToolItem addObjectAttribute;
	private ToolItem deleteObjectAttribute;
	private ToolItem objectType;
	private ToolItem androidDeviceConfiguration;
	private MenuItem copyMenuItem;
	private MenuItem pasteMenuItem;
	private MenuItem renameMenuItem;
	private MenuItem deleteMenuItem;
	private ToolItem addParentObjectToolItem;
	private ToolItem addChildObjectToolItem;
	private boolean isParentObjectItemsListVisible;
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

	private ArtifactCodeView codedFunctionView;
	private MPart currentMPart;

	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		initArtifact();
		initObjectRepositoryUI();
		toggleSaveButton(false);
		toggleRenameButton(false);
		toggleDeleteButton(false);
		toggleAddAttributeButton(false);
		toggleDeleteAttributeButton(false);
		toggleParentObjectToolItem(true);
		toggleChildObjectToolItem(false);
		intelliChooseObject.setEnabled(false);
		intelliChooseObjectAttribute.setEnabled(false);
		addOpKeyGlobalListener();
		this.isParentObjectItemsListVisible = false;
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

	private void addOpKeyGlobalListener() {
		this.addOpKeyGlobalEventListener(new ArtifactPersistListener() {

			@Override
			public void handleGlobalEvent() {
				System.out.println("Object Repository Global Listner Called");
				handleSaveOnRefresh();
			}
		});
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
							item.getText(), objectRepositoryTree.getAllORObjects());
					setObjectId(orobject.getObject_id());
					objectRepositoryTree.getAllORObjects().add(orobject);
					toggleSaveButton(true);
					addParentObjectToolItem.setText("Add (" + menuItem + ")");
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
					ORObject selectedobject = treeItem.getORObject();
					ORObject orobject = new ORObjectMaker().getORObjectDTO(getArtifact(), getOrId(),
							selectedobject.getObject_id(), name, item.getText(),
							objectRepositoryTree.getAllORObjects());
					setObjectId(orobject.getObject_id());
					objectRepositoryTree.getAllORObjects().add(orobject);
					toggleSaveButton(true);
					addChildObjectToolItem.setText("Add (" + menuItem + ")");
					objectRepositoryTree.refreshObjectRepositories();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
		}
		return menu;
	}

	private void initObjectRepositoryUI() {
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

		new ToolItem(toolBar, SWT.SEPARATOR);

		addParentObjectToolItem = new ToolItem(toolBar, SWT.DROP_DOWN);
		addParentObjectToolItem.setText("Add(HtmlPage)");

		new ToolItem(toolBar, SWT.SEPARATOR);

		addChildObjectToolItem = new ToolItem(toolBar, SWT.DROP_DOWN);
		addChildObjectToolItem.setText("Add(Area)");

		Menu parentObjectMenu = createParentObjectCreationMenu(addParentObjectToolItem.getDisplay().getActiveShell());

		addParentObjectToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.ARROW && !isParentObjectItemsListVisible) {
					Rectangle rect = addParentObjectToolItem.getBounds();
					Point pt = new Point(rect.x, rect.y + rect.height);
					pt = toolBar.toDisplay(pt);
					parentObjectMenu.setLocation(pt.x, pt.y);
					parentObjectMenu.setVisible(true);
					isParentObjectItemsListVisible = true;
					return;
				} else if (e.detail == SWT.ARROW) {
					isParentObjectItemsListVisible = false;
					return;
				}
				String objectName = addParentObjectToolItem.getText().replaceAll("Add", "").replace("(", "")
						.replace(")", "").trim();
				System.out.println("Object Name " + objectName);
				String name = new Utilities().getRandomVariableName("New Node " + objectName);
				ORObject orobject = new ORObjectMaker().getORObjectDTO(getArtifact(), getOrId(), null, name, objectName,
						objectRepositoryTree.getAllORObjects());
				setObjectId(orobject.getObject_id());
				objectRepositoryTree.getAllORObjects().add(orobject);
				toggleSaveButton(true);
				objectRepositoryTree.refreshObjectRepositories();
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
					return;
				}
				String objectName = addChildObjectToolItem.getText().replaceAll("Add", "").replace("(", "")
						.replace(")", "").trim();
				System.out.println("Object Name " + objectName);
				String name = new Utilities().getRandomVariableName("New Node " + objectName);
				ObjectRepositoryTreeItem treeItem = objectRepositoryTree.getSelectedTreeItem();
				ORObject selectedobject = treeItem.getORObject();
				ORObject orobject = new ORObjectMaker().getORObjectDTO(getArtifact(), getOrId(),
						selectedobject.getObject_id(), name, objectName, objectRepositoryTree.getAllORObjects());
				setObjectId(orobject.getObject_id());
				objectRepositoryTree.getAllORObjects().add(orobject);
				toggleSaveButton(true);
				objectRepositoryTree.refreshObjectRepositories();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		saveObject = new ToolItem(toolBar, SWT.NONE);
		saveObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.SAVE_ICON));
		saveObject.setToolTipText("Save");

		new ToolItem(toolBar, SWT.SEPARATOR);

		renameObject = new ToolItem(toolBar, SWT.NONE);
		renameObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		renameObject.setToolTipText("Rename");

		new ToolItem(toolBar, SWT.SEPARATOR);

		deleteObject = new ToolItem(toolBar, SWT.NONE);
		deleteObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteObject.setToolTipText("Delete");

		new ToolItem(toolBar, SWT.SEPARATOR);

		refreshObject = new ToolItem(toolBar, SWT.NONE);
		refreshObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.REFRESH_ICON));
		refreshObject.setToolTipText("Refresh");

		new ToolItem(toolBar, SWT.SEPARATOR);
		androidDeviceConfiguration = new ToolItem(toolBar, SWT.NONE);
		androidDeviceConfiguration.setImage(
				ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.MOBILE_ADD_TO_OR_CAPTURED_IMAGE));
		androidDeviceConfiguration.setToolTipText("Device Configuration and Spy Android");
		new ToolItem(toolBar, SWT.SEPARATOR);

		intelliChooseObject = new ToolItem(toolBar, SWT.CHECK | SWT.BORDER);
		intelliChooseObject.setText("IntelliChoose");
		intelliChooseObject.setToolTipText("IntelliChoose");
		intelliChooseObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.GREEN_FLAG_ICON));

		objectRepositoryTree = new ObjectRepositoryTree(composite_3, SWT.BORDER, this);
		objectRepositoryTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectRepositoryTree.setBounds(0, 0, 85, 85);

		ServiceRepository.getInstance().setProjectTreeObject(objectRepositoryTree);
		Menu menu = new Menu(objectRepositoryTree);
		objectRepositoryTree.setMenu(menu);

		renameMenuItem = new MenuItem(menu, SWT.NONE);
		renameMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.RENAME_ICON));
		renameMenuItem.setText("Rename");

		deleteMenuItem = new MenuItem(menu, SWT.NONE);
		deleteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteMenuItem.setText("Delete");

		copyMenuItem = new MenuItem(menu, SWT.NONE);
		copyMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.COPY_ICON));
		copyMenuItem.setText("Copy");

		pasteMenuItem = new MenuItem(menu, SWT.NONE);
		pasteMenuItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.PASTE_ICON));
		pasteMenuItem.setText("Paste");

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

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		deleteObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		deleteObjectAttribute.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.DELETE_ICON));
		deleteObjectAttribute.setText("Delete");
		deleteObjectAttribute.setToolTipText("Delete Property");

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		objectType = new ToolItem(toolBar_1, SWT.NONE);
		objectType.setEnabled(false);

		new ToolItem(toolBar_1, SWT.SEPARATOR);

		intelliChooseObjectAttribute = new ToolItem(toolBar_1, SWT.CHECK | SWT.BORDER);
		intelliChooseObjectAttribute.setText("IntelliChoose");
		intelliChooseObjectAttribute.setToolTipText("IntelliChoose");
		intelliChooseObjectAttribute
				.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.GREEN_FLAG_ICON));

		objectAttributeTable = new ObjectAttributeTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION, this);
		objectAttributeTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		objectAttributeTable.setHeaderVisible(true);
		objectAttributeTable.setLinesVisible(true);
		objectAttributeTable.setHeaderBackground(SWTResourceManager.getColor(248, 248, 245));
		sashForm.setWeights(new int[] { 2, 1 });

		TabItem sourceCodeTabItem = new TabItem(tabFolder, SWT.NONE);
		sourceCodeTabItem.setText("SourceCode");
		sourceCodeTabItem.setImage(ResourceManager.getPluginImage("OpKeyStudio", OpKeyStudioIcons.CFL_ICON));
		Composite sourceCodeHolder = new Composite(tabFolder, SWT.NONE);
		sourceCodeHolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		sourceCodeTabItem.setControl(sourceCodeHolder);
		sourceCodeHolder.setLayout(new FillLayout(SWT.HORIZONTAL));
		ArtifactCodeView codedFunctionView = new ArtifactCodeView(sourceCodeHolder, SWT.NONE, this, false);
		setCodedFunctionView(codedFunctionView);

		intelliChooseObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ORObject orobject = objectRepositoryTree.getSelectedORObject();
				if (orobject != null) {
					intelliChooseObjectAttribute.setSelection(intelliChooseObject.getSelection());
					orobject.setUsesmartidentification(intelliChooseObject.getSelection());
					orobject.setModified(true);
					toggleSaveButton(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		intelliChooseObjectAttribute.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ORObject orobject = objectRepositoryTree.getSelectedORObject();
				if (orobject != null) {
					intelliChooseObject.setSelection(intelliChooseObjectAttribute.getSelection());
					orobject.setUsesmartidentification(intelliChooseObjectAttribute.getSelection());
					orobject.setModified(true);
					toggleSaveButton(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		objectRepositoryTree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				ObjectRepositoryTreeItem item = (ObjectRepositoryTreeItem) objectRepositoryTree.getSelection()[0];
				renderObjectAttributeProperty(item);
				if (item.getORObject() != null) {
					setObjectId(item.getORObject().getObject_id());
					intelliChooseObject.setEnabled(true);
					intelliChooseObjectAttribute.setEnabled(true);
					toggleDeleteButton(true);
					toggleRenameButton(true);
					toggleAddAttributeButton(true);
					toggleRenameMenuItem(true);
					if (item.getORObject().getParent_object_id() != null) {
						toggleCopyMenuItem(true);
					} else {
						toggleCopyMenuItem(false);
					}
					if (item.getORObject().getParent_object_id() == null) {
						togglePasteMenuItem(true);
					} else {
						togglePasteMenuItem(false);
					}
					toggleDeleteMenuItem(true);
					if (item.getORObject().getParent_object_id() == null) {
						toggleChildObjectToolItem(true);
					} else {
						toggleChildObjectToolItem(false);
					}
					toggleObjectTypeMenuItem(true);
					objectType.setText("Object Type: " + objectRepositoryTree.getSelectedORObject().getOpkeytype());
					intelliChooseObject
							.setSelection(objectRepositoryTree.getSelectedORObject().isUsesmartidentification());
					intelliChooseObjectAttribute
							.setSelection(objectRepositoryTree.getSelectedORObject().isUsesmartidentification());
				} else {
					intelliChooseObject.setEnabled(false);
					intelliChooseObjectAttribute.setEnabled(false);
					toggleDeleteButton(false);
					toggleRenameButton(false);
					toggleDeleteAttributeButton(false);
					toggleAddAttributeButton(false);
					toggleRenameMenuItem(false);
					toggleCopyMenuItem(false);
					togglePasteMenuItem(false);
					toggleDeleteMenuItem(false);
					toggleChildObjectToolItem(false);
					toggleObjectTypeMenuItem(false);
					objectType.setText("");
					intelliChooseObject.setSelection(false);
					intelliChooseObjectAttribute.setSelection(false);
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
				toggleDeleteAttributeButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		saveObject.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAll();
				OpKeyGlobalLoadListenerDispatcher.getInstance().fireAllSuperCompositeGlobalListener();
				CustomNotificationUtil.openInformationNotification("OpKey", "Saved!");
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
				handleSaveOnRefresh();
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
				selectedProperty.setAdded(false);
				selectedProperty.setModified(false);
				selectedProperty.setDeleted(true);
				objectAttributeTable.renderObjectAttributes();
				toggleSaveButton(true);
				CustomNotificationUtil.openInformationNotification("OpKey", "Property Deleted!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		androidDeviceConfiguration.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				AppiumConfiguration.getInstance();
				if (AppiumConfiguration.getHostAddress() == null
						&& OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address") != null) {
					AppiumConfiguration
							.setHostAddress(OpKeyStudioPreferences.getPreferences().getBasicSettings("host_address"));
					AppiumConfiguration
							.setPort(OpKeyStudioPreferences.getPreferences().getBasicSettings("port_number"));
					AppiumConfiguration.setAppiumDirectory(
							OpKeyStudioPreferences.getPreferences().getBasicSettings("appium_directory"));
				}

				if (AppiumConfiguration.getPort() == null || AppiumConfiguration.getHostAddress() == null
						|| AppiumConfiguration.getAppiumDirectory() == null) {
					openAppiumSettingDialog();
				} else {
					openDeviceConfigurationDialog();
				}
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
					CustomNotificationUtil.openInformationNotification("OpKey", "Property Added!");
					ObjectAttributeProperty attrProp = new ORObjectMaker().getNewObjectAttributeProperty(orobject,
							objectAttributeTable.getObjectPropertiesData());
					objectAttributeTable.getObjectPropertiesData().add(attrProp);
					objectAttributeTable.renderObjectAttributes();
					objectAttributeTable.selectDefaultRowByCursor(objectAttributeTable.cursor, 0);
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
				ORObject selectedORObject = selectedTreeItem.getORObject();
				if (selectedORObject == null) {
					return;
				}
				CopyPasteOperation.getInstance().setOrObject(selectedORObject);
				toggleCopyMenuItem(false);
				togglePasteMenuItem(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		pasteMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectRepositoryTreeItem selectedItem = (ObjectRepositoryTreeItem) objectRepositoryTree
						.getSelection()[0];
				ORObject selectedORObject = selectedItem.getORObject();
				ORObject pasteORobject = CopyPasteOperation.getInstance().getOrObject();
				if (pasteORobject == null) {
					return;
				}
				String objectName = pasteORobject.getName();
				objectName = objectRepositoryTree.getUniqueTreeItemName(selectedItem, objectName);
				System.out.println(">>Object Name " + objectName);
				ORObject orobjectReplica = new ORObjectMaker().createORObjectReplica(getArtifact(), objectName,
						selectedORObject, pasteORobject, objectRepositoryTree.getAllORObjects());
				objectRepositoryTree.getAllORObjects().add(orobjectReplica);
				objectRepositoryTree.refreshObjectRepositories();
				togglePasteMenuItem(false);
				toggleSaveButton(true);
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

	private void handleSaveOnRefresh() {
		try {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			if (saveObject.isEnabled()) {
				opkeystudio.core.utils.Utilities.getInstance().activateMpart(getCurrentMPart());
				toggleDeleteAttributeButton(false);
				toggleAddAttributeButton(false);

				int result = CustomNotificationUtil.openConfirmDialog("OpKey", "Do you want to save changes?");
				System.out.println("Result Code " + result);
				if (result == 2) {
					return;
				}
				if (result != 0) {
					toggleSaveButton(false);
					objectRepositoryTree.renderObjectRepositories();
					return;
				}
				List<ORObject> allors = objectRepositoryTree.getAllORObjects();
				new ObjectRepositoryApi().saveORObjects(getArtifact(), allors);
				toggleSaveButton(false);
				objectRepositoryTree.renderObjectRepositories();
				toggleSaveButton(false);
				CustomNotificationUtil.openInformationNotification("OpKey", "Refreshed!");
			}
			intelliChooseObject.setEnabled(false);
			intelliChooseObjectAttribute.setEnabled(false);
			toggleDeleteAttributeButton(false);
			toggleAddAttributeButton(false);
			objectRepositoryTree.renderObjectRepositories();
			getCodedFunctionView().refreshORCode();
			CustomNotificationUtil.openInformationNotification("OpKey", "Refreshed!");
		} finally {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}

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

	public void toggleRenameMenuItem(boolean status) {
		renameMenuItem.setEnabled(status);
	}

	public void togglePasteMenuItem(boolean status) {
		pasteMenuItem.setEnabled(status);
	}

	public void toggleDeleteMenuItem(boolean status) {
		deleteMenuItem.setEnabled(status);
	}

	public void toggleObjectTypeMenuItem(boolean status) {
		objectType.setEnabled(status);
	}

	private void openAppiumSettingDialog() {
		new AppiumSettingsDialog(this.getShell(), SWT.NONE, this).open();
	}

	private void openDeviceConfigurationDialog() {
		new DeviceConfigurationDialog(this.getShell(), SWT.NONE, this).open();
	}

	public void renameFunction() {
		try {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
			ORObject obRepo = selectedTreeItem.getORObject();
			String input = CustomNotificationUtil.openInputDialog("OpKey", "Rename: " + obRepo.getName(),
					obRepo.getName());

			if (input == null) {
				return;
			}
			if (input.trim().isEmpty()) {
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Invalid Input",
						"Please Enter Some Value");
				return;
			}
			obRepo.setName(input.trim());
			obRepo.setModified(true);
			toggleSaveButton(true);
			objectRepositoryTree.refreshObjectRepositories();
			CustomNotificationUtil.openInformationNotification("OpKey", "Renamed to " + obRepo.getName() + "!");
		} finally {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}

	}

	public void deleteFunction() {
		try {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_WAIT));
			boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "OpKey",
					"Do you want to delete '" + objectRepositoryTree.getSelectedTreeItem().getText() + "'?");
			if (!result) {
				return;
			}

			ObjectRepositoryTreeItem selectedTreeItem = objectRepositoryTree.getSelectedTreeItem();
			obRepo = selectedTreeItem.getORObject();
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
					ORObject orobject = item.getORObject();
					boolean isused = new ObjectRepositoryApiUtilities().isORObjectUsed(orobject);
					if (isused) {
						new MessageDialogs().openInformationDialog("Can't delete ORObject", "Unable to delete "
								+ obRepo.getName() + " as it is being used in some higher components");
						return;
					}
				}
			}
			objectAttributeTable.clearAllDatas();
			System.out.println("Deleting.. " + obRepo.getObject_id());
			obRepo.setDeleted(true);
			toggleSaveButton(true);
			objectRepositoryTree.refreshObjectRepositories();
			CustomNotificationUtil.openInformationNotification("OpKey", "Deleted " + obRepo.getName() + "!");
		} finally {
			getParent().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));
		}

	}

	private void renderObjectAttributeProperty(ObjectRepositoryTreeItem item) {
		if (item.getORObject() == null) {
			objectAttributeTable.clearAllDatas();
			return;
		}
		if (item.getORObject() != null) {
			String objectId = item.getORObject().getObject_id();
			if (item.getORObject().getObjectAttributesProperty().size() == 0) {
				System.out.println("Executing Object Property Fetch");
				item.getORObject()
						.setObjectAttributesProperty(new ObjectRepositoryApi().getObjectAttributeProperty(objectId));
			}
			objectAttributeTable.setControlData(item.getORObject().getObjectAttributesProperty());
			objectAttributeTable.renderObjectAttributes();
		}
	}

	public void saveAll() {
		List<ORObject> allors = objectRepositoryTree.getAllORObjects();
		new ObjectRepositoryApi().saveORObjects(getArtifact(), allors);
		toggleSaveButton(false);
		objectRepositoryTree.renderObjectRepositories();
		getCodedFunctionView().refreshORCode();
	}

	private void initArtifact() {
		MPart mpart = opkeystudio.core.utils.Utilities.getInstance().getActivePart();
		this.setCurrentMPart(mpart);
		this.artifact = (Artifact) mpart.getTransientData().get("opkeystudio.artifactData");
	}

	public Artifact getCurrentArtifact() {
		return GlobalLoader.getInstance().getArtifactById(getArtifact().getId());
	}

	public Artifact getArtifact() {
		return this.artifact;
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

	public ArtifactCodeView getCodedFunctionView() {
		return codedFunctionView;
	}

	public void setCodedFunctionView(ArtifactCodeView codedFunctionView) {
		this.codedFunctionView = codedFunctionView;
	}

	public MPart getCurrentMPart() {
		return currentMPart;
	}

	public void setCurrentMPart(MPart currentMPart) {
		this.currentMPart = currentMPart;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
