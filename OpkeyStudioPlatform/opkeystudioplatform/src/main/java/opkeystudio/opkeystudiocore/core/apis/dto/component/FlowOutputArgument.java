package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowOutputArgument extends Modified implements Cloneable {
	@DBField
	private String flow_step_oa_id;
	@DBField
	private String keyword_op_id;

	private int clustering_key;

	@DBField
	private String flow_stepid;
	@DBField
	private String componentstep_id;
	@DBField
	private String componentstep_oa_id;
	@DBField
	private String component_op_id;
	@DBField
	private String outputvariablename;

	public String getFlow_step_oa_id() {
		return flow_step_oa_id;
	}

	public void setFlow_step_oa_id(String flow_step_oa_id) {
		this.flow_step_oa_id = flow_step_oa_id;
	}

	public String getKeyword_op_id() {
		return keyword_op_id;
	}

	public void setKeyword_op_id(String keyword_op_id) {
		this.keyword_op_id = keyword_op_id;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getFlow_stepid() {
		return flow_stepid;
	}

	public void setFlow_stepid(String flow_stepid) {
		this.flow_stepid = flow_stepid;
	}

	public String getComponentstep_id() {
		return componentstep_id;
	}

	public void setComponentstep_id(String componentstep_id) {
		this.componentstep_id = componentstep_id;
	}

	public String getComponentstep_oa_id() {
		return componentstep_oa_id;
	}

	public void setComponentstep_oa_id(String componentstep_oa_id) {
		this.componentstep_oa_id = componentstep_oa_id;
	}

	public String getOutputvariablename() {
		return outputvariablename;
	}

	public void setOutputvariablename(String outputvariablename) {
		this.outputvariablename = outputvariablename;
	}

	public FlowOutputArgument clone() {
		try {
			return (FlowOutputArgument) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getComponent_op_id() {
		return component_op_id;
	}

	public void setComponent_op_id(String component_op_id) {
		this.component_op_id = component_op_id;
	}
	
	public String getVariableName() {
		String varName = Utilities.getInstance().removeSpecialCharacters(getOutputvariablename());
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
}
