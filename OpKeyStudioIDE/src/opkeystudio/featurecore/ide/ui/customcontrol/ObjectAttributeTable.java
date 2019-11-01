package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class ObjectAttributeTable extends CustomTable {

	public ObjectAttributeTable(Composite parent, int style) {
		super(parent, style);
		init();
	}

	private void init() {
		String[] tableHeaders = { "Property", "Value", "IsUsed", "IsRegex" };
		for (String header : tableHeaders) {
			TableColumn column = new TableColumn(this, 0);
			column.setText(header);
		}
		this.pack();
		for (int i = 0; i < tableHeaders.length; i++) {
			this.getColumn(i).pack();
		}

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				Table table_0 = (Table) arg0.getSource();
				for (TableColumn column : table_0.getColumns()) {
					column.setWidth(table_0.getBounds().width / 4);
				}
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

	public void renderObjectAttributes() {
		this.removeAll();
		List<ObjectAttributeProperty> objectProperties = getObjectPropertiesData();
		for (ObjectAttributeProperty attributeProperty : objectProperties) {
			ObjectAttributeTableItem oati = new ObjectAttributeTableItem(this, 0);
			oati.setText(new String[] { attributeProperty.getProperty(), attributeProperty.getValue(),
					String.valueOf(attributeProperty.isIsused()), String.valueOf(attributeProperty.isIsregex()) });
			oati.setObjectAttributeData(attributeProperty);
		}
	}
}
