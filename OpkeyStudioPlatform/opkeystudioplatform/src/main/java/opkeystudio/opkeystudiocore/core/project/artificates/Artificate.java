package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Artificate extends File {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artificateId;
	private ArtificateType artificateType;
	private String artificateTypeString;
	private boolean canContainChildren = false;
	private List<Artificate> artificates = new ArrayList<>();
	private String artificatePath;
	private boolean isRootFolder = false;

	public enum ArtificateType {
		ROOTFOLDER, FOLDER, TESTCASE, OBJECTREPOSITORY, TESTSUITES
	};

	public Artificate(String path, String filename, ArtificateType type) {
		super(path, filename);
		setArtificateId(UUID.randomUUID().toString());
		setContainChildren(false);
		setArtificateType(type);
		setArtificatePath(path + File.separator + filename);
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

	public void addArtificates(List<Artificate> artificates) {
		this.artificates.addAll(artificates);
	}

	public String getArtificatePath() {
		return artificatePath;
	}

	public void createArtificate() throws IOException {
		if (getArtificateType() == ArtificateType.FOLDER || getArtificateType() == ArtificateType.ROOTFOLDER) {
			this.mkdir();
			String serializedData = Utilities.getInstance().getXMLSerializedData(this);
			Utilities.getInstance().writeToFile(new File(getArtificatePath() + ".descriptor"), serializedData);
			return;
		}
		this.createNewFile();
		String serializedData = Utilities.getInstance().getXMLSerializedData(this);
		Utilities.getInstance().writeToFile(this, serializedData);
	}

	private void setArtificatePath(String artificatePath) {
		this.artificatePath = artificatePath;
	}

	public boolean isRootFolder() {
		return isRootFolder;
	}

	public void setRootFolder(boolean isRootFolder) {
		this.isRootFolder = isRootFolder;
	}

}
