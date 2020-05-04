package com.opkeystudio.core.sessioninterfaces;

public interface ExecutionSession {
	public void beforeSessionStart(Object sessionInfo);
	public void afterSessionEnds(Object sessionInfo);
	public void pauseExecutionSession(Object sessionInfo);
	public void resumeExecutionSession(Object sessionInfo);
}
