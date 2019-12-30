package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowMaker {
	public FlowStep getFlowStepDTO(Artifact artifact, FlowStep selectedFlowStep, Keyword keyword, String flow_id,
			List<FlowStep> flowSteps) {
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
