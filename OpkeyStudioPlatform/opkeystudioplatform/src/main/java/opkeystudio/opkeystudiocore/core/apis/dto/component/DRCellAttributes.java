package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class DRCellAttributes extends Modified {
	private int clustering_key;
	@DBField
	private String column_id;
	@DBField
	private int position;
	@DBField
	private String dr_id;
	@DBField
	private String dr_cellid;
	@DBField
	private String value;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getColumn_id() {
		return column_id;
	}

	public void setColumn_id(String column_id) {
		this.column_id = column_id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getDr_id() {
		return dr_id;
	}

	public void setDr_id(String dr_id) {
		this.dr_id = dr_id;
	}

	public String getDr_cellid() {
		return dr_cellid;
	}

	public void setDr_cellid(String dr_cellid) {
		this.dr_cellid = dr_cellid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
