package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class IntelliSenseTools {
	private static List<File> getAllFiles(File rootFile, String extension) {
		List<File> allFiles = new ArrayList<File>();
		File[] files = rootFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				allFiles.addAll(getAllFiles(file, extension));
			} else {
				if (file.getName().toLowerCase().endsWith(extension.toLowerCase())) {
					allFiles.add(file);
				}
			}
		}
		return allFiles;
	}

	public static List<File> getPluginBaseLibraries() {
		String pluginBaseFolder = Utilities.getInstance().getDefaultPluginBaseDir();
		File file = new File(pluginBaseFolder);
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

	public static void main(String[] args) {
		List<String> allCasses = getAllClassNamesFromJar(
				"C:\\Program Files (x86)\\Java\\jdk1.8.0_231\\jre\\lib\\rt.jar");
		for (String className : allCasses) {
			System.out.println(className);
		}
	}
}
