package com.opkey.sessions;

import java.io.File;

import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.ssts.reporting.Report;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {

	public void afterSessionEnds() {
		Report.get().endTestCase();
		Report.get().endSuite();
	}

	public void beforeSessionStart() {
		ReportBuilder builder = ReportBuilder.atPath(new File(System.getProperty("user.dir") + "/hello.html"));
		Report report = builder.withFormat(ReportFormat.HTML).build();
		report.beginSuite("Appium Automation");
		report.beginTestCase("Test Case1");
	}

	public void pauseExecutionSession() {
		// TODO Auto-generated method stub
		
	}

	public void resumeExecutionSession() {
		// TODO Auto-generated method stub
		
	}

}
