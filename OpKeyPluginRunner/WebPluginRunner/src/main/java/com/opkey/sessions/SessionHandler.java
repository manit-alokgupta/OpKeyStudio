package com.opkey.sessions;

import java.io.File;

import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.ssts.reporting.Report;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {
	private static boolean sessionPaused = false;

	public void afterSessionEnds(SessionInfo sessionInfo) {
		try {
			//Report.get().endTestCase();
			//Report.get().endSuite();
			new Browser().Method_CloseAllBrowsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void beforeSessionStart(SessionInfo sessionInfo) {
		ReportBuilder builder = ReportBuilder.atPath(new File(System.getProperty("user.dir") + "/Report.html"));
		Report report = builder.withName("GiveSessionNameHere").withFormat(ReportFormat.HTML).build();
		//report.beginSuite("Web Plugin Automation");
		//report.beginTestCase("Web Test Case");

	}

	public void pauseExecutionSession(SessionInfo sessionInfo) {
		setSessionPaused(true);
		System.out.println("Execution Session is in Pause State");
	}

	public void resumeExecutionSession(SessionInfo sessionInfo) {
		setSessionPaused(false);
		System.out.println("Execution Session is in Resume State");
	}

	public static boolean isSessionPaused() {
		return sessionPaused;
	}

	public static void setSessionPaused(boolean sessionPaused) {
		SessionHandler.sessionPaused = sessionPaused;
	}

}
