package opkeystudio.opkeystudiocore.core.project.artificates;

public class RootFolder extends Artificate {


	public RootFolder(String path, String filename) {
		super(path, filename, ArtificateType.ROOTFOLDER);
		setContainChildren(true);
		setRootFolder(true);
	}

}
