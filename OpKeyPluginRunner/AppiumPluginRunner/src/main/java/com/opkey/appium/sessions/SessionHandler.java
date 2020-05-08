package com.opkey.appium.sessions;

import java.io.File;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.opkeystudio.core.sessions.SessionInfoConverter;
import com.plugin.appium.Finder;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
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
		String deviceApiLevel = sessionInfo.getPluginSetting("DeviceApiLevel"); // device SDK
		String deviceAbi = sessionInfo.getPluginSetting("DeviceABI"); // device ABI

		/*
		 * Report.get().endTestCase(); Report.get().endSuite();
		 */

		try {
			new Connect2AppiumServer().Method_closeApplication();
		} catch (Exception e) {
		}
		ReportBuilder.get().close();
	}

	public void beforeSessionStart(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
		String deviceApiLevel = sessionInfo.getPluginSetting("DeviceApiLevel"); // device SDK
		String deviceAbi = sessionInfo.getPluginSetting("DeviceABI"); // device ABI

		SessionHandler.sessionInfo = sessionInfo;
		MobileDevice device = sessionInfo.getMobileDevice();

		System.out.println(">>Mobile Device Info");
		System.out.println("Device Name :" + device.getDisplayName());
		System.out.println("Device Id :" + device.getSerialNumber());
		System.out.println("Device Version :" + device.getVersion());
		System.out.println("Device OS :" + device.getOperatingSystem());
		
		File htmlFile = new File(sessionInfo.getReportFilePath());
		reportFilePath = htmlFile.getParentFile();
		ReportBuilder builder = ReportBuilder.atPath(htmlFile);
		IReport report = builder.withName(sessionInfo.getSessionName()).withFormat(ReportFormat.HTML).build();
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
