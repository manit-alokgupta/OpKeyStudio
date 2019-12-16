package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class Suite_Email {
//	db - suite_email
	private int clustering_key;
	@DBField
	private String email_id;
	@DBField
	private String email_sender_id;
	@DBField
	private String suite_id;
	@DBField
	private int checked;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getEmail_sender_id() {
		return email_sender_id;
	}

	public void setEmail_sender_id(String email_sender_id) {
		this.email_sender_id = email_sender_id;
	}

	public String getSuite_id() {
		return suite_id;
	}

	public void setSuite_id(String suite_id) {
		this.suite_id = suite_id;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

}
