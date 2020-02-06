package opkeystudio.opkeystudiocore.core.apis.dto.cfl;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class CFLCode extends Modified {
	private int clustering_key;

	@DBField
	private String cf_id;
	@DBField
	private String pluginid;
	@DBField
	private String usercode;
	@DBField
	private String privateuserfunctions;
	@DBField
	private String language;
	@DBField
	private String importpackages;

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


	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPrivateuserfunctions() {
		return privateuserfunctions;
	}

	public void setPrivateuserfunctions(String privateuserfunctions) {
		this.privateuserfunctions = privateuserfunctions;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getImportpackages() {
		return importpackages;
	}

	public void setImportpackages(String importpackages) {
		this.importpackages = importpackages;
	}

	public String getPluginid() {
		return pluginid;
	}

	public void setPluginid(String pluginid) {
		this.pluginid = pluginid;
	}
}
