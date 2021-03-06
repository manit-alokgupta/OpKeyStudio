package opkeystudio.opkeystudiocore.core.apis.dto;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class Project extends Modified {
	@DBField
	private String name;
	@DBField
	private String projectPath;
	@DBField
	private String p_ID;
	@DBField
	private String auditTrailLevel_ENUM;
	@DBField
	private String createdBy_ID;
	@DBField
	private String createdOn;
	@DBField
	private String projectMode_ENUM;
	@DBField
	private boolean isEncryptionModeOn;
	@DBField
	private int maxParallelRunAllowed;
	@DBField
	private int maxParallelRunOnBrowserStack;
	@DBField
	private int maxParallelRunOnSauceLabs;
	@DBField
	private String sessionLimit;
	@DBField
	private String tracking_ID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getP_ID() {
		return p_ID;
	}

	public void setP_ID(String p_ID) {
		this.p_ID = p_ID;
	}

	public String getAuditTrailLevel_ENUM() {
		return auditTrailLevel_ENUM;
	}

	public void setAuditTrailLevel_ENUM(String auditTrailLevel_ENUM) {
		this.auditTrailLevel_ENUM = auditTrailLevel_ENUM;
	}

	public String getCreatedBy_ID() {
		return createdBy_ID;
	}

	public void setCreatedBy_ID(String createdBy_ID) {
		this.createdBy_ID = createdBy_ID;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getProjectMode_ENUM() {
		return projectMode_ENUM;
	}

	public void setProjectMode_ENUM(String projectMode_ENUM) {
		this.projectMode_ENUM = projectMode_ENUM;
	}

	public boolean isEncryptionModeOn() {
		return isEncryptionModeOn;
	}

	public void setEncryptionModeOn(boolean isEncryptionModeOn) {
		this.isEncryptionModeOn = isEncryptionModeOn;
	}

	public int getMaxParallelRunAllowed() {
		return maxParallelRunAllowed;
	}

	public void setMaxParallelRunAllowed(int maxParallelRunAllowed) {
		this.maxParallelRunAllowed = maxParallelRunAllowed;
	}

	public int getMaxParallelRunOnBrowserStack() {
		return maxParallelRunOnBrowserStack;
	}

	public void setMaxParallelRunOnBrowserStack(int maxParallelRunOnBrowserStack) {
		this.maxParallelRunOnBrowserStack = maxParallelRunOnBrowserStack;
	}

	public int getMaxParallelRunOnSauceLabs() {
		return maxParallelRunOnSauceLabs;
	}

	public void setMaxParallelRunOnSauceLabs(int maxParallelRunOnSauceLabs) {
		this.maxParallelRunOnSauceLabs = maxParallelRunOnSauceLabs;
	}

	public String getSessionLimit() {
		return sessionLimit;
	}

	public void setSessionLimit(String sessionLimit) {
		this.sessionLimit = sessionLimit;
	}

	public String getTracking_ID() {
		return tracking_ID;
	}

	public void setTracking_ID(String tracking_ID) {
		this.tracking_ID = tracking_ID;
	}
}
