package opkeystudio.opkeystudiocore.core.apis.dto.component;

public class ComponentInputArgument {
	private boolean ismandatory;
	private String component_id;
	private String ip_id;
	private String name;
	private int clustering_key;
	private int position;
	private String type;

	public boolean isIsmandatory() {
		return ismandatory;
	}

	public void setIsmandatory(boolean ismandatory) {
		this.ismandatory = ismandatory;
	}

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	public String getIp_id() {
		return ip_id;
	}

	public void setIp_id(String ip_id) {
		this.ip_id = ip_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
