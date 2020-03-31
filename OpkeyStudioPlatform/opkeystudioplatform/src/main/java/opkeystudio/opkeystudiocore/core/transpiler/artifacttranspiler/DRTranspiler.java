package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.GlobalTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;

public class DRTranspiler extends AbstractTranspiler {

	@Override
	public void transpile(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.DataRepository) {
			return;
		}
		File file = createArtifactFile(artifact);
		JavaClassSource classSource = new GlobalTranspiler().getJavaClassDRObjects(artifact);
		new TranspilerUtilities().writeCodeToFile(file, classSource);
	}
}
