package opkeystudio.opkeystudiocore.core.apis.dto.component;

/**
 * 
 *  Bottom Factory Output Parameters
 *  DB- component_output_parameters
 * 
 * 
 * 
 * */
import opkeystudio.opkeystudiocore.core.query.DBField;

public class Fl_BottomFactoryOutput {
	@DBField
	private String name;
	@DBField
	private String op_id;

	private int clustering_key;
	@DBField
	private int position;
	@DBField
	private String description;
	@DBField
	private String type;
	@DBField
	private String component_id;
	@DBField
	private String componentstep_oa_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	public String getComponentstep_oa_id() {
		return componentstep_oa_id;
	}

	public void setComponentstep_oa_id(String componentstep_oa_id) {
		this.componentstep_oa_id = componentstep_oa_id;
	}

}
