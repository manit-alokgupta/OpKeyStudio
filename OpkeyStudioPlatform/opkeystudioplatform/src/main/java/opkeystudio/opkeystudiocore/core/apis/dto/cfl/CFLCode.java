package opkeystudio.opkeystudiocore.core.apis.dto.cfl;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class CFLCode extends Modified {
	private int clustering_key;

	@DBField
	private String component_id;
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

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
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
