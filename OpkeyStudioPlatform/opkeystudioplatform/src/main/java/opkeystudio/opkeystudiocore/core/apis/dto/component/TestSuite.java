package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class TestSuite extends Modified {
	@DBField
	private String suite_stepid;
	@DBField
	private String flow_id;

	private int clustering_key;

	@DBField
	private int position;
	@DBField
	private boolean shouldrun;
	@DBField
	private boolean usedatasheet;
	@DBField
	private String suite_id;
	@DBField
	private String flow_type_enum;

	private Artifact artifact;

	public String getSuite_stepid() {
		return suite_stepid;
	}

	public void setSuite_stepid(String suite_stepid) {
		this.suite_stepid = suite_stepid;
	}

	public String getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isShouldrun() {
		return shouldrun;
	}

	public void setShouldrun(boolean shouldrun) {
		this.shouldrun = shouldrun;
	}

	public boolean isUsedatasheet() {
		return usedatasheet;
	}

	public void setUsedatasheet(boolean usedatasheet) {
		this.usedatasheet = usedatasheet;
	}

	public String getSuite_id() {
		return suite_id;
	}

	public void setSuite_id(String suite_id) {
		this.suite_id = suite_id;
	}

	public String getFlow_type_enum() {
		return flow_type_enum;
	}

	public void setFlow_type_enum(String flow_type_enum) {
		this.flow_type_enum = flow_type_enum;
	}

	public Artifact getArtifact() {
		return artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}
}
