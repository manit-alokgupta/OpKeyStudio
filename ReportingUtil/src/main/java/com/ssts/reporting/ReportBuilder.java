package com.ssts.reporting;

import java.io.File;

public class ReportBuilder {

	private File atPath;
	private ReportFormat format;

	private ReportBuilder() {
		// enforce builder pattern
	}

	public static ReportBuilder atPath(File path) {
		ReportBuilder util = new ReportBuilder();
		util.atPath = path;
		return util;
	}

	public ReportBuilder withFormat(ReportFormat format) {
		this.format = format;
		return this;
	}

	public Report build() {
		return new Report(this);
	}

	public File getPath() {
		return atPath;
	}

	public ReportFormat getFormat() {
		return format;
	}

	enum ReportFormat {
		PDF, HTML
	}
}
