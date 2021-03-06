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
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
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

	private List<FlowInputArgument> syncAllComponentInputArgument(String flowId, FlowStep flowStep,
			List<ComponentInputArgument> componentInputArgs, List<FlowInputArgument> flowInputArgs) {
		List<FlowInputArgument> syncFlowInputArguments = new ArrayList<FlowInputArgument>();
		for (ComponentInputArgument inputArgument : componentInputArgs) {
			for (FlowInputArgument flowInputArgument : flowInputArgs) {
				if (flowInputArgument.getComponent_ip_id().equals(inputArgument.getIp_id())) {
					syncFlowInputArguments.add(flowInputArgument);
					inputArgument.setChecked(true);
				}
			}
		}

		for (ComponentInputArgument inputArgument : componentInputArgs) {
			if (inputArgument.isChecked() == false) {
				FlowInputArgument flowinputArgument = new FlowInputArgument();
				flowinputArgument.setStepid(flowStep.getStepid());
				flowinputArgument.setStep_arg_id(Utilities.getInstance().getUniqueUUID(""));
				flowinputArgument.setComponent_ip_id(inputArgument.getIp_id());
				flowinputArgument.setArg_datasource(DataSource.StaticValue);
				flowinputArgument.setStaticvalue("");
				flowinputArgument.setAdded(true);

				syncFlowInputArguments.add(flowinputArgument);
			}
		}

		System.out.println("Synced Input Arguments " + syncFlowInputArguments.size());
		new FunctionLibraryConstruct().saveFlowInputArguments(syncFlowInputArguments);
		for (FlowInputArgument inputArg : syncFlowInputArguments) {
			inputArg.setAdded(false);
		}
		return syncFlowInputArguments;
	}

	private List<FlowOutputArgument> syncAllComponentOutputArgument(String flowId, FlowStep flowStep,
			List<ComponentOutputArgument> componentInputArgs, List<FlowOutputArgument> flowInputArgs) {
		/*
		 * List<FlowOutputArgument> syncFlowInputArguments = new
		 * ArrayList<FlowOutputArgument>(); for (ComponentOutputArgument inputArgument :
		 * componentInputArgs) { for (FlowOutputArgument flowInputArgument :
		 * flowInputArgs) { if
		 * (flowInputArgument.getComponent_op_id().equals(inputArgument.
		 * getComponentstep_oa_id())) { syncFlowInputArguments.add(flowInputArgument);
		 * inputArgument.setChecked(true); } } }
		 * 
		 * for (ComponentOutputArgument inputArgument : componentInputArgs) { if
		 * (inputArgument.isChecked() == false) { FlowOutputArgument flowinputArgument =
		 * new FlowOutputArgument();
		 * flowinputArgument.setFlow_stepid(flowStep.getFlow_stepid());
		 * flowinputArgument.setFlow_step_oa_id(Utilities.getInstance().getUniqueUUID(""
		 * ));
		 * flowinputArgument.setComponent_op_id(inputArgument.getComponentstep_oa_id());
		 * flowinputArgument.setAdded(true);
		 * 
		 * syncFlowInputArguments.add(flowinputArgument); } }
		 * 
		 * System.out.println("Synced Input Arguments " +
		 * syncFlowInputArguments.size()); new
		 * FlowConstruct().saveFlowOutputArguments(syncFlowInputArguments); for
		 * (FlowOutputArgument inputArg : syncFlowInputArguments) {
		 * inputArg.setAdded(false); }
		 */
		return flowInputArgs;
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
				new FlowApi().insertKeywordInputArgument(keyword, fis);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
				flowStep.setKeyword(keyword);
				flowStep.setFlowInputArgs(fis);
				flowStep.setFlowOutputArgs(fos);
				List<ORObject> allORObject = getORObjectsArguments(flowStep);
				flowStep.setOrObject(allORObject);
				flowStep.setIsTestCaseStep(true);
			} else if (flowStep.getStepcomponent_id() != null) {
				FunctionLibraryComponent flComp = FlowApi.getInstance().getFunctionLibraryComponent(flowStep.getStepcomponent_id()).get(0);
				List<ComponentInputArgument> inputArgs = getAllComponentInputArgument(flowStep.getStepcomponent_id());
				List<ComponentOutputArgument> outputArgs = getAllComponentOutputArgument(
						flowStep.getStepcomponent_id());
				List<FlowInputArgument> fis = getFlowStepInputArguments(flowStep);
				List<FlowOutputArgument> fos = getFlowStepOutputArguments(flowStep);
				System.out.println("Input Size " + fis.size() + "   CInput Size " + inputArgs.size());
				List<FlowInputArgument> flowInputArgs = syncAllComponentInputArgument(flowId, flowStep, inputArgs, fis);
				flComp.setComponentInputArguments(inputArgs);
				flComp.setComponentOutputArguments(outputArgs);
				flowStep.setFunctionLibraryComponent(flComp);
				insertComponentInputArgument(flComp, flowInputArgs);
				flowStep.setFlowInputArgs(flowInputArgs);
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
			e.printStackTrace();
		}
		return new ArrayList<ComponentOutputArgument>();
	}

}
