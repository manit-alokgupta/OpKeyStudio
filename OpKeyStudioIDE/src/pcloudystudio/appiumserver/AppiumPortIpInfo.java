package pcloudystudio.appiumserver;

public class AppiumPortIpInfo {
	private static String port;
	private static String host_Address;
	private static String appium_Directory;
	private static AppiumPortIpInfo appiumPortIp;

	private AppiumPortIpInfo() {
	}

	public static AppiumPortIpInfo getInstance() {
		if (appiumPortIp == null) {
			return new AppiumPortIpInfo();
		}
		return appiumPortIp;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		AppiumPortIpInfo.port = port;
	}

	public static String getHost_Address() {
		return host_Address;
	}

	public static void setHost_Address(String host_Address) {
		AppiumPortIpInfo.host_Address = host_Address;
	}

	public static String getAppium_Directory() {
		return appium_Directory;
	}

	public static void setAppium_Directory(String appium_Directory) {
		AppiumPortIpInfo.appium_Directory = appium_Directory;
	}
}
