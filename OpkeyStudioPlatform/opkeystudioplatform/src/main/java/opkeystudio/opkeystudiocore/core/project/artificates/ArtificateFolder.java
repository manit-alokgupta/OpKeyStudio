package opkeystudio.opkeystudiocore.core.project.artificates;

public class ArtificateFolder extends Artificate {
	public ArtificateFolder(String path, String filename) {
		super(path, filename, ArtificateType.FOLDER);
		setContainChildren(true);
	}

}
