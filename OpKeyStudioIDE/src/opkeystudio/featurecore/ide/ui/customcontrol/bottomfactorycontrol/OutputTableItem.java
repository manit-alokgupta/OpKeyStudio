package opkeystudio.featurecore.ide.ui.customcontrol.bottomfactorycontrol;

import org.eclipse.swt.widgets.Table;

import opkeystudio.featurecore.ide.ui.customcontrol.generic.CustomTableItem;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;

public class OutputTableItem extends CustomTableItem {

	public OutputTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Fl_BottomFactoryOutput getBottomFactoryOutputData() {
		return (Fl_BottomFactoryOutput) super.getControlData();
	}

	public void setBottomFactoryOutputData(Fl_BottomFactoryOutput bottomFactoryOutput) {
		super.setControlData(bottomFactoryOutput);
	}

}
