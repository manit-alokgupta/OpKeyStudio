package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import opkeystudio.opkeystudiocore.core.apis.dto.ArtifactTreeNode;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;

public class ArtifactExporting {
	@SuppressWarnings("unused")
	public String getFolderDetails(String folderId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeID", folderId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/GetFolderDetails", "GET",
				params, null, null);
		return retdata;
	}

	@SuppressWarnings("unused")
	public String checkForSecuredData(String nodeid, String portalType) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("strNodeIDs", nodeid);
		params.put("portalType", portalType);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/ExportImportAPI/CheckForSecuredData", "GET",
				params, null, null);
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
	
	public boolean exportArtifactFromOpKey(ArtifactTreeNode artifactTreeNode) throws IOException
	{
		System.out.println("Selected Artifact " + artifactTreeNode.getText());
		String folderDetails = new ArtifactExporting().getFolderDetails(artifactTreeNode.getId());
		if (folderDetails.equals("true")) {
			String nodeIds = "['" + artifactTreeNode.getId() + "']";
			String sessionData = new ArtifactExporting().checkForSecuredData(nodeIds, "Opkey");
			sessionData = sessionData.replaceAll("\"", "");
			System.out.println("Secured Data " + sessionData);

			String securedDataStatus = new ArtifactExporting().getSecureDataStatus(sessionData);
			System.out.println("Secured Data Status " + securedDataStatus);
			String exportArtifactId = new ArtifactExporting().exportArtificate(nodeIds, "Opkey", sessionData,
					"00000000-0000-0000-0000-000000000000", "");

			System.out.println("Export Artifact Data " + exportArtifactId);
			String downlodedData = new ArtifactExporting().downloadExportedZip(sessionData);
			System.out.println("Downloaded DATA "+downlodedData);
			return true;
		}
		return false;
	}
	
	
}
