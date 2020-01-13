package opkeystudio.opkeystudiocore.core.keywordmanager.dto;

import opkeystudio.opkeystudiocore.core.utils.DataType.GenericDataType;

public class KeyWordInputArgument {
	
	private String keywordid;
	private GenericDataType datatype;
	private String name;
	private String description;
	private int position;
	private String argid;
	private boolean isoptional;
	private String defaultvalue;

	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public GenericDataType getDatatype() {
		return datatype;
	}

	public void setDatatype(GenericDataType datatype) {
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
