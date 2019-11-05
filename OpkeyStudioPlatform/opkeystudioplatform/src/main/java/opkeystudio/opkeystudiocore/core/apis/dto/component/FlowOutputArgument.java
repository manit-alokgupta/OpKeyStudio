package opkeystudio.opkeystudiocore.core.apis.dto.component;

public class FlowOutputArgument {
	private String flow_step_oa_id;
	private String keyword_op_id;
	private int clustering_key;
	private String flow_stepid;

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
}
