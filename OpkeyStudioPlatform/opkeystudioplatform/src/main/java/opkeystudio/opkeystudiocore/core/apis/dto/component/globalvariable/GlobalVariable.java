package opkeystudio.opkeystudiocore.core.apis.dto.component.globalvariable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalVariable {
	@JsonProperty("Name")
	private String name;

	@JsonProperty("Value")
	private String value;

	@JsonProperty("DataType_ENUM")
	private String dataType_ENUM;

	@JsonProperty("ExternallyUpdatable")
	private String externallyUpdatable;

	@JsonProperty("IsDeleted")
	private boolean isDeleted;

	@JsonProperty("ListValues")
	private ListValues listValues;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType_ENUM() {
		return dataType_ENUM;
	}

	public void setDataType_ENUM(String dataType_ENUM) {
		this.dataType_ENUM = dataType_ENUM;
	}

	public String getExternallyUpdatable() {
		return externallyUpdatable;
	}

	public void setExternallyUpdatable(String externallyUpdatable) {
		this.externallyUpdatable = externallyUpdatable;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ListValues getListValues() {
		return listValues;
	}

	public void setListValues(ListValues listValues) {
		this.listValues = listValues;
	}
}
