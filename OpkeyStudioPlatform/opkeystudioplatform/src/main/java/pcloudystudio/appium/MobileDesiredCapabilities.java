package pcloudystudio.appium;

import java.util.LinkedHashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileDesiredCapabilities {
	private static MobileDesiredCapabilities instance;
	private static LinkedHashMap<String, String> mapOfCapabilities;
	private static LinkedHashMap<String, String> mapOfCapabilityNameType;

	private MobileDesiredCapabilities() {
	}

	public static MobileDesiredCapabilities getinstance() {
		if (instance == null)
			return new MobileDesiredCapabilities();
		return instance;
	}

	public static LinkedHashMap<String, String> getMapOfCapabilities() {
		return mapOfCapabilities;
	}

	public void setMapOfCapabilities(LinkedHashMap<String, String> mapOfCapabilities) {
		MobileDesiredCapabilities.mapOfCapabilities = mapOfCapabilities;
	}

	public static LinkedHashMap<String, String> getMapOfCapabilityNameType() {
		return mapOfCapabilityNameType;
	}

	public static void setMapOfCapabilityNameType(LinkedHashMap<String, String> mapOfCapabilityNameType) {
		MobileDesiredCapabilities.mapOfCapabilityNameType = mapOfCapabilityNameType;
	}

	public static DesiredCapabilities getCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		LinkedHashMap<String, String> caps_map = getMapOfCapabilities();
		capabilities.setCapability("newCommandTimeout", 0);
		if (!caps_map.isEmpty()) {
			caps_map.entrySet().forEach(entry -> {
				String capabilityName = entry.getKey();
				String capabilityType = getCapabilityType(capabilityName);
				switch (capabilityType) {
				case "String":
					capabilities.setCapability(entry.getKey(), entry.getValue());
					break;
				case "int":
					capabilities.setCapability(entry.getKey(), Integer.parseInt(entry.getValue()));
					break;
				case "boolean":
					capabilities.setCapability(entry.getKey(), Boolean.parseBoolean(entry.getValue()));
					break;
				default:
					capabilities.setCapability(entry.getKey(), entry.getValue());

				}
				// capabilities.setCapability(entry.getKey(), entry.getValue());
			});
		}

		return capabilities;
	}

	public static String getCapabilityType(String capName) {
		if (mapOfCapabilityNameType != null) {
			if (mapOfCapabilityNameType.containsKey(capName))
				return mapOfCapabilityNameType.get(capName);

		}
		return "String";
	}

}
