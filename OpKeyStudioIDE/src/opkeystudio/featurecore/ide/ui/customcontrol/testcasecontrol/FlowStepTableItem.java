package opkeystudio.featurecore.ide.ui.customcontrol.testcasecontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class FlowStepTableItem extends CustomTableItem {

	public FlowStepTableItem(Table parent, int style) {
		super(parent, style);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public FlowStep getFlowStepeData() {
		return (FlowStep) super.getControlData();
	}

	public void setFlowStepData(FlowStep objectProperty) {
		super.setControlData(objectProperty);
	}

}
