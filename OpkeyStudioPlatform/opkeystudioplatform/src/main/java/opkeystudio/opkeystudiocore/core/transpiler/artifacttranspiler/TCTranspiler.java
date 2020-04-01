package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
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
		try {
			List<FlowStep> flowSteps = new FlowApi().getAllFlowSteps(artifact.getId());
			for (FlowStep flowStep : flowSteps) {
				if (flowStep.getKeyword() != null) {
					System.out.println(flowStep.getKeyword().getAssociatedmethod());
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
