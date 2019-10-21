package opkeystudio.opkeystudiocore.core.communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;
import java.util.Set;

import opkeystudio.opkeystudiocore.core.repositories.repository.ServiceRepository;

public class OpKeyApiCommunicator {
	String hostUrl;

	public OpKeyApiCommunicator() {
		ServiceRepository.getInstance().setOpKeyHostUrl("http://localhost:61527");
		this.hostUrl = ServiceRepository.getInstance().getOpKeyHostUrl();
	}

	private String convertMapToQueryParams(Map<String, Object> params) {
		Set<String> keys = params.keySet();
		String queryString = "";
		for (String key : keys) {
			if (!queryString.isEmpty()) {
				queryString += "&";
			}
			String value = (String) params.get(key);
			String queryData = key + "=" + value;
			queryString += queryData;
		}
		System.out.println(queryString);
		return queryString;
	}

	public String sendDataToOpKeyServer(String apiurl, String requestMethod, Map<String, Object> params,
			String username, String password) throws IOException {
		if (requestMethod.toUpperCase().equals("POST")) {
			apiurl = apiurl + "?" + convertMapToQueryParams(params);
		}
		URL obj = new URL(this.hostUrl + apiurl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestMethod(requestMethod.toUpperCase());

		if (username != null && password != null) {
			String userpassword = username + ":" + password;
			String encodedAuthorization = Base64.getEncoder().encodeToString(userpassword.getBytes());
			con.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
		}
		
		if (ServiceRepository.getInstance().getOpKeyHostAuthToken() != null) {
			System.out.println(ServiceRepository.getInstance().getOpKeyHostAuthToken());
			con.setRequestProperty("Authorization",
					"Bearer " + ServiceRepository.getInstance().getOpKeyHostAuthToken());
		}
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		
		//String value=con.getHeaderField("Authorization");
		//System.out.println(value);
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		if (requestMethod.toUpperCase().equals("POST")) {
			con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
			con.getOutputStream().write(postDataBytes);
		}

		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
		con.disconnect();
		return response.toString();
	}

}
