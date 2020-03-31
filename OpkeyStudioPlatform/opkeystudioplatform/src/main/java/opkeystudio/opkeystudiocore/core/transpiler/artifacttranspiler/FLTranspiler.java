package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;

public class FLTranspiler extends AbstractTranspiler {

	@Override
	public void transpile(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.Component) {
			return;
		}
	}

}
