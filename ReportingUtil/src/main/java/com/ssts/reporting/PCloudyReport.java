package com.ssts.reporting;

import java.io.File;

import com.ssts.util.reporting.ExecutionResult;
import com.ssts.util.reporting.SingleRunReport;
import com.ssts.util.reporting.printers.HtmlFilePrinter;

public class PCloudyReport implements IReport {

	SingleRunReport report = null;

	private ReportBuilder builder;

	public void close() {
		HtmlFilePrinter printer = new HtmlFilePrinter(builder.getPath());
		printer.printSingleRunReport(report, true);
		ReportBuilder.report = null;
	}

	PCloudyReport(ReportBuilder builder) {
		this.builder = builder;
		report = new SingleRunReport();
		report.Header = builder.getSessionName();
		report.ProjectLogo = "http://www.qatestingtools.com/sites/default/files/tools_shortcuts/OpKey%20150.png";
	}

	public void addStep(String action, String[] parameters, Status status) {
		this.addStep(action, parameters, status, null, null);
	}

	public void addStep(String action, String[] parameters, Status status, File snapshotPath) {
		this.addStep(action, parameters, status, null, snapshotPath);
	}

	public void addStep(String action, String[] parameters, Status status, String output, File snapshotPath) {
		this.addStep(action, parameters, status, output, snapshotPath, null);
	}

	public void addStep(String action, String[] parameters, Status status, String output, File snapshotPath,
			Exception ex) {
		ExecutionResult result;
		if (status == Status.FAIL)
			result = ExecutionResult.Fail;
		else if (status == Status.PASS)
			result = ExecutionResult.Pass;
		else
			result = ExecutionResult.NotExecuted;

		if (snapshotPath != null && snapshotPath.exists())
			this.report.addStep(action, String.join(", ", parameters), output, snapshotPath.getAbsolutePath(), result);
		else
			this.report.addStep(action, String.join(", ", parameters), output, result);

	}

	public void beginFunctionLibrary(String flCaseName) {
		System.out.println("@BeginFunctionLibrary");
		this.report.addComment("@Begin: " + flCaseName);
	}

	public void endFunctionLibrary() {
		System.out.println("@EndFL");

	}

	public void beginTestCase(String testCaseName) {
		System.out.println("@BeginTest");
		this.report.beginTestcase(testCaseName);
	}

	public void endTestCase() {
		System.out.println("@EndTest");

	}

	public void beginSuite(String suiteName) {
		System.out.println("@BeginSuite");

	}

	public void endSuite() {
		System.out.println("@EndSuite");

	}

}
