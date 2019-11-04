package opkeystudio.featurecore.ide.ui.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.featurecore.ide.ui.customcontrol.ObjectAttributeTable;
import opkeystudio.featurecore.ide.ui.customcontrol.ObjectAttributeTableItem;
import opkeystudio.featurecore.ide.ui.customcontrol.ObjectRepositoryTree;
import opkeystudio.featurecore.ide.ui.customcontrol.ObjectRepositoryTreeItem;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class ObjectRepositoryView extends Composite {
	private ObjectAttributeTable table;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ObjectRepositoryView(Composite parent, int style) {
		super(parent, style);
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		setLayout(fillLayout);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setSashWidth(5);
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		sashForm_1.setSashWidth(0);
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		FillLayout fillLayout_1 = new FillLayout(SWT.HORIZONTAL);
		composite_3.setLayout(fillLayout_1);
		ToolBar toolBar = new ToolBar(composite_3, SWT.FLAT | SWT.RIGHT);
		ToolItem saveObject = new ToolItem(toolBar, SWT.NONE);
		saveObject.setText("Save");
		saveObject.setEnabled(false);

		ToolItem renameObject = new ToolItem(toolBar, SWT.NONE);
		renameObject.setText("Rename");
		renameObject.setEnabled(false);

		ToolItem deleteObject = new ToolItem(toolBar, SWT.NONE);
		deleteObject.setText("Delete");
		deleteObject.setEnabled(false);

		ToolItem refreshObject = new ToolItem(toolBar, SWT.NONE);
		refreshObject.setText("Refresh");

		Composite composite_4 = new Composite(sashForm_1, SWT.BORDER);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		ObjectRepositoryTree tree = new ObjectRepositoryTree(composite_4, SWT.BORDER);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		sashForm_1.setWeights(new int[] { 1, 15 });

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_2 = new SashForm(composite_2, SWT.VERTICAL);

		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));

		ToolBar toolBar_1 = new ToolBar(composite_5, SWT.FLAT | SWT.RIGHT);

		ToolItem addObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		addObjectAttribute.setText("Add");

		ToolItem deleteObjectAttribute = new ToolItem(toolBar_1, SWT.NONE);
		deleteObjectAttribute.setText("Delete");
		deleteObjectAttribute.setEnabled(false);

		Composite composite_6 = new Composite(sashForm_2, SWT.BORDER);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new ObjectAttributeTable(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		sashForm_2.setWeights(new int[] { 1, 15 });
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

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ObjectAttributeTableItem oati = (ObjectAttributeTableItem) table.getSelection()[0];
				System.out.println(oati.getObjectAttributeData().getProperty());
				deleteObjectAttribute.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		saveObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		renameObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deleteObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		refreshObject.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
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
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
