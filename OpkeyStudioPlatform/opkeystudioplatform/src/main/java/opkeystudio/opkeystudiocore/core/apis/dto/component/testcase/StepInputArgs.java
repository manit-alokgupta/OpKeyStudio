package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.ArrayList;
import java.util.List;

public class StepInputArgs {
	private List<FlowStepInputArgument> FlowStepInputArgument = new ArrayList<>();

	public List<FlowStepInputArgument> getFlowStepInputArgument() {
		return FlowStepInputArgument;
	}

	public void setFlowStepInputArgument(List<FlowStepInputArgument> flowStepInputArgument) {
		FlowStepInputArgument = flowStepInputArgument;
	}
}
