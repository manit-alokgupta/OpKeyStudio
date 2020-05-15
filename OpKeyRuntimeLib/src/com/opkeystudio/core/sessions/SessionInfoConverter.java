package com.opkeystudio.core.sessions;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;

public class SessionInfoConverter {

	@SuppressWarnings("unchecked")
	public static SessionInfo convertIntoSessionInfo(Object object) {

		@SuppressWarnings("rawtypes")
		Class _class = object.getClass();
		System.out.println("Converting Into SessionInfo from: " + _class.getName());

		try {
			String sessionName = (String) _class.getDeclaredMethod("getSessionName").invoke(object);
			String defaultPluginLocation = (String) _class.getDeclaredMethod("getDefaultPluginLocation").invoke(object);

			String reportFilePath = (String) _class.getDeclaredMethod("getReportFilePath").invoke(object);

			Object mobileDeviceObject = _class.getDeclaredMethod("getMobileDevice").invoke(object);
			MobileDevice mobileDevice = convertObjectIntoMobileDevice(mobileDeviceObject);

			Map<String, String> pluginSettings = (Map<String, String>) _class.getDeclaredMethod("getPluginSettings")
					.invoke(object);

			SessionInfo info = new SessionInfo();
			info.sessionName = sessionName;
			info.defaultPluginLocation = defaultPluginLocation;
			info.reportFilePath = reportFilePath;
			info.mobileDevice = mobileDevice;
			info.pluginSettings = pluginSettings;
			info.buildName = (String) _class.getDeclaredMethod("getBuildName").invoke(object);
			info.sessionDirectory = (String) _class.getDeclaredMethod("getSessionDirectory").invoke(object);
			info.mobileCapabilities = (Map<String, String>) _class.getDeclaredMethod("getMobileCapabilities")
					.invoke(object);

			return info;

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	private static MobileDevice convertObjectIntoMobileDevice(Object object) {
		if (object == null) {
			return null;
		}
		MobileDevice device = new MobileDevice();
		@SuppressWarnings("rawtypes")
		Class _class = object.getClass();
		try {
			String displayName = (String) _class.getDeclaredMethod("getDisplayName").invoke(object);
			String ipaddress = (String) _class.getDeclaredMethod("getIPAddress").invoke(object);
			String os = (String) _class.getDeclaredMethod("getOperatingSystem").invoke(object);
			String serialNumber = (String) _class.getDeclaredMethod("getSerialNumber").invoke(object);
			String version = (String) _class.getDeclaredMethod("getVersion").invoke(object);
			device.setDisplayName(displayName);
			device.setIPAddress(ipaddress);
			device.setOperatingSystem(os);
			device.setSerialNumber(serialNumber);
			device.setVersion(version);
			return device;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
