package com.opkey.web.sessions;

import java.io.File;

import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;
import com.opkeystudio.core.sessions.SessionInfo;
import com.opkeystudio.core.sessions.SessionInfoConverter;
import com.ssts.reporting.IReport;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.ReportFormat;

public class SessionHandler implements ExecutionSession {
	private static boolean sessionPaused = false;
	public static File reportFilePath;
	public static File screenshotPath;

	public void afterSessionEnds(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
		try {
			new Browser().Method_CloseAllBrowsers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ReportBuilder.get().close();
	}

	public void beforeSessionStart(Object sessionObject) {
		SessionInfo sessionInfo = SessionInfoConverter.convertIntoSessionInfo(sessionObject);
		File htmlFile = new File(sessionInfo.reportFilePath);
		reportFilePath = htmlFile.getParentFile();
		ReportBuilder builder = ReportBuilder.atPath(htmlFile);
		builder.addSessionParameter("BuildName", sessionInfo.buildName);
		builder.addSessionParameter("SessionDir", sessionInfo.sessionDirectory);
		IReport report = builder.withName(sessionInfo.sessionName).withFormat(ReportFormat.HTML).build();
		
		setScreenshotDirPath();
	}

	public void pauseExecutionSession(Object sessionInfo) {
		setSessionPaused(true);
		System.out.println("Execution Session is in Pause State");
	}

	public void resumeExecutionSession(Object sessionInfo) {
		setSessionPaused(false);
		System.out.println("Execution Session is in Resume State");
	}

	public static boolean isSessionPaused() {
		return sessionPaused;
	}

	public static void setSessionPaused(boolean sessionPaused) {
		SessionHandler.sessionPaused = sessionPaused;
	}
	
	public static void setScreenshotDirPath() {
		screenshotPath = new File(reportFilePath.getPath() + File.separator + "screenshot");
		screenshotPath.mkdir();
	}
}
