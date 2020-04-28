package com.opkey.sessions;

import java.io.File;

import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.ssts.reporting.IReport;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {
	private static boolean sessionPaused = false;

	public void afterSessionEnds(SessionInfo sessionInfo) {
		try {
			new Browser().Method_CloseAllBrowsers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ReportBuilder.get().close();
	}

	public void beforeSessionStart(SessionInfo sessionInfo) {
		ReportBuilder builder = ReportBuilder.atPath(new File(sessionInfo.getReportFilePath()));
		IReport report = builder.withName(sessionInfo.getSessionName()).withFormat(ReportFormat.HTML).build();
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
