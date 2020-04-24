package com.opkey.sessions;

import java.io.File;

import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.ssts.reporting.Report;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	public void afterSessionEnds(SessionInfo sessionInfo) {
		/*
		 * Report.get().endTestCase(); Report.get().endSuite();
		 */
	}

	public void beforeSessionStart(SessionInfo sessionInfo) {
		ReportBuilder builder = ReportBuilder.atPath(new File(sessionInfo.getReportFilePath()));
		Report report = builder.withName(sessionInfo.getSessionName()).withFormat(ReportFormat.HTML).build();
		// you may now get this instance with Report.get()

	}

	public void pauseExecutionSession(SessionInfo sessionInfo) {

	}

	public void resumeExecutionSession(SessionInfo sessionInfo) {

	}

}
