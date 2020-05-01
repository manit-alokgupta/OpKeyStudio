package com.opkeystudio.core.sessions;

import java.util.HashMap;
import java.util.Map;

public class SessionInfo {
	private String sessionName;
	private String defaultPluginLocation;
	private String reportFilePath;

	private Map<String, String> pluginSettings = new HashMap<String, String>();

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
}
