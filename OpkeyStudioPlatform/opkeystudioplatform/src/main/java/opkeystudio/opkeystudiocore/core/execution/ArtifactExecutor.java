package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactExecutor {
	private String sorceFileExt = ".java";
	private String compileFileExt = ".class";

	public void execute(Artifact artifact) {
		initExecute(artifact);
	}

	private void initExecute(Artifact artifact) {
		String transpiledFilesDir = Utilities.getInstance().getTranspiledArtifactsFolder();
		String sourceFilePath = transpiledFilesDir + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + getSorceFileExt();
		String compileFilePath = transpiledFilesDir + File.separator + artifact.getPackagePath() + File.separator
				+ artifact.getVariableName() + getCompileFileExt();
		System.out.println(sourceFilePath);
	}

	public String getSorceFileExt() {
		return sorceFileExt;
	}

	public void setSorceFileExt(String sorceFileExt) {
		this.sorceFileExt = sorceFileExt;
	}

	public String getCompileFileExt() {
		return compileFileExt;
	}

	public void setCompileFileExt(String compileFileExt) {
		this.compileFileExt = compileFileExt;
	}
}
