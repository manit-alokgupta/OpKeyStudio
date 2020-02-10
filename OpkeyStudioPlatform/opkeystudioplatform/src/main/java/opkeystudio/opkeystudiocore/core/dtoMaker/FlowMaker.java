package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowMaker {
	public FlowStep getFlowStepDTO(Artifact artifact, FlowStep selectedFlowStep, Keyword keyword, String flow_id,
			List<FlowStep> flowSteps) {
		int selectedFlowStepIndex = flowSteps.indexOf(selectedFlowStep);
		int selectedFlowStepPosition = 0;
		if (selectedFlowStep != null) {
			selectedFlowStepPosition = selectedFlowStep.getPosition();
		} else {
			if (flowSteps.size() > 0) {
				FlowStep lastTestSuite = flowSteps.get(flowSteps.size() - 1);
				selectedFlowStepPosition = lastTestSuite.getPosition();
			}
		}

		FlowStep flowStep = new FlowStep();
		if (keyword == null) {
			flowStep.setNullKeyword(true);
		}
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowStep.setComponent_id(flow_id);
			flowStep.setStepid(Utilities.getInstance().getUniqueUUID(""));
		} else {
			flowStep.setFlow_id(flow_id);
			flowStep.setFlow_stepid(Utilities.getInstance().getUniqueUUID(""));
		}
		if (keyword != null) {
			flowStep.setKeyword(keyword);
			flowStep.setKeywordid(keyword.getKeywordid());
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				flowStep.setComments(flowStep.getKeyword().getKeyworddescription());
			} else {
				flowStep.setComment(flowStep.getKeyword().getKeyworddescription());
			}
		}
		flowStep.setPosition(selectedFlowStepPosition + 5);
		flowStep.setShouldrun(true);
		flowStep.setContinueonerror(true);
		if (keyword != null) {
			List<FlowInputArgument> flowInputArguments = getFlowStepInputArguments(artifact, flowStep);
			List<FlowOutputArgument> flowOutputArguments = getFlowStepOutputArguments(artifact, flowStep);
			flowStep.setFlowInputArgs(flowInputArguments);
			flowStep.setFlowOutputArgs(flowOutputArguments);
		}
		flowStep.setAdded(true);
		for (int i = selectedFlowStepIndex + 1; i < flowSteps.size(); i++) {
			FlowStep iflowStep = flowSteps.get(i);
			iflowStep.setPosition(iflowStep.getPosition() + 10);
			iflowStep.setModified(true);
		}
		return flowStep;
	}

	public FlowStep createFlowStepReplica(Artifact artifact, FlowStep selectedFlowStep, FlowStep pasteFlowStep,
			List<FlowStep> flowSteps) {
		int selectedFlowStepIndex = flowSteps.indexOf(selectedFlowStep);
		int selectedFlowStepPosition = 0;
		if (selectedFlowStep != null) {
			selectedFlowStepPosition = selectedFlowStep.getPosition();
			System.out.println("Position " + selectedFlowStepPosition);
		} else {
			if (flowSteps.size() > 0) {
				FlowStep lastFlowStep = flowSteps.get(flowSteps.size() - 1);
				selectedFlowStepPosition = lastFlowStep.getPosition();
			}
		}

		FlowStep flowStep = pasteFlowStep.clone();
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowStep.setStepid(Utilities.getInstance().getUniqueUUID(""));
		} else {
			flowStep.setFlow_stepid(Utilities.getInstance().getUniqueUUID(""));
		}
		flowStep.setPosition(selectedFlowStepPosition + 5);
		List<ORObject> orObjects = pasteFlowStep.getOrObject();
		List<ORObject> allORObjects = new ArrayList<ORObject>();
		for (ORObject orobject : orObjects) {
			allORObjects.add(orobject.clone());
		}
		flowStep.setOrObject(allORObjects);

		List<FlowInputArgument> inputArgs = pasteFlowStep.getFlowInputArgs();
		List<FlowInputArgument> allInputArgs = new ArrayList<FlowInputArgument>();
		for (FlowInputArgument inputArg : inputArgs) {
			FlowInputArgument inputArgument = inputArg.clone();
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				inputArgument.setStep_arg_id(Utilities.getInstance().getUniqueUUID(""));
				inputArgument.setStepid(flowStep.getStepid());
			} else {
				inputArgument.setFlow_step_ia_id(Utilities.getInstance().getUniqueUUID(""));
				inputArgument.setFlow_stepid(flowStep.getFlow_stepid());
			}
			inputArgument.setAdded(true);
			allInputArgs.add(inputArgument);
		}
		flowStep.setFlowInputArgs(allInputArgs);
		List<FlowOutputArgument> outputArgs = pasteFlowStep.getFlowOutputArgs();
		List<FlowOutputArgument> allOutputArgs = new ArrayList<FlowOutputArgument>();
		for (FlowOutputArgument outputArg : outputArgs) {
			FlowOutputArgument outputarg = outputArg.clone();
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				outputArg.setComponentstep_oa_id(Utilities.getInstance().getUniqueUUID(""));
				outputArg.setComponentstep_id(flowStep.getStepid());
			} else {
				outputArg.setFlow_step_oa_id(Utilities.getInstance().getUniqueUUID(""));
			}
			outputarg.setAdded(true);
			allOutputArgs.add(outputarg);
		}
		flowStep.setFlowOutputArgs(allOutputArgs);
		flowStep.setAdded(true);
		for (int i = selectedFlowStepIndex + 1; i < flowSteps.size(); i++) {
			FlowStep iflowStep = flowSteps.get(i);
			iflowStep.setPosition(iflowStep.getPosition() + 10);
			iflowStep.setModified(true);
		}
		return flowStep;
	}

	public FlowStep getFlowStepDTOWithFunctionLibray(Artifact artifact, FlowStep selectedFlowStep, Artifact flArtifact,
			String flow_id, List<FlowStep> flowSteps)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		int selectedFlowStepIndex = flowSteps.indexOf(selectedFlowStep);
		int selectedFlowStepPosition = selectedFlowStep.getPosition();
		FlowStep flowStep = new FlowStep();
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowStep.setComponent_id(flow_id);
			flowStep.setStepid(Utilities.getInstance().getUniqueUUID(""));
		} else {
			flowStep.setFlow_id(flow_id);
			flowStep.setFlow_stepid(Utilities.getInstance().getUniqueUUID(""));
		}

		FunctionLibraryComponent flComp = FlowApi.getInstance().getFunctinLibraryComponent(flArtifact.getId()).get(0);
		flowStep.setFunctionLibraryComponent(flComp);
		List<ComponentInputArgument> inputArgs = FlowApi.getInstance().getAllComponentInputArgument(flArtifact.getId());
		List<ComponentOutputArgument> outputArgs = FlowApi.getInstance()
				.getAllComponentOutputArgument(flArtifact.getId());
		flComp.setComponentInputArguments(inputArgs);
		flComp.setComponentOutputArguments(outputArgs);

		List<FlowInputArgument> flowInputArguments = getFlowStepInputArguments(artifact, flowStep);
		List<FlowOutputArgument> flowOutputArguments = getFlowStepOutputArguments(artifact, flowStep);
		flowStep.setFlowInputArgs(flowInputArguments);
		flowStep.setFlowOutputArgs(flowOutputArguments);

		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowStep.setStepcomponent_id(flArtifact.getId());
		} else {
			flowStep.setComponent_id(flArtifact.getId());
		}

		flowStep.setPosition(selectedFlowStepPosition + 5);
		flowStep.setShouldrun(true);

		flowStep.setAdded(true);
		for (int i = selectedFlowStepIndex + 1; i < flowSteps.size(); i++) {
			FlowStep iflowStep = flowSteps.get(i);
			iflowStep.setPosition(iflowStep.getPosition() + 10);
			iflowStep.setModified(true);
		}
		return flowStep;
	}

	public List<FlowOutputArgument> getFlowStepOutputArguments(Artifact artifact, FlowStep flowStep) {
		List<FlowOutputArgument> flowOutputArguments = new ArrayList<FlowOutputArgument>();
		if (flowStep.getKeyword() != null) {
			FlowOutputArgument flowOutputArgument = new FlowOutputArgument();
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				flowOutputArgument.setComponentstep_oa_id(Utilities.getInstance().getUniqueUUID(""));
				flowOutputArgument.setComponentstep_id(flowStep.getStepid());
			} else {
				flowOutputArgument.setFlow_step_oa_id(Utilities.getInstance().getUniqueUUID(""));
				flowOutputArgument.setFlow_stepid(flowStep.getFlow_stepid());
			}
			flowOutputArgument.setKeyword_op_id(flowStep.getKeyword().getKeywordid());
			flowOutputArgument.setAdded(true);

			flowOutputArguments.add(flowOutputArgument);
		}

		if (flowStep.getFunctionLibraryComponent() != null) {
			List<ComponentInputArgument> componentInputArgs = flowStep.getFunctionLibraryComponent()
					.getComponentInputArguments();
			for (ComponentInputArgument componentInputArgument : componentInputArgs) {
				FlowInputArgument flowInputArgument = new FlowInputArgument();
				if (artifact.getFile_type_enum() == MODULETYPE.Component) {
					flowInputArgument.setStep_arg_id(Utilities.getInstance().getUniqueUUID(""));
					flowInputArgument.setStepid(flowStep.getStepid());
					flowInputArgument.setArg_datasource(DataSource.StaticValue);
				} else {
					flowInputArgument.setFlow_step_ia_id(Utilities.getInstance().getUniqueUUID(""));
					flowInputArgument.setFlow_stepid(flowStep.getFlow_stepid());
					flowInputArgument.setDatasource(DataSource.StaticValue);
				}
				flowInputArgument.setFlow_stepid(flowStep.getFlow_stepid());
				flowInputArgument.setComponent_ip_id(componentInputArgument.getIp_id());
				flowInputArgument.setStaticobjectid(null);
				flowInputArgument.setAdded(true);

				// flowOutputArguments.add(flowInputArgument);
			}
		}
		return flowOutputArguments;
	}

	public List<FlowInputArgument> getFlowStepInputArguments(Artifact artifact, FlowStep flowStep) {
		List<FlowInputArgument> flowInputArguments = new ArrayList<FlowInputArgument>();
		if (flowStep.getKeyword() != null) {
			List<KeyWordInputArgument> keywordInputArguments = flowStep.getKeyword().getKeywordInputArguments();
			for (KeyWordInputArgument keywordInputArgument : keywordInputArguments) {
				FlowInputArgument flowInputArgument = new FlowInputArgument();
				if (artifact.getFile_type_enum() == MODULETYPE.Component) {
					flowInputArgument.setStep_arg_id(Utilities.getInstance().getUniqueUUID(""));
					flowInputArgument.setStepid(flowStep.getStepid());
					flowInputArgument.setArg_datasource(DataSource.StaticValue);
				} else {
					flowInputArgument.setFlow_step_ia_id(Utilities.getInstance().getUniqueUUID(""));
					flowInputArgument.setFlow_stepid(flowStep.getFlow_stepid());
					flowInputArgument.setDatasource(DataSource.StaticValue);
				}
				flowInputArgument.setKeyword_ip_id(keywordInputArgument.getArgid());
				flowInputArgument.setStaticobjectid(null);
				flowInputArgument.setAdded(true);

				flowInputArguments.add(flowInputArgument);
			}
		}

		if (flowStep.getFunctionLibraryComponent() != null) {
			List<ComponentInputArgument> componentInputArgs = flowStep.getFunctionLibraryComponent()
					.getComponentInputArguments();
			for (ComponentInputArgument componentInputArgument : componentInputArgs) {
				FlowInputArgument flowInputArgument = new FlowInputArgument();
				if (artifact.getFile_type_enum() == MODULETYPE.Component) {
					flowInputArgument.setStep_arg_id(Utilities.getInstance().getUniqueUUID(""));
					flowInputArgument.setStepid(flowStep.getStepid());
					flowInputArgument.setArg_datasource(DataSource.StaticValue);
				} else {
					flowInputArgument.setFlow_step_ia_id(Utilities.getInstance().getUniqueUUID(""));
					flowInputArgument.setFlow_stepid(flowStep.getFlow_stepid());
					flowInputArgument.setDatasource(DataSource.StaticValue);
				}
				flowInputArgument.setFlow_stepid(flowStep.getFlow_stepid());
				flowInputArgument.setComponent_ip_id(componentInputArgument.getIp_id());
				flowInputArgument.setStaticobjectid(null);
				flowInputArgument.setAdded(true);

				flowInputArguments.add(flowInputArgument);
			}
		}
		return flowInputArguments;
	}
}
