package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TCTranspiler extends AbstractTranspiler {
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
			methodBodyCode += flowStepCode;
		}

		// class1.addMethod().setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}

	public String convertToFunctionCode(FlowStep flowStep) {
		if (isKeywordType(flowStep)) {
			if (isConstructFlowKeyword(flowStep)) {
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
		if (keywordName.equals("For")) {
			return "for(;;){";
		}
		if (keywordName.equals("Next")) {
			return "}";
		}
		if (keywordName.equals("Else")) {
			return "else{";
		}
		if (keywordName.equals("EndIf")) {
			return "}";
		}
		if (keywordName.equals("Sleep")) {
			return "Thread.sleep(1000)";
		}
		if (keywordName.equals("Comment")) {
			return "// My Comment";
		}
		if (keywordName.equals("PauseExecution")) {

		}
		if (keywordName.equals("StopExecution")) {
			return "System.exit(0)";
		}
		if (keywordName.equals("ExitLoop")) {
			return "break;";
		}
		if (keywordName.equals("If")) {
			return "if(true){";
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
