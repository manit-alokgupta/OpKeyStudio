package opkeystudio.opkeystudiocore.core.transpiler;

import java.io.File;
import java.io.IOException;
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
			if (artifact.getFile_type_enum() == MODULETYPE.Folder) {
				continue;
			}
			File file = createArtifactFile(artifact);
			new ORTranspiler().transpile(artifact, file);
			new DRTranspiler().transpile(artifact, file);
		}
	}

	private File createArtifactFile(Artifact artifact) {
		String filePath = getTranspiledDataFolder() + File.separator + artifact.getPackagePath();
		File file1 = new File(filePath);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		filePath = getTranspiledDataFolder() + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + getFileExtension();
		File file2 = new File(filePath);
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file2;
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
