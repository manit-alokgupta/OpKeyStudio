package com.opkey.sessions;

import java.io.File;

import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.ssts.reporting.Report;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	public void afterSessionEnds(SessionInfo sessionInfo) {
		
		Report.get().endTestCase();
		Report.get().endSuite();
	}

	public void beforeSessionStart(SessionInfo sessionInfo) {
		ReportBuilder builder = ReportBuilder
				.atPath(new File(System.getProperty("user.dir") + "/Report" + System.currentTimeMillis() + ".html"));
		Report report = builder.withFormat(ReportFormat.HTML).build();
		report.beginSuite("Appium Plugin Automation");
		report.beginTestCase("Appium Test Case");

		// you may now get this instance with Report.get()

	}

	public void pauseExecutionSession(SessionInfo sessionInfo) {


	}

	public void resumeExecutionSession(SessionInfo sessionInfo) {

	}

}
