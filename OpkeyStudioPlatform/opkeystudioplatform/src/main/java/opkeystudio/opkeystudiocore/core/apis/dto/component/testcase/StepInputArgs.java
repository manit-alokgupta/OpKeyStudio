package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StepInputArgs {
	
	@JsonProperty("FlowStepInputArgument")
	private List<FlowStepInputArgument> flowStepInputArgument = new ArrayList<>();

	public List<FlowStepInputArgument> getFlowStepInputArgument() {
		return flowStepInputArgument;
	}

	public void setFlowStepInputArgument(List<FlowStepInputArgument> flowStepInputArgument) {
		this.flowStepInputArgument = flowStepInputArgument;
	}
}
