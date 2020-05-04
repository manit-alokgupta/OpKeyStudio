package com.opkey.appium.sessions;

import java.io.File;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.opkeystudio.core.sessions.SessionInfoConverter;
import com.plugin.appium.Finder;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
import com.ssts.reporting.IReport;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	private static SessionInfo sessionInfo;

	public void afterSessionEnds(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
		/*
		 * Report.get().endTestCase(); Report.get().endSuite();
		 */
		ReportBuilder.get().close();
	}

	public void beforeSessionStart(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
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

	public void pauseExecutionSession(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
	}

	public void resumeExecutionSession(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
	}

	public static SessionInfo getSessionInfo() {
		return SessionHandler.sessionInfo;
	}

}
