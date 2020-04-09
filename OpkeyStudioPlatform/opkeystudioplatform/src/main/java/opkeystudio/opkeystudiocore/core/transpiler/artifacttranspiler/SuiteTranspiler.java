package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite.TestSuiteApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class SuiteTranspiler extends AbstractTranspiler {
	private String newLineChar = "\n";

	public SuiteTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.Suite) {
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
		String methodBodyCode = "";
		List<TestSuiteStep> suiteSteps = new TestSuiteApi().getAllTestSuitesStepsWithArtifact(artifact.getId());
		for (TestSuiteStep suiteStep : suiteSteps) {
			methodBodyCode += newLineChar + " new " + suiteStep.getArtifact().getVariableName() + "().execute();";
		}
		class1.addMethod().setName("execute").setPublic().setBody(methodBodyCode).addThrows("Exception");
		return class1;
	}
}
