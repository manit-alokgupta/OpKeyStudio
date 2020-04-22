package com.opkey.sessions;

import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;

public class SessionHandler implements ExecutionSession {
	private static boolean sessionPaused = false;

	public void afterSessionEnds() {
		try {
			new Browser().Method_CloseAllBrowsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void beforeSessionStart() {
		// TODO Auto-generated method stub

	}

	public void pauseExecutionSession() {
		setSessionPaused(true);
		System.out.println("Execution Session is in Pause State");
	}

	public void resumeExecutionSession() {
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
