package opkeystudio.opkeystudiocore.core.apis.dto.component.objectrepository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectProperty {
	@JsonProperty("DataType")
	private String dataType;

	@JsonProperty("IsUsed")
	private boolean isUsed;

	@JsonProperty("IsEncrypted")
	private boolean isEncrypted;

	@JsonProperty("Position")
	private int position;

	@JsonProperty("Value")
	private String value;

	@JsonProperty("IsEditable")
	private boolean isEditable;

	@JsonProperty("IsRegex")
	private boolean isRegex;

	@JsonProperty("PluginSpecificity")
	private String pluginSpecificity;

	@JsonProperty("PropertyName")
	private String propertyName;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
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

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public boolean isRegex() {
		return isRegex;
	}

	public void setRegex(boolean isRegex) {
		this.isRegex = isRegex;
	}

	public String getPluginSpecificity() {
		return pluginSpecificity;
	}

	public void setPluginSpecificity(String pluginSpecificity) {
		this.pluginSpecificity = pluginSpecificity;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
