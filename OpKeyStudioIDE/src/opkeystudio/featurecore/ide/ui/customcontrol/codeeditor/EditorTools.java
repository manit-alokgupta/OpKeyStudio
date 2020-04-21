package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.management.RuntimeErrorException;
import javax.tools.Diagnostic.Kind;

import org.jboss.forge.roaster.Roaster;

import opkeystudio.featurecore.ide.ui.ui.CodedFunctionView;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class EditorTools {
	private ByteArrayOutputStream standardOutput;
	private ByteArrayOutputStream standardErrorOutput;
	private CodedFunctionView parentCodedFunctionView;
	private boolean executionCompleted = false;
	private static List<String> alreadyScannedClasses = new ArrayList<String>();

	public EditorTools() {
	}

	public EditorTools(CodedFunctionView codedFuntionView) {
		this.setParentCodedFunctionView(codedFuntionView);
	}

	public List<File> getAllFiles(File rootFile, String extension) {
		List<File> allFiles = new ArrayList<File>();
		File[] files = rootFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if (file.getName().toLowerCase().endsWith(extension.toLowerCase())) {
					allFiles.add(file);
				}
			} else if (file.isDirectory()) {
				allFiles.addAll(getAllFiles(file, extension));
			}
		}
		return allFiles;
	}

	public List<File> getPluginBaseLibraries() {
		String pluginBaseFolder = Utilities.getInstance().getDefaultPluginBaseDir();
		File file = new File(pluginBaseFolder);
		return getAllFiles(file, ".jar");
	}

	public List<File> getPluginsLibraries(String pluginName) {
		String pluginBaseFolder = Utilities.getInstance().getDefaultPluginsDir() + File.separator + pluginName;
		File file = new File(pluginBaseFolder);
		if (!file.exists()) {
			return new ArrayList<File>();
		}
		return getAllFiles(file, ".jar");
	}

	public List<File> getAllAssocitedLibraries(String pluginName) {
		List<File> allFiles = new ArrayList<File>();
		allFiles.addAll(getPluginBaseLibraries());
		allFiles.addAll(getAllCFLOpKeyLibs());
		allFiles.addAll(getAllCFLAssociatedLibs());
		allFiles.addAll(getPluginsLibraries(pluginName));
		return allFiles;
	}

	public List<File> getAllCFLOpKeyLibs() {
		String path = getParentCodedFunctionView().getArtifactOpkeyDataLibraryPath();
		System.out.println("Parent Path " + path);
		return getAllFiles(new File(path), ".jar");
	}

	public List<File> getAllCFLAssociatedLibs() {
		String path = getParentCodedFunctionView().getArtifactAssociatedLibraryPath();
		return getAllFiles(new File(path), ".jar");
	}

	public String getClassPathOFAllAssociatedLibs(String pluginName) {
		String classPath = "";
		List<File> files = getPluginBaseLibraries();
		files.addAll(getAllCFLOpKeyLibs());
		files.addAll(getAllCFLAssociatedLibs());
		files.addAll(getPluginsLibraries(pluginName));
		for (File file : files) {
			if (!classPath.isEmpty()) {
				classPath += ";";
			}
			classPath += file.getAbsolutePath();
		}
		return classPath;
	}

	public List<String> getAllClassNameFromAassociatedJar() {
		ArrayList<String> allClases = new ArrayList<String>();
		List<File> pluginBaseLibs = new ArrayList<File>();
		pluginBaseLibs.addAll(getAllCFLOpKeyLibs());
		pluginBaseLibs.addAll(getAllCFLAssociatedLibs());
		for (File file : pluginBaseLibs) {
			allClases.addAll(getAllClassNamesFromJar(file.getAbsolutePath()));
		}

		List<File> pluginsLibrary = getPluginsLibraries(opkeystudio.core.utils.Utilities.getInstance().getPluginName());
		pluginsLibrary.addAll(getPluginBaseLibraries());
		for (File file : pluginsLibrary) {
			List<String> classNames = getAllClassNamesFromJar(file.getAbsolutePath());
			for (String className : classNames) {
				if (className.contains("org.openqa") || className.contains("java.lang")
						|| className.contains("java.util") || className.contains("java.io")
						|| className.contains("com.opkeystudio")) {
					if (!alreadyScannedClasses.contains(className)) {
						allClases.add(className);
						alreadyScannedClasses.add(className);
					}
				}
			}
		}
		return allClases;
	}

	public List<String> getAllClassNamesFromJar(String jarName) {
		List<String> listofClasses = new ArrayList<String>();
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if ((jarEntry.getName().toLowerCase().endsWith(".class"))) {
					String className = jarEntry.getName().replaceAll("/", "\\.");
					String myClass = className.substring(0, className.lastIndexOf('.'));
					listofClasses.add(myClass);
				}
			}
			jarFile.close();
		} catch (Exception e) {

		}
		return listofClasses;
	}

	public void initIntellisense(boolean requireInIt) {
		try {
			if (requireInIt) {
				CodeCompletionProvider.getInstance(getParentCodedFunctionView()).clearAutoCompleteToken();
				CodeCompletionProvider.getInstance(getParentCodedFunctionView()).reinitProvider();
				alreadyScannedClasses.clear();
			}
			System.out.println("Fetching Class Information");
			URLClassLoader classLoader = getURLClassLoaderOfClasses(
					opkeystudio.core.utils.Utilities.getInstance().getPluginName());
			List<String> classNames = getAllClassNameFromAassociatedJar();
			for (String className : classNames) {
				if (className.contains("$")) {
					continue;
				}
				try {
					@SuppressWarnings("rawtypes")
					Class classToLoad = Class.forName(className.replaceAll(".class", ""), true, classLoader);
					String modifiers = Modifier.toString(classToLoad.getModifiers());
					modifiers = modifiers.toLowerCase();
					parseClass(classToLoad);

				} catch (NoClassDefFoundError e) {
					// TODO: handle exception
				} catch (IncompatibleClassChangeError e) {
					// TODO: handle exception
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
				} catch (UnsupportedClassVersionError e) {
					// TODO: handle exception
				} catch (RuntimeErrorException e) {
					// TODO: handle exception
				} catch (RuntimeException e) {
					// TODO: handle exception
				} catch (ExceptionInInitializerError e) {
					// TODO: handle exception
				} catch (UnsatisfiedLinkError e) {
					// TODO: handle exception
				} catch (LinkageError e) {
					// TODO: handle exception
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseClass(@SuppressWarnings("rawtypes") Class _class) {
		AutoCompleteToken token = new AutoCompleteToken(_class);
		CodeCompletionProvider.getInstance(getParentCodedFunctionView()).addAutoCompleteToken(token);
		CodeCompletionProvider.getInstance(getParentCodedFunctionView()).createConstructorIntellisense(_class);
	}

	public String formatJavaSourceCode(String javaCode) {
		try {
			String formattedCode = Roaster.format(javaCode);
			return formattedCode;
		} catch (Exception e) {
			return javaCode;
		}
	}

	public void executeCodedFl(String codedString, String pluginName) {

		Thread executionThread = new Thread(new Runnable() {

			@Override
			public void run() {
				setExecutionCompleted(false);
				java.io.ByteArrayOutputStream standrdout = new java.io.ByteArrayOutputStream();
				java.io.ByteArrayOutputStream errorout = new java.io.ByteArrayOutputStream();
				System.setOut(new java.io.PrintStream(standrdout));
				System.setErr(new java.io.PrintStream(errorout));
				setStandardOutput(standrdout);
				setStandardErrorOutput(errorout);
				try {
					execute(codedString, pluginName);
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

	public URLClassLoader getURLClassLoaderOfClasses(String pluginName) throws MalformedURLException {
		List<File> allLibs = getAllAssocitedLibraries(pluginName);
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses, EditorTools.class.getClassLoader());
		return child;
	}

	private void execute(String codedFLFileName, String pluginName)
			throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<File> allLibs = getAllAssocitedLibraries(pluginName);
		File codedflfile = new File(codedFLFileName);
		allLibs.add(codedflfile.getParentFile());
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses, EditorTools.class.getClassLoader());
		@SuppressWarnings("rawtypes")
		Class classToLoad = Class.forName(codedflfile.getName().replaceAll(".class", ""), true, child);
		Object instance = classToLoad.newInstance();
		Method method = instance.getClass().getDeclaredMethod("run");
		@SuppressWarnings("unused")
		Object result = method.invoke(instance);
		try {
			child.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CompileError> filterErrors(List<CompileError> errors, Kind kind) {
		List<CompileError> filteredErrors = new ArrayList<CompileError>();
		for (CompileError ce : errors) {
			if (ce.getKind() == kind) {
				filteredErrors.add(ce);
			}
		}
		return filteredErrors;
	}

	public CodedFunctionView getParentCodedFunctionView() {
		return parentCodedFunctionView;
	}

	public void setParentCodedFunctionView(CodedFunctionView parentCodedFunctionView) {
		this.parentCodedFunctionView = parentCodedFunctionView;
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

	public boolean isExecutionCompleted() {
		return executionCompleted;
	}

	public void setExecutionCompleted(boolean executionCompleted) {
		this.executionCompleted = executionCompleted;
	}
}
