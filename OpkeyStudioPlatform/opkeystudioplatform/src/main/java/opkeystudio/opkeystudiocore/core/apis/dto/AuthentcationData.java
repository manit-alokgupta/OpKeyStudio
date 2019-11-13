package opkeystudio.opkeystudiocore.core.apis.dto;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class AuthentcationData {
	@DBField
	private boolean status;
	@DBField
	private String message;
	@DBField
	private String sessionId;
	@DBField
	private String authenticationToken;

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
