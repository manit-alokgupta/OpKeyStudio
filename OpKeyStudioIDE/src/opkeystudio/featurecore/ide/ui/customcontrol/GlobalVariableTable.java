package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class GlobalVariableTable extends CustomTable {

	public GlobalVariableTable(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setGlobalVariablesData(List<GlobalVariable> gvars) {
		super.setControlData(gvars);
	}

	@SuppressWarnings("unchecked")
	public List<GlobalVariable> getGlobalVariablesData() {
		return (List<GlobalVariable>) super.getControlData();
	}
}
