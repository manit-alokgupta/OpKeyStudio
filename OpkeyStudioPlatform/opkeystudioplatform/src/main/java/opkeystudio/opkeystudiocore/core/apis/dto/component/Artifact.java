package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Artifact extends Modified {
	public enum MODULETYPE {
		AllUnified, Component, ServiceRepository, Flow, Suite, ObjectRepository, BDD_Gherkin_Stories, Execution,
		DataRepository, EventHandler, Folder, CodedFunction, BP_Group, MBT, Sparkin
	};

	@DBField
	private String modified_on;
	@DBField
	private boolean isshared;
	@DBField
	private String created_on_tz;
	@DBField
	private String modified_on_tz;

	private int clustering_key;

	@DBField
	private String created_by;
	@DBField
	private boolean isdeleted;
	@DBField
	private MODULETYPE file_type_enum;
	@DBField
	private String created_on;
	@DBField
	private String name;
	@DBField
	private String modified_by;
	@DBField
	private boolean isautocreated;
	@DBField
	private String id;
	@DBField
	private int position;
	@DBField
	private String p_id;
	@DBField
	private String parentid;
	@DBField
	private String state_id;

	private Artifact parentArtifact;

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getCreated_on_tz() {
		return created_on_tz;
	}

	public void setCreated_on_tz(String created_on_tz) {
		this.created_on_tz = created_on_tz;
	}

	public String getModified_on_tz() {
		return modified_on_tz;
	}

	public void setModified_on_tz(String modified_on_tz) {
		this.modified_on_tz = modified_on_tz;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public MODULETYPE getFile_type_enum() {
		return file_type_enum;
	}

	public void setFile_type_enum(MODULETYPE file_type_enum) {
		this.file_type_enum = file_type_enum;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public boolean isIsshared() {
		return isshared;
	}

	public void setIsshared(boolean isshared) {
		this.isshared = isshared;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public boolean isIsautocreated() {
		return isautocreated;
	}

	public void setIsautocreated(boolean isautocreated) {
		this.isautocreated = isautocreated;
	}

	public String getVariableName() {
		String varName = Utilities.getInstance().removeSpecialCharacters(getName());
		varName = varName.replaceAll(" ", "_").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\*", "");
		if (varName.trim().isEmpty()) {
			return "unknownVar";
		}
		return varName;
	}

	public Artifact getParentArtifact() {
		return parentArtifact;
	}

	public void setParentArtifact(Artifact parentArtifact) {
		this.parentArtifact = parentArtifact;
	}
}
