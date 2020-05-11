package com.ssts.reporting;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReportBuilder {

	private File atPath;
	private ReportFormat format;
	private String SessionName;
	private Map<String, String> sessionParameters = new HashMap<String, String>();

	static IReport report;

	public static IReport get() {
		if (report == null) {
			System.err.println("Call the ReportBuilder.build method at the very beginning of the execution");

		}
		return report;
	}

	private ReportBuilder() {
		// enforce builder pattern
	}

	public static ReportBuilder atPath(File path) {
		ReportBuilder util = new ReportBuilder();
		util.atPath = path;
		System.out.println("### Report @ " + path.getAbsolutePath());
		return util;
	}

	public ReportBuilder withName(String sessionName) {
		this.SessionName = sessionName;
		return this;
	}

	public ReportBuilder withFormat(ReportFormat format) {
		this.format = format;
		return this;
	}

	public void addSessionParameter(String name, String value) {
		this.sessionParameters.put(name, value);
	}

	public IReport build() {
		// report =new ExtentReport(this);
		report = new PCloudyReport(this);
		return report;
	}

	public String getSessionName() {
		return this.SessionName;
	}

	public File getPath() {
		return atPath;
	}

	public ReportFormat getFormat() {
		return format;
	}

	public Map<String, String> getSessionParameters() {
		return this.sessionParameters;
	}

//	enum ReportFormat {
//		PDF, HTML
//	}
}
