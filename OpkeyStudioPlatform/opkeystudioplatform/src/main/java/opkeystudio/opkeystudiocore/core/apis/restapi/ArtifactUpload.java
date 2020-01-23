package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.File;
import java.io.IOException;

public class ArtifactUpload {
	public String uploadArtifactFile(File file) throws IOException {
		System.out.println("Artifact Uploading");
		String charset = "UTF-8";

		MultipartUtility multipart = new MultipartUtility(
				"/ArtifactSyncReceiver/ImportArtifact?MainProcessType=Import&&JobType=Opkey&&IsImporting=false",
				charset);
		multipart.addFormField("IsImporting", "true");
		multipart.addFilePart("file", file);
		return multipart.finish(); // response from server.
	}
}
