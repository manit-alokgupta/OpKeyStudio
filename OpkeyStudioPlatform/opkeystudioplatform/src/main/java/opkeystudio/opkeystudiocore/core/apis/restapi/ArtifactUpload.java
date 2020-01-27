package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactUpload {
	public String uploadArtifactFile(File file) throws IOException {
		System.out.println("Artifact Uploading");
		String charset = "UTF-8";

		MultipartUtility multipart = new MultipartUtility(
				"/api/ExportImportAPI/ImportArtifact?MainProcessType=Import&JobType=Opkey&IsImporting=true", charset);
		multipart.addFilePart("file", file);
		return multipart.finish(); // response from server.
	}

	public void uploadCurrentUsedArtifact() {
		String dbPath = ServiceRepository.getInstance().getExportedDBFilePath();
		try {
			File outZipFile = createZip(new File(dbPath));
			System.out.println("File Zipped to " + outZipFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("Writing file : '" + path + "' to zip file");
		File aFile = new File(path);
		FileInputStream fis = new FileInputStream(aFile);
		ZipEntry zipEntry = new ZipEntry(path);
		zipStream.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipStream.write(bytes, 0, length);
		}

		zipStream.closeEntry();
		fis.close();
	}
}
