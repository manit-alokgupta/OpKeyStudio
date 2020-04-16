package pcloudystudio.global.device;

public class MobileDeviceUtils {

	private static String deviceId;
	private static String deviceName;
	private static String deviceSdk;
	private static String deviceAbi;
	private static String deviceAbiManufacturer;
	private static String deviceRelease;
	private static MobileDeviceUtils object;

	private MobileDeviceUtils() {
	}

	private static MobileDeviceUtils getInstance() {
		if (object == null)
			return new MobileDeviceUtils();

		return object;
	}

	public static String getDeviceId() {
		return deviceId;
	}

	public static void setDeviceId(String deviceId) {
		MobileDeviceUtils.deviceId = deviceId;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static void setDeviceName(String deviceName) {
		MobileDeviceUtils.deviceName = deviceName;
	}

	public static String getDeviceSdk() {
		return deviceSdk;
	}

	public static void setDeviceSdk(String deviceSdk) {
		MobileDeviceUtils.deviceSdk = deviceSdk;
	}

	public static String getDeviceAbi() {
		return deviceAbi;
	}

	public static void setDeviceAbi(String deviceAbi) {
		MobileDeviceUtils.deviceAbi = deviceAbi;
	}

	public static String getDeviceAbiManufacturer() {
		return deviceAbiManufacturer;
	}

	public static void setDeviceAbiManufacturer(String deviceAbiManufacturer) {
		MobileDeviceUtils.deviceAbiManufacturer = deviceAbiManufacturer;
	}

	public static String getDeviceRelease() {
		return deviceRelease;
	}

	public static void setDeviceRelease(String deviceRelease) {
		MobileDeviceUtils.deviceRelease = deviceRelease;
	}

}
