package opkeystudio.opkeystudiocore.core.execution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ExecutionEngine {
	private static ExecutorService executionThread;

	private List<File> getAllFiles(File rootFile) {
		List<File> allFiles = new ArrayList<File>();
		File[] files = rootFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				allFiles.add(file);
			} else {
				List<File> cfiles = getAllFiles(file);
				allFiles.addAll(cfiles);
			}
		}
		return allFiles;
	}

	private ArrayList<String> getLibrariesPath(FileNode rootFileNode) {
		ArrayList<String> allFiles = new ArrayList<String>();
		File file = new File(rootFileNode.getFilePath() + File.separator + "libs");
		for (File fl : file.listFiles()) {
			allFiles.add(fl.getAbsolutePath());
		}
		return allFiles;
	}

	private String getLibrariesClassPath(FileNode rootFileNode) {
		String data = "";
		ArrayList<String> classPathDatas = getLibrariesPath(rootFileNode);
		for (String classPath : classPathDatas) {
			if (!data.isEmpty()) {
				data += ";";
			}
			data += classPath;
		}
		return data;
	}

	private String getJavaPath() {
		String path = Utilities.getInstance().getDefaultInstallDir() + File.separator + "jdk" + File.separator + "bin"
				+ File.separator + "java.exe";
		if (!new File(path).exists()) {
			return "C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\jdk\\bin\\java.exe";
		}
		return path;

	}

	static Process process = null;

	public void executeRun(FileNode rootFileNode) {
		String path = rootFileNode.getFilePath() + File.separator + "bin";
		System.out.println("Executiong " + path);
		try {
			File mainClassFile = getMainClass(rootFileNode);
			String javaExePath = getJavaPath();
			String mainClassName = mainClassFile.getName().replaceAll(".class", "");
			String parentDirPath = mainClassFile.getParentFile().getAbsolutePath();
			System.out.println("Java Path " + javaExePath);
			System.out.println("Main Class Name " + mainClassName);
			System.out.println("Parent DIR Path " + parentDirPath);
			final String classPathString = getLibrariesClassPath(rootFileNode)+ ";" + parentDirPath;

			Thread processStartThread = new Thread(new Runnable() {

				@Override
				public void run() {
					String[] command = new String[] { javaExePath, "-classpath", classPathString, mainClassName };
					ProcessBuilder pb = new ProcessBuilder(command);
					pb.directory(new File(parentDirPath));
					try {
						process = pb.start();
						readProcessInputStream();
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			processStartThread.start();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readProcessInputStream() throws InterruptedException {
		final Thread ioThread = new Thread() {
			@Override
			public void run() {
				try {
					final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String line = null;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
					reader.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		};
		ioThread.start();
		process.waitFor();
	}

	private File getMainClass(FileNode rootNode) throws MalformedURLException, ClassNotFoundException {
		String path = rootNode.getFilePath() + File.separator + "bin" + File.separator;
		URLClassLoader urlClass = new URLClassLoader(new URL[] { new File(path).toURI().toURL() });
		List<File> allFiles = getAllFiles(new File(path));

		String rootPath = path.replaceAll("\\\\", "OPKEY_SLASH");
		for (File file : allFiles) {
			if (file.getName().toLowerCase().endsWith(".class")) {
				if (file.getName().equals("Main.class")) {
					String filePath = file.getAbsolutePath();
					filePath = filePath.replaceAll("\\\\", "OPKEY_SLASH");
					filePath = filePath.replaceAll(rootPath, "");
					filePath = filePath.replaceAll("OPKEY_SLASH", ".");
					filePath = filePath.replaceAll(".class", "");

					String className = filePath;
					return file;
				}
			}
		}
		return null;
	}

	private void executeMainMethod(Class _class) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		executionThread = Executors.newSingleThreadExecutor();
		executionThread.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Method method;
				try {
					method = _class.getMethod("main", String[].class);
					final Object[] args = new Object[1];
					method.invoke(null, args);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public static ExecutorService getExecutionThread() {
		return executionThread;
	}
}
