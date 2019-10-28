package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;

public class ObjectRepository extends Modified {
	private boolean usesmartidentification;
	private String or_id;
	private int clustering_key;
	private String object_id;
	private String createdon;
	private String modifiedon;
	private String modifiedon_tz;
	private String createdby;
	private boolean isupdatable;
	private String name;
	private String modifiedby;
	private String createdon_tz;
	private String opkeytype;
	private int position;
	private String parent_object_id;

	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getCreatedon_tz() {
		return createdon_tz;
	}

	public void setCreatedon_tz(String createdon_tz) {
		this.createdon_tz = createdon_tz;
	}

	public String getOpkeytype() {
		return opkeytype;
	}

	public void setOpkeytype(String opkeytype) {
		this.opkeytype = opkeytype;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getParent_object_id() {
		return parent_object_id;
	}

	public void setParent_object_id(String parent_object_id) {
		this.parent_object_id = parent_object_id;
	}

	public boolean isUsesmartidentification() {
		return usesmartidentification;
	}

	public void setUsesmartidentification(boolean usesmartidentification) {
		this.usesmartidentification = usesmartidentification;
	}

	public boolean isIsupdatable() {
		return isupdatable;
	}

	public void setIsupdatable(boolean isupdatable) {
		this.isupdatable = isupdatable;
	}

}
