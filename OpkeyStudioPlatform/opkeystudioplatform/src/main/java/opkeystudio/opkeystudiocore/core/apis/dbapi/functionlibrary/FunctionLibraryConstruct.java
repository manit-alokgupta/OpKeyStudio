package opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;

public class FunctionLibraryConstruct {

	public void saveAllComponentSteps(List<FlowStep> allFlowSteps) {
		for (FlowStep flowStep : allFlowSteps) {
			saveComponentInputArguments(flowStep.getComponentInputArgs());
			saveComponentOutputArguments(flowStep.getComponentOutputArgs());
			saveComponentStep(flowStep);
		}
	}

	private void saveComponentStep(FlowStep flowStep) {
		deleteComponentStep(flowStep);
		updateComponentStep(flowStep);
		addComponentStep(flowStep);
	}

	private void saveComponentInputArguments(List<ComponentInputArgument> flowInputArguments) {
		for (ComponentInputArgument flowInputArgument : flowInputArguments) {
			deleteComponentInputArgument(flowInputArgument);
			updateComponentInputArgument(flowInputArgument);
			addComponentInputArgument(flowInputArgument);
		}
	}

	private void saveComponentOutputArguments(List<ComponentOutputArgument> flowOutputArguments) {
		for (ComponentOutputArgument flowOutputArgument : flowOutputArguments) {
			deleteComponentOutputArgument(flowOutputArgument);
			updateComponentOutputArgument(flowOutputArgument);
			addComponentOutputArgument(flowOutputArgument);
		}
	}

	private void deleteComponentStep(FlowStep flowStep) {
		if (flowStep.isDeleted()) {
			String query = String.format("delete from component_design_steps where flow_stepid ='%s'",
					flowStep.getFlow_stepid());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentStep(FlowStep flowStep) {
		if (flowStep.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowStep, "component_design_steps",
					String.format("WHERE flow_stepid ='%s'", flowStep.getFlow_stepid()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentStep(FlowStep flowStep) {
		if (flowStep.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowStep, "component_design_steps", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteComponentInputArgument(ComponentInputArgument flowInputArgument) {
		if (flowInputArgument.isDeleted()) {
			String query = String.format("delete from component_step_input_args where step_arg_id ='%s'",
					flowInputArgument.getStep_arg_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentInputArgument(ComponentInputArgument flowInputArgument) {
		if (flowInputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowInputArgument, "component_step_input_args",
					String.format("WHERE step_arg_id ='%s'", flowInputArgument.getStep_arg_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentInputArgument(ComponentInputArgument flowInputArgument) {
		if (flowInputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowInputArgument, "component_step_input_args", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteComponentOutputArgument(ComponentOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isDeleted()) {
			String query = String.format("delete from component_step_output_arguments where component_step_oa_id ='%s'",
					flowOutputArgument.getComponentstep_oa_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentOutputArgument(ComponentOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowOutputArgument, "component_step_output_arguments",
					String.format("WHERE component_step_oa_id ='%s'", flowOutputArgument.getComponentstep_oa_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentOutputArgument(ComponentOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowOutputArgument, "component_step_output_arguments",
					"");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}
}
