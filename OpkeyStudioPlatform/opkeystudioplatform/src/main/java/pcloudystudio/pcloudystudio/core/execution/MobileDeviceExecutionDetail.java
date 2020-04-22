package pcloudystudio.pcloudystudio.core.execution;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

public class MobileDeviceExecutionDetail {
	private String deviceId;
	private String deviceName;
	private String deviceVersion;
	private String deviceAPILevel;
	private String deviceABI;

	private static MobileDeviceExecutionDetail instance = null;

	private MobileDeviceExecutionDetail() {
	}

	public static MobileDeviceExecutionDetail getInstance() {
		if (instance == null) {
			instance = new MobileDeviceExecutionDetail();
		}
		return instance;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getDeviceAPILevel() {
		return deviceAPILevel;
	}

	public void setDeviceAPILevel(String deviceAPILevel) {
		this.deviceAPILevel = deviceAPILevel;
	}

	public String getDeviceABI() {
		return deviceABI;
	}

	public void setDeviceABI(String deviceABI) {
		this.deviceABI = deviceABI;
	}

}
