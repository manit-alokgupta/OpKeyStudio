package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.File;
import java.util.UUID;

class Artificate extends File {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artificateId;

	public Artificate(File arg0, String arg1) {
		super(arg0, arg1);
		setArtificateId(UUID.randomUUID().toString());
	}

	public String getArtificateId() {
		return artificateId;
	}

	private void setArtificateId(String artificateId) {
		this.artificateId = artificateId;
	}

}
