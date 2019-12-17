package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
/**
 * 
 *  Bottom Factory Input Parameters
 *  DB- component_input_parameters
 * 
 * 
 * 
 * */
import opkeystudio.opkeystudiocore.core.query.DBField;

public class Fl_BottomFactoryInput extends Modified {

	@DBField
	private String ip_id;
	@DBField
	private String name;
	@DBField
	private String default_value;
	@DBField
	private boolean is_mandatory;
	@DBField
	private String description;
	@DBField
	private int position;
	@DBField
	private String component_id;
	@DBField
	private String type;
	private FunctionLibraryComponent flowStep;
	private int clustering_key;

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

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public boolean isOptional() {
		return is_mandatory;
	}

	public void setOptional(boolean isOptional) {
		this.is_mandatory = isOptional;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIs_mandatory() {
		return is_mandatory;
	}

	public void setIs_mandatory(boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	public FunctionLibraryComponent getFunctionLibraryComponent() {
		return flowStep;
	}

	public void setFunctionLibraryComponent(FunctionLibraryComponent flowStep2) {
		this.flowStep = flowStep2;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

}
