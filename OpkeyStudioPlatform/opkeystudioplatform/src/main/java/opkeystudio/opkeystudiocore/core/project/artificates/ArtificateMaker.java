package opkeystudio.opkeystudiocore.core.project.artificates;

import java.io.IOException;

import opkeystudio.opkeystudiocore.core.project.artificates.Artificate.ArtificateType;

public class ArtificateMaker {
	private void createArtificate(String path, String artificateName, ArtificateType type) throws IOException {
		artificateName=ArtificateUtilities.getInstance().getUniqueArtificateName(path, artificateName);
		Artificate artificate = new Artificate(path, artificateName, type);
		artificate.createArtificate();
	}

	public void createTestCase(String parentPath, String artificateName) throws IOException {
		createArtificate(parentPath, artificateName, ArtificateType.TESTCASE);
	}

	public void createObjectRepository(String parentPath, String artificateName) throws IOException {
		createArtificate(parentPath, artificateName, ArtificateType.OBJECTREPOSITORY);
	}
}
