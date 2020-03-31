package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.GlobalTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.TranspilerUtilities;

public class ORTranspiler implements Transpiler {

	@Override
	public void transpile(Artifact artifact, File outputFile) {
		if (artifact.getFile_type_enum() != MODULETYPE.ObjectRepository) {
			return;
		}
		JavaClassSource classSource = new GlobalTranspiler().getJavaClassORObjects(artifact);
		new TranspilerUtilities().writeCodeToFile(outputFile, classSource);
	}
}
