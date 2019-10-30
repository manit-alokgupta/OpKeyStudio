package opkeystudio.featurecore.ide.ui.customcontrol;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

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

	public void addGlobalVariable(GlobalVariable gv) {
		getGlobalVariablesData().add(gv);
	}

	public void addBlankGlobalVariableStep() {
		int lastPosition = getGlobalVariablesData().get(getGlobalVariablesData().size() - 1).getPosition();
		GlobalVariable gv = new GlobalVariable();
		gv.setPosition(lastPosition + 1);
		gv.setGv_id(Utilities.getInstance().getUniqueUUID(""));
		gv.setP_id("");
		gv.setName("Neon");
		addGlobalVariable(gv);
	}
}
