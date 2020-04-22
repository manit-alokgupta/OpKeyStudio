package com.ssts.reporting;

public class Report {

	private static Report report;
	private ReportBuilder builder;

	public static Report get() {
		if (report == null) {
			System.err.println("Call the ReportBuilder.build method at the very beginning of the execution");

		}
		return report;
	}

	Report(ReportBuilder builder) {
		this.builder = builder;
		report = this;
	}

	public void addStep(String action, String[] parameters, Status status) {

	}

	public void beginFunctionLibrary(String testCaseName) {

	}

	public void endFunctionLibrary() {

	}

	public void beginTestCase(String testCaseName) {

	}

	public void endTestCase() {

	}

	public void beginSuite(String suiteName) {

	}

	public void endSuite() {

	}
}
