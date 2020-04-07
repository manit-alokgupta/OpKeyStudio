package opkeystudio.opkeystudiocore.core.execution;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public class ExecutionSession {
	private String sessionId;
	private String sessionName;
	private String pluginName;
	private String artifact;

	public ExecutionSession(String sessionName, String pluginName, Artifact artifact) {

	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
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
		this.pluginName = pluginName;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}
}
