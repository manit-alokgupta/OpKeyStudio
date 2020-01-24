package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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

	public static List<String> getAllClassNamesFromJar(String crunchifyJarName) {
		List<String> listofClasses = new ArrayList<String>();
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(crunchifyJarName));
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
}
