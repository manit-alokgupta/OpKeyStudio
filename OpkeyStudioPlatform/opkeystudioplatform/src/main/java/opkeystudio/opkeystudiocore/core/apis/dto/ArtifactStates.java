package opkeystudio.opkeystudiocore.core.apis.dto;

public class ArtifactStates {
	private int clustering_key;
	private String state_id;
	private String p_id;
	private String name;
	private int position;
	private String color_code;
	private String artifactstate_type;
	private String createdby;
	private String createdon;
	private String createdon_tz;
	private String modifiedby;
	private String modifiedon;
	private String modifiedon_tz;
	private boolean isfreezable;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getColor_code() {
		return color_code;
	}

	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}

	public String getArtifactstate_type() {
		return artifactstate_type;
	}

	public void setArtifactstate_type(String artifactstate_type) {
		this.artifactstate_type = artifactstate_type;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getCreatedon_tz() {
		return createdon_tz;
	}

	public void setCreatedon_tz(String createdon_tz) {
		this.createdon_tz = createdon_tz;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getModifiedon_tz() {
		return modifiedon_tz;
	}

	public void setModifiedon_tz(String modifiedon_tz) {
		this.modifiedon_tz = modifiedon_tz;
	}

	public boolean isIsfreezable() {
		return isfreezable;
	}

	public void setIsfreezable(boolean isfreezable) {
		this.isfreezable = isfreezable;
	}
}
