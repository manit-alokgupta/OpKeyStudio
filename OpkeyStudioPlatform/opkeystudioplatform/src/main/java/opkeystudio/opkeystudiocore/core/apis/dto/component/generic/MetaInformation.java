package opkeystudio.opkeystudiocore.core.apis.dto.component.generic;

public class MetaInformation {
	private String descriptionRTF;
	private String expectedResult;
	private boolean isShared;
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
