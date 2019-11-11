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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
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

public class ObjectRepositoryView extends Composite {
//	private Table table;
	private ObjectAttributeTable table;
	private ToolItem saveObject;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);

		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_3.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite_3, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolBar.setBounds(0, 0, 87, 23);

		saveObject = new ToolItem(toolBar, SWT.NONE);
		saveObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/save_icon.png"));
		saveObject.setText("Save");
		saveObject.setToolTipText("Save");

		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem renameObject = new ToolItem(toolBar, SWT.NONE);
		renameObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/rename.png"));
		renameObject.setText("Rename");
		renameObject.setToolTipText("Rename");

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem deleteObject = new ToolItem(toolBar, SWT.NONE);
		deleteObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteObject.setText("Delete");
		deleteObject.setToolTipText("Delete");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem refreshObject = new ToolItem(toolBar, SWT.NONE);
		refreshObject.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/refresh_icon.png"));
		refreshObject.setText("Refresh");
		refreshObject.setToolTipText("Refresh");

		ObjectRepositoryTree tree = new ObjectRepositoryTree(composite_3, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree.setBounds(0, 0, 85, 85);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite_1.setLayout(new GridLayout(1, false));

		ToolBar toolBar_1 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		toolBar_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem addObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		addObjectAttribute.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/add_icon.png"));
		addObjectAttribute.setText("Add");
		addObjectAttribute.setToolTipText("Add");

		ToolItem toolItem_3 = new ToolItem(toolBar_1, SWT.SEPARATOR);

		ToolItem deleteObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		deleteObjectAttribute.setImage(ResourceManager.getPluginImage("OpKeyStudio", "icons/testcase_icons/delete_icon.png"));
		deleteObjectAttribute.setText("Delete");
		deleteObjectAttribute.setToolTipText("Delete");

		table = new ObjectAttributeTable(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		sashForm.setWeights(new int[] { 2, 1 });

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectRepositoryTreeItem item = (ObjectRepositoryTreeItem) tree.getSelection()[0];
				renderObjectAttributeProperty(item);
				if (item.getObjectRepository() != null) {
					deleteObject.setEnabled(true);
					renameObject.setEnabled(true);
				} else {
					deleteObject.setEnabled(false);
					renameObject.setEnabled(false);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectRepositoryTreeItem item = (ObjectRepositoryTreeItem) tree.getSelection()[0];
				renderObjectAttributeProperty(item);
				if (item.getObjectRepository() != null) {
					deleteObject.setEnabled(true);
					renameObject.setEnabled(true);
				} else {
					deleteObject.setEnabled(false);
					renameObject.setEnabled(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectAttributeTableItem oati = (ObjectAttributeTableItem) table.getSelection()[0];
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
				List<ORObject> allors = tree.getObjectRepositoriesData();
				try {
					new ObjectRepositoryApi().saveORObjects(allors);
					toggleSaveButton(false);
					tree.renderObjectRepositories();
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

				ObjectRepositoryTreeItem selectedTreeItem = tree.getSelectedTreeItem();
				ORObject obRepo = selectedTreeItem.getObjectRepository();
				InputDialog input = new InputDialog(Display.getCurrent().getActiveShell(), "Rename",
						"Enter name to rename", obRepo.getName(), null);

				if (input.open() != InputDialog.OK) {
					return;
				}
				if (input.getValue().trim().isEmpty()) {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Invalid Input",
							"Please Enter Some Value");
					return;
				}
				obRepo.setName(input.getValue());
				obRepo.setModified(true);
				toggleSaveButton(true);
				tree.refreshObjectRepositories();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		refreshObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				toggleSaveButton(false);
				tree.renderObjectRepositories();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		deleteObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean result = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Delete",
						"Please press OK to Delete");
				if (!result) {
					return;
				}
				ObjectRepositoryTreeItem selectedTreeItem = tree.getSelectedTreeItem();
				ORObject obRepo = selectedTreeItem.getObjectRepository();
				System.out.println("Deleting.. " + obRepo.getObject_id());
				obRepo.setDeleted(true);
				toggleSaveButton(true);
				tree.refreshObjectRepositories();
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
				ObjectAttributeProperty selectedProperty = table.getSelectedObjectAttributeProperty();
				System.out.println("Deleting " + selectedProperty.getOr_id());
				selectedProperty.setDeleted(true);

				try {
					table.renderObjectAttributes();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	private void toggleSaveButton(boolean status) {
		saveObject.setEnabled(status);
	}

	private void renderObjectAttributeProperty(ObjectRepositoryTreeItem item) {
		if (item.getObjectRepository() != null) {
			String objectId = item.getObjectRepository().getObject_id();
			try {
				List<ObjectAttributeProperty> objectAttributes = new ObjectRepositoryApi()
						.getObjectAttributeProperty(objectId);
				table.setControlData(objectAttributes);
				table.renderObjectAttributes();
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
}
