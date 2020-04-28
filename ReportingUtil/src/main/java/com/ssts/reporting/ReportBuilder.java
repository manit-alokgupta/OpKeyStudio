package com.ssts.reporting;

import java.io.File;

public class ReportBuilder {

	private File atPath;
	private ReportFormat format;
	private String SessionName;

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

//	enum ReportFormat {
//		PDF, HTML
//	}
}
