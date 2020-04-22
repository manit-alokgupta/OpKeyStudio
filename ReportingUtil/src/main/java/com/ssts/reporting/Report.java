package com.ssts.reporting;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ssts.reporting.ReportBuilder.ReportFormat;

public class Report {

	private static Report report;
	private ReportBuilder builder;
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extentReport;
	private ExtentTest extentTest;
	ExtentTest nodeTest;
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
	
	public void addStep(String action, Map<String, Object> parameters, Status status) {
		
		String[][] data = new String[parameters.size()][];
		int ii =0;
		for(Entry<String, Object> entry : parameters.entrySet()){
		    data[ii++] = new String[] { entry.getKey(), entry.getValue().toString() };
		}
		
		Markup markup = MarkupHelper.createTable(data);
		nodeTest.log(com.aventstack.extentreports.Status.valueOf(status.toString()), markup);
	}

	public void beginFunctionLibrary(String testCaseName) {

	}

	public void endFunctionLibrary() {

	}

	public void beginTestCase(String testCaseName) {
		extentTest =   extentReport.createTest(testCaseName);
		nodeTest = extentTest.createNode(testCaseName);

	}

	public void endTestCase() {
		this.extentReport.flush();
	}

	public void beginSuite(String suiteName) {
		this.sparkReporter = new ExtentSparkReporter(this.builder.getPath());
		this.sparkReporter.config().setDocumentTitle(suiteName);
		this.sparkReporter.config().setReportName(suiteName);
		this.sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
		this.extentReport = new ExtentReports();
		this.extentReport.attachReporter(sparkReporter);
	}

	public void endSuite() {
		this.extentReport.flush();
	}
	
	public static void main(String[] args) {
		System.out.println("start");
		ReportBuilder builder = ReportBuilder.atPath(new File(System.getProperty("user.dir")+ "/hello.html"));
		Report report1 = builder.withFormat(ReportFormat.HTML).build();
		
		report1.beginSuite("Appium Automation");
		
		
		report1.beginTestCase("Test Case1");
		report1.addStep("Click on Something", new String[] {"Par1, Par2"}, Status.FAIL);
		report1.addStep("Click on Something", new String[] {"Par1, Par2"}, Status.PASS);
		report1.addStep("Click on Something", new String[] {"Par1, Par2"}, Status.PASS);
		report1.endTestCase();
		
		
		report1.beginTestCase("Test Case2");
		Map<String, Object> output = new HashMap<String, Object>();
		output.put("Action", "ClickOnObject");
		output.put("Arguments", "Ahmad, 1234, True");
		output.put("Output", "False");
		output.put("Message", "Property-Name and value matched");
		
		
		Map<String, Object> output1 = new HashMap<String, Object>();
		output1.put("Action", "TypeTextOnEditBox");
		output1.put("Arguments", "Ahmad, 1234, True");
		output1.put("Output", "True");
		output1.put("Message", "");
		
		report1.addStep("Click on Something", output, Status.FAIL);
		report1.addStep("Click on Something",output1, Status.PASS);
		report1.addStep("Click on Something",output, Status.FAIL);
		report1.endTestCase();
		
		report1.endSuite();
		
		System.out.println("End");
	}
}
