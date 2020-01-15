package opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader;

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
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalLoader {
	private static GlobalLoader globalLoader;
	private List<FlowInputArgument> flowInputArguments = new ArrayList<FlowInputArgument>();
	private List<FlowOutputArgument> flowOutputArguments = new ArrayList<FlowOutputArgument>();;

	private List<FlowInputArgument> componentflowInputArguments = new ArrayList<>();
	private List<FlowOutputArgument> componentflowOutputArguments = new ArrayList<>();

	private List<ORObject> allORObjects = new ArrayList<ORObject>();

	public static GlobalLoader getInstance() {
		if (globalLoader == null) {
			globalLoader = new GlobalLoader();
		}
		return globalLoader;
	}

	public void initAllFlowInputArguments() {
		String query = "SELECT * FROM flow_step_input_arguments";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
		try {
			flowInputArgs = mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFlowInputArguments(flowInputArgs);
	}

	public void initAllFlowOutputArguments()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = "SELECT * FROM flow_step_output_arguments";
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		sqlComm.disconnect();
		List<FlowOutputArgument> outputArguments = mapper.readValue(result, type);
		setFlowOutputArguments(outputArguments);
	}

	public void initAllComponentFlowInputArguments()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = "SELECT * FROM component_step_input_args";
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		sqlComm.disconnect();
		List<FlowInputArgument> flowInputArgs = mapper.readValue(result, type);
		setComponentflowInputArguments(flowInputArgs);
	}

	public void initComponentAllFlowOutputArguments()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = "SELECT * FROM component_step_output_arguments";
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		sqlComm.disconnect();
		List<FlowOutputArgument> outputArguments = mapper.readValue(result, type);
		setComponentflowOutputArguments(outputArguments);
	}

	public List<FlowInputArgument> getFlowInputArguments() {
		return flowInputArguments;
	}

	public void setFlowInputArguments(List<FlowInputArgument> flowInputArguments) {
		this.flowInputArguments = flowInputArguments;
	}

	public List<FlowOutputArgument> getFlowOutputArguments() {
		return flowOutputArguments;
	}

	public void setFlowOutputArguments(List<FlowOutputArgument> flowOutputArguments) {
		this.flowOutputArguments = flowOutputArguments;
	}

	public List<FlowInputArgument> getComponentflowInputArguments() {
		return componentflowInputArguments;
	}

	public void setComponentflowInputArguments(List<FlowInputArgument> componentflowInputArguments) {
		this.componentflowInputArguments = componentflowInputArguments;
	}

	public List<FlowOutputArgument> getComponentflowOutputArguments() {
		return componentflowOutputArguments;
	}

	public void setComponentflowOutputArguments(List<FlowOutputArgument> componentflowOutputArguments) {
		this.componentflowOutputArguments = componentflowOutputArguments;
	}
}
