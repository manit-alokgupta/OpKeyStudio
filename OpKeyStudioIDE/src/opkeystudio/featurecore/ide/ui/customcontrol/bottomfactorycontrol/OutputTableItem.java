package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;

public class OutputTableItem extends CustomTableItem {

	public OutputTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ComponentOutputArgument getBottomFactoryOutputData() {
		return (ComponentOutputArgument) super.getControlData();
	}

	public void setBottomFactoryOutputData(ComponentOutputArgument bottomFactoryOutput) {
		super.setControlData(bottomFactoryOutput);
	}

}
