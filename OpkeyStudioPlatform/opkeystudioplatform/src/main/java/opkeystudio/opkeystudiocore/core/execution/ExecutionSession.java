package opkeystudio.opkeystudiocore.core.execution;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ExecutionSession {
	private String sessionId;
	private String sessionName;
	private String buildName;
	private String pluginName;
	private Artifact artifact;
	private String reportFolderDir;

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
}
