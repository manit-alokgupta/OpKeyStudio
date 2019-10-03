package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.File;
import java.util.UUID;

class Artificate extends File {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artificateId;
	private ArtificateType artificateType;
	private String artificateTypeString;
	private boolean canContainChilderen = false;

	public enum ArtificateType {
		ROOTFOLDER, FOLDER, TESTCASE, OBJECTREPOSITORY, TESTSUITES
	};

	public Artificate(String arg0, String arg1, ArtificateType type) {
		super(arg0, arg1);
		setArtificateId(UUID.randomUUID().toString());
		setContainChilderen(false);
		setArtificateType(type);
	}

	public String getArtificateId() {
		return artificateId;
	}

	private void setArtificateId(String artificateId) {
		this.artificateId = artificateId;
	}

	public boolean isContainChilderen() {
		return canContainChilderen;
	}

	public void setContainChilderen(boolean canContainChilderen) {
		this.canContainChilderen = canContainChilderen;
	}

	public ArtificateType getArtificateType() {
		return artificateType;
	}

	private void setArtificateType(ArtificateType artificateType) {
		setArtificateTypeString(artificateType.name());
		this.artificateType = artificateType;
	}

	public String getArtificateTypeString() {
		return artificateTypeString;
	}

	private void setArtificateTypeString(String artificateTypeString) {
		this.artificateTypeString = artificateTypeString;
	}

}
