package opkeystudio.opkeystudiocore.core.apis.dto.component.globalvariable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalVariableSchema {
	@JsonProperty("GlobalVariables")
	private List<GlobalVariable> globalVariables = new ArrayList<>();

	public List<GlobalVariable> getGlobalVariables() {
		return globalVariables;
	}

	public void setGlobalVariables(List<GlobalVariable> globalVariables) {
		this.globalVariables = globalVariables;
	}
}
