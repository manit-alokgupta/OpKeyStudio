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
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct.TCCodeConstruct;
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
		class1.addMethod().setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}

	public String convertToFunctionCode(FlowStep flowStep) {
		if (isKeywordType(flowStep)) {
			if (isConstructFlowKeyword(flowStep)) {
				return new TCCodeConstruct().getConstructFlowKeywordCode(flowStep);
			}
			if (isOpKeyGenericKeyword(flowStep)) {

			}
			if (isSystemKeyword(flowStep)) {

			}
			if (isPluginSpecificKeyword(flowStep)) {

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

}
