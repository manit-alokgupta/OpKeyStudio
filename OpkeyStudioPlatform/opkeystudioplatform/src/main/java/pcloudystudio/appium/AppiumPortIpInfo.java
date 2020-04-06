package pcloudystudio.appium;

public class AppiumPortIpInfo {
	private static String port = "4723";
	private static String hostAddress = "127.0.0.1";
	private static String appiumDirectory = "C:\\Users\\alok.gupta\\AppData\\Roaming\\npm\\node_modules\\appium";
	
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

	public void setPort(String port) {
		AppiumPortIpInfo.port = port;
	}

	public static String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String host_Address) {
		AppiumPortIpInfo.hostAddress = host_Address;
	}

	public static String getAppiumDirectory() {
		return appiumDirectory;
	}

	public void setAppiumDirectory(String appium_Directory) {
		AppiumPortIpInfo.appiumDirectory = appium_Directory;
	}
}
