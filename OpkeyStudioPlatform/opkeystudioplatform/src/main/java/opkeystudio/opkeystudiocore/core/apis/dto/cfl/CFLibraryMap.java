package opkeystudio.opkeystudiocore.core.apis.dto.cfl;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class CFLibraryMap extends Modified {
	private int clustering_key;
	@DBField
	private String component_id;
	@DBField
	private String f_id;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
}
