package opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.codedfunctionapi.CodedFunctionApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
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
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FunctionLibraryApi {
	private static FunctionLibraryApi flowApi;

	public static FunctionLibraryApi getInstance() {
		if (flowApi == null) {
			flowApi = new FunctionLibraryApi();
		}
		return flowApi;
	}

	private List<FlowStep> getAllSteps(String flowId) {
		String query = String
				.format("SELECT * FROM component_design_steps where component_id='%s' ORDER BY position asc", flowId);
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

	private List<FlowInputArgument> getFlowStepInputArguments(FlowStep flowStep) {
		List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
		List<FlowInputArgument> inputArguments = GlobalLoader.getInstance().getComponentflowInputArguments();
		for (FlowInputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getStepid().equals(flowStep.getStepid())) {
				flowInputArgs.add(flowInputArgument);
			}
		}
		return flowInputArgs;
	}

	private List<FlowOutputArgument> getFlowStepOutputArguments(FlowStep flowStep) {
		List<FlowOutputArgument> flowInputArgs = new ArrayList<FlowOutputArgument>();
		List<FlowOutputArgument> inputArguments = GlobalLoader.getInstance().getComponentflowOutputArguments();
		for (FlowOutputArgument flowInputArgument : inputArguments) {
			if (flowInputArgument.getComponentstep_id().equals(flowStep.getStepid())) {
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

	public List<FlowStep> getAllFlowSteps(String flowId) {
		List<FlowStep> flowSteps = getAllSteps(flowId);
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.getKeywordid() != null) {
				Keyword keyword = KeywordManager.getInstance().getKeyword(flowStep.getKeywordid());
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				new FlowApi().insertKeywordInputArgument(keyword, fis);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
				flowStep.setKeyword(keyword);
				flowStep.setFlowInputArgs(fis);
				flowStep.setFlowOutputArgs(fos);
				List<ORObject> allORObject = getORObjectsArguments(flowStep);
				flowStep.setOrObject(allORObject);
				flowStep.setIsTestCaseStep(true);
			} else if (flowStep.getStepcomponent_id() != null) {
				FunctionLibraryComponent flComp = getFunctinLibraryComponent(flowStep.getStepcomponent_id()).get(0);
				List<ComponentInputArgument> inputArgs = getAllComponentInputArgument(flowStep.getStepcomponent_id());
				List<ComponentOutputArgument> outputArgs = getAllComponentOutputArgument(
						flowStep.getStepcomponent_id());
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
				flComp.setComponentInputArguments(inputArgs);
				flComp.setComponentOutputArguments(outputArgs);
				flowStep.setFunctionLibraryComponent(flComp);
				flowStep.setFlowInputArgs(fis);
				flowStep.setFlowOutputArgs(fos);
				List<ORObject> allORObject = getORObjectsArguments(flowStep);
				flowStep.setOrObject(allORObject);
				flowStep.setIsfunctionlibraryStep(true);
			} else if (flowStep.getStepcodedfunction_id() != null) {
				CodedFunctionArtifact codedFunctionArtifact = FlowApi.getInstance()
						.getCodedFunctionArtifact(flowStep.getStepcodedfunction_id()).get(0);
				List<CFLInputParameter> cflInputParams = new CodedFunctionApi()
						.getCodedFLInputParameters(codedFunctionArtifact);
				List<CFLOutputParameter> cflOutputParams = new CodedFunctionApi()
						.getCodedFLOutputParameters(codedFunctionArtifact);
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);

				codedFunctionArtifact.setCflInputParameters(cflInputParams);
				codedFunctionArtifact.setCflOutputParameters(cflOutputParams);
				flowStep.setCodedFunctionArtifact(codedFunctionArtifact);
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

	public List<FunctionLibraryComponent> getFunctinLibraryComponent(String componentId) {
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

}
