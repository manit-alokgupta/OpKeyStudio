package opkeystudio.opkeystudiocore.core.apis.dto.cfl;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class CFLibraryMap extends Modified {
	private int clustering_key;
	@DBField
	private String cf_id;
	@DBField
	private String f_id;

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getCf_id() {
		return cf_id;
	}

	public void setCf_id(String cf_id) {
		this.cf_id = cf_id;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
}
