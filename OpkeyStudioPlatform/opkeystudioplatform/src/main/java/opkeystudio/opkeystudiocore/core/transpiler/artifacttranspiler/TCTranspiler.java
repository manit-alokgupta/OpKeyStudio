package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct.TCFLCodeConstruct;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TCTranspiler extends AbstractTranspiler {
	private String newLineChar = "\n";

	public TCTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getProjectTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(ArtifactDTO artifact) {
		try {
			if (artifact.getFile_type_enum() != MODULETYPE.Flow) {
				return;
			}
			File file = createArtifactFile(artifact);
			JavaClassSource classSource = getJavaClassOfTestCase(artifact);
			new TranspilerUtilities().addPackageName(artifact, classSource);
			new TranspilerUtilities().addDefaultImports(classSource);
			new TranspilerUtilities().writeCodeToFile(file, classSource);
			// new ArtifactParser().parseArtifact(artifact);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JavaClassSource getJavaClassOfTestCase(ArtifactDTO artifact) {
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName(artifact.getVariableName()).setPublic();
		List<FlowStep> flowSteps = FlowApi.getInstance().getAllFlowSteps(artifact.getId());
		new TranspilerUtilities().processFlowStepsForAppium(artifact, flowSteps);
		String methodBodyCode = "";
		for (String varName : new TCFLCodeConstruct().getDefaultKeywordsClassVariables()) {
			methodBodyCode += newLineChar + varName + newLineChar;
		}
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.isShouldrun() == false) {
				continue;
			}
			String flowStepCode = new TCFLCodeConstruct().convertToFunctionCode(artifact, flowStep);
			methodBodyCode += flowStepCode;
		}

		String reportingStart = String.format("ReportBuilder.get().beginTestCase(%s);",
				"\"" + artifact.getName() + "\"");
		String reportingEnd = "ReportBuilder.get().endTestCase();";
		methodBodyCode = reportingStart + methodBodyCode + reportingEnd;

		class1.addMethod().setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}

}
