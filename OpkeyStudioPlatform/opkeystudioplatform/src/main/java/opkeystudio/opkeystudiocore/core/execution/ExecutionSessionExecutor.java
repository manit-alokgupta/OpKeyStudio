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
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ExecutionSessionExecutor {
	private String sorceFileExt = ".java";
	private String compileFileExt = ".class";

	public ArtifactExecutor execute(ExecutionSession session) {
		try {
			return initExecute(session);
		} catch (IOException e) {
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

		String transpiledFilesDir = null;
		if (session.getArtifactFilePackageClass() != null) {
			transpiledFilesDir = session.getArtifactCodeDirPath();
		} else {
			transpiledFilesDir = Utilities.getInstance().getProjectTranspiledArtifactsFolder();
		}
		createExecutionSession(session.getSessionName());
		String artifactCodesDirPath = getSessionArtifacCodesFolder(session.getSessionName());

		FileUtils.copyDirectory(new File(transpiledFilesDir), new File(artifactCodesDirPath));
		session.setReportFolderDir(getSessionReportFolder(session.getSessionName()));
		session.setSessionDirectory(getSessionFolder(session.getSessionName()));
		if (session.getArtifactFilePackageClass() != null) {
			String javaFilePath = artifactCodesDirPath + File.separator + session.getArtifactJavaFilePath();
			List<CompileError> compileErrors = new ArtifactCompiler().compileArtifact(artifactCodesDirPath,
					javaFilePath, pluginName);
			compileErrors = new CompilerUtilities().filterErrors(compileErrors, Kind.ERROR);
			if (compileErrors.size() > 0) {
				ArtifactExecutor retObject = new ArtifactExecutor(session);
				retObject.setContainsErrors(true);
				retObject.setCompileErrors(compileErrors);
				return retObject;
			}
			return executeArtifactFile(session, artifactCodesDirPath, session.getArtifactFilePackageClass(),
					pluginName);
		}

		String artifactPackagePath = ArtifactTranspiler.getInstance().getArtifactPackagePath(artifact);
		String artifactCodePath = artifactCodesDirPath + File.separator + artifactPackagePath + File.separator
				+ artifact.getVariableName() + ".java";
		System.out.println("Compiling " + artifactCodePath);
		List<CompileError> compileErrors = new ArtifactCompiler().compileArtifact(artifactCodesDirPath,
				artifactCodePath, pluginName);
		compileErrors = new CompilerUtilities().filterErrors(compileErrors, Kind.ERROR);
		if (compileErrors.size() > 0) {
			ArtifactExecutor retObject = new ArtifactExecutor(session);
			retObject.setContainsErrors(true);
			retObject.setCompileErrors(compileErrors);
			return retObject;
		}
		if (session.isCflArtifact()) {
			return executeCFLArtifact(session, artifactCodesDirPath, artifact, pluginName);
		}
		return executeArtifact(session, artifactCodesDirPath, artifact, pluginName);
	}

	private ArtifactExecutor executeArtifact(ExecutionSession esession, String sessionRootDir, Artifact artifact,
			String pluginName) {
		ArtifactExecutor executor = new ArtifactExecutor(esession);
		executor.executeArtifact(sessionRootDir, artifact, pluginName);
		return executor;
	}

	private ArtifactExecutor executeCFLArtifact(ExecutionSession esession, String sessionRootDir, Artifact artifact,
			String pluginName) {
		ArtifactExecutor executor = new ArtifactExecutor(esession);
		executor.executeCFL(sessionRootDir, artifact, pluginName);
		return executor;
	}

	private ArtifactExecutor executeArtifactFile(ExecutionSession esession, String sessionRootDir,
			String artifactClassName, String pluginName) {
		ArtifactExecutor executor = new ArtifactExecutor(esession);
		executor.executeArtifactFile(sessionRootDir, artifactClassName, pluginName);
		return executor;
	}

	private void createExecutionSession(String sessionName) throws IOException {
		File sessionFolder = new File(Utilities.getInstance().getSessionsFolder());
		if (!sessionFolder.exists())
			sessionFolder.mkdirs();

		File currentSessionFolder = this.getSessionFolder(sessionName);
		if (currentSessionFolder.exists())
			throw new IOException("Session '" + sessionName + "' already exists at " + sessionFolder.getAbsolutePath());
		else
			currentSessionFolder.mkdir();

		File file1 = new File(currentSessionFolder, "ArtifactCodes");
		if (!file1.exists()) {
			file1.mkdir();
		}
		File file2 = new File(currentSessionFolder, "logs");
		if (!file2.exists()) {
			file2.mkdir();
		}

		File file3 = new File(currentSessionFolder, "Reports");
		if (!file3.exists()) {
			file3.mkdir();
		}
	}

	private String getSessionArtifacCodesFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator
				+ "ArtifactCodes";
	}

	public String getSessionLogsFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator + "logs";
	}

	private String getSessionReportFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator + "Reports";
	}

	public File getSessionFolder(String sessionName) {
		return new File(Utilities.getInstance().getSessionsFolder() + File.separator + sessionName);
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
