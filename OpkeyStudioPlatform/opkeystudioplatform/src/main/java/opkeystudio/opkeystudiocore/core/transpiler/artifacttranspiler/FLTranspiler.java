package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;

public class FLTranspiler implements Transpiler {

	@Override
	public void transpile(Artifact artifact, File outputFile) {
		if (artifact.getFile_type_enum() != MODULETYPE.Component) {
			return;
		}
	}

}
