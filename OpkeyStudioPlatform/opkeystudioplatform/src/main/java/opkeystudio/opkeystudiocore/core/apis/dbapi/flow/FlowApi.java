package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.keywordmanager.KeywordManager;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowApi {

	private static FlowApi flowApi;

	public static FlowApi getInstance() {
		if (flowApi == null) {
			flowApi = new FlowApi();
		}
		return flowApi;
	}

	private List<FlowStep> getAllSteps(String flowId) {
		String query = String.format("SELECT * FROM flow_design_steps where flow_id='%s' ORDER BY position asc",
				flowId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowStep>();
	}

	public List<FlowInputArgument> getFlowStepInputArguments(FlowStep flowStep)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
		List<FlowInputArgument> inputArguments = GlobalLoader.getInstance().getFlowInputArguments();
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
		List<FlowOutputArgument> inputArguments = GlobalLoader.getInstance().getFlowOutputArguments();
		for (FlowOutputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getFlow_stepid().equals(flowStep.getFlow_stepid())) {
				flowInputArgs.add(flowInputArgument);
			}
		}
		return flowInputArgs;
	}

	private List<ORObject> getORObjectsArguments(FlowStep flowStep)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		List<ORObject> allORObjects = new ArrayList<ORObject>();
		List<FlowInputArgument> inputArguments = flowStep.getFlowInputArgs();
		for (FlowInputArgument inputArgument : inputArguments) {
			if (inputArgument.getStaticobjectid() != null) {
				// List<ORObject> orobjects = new
				// ObjectRepositoryApi().getORObject(inputArgument.getStaticobjectid());
				List<ORObject> orobjects = GlobalLoader.getInstance().getAllORObjects();
				for (ORObject orobect : orobjects) {
					if (orobect.getObject_id().equals(inputArgument.getStaticobjectid())) {
						allORObjects.add(orobect);
					}
				}
			}
		}
		return allORObjects;

	}

	public void insertKeywordInputArgument(Keyword keyword, List<FlowInputArgument> flowInputArguments) {
		if (keyword == null) {
			return;
		}
		if (flowInputArguments.size() == keyword.getKeywordInputArguments().size()) {
			for (int i = 0; i < flowInputArguments.size(); i++) {
				FlowInputArgument flowInp = flowInputArguments.get(i);
				KeyWordInputArgument keyInp = keyword.getKeywordInputArguments().get(i);
				flowInp.setKeywordInputArgument(keyInp);
			}
		}
	}

	public void insertComponentInputArgument(FunctionLibraryComponent flcomp,
			List<FlowInputArgument> flowInputArguments) {
		if (flcomp == null) {
			return;
		}
		if (flowInputArguments.size() == flcomp.getComponentInputArguments().size()) {
			for (int i = 0; i < flowInputArguments.size(); i++) {
				FlowInputArgument flowInp = flowInputArguments.get(i);
				ComponentInputArgument compoInp = flcomp.getComponentInputArguments().get(i);
				flowInp.setComponentInputArgument(compoInp);
			}
		}
	}

	public List<FlowStep> getAllFlowSteps(String flowId)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		List<FlowStep> flowSteps = getAllSteps(flowId);
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.getKeywordid() != null) {
				Keyword keyword = KeywordManager.getInstance().getKeyword(flowStep.getKeywordid());
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				insertKeywordInputArgument(keyword, fis);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
				flowStep.setKeyword(keyword);
				flowStep.setFlowInputArgs(fis);
				flowStep.setFlowOutputArgs(fos);
				List<ORObject> allORObject = getORObjectsArguments(flowStep);
				flowStep.setOrObject(allORObject);
				flowStep.setIstestcase(true);
			} else if (flowStep.getComponent_id() != null) {
				FunctionLibraryComponent flComp = getFunctinLibraryComponent(flowStep.getComponent_id()).get(0);
				List<ComponentInputArgument> inputArgs = getAllComponentInputArgument(flowStep.getComponent_id());
				List<ComponentOutputArgument> outputArgs = getAllComponentOutputArgument(flowStep.getComponent_id());
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
				flComp.setComponentInputArguments(inputArgs);
				flComp.setComponentOutputArguments(outputArgs);
				insertComponentInputArgument(flComp, fis);
				flowStep.setFunctionLibraryComponent(flComp);
				flowStep.setFlowInputArgs(fis);
				flowStep.setFlowOutputArgs(fos);
				List<ORObject> allORObject = getORObjectsArguments(flowStep);
				flowStep.setOrObject(allORObject);
				flowStep.setIsfunctionlibrary(true);
			}
		}
		return flowSteps;
	}

	public List<ComponentInputArgument> getAllComponentInputArgument(String componentId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		String query = String.format(
				"select * from component_input_parameters where component_id='%s' ORDER BY position asc", componentId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ComponentInputArgument.class);
		return mapper.readValue(result, type);
	}

	public List<ComponentOutputArgument> getAllComponentOutputArgument(String componentId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		String query = String.format(
				"select * from component_output_parameters where component_id='%s' ORDER BY position asc", componentId);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				ComponentOutputArgument.class);
		return mapper.readValue(result, type);
	}

	public List<FunctionLibraryComponent> getFunctinLibraryComponent(String componentId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		String query = String.format("select * from main_artifact_filesystem where id='%s'", componentId);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				FunctionLibraryComponent.class);
		return mapper.readValue(result, type);
	}

	public List<FlowOutputArgument> fetchFlowStepOutputArguments(String flowStepId) {
		String query = String.format("SELECT * FROM flow_step_output_arguments where flow_stepID!='%s'", flowStepId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowOutputArgument>();
	}
}
