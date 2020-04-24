package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.tools.Diagnostic.Kind;

import org.apache.commons.io.FileUtils;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.compiler.ArtifactCompiler;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ExecutionSessionExecutor {
	private String sorceFileExt = ".java";
	private String compileFileExt = ".class";

	public ArtifactExecutor execute(ExecutionSession session) {
		try {
			return initExecute(session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private ArtifactExecutor initExecute(ExecutionSession session) throws IOException {
		System.out.println(">> Initiating Execution ");
		System.out.println(">>Session Id " + session.getSessionId());
		System.out.println(">>Session Name " + session.getSessionName());
		System.out.println(">>Plugin Name " + session.getPluginName());
		String pluginName = session.getPluginName();
		Artifact artifact = session.getArtifact();

		String transpiledFilesDir = Utilities.getInstance().getTranspiledArtifactsFolder();

		createExecutionSession(session.getSessionName());
		String artifactCodesDirPath = getSessionArtifacCodesFolder(session.getSessionName());

		FileUtils.copyDirectory(new File(transpiledFilesDir), new File(artifactCodesDirPath));
		List<CompileError> compileErrors = new ArtifactCompiler().compileAllArtifacts(artifactCodesDirPath, pluginName);
		compileErrors = new CompilerUtilities().filterErrors(compileErrors, Kind.ERROR);
		if (compileErrors.size() > 0) {
			ArtifactExecutor retObject = new ArtifactExecutor(session);
			retObject.setContainsErrors(true);
			retObject.setCompileErrors(compileErrors);
			return retObject;
		}
		session.setReportFolderDir(getSessionReportFolder(session.getSessionName()));
		return executeArtifact(session, artifactCodesDirPath, artifact, pluginName);
	}

	private ArtifactExecutor executeArtifact(ExecutionSession esession, String sessionRootDir, Artifact artifact,
			String pluginName) {
		ArtifactExecutor executor = new ArtifactExecutor(esession);
		executor.executeArtifact(sessionRootDir, artifact, pluginName);
		return executor;
	}

	private void createExecutionSession(String sessionName) {
		File file = new File(Utilities.getInstance().getSessionsFolder() + File.separator + sessionName);
		if (!file.exists()) {
			file.mkdir();
		}
		File file1 = new File(file.getAbsolutePath() + File.separator + "ArtifactCodes");
		if (!file1.exists()) {
			file1.mkdir();
		}
		File file2 = new File(file.getAbsolutePath() + File.separator + "logs");
		if (!file2.exists()) {
			file2.mkdir();
		}

		File file3 = new File(file.getAbsolutePath() + File.separator + "Reports");
		if (!file3.exists()) {
			file3.mkdir();
		}
	}

	private String getSessionArtifacCodesFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator
				+ "ArtifactCodes";
	}

	private String getSessionLogsFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator + "logs";
	}

	private String getSessionReportFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator + "Reports";
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
