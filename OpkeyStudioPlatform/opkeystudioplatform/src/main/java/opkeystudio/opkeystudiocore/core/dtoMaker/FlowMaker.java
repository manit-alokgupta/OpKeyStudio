package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowMaker {
	public FlowStep getFlowStepDTO(Keyword keyword, String flow_id) {
		FlowStep flowStep = new FlowStep();
		flowStep.setFlow_id(flow_id);
		flowStep.setFlow_stepid(Utilities.getInstance().getUniqueUUID(""));
		flowStep.setKeyword(keyword);
		// flowStep.setFlowInputArgs("");
		// flowStep.setFlowOutputArgs(flowOutputArgs);
		// flowStep.setOrObject(orObject);

		flowStep.setAdded(true);
		return flowStep;
	}

	private List<FlowInputArgument> getFlowStepInputArguments(Keyword keyword) {
		List<FlowInputArgument> flowInputArguments = new ArrayList<FlowInputArgument>();
		List<KeyWordInputArgument> keywordInputArguments = keyword.getKeywordInputArguments();
		
		return flowInputArguments;
	}
}
