package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
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

	public ObjectAttributeTable(Composite parent, int style) {
		super(parent, style);
		init();
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
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		return editor;
	}

	private void addTableEditor(ObjectAttributeTableItem item) {
		ObjectAttributeProperty attrProperty = item.getObjectAttributeData();
		TableEditor editor = getTableEditor();
		CustomButton isUsedButton = new CustomButton(this, SWT.CHECK);
		CustomButton isRegexButton = new CustomButton(this, SWT.CHECK);
		CustomText valueText = new CustomText(this, 0);
		valueText.setText(attrProperty.getValue());
		isUsedButton.setSelection(attrProperty.isIsused());
		isUsedButton.setControlData(attrProperty);

		isRegexButton.setSelection(attrProperty.isIsregex());
		isRegexButton.setControlData(attrProperty);

		isUsedButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
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

		editor.setEditor(isUsedButton, item, 2);
		editor.setEditor(isRegexButton, item, 3);
	}

	public void renderObjectAttributes() {
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
	}
}
