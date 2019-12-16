package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.sql.Date;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class AuditTrails {
// db- project_audit_trail
	@DBField
	private String task;
	@DBField
	private String field_name;
	@DBField
	private String old_value;
	@DBField
	private String new_value;
	@DBField
	private String byUser;
	@DBField
	private Date time_stamp;
	@DBField
	private String time_stamp_tz;
	private int clustering_key;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getField() {
		return field_name;
	}

	public void setField(String fieldName) {
		this.field_name = fieldName;
	}

	public String getOldValue() {
		return old_value;
	}

	public void setOldValue(String oldValue) {
		this.old_value = oldValue;
	}

	public String getNewValue() {
		return new_value;
	}

	public void setNewValue(String newValue) {
		this.new_value = newValue;
	}

	public String getByUser() {
		return byUser;
	}

	public void setByUser(String byUser) {
		this.byUser = byUser;
	}

	public Date getTimeStamp() {
		return time_stamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.time_stamp = timeStamp;
	}

	public String getTime_stamp_tz() {
		return time_stamp_tz;
	}

	public void setTime_stamp_tz(String time_stamp_tz) {
		this.time_stamp_tz = time_stamp_tz;
	}

	public int getAudit_trail_id() {
		return clustering_key;
	}

	public void setAudit_trail_id(int audit_trail_id) {
		this.clustering_key = audit_trail_id;
	}

}
