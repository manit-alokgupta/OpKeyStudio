package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class SuiteStep {

	private int clustering_key;

	@DBField
	private String suite_stepid;
	@DBField
	private String suite_id;
	@DBField
	private String flow_id;
	@DBField
	private String bpgroup_id;
	@DBField
	private String dsheet_id;
	@DBField
	private String comment;
	@DBField
	private int position;
	@DBField
	private boolean sholudrun;
	@DBField
	private String bdd_id;
	@DBField
	private String sparkin_id;
	@DBField
	private int usedatasheet;
	@DBField
	private String flowtype_enum;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getSuite_stepid() {
		return suite_stepid;
	}

	public void setSuite_stepid(String suite_stepid) {
		this.suite_stepid = suite_stepid;
	}

	public String getSuite_id() {
		return suite_id;
	}

	public void setSuite_id(String suite_id) {
		this.suite_id = suite_id;
	}

	public String getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}

	public String getBpgroup_id() {
		return bpgroup_id;
	}

	public void setBpgroup_id(String bpgroup_id) {
		this.bpgroup_id = bpgroup_id;
	}

	public String getDsheet_id() {
		return dsheet_id;
	}

	public void setDsheet_id(String dsheet_id) {
		this.dsheet_id = dsheet_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isSholudrun() {
		return sholudrun;
	}

	public void setSholudrun(boolean sholudrun) {
		this.sholudrun = sholudrun;
	}

	public String getBdd_id() {
		return bdd_id;
	}

	public void setBdd_id(String bdd_id) {
		this.bdd_id = bdd_id;
	}

	public String getSparkin_id() {
		return sparkin_id;
	}

	public void setSparkin_id(String sparkin_id) {
		this.sparkin_id = sparkin_id;
	}

	public int getUsedatasheet() {
		return usedatasheet;
	}

	public void setUsedatasheet(int usedatasheet) {
		this.usedatasheet = usedatasheet;
	}

	public String getFlowtype_enum() {
		return flowtype_enum;
	}

	public void setFlowtype_enum(String flowtype_enum) {
		this.flowtype_enum = flowtype_enum;
	}

}
