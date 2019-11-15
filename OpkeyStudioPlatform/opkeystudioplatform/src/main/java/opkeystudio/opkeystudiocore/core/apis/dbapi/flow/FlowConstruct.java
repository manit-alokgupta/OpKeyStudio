package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;

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
		if (flowStep.isDeleted()) {

		}
	}

	private void updateFlowStep(FlowStep flowStep) {
		if (flowStep.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowStep, "", "");
		}
	}

	private void addFlowStep(FlowStep flowStep) {
		if (flowStep.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowStep, "", "");
		}
	}

	private void deleteFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isDeleted()) {

		}
	}

	private void updateFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowInputArgument, "", "");
		}
	}

	private void addFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowInputArgument, "", "");
		}
	}

	private void deleteFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isDeleted()) {

		}
	}

	private void updateFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowOutputArgument, "", "");
		}
	}

	private void addFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowOutputArgument, "", "");
		}
	}
}
