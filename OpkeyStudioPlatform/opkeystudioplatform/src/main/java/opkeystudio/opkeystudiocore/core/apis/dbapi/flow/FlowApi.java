package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArguments;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowApi {
	private List<FlowStep> getAllSteps(String flowId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("SELECT * FROM flow_design_steps where flow_id='%s' ORDER BY position asc",
				flowId);
		String result = sqlComm.executeQueryString(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	private List<FlowInputArguments> getFlowStepInputArguments(FlowStep flowStep)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("SELECT * FROM flow_step_input_arguments where flow_stepid='%s'",
				flowStep.getFlow_stepid());
		String result = sqlComm.executeQueryString(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArguments.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	private List<FlowOutputArgument> getFlowStepOutputArguments(FlowStep flowStep)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("SELECT * FROM flow_step_output_arguments where flow_stepid='%s'",
				flowStep.getFlow_stepid());
		String result = sqlComm.executeQueryString(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public List<FlowStep> getAllFlowSteps(String flowId)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		List<FlowStep> flowSteps = getAllSteps("38f96dfe-9561-47f5-b4a7-8ebf2421148a");
		for (FlowStep flowStep : flowSteps) {
			List<FlowInputArguments> fis = getFlowStepInputArguments(flowStep);
			List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
			flowStep.setFlowInputArgs(fis);
			flowStep.setFlowOutputArgs(fos);
		}
		return flowSteps;
	}

	public static void main(String[] args) throws SQLException, JsonParseException, JsonMappingException, IOException {
	}
}
