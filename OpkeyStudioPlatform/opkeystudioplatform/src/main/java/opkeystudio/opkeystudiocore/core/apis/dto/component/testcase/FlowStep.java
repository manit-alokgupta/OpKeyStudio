package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlowStep {
	@JsonProperty("ContinueOnError")
	private boolean continueOnError;
	
	@JsonProperty("")
	private boolean isNegative;
	
	@JsonProperty("")
	private boolean shouldRun;
	
	@JsonProperty("")
	private boolean wantSnapshot;
	
	@JsonProperty("")
	private String comment;
	
	@JsonProperty("")
	private String keyword_RelativePath;
	
	@JsonProperty("")
	private StepInputArgs stepInputArgs;
	
	@JsonProperty("")
	private StepOutputArgs stepOutputArgs;

	public boolean isContinueOnError() {
		return continueOnError;
	}

	public void setContinueOnError(boolean continueOnError) {
		this.continueOnError = continueOnError;
	}

	public boolean isNegative() {
		return isNegative;
	}

	public void setNegative(boolean isNegative) {
		this.isNegative = isNegative;
	}

	public boolean isShouldRun() {
		return shouldRun;
	}

	public void setShouldRun(boolean shouldRun) {
		this.shouldRun = shouldRun;
	}

	public boolean isWantSnapshot() {
		return wantSnapshot;
	}

	public void setWantSnapshot(boolean wantSnapshot) {
		this.wantSnapshot = wantSnapshot;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getKeyword_RelativePath() {
		return keyword_RelativePath;
	}

	public void setKeyword_RelativePath(String keyword_RelativePath) {
		this.keyword_RelativePath = keyword_RelativePath;
	}

	public StepInputArgs getStepInputArgs() {
		return stepInputArgs;
	}

	public void setStepInputArgs(StepInputArgs stepInputArgs) {
		this.stepInputArgs = stepInputArgs;
	}

	public StepOutputArgs getStepOutputArgs() {
		return stepOutputArgs;
	}

	public void setStepOutputArgs(StepOutputArgs stepOutputArgs) {
		this.stepOutputArgs = stepOutputArgs;
	}
}
