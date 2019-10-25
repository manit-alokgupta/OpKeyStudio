package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlowStep {
	@JsonProperty("ContinueOnError")
	private boolean continueOnError;
	
	@JsonProperty("IsNegative")
	private boolean isNegative;
	
	@JsonProperty("ShouldRun")
	private boolean shouldRun;
	
	@JsonProperty("WantSnapshot")
	private boolean wantSnapshot;
	
	@JsonProperty("Comment")
	private String comment;
	
	@JsonProperty("Keyword_RelativePath")
	private String keyword_RelativePath;
	
	@JsonProperty("StepInputArgs")
	private List<FlowStepInputArgument> stepInputArgs;
	
	@JsonProperty("StepOutputArgs")
	private List<FlowStepOutputArgument> stepOutputArgs;

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

	public List<FlowStepInputArgument> getStepInputArgs() {
		return stepInputArgs;
	}

	public void setStepInputArgs(List<FlowStepInputArgument> stepInputArgs) {
		this.stepInputArgs = stepInputArgs;
	}

	public List<FlowStepOutputArgument> getStepOutputArgs() {
		return stepOutputArgs;
	}

	public void setStepOutputArgs(List<FlowStepOutputArgument> stepOutputArgs) {
		this.stepOutputArgs = stepOutputArgs;
	}
}
