package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import com.opkeystudio.core.sessions.SessionInfo;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.transpiler.ArtifactTranspiler;
import pcloudystudio.core.vncutils.AndroidVNCUtil;

public class ArtifactExecutor {

	private ExecutionSession executionSession;

	private File outLogFile = null;
	private File errLogFile = null;

	private URLClassLoader classLoader;
	private boolean executionCompleted = false;
	private List<CompileError> compileErrors = new ArrayList<CompileError>();
	private boolean containsErrors;
	private Thread executionThread;

	public ArtifactExecutor(ExecutionSession esession) {
		this.setExecutionSession(esession);
	}

	public void executeArtifact(String sessionRootDir, Artifact artifact, String pluginName) {
		String artifactClassName = ArtifactTranspiler.getInstance().getArtifactPackageName(artifact) + "."
				+ artifact.getVariableName();
		System.out.println(">>Artifact Code Folder " + sessionRootDir);
		System.out.println(">>Executing Artifact " + artifactClassName);
		Thread executionThread = new Thread(new Runnable() {

			@Override
			public void run() {
				setExecutionCompleted(false);
				outLogFile = new File(executionSession.getSessionLogDirectory(), "SessionLogs_out.txt");
				errLogFile = new File(executionSession.getSessionLogDirectory(), "SessionLogs_err.txt");
				try {
					outLogFile.getParentFile().mkdirs();
					outLogFile.createNewFile();
					errLogFile.createNewFile();

					OutputStream standrdout = new java.io.FileOutputStream(outLogFile, true);
					OutputStream errorout = new java.io.FileOutputStream(errLogFile, true);

					System.setOut(new java.io.PrintStream(standrdout, true));
					System.setErr(new java.io.PrintStream(errorout, true));

				} catch (IOException e2) {
					e2.printStackTrace();
				}

				try {
					execute(sessionRootDir, artifactClassName, pluginName);
				} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
						| InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					try {
						callExecuteSessionEnd();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| NoSuchMethodException | SecurityException | IllegalArgumentException
							| InvocationTargetException e1) {
					}
				}
				setExecutionCompleted(true);
			}
		});
		executionThread.start();
		setExecutionThread(executionThread);
	}

	public void executeCFL(String sessionRootDir, Artifact artifact, String pluginName) {
		String artifactClassName = ArtifactTranspiler.getInstance().getArtifactPackageName(artifact) + "."
				+ artifact.getVariableName();
		System.out.println(">>Artifact Code Folder " + sessionRootDir);
		System.out.println(">>Executing Artifact " + artifactClassName);
		Thread executionThread = new Thread(new Runnable() {

			@Override
			public void run() {
				setExecutionCompleted(false);
				outLogFile = new File(executionSession.getSessionLogDirectory(), "SessionLogs_out.txt");
				errLogFile = new File(executionSession.getSessionLogDirectory(), "SessionLogs_err.txt");
				try {
					outLogFile.getParentFile().mkdirs();
					outLogFile.createNewFile();
					errLogFile.createNewFile();

					OutputStream standrdout = new java.io.FileOutputStream(outLogFile, true);
					OutputStream errorout = new java.io.FileOutputStream(errLogFile, true);

					System.setOut(new java.io.PrintStream(standrdout, true));
					System.setErr(new java.io.PrintStream(errorout, true));

				} catch (IOException e2) {
					e2.printStackTrace();
				}

				try {
					executeCFL(sessionRootDir, artifactClassName, pluginName);
				} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
						| InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					try {
						callExecuteSessionEnd();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| NoSuchMethodException | SecurityException | IllegalArgumentException
							| InvocationTargetException e1) {
					}
				}
				setExecutionCompleted(true);
			}
		});
		executionThread.start();
		setExecutionThread(executionThread);
	}

	public void executeArtifactFile(String sessionRootDir, String artifactClassName, String pluginName) {
		System.out.println(">>Artifact Code Folder " + sessionRootDir);
		System.out.println(">>Executing Artifact " + artifactClassName);
		Thread executionThread = new Thread(new Runnable() {

			@Override
			public void run() {
				setExecutionCompleted(false);
				outLogFile = new File(executionSession.getSessionLogDirectory(), "SessionLogs_out.txt");
				errLogFile = new File(executionSession.getSessionLogDirectory(), "SessionLogs_err.txt");
				try {
					outLogFile.getParentFile().mkdirs();
					outLogFile.createNewFile();
					errLogFile.createNewFile();

					OutputStream standrdout = new java.io.FileOutputStream(outLogFile, true);
					OutputStream errorout = new java.io.FileOutputStream(errLogFile, true);

					System.setOut(new java.io.PrintStream(standrdout, true));
					System.setErr(new java.io.PrintStream(errorout, true));

				} catch (IOException e2) {
					e2.printStackTrace();
				}

				try {
					executeMainFunction(sessionRootDir, artifactClassName, pluginName);
				} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
						| InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					try {
						callExecuteSessionEnd();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| NoSuchMethodException | SecurityException | IllegalArgumentException
							| InvocationTargetException e1) {
					}
				}
				setExecutionCompleted(true);
			}
		});
		executionThread.start();
		setExecutionThread(executionThread);
	}

	@SuppressWarnings("rawtypes")
	private void execute(String sessionRootDir, String artifactClassNAME, String pluginName)
			throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<File> allLibs = new CompilerUtilities().getAllAssocitedLibraries(pluginName);
		allLibs.add(new File(sessionRootDir));
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses);
		setClassLoader(child);

		callExecuteSessionStart();
		Class classToLoad = Class.forName(artifactClassNAME, true, child);
		Object instance = classToLoad.newInstance();
		Method[] methods = instance.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals("execute")) {
				try {
					method.invoke(instance);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (method.getName().equals("executeDefault")) {
				try {
					method.invoke(instance);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		callExecuteSessionEnd();
		cleanExecutionSession();
	}

	@SuppressWarnings("rawtypes")
	private void executeCFL(String sessionRootDir, String artifactClassNAME, String pluginName)
			throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<File> allLibs = new CompilerUtilities().getAllAssocitedLibraries(pluginName);
		allLibs.add(new File(sessionRootDir));
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses);
		setClassLoader(child);

		callExecuteSessionStart();
		Class classToLoad = Class.forName(artifactClassNAME, true, child);
		Object instance = classToLoad.newInstance();
		Method[] methods = instance.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals("run")) {
				try {
					method.invoke(instance);
					break;
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
			if (method.getName().equals("executeDefault")) {
				try {
					method.invoke(instance);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		callExecuteSessionEnd();
		cleanExecutionSession();
	}

	private void executeMainFunction(String sessionRootDir, String artifactClassNAME, String pluginName)
			throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<File> allLibs = new CompilerUtilities().getAllAssocitedLibraries(pluginName);
		allLibs.add(new File(sessionRootDir));
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses);
		setClassLoader(child);

		callExecuteSessionStart();

		Class<?> classToLoad = Class.forName(artifactClassNAME, true, child);
		Method method = classToLoad.getDeclaredMethod("main", String[].class);
		final Object[] args = new Object[1];
		args[0] = new String[] {};

		method.invoke(classToLoad, args);

		callExecuteSessionEnd();
		cleanExecutionSession();
	}

	private SessionInfo getSessionInfo() {
		SessionInfo sinfo = new SessionInfo();
		ExecutionSession session = getExecutionSession();
		sinfo.setMobileCapabilities(session.getMobileCapabilities());
		sinfo.setSessionName(session.getSessionName());
		sinfo.setPluginSettings(session.getPluginSettings());
		sinfo.setMobileDevice(session.getMobileDevice());
		sinfo.setDefaultPluginLocation("");
		sinfo.buildName = session.getBuildName();
		sinfo.sessionDirectory = session.getSessionDirectory().getAbsolutePath();
		sinfo.setReportFilePath(session.getReportFolderDir() + File.separator + "Report.html");
		return sinfo;
	}

	@SuppressWarnings("rawtypes")
	private void callExecuteSessionStart()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		SessionInfo info = getSessionInfo();
		String pluginName = getExecutionSession().getPluginName().toLowerCase();
		URLClassLoader classLoader = getClassLoader();
		Class classToLoad = Class.forName("com.opkey." + pluginName + ".sessions.SessionHandler", true, classLoader);
		Object instance = classToLoad.newInstance();
		Method method = instance.getClass().getDeclaredMethod("beforeSessionStart", Object.class);
		method.invoke(instance, info);
	}

	@SuppressWarnings("rawtypes")
	private void callExecuteSessionEnd() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		SessionInfo info = getSessionInfo();
		String pluginName = getExecutionSession().getPluginName().toLowerCase();
		URLClassLoader classLoader = getClassLoader();
		Class classToLoad = Class.forName("com.opkey." + pluginName + ".sessions.SessionHandler", true, classLoader);
		Object instance = classToLoad.newInstance();
		Method method = instance.getClass().getDeclaredMethod("afterSessionEnds", Object.class);
		method.invoke(instance, info);

		AndroidVNCUtil.getInstance().closeOpenVncViewer();
	}

	public void stopExecutionSession() {
		try {

			callExecuteSessionEnd();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cleanExecutionSession() {
		try {
			getClassLoader().close();
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
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));

	}

	public File getOutLogFile() {
		return outLogFile;
	}

	public File getErrLogFile() {
		return errLogFile;
	}

	public URLClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(URLClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public List<CompileError> getCompileErrors() {
		return compileErrors;
	}

	public void setCompileErrors(List<CompileError> compileErrors) {
		this.compileErrors = compileErrors;
	}

	public boolean isContainsErrors() {
		return containsErrors;
	}

	public void setContainsErrors(boolean containsErrors) {
		this.containsErrors = containsErrors;
	}

	public Thread getExecutionThread() {
		return executionThread;
	}

	public void setExecutionThread(Thread executionThread) {
		this.executionThread = executionThread;
	}

	public ExecutionSession getExecutionSession() {
		return executionSession;
	}

	public void setExecutionSession(ExecutionSession executionSession) {
		this.executionSession = executionSession;
	}

}
