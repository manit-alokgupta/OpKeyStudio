package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.GlobalTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ORTranspiler extends AbstractTranspiler {
	public ORTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getProjectTranspiledArtifactsFolder());
	}

	@Override
	public void transpile(ArtifactDTO artifact) {
		try {
			if (artifact.getFile_type_enum() != MODULETYPE.ObjectRepository) {
				return;
			}
			File file = createArtifactFile(artifact);
			JavaClassSource classSource = new GlobalTranspiler().getJavaClassORObjects(artifact);
			new TranspilerUtilities().addPackageName(artifact, classSource);
			new TranspilerUtilities().addDefaultImports(classSource);
			new TranspilerUtilities().writeCodeToFile(file, classSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
