package opkeystudio.opkeystudiocore.core.sourcecodeeditor;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class GlobalVariableComposer implements CodeComposer {
	private List<GlobalVariable> globalVariables = new ArrayList<GlobalVariable>();

	@Override
	public void compose() {
		List<GlobalVariable> gvars = getGlobalVariables();
		for (GlobalVariable globalVariable : gvars) {
			
		}
	}

	public List<GlobalVariable> getGlobalVariables() {
		return globalVariables;
	}

	public void setGlobalVariables(List<GlobalVariable> globalVariables) {
		this.globalVariables = globalVariables;
	}
}
