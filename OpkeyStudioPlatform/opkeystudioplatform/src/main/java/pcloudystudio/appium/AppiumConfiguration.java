package pcloudystudio.appium;

public class AppiumConfiguration {
	private static String port;
	private static String hostAddress;
	private static String appiumDirectory;

	private static AppiumConfiguration instance;

	private AppiumConfiguration() {
	}

	public static AppiumConfiguration getInstance() {
		if (instance == null) {
			return new AppiumConfiguration();
		}
		return instance;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		AppiumConfiguration.port = port;
	}

	public static String getHostAddress() {
		return hostAddress;
	}

	public static void setHostAddress(String host_Address) {
		AppiumConfiguration.hostAddress = host_Address;
	}

	public static String getAppiumDirectory() {
		return appiumDirectory;
	}

	public static void setAppiumDirectory(String appium_Directory) {
		AppiumConfiguration.appiumDirectory = appium_Directory;
	}
}
