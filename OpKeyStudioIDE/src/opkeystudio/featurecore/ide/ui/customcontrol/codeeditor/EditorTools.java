package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.tools.Diagnostic.Kind;

import org.jboss.forge.roaster.Roaster;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class EditorTools {
	private static List<File> getAllFiles(File rootFile, String extension) {
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

	public static List<File> getPluginBaseLibraries() {
		String pluginBaseFolder = Utilities.getInstance().getDefaultPluginBaseDir();
		File file = new File(pluginBaseFolder);
		return getAllFiles(file, ".jar");
	}

	public static List<File> getPluginsLibraries(String pluginName) {
		String pluginBaseFolder = Utilities.getInstance().getDefaultPluginsDir() + File.separator + pluginName;
		File file = new File(pluginBaseFolder);
		if (!file.exists()) {
			return new ArrayList<File>();
		}
		return getAllFiles(file, ".jar");
	}

	public static List<File> getAllAssocitedLibraries(String pluginName) {
		List<File> allFiles = new ArrayList<File>();
		allFiles.addAll(getPluginBaseLibraries());
		allFiles.addAll(getPluginsLibraries(pluginName));
		return allFiles;
	}

	public static String getClassPathOFAllAssociatedLibs(String pluginName) {
		String classPath = "";
		List<File> files = getPluginBaseLibraries();
		files.addAll(getPluginsLibraries(pluginName));
		for (File file : files) {
			if (!classPath.isEmpty()) {
				classPath += ";";
			}
			classPath += file.getAbsolutePath();
		}
		return classPath;
	}

	public static List<String> getAllClassNameFromAassociatedJar() {
		ArrayList<String> allClases = new ArrayList<String>();
		List<File> pluginBaseLibs = getPluginBaseLibraries();
		pluginBaseLibs.addAll(getPluginsLibraries(opkeystudio.core.utils.Utilities.getInstance().getPluginName()));
		for (File file : pluginBaseLibs) {
			allClases.addAll(getAllClassNamesFromJar(file.getAbsolutePath()));
		}
		return allClases;
	}

	public static List<String> getAllClassNamesFromJar(String jarName) {
		List<String> listofClasses = new ArrayList<String>();
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if ((jarEntry.getName().endsWith(".class"))) {
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

	public static String formatJavaSourceCode(String javaCode) {
		try {
			String formattedCode = Roaster.format(javaCode);
			return formattedCode;
		} catch (Exception e) {
			return javaCode;
		}
	}

	public static void executeCodedFl(String codedString, String pluginName) {
		try {
			execute(codedString, pluginName);
		} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
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

	private static void execute(String codedFLFileName, String pluginName)
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
		Class classToLoad = Class.forName(codedflfile.getName().replaceAll(".class", ""), true, child);
		Object instance = classToLoad.newInstance();
		Method method = instance.getClass().getDeclaredMethod("run");
		Object result = method.invoke(instance);
	}

	public static List<CompileError> filterErrors(List<CompileError> errors, Kind kind) {
		List<CompileError> filteredErrors = new ArrayList<CompileError>();
		for (CompileError ce : errors) {
			if (ce.getKind() == kind) {
				filteredErrors.add(ce);
			}
		}
		return filteredErrors;
	}
}
