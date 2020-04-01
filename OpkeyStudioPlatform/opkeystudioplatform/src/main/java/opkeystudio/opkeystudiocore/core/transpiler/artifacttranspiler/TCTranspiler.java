package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
		try {
			JavaClassSource classSource = getJavaClassOfTestCase(artifact);
			new TranspilerUtilities().writeCodeToFile(file, classSource);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public JavaClassSource getJavaClassOfTestCase(Artifact artifact)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		JavaClassSource class1 = Roaster.create(JavaClassSource.class);
		class1.setName(artifact.getVariableName()).setPublic();
		List<FlowStep> flowSteps = new FlowApi().getAllFlowSteps(artifact.getId());
		for (FlowStep flowStep : flowSteps) {
			if (flowStep.getKeyword() != null) {
				System.out.println(flowStep.getKeyword().getAssociatedmethod() + "    "
						+ flowStep.getFlowInputArgs().get(0).getKeywordInputArgument().getDatatype() + "    " + flowStep.getFlowOutputArgs().size());
			}
		}
		return class1;
	}

}
