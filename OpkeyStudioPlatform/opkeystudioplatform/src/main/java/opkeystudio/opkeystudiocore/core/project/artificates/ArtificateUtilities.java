package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.File;

public class ArtificateUtilities {
	private int counter = 0;
	private String initialFilename = null;
	private static ArtificateUtilities artificateUtilities;

	public static ArtificateUtilities getInstance() {
		if (artificateUtilities == null) {
			artificateUtilities = new ArtificateUtilities();
		}
		return artificateUtilities;
	}

	public String getUniqueArtificateName(String parentPath, String initial) {
		if (initialFilename == null) {
			initialFilename = initial;
		}
		File parentfile = new File(parentPath);
		String[] filenames = parentfile.list();
		boolean uniqueFound = true;
		for (String filename : filenames) {
			if (filename.equals(initial)) {
				uniqueFound = false;
			}
		}

		if (uniqueFound) {
			counter = 0;
			initialFilename = null;
			return initial;
		}
		counter++;
		return getUniqueArtificateName(parentPath, initialFilename + "_" + String.valueOf(counter));
	}
}
