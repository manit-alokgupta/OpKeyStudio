package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;

public class TCFLCodeConstruct {
	private String newLineChar = "\n";

	public String convertToFunctionCode(Artifact artifact, FlowStep flowStep) {
		return convertToFunctionCode(flowStep);
	}

	public String convertToFunctionCode(FlowStep flowStep) {
		if (isKeywordType(flowStep)) {
			if (isConstructFlowKeyword(flowStep)) {
				return getConstructFlowKeywordCode(flowStep);
			}
			if (isOpKeyGenericKeyword(flowStep)) {

			}
			if (isSystemKeyword(flowStep)) {

			}
			if (isPluginSpecificKeyword(flowStep)) {

			}
		}

		if (new TCFLCodeConstruct().isFunctionLibraryType(flowStep)) {

		}
		return "";
	}

	private String getConstructFlowKeywordCode(FlowStep flowStep) {
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

	private boolean isKeywordType(FlowStep flowStep) {
		if (flowStep.getKeyword() != null) {
			return true;
		}
		return false;
	}

	private boolean isConstructFlowKeyword(FlowStep flowStep) {
		if (flowStep.getKeyword().getKeywordtype().equals("ControlFlowConstruct")) {
			return true;
		}
		return false;
	}

	private boolean isOpKeyGenericKeyword(FlowStep flowStep) {
		if (flowStep.getKeyword().getKeywordtype().equals("OpKeyGenericKeyword")) {
			return true;
		}
		return false;
	}

	private boolean isSystemKeyword(FlowStep flowStep) {
		if (flowStep.getKeyword().getKeywordtype().equals("SystemKeyword")) {
			return true;
		}
		return false;
	}

	private boolean isPluginSpecificKeyword(FlowStep flowStep) {
		if (flowStep.getKeyword().getKeywordtype().equals("PluginSpecificKeyword")) {
			return true;
		}
		return false;
	}

	private boolean isFunctionLibraryType(FlowStep flowStep) {
		if (flowStep.getFunctionLibraryComponent() != null) {
			return true;
		}
		return false;
	}

	public List<String> getDefaultKeywordsClassVariables() {
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("OpKeyGenericKeywords genericKeywords=new OpKeyGenericKeywords();");
		variables.add("OpKeySystemKeywords systemKeywords=new OpKeySystemKeywords();");
		return variables;
	}
}
