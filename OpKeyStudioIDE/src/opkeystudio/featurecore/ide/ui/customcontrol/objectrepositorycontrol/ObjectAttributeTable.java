package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomButton;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomText;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class ObjectAttributeTable extends CustomTable {
	private boolean paintCalled = false;
	private ObjectAttributeTable thisTable;

	public ObjectAttributeTable(Composite parent, int style) {
		super(parent, style);
		init();
		thisTable = this;
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
		return (List<ObjectAttributeProperty>) super.getControlData();
	}

	private TableEditor getTableEditor() {
		TableEditor editor = new TableEditor(this);
		editor.horizontalAlignment = SWT.CENTER;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(ObjectAttributeTableItem item) {
		ObjectAttributeProperty attrProperty = item.getObjectAttributeData();
		TableEditor editor1 = getTableEditor();
		TableEditor editor2 = getTableEditor();
		TableEditor editor3 = getTableEditor();
		TableEditor editor4 = getTableEditor();
		CustomButton isUsedButton = new CustomButton(this, SWT.CHECK);
		CustomButton isRegexButton = new CustomButton(this, SWT.CHECK);
		CustomText valueText = new CustomText(this, 0);
		CustomText propertyText = new CustomText(this, 0);
		propertyText.setText(attrProperty.getProperty());
		valueText.setText(attrProperty.getValue());
		isUsedButton.setSelection(attrProperty.isIsused());
		isRegexButton.setSelection(attrProperty.isIsregex());

		isUsedButton.setControlData(attrProperty);
		isRegexButton.setControlData(attrProperty);
		valueText.setControlData(attrProperty);
		propertyText.setControlData(attrProperty);
		isUsedButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				thisTable.setSelection(item);
				CustomButton button = (CustomButton) e.getSource();
				ObjectAttributeProperty oatr = (ObjectAttributeProperty) button.getControlData();
				oatr.setModified(true);
				oatr.setIsused(button.getSelection());
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
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		valueText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				thisTable.select(0);
				CustomText text = (CustomText) e.getSource();
				ObjectAttributeProperty attrPro = (ObjectAttributeProperty) text.getControlData();
				attrPro.setValue(text.getText());
				attrPro.setModified(true);
			}
		});

		propertyText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				thisTable.setSelection(item);
				CustomText text = (CustomText) e.getSource();

				ObjectAttributeProperty attrPro = (ObjectAttributeProperty) text.getControlData();
				attrPro.setValue(text.getText());
				attrPro.setModified(true);
			}
		});

		editor4.setEditor(propertyText, item, 0);
		editor3.setEditor(valueText, item, 1);
		editor2.setEditor(isUsedButton, item, 2);
		editor1.setEditor(isRegexButton, item, 3);
	}

	public ObjectAttributeTableItem getSelectedTableItem() {
		return (ObjectAttributeTableItem) this.getSelection()[0];
	}

	public ObjectAttributeProperty getSelectedObjectAttributeProperty() {
		ObjectAttributeTableItem selectedItem = getSelectedTableItem();
		return selectedItem.getObjectAttributeData();
	}

	public void renderObjectAttributes() {
		this.removeAll();
		List<ObjectAttributeProperty> objectProperties = getObjectPropertiesData();
		for (ObjectAttributeProperty attributeProperty : objectProperties) {
			if (attributeProperty.isDeleted() == false) {
				ObjectAttributeTableItem oati = new ObjectAttributeTableItem(this, 0);
				oati.setText(new String[] { "", "", "", "" });
				oati.setObjectAttributeData(attributeProperty);
				addTableEditor(oati);
			}
		}
	}
}
