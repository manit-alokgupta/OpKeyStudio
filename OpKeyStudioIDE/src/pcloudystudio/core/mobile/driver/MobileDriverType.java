package pcloudystudio.core.mobile.driver;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

public enum MobileDriverType {
	IOS_DRIVER("IOS_DRIVER", 0, "iOS"), ANDROID_DRIVER("ANDROID_DRIVER", 1, "Android");

	private String driverName;

	private MobileDriverType(String name, int ordinal, String driverName) {
		this.driverName = driverName;
	}

	public String getName() {
		return this.name();
	}

	public String getPlatform() {
		return this.toString();
	}

	public String toString() {
		return this.driverName;
	}

	public static String[] stringValues() {
		final String[] stringValues = new String[values().length];
		for (int i = 0; i < values().length; ++i) {
			stringValues[i] = values()[i].toString();
		}
		return stringValues;
	}

	public static MobileDriverType fromStringValue(final String stringValue) {
		if (stringValue == null) {
			return null;
		}
		for (int i = 0; i < values().length; ++i) {
			if (values()[i].toString().equals(stringValue)) {
				return values()[i];
			}
		}
		return null;
	}

	public String getPropertyKey() {
		return "devicePlatform";
	}

	public String getPropertyValue() {
		return this.getName();
	}
}
