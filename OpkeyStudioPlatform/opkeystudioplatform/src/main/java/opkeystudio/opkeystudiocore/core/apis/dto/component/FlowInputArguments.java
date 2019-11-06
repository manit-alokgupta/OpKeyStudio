package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;

public class FlowInputArguments extends Modified {
	private String flow_step_ia_id;
	private String keyword_ip_id;
	private String datasource;
	private String staticvalue;
	private int clustering_key;
	private String flow_stepid;
	private String staticobjectid;

	public String getFlow_step_ia_id() {
		return flow_step_ia_id;
	}

	public void setFlow_step_ia_id(String flow_step_ia_id) {
		this.flow_step_ia_id = flow_step_ia_id;
	}

	public String getKeyword_ip_id() {
		return keyword_ip_id;
	}

	public void setKeyword_ip_id(String keyword_ip_id) {
		this.keyword_ip_id = keyword_ip_id;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getStaticvalue() {
		return staticvalue;
	}

	public void setStaticvalue(String staticvalue) {
		this.staticvalue = staticvalue;
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

	public String getStaticobjectid() {
		return staticobjectid;
	}

	public void setStaticobjectid(String staticobjectid) {
		this.staticobjectid = staticobjectid;
	}
}
