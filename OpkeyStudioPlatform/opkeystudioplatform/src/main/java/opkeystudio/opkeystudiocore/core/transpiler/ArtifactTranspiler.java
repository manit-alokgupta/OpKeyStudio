package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.AbstractTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.DRTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.FLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.ORTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.TCTranspiler;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactTranspiler extends AbstractTranspiler {


	public ArtifactTranspiler() {
		setFileExtension(".java");
		setTranspiledDataFolder(Utilities.getInstance().getTranspiledArtifactsFolder());
	}

	public void setPackageProperties() {
		List<Artifact> allArtifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : allArtifacts) {
			if (artifact.getFile_type_enum() == MODULETYPE.Folder) {
				continue;
			}
			int count = 0;
			String packagePath = "";
			String packageName = "";
			Artifact tempArtfact = artifact;
			List<String> variableNames = new ArrayList<String>();
			while (tempArtfact != null) {
				if (count == 0) {
					count++;
					tempArtfact = tempArtfact.getParentArtifact();
					continue;
				}

				variableNames.add(tempArtfact.getVariableName());
				tempArtfact = tempArtfact.getParentArtifact();
			}

			Collections.reverse(variableNames);
			for (String varName : variableNames) {
				if (!packagePath.isEmpty()) {
					packagePath += File.separator;
				}
				if (!packageName.isEmpty()) {
					packageName += ".";
				}
				packagePath += varName;
				packageName += varName;
			}
			artifact.setPackagePath(packagePath.toLowerCase());
			artifact.setPackageName(packageName.toLowerCase());
		}
	}

	public void transpileAllArtifacts() {
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : artifacts) {
			new ORTranspiler().transpile(artifact);
			new DRTranspiler().transpile(artifact);
			new TCTranspiler().transpile(artifact);
			new FLTranspiler().transpile(artifact);
		}
	}
}
