package com.opkeystudio.core.sessions;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;

public class SessionInfo {

	public String sessionName;
	public String buildName;
	public String sessionDirectory;
	public String defaultPluginLocation;
	public String reportFilePath;
	public MobileDevice mobileDevice;
	public Map<String, String> pluginSettings = new HashMap<String, String>();
	private DesiredCapabilities mobileCapabilities;

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

	public MobileDevice getMobileDevice() {
		return mobileDevice;
	}

	public void setMobileDevice(MobileDevice mobileDevice) {
		this.mobileDevice = mobileDevice;
	}
	
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	
	public String getBuildName() {
		return this.buildName;
	}
	
	public String getSessionDirectory() {
		return this.sessionDirectory;
	}
	
	public DesiredCapabilities getMobileCapabilities() {
		return mobileCapabilities;
	}

	public void setMobileCapabilities(DesiredCapabilities mobileCapabilities) {
		this.mobileCapabilities = mobileCapabilities;
	}

}
