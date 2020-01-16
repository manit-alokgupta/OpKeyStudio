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
			saveFlowInputArguments(flowStep.getFlowInputArgs());
			saveFlowOutputArguments(flowStep.getFlowOutputArgs());
			if (flowStep.getFunctionLibraryComponent() != null) {
				saveComponentInputArguments(flowStep.getFunctionLibraryComponent().getComponentInputArguments());
				saveComponentOutputArguments(flowStep.getFunctionLibraryComponent().getComponentOutputArguments());
			}
			saveComponentStep(flowStep);
		}
	}

	private void saveComponentStep(FlowStep flowStep) {

		deleteComponentStep(flowStep);
		addComponentStep(flowStep);
		updateComponentStep(flowStep);
	}

	public void saveComponentInputArguments(List<ComponentInputArgument> flowInputArguments) {
		for (ComponentInputArgument flowInputArgument : flowInputArguments) {
			deleteComponentInputArgument(flowInputArgument);
			addComponentInputArgument(flowInputArgument);
			updateComponentInputArgument(flowInputArgument);
		}
	}

	private void saveFlowInputArguments(List<FlowInputArgument> flowInputArguments) {
		for (FlowInputArgument flowInputArgument : flowInputArguments) {
			deleteFlowInputArgument(flowInputArgument);
			addFlowInputArgument(flowInputArgument);
			updateFlowInputArgument(flowInputArgument);
		}
	}

	public void saveComponentOutputArguments(List<ComponentOutputArgument> flowOutputArguments) {
		for (ComponentOutputArgument flowOutputArgument : flowOutputArguments) {
			deleteComponentOutputArgument(flowOutputArgument);
			addComponentOutputArgument(flowOutputArgument);
			updateComponentOutputArgument(flowOutputArgument);
		}
	}

	private void saveFlowOutputArguments(List<FlowOutputArgument> flowOutputArguments) {
		for (FlowOutputArgument flowOutputArgument : flowOutputArguments) {
			deleteFlowOutputArgument(flowOutputArgument);
			addFlowOutputArgument(flowOutputArgument);
			updateFlowOutputArgument(flowOutputArgument);
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
			String query = new QueryMaker().createInsertQuery(flowStep, "component_design_steps", "", "flow_id",
					"flow_stepid");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isDeleted()) {
			String query = String.format("delete from component_step_input_args where step_arg_id ='%s'",
					flowInputArgument.getStep_arg_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isModified()) {
			System.out.println("Flow Input Argument Modified " + flowInputArgument.getStep_arg_id());
			String query = new QueryMaker().createUpdateQuery(flowInputArgument, "component_step_input_args",
					String.format("WHERE step_arg_id ='%s'", flowInputArgument.getStep_arg_id()), "flow_step_ia_id",
					"keyword_ip_id", "datasource");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addFlowInputArgument(FlowInputArgument flowInputArgument) {
		if (flowInputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowInputArgument, "component_step_input_args", "",
					"flow_step_ia_id", "keyword_ip_id", "datasource");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteComponentInputArgument(ComponentInputArgument flowInputArgument) {
		if (flowInputArgument.isDeleted()) {
			String query = String.format("delete from component_input_parameters where ip_id ='%s'",
					flowInputArgument.getIp_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentInputArgument(ComponentInputArgument flowInputArgument) {
		if (flowInputArgument.isModified()) {
			System.out.println("Flow Input Argument Modified " + flowInputArgument.getStep_arg_id());
			String query = new QueryMaker().createUpdateQuery(flowInputArgument, "component_input_parameters",
					String.format("WHERE ip_id ='%s'", flowInputArgument.getIp_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentInputArgument(ComponentInputArgument flowInputArgument) {
		if (flowInputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowInputArgument, "component_input_parameters", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isDeleted()) {
			String query = String.format("delete from component_step_output_arguments where component_step_oa_id ='%s'",
					flowOutputArgument.getFlow_step_oa_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowOutputArgument, "component_step_output_arguments",
					String.format("WHERE component_step_oa_id ='%s'", flowOutputArgument.getFlow_step_oa_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addFlowOutputArgument(FlowOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowOutputArgument, "component_step_output_arguments",
					"");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteComponentOutputArgument(ComponentOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isDeleted()) {
			String query = String.format("delete from component_output_parameters where op_id ='%s'",
					flowOutputArgument.getOp_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateComponentOutputArgument(ComponentOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isModified()) {
			String query = new QueryMaker().createUpdateQuery(flowOutputArgument, "component_output_parameters",
					String.format("WHERE op_id ='%s'", flowOutputArgument.getOp_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addComponentOutputArgument(ComponentOutputArgument flowOutputArgument) {
		if (flowOutputArgument.isAdded()) {
			String query = new QueryMaker().createInsertQuery(flowOutputArgument, "component_output_parameters", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}
}
