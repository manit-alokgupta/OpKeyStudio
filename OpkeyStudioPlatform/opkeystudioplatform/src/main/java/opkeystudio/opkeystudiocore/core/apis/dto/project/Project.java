package opkeystudio.opkeystudiocore.core.apis.dto.project;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class Project {
	@DBField
	private String audittraillevel;
	@DBField
	private String salt;
	@DBField
	private int maxparallelrunallowed;
	
	private int clustering_key;
	@DBField
	private String createdon;
	@DBField
	private int maxparallelrunonsaucelabs;
	@DBField
	private String sessionlimit;
	@DBField
	private String mode;
	@DBField
	private String createdby;
	@DBField
	private String name;
	@DBField
	private String encryptionpassword;
	@DBField
	private String createdon_tz;
	@DBField
	private String p_id;
	@DBField
	private int maxparallelrunonbrowserstack;
	@DBField
	private boolean isencryptionmodeon;

	public String getAudittraillevel() {
		return audittraillevel;
	}

	public void setAudittraillevel(String audittraillevel) {
		this.audittraillevel = audittraillevel;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getMaxparallelrunallowed() {
		return maxparallelrunallowed;
	}

	public void setMaxparallelrunallowed(int maxparallelrunallowed) {
		this.maxparallelrunallowed = maxparallelrunallowed;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public int getMaxparallelrunonsaucelabs() {
		return maxparallelrunonsaucelabs;
	}

	public void setMaxparallelrunonsaucelabs(int maxparallelrunonsaucelabs) {
		this.maxparallelrunonsaucelabs = maxparallelrunonsaucelabs;
	}

	public String getSessionlimit() {
		return sessionlimit;
	}

	public void setSessionlimit(String sessionlimit) {
		this.sessionlimit = sessionlimit;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncryptionpassword() {
		return encryptionpassword;
	}

	public void setEncryptionpassword(String encryptionpassword) {
		this.encryptionpassword = encryptionpassword;
	}

	public String getCreatedon_tz() {
		return createdon_tz;
	}

	public void setCreatedon_tz(String createdon_tz) {
		this.createdon_tz = createdon_tz;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public int getMaxparallelrunonbrowserstack() {
		return maxparallelrunonbrowserstack;
	}

	public void setMaxparallelrunonbrowserstack(int maxparallelrunonbrowserstack) {
		this.maxparallelrunonbrowserstack = maxparallelrunonbrowserstack;
	}

	public boolean isIsencryptionmodeon() {
		return isencryptionmodeon;
	}

	public void setIsencryptionmodeon(boolean isencryptionmodeon) {
		this.isencryptionmodeon = isencryptionmodeon;
	}
}
