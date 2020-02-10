package opkeystudio.opkeystudiocore.core.keywordmanager.dto;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class KeyWordInputArgument {
	@DBField
	private String keywordid;
	@DBField
	private String datatype;
	@DBField
	private String name;
	@DBField
	private String description;
	@DBField
	private int position;
	@DBField
	private String argid;
	@DBField
	private boolean isoptional;
	@DBField
	private String defaultvalue;

	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getArgid() {
		return argid;
	}

	public void setArgid(String argid) {
		this.argid = argid;
	}

	public boolean isIsoptional() {
		return isoptional;
	}

	public void setIsoptional(boolean isoptional) {
		this.isoptional = isoptional;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
}
