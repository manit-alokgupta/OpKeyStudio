package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class BottomFactoryTag extends Modified {

	private int clustering_key;
	@DBField
	private String key;
	@DBField
	private String value;
	@DBField
	private int position;
	@DBField
	private String tag_id;
	@DBField
	private String p_id_denormalized;
	@DBField
	private String id;
	private FunctionLibraryComponent functionLibraryComponent;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getP_id_denormalized() {
		return p_id_denormalized;
	}

	public void setP_id_denormalized(String p_id_denormalized) {
		this.p_id_denormalized = p_id_denormalized;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FunctionLibraryComponent getFunctionLibraryComponent() {
		return functionLibraryComponent;
	}

	public void setFunctionLibraryComponent(FunctionLibraryComponent functionLibraryComponent) {
		this.functionLibraryComponent = functionLibraryComponent;
	}

}
