package com.opkey.sessions;

import java.io.File;

import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.ssts.reporting.Report;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	public void afterSessionEnds() {

	}

	public void beforeSessionStart() {
		ReportBuilder builder = ReportBuilder
				.atPath(new File(System.getProperty("user.dir") + "/Report" + System.currentTimeMillis() + ".html"));
		Report report = builder.withFormat(ReportFormat.HTML).build();
		// you may now get this instance with Report.get()

	}

	public void pauseExecutionSession() {


	}

	public void resumeExecutionSession() {

	}

}
