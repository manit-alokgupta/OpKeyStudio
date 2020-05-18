package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApiUtilities;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.collections.FlowInputObject;
import opkeystudio.opkeystudiocore.core.collections.FlowOutputObject;

public class TCFLCodeConstruct {
	private String newLineChar = "\n";

	public String convertToFunctionCode(Artifact artifact, FlowStep flowStep) {
		if (isKeywordType(flowStep)) {
			if (isConstructFlowKeyword(flowStep)) {
				String code = getConstructFlowKeywordCode(artifact, flowStep);
				code = addOutputVariables(artifact, flowStep, code);
				return addDataRepositoryIterations(artifact, flowStep, code);
			}
			if (isOpKeyGenericKeyword(flowStep)) {
				String variableName = "genericKeywords";
				if (flowStep.isAppiumType()) {
					variableName = "appiumKeywords";
				}
				String code = getKeywordCode(artifact, flowStep, variableName);
				code = addOutputVariables(artifact, flowStep, code);
				return addDataRepositoryIterations(artifact, flowStep, code);
			}
			if (isSystemKeyword(flowStep)) {
				String code = getKeywordCode(artifact, flowStep, "systemKeywords");
				code = addOutputVariables(artifact, flowStep, code);
				return addDataRepositoryIterations(artifact, flowStep, code);
			}
			if (isPluginSpecificKeyword(flowStep)) {
				String varName = getPluginSpecificVarName(flowStep);
				String code = getKeywordCode(artifact, flowStep, varName);
				code = addOutputVariables(artifact, flowStep, code);
				return addDataRepositoryIterations(artifact, flowStep, code);
			}
		}

		if (isFunctionLibraryType(flowStep)) {
			return getFunctionLibraryCode(artifact, flowStep);
			// String mainCode = getFunctionLibraryCode(artifact, flowStep);
			// return addOutputVariables(artifact, flowStep, mainCode);
		}
		if (isCodedFunctionType(flowStep)) {
			return getCodedFunctionCode(artifact, flowStep);
		}
		return "";
	}

	private String getPluginSpecificVarName(FlowStep flowStep) {
		String pluginName = flowStep.getKeyword().getPluginName().toLowerCase();
		if (pluginName.contains("appium")) {
			return "appiumKeywords";
		}
		return "genericKeywords";
	}

	private String getKeywordCode(Artifact artifact, FlowStep flowStep, String refvarName) {
		String keywordName = flowStep.getKeyword().getName();
		String methodcode = newLineChar + refvarName + "." + keywordName + "(";
		List<FlowInputArgument> flowInputArguments = flowStep.getFlowInputArgs();
		List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject(artifact,
				flowInputArguments);
		String argumentCall = "";
		for (FlowInputObject flowInputObject : flowInputObjects) {
			if (!argumentCall.isEmpty()) {
				argumentCall += ", ";
			}
			if (flowInputObject.isStaticObjectDataExist()) {
				System.out.println("Cond 1 " + flowInputObject.getDataType());
				if (flowStep.getOrObject().size() == 0) {
					argumentCall += "null";
				} else {
					ORObject orobject = flowStep.getOrObject().get(0);
					Artifact orartifact = GlobalLoader.getInstance().getArtifactById(orobject.getOr_id());
					String varName = orartifact.getVariableName() + "." + orobject.getVariableName();
					argumentCall += varName;
				}
			}

			if (flowInputObject.isStaticValueDataExist()) {
				System.out.println("Cond 2 " + flowInputObject.getDataType());
				String value = formatDataType(flowInputObject.getDataType(), flowInputObject.getStaticValueData());
				argumentCall += value;
			}

			if (flowInputObject.isGlobalVariableDataExist()) {
				System.out.println("Cond 3 " + flowInputObject.getDataType());
				GlobalVariable globalVariable = GlobalLoader.getInstance()
						.getGlobalVariableById(flowInputObject.getGlobalVariableData());

				argumentCall += "OpKeyGlobalVariables." + globalVariable.getVariableName();
			}

			if (flowInputObject.isFlowOutputDataExist()) {
				System.out.println("Cond 4 " + flowInputObject.getDataType());
				String flowOutputId = flowInputObject.getFlowOutputData();
				FlowOutputArgument flowOutputArgument = GlobalLoader.getInstance()
						.getFlowOutputArgumentById(flowOutputId);
				argumentCall += flowOutputArgument.getVariableName();
			}

			if (flowInputObject.isFlowInputDataExist()) {
				System.out.println("Cond 5 " + flowInputObject.getDataType());
				String flowInputId = flowInputObject.getFlowInputData();
				System.out.println(">>Flow Input Id " + flowInputId);
				ComponentInputParameter flowOutputArgument = GlobalLoader.getInstance()
						.getComponentInputArgumentById(flowInputId);
				argumentCall += flowOutputArgument.getVariableName();
			}
			if (flowInputObject.isDataRepositoryColumnDataExist()) {
				System.out.println("Cond 6 " + flowInputObject.getDataType());
				String columnId = flowInputObject.getDataRepositoryColumnData();
				DRColumnAttributes drColumn = GlobalLoader.getInstance().getDRColumn(columnId);
				String columnName = drColumn.getVariableName();
				argumentCall += columnName;
			}
		}
		System.out.println(argumentCall);
		methodcode += argumentCall;
		methodcode += ");";
		return methodcode;
	}

