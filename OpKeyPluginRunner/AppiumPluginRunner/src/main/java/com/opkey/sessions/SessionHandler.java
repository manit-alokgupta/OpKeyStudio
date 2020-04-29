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
		ReportBuilder builder = ReportBuilder.atPath(new File(sessionInfo.getReportFilePath()));
		IReport report = builder.withName(sessionInfo.getSessionName()).withFormat(ReportFormat.HTML).build();
		// you may now get this instance with Report.get()

	}

	public void pauseExecutionSession(SessionInfo sessionInfo) {

	}

	public void resumeExecutionSession(SessionInfo sessionInfo) {

	}

}
