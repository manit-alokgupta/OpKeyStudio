package com.opkeystudio.core.sessions;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;

public class SessionInfoConverter {
	@SuppressWarnings("unchecked")
	public static SessionInfo convertIntoSessionInfo(Object object) {
		@SuppressWarnings("rawtypes")
		Class _class = object.getClass();
		try {
			String sessionName = (String) _class.getDeclaredMethod("getSessionName").invoke(object);
			String defaultPluginLocation = (String) _class.getDeclaredMethod("getDefaultPluginLocation").invoke(object);

			String reportFilePath = (String) _class.getDeclaredMethod("getReportFilePath").invoke(object);

			Object mobileDeviceObject = _class.getDeclaredMethod("getMobileDevice").invoke(object);

			MobileDevice mobileDevice = convertObjectIntoMobileDevice(mobileDeviceObject);
			Map<String, String> pluginSettings = (Map<String, String>) _class.getDeclaredMethod("getPluginSettings")
					.invoke(object);

			SessionInfo info = new SessionInfo();
			info.setSessionName(sessionName);
			info.setDefaultPluginLocation(defaultPluginLocation);
			info.setReportFilePath(reportFilePath);
			info.setMobileDevice(mobileDevice);
			info.setPluginSettings(pluginSettings);
			return info;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
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
