package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.sql.Date;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class Details {
// db- main_role
	private int clustering_key;
	@DBField
	private Date creation_date;
	@DBField
	private String created_by;
	@DBField
	private String creation_date_tz;
	@DBField
	private Date lastmodification_date;
	@DBField
	private String lastmodified_by;
	@DBField
	private String lastmodification_date_tz;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreation_date_tz() {
		return creation_date_tz;
	}

	public void setCreation_date_tz(String creation_date_tz) {
		this.creation_date_tz = creation_date_tz;
	}

	public Date getLastmodification_date() {
		return lastmodification_date;
	}

	public void setLastmodification_date(Date lastmodification_date) {
		this.lastmodification_date = lastmodification_date;
	}

	public String getLastmodified_by() {
		return lastmodified_by;
	}

	public void setLastmodified_by(String lastmodified_by) {
		this.lastmodified_by = lastmodified_by;
	}

	public String getLastmodification_date_tz() {
		return lastmodification_date_tz;
	}

	public void setLastmodification_date_tz(String lastmodification_date_tz) {
		this.lastmodification_date_tz = lastmodification_date_tz;
	}

}
