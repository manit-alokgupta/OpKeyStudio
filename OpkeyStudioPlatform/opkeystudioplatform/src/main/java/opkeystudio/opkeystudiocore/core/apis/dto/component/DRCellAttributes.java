package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class DRCellAttributes extends Modified implements Comparable<DRCellAttributes> {
	private int clustering_key;
	@DBField
	private String column_id;
	@DBField
	private int position;
	@DBField
	private String dr_id;
	@DBField
	private String dr_cell_id;
	@DBField
	private String value;

	private DRColumnAttributes drColumnAttribute;
	private int rowNo;
	private int columnNo;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DRColumnAttributes getDrColumnAttribute() {
		return drColumnAttribute;
	}

	public void setDrColumnAttribute(DRColumnAttributes drColumnAttribute) {
		this.drColumnAttribute = drColumnAttribute;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}

	@Override
	public int compareTo(DRCellAttributes arg0) {
		return this.getPosition() - arg0.getPosition();
	}

	public String getDr_cell_id() {
		return dr_cell_id;
	}

	public void setDr_cell_id(String dr_cell_id) {
		this.dr_cell_id = dr_cell_id;
	}

}
