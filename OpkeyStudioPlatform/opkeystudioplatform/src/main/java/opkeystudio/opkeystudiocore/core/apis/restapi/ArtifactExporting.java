package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactTreeNode;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;
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

	public boolean exportArtifactFromOpKey(ArtifactTreeNode artifactTreeNode) throws IOException {
		System.out.println("Selected Artifact " + artifactTreeNode.getText());
		String folderDetails = new ArtifactExporting().getFolderDetails(artifactTreeNode.getId());
		if (folderDetails.equals("true")) {
			String nodeIds = "['" + artifactTreeNode.getId() + "']";
			String sessionData = new ArtifactExporting().checkForSecuredData(nodeIds, "Opkey");
			sessionData = sessionData.replaceAll("\"", "");
			String securedDataStatus = new ArtifactExporting().getSecureDataStatus(sessionData);

			String exportArtifactId = new ArtifactExporting().exportArtificate(nodeIds, "Opkey", sessionData,
					"00000000-0000-0000-0000-000000000000", "");

			String downlodedData = new ArtifactExporting().downloadExportedZip(sessionData);
			JSONObject jsonObject = new JSONObject(downlodedData);
			String fileName = jsonObject.getString("Item1");
			String filePath = jsonObject.getString("Item2");
			new UploadFile().executeMultiPartRequest(
					"https://qa1.stg.smartopkey.com/ArtifactSyncReceiver/ImportArtifact?MainProcessType=Import&&JobType=Opkey&&IsImporting=false",
					new File("E:\\Data\\ExportedArtifacts.zip"), "ExportedArtifacts.zip", "");
			downLoadArtifactFile(fileName, filePath);
			return true;
		}
		return false;
	}

	private void downLoadArtifactFile(String fileName, String filePath) {
		String artifactsDownloadFolder = Utilities.getInstance().getArtifactsDownloadFolder();
		String artifactFilePath = artifactsDownloadFolder + File.separator + Utilities.getInstance().getUniqueUUID("")
				+ fileName + ".zip";
		try {
			// connectionTimeout, readTimeout = 10 seconds
			downloadUsingStream(filePath, artifactFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
