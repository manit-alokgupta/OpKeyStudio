package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.queryMaker.DBField;

public class FlowInputArgument extends Modified {
	@DBField
	private String flow_step_ia_id;
	@DBField
	private String keyword_ip_id;
	@DBField
	private String datasource;
	@DBField
	private String staticvalue;
	
	private int clustering_key;
	
	@DBField
	private String flow_stepid;
	@DBField
	private String staticobjectid;
	@DBField
	private String arg_datasource;
	@DBField
	private String step_arg_id;
	@DBField
	private String globalvariable_id;
	@DBField
	private String stepid;
	@DBField
	private String key_arg_id;
	@DBField
	private String componentstep_id;
	@DBField
	private String component_ip_id;

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

	public String getArg_datasource() {
		return arg_datasource;
	}

	public void setArg_datasource(String arg_datasource) {
		this.arg_datasource = arg_datasource;
	}

	public String getStep_arg_id() {
		return step_arg_id;
	}

	public void setStep_arg_id(String step_arg_id) {
		this.step_arg_id = step_arg_id;
	}

	public String getGlobalvariable_id() {
		return globalvariable_id;
	}

	public void setGlobalvariable_id(String globalvariable_id) {
		this.globalvariable_id = globalvariable_id;
	}

	public String getStepid() {
		return stepid;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public String getKey_arg_id() {
		return key_arg_id;
	}

	public void setKey_arg_id(String key_arg_id) {
		this.key_arg_id = key_arg_id;
	}

	public String getComponentstep_id() {
		return componentstep_id;
	}

	public void setComponentstep_id(String componentstep_id) {
		this.componentstep_id = componentstep_id;
	}

	public String getComponent_ip_id() {
		return component_ip_id;
	}

	public void setComponent_ip_id(String component_ip_id) {
		this.component_ip_id = component_ip_id;
	}
}
