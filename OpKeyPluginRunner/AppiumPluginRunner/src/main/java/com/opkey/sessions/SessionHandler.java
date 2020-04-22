package com.opkey.sessions;

import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.opkeystudio.core.sessioninterfaces.ExecutionSession;

public class SessionHandler implements ExecutionSession {

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

}
