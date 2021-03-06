package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.crestech.opkey.plugin.base64.Base64;

import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactUpload {

	public boolean isLoginToSaasRequired() {
		if (ServiceRepository.getInstance().getOpKeyHostAuthToken() == null) {
			return true;
		}
		return false;
	}

	public String uploadArtifactFile(File file, String securedPassword) throws IOException {
		System.out.println("Artifact Uploading " + file.getAbsolutePath());
		String charset = "UTF-8";
		if (securedPassword != null) {
			securedPassword = java.util.Base64.getEncoder().encodeToString(securedPassword.getBytes());
		}
		MultipartUtility multipart = new MultipartUtility(
				"/api/ExportImportAPI/ImportArtifact?MainProcessType=Import&JobType=Opkey&IsImporting=true&SourceProjectPasword="
						+ securedPassword,
				charset);
		multipart.addFilePart("MyFile", file);
		return multipart.finish(); // response from server.
	}

	public String uploadCurrentUsedArtifact(String securedPassword) {
		String dbPath = ServiceRepository.getInstance().getExportedDBFilePath();
		try {
			File outZipFile = createZip(new File(dbPath));
			System.out.println("File Zipped to " + outZipFile.getAbsolutePath());
			String retData = uploadArtifactFile(outZipFile, securedPassword);
			System.out.println("Server Data " + retData);
			return retData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private File createZip(File inputFile) throws FileNotFoundException, IOException {
		String exportedZipId = Utilities.getInstance().getUniqueUUID("");
		File outZipFile = new File(inputFile.getParent() + File.separator + exportedZipId + ".zip");
		FileOutputStream fos = new FileOutputStream(outZipFile.getAbsolutePath());
		ZipOutputStream zipOS = new ZipOutputStream(fos);

		writeToZipFile(inputFile.getAbsolutePath(), zipOS);

		zipOS.close();
		fos.close();
		return outZipFile;
	}

	public void writeToZipFile(String path, ZipOutputStream zipStream) throws FileNotFoundException, IOException {
		File aFile = new File(path);
		FileInputStream fis = new FileInputStream(aFile);
		ZipEntry zipEntry = new ZipEntry(aFile.getName());
		zipStream.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipStream.write(bytes, 0, length);
		}
		zipStream.flush();
		zipStream.closeEntry();
		fis.close();
	}
}
