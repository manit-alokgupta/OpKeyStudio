package com.ssts.reporting;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {

	private static Report report;
	private ReportBuilder builder;

	private ExtentReports extentReport;
	private ExtentTest aNode = null;

	private Stack<ExtentTest> reportingStacks = new Stack<ExtentTest>();

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
		String[][] data = new String[parameters.length + 1][];
		data[0] = new String[] { "Action", action };

		int ii = 1;
		for (String entry : parameters) {
			data[ii++] = new String[] { "Param-" + ii, entry };
		}

		this.addStep(data, status);
	}

	public void addStep(Map<String, String> parameters, Status status) {

		String[][] data = new String[parameters.size()][];
		int ii = 0;
		for (Entry<String, String> entry : parameters.entrySet()) {
			data[ii++] = new String[] { entry.getKey(), entry.getValue() };
		}
		this.addStep(data, status);
	}

	public void addStep(String[][] parameters, Status status) {
		
		if (aNode == null) {
			aNode = reportingStacks.peek().createNode(reportingStacks.peek().getModel().getName() + " continued...");
		}
		
		Markup markup = MarkupHelper.createTable(parameters);
		aNode.log(com.aventstack.extentreports.Status.valueOf(status.toString()), markup);
	}

	public void beginFunctionLibrary(String flCaseName) {
		ExtentTest childNode = reportingStacks.peek().createNode(flCaseName);
		aNode = childNode;
		reportingStacks.push(childNode);
	}

	public void endFunctionLibrary() {
		aNode = null;
		reportingStacks.pop();
	}

	public void beginTestCase(String testCaseName) {
		ExtentTest childNode = extentReport.createTest(testCaseName);
		aNode = null;
		reportingStacks.push(childNode);
	}

	public void endTestCase() {
		reportingStacks.pop();
		aNode = null;
		this.extentReport.flush();

	}

	public void beginSuite(String suiteName) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(this.builder.getPath());
		sparkReporter.config().setDocumentTitle(suiteName);
		sparkReporter.config().setReportName(suiteName);
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
		this.extentReport = new ExtentReports();
		this.extentReport.attachReporter(sparkReporter);
	}

	public void endSuite() {
		this.extentReport.flush();
	}

	public static void main(String[] args) {
		System.out.println("start");
		ReportBuilder builder = ReportBuilder.atPath(new File(System.getProperty("user.dir") + "/hello.html"));
		Report report1 = builder.withFormat(ReportFormat.HTML).build();

		report1.beginSuite("Appium Automation");

		report1.beginTestCase("Test Case1");
		report1.addStep("Click on Something", new String[] { "Par1, Par2" }, Status.FAIL);
		report1.addStep("Click on Something", new String[] { "Par1, Par2" }, Status.PASS);
		report1.addStep("Click on Something", new String[] { "Par1, Par2" }, Status.PASS);
		report1.endTestCase();

		report1.beginTestCase("Test Case2");

		report1.beginFunctionLibrary("FL0");
		report1.addStep("tc2-click", new String[] { "Par1, Par2" }, Status.PASS);
		report1.endFunctionLibrary();

		report1.beginFunctionLibrary("FL1");
		report1.addStep("tc2-fl1-click", new String[] { "Par1, Par2" }, Status.PASS);

		report1.beginFunctionLibrary("Inner FL1");
		report1.addStep("tc2-fl1-innerfl1-click1", new String[] { "Par1, Par2" }, Status.PASS);
		report1.addStep("tc2-fl1-innerfl1-click2", new String[] { "Par1, Par2" }, Status.PASS);
		report1.beginFunctionLibrary("Inner FL1- InnerFL1");
		report1.addStep("tc2-fl1-innerfl1-innerfl1-click1", new String[] { "Par1, Par2" }, Status.PASS);
		report1.addStep("tc2-fl1-innerfl1-innerfl1-click2", new String[] { "Par1, Par2" }, Status.PASS);
		report1.endFunctionLibrary();
		report1.addStep("tc2-fl1-innerfl1-click3", new String[] { "Par1, Par2" }, Status.PASS);
		report1.endFunctionLibrary();

		report1.addStep("tc2-fl1-click2", new String[] { "Par1, Par2" }, Status.FAIL);
		report1.endFunctionLibrary();

		report1.endTestCase();

		report1.endSuite();

		System.out.println(builder.getPath().getAbsolutePath());
	}
}
