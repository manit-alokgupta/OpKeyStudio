package opkeystudio.opkeystudiocore.core.apis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import opkeystudio.opkeystudiocore.core.apis.dao.AuthentcationData;
import opkeystudio.opkeystudiocore.core.communicator.OpKeyApiCommunicator;

public class Authenticate {
	public AuthentcationData loginToOpKey(String username, String password) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("loginsource", "OPKEYECLIPSE");
			String retdata = new OpKeyApiCommunicator().sendDataToOpKeyServer("/api/OpKeyAuth/Login", "POST", params);
			ObjectMapper mapper=new ObjectMapper();
			return (AuthentcationData)mapper.readValue(retdata, AuthentcationData.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		AuthentcationData adata= new Authenticate().loginToOpKey("admin", "admin");
		System.out.println(adata.getAuthenticationToken());
	}
}
