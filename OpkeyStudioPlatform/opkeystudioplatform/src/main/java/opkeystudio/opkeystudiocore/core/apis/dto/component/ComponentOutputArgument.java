package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ComponentOutputArgument extends Modified implements Cloneable, Comparable<ComponentOutputArgument> {

	private int clustering_key;
	@DBField
	private String component_id;
	@DBField
	private String componentstep_id;
	@DBField
	private String componentstep_oa_id;
	@DBField
	private String keyword_op_id;
	@DBField
	private String type;
	@DBField
	private String description;
	@DBField
	private int position;
	@DBField
	private String op_id;
	@DBField
	private String name;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getComponentstep_id() {
		return componentstep_id;
	}

	public void setComponentstep_id(String componentstep_id) {
		this.componentstep_id = componentstep_id;
	}

	public String getComponentstep_oa_id() {
		return componentstep_oa_id;
	}

	public void setComponentstep_oa_id(String componentstep_oa_id) {
		this.componentstep_oa_id = componentstep_oa_id;
	}

	public String getKeyword_op_id() {
		return keyword_op_id;
	}

	public void setKeyword_op_id(String keyword_op_id) {
		this.keyword_op_id = keyword_op_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	public String getVariableName() {
		String varName = Utilities.getInstance().removeSpecialCharacters(getName());
		varName = varName.replaceAll(" ", "_").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\*", "");
		if (varName.trim().isEmpty()) {
			return "unknownVar";
		}
		if (checkVariableNameIsValid(varName) == false) {
			return "o" + varName;
		}
		return varName;
	}

	private boolean checkVariableNameIsValid(String packagename) {
		try {
			Integer.parseInt(packagename);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Integer.parseInt(String.valueOf(packagename.charAt(0)));
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	public ComponentOutputArgument clone() {
		try {
			return (ComponentOutputArgument) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int compareTo(ComponentOutputArgument arg0) {
		return this.getPosition() - arg0.getPosition();
	}
}
