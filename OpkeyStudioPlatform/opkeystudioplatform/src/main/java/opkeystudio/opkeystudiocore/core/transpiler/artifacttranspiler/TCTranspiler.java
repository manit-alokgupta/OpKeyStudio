package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TCTranspiler extends AbstractTranspiler {
	private String newLineChar = "\n";

	public TCTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.Flow) {
			return;
		}
		File file = createArtifactFile(artifact);
		JavaClassSource classSource = getJavaClassOfTestCase(artifact);
		new TranspilerUtilities().addPackageName(artifact, classSource);
		new TranspilerUtilities().addDefaultImports(classSource);
		new TranspilerUtilities().writeCodeToFile(file, classSource);
	}

	public JavaClassSource getJavaClassOfTestCase(Artifact artifact) {
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName(artifact.getVariableName()).setPublic();
		List<FlowStep> flowSteps = new FlowApi().getAllFlowSteps(artifact.getId());
		String methodBodyCode = "";
		for (FlowStep flowStep : flowSteps) {
			String flowStepCode = convertToFunctionCode(flowStep);
			System.out.println("Inline Code " + flowStepCode);
			methodBodyCode += flowStepCode;
		}
		System.out.println("Code " + methodBodyCode);
		class1.addMethod().setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}

	public String convertToFunctionCode(FlowStep flowStep) {
		if (isKeywordType(flowStep)) {
			if (isConstructFlowKeyword(flowStep)) {
				System.out.println("Inside Construct Flow");
				return getConstructFlowKeywordCode(flowStep);
			}
		}
		if (isFunctionLibraryType(flowStep)) {

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

	private String getConstructFlowKeywordCode(FlowStep flowStep) {
		String keywordName = flowStep.getKeyword().getName();
		System.out.println("Keyword Name " + keywordName);
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

	private boolean isFunctionLibraryType(FlowStep flowStep) {
		if (flowStep.getFunctionLibraryComponent() != null) {
			return true;
		}
		return false;
	}

}
