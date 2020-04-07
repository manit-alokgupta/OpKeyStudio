package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.DRTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.FLTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.GlobalVariablesTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.ORTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.SuiteTranspiler;
import opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler.TCTranspiler;

public class ArtifactTranspiler {

	private Set<String> allPackagesNames = new HashSet<String>();
	private static ArtifactTranspiler artifactTranspiler;

	public static ArtifactTranspiler getInstance() {
		if (artifactTranspiler == null) {
			artifactTranspiler = new ArtifactTranspiler();
		}
		return artifactTranspiler;
	}

	public void setPackageProperties() {
		ArtifactTranspiler.getInstance().getAllPackagesNames().clear();
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
			ArtifactTranspiler.getInstance().addPackageName(artifact.getPackageName());
		}
	}

	public void transpileAllArtifacts() {
		new GlobalVariablesTranspiler().transpile();
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : artifacts) {
			new ORTranspiler().transpile(artifact);
			new DRTranspiler().transpile(artifact);
			new TCTranspiler().transpile(artifact);
			new FLTranspiler().transpile(artifact);
			new SuiteTranspiler().transpile(artifact);
		}
	}

	public Set<String> getAllPackagesNames() {
		return allPackagesNames;
	}

	public void addPackageName(String packageName) {
		this.allPackagesNames.add(packageName);
	}

	public void setAllPackagesNames(Set<String> allPackagesNames) {
		this.allPackagesNames = allPackagesNames;
	}
}
