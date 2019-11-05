package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;

public class FlowStep extends Modified {
	private String keywordid;
	private String flow_step_ia_id;
	private String staticobjectid;
	private int clustering_key;
	private String flow_stepid;
	private boolean shouldrun;
	private String keyword_ip_id;
	private boolean wantsnapshot;
	private boolean continueonerror;
	private String datasource;
	private String comment;
	private boolean isnegative;
	private int position;
	private String staticvalue;
	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public String getFlow_step_ia_id() {
		return flow_step_ia_id;
	}

	public void setFlow_step_ia_id(String flow_step_ia_id) {
		this.flow_step_ia_id = flow_step_ia_id;
	}

	public String getStaticobjectid() {
		return staticobjectid;
	}

	public void setStaticobjectid(String staticobjectid) {
		this.staticobjectid = staticobjectid;
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

	public boolean isShouldrun() {
		return shouldrun;
	}

	public void setShouldrun(boolean shouldrun) {
		this.shouldrun = shouldrun;
	}

	public String getKeyword_ip_id() {
		return keyword_ip_id;
	}

	public void setKeyword_ip_id(String keyword_ip_id) {
		this.keyword_ip_id = keyword_ip_id;
	}

	public boolean isWantsnapshot() {
		return wantsnapshot;
	}

	public void setWantsnapshot(boolean wantsnapshot) {
		this.wantsnapshot = wantsnapshot;
	}

	public boolean isContinueonerror() {
		return continueonerror;
	}

	public void setContinueonerror(boolean continueonerror) {
		this.continueonerror = continueonerror;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isIsnegative() {
		return isnegative;
	}

	public void setIsnegative(boolean isnegative) {
		this.isnegative = isnegative;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getStaticvalue() {
		return staticvalue;
	}

	public void setStaticvalue(String staticvalue) {
		this.staticvalue = staticvalue;
	}
}
