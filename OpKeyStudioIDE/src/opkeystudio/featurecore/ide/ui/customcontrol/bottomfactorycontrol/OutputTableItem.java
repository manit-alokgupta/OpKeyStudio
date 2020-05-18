package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputParameter;

public class OutputTableItem extends CustomTableItem {

	public OutputTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ComponentOutputParameter getBottomFactoryOutputData() {
		return (ComponentOutputParameter) super.getControlData();
	}

	public void setBottomFactoryOutputData(ComponentOutputParameter bottomFactoryOutput) {
		super.setControlData(bottomFactoryOutput);
	}

}
