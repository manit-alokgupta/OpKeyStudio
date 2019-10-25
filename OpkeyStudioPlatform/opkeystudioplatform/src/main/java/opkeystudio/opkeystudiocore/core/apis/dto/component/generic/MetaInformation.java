package opkeystudio.opkeystudiocore.core.apis.dto.component.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaInformation {
	
	@JsonProperty("DescriptionRTF")
	private String descriptionRTF;
	
	@JsonProperty("ExpectedResult")
	private String expectedResult;
	
	@JsonProperty("IsShared")
	private boolean isShared;
	
	@JsonProperty("IsAutoCreated")
	private boolean isAutoCreated;

	public String getDescriptionRTF() {
		return descriptionRTF;
	}

	public void setDescriptionRTF(String descriptionRTF) {
		this.descriptionRTF = descriptionRTF;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public boolean isShared() {
		return isShared;
	}

	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

	public boolean isAutoCreated() {
		return isAutoCreated;
	}

	public void setAutoCreated(boolean isAutoCreated) {
		this.isAutoCreated = isAutoCreated;
	}
}
