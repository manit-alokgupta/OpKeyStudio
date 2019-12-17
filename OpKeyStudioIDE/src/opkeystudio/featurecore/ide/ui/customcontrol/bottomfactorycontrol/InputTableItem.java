package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;

public class InputTableItem extends CustomTableItem {

	public InputTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Fl_BottomFactoryInput getBottomFactoryInputData() {
		return (Fl_BottomFactoryInput) super.getControlData();
	}

	public void setBottomFactoryInputData(Fl_BottomFactoryInput bottomFactoryInput) {
		super.setControlData(bottomFactoryInput);
	}
}
