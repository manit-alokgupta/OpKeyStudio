package com.opkey.sessions;

import java.io.File;

import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.plugin.appium.Finder;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
import com.ssts.reporting.IReport;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	public void afterSessionEnds(SessionInfo sessionInfo) {
		/*
		 * Report.get().endTestCase(); Report.get().endSuite();
		 */
		try {
			Finder.findAppiumDriver().quit();
		} catch (ToolNotSetException e) {
			System.out.println("@Exception while driver quit.");
			e.printStackTrace();
		}
		ReportBuilder.get().close();
	}

	public void beforeSessionStart(SessionInfo sessionInfo) {
		String deviceId = sessionInfo.getPluginSetting("DeviceID");
		String deviceName = sessionInfo.getPluginSetting("DeviceName");
		String deviceVersion = sessionInfo.getPluginSetting("DeviceVersion");
		String deviceApiLevel = sessionInfo.getPluginSetting("DeviceApiLevel");
		String deviceAbi = sessionInfo.getPluginSetting("DeviceABI");

		System.out.println(">>Mobile Device Info");
		System.out.println("Device Name :" + deviceName);
		System.out.println("Device Id :" + deviceId);
		System.out.println("Device Version :" + deviceVersion);
		System.out.println("Device Api Level :" + deviceApiLevel);
		System.out.println("Device Abi :" + deviceAbi);

		ReportBuilder builder = ReportBuilder.atPath(new File(sessionInfo.getReportFilePath()));
		IReport report = builder.withName(sessionInfo.getSessionName()).withFormat(ReportFormat.HTML).build();
		// you may now get this instance with Report.get()

	}

	public void pauseExecutionSession(SessionInfo sessionInfo) {

	}

	public void resumeExecutionSession(SessionInfo sessionInfo) {

	}

}
