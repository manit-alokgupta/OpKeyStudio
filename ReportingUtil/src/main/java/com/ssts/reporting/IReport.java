package com.ssts.reporting;

import java.io.File;

public interface IReport {

	public void close();

	public void addStep(String action, String[] parameters, Status status);
	
	public void addStep(String action, String[] parameters, Status status, File snapshotPath);

	public void addStep(String action, String[] parameters, Status status, String output, File snapshotPath);

	public void addStep(String action, String[] parameters, Status status, String output, File snapshotPath,
			Exception ex);

	public void beginFunctionLibrary(String flCaseName);

	public void endFunctionLibrary();

	public void beginTestCase(String testCaseName);

	public void endTestCase();

	public void beginSuite(String suiteName);

	public void endSuite();

}
