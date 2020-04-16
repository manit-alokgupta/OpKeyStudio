package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ComponentInputArgument extends Modified implements Comparable<ComponentInputArgument>, Cloneable {
	@DBField
	private boolean ismandatory;
	@DBField
	private String component_id;
	@DBField
	private String ip_id;
	@DBField
	private String name;

	private int clustering_key;
	@DBField
	private String stepid;

	@DBField
	private String staticobjectid;
	@DBField
	private int position;
	@DBField
	private String type;
	@DBField
	private String step_arg_id;
	@DBField
	private String defaultvalue;
	@DBField
	private String description;

	public String getStep_arg_id() {
		return step_arg_id;
	}

	public void setStep_arg_id(String step_arg_id) {
		this.step_arg_id = step_arg_id;
	}

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

	public String getStaticobjectid() {
		return staticobjectid;
	}

	public void setStaticobjectid(String staticobjectid) {
		this.staticobjectid = staticobjectid;
	}

	public String getStepid() {
		return stepid;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariableName() {
		String varName = Utilities.getInstance().removeSpecialCharacters(getName());
		varName = varName.replaceAll(" ", "_").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\*", "");
		if (varName.trim().isEmpty()) {
			return "unknownVar";
		}
		return varName;
	}

	@Override
	public int compareTo(ComponentInputArgument arg0) {
		return this.getPosition() - arg0.getPosition();
	}

	public ComponentInputArgument clone() {
		try {
			return (ComponentInputArgument) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
