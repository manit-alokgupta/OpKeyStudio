package com.ssts.reporting;

import java.io.File;
import java.util.HashMap;
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
	
	public void close() {
		this.report = null;
	}

	Report(ReportBuilder builder) {
		this.builder = builder;
		report = this;
	}

	public void addStep(String action, String[] parameters, Status status) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Action", action);

		int ii = 1;
		for (String entry : parameters) {
			map.put("Param-" + ii, entry);
		}

		this.addStep(map, status);
	}

	public void addStep(Map<String, String> parameters, Status status) {
		this.addStep(parameters, status, null);
	}

	public void addStep(Map<String, String> parameters, Status status, Exception e) {
		System.out.println("@AddStep");
		if (aNode == null) {
			aNode = reportingStacks.peek().createNode(reportingStacks.peek().getModel().getName() + " continued...");
		}

		if (e != null) {
			aNode.error(e);
		} else {
			String[][] data = new String[parameters.size()][];
			int ii = 0;
			for (Entry<String, String> entry : parameters.entrySet()) {
				System.out.println("Key: " + entry.getKey() + ", " + entry.getValue());
				data[ii++] = new String[] { entry.getKey(), entry.getValue() };
			}
			Markup markup = MarkupHelper.createTable(data);
			aNode.log(com.aventstack.extentreports.Status.valueOf(status.toString()), markup);
		}

		this.extentReport.flush();
	}

	public void beginFunctionLibrary(String flCaseName) {
		System.out.println("@BeginSuite");
		ExtentTest childNode = reportingStacks.peek().createNode(flCaseName);
		aNode = childNode;
		reportingStacks.push(childNode);
	}

	public void endFunctionLibrary() {
		System.out.println("@EndFL");
		aNode = null;
		reportingStacks.pop();
	}

	public void beginTestCase(String testCaseName) {
		System.out.println("@BeginTest");
		ExtentTest childNode = extentReport.createTest(testCaseName);
		aNode = null;
		reportingStacks.push(childNode);
	}

	public void endTestCase() {
		System.out.println("@EndTest");
		reportingStacks.pop();
		aNode = null;
		this.extentReport.flush();

	}

	public void beginSuite(String suiteName) {
		System.out.println("@BeginSuite");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(this.builder.getPath());
		sparkReporter.config().setDocumentTitle(suiteName);
		sparkReporter.config().setReportName(suiteName);
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
		this.extentReport = new ExtentReports();
		this.extentReport.attachReporter(sparkReporter);
	}

	public void endSuite() {
		System.out.println("@EndSuite");
		this.extentReport.flush();
		report = null;
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
