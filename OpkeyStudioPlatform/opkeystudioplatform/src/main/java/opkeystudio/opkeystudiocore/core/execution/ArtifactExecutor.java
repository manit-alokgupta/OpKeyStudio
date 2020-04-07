package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactExecutor {
	private String sorceFileExt = ".java";
	private String compileFileExt = ".class";

	public void execute(ExecutionSession session) {
		initExecute(session);
	}

	private void initExecute(ExecutionSession session) {
		System.out.println(">> Initiating Execution ");
		System.out.println(">>Session Id " + session.getSessionId());
		System.out.println(">>Session Name " + session.getSessionName());
		System.out.println(">>Plugin Name " + session.getPluginName());
		Artifact artifact = session.getArtifact();

		String transpiledFilesDir = Utilities.getInstance().getTranspiledArtifactsFolder();

		String sessionsFolderPath = Utilities.getInstance().getSessionsFolder();

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
