package opkeystudio.opkeystudiocore.core.project.artificates;

public class RootFolder extends Artificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RootFolder(String arg0, String arg1) {
		super(arg0, arg1, ArtificateType.ROOTFOLDER);
		setContainChildren(true);
	}

}
