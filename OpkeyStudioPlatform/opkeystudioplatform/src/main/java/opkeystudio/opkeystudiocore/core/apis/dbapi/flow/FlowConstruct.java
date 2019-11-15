package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class FlowConstruct {
	public void saveAllFlowSteps(List<FlowStep> allFlowSteps) {
		for (FlowStep flowStep : allFlowSteps) {
			saveFlowInputArguments(flowStep.getFlowInputArgs());
			saveFlowOutputArguments(flowStep.getFlowOutputArgs());
			saveORObjects(flowStep.getOrObject());
			saveFlowStep(flowStep);
		}
	}

	private void saveFlowStep(FlowStep flowStep) {

	}

	private void saveFlowInputArguments(List<FlowInputArgument> flowInputArguments) {

	}

	private void saveFlowOutputArguments(List<FlowOutputArgument> flowOutputAarguments) {

	}

	private void saveORObjects(List<ORObject> orbjects) {

	}
}
