package pcloudystudio.appiumserver;

public class AppiumPortIpInfo {
	private String port = "4723";
	private String host_Address = "127.0.0.1";
	private String appium_Directory;
	private static AppiumPortIpInfo appiumPortIp;

	public static AppiumPortIpInfo getInstance() {
		if (appiumPortIp != null) {

			return appiumPortIp;
		} else
			return new AppiumPortIpInfo();
	}

	public AppiumPortIpInfo() {
	}

	public AppiumPortIpInfo(String port, String host_Address, String appium_Directory) {
		this.port = port;
		this.host_Address = host_Address;
		this.appium_Directory = appium_Directory;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost_Address() {
		return host_Address;
	}

	public void setHost_Address(String host_Address) {
		this.host_Address = host_Address;
	}

	public String getAppium_Directory() {
		return appium_Directory;
	}

	public void setAppium_Directory(String appium_Directory) {
		this.appium_Directory = appium_Directory;
	}
}
