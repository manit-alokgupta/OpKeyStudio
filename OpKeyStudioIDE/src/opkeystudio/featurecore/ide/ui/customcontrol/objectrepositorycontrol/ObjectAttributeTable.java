package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomLabel;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.featurecore.ide.ui.customcontrol.imagetools.ImageViewer;
import opkeystudio.featurecore.ide.ui.ui.ObjectRepositoryView;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;

public class ObjectAttributeTable extends CustomTable {
	private boolean paintCalled = false;
	private ObjectAttributeTable thisTable;
	private ObjectRepositoryView parentObjectRepositoryView;

	private Composite parentComposite;

	public ObjectAttributeTable(Composite parent, int style, ObjectRepositoryView parentView) {
		super(parent, style);
		this.setParentComposite(parent);
		init();
		thisTable = this;
		this.setParentObjectRepositoryView(parentView);
	}

	private void init() {
		String[] tableHeaders = { "Property", "Value", "IsUsed", "IsRegex" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
			column.setWidth(100);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				if (paintCalled) {
					// return;
				}
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 4);
				}
				paintCalled = true;
			}
		});

		TableCursor cursor = new TableCursor(this, 0);
		ControlEditor controlEditor = new ControlEditor(cursor);
		controlEditor.grabHorizontal = true;
		controlEditor.grabVertical = true;
		cursor.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// thisTable.deselectAll();
				ObjectAttributeTableItem selectedTableItem = (ObjectAttributeTableItem) cursor.getRow();
				ObjectAttributeProperty objectAttributeProperty = selectedTableItem.getObjectAttributeData();
				objectAttributeProperty.setOldPropertyName(objectAttributeProperty.getProperty());
				int selectedColumn = cursor.getColumn();
				CustomText text = new CustomText(cursor, 0);
				if (selectedColumn == 0) {
					text.setText(objectAttributeProperty.getProperty());
				}
				if (selectedColumn == 1) {
					text.setText(objectAttributeProperty.getValue());
				}
				text.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						String propertyName = objectAttributeProperty.getProperty();
						boolean isUnique = isColumnDataUnique(propertyName, 0);
						if (isUnique == false) {
							objectAttributeProperty.setProperty(objectAttributeProperty.getOldPropertyName());
							text.setText(objectAttributeProperty.getProperty());
							new MessageDialogs().openErrorDialog("OpKey", "Attriute named as '" + propertyName
									+ "' already exists. Please provide a new name.");
							return;
						}
						text.dispose();
					}

					@Override
					public void focusGained(FocusEvent e) {

					}
				});

				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						selectedTableItem.setText(selectedColumn, text.getText());
						objectAttributeProperty.setModified(true);
						getParentObjectRepositoryView().toggleSaveButton(true);
						if (selectedColumn == 0) {
							objectAttributeProperty.setProperty(text.getText());
						}
						if (selectedColumn == 1) {
							objectAttributeProperty.setValue(text.getText());
						}
					}
				});
				controlEditor.setEditor(text);
				getParentObjectRepositoryView().toggleDeleteAttributeButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void addObjectPropetiesData(List<ObjectAttributeProperty> obProperties) {
		super.setControlData(obProperties);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectAttributeProperty> getObjectPropertiesData() {
		if (super.getControlData() == null) {
			return new ArrayList<ObjectAttributeProperty>();
		}
		return (List<ObjectAttributeProperty>) super.getControlData();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private List<Control> allTableEditors = new ArrayList<Control>();

	private void addTableEditor(ObjectAttributeTableItem item) {
		ObjectAttributeProperty attrProperty = item.getObjectAttributeData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		TableEditor editor3 = getTableEditor();
		CustomButton isUsedButton = new CustomButton(this, SWT.CHECK);
		CustomButton isRegexButton = new CustomButton(this, SWT.CHECK);
		isUsedButton.setSelection(attrProperty.isIsused());
		isRegexButton.setSelection(attrProperty.isIsregex());

		isUsedButton.setControlData(attrProperty);
		isRegexButton.setControlData(attrProperty);
		isUsedButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				thisTable.setSelection(item);
				CustomButton button = (CustomButton) e.getSource();
				ObjectAttributeProperty oatr = (ObjectAttributeProperty) button.getControlData();
				oatr.setModified(true);
				oatr.setIsused(button.getSelection());
				getParentObjectRepositoryView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		isRegexButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				thisTable.select(0);
				CustomButton button = (CustomButton) e.getSource();
				ObjectAttributeProperty oatr = (ObjectAttributeProperty) button.getControlData();
				oatr.setModified(true);
				oatr.setIsregex(button.getSelection());
				getParentObjectRepositoryView().toggleSaveButton(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		if (attrProperty.getProperty().toLowerCase().equals("objectimage")
				|| attrProperty.getProperty().toLowerCase().equals("image")) {
			String query = String.format("SELECT VALUE FROM or_object_properties WHERE Property_Id='%s'",
					attrProperty.getProperty_id());
			byte[] bytes = QueryExecutor.getInstance().executeQueryWithByteData(query);
			if (bytes == null) {
				return;
			}
			ByteArrayInputStream is = new ByteArrayInputStream(bytes);
			ImageData imgData = new ImageData(is);
			Image img = new Image(this.getDisplay(), imgData);
			CustomLabel label = new CustomLabel(this, SWT.CENTER);
			label.setText("View Image");
			label.setControlData(img);

			label.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					Image image = (Image) label.getControlData();
					ImageViewer viewer = new ImageViewer(getParentComposite().getShell(), image, 0);
					viewer.open();

				}

				@Override
				public void mouseDown(MouseEvent e) {

				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {

				}
			});
			editor3.setEditor(label, item, 1);
			allTableEditors.add(editor3.getEditor());
		}

		editor2.setEditor(isUsedButton, item, 2);
		editor1.setEditor(isRegexButton, item, 3);

		allTableEditors.add(editor1.getEditor());
		allTableEditors.add(editor2.getEditor());
	}

	private void disposeAllTableEditors() {
		for (Control editor : allTableEditors) {
			editor.dispose();
		}
	}

	public ObjectAttributeTableItem getSelectedTableItem() {
		return (ObjectAttributeTableItem) this.getSelection()[0];
	}

	public ObjectAttributeProperty getSelectedObjectAttributeProperty() {
		ObjectAttributeTableItem selectedItem = getSelectedTableItem();
		return selectedItem.getObjectAttributeData();
	}

	public void renderObjectAttributes() {
		disposeAllTableEditors();
		this.removeAll();
		List<ObjectAttributeProperty> objectProperties = getObjectPropertiesData();
		for (ObjectAttributeProperty attributeProperty : objectProperties) {
			if (attributeProperty.isDeleted() == false) {
				ObjectAttributeTableItem oati = new ObjectAttributeTableItem(this, 0);
				oati.setText(new String[] { attributeProperty.getProperty(), attributeProperty.getValue(), "", "" });
				oati.setObjectAttributeData(attributeProperty);
				addTableEditor(oati);
			}
		}
		getParentObjectRepositoryView().toggleAddAttributeButton(true);
		getParentObjectRepositoryView().toggleDeleteAttributeButton(false);
		selectLastRow();
	}

	public void clearAllDatas() {
		disposeAllTableEditors();
		this.removeAll();
	}

	public ObjectRepositoryView getParentObjectRepositoryView() {
		return parentObjectRepositoryView;
	}

	public void setParentObjectRepositoryView(ObjectRepositoryView parentObjectRepositoryView) {
		this.parentObjectRepositoryView = parentObjectRepositoryView;
	}

	public Composite getParentComposite() {
		return parentComposite;
	}

	public void setParentComposite(Composite parentComposite) {
		this.parentComposite = parentComposite;
	}
}
