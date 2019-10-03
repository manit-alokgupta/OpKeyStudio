package opkeystudio.opkeystudiocore.core.project.artificates;

public class ArtificateFolder extends Artificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtificateFolder(String path, String filename) {
		super(path, filename, ArtificateType.FOLDER);
		setContainChildren(true);
	}

}
