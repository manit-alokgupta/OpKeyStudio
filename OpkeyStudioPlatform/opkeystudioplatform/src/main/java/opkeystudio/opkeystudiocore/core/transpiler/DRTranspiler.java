package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;

public class DRTranspiler {
	public void getJavaClassOfDataRepository(Artifact artifact, File outputFile) {
		if (artifact.getFile_type_enum() != MODULETYPE.DataRepository) {
			return;
		}
		JavaClassSource classSource = new GlobalTranspiler().getJavaClassDRObjects(artifact);
		new TranspilerUtilities().writeCodeToFile(outputFile, classSource);
	}
}
