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
			String defaultPluginLocation = (String) _class.getDeclaredMethod("getDefaultPluginLocation")
					.invoke(object);

			String reportFilePath = (String) _class.getDeclaredMethod("getReportFilePath").invoke(object);

			MobileDevice mobileDevice = (MobileDevice) _class.getDeclaredMethod("getMobileDevice").invoke(object);

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
}
