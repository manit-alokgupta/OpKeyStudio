package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowApi {
	private List<FlowInputArgument> flowInputArguments;
	private List<FlowOutputArgument> flowOutputArguments;
	private static FlowApi flowApi;

	public static FlowApi getInstance() {
		if (flowApi == null) {
			flowApi = new FlowApi();
		}
		return flowApi;
	}

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

	public void initAllFlowInputArguments() throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = "SELECT * FROM flow_step_input_arguments";
		String result = sqlComm.executeQueryString(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		sqlComm.disconnect();
		List<FlowInputArgument> flowInputArgs = mapper.readValue(result, type);
		setFlowInputArguments(flowInputArgs);
	}

	public void initAllFlowOutputArguments()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = "SELECT * FROM flow_step_output_arguments";
		String result = sqlComm.executeQueryString(query);
		System.out.println(result);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		sqlComm.disconnect();
		List<FlowOutputArgument> outputArguments = mapper.readValue(result, type);
		setFlowOutputArguments(outputArguments);
	}

	private List<FlowInputArgument> getFlowStepInputArguments(FlowStep flowStep)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
		List<FlowInputArgument> inputArguments = getFlowInputArguments();
		for (FlowInputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getFlow_stepid().equals(flowStep.getFlow_stepid())) {
				flowInputArgs.add(flowInputArgument);
			}
		}
		return flowInputArgs;
	}

	private List<FlowOutputArgument> getFlowStepOutputArguments(FlowStep flowStep)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<FlowOutputArgument> flowInputArgs = new ArrayList<FlowOutputArgument>();
		List<FlowOutputArgument> inputArguments = getFlowOutputArguments();
		for (FlowOutputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getFlow_stepid().equals(flowStep.getFlow_stepid())) {
				flowInputArgs.add(flowInputArgument);
			}
		}
		return flowInputArgs;
	}

	public List<FlowStep> getAllFlowSteps(String flowId)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		List<FlowStep> flowSteps = getAllSteps(flowId);
		for (FlowStep flowStep : flowSteps) {
			Keyword keyword = KeywordManager.getInstance().getKeyword(flowStep.getKeywordid());
			List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
			List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
			flowStep.setKeyword(keyword);
			flowStep.setFlowInputArgs(fis);
			flowStep.setFlowOutputArgs(fos);
		}
		return flowSteps;
	}

	public List<FlowInputArgument> getFlowInputArguments() {
		return flowInputArguments;
	}

	private void setFlowInputArguments(List<FlowInputArgument> flowInputArguments) {
		this.flowInputArguments = flowInputArguments;
	}

	public List<FlowOutputArgument> getFlowOutputArguments() {
		return flowOutputArguments;
	}

	private void setFlowOutputArguments(List<FlowOutputArgument> flowOutputArguments) {
		this.flowOutputArguments = flowOutputArguments;
	}
}
