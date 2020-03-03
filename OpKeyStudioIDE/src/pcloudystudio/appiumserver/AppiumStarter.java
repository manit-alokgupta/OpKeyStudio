package pcloudystudio.appiumserver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import pcloudystudio.capability.AndroidDefaultCapabilities;
import pcloudystudio.capability.AndroidDriverObject;

public class AppiumStarter {
	private AndroidDriver<WebElement> driver;
	private DesiredCapabilities capabilities;

	public AppiumStarter(String deviceName, String ApplicationPath) {
		AppiumServer.startServer();
		capabilities = new AndroidDefaultCapabilities().getCapabilities();
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("app", ApplicationPath);

		try {
			launch(capabilities);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void launch(DesiredCapabilities capabilities) throws MalformedURLException, InterruptedException {
		try {

			String port = new AppiumPortIpInfo().getPort();
			String hostAddress = new AppiumPortIpInfo().getHost_Address();
			if ((port != "" || port != null) && (hostAddress != "" || hostAddress != null)) {
				driver = new AndroidDriver<WebElement>(new URL("http://" + hostAddress + ":" + port + "/wd/hub"),
						capabilities);
				AndroidDriverObject.setDriver(driver);
				// Thread.sleep(20000);
				// String page_source = AndroidDriverObject.getDriver().getPageSource();

			}

			else {

				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				AndroidDriverObject.getInstance();
				AndroidDriverObject.setDriver(driver);

			}
		} catch (Exception e) {
			System.out.println("An Exception Occured See Exception Logs");
			e.printStackTrace();
		}
	}

}