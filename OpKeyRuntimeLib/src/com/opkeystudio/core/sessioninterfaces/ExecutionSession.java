package com.opkeystudio.core.sessioninterfaces;

public interface ExecutionSession {
	public void beforeSessionStart();
	public void afterSessionEnds();
	public void pauseExecutionSession();
	public void resumeExecutionSession();
}