	private String getFunctionLibraryCode(Artifact artifact, FlowStep flowStep) {
		FunctionLibraryComponent libraryComponent = flowStep.getFunctionLibraryComponent();
		List<FlowInputArgument> componentInputArguments = flowStep.getFlowInputArgs();
		List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject_FL(artifact,
				libraryComponent, componentInputArguments);
		String value = "";
		for (FlowInputObject flowInputObject : flowInputObjects) {
			if (!value.isEmpty()) {
				value += ", ";
			}
			value += formatDataType(flowInputObject.getDataType(), flowInputObject.getStaticValueData());
		}
		String code = newLineChar + " new " + libraryComponent.getVariableName() + "().execute(" + value + ");";
		return code;
	}

	private String getCodedFunctionCode(Artifact artifact, FlowStep flowStep) {
		CodedFunctionArtifact libraryComponent = flowStep.getCodedFunctionArtifact();
		List<FlowInputArgument> componentInputArguments = flowStep.getFlowInputArgs();
		List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject_FL(artifact, libraryComponent.getParentccomponent(), componentInputArguments);
		String value = "";
		for (FlowInputObject flowInputObject : flowInputObjects) {
			if (!value.isEmpty()) {
				value += ", ";
			}
			value += formatDataType(flowInputObject.getDataType(), flowInputObject.getStaticValueData());
		}
		String code = newLineChar + " new " + libraryComponent.getParentccomponent().getVariableName() + "().run(" + value + ");";
		return code;
	}

	private String formatDataType(String dataType, String data) {
		if (data == null) {
			data = "";
		}
		if (dataType.equals("String")) {
			return "\"" + data.trim().replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
		}
		if (dataType.equals("Integer")) {
			if (data.isEmpty()) {
				data = "0";
			}
			return data;
		}
		if (dataType.equals("Double")) {
			if (data.isEmpty()) {
				data = "0";
			}
			return data;
		}
		if (dataType.equals("Boolean")) {
			if (data.isEmpty()) {
				data = "false";
			}
			return data.toLowerCase();
		}
		if (dataType.equals("File")) {
			return "\"" + data.trim().replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
		}
		if (dataType.equals("DateTime")) {

			return "\"" + data.trim().replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
		}
		return "\"" + data.trim().replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
	}

	private String getConstructFlowKeywordCode(Artifact artifact, FlowStep flowStep) {
		String keywordName = flowStep.getKeyword().getName();
		System.out.println("Keyword " + keywordName);
		if (keywordName.equals("For")) {
			List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject(artifact,
					flowStep.getFlowInputArgs());
			String value = flowInputObjects.get(0).getStaticValueData();
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
			List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject(artifact,
					flowStep.getFlowInputArgs());
			String value = flowInputObjects.get(0).getStaticValueData();
			System.out.println(">>Value is " + value);
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
			List<FlowInputArgument> flowInputArgs = flowStep.getFlowInputArgs();
			List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject(artifact,
					flowInputArgs);
			String conditionData = getArgsOfIFKeyword(flowInputObjects);
			System.out.println("Argument " + conditionData);
			return newLineChar + "if(" + conditionData + "){";
		}
		return "";
	}

