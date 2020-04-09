package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct.TCFLCodeConstruct;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FLTranspiler extends AbstractTranspiler {
	private String newLineChar = "\n";

	public FLTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.Component) {
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
		List<FlowStep> flowSteps = FunctionLibraryApi.getInstance().getAllFlowSteps(artifact.getId());
		String methodBodyCode = "";
		for (String varName : new TCFLCodeConstruct().getDefaultKeywordsClassVariables()) {
			methodBodyCode += newLineChar + varName + newLineChar;
		}
		for (FlowStep flowStep : flowSteps) {
			String flowStepCode = new TCFLCodeConstruct().convertToFunctionCode(artifact, flowStep);
			methodBodyCode += flowStepCode;
		}
		class1.addMethod().setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}
}
