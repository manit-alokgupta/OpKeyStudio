package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryConstruct;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;

public class FlowConstruct {
	public void saveAllFlowSteps(Artifact artifact, List<FlowStep> allFlowSteps) {
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			new FunctionLibraryConstruct().saveAllComponentSteps(allFlowSteps);
			return;
		}
		
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
			String query = String.format("delete from flow_design_steps where flow_stepid='%s'",
					flowStep.getFlow_stepid());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateFlowStep(FlowStep flowStep) {
		if (flowStep.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowStep, "flow_design_steps",
					String.format("WHERE flow_stepid='%s'", flowStep.getFlow_stepid()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addFlowStep(FlowStep flowStep) {
		if (flowStep.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowStep, "flow_design_steps", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isDeleted()) {
			String query = String.format("delete from flow_step_input_arguments where flow_step_ia_id ='%s'",
					flowInputArgument.getFlow_step_ia_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowInputArgument, "flow_step_input_arguments",
					String.format("WHERE flow_step_ia_id='%s'", flowInputArgument.getFlow_step_ia_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowInputArgument, "flow_step_input_arguments", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isDeleted()) {
			String query = String.format("delete from flow_step_output_arguments where flow_step_oa_id ='%s'",
					flowOutputArgument.getFlow_step_oa_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowOutputArgument, "flow_step_output_arguments",
					String.format("WHERE flow_step_oa_id='%s'", flowOutputArgument.getFlow_step_oa_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowOutputArgument, "flow_step_output_arguments", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}
}
