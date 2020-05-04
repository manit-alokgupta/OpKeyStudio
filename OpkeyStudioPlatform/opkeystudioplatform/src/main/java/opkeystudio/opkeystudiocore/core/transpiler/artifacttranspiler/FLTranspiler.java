package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.codeconstruct.TCFLCodeConstruct;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FLTranspiler extends AbstractTranspiler {
	private String newLineChar = "\n";

	public FLTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getProjectTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(Artifact artifact) {
		try {
			if (artifact.getFile_type_enum() != MODULETYPE.Component) {
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

	public JavaClassSource getJavaClassOfTestCase(Artifact artifact) {
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName(artifact.getVariableName()).setPublic();
		List<FlowStep> flowSteps = FunctionLibraryApi.getInstance().getAllFlowSteps(artifact.getId());
		new TranspilerUtilities().processFlowStepsForAppium(artifact, flowSteps);
		String methodBodyCode = "";
		for (String varName : new TCFLCodeConstruct().getDefaultKeywordsClassVariables()) {
			methodBodyCode += newLineChar + varName + newLineChar;
		}
		for (FlowStep flowStep : flowSteps) {
			String flowStepCode = new TCFLCodeConstruct().convertToFunctionCode(artifact, flowStep);
			methodBodyCode += flowStepCode;
		}

		List<ComponentInputArgument> componentInputArguments = FunctionLibraryApi.getInstance()
				.getAllComponentInputArgument(artifact.getId());
		MethodSource<JavaClassSource> method = class1.addMethod();

		for (ComponentInputArgument cia : componentInputArguments) {
			System.out.println("DataType " + cia.getType() + "  " + cia.getVariableName());
			String dataType = convertDataType(cia.getType());
			String varName = cia.getVariableName();
			method.addParameter(dataType, varName);
		}

		String reportingStart = String.format("ReportBuilder.get().beginFunctionLibrary(%s);",
				"\"" + artifact.getName() + "\"");
		String reportingEnd = "ReportBuilder.get().endFunctionLibrary();";
		methodBodyCode = reportingStart + methodBodyCode + reportingEnd;

		method.setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}

	private String convertDataType(String dataType) {
		if (dataType.equals("Integer")) {
			return "int";
		}
		if (dataType.equals("Double")) {
			return "double";
		}
		if (dataType.equals("Float")) {
			return "float";
		}
		if (dataType.equals("Boolean")) {
			return "boolean";
		}
		return "String";
	}

}
