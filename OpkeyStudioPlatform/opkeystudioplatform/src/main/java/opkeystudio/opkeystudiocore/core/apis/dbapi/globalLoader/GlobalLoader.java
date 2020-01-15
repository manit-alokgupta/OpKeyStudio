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

	public void initAllArguments() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				globalLoader.initAllFlowInputArguments();
				globalLoader.initAllFlowOutputArguments();
				globalLoader.initAllComponentFlowInputArguments();
				globalLoader.initAllComponentFlowOutputArguments();
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void initAllFlowOutputArguments() {
		String query = "SELECT * FROM flow_step_output_arguments";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		List<FlowOutputArgument> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setFlowOutputArguments(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllComponentFlowInputArguments() {
		String query = "SELECT * FROM component_step_input_args";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		List<FlowInputArgument> flowInputArgs;
		try {
			flowInputArgs = mapper.readValue(result, type);
			setComponentflowInputArguments(flowInputArgs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllComponentFlowOutputArguments() {
		String query = "SELECT * FROM component_step_output_arguments";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		List<FlowOutputArgument> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setComponentflowOutputArguments(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
