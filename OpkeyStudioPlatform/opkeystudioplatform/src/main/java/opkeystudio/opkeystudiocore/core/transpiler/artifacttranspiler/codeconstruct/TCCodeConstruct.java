package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class TCCodeConstruct {
	private String newLineChar = "\n";

	public String getConstructFlowKeywordCode(FlowStep flowStep) {
		String keywordName = flowStep.getKeyword().getName();
		if (keywordName.equals("For")) {
			FlowInputArgument inputArg = flowStep.getFlowInputArgs().get(0);
			String value = inputArg.getStaticvalue();
			if (value == null) {
				value = "";
			}
			if (value.isEmpty()) {
				value = "0";
			}
			return newLineChar + "for(int i=0;i<" + value + ";i++){";
		}
		if (keywordName.equals("Next")) {
			return newLineChar + "}";
		}
		if (keywordName.equals("Else")) {
			return newLineChar + "} else{";
		}
		if (keywordName.equals("EndIf")) {
			return newLineChar + "}";
		}
		if (keywordName.equals("Sleep")) {
			FlowInputArgument inputArg = flowStep.getFlowInputArgs().get(0);
			String value = inputArg.getStaticvalue();
			if (value == null) {
				value = "";
			}
			if (value.isEmpty()) {
				value = "0";
			}
			return newLineChar + "Thread.sleep(" + value + ");";
		}
		if (keywordName.equals("Comment")) {
			return newLineChar + "// My Comment";
		}
		if (keywordName.equals("PauseExecution")) {

		}
		if (keywordName.equals("StopExecution")) {
			return newLineChar + "System.exit(0);";
		}
		if (keywordName.equals("ExitLoop")) {
			return newLineChar + "break;";
		}
		if (keywordName.equals("If")) {
			return newLineChar + "if(true){";
		}
		return "";
	}
}
