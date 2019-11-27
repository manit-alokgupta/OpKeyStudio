package opkeystudio.featurecore.ide.ui.customcontrol.objectrepositorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class ObjectAttributeTableItem extends CustomTableItem {

	public ObjectAttributeTableItem(Table parent, int style) {
		super(parent, style);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ObjectAttributeProperty getObjectAttributeData() {
		return (ObjectAttributeProperty) super.getControlData();
	}

	public void setObjectAttributeData(ObjectAttributeProperty objectProperty) {
		super.setControlData(objectProperty);
	}

}
