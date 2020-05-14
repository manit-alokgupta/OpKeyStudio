package com.opkey.appium.sessions;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.opkeystudio.core.sessions.SessionInfoConverter;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Connect2AppiumServer;
import com.ssts.reporting.IReport;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	private static SessionInfo sessionInfo;
	public static File reportFilePath;
	public static File screenshotPath;

	public void afterSessionEnds(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
		String deviceApiLevel = sessionInfo.pluginSettings.get("DeviceApiLevel"); // device SDK
		String deviceAbi = sessionInfo.pluginSettings.get("DeviceABI"); // device ABI

		/*
		 * Report.get().endTestCase(); Report.get().endSuite();
		 */

		try {
			new Connect2AppiumServer().Method_closeApplication();
		} catch (Exception e) {
		}

		if (ReportBuilder.get() != null)
			ReportBuilder.get().close();
	}

	public void beforeSessionStart(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
		SessionHandler.sessionInfo = sessionInfo;

		// Appium Configuration Info
//		String appiumHost = sessionInfo.pluginSettings.get("appiumHost"); // appium Host
//		String appiumPort = sessionInfo.pluginSettings.get("appiumPort"); // appium Port
//		String appiumDir = sessionInfo.pluginSettings.get("appiumDir"); // appium directory
//
//		System.out.println("appiumHost: " + appiumHost);
//		System.out.println("appiumPort: " + appiumPort);
//		System.out.println("appiumDir: " + appiumDir);
//		// Appium Mobile Desired Capabilities
//		DesiredCapabilities mobileCapabilities = sessionInfo.getMobileCapabilities();

		// Mobile Device Info
		MobileDevice device = sessionInfo.mobileDevice;
		System.out.println(">>Mobile Device Info");
		System.out.println("Device Name :" + device.getDisplayName());
		System.out.println("Device Id :" + device.getSerialNumber());
		System.out.println("Device Version :" + device.getVersion());
		System.out.println("Device OS :" + device.getOperatingSystem());
		String deviceApiLevel = sessionInfo.pluginSettings.get("DeviceApiLevel"); // device SDK
		String deviceAbi = sessionInfo.pluginSettings.get("DeviceABI"); // device ABI

		File htmlFile = new File(sessionInfo.reportFilePath);
		reportFilePath = htmlFile.getParentFile();
		ReportBuilder builder = ReportBuilder.atPath(htmlFile);
		builder.addSessionParameter("BuildName", sessionInfo.buildName);
		builder.addSessionParameter("SessionDir", sessionInfo.sessionDirectory);

		IReport report = builder.withName(sessionInfo.sessionName).withFormat(ReportFormat.HTML).build();
		// you may now get this instance with Report.get()

		setScreenshotDirPath();
	}

	public void pauseExecutionSession(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
	}

	public void resumeExecutionSession(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
	}

	public static SessionInfo getSessionInfo() {
		return SessionHandler.sessionInfo;
	}

	public static void setScreenshotDirPath() {
		screenshotPath = new File(reportFilePath.getPath() + File.separator + "screenshot");
		screenshotPath.mkdir();
	}
}
