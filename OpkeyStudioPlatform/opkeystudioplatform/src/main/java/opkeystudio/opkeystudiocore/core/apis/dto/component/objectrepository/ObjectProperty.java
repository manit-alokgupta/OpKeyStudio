package opkeystudio.opkeystudiocore.core.apis.dto.component.objectrepository;

public class ObjectProperty {
	private String dataType;
	private boolean isUsed;
	private boolean isEncrypted;
	private int position;
	private String value;
	private boolean isEditable;
	private boolean isRegex;
	private String pluginSpecificity;
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
