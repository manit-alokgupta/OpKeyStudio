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
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
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
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowStep.setComponent_id(flow_id);
			flowStep.setStepid(Utilities.getInstance().getUniqueUUID(""));
		} else {
			flowStep.setFlow_id(flow_id);
			flowStep.setFlow_stepid(Utilities.getInstance().getUniqueUUID(""));
		}
		flowStep.setKeyword(keyword);
		flowStep.setKeywordid(keyword.getKeywordid());
		flowStep.setPosition(selectedFlowStepPosition + 5);
		flowStep.setShouldrun(true);
		List<FlowInputArgument> flowInputArguments = getFlowStepInputArguments(flowStep);
		List<FlowOutputArgument> flowOutputArguments = new ArrayList<FlowOutputArgument>();
		flowStep.setFlowInputArgs(flowInputArguments);
		flowStep.setFlowOutputArgs(flowOutputArguments);
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
		List<ComponentInputArgument> inputArgs = FlowApi.getInstance().getAllComponentInputArgument(flArtifact.getId());
		List<ComponentOutputArgument> outputArgs = FlowApi.getInstance()
				.getAllComponentOutputArgument(flArtifact.getId());
		flComp.setComponentInputArgument(inputArgs);
		flComp.setComponentOutputArgument(outputArgs);

		List<FlowInputArgument> flowInputArguments = new ArrayList<>();
		List<FlowOutputArgument> flowOutputArguments = new ArrayList<FlowOutputArgument>();
		flowStep.setFlowInputArgs(flowInputArguments);
		flowStep.setFlowOutputArgs(flowOutputArguments);

		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowStep.setStepcomponent_id(flArtifact.getId());
		} else {
			flowStep.setComponent_id(flArtifact.getId());
		}
		flowStep.setFunctionLibraryComponent(flComp);
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

	private List<FlowInputArgument> getFlowStepInputArguments(FlowStep flowStep) {
		List<FlowInputArgument> flowInputArguments = new ArrayList<FlowInputArgument>();
		List<KeyWordInputArgument> keywordInputArguments = flowStep.getKeyword().getKeywordInputArguments();
		for (KeyWordInputArgument keywordInputArgument : keywordInputArguments) {
			FlowInputArgument flowInputArgument = new FlowInputArgument();
			flowInputArgument.setFlow_stepid(Utilities.getInstance().getUniqueUUID(""));
			flowInputArgument.setFlow_stepid(flowStep.getFlow_stepid());
			flowInputArgument.setKeyword_ip_id(keywordInputArgument.getArgid());
			flowInputArgument.setStaticobjectid(null);
			flowInputArgument.setAdded(true);
			flowInputArguments.add(flowInputArgument);
		}
		return flowInputArguments;
	}
}
