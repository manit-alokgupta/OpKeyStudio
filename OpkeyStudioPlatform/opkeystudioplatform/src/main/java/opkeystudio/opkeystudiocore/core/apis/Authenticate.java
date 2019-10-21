package opkeystudio.opkeystudiocore.core.apis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import opkeystudio.opkeystudiocore.core.apis.dao.AuthentcationData;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class Authenticate {
	public AuthentcationData loginToOpKey(String username, String password) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("loginsource", "OPKEYECLIPSE");
			String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/Login", "POST", params,
					username, password);
			ObjectMapper mapper = new ObjectMapper();
			AuthentcationData adata = (AuthentcationData) mapper.readValue(retdata, AuthentcationData.class);
			ServiceRepository.getInstance().setOpKeyHostAuthToken(adata.getAuthenticationToken());
			ServiceRepository.getInstance().setOpKeyHostSessionId(adata.getSessionId());
			return adata;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void getAssignedProjects() throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/GetListOfAssignedProject",
				"GET", params, null, null);

		System.out.println(retdata);
	}

	public static void main(String[] args) throws IOException {
		Authenticate auth = new Authenticate();
		auth.loginToOpKey("admin", "admin");
		auth.getAssignedProjects();
	}
}
