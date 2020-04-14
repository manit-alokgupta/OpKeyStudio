package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactTreeNode;
import opkeystudio.opkeystudiocore.core.apis.dto.Project;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactExporting {
	@SuppressWarnings("unused")
	public String getFolderDetails(String folderId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeID", folderId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/GetFolderDetails",
				"GET", params, null, null);
		return retdata;
	}

	@SuppressWarnings("unused")
	public String checkForSecuredData(String nodeid, String portalType) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("strNodeIDs", nodeid);
		params.put("portalType", portalType);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/CheckForSecuredData",
				"GET", params, null, null);
		return retdata;
	}

	@SuppressWarnings("unused")
	public String getSecureDataStatus(String sessionId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sessionId", sessionId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/GetSecuredDataStatus",
				"GET", params, null, null);
		return retdata;
	}

	@SuppressWarnings("unused")
	public String exportArtificate(String strNodeIDs, String jobType, String sessionID, String SourceLabelID,
			String SourceProjectPasword) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("strNodeIDs", strNodeIDs);
		params.put("jobType", jobType);
		params.put("sessionID", sessionID);
		params.put("SourceLabelID", SourceLabelID);
		params.put("SourceProjectPasword", SourceProjectPasword);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/ExportArtifact", "POST",
				params, null, null);
		return retdata;
	}

	@SuppressWarnings("unused")
	public String getArtifactActionLogs(String sessionId, String jobType, String processtype) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sessionId", sessionId);
		params.put("jobType", jobType);
		params.put("processtype", processtype);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/GetArtifactActionLogs",
				"GET", params, null, null);
		return retdata;
	}

	@SuppressWarnings("unused")
	public String downloadExportedZip(String sessionId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sessionId", sessionId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/DownloadArtifactStuff",
				"GET", params, null, null);
		return retdata;
	}

	public boolean exportArtifactFromOpKey(ArtifactTreeNode artifactTreeNode, Project project) throws IOException {
		System.out.println("Selected Artifact " + artifactTreeNode.getText());
		String folderDetails = new ArtifactExporting().getFolderDetails(artifactTreeNode.getId());
		if (folderDetails.equals("true")) {
			String nodeIds = "['" + artifactTreeNode.getId() + "']";
			String sessionData = new ArtifactExporting().checkForSecuredData(nodeIds, "Opkey");
			System.out.println(">>Secured Data Status " + sessionData);
			sessionData = sessionData.replaceAll("\"", "");
			String securedDataStatus = new ArtifactExporting().getSecureDataStatus(sessionData);
			System.out.println(">>Secured Data Data " + securedDataStatus);
			String exportArtifactId = new ArtifactExporting().exportArtificate(nodeIds, "Opkey", sessionData,
					"00000000-0000-0000-0000-000000000000", "");

			String downlodedData = new ArtifactExporting().downloadExportedZip(sessionData);
			System.out.println("Downloaded Data " + downlodedData);
			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(downlodedData);
			JsonNode fileNameNode = rootNode.path("Item1");
			JsonNode filePathNode = rootNode.path("Item2");
			boolean status = downLoadArtifactFile(fileNameNode.textValue(), filePathNode.textValue(),
					project.getName());
			return status;
		}
		return false;
	}

	private int counter = 0;

	private boolean downLoadArtifactFile(String fileName, String fileDownloadURL, String projectName) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String downloadFileId = Utilities.getInstance().getUniqueUUID("");
		String projectsFolder = Utilities.getInstance().getProjectsFolder();
		String artifactDownloadFolder = projectsFolder + File.separator + projectName;
		if (!new File(artifactDownloadFolder).exists()) {
			new File(artifactDownloadFolder).mkdir();
		}
		String artifactFilePath = artifactDownloadFolder + File.separator + downloadFileId + ".zip";
		try {
			downloadUsingStream(fileDownloadURL, artifactFilePath);
			Utilities.getInstance().extractZipFolder(artifactFilePath, artifactDownloadFolder);

			File dbFilesFolder = new File(artifactDownloadFolder);
			File[] dbFiles = dbFilesFolder.listFiles();
			for (File dbFile : dbFiles) {
				if (dbFile.getName().endsWith(".db")) {
					String dbFileName = dbFile.getAbsolutePath();
					System.out.println("Downloaded File Path " + dbFileName);
					ServiceRepository.getInstance().setExortedDBFilePath(dbFileName);
					break;
				}
			}
			new File(artifactFilePath).delete();
		} catch (IOException e) {
			e.printStackTrace();
			if (counter == 2) {
				return false;
			}
			counter++;
			downLoadArtifactFile(fileName, fileDownloadURL, projectName);
		}
		return true;
	}

	private void downloadUsingStream(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();
	}

}
