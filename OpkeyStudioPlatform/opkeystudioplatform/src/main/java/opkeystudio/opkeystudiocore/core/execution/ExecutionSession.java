package opkeystudio.opkeystudiocore.core.execution;

import java.util.HashMap;
import java.util.Map;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ExecutionSession {
	private String sessionId;
	private String sessionName;
	private String buildName;
	private String pluginName;
	private Artifact artifact;
	private String artifactFilePackageClass;
	private String reportFolderDir;
	private MobileDevice mobileDevice;
	private Map<String, String> pluginSettings = new HashMap<String, String>();

	public ExecutionSession(String sessionName, String buildName) {
		this.setSessionName((sessionName + "_" + Utilities.getInstance().getCurrentDateTime()).replaceAll(" ", "_")
				.replaceAll(":", "_"));
		this.setBuildName((buildName + "_" + Utilities.getInstance().getCurrentDateTime()).replaceAll(" ", "_")
				.replaceAll(":", "_"));
		this.setSessionId(Utilities.getInstance().getUniqueUUID(sessionName));
	}

	public ExecutionSession(String sessionName, String pluginName, Artifact artifact) {
		this.setSessionName(sessionName);
		this.setPluginName(pluginName);
		this.setArtifact(artifact);
		this.setSessionId(Utilities.getInstance().getUniqueUUID(""));
	}

	public String getSessionId() {
		return sessionId;
	}

	private void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		pluginName = pluginName.toUpperCase();
		System.out.println(">>Plugin Changed to " + pluginName);
		this.pluginName = pluginName;
	}

	public Artifact getArtifact() {
		return artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getReportFolderDir() {
		return reportFolderDir;
	}

	public void setReportFolderDir(String reportFolderDir) {
		this.reportFolderDir = reportFolderDir;
	}

	public String getArtifactFilePackageClass() {
		return artifactFilePackageClass;
	}

	public void setArtifactFilePackageClass(String artifactFilePackageClass) {
		this.artifactFilePackageClass = artifactFilePackageClass;
	}

	public Map<String, String> getPluginSettings() {
		return pluginSettings;
	}

	public void setPluginSettings(Map<String, String> pluginSettings) {
		this.pluginSettings = pluginSettings;
	}

	public void addPluginSetting(String key, String value) {
		getPluginSettings().put(key, value);
	}

	public String getPluginSetting(String key) {
		return getPluginSettings().get(key);
	}

	public MobileDevice getMobileDevice() {
		return mobileDevice;
	}

	public void setMobileDevice(MobileDevice mobileDevice) {
		this.mobileDevice = mobileDevice;
	}
}
