package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;

public class ArtifactExporting {
	public void getFolderDetails(String folderId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeID", folderId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/GetFolderDetails", "GET",
				params, null, null);
	}

	public void checkForSecuredData(String nodeid,String portalType) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("strNodeIDs", nodeid);
		params.put("portalType", portalType);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/CheckForSecuredData", "GET",
				params, null, null);
	}

	public void getSecureDataStatus(String sessionId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sessionId", sessionId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/GetSecuredDataStatus", "GET",
				params, null, null);
	}

	public void exportArtificate(String strNodeIDs, String jobType, String sessionID, String SourceLabelID, String SourceProjectPasword) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("strNodeIDs", strNodeIDs);
		params.put("jobType", jobType);
		params.put("sessionID", sessionID);
		params.put("SourceLabelID", SourceLabelID);
		params.put("SourceProjectPasword", SourceProjectPasword);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/ExportArtifact", "GET",
				params, null, null);
	}

	public void getArtifactActionLogs(String sessionId, String jobType, String processtype) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sessionId", sessionId);
		params.put("jobType", jobType);
		params.put("processtype", processtype);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/GetArtifactActionLogs", "GET",
				params, null, null);
	}

	public void downloadExportedZip(String sessionId) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sessionId", sessionId);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/DownloadArtifactStuff", "GET",
				params, null, null);
	}
}