	private String getArgsOfIFKeyword(List<FlowInputObject> flowInputObjects) {
		String conditionData = "";
		for (FlowInputObject flowInputObject : flowInputObjects) {
			if (flowInputObject.isStaticValueDataExist()) {
				conditionData += convertToConditionData(flowInputObject.getStaticValueData());
			}
			System.out.println(
					">.IF condition " + flowInputObject.getDataType() + "  " + flowInputObject.getStaticValueData());
		}
		if (conditionData.trim().isEmpty()) {
			return "true";
		}
		return conditionData;
	}

	private String convertToConditionData(String data) {
		if (data == null) {
			return "";
		}
		if (data.equals("=")) {
			return " == ";
		}
		if (data.equals("<")) {
			return "<";
		}
		if (data.equals(">")) {
			return ">";
		}
		if (data.equals("<>")) {
			return " != ";
		}
		if (data.toLowerCase().equals("and")) {
			return "&&";
		}
		if (data.toLowerCase().equals("or")) {
			return "||";
		}
		if (data.isEmpty()) {
			return "";
		}
		return "\"" + data + "\"";
	}

	private String addOutputVariables(Artifact artifact, FlowStep flowStep, String mainCode) {
		String outputCode = "";
		List<FlowOutputObject> flowOutputObjects = new FlowApiUtilities().getAllFlowOutputObject(artifact, flowStep);
		for (FlowOutputObject flowOutPutObject : flowOutputObjects) {
			String dataType = flowOutPutObject.getDataType();
			String outputVariableName = flowOutPutObject.getOutputVariableName();
			if (outputVariableName != null) {
				if (!outputVariableName.isEmpty()) {
					if (dataType.equals("String")) {
						outputCode = "String " + outputVariableName + " = ";
					}
					if (dataType.equals("Integer")) {
						outputCode = "int " + outputVariableName + " = ";
					}
					if (dataType.equals("Boolean")) {
						outputCode = "boolean " + outputVariableName + " = ";
					}
				}
			}
		}
		return outputCode + mainCode;
	}

	private String addDataRepositoryIterations(Artifact artifact, FlowStep flowStep, String mainCode) {
		String forLoopParameters = "";
		List<FlowInputArgument> flowInputArguments = flowStep.getFlowInputArgs();
		List<FlowInputObject> flowInputObjects = new FlowApiUtilities().getAllFlowInputObject(artifact,
				flowInputArguments);
		for (FlowInputObject flowInputObject : flowInputObjects) {
			if (flowInputObject.isDataRepositoryColumnDataExist()) {
				String columnId = flowInputObject.getDataRepositoryColumnData();
				DRColumnAttributes drColumn = GlobalLoader.getInstance().getDRColumn(columnId);
				String columnName = drColumn.getVariableName();
				Artifact drArtifact = GlobalLoader.getInstance().getArtifactById(drColumn.getDr_id());
				String path = drArtifact.getPackageName() + "." + drArtifact.getVariableName() + "." + "getDRCells("
						+ "\"" + columnName + "\"" + ")";
				forLoopParameters = "String " + columnName + ":" + path;
				System.out.println(forLoopParameters);
				break;
			}
		}
		if (forLoopParameters.isEmpty()) {
			return mainCode;
		}
		String forLoopFormat = "for(%s) {%s}";
		String forLoopCode = String.format(forLoopFormat, forLoopParameters, mainCode);
		return forLoopCode;
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
		System.out.println(flowStep.getKeyword().getName() + "    " + flowStep.getKeyword().getKeywordtype());
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

	private boolean isCodedFunctionType(FlowStep flowStep) {
		if (flowStep.getCodedFunctionArtifact() != null) {
			return true;
		}
		return false;
	}

	public List<String> getDefaultKeywordsClassVariables() {
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("OpKeyGenericKeywords genericKeywords= new OpKeyGenericKeywords();");
		variables.add("OpKeySystemKeywords systemKeywords= new OpKeySystemKeywords();");
		variables.add("OpKeyAppiumKeywords appiumKeywords= new OpKeyAppiumKeywords();");
		return variables;
	}
}
