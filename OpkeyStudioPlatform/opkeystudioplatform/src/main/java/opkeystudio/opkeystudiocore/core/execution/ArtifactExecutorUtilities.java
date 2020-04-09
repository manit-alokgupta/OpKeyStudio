package opkeystudio.opkeystudiocore.core.execution;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;

public class ArtifactExecutorUtilities {
	private ByteArrayOutputStream standardOutput;
	private ByteArrayOutputStream standardErrorOutput;
	private boolean executionCompleted = false;

	public void executeArtifact(String sessionRootDir, Artifact artifact, String pluginName) {
		String artifactClassName = artifact.getPackageName() + "." + artifact.getVariableName();
		System.out.println(">>Artifact Code Folder " + sessionRootDir);
		System.out.println(">>Executing Artifact " + artifactClassName);
		Thread executionThread = new Thread(new Runnable() {

			@Override
			public void run() {
				setExecutionCompleted(false);
			//	java.io.ByteArrayOutputStream standrdout = new java.io.ByteArrayOutputStream();
		//		java.io.ByteArrayOutputStream errorout = new java.io.ByteArrayOutputStream();
		//		System.setOut(new java.io.PrintStream(standrdout));
		//		System.setErr(new java.io.PrintStream(errorout));
		//		setStandardOutput(standrdout);
		//		setStandardErrorOutput(errorout);
				try {
					execute(sessionRootDir, artifactClassName, pluginName);
				} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
						| InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				setExecutionCompleted(true);
			}
		});
		executionThread.start();
	}

	private void execute(String sessionRootDir, String artifactClassNAME, String pluginName)
			throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<File> allLibs = new CompilerUtilities().getAllAssocitedLibraries(pluginName);
		allLibs.add(new File(sessionRootDir));
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses, ArtifactExecutorUtilities.class.getClassLoader());
		@SuppressWarnings("rawtypes")
		Class classToLoad = Class.forName(artifactClassNAME, true, child);
		Object instance = classToLoad.newInstance();
		Method method = instance.getClass().getDeclaredMethod("execute");
		Object result = method.invoke(instance);
		try {
			child.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isExecutionCompleted() {
		return executionCompleted;
	}

	public void setExecutionCompleted(boolean executionCompleted) {
		this.executionCompleted = executionCompleted;
	}

	public ByteArrayOutputStream getStandardErrorOutput() {
		return standardErrorOutput;
	}

	public void setStandardErrorOutput(ByteArrayOutputStream standardErrorOutput) {
		this.standardErrorOutput = standardErrorOutput;
	}

	public ByteArrayOutputStream getStandardOutput() {
		return standardOutput;
	}

	public void setStandardOutput(ByteArrayOutputStream standardOutput) {
		this.standardOutput = standardOutput;
	}
}
