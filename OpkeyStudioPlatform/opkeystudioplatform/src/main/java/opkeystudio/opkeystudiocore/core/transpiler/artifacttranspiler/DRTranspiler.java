package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.GlobalTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class DRTranspiler extends AbstractTranspiler {
	public DRTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getTranspiledArtifactsFolder());
	}
	@Override
	public void transpile(Artifact artifact) {
		if (artifact.getFile_type_enum() != MODULETYPE.DataRepository) {
			return;
		}
		File file = createArtifactFile(artifact);
		JavaClassSource classSource = new GlobalTranspiler().getJavaClassDRObjects(artifact);
		new TranspilerUtilities().addPackageName(artifact, classSource);
		new TranspilerUtilities().addDefaultImports(classSource);
		new TranspilerUtilities().writeCodeToFile(file, classSource);
	}
}
