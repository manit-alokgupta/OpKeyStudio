package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class Artificate {

	private String artificateId;
	private String artificateName;
	private ArtificateType artificateType;
	private String artificateTypeString;
	private boolean canContainChildren = false;
	private List<Artificate> artificates = new ArrayList<>();
	private String artificatePath;
	private String parentPath;
	private boolean isRootFolder = false;

	public enum ArtificateType {
		ROOTFOLDER, FOLDER, TESTCASE, OBJECTREPOSITORY, TESTSUITES
	};

	public Artificate() {

	}

	public Artificate(String path, String filename, ArtificateType type) {
		setArtificateId(UUID.randomUUID().toString());
		setContainChildren(false);
		setArtificateType(type);
		setArtificateName(filename);
		setParentPath(path);
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
		File file = new File(getArtificatePath());
		if (getArtificateType() == ArtificateType.FOLDER || getArtificateType() == ArtificateType.ROOTFOLDER) {
			file.mkdir();
			return;
		}
		file.createNewFile();
		String serializedData = Utilities.getInstance().getXMLSerializedData(this);
		Utilities.getInstance().writeToFile(file, serializedData);
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

	public String getArtificateName() {
		return artificateName;
	}

	private void setArtificateName(String artificateName) {
		this.artificateName = artificateName;
	}

	public String getParentPath() {
		return parentPath;
	}

	private void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@JsonIgnore
	public File getFile() {
		return new File(getArtificatePath());
	}

}
