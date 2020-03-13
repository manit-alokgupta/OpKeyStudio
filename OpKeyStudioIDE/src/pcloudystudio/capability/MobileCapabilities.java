package pcloudystudio.capability;

import java.util.LinkedHashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileCapabilities {
	private static MobileCapabilities instance;
	private static LinkedHashMap<String, String> mapOfCapabilities;

	private MobileCapabilities() {
	}

	public static MobileCapabilities getinstance() {
		if (instance == null)
			return new MobileCapabilities();
		return instance;
	}

	public static LinkedHashMap<String, String> getMapOfCapabilities() {
		return mapOfCapabilities;
	}

	public void setMapOfCapabilities(LinkedHashMap<String, String> mapOfCapabilities) {
		MobileCapabilities.mapOfCapabilities = mapOfCapabilities;
	}

	public static DesiredCapabilities getCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		LinkedHashMap<String, String> caps_map = getMapOfCapabilities();

		if (!caps_map.isEmpty()) {
			caps_map.entrySet().forEach(entry -> {
				capabilities.setCapability(entry.getKey(), entry.getValue());
			});
		}

		return capabilities;
	}

}
