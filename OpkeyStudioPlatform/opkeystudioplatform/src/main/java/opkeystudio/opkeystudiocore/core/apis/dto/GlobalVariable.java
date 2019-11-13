package opkeystudio.opkeystudiocore.core.apis.dto;

import opkeystudio.opkeystudiocore.core.queryMaker.DBField;

public class GlobalVariable extends Modified {
	@DBField
	private int isdeleted;
	@DBField
	private int position;
	@DBField
	private String value;
	
	private int clustering_key;
	@DBField
	private String datatype;
	@DBField
	private String gv_id;
	@DBField
	private boolean externallyupdatable;
	@DBField
	private String p_id;
	@DBField
	private String name;

	public int getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getGv_id() {
		return gv_id;
	}

	public void setGv_id(String gv_id) {
		this.gv_id = gv_id;
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

	public boolean isExternallyupdatable() {
		return externallyupdatable;
	}

	public void setExternallyupdatable(boolean externallyupdatable) {
		this.externallyupdatable = externallyupdatable;
	}

}
