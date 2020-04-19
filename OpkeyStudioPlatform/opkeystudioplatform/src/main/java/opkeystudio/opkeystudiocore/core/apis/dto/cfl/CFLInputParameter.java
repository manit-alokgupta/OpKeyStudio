package opkeystudio.opkeystudiocore.core.apis.dto.cfl;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class CFLInputParameter extends Modified {
	private int clustering_key;
	@DBField
	private String ip_id;
	@DBField
	private String tracking_id;
	@DBField
	private String cf_id;
	@DBField
	private String name;
	@DBField
	private String type;
	@DBField
	private boolean ismandatory;
	@DBField
	private String defaultvalue;
	@DBField
	private String object_id;
	@DBField
	private String attachment_id;
	@DBField
	private String description;
	@DBField
	private int position;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getIp_id() {
		return ip_id;
	}

	public void setIp_id(String ip_id) {
		this.ip_id = ip_id;
	}

	public String getTracking_id() {
		return tracking_id;
	}

	public void setTracking_id(String tracking_id) {
		this.tracking_id = tracking_id;
	}

	public String getCf_id() {
		return cf_id;
	}

	public void setCf_id(String cf_id) {
		this.cf_id = cf_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIsmandatory() {
		return ismandatory;
	}

	public void setIsmandatory(boolean ismandatory) {
		this.ismandatory = ismandatory;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
