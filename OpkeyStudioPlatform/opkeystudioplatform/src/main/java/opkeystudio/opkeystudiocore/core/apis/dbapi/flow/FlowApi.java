package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
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

	public List<FlowInputArgument> getFlowStepInputArguments(FlowStep flowStep) {
		List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
		List<FlowInputArgument> inputArguments = GlobalLoader.getInstance().getFlowInputArguments();
		for (FlowInputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getFlow_stepid().equals(flowStep.getFlow_stepid())) {
				flowInputArgs.add(flowInputArgument);
			}
		}
		return flowInputArgs;
	}

	private List<FlowOutputArgument> getFlowStepOutputArguments(FlowStep flowStep) {
		List<FlowOutputArgument> flowInputArgs = new ArrayList<FlowOutputArgument>();
		List<FlowOutputArgument> inputArguments = GlobalLoader.getInstance().getFlowOutputArguments();
		for (FlowOutputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getFlow_stepid().equals(flowStep.getFlow_stepid())) {
				flowInputArgs.add(flowInputArgument);
			}
		}
		return flowInputArgs;
	}

	private List<ORObject> getORObjectsArguments(FlowStep flowStep) {
		List<ORObject> allORObjects = new ArrayList<ORObject>();
		List<FlowInputArgument> inputArguments = flowStep.getFlowInputArgs();
		for (FlowInputArgument inputArgument : inputArguments) {
			if (inputArgument.getStaticobjectid() != null) {
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

	public List<FlowStep> getAllFlowSteps(String flowId) {
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
				flowStep.setIsTestCaseStep(true);
			} else if (flowStep.getComponent_id() != null) {
				FunctionLibraryComponent flComp = getFunctionLibraryComponent(flowStep.getComponent_id()).get(0);
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
				flowStep.setIsfunctionlibraryStep(true);
			} else if (flowStep.getCodedfunction_id() != null) {
				CodedFunctionArtifact codedFunctionArtifac = getCodedFunctionArtifact(flowStep.getCodedfunction_id())
						.get(0);
				List<CFLInputParameter> cflInputParams = new ArrayList<CFLInputParameter>();
				List<CFLOutputParameter> cflOutputParams = new ArrayList<CFLOutputParameter>();
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);

				codedFunctionArtifac.setCflInputParameters(cflInputParams);
				codedFunctionArtifac.setCflOutputParameters(cflOutputParams);
				flowStep.setCodedFunctionArtifact(codedFunctionArtifac);
				flowStep.setFlowInputArgs(fis);
				flowStep.setFlowOutputArgs(fos);
				flowStep.setCodedFunctionLibrary(true);
			}
		}
		return flowSteps;
	}

	public List<ComponentInputArgument> getAllComponentInputArgument(String componentId) {
		String query = String.format(
				"select * from component_input_parameters where component_id='%s' ORDER BY position asc", componentId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ComponentInputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ComponentInputArgument>();
	}

	public List<ComponentOutputArgument> getAllComponentOutputArgument(String componentId) {
		String query = String.format(
				"select * from component_output_parameters where component_id='%s' ORDER BY position asc", componentId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				ComponentOutputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ComponentOutputArgument>();
	}

	public List<FunctionLibraryComponent> getFunctionLibraryComponent(String componentId) {
		String query = String.format("select * from main_artifact_filesystem where id='%s'", componentId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				FunctionLibraryComponent.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FunctionLibraryComponent>();
	}

	public List<CodedFunctionArtifact> getCodedFunctionArtifact(String componentId) {
		String query = String.format("select * from main_artifact_filesystem where id='%s'", componentId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CodedFunctionArtifact.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<CodedFunctionArtifact>();
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
