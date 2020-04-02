package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.io.IOException;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TranspilerUtilities {
	public void writeCodeToFile(File file, JavaClassSource classSource) {
		try {
			Utilities.getInstance().writeToFile(file, classSource.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addDefaultImports(JavaClassSource classSource) {
		for (String packageName : ArtifactTranspiler.getInstance().getAllPackagesNames()) {
			classSource.addImport(packageName + ".*");
		}
	}

	public void addPackageName(Artifact artifact, JavaClassSource classSource) {
		if (artifact.getPackageName() == null) {
			return;
		}
		if (artifact.getPackageName().trim().isEmpty()) {
			return;
		}
		classSource.setPackage(artifact.getPackageName());
	}
}
