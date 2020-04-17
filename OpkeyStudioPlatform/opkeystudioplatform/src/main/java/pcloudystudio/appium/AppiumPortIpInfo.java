package pcloudystudio.appium;

public class AppiumPortIpInfo {
	private static String port;
	private static String hostAddress;
	private static String appiumDirectory;

	private static AppiumPortIpInfo instance;

	private AppiumPortIpInfo() {
	}

	public static AppiumPortIpInfo getInstance() {
		if (instance == null) {
			return new AppiumPortIpInfo();
		}
		return instance;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		AppiumPortIpInfo.port = port;
	}

	public static String getHostAddress() {
		return hostAddress;
	}

	public static void setHostAddress(String host_Address) {
		AppiumPortIpInfo.hostAddress = host_Address;
	}

	public static String getAppiumDirectory() {
		return appiumDirectory;
	}

	public static void setAppiumDirectory(String appium_Directory) {
		AppiumPortIpInfo.appiumDirectory = appium_Directory;
	}
}
