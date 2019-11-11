package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;

public class FlowOutputArgument extends Modified {
	private String flow_step_oa_id;
	private String keyword_op_id;
	private int clustering_key;
	private String flow_stepid;
	private String componentstep_id;
	private String componentstep_oa_id;

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
}
