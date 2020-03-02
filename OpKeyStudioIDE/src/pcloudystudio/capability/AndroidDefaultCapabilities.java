package pcloudystudio.capability;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDefaultCapabilities {

   //  private String app="C:\\Users\\arjun.singh\\Desktop\\FollowMeInstrumentor_V3\\Original\\Alarmy Smart alarm_v1.3.2_apkpure.com.apk";
   //  private String udid="fdfed4de";
	private String plateformName = "Android";
	private String launchTimeOut = "9000";
	private String newCommandTimeout = "6000";
	private String automationName = "UiAutomator2";
	//private String orientation = "LANDSCAPE";
	private DesiredCapabilities capabilities;
	
    public AndroidDefaultCapabilities() {
	capabilities=new DesiredCapabilities();
	capabilities=getCapabilities();
}
	public  DesiredCapabilities getCapabilities() {
             
		
		capabilities.setCapability("platformName", plateformName);
		//capabilities.setCapability("orientation	",orientation );
		capabilities.setCapability("automationName", automationName);
		capabilities.setCapability("newCommandTimeout", newCommandTimeout);
		capabilities.setCapability("launchTimeout", launchTimeOut);
		//capabilities.setCapability("udid", udid);
		//capabilities.setCapability("app", app);
		
		
		return capabilities;
	}

	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}




	public String getPlateformName() {
		return plateformName;
	}

	public void setPlateformName(String plateformName) {
		this.plateformName = plateformName;
	}

	public String getLaunchTimeOut() {
		return launchTimeOut;
	}

	public void setLaunchTimeOut(String launchTimeOut) {
		this.launchTimeOut = launchTimeOut;
	}

	public String getNewCommandTimeout() {
		return newCommandTimeout;
	}

	public void setNewCommandTimeout(String newCommandTimeout) {
		this.newCommandTimeout = newCommandTimeout;
	}

	public String getAutomationName() {
		return automationName;
	}

	public void setAutomationName(String automationName) {
		this.automationName = automationName;
	}

	/*
	 * public String getOrientation() { return orientation; }
	 * 
	 * public void setOrientation(String orientation) { this.orientation =
	 * orientation; }
	 */

}
