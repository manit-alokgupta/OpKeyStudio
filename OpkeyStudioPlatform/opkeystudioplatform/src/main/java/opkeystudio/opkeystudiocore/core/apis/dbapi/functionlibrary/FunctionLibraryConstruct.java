package opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentStepInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentStepOutputArgument;
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

	private void saveComponentInputArguments(List<ComponentStepInputArgument> flowInputArguments) {
		for (ComponentStepInputArgument flowInputArgument : flowInputArguments) {
			deleteComponentInputArgument(flowInputArgument);
			updateComponentInputArgument(flowInputArgument);
			addComponentInputArgument(flowInputArgument);
		}
	}

	private void saveComponentOutputArguments(List<ComponentStepOutputArgument> flowOutputArguments) {
		for (ComponentStepOutputArgument flowOutputArgument : flowOutputArguments) {
			deleteComponentOutputArgument(flowOutputArgument);
			updateComponentOutputArgument(flowOutputArgument);
			addComponentOutputArgument(flowOutputArgument);
		}
	}

	private void deleteComponentStep(FlowStep flowStep) {
		if (flowStep.isDeleted()) {
			String query = String.format("delete from component_design_steps where stepid ='%s'", flowStep.getStepid());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentStep(FlowStep flowStep) {
		System.out.println(" 1: Saving Flow Step " + flowStep.getStepid());
		if (flowStep.isModified()) {
			System.out.println(" 2: Saving Flow Step " + flowStep.getStepid());
			String query = new QueryMaker().createUpdateQuery(flowStep, "component_design_steps",
					String.format("WHERE stepid ='%s'", flowStep.getStepid()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentStep(FlowStep flowStep) {
		if (flowStep.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowStep, "component_design_steps", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteComponentInputArgument(ComponentStepInputArgument flowInputArgument) {
		if (flowInputArgument.isDeleted()) {
			String query = String.format("delete from component_step_input_args where step_arg_id ='%s'",
					flowInputArgument.getStep_arg_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentInputArgument(ComponentStepInputArgument flowInputArgument) {
		if (flowInputArgument.isModified()) {
			System.out.println("Flow Input Argument Modified "+flowInputArgument.getStep_arg_id());
			String query = new QueryMaker().createUpdateQuery(flowInputArgument, "component_step_input_args",
					String.format("WHERE step_arg_id ='%s'", flowInputArgument.getStep_arg_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentInputArgument(ComponentStepInputArgument flowInputArgument) {
		if (flowInputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowInputArgument, "component_step_input_args", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteComponentOutputArgument(ComponentStepOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isDeleted()) {
			String query = String.format("delete from component_step_output_arguments where component_step_oa_id ='%s'",
					flowOutputArgument.getComponentstep_oa_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentOutputArgument(ComponentStepOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowOutputArgument, "component_step_output_arguments",
					String.format("WHERE component_step_oa_id ='%s'", flowOutputArgument.getComponentstep_oa_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentOutputArgument(ComponentStepOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowOutputArgument, "component_step_output_arguments",
					"");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}
}
