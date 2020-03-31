package opkeystudio.opkeystudiocore.core.transpiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactTranspiler {
	private String fileExtension;
	private String transpiledDataFolder;

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
			String packagePath = "";
			String packageName = "";
			Artifact tempArtfact = artifact;
			List<String> variableNames = new ArrayList<String>();
			while (tempArtfact != null) {
				variableNames.add(tempArtfact.getVariableName());
				tempArtfact = tempArtfact.getParentArtifact();
			}

			Collections.reverse(variableNames);
			for (String varName : variableNames) {
				if (!packagePath.isEmpty()) {
					packagePath += "\\";
				}
				if (!packageName.isEmpty()) {
					packageName += ".";
				}
				packagePath += varName;
				packageName += varName;
			}
			artifact.setPackagePath(packagePath);
			artifact.setPackageName(packageName);
		}
	}

	public void transpileAllArtifacts() {
		List<Artifact> artifacts = GlobalLoader.getInstance().getAllArtifacts();
		for (Artifact artifact : artifacts) {
			System.out.println("Artifact Package Path " + artifact.getPackagePath() + "   " + artifact.getName());
			System.out.println("Artifact Package Name " + artifact.getPackageName() + "   " + artifact.getName());
		}
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getTranspiledDataFolder() {
		return transpiledDataFolder;
	}

	public void setTranspiledDataFolder(String transpiledDataFolder) {
		this.transpiledDataFolder = transpiledDataFolder;
	}
}
