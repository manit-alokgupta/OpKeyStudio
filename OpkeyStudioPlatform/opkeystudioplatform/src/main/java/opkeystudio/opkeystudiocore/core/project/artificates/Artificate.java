package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Artificate extends File {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artificateId;
	private ArtificateType artificateType;
	private String artificateTypeString;
	private boolean canContainChildren = false;
	private List<Artificate> artificates = null;
	private String artificatePath;

	public enum ArtificateType {
		ROOTFOLDER, FOLDER, TESTCASE, OBJECTREPOSITORY, TESTSUITES
	};

	public Artificate(String arg0, String arg1, ArtificateType type) {
		super(arg0, arg1);
		setArtificateId(UUID.randomUUID().toString());
		setContainChildren(false);
		setArtificateType(type);
	}

	public String getArtificateId() {
		return artificateId;
	}

	private void setArtificateId(String artificateId) {
		this.artificateId = artificateId;
	}

	public boolean isContainChildren() {
		return canContainChildren;
	}

	public void setContainChildren(boolean canContainChilderen) {
		this.canContainChildren = canContainChilderen;
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

	public List<Artificate> getArtificates() {
		if (!isContainChildren()) {
			return new ArrayList<>();
		}
		return artificates;
	}

	public void addArtificate(Artificate artificate) {
		this.artificates.add(artificate);
	}

	public String getArtificatePath() {
		return artificatePath;
	}

	public void createArtificate() throws IOException {
		if (getArtificateType() == ArtificateType.FOLDER || getArtificateType() == ArtificateType.ROOTFOLDER) {
			this.mkdir();
			setArtificatePath(this.getAbsolutePath());
			return;
		}
		this.createNewFile();
		setArtificatePath(this.getAbsolutePath());
	}

	private void setArtificatePath(String artificatePath) {
		this.artificatePath = artificatePath;
	}

}
