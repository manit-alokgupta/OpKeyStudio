package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;

public class InputTableItem extends CustomTableItem {

	public InputTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ComponentInputArgument getBottomFactoryInputData() {
		return (ComponentInputArgument) super.getControlData();
	}

	public void setBottomFactoryInputData(ComponentInputArgument bottomFactoryInput) {
		super.setControlData(bottomFactoryInput);
	}
}
