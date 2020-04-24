package com.opkeystudio.core.sessions;

public class SessionInfo {
	private String sessionName;
	private String defaultPluginLocation;
	private String reportFilePath;

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getDefaultPluginLocation() {
		return defaultPluginLocation;
	}

	public void setDefaultPluginLocation(String defaultPluginLocation) {
		this.defaultPluginLocation = defaultPluginLocation;
	}

	public String getReportFilePath() {
		return reportFilePath;
	}

	public void setReportFilePath(String reportFilePath) {
		this.reportFilePath = reportFilePath;
	}
}
