package opkeystudio.opkeystudiocore.core.project.artificates;

public class RootFolder extends Artificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RootFolder(String path, String filename) {
		super(path, filename, ArtificateType.ROOTFOLDER);
		setContainChildren(true);
	}

}
