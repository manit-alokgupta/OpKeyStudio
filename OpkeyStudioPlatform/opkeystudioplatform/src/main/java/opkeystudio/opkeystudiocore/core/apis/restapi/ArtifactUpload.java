package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.File;
import java.io.IOException;

public class ArtifactUpload {
	public String uploadArtifactFile(String url, String filePath) throws IOException {
		String charset = "UTF-8";

		MultipartUtility multipart = new MultipartUtility(url, charset);
		multipart.addFormField("param_name_1", "param_value");
		multipart.addFormField("param_name_2", "param_value");
		multipart.addFormField("param_name_3", "param_value");
		multipart.addFilePart("file_param_1", new File(filePath));
		return multipart.finish(); // response from server.
	}
}
