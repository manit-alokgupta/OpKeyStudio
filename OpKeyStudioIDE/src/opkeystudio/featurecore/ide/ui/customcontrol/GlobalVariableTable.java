package opkeystudio.featurecore.ide.ui.customcontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
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

	public void renderGlobalVaribles() {
		this.removeAll();
		try {
			List<GlobalVariable> globalvariables = new GlobalVariableApi().getAllGlobalVariables();
			for (GlobalVariable globalvariable : globalvariables) {
				TableItem ti = new TableItem(this, 0);
				ti.setData(globalvariable);
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue(), String.valueOf(globalvariable.isExternallyupdatable()) });
			}
			this.setControlData(globalvariables);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void refreshGlobalVariables() {
		this.removeAll();
		List<GlobalVariable> globalvariables = this.getGlobalVariablesData();
		for (GlobalVariable globalvariable : globalvariables) {
			if (globalvariable.isDeleted() == false) {
				TableItem ti = new TableItem(this, 0);
				ti.setData(globalvariable);
				ti.setText(new String[] { globalvariable.getName(), globalvariable.getDatatype(),
						globalvariable.getValue(), String.valueOf(globalvariable.isExternallyupdatable()) });
			}
		}
	}

	public void addBlankGlobalVariableStep() {
		int lastPosition = getGlobalVariablesData().get(getGlobalVariablesData().size() - 1).getPosition();
		GlobalVariable gv = new GlobalVariable();
		gv.setPosition(lastPosition + 1);
		gv.setGv_id(Utilities.getInstance().getUniqueUUID(""));
		gv.setP_id("");
		gv.setName("Neon");
		gv.setAdded(true);
		addGlobalVariable(gv);
		refreshGlobalVariables();
	}

	public void deleteGlobalVariableStep() {
		int selectedIndex = this.getSelectionIndex();
		getGlobalVariablesData().get(selectedIndex).setDeleted(true);
		refreshGlobalVariables();
	}

	public void saveAll() {
		List<GlobalVariable> allGlobalVariables = getGlobalVariablesData();
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isDeleted()) {
				new GlobalVariableApi().deleteGlobalVariable(gv);
			}
		}
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isModified()) {
				new GlobalVariableApi().updateGlobalVariable(gv);
			}
		}
		for (GlobalVariable gv : allGlobalVariables) {
			if (gv.isAdded()) {

			}
		}
		renderGlobalVaribles();
	}

}
