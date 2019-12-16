package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.sql.Date;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class AuditTrails {

	@DBField
	private String task;
	@DBField
	private String fieldName;
	@DBField
	private String oldValue;
	@DBField
	private String newValue;
	@DBField
	private String byUser;
	@DBField
	private Date timeStamp;
	private int clustering_key;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getField() {
		return fieldName;
	}

	public void setField(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getByUser() {
		return byUser;
	}

	public void setByUser(String byUser) {
		this.byUser = byUser;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getAudit_trail_id() {
		return clustering_key;
	}

	public void setAudit_trail_id(int audit_trail_id) {
		this.clustering_key = audit_trail_id;
	}

}
