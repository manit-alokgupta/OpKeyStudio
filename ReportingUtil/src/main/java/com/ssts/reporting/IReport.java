package com.ssts.reporting;

public interface IReport {

	public void close();

	public void addStep(String action, String[] parameters, Status status);

	public void addStep(String action, String[] parameters, Status status, String output);

	public void addStep(String action, String[] parameters, Status status, String output, Exception ex);

	public void beginFunctionLibrary(String flCaseName);

	public void endFunctionLibrary();

	public void beginTestCase(String testCaseName);

	public void endTestCase();

	public void beginSuite(String suiteName);

	public void endSuite();

}
