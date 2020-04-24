package com.opkeystudio.core.sessioninterfaces;

import com.opkeystudio.core.sessions.SessionInfo;

public interface ExecutionSession {
	public void beforeSessionStart(SessionInfo sessionInfo);
	public void afterSessionEnds(SessionInfo sessionInfo);
	public void pauseExecutionSession(SessionInfo sessionInfo);
	public void resumeExecutionSession(SessionInfo sessionInfo);
}
