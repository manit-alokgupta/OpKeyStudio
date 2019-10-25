package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StepOutputArgs {
	
	@JsonProperty("FlowStepOutputArgument")
	private List<FlowStepOutputArgument> flowStepOutputArgument=new ArrayList<>();
}
