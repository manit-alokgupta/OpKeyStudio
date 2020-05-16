package opkeystudio.opkeystudiocore.core.collections;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowOutputObject {
	private String dataType;
	private String outputVariableName;
	private ComponentOutputArgument componentOutputArgument;

	public String getOutputVariableName() {
		return outputVariableName;
	}

	public void setOutputVariableName(String outputVariableName) {
		this.outputVariableName = outputVariableName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getVariableName() {
		String varName = Utilities.getInstance().removeSpecialCharacters(getOutputVariableName());
		varName = varName.replaceAll(" ", "_").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\*", "");
		if (varName.trim().isEmpty()) {
			return "unknownVar";
		}
		if (checkVariableNameIsValid(varName) == false) {
			return "o" + varName;
		}
		return varName;
	}

	private boolean checkVariableNameIsValid(String packagename) {
		try {
			Integer.parseInt(packagename);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Integer.parseInt(String.valueOf(packagename.charAt(0)));
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public ComponentOutputArgument getComponentOutputArgument() {
		return componentOutputArgument;
	}

	public void setComponentOutputArgument(ComponentOutputArgument componentOutputArgument) {
		this.componentOutputArgument = componentOutputArgument;
	}
}
