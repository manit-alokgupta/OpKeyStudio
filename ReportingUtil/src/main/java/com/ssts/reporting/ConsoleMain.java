package com.ssts.reporting;

import java.io.File;

public class ConsoleMain {

	public static void main(String[] args) {
		System.out.println("start");
		ReportBuilder builder = ReportBuilder.atPath(new File(System.getProperty("user.dir") + "/hello.html"));
		IReport report1 = builder.withFormat(ReportFormat.HTML).build();

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

		report1.close();
		
		System.out.println(builder.getPath().getAbsolutePath());
	}

}
