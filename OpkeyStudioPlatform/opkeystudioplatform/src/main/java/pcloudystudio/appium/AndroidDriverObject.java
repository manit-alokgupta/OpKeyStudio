package pcloudystudio.appium;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverObject {
	private static AndroidDriver<WebElement> driver;
	private static AndroidDriverObject instance;

	private AndroidDriverObject() {
	}

	public static AndroidDriverObject getInstance() {
		if (instance == null)
			return new AndroidDriverObject();

		return instance;
	}

	public static AndroidDriver<WebElement> getDriver() {
		return driver;
	}

	public void setDriver(AndroidDriver<WebElement> driver) {
		AndroidDriverObject.driver = driver;
	}

}