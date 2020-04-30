package pcloudystudio.appium;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class MobileDriverObject {
	private static AndroidDriver<WebElement> driver;
	private static MobileDriverObject instance;

	private MobileDriverObject() {
	}

	public static MobileDriverObject getInstance() {
		if (instance == null)
			return new MobileDriverObject();

		return instance;
	}

	public static AndroidDriver<WebElement> getDriver() {
		return driver;
	}

	public void setDriver(AndroidDriver<WebElement> driver) {
		MobileDriverObject.driver = driver;
	}

}
