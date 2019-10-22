package opkeystudio.opkeystudiocore.core.apis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dao.ArtificateTreeNode;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtificateTreeApi {
	public List<ArtificateTreeNode> getArtificateNodes(String parentID) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("moduleType", "AllUnified");
		params.put("parentID", parentID);
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyStudio/GetJSTreeNodesLazy", "GET",
				params, null, null);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ArtificateTreeNode.class);
		return mapper.readValue(retdata, type);
	}

	public List<ArtificateTreeNode> getRootArtificateFolder() throws IOException {
		return getArtificateNodes("00000000-0000-0000-0000-000000000000");
	}
}
