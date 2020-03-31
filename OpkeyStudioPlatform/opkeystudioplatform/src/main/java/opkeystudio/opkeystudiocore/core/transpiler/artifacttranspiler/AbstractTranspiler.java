package opkeystudio.opkeystudiocore.core.transpiler.artifacttranspiler;

import java.io.File;
import java.io.IOException;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;

public abstract class AbstractTranspiler {
	private String fileExtension;
	private String transpiledDataFolder;

	public void transpile(Artifact artifact) {
	}

	public File createArtifactFile(Artifact artifact) {
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