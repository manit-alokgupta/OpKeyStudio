package opkeystudio.opkeystudiocore.core.apis.dto.component.project;

import java.util.ArrayList;
import java.util.List;

public class Opkey6XProjectSchema {
	private String auditTrailLevel;
	private String createdBy;
	private String mode;
	private boolean isEncryptionModeOn;
	private String encryptionPassword;
	private String salt;
	private int maxParallelRunAllowed;
	private int maxParallelRunOnBrowserStack;
	private int maxParallelRunOnSauceLabs;
	private List<String> plugins = new ArrayList<>();

	public String getAuditTrailLevel() {
		return auditTrailLevel;
	}

	public void setAuditTrailLevel(String auditTrailLevel) {
		this.auditTrailLevel = auditTrailLevel;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isEncryptionModeOn() {
		return isEncryptionModeOn;
	}

	public void setEncryptionModeOn(boolean isEncryptionModeOn) {
		this.isEncryptionModeOn = isEncryptionModeOn;
	}

	public String getEncryptionPassword() {
		return encryptionPassword;
	}

	public void setEncryptionPassword(String encryptionPassword) {
		this.encryptionPassword = encryptionPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public List<String> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<String> plugins) {
		this.plugins = plugins;
	}
}
