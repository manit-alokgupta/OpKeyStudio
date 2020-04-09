package opkeystudio.opkeystudiocore.core.execution;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.compiler.ArtifactCompiler;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactExecutor {
	private String sorceFileExt = ".java";
	private String compileFileExt = ".class";

	public void execute(ExecutionSession session) {
		try {
			initExecute(session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initExecute(ExecutionSession session) throws IOException {
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
		new ArtifactCompiler().compileAllArtifacts(artifactCodesDirPath, pluginName);
		executeArtifact(artifactCodesDirPath, artifact, pluginName);
	}

	private void executeArtifact(String sessionRootDir, Artifact artifact, String pluginName) {
		ArtifactExecutorUtilities executor = new ArtifactExecutorUtilities();
		executor.executeArtifact(sessionRootDir, artifact, pluginName);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ByteArrayOutputStream standardOutPut = executor.getStandardOutput();
					ByteArrayOutputStream standardErrorOutput = executor.getStandardErrorOutput();
					String consoleOutPut = standardOutPut.toString() + System.lineSeparator()
							+ standardErrorOutput.toString();
					System.out.println(">>Logs " + consoleOutPut);
					System.out.println(">>Execution Status " + executor.isExecutionCompleted());
					if (executor.isExecutionCompleted()) {
						break;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		// thread.start();
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
	}

	private String getSessionArtifacCodesFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator
				+ "ArtifactCodes";
	}

	private String getSessionLogsFolder(String sessionName) {
		return Utilities.getInstance().getSessionsFolder() + File.separator + sessionName + File.separator + "logs";
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
