package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.ArrayList;
import java.util.List;

public class FlowSteps {
	private List<FlowStep> flowSteps = new ArrayList<>();

	public List<FlowStep> getFlowSteps() {
		return flowSteps;
	}

	public void setFlowSteps(List<FlowStep> flowSteps) {
		this.flowSteps = flowSteps;
	}

	public void addFlowStep(FlowStep flowStep) {
		this.flowSteps.add(flowStep);
	}
}
