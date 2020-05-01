package com.opkey.sessions;

import java.io.File;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.plugin.appium.Finder;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
import com.ssts.reporting.IReport;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {
	
	private static SessionInfo sessionInfo;

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
		SessionHandler.sessionInfo = sessionInfo;
		MobileDevice device = sessionInfo.getMobileDevice();

		System.out.println(">>Mobile Device Info");
		System.out.println("Device Name :" + device.getDisplayName());
		System.out.println("Device Id :" + device.getSerialNumber());
		System.out.println("Device Version :" + device.getVersion());
		System.out.println("Device OS :" + device.getOperatingSystem());

		ReportBuilder builder = ReportBuilder.atPath(new File(sessionInfo.getReportFilePath()));
		IReport report = builder.withName(sessionInfo.getSessionName()).withFormat(ReportFormat.HTML).build();
		// you may now get this instance with Report.get()

	}

	public void pauseExecutionSession(SessionInfo sessionInfo) {

	}

	public void resumeExecutionSession(SessionInfo sessionInfo) {

	}
	
	public static SessionInfo getSessionInfo() {
		return SessionHandler.sessionInfo;
	}

}
