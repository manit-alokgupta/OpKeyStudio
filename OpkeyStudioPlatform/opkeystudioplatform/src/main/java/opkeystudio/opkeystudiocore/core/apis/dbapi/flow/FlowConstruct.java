package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class FlowConstruct {
	public void saveAllFlowSteps(List<FlowStep> allFlowSteps) {
		for (FlowStep flowStep : allFlowSteps) {
			saveFlowInputArguments(flowStep.getFlowInputArgs());
			saveFlowOutputArguments(flowStep.getFlowOutputArgs());
			saveFlowStep(flowStep);
		}
	}

	private void saveFlowStep(FlowStep flowStep) {
		deleteFlowStep(flowStep);
		updateFlowStep(flowStep);
		addFlowStep(flowStep);
	}

	private void saveFlowInputArguments(List<FlowInputArgument> flowInputArguments) {
		for (FlowInputArgument flowInputArgument : flowInputArguments) {
			deleteFlowInputArgument(flowInputArgument);
			updateFlowInputArgument(flowInputArgument);
			addFlowInputArgument(flowInputArgument);
		}
	}

	private void saveFlowOutputArguments(List<FlowOutputArgument> flowOutputArguments) {
		for (FlowOutputArgument flowOutputArgument : flowOutputArguments) {
			deleteFlowOutputArgument(flowOutputArgument);
			updateFlowOutputArgument(flowOutputArgument);
			addFlowOutputArgument(flowOutputArgument);
		}
	}

	private void deleteFlowStep(FlowStep flowStep) {

	}

	private void updateFlowStep(FlowStep flowStep) {

	}

	private void addFlowStep(FlowStep flowStep) {

	}

	private void deleteFlowInputArgument(FlowInputArgument flowInputArgument) {

	}

	private void updateFlowInputArgument(FlowInputArgument flowInputArgument) {

	}

	private void addFlowInputArgument(FlowInputArgument flowInputArgument) {

	}

	private void deleteFlowOutputArgument(FlowOutputArgument flowOutputArgument) {

	}

	private void updateFlowOutputArgument(FlowOutputArgument flowOutputArgument) {

	}

	private void addFlowOutputArgument(FlowOutputArgument flowOutputArgument) {

	}
}
