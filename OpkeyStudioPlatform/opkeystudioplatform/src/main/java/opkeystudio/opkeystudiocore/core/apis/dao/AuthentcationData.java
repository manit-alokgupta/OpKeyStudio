package opkeystudio.opkeystudiocore.core.apis.dao;

public class AuthentcationData {
	private boolean status;
	private String message;
	private String sessionId;
	private String authenticationToken;

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	private void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	private void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMessage() {
		return message;
	}

	private void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	private void setStatus(boolean status) {
		this.status = status;
	}
}
