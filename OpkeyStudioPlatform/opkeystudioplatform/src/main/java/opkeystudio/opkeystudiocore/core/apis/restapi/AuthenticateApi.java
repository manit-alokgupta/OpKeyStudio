package opkeystudio.opkeystudiocore.core.apis.restapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import opkeystudio.opkeystudiocore.core.apis.dto.AuthentcationData;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;
import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class AuthenticateApi {
	public AuthentcationData loginToOpKey(String username, String password) throws IOException {
		try {
			ServiceRepository.getInstance().setOpKeyHostAuthToken(null);
			ServiceRepository.getInstance().setOpKeyHostSessionId(null);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("loginsource", "OPKEYECLIPSE");
			String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/Login", "POST", params,
					username, password);
			ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
			AuthentcationData adata = (AuthentcationData) mapper.readValue(retdata, AuthentcationData.class);
			ServiceRepository.getInstance().setOpKeyHostAuthToken(adata.getAuthenticationToken());
			ServiceRepository.getInstance().setOpKeyHostSessionId(adata.getSessionId());
			return adata;
		} catch (Exception e) {
		}
		return new AuthentcationData();
	}

	public String logout() throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/Logout", "POST", params, null,
				null);
		return retdata;
	}
}
