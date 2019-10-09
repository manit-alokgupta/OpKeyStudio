package opkeystudio.opkeystudiocore.core.models.partObject;

import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;

public class WorkBenchPartObject {
	private String identifier;
	private Artificate artificate;

	public WorkBenchPartObject(String identifer) {
		setIdentifier(identifer);
	}

	public String getIdentifier() {
		return identifier;
	}

	private void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Artificate getArtificate() {
		return artificate;
	}

	public void setArtificate(Artificate artificate) {
		this.artificate = artificate;
	}
}
