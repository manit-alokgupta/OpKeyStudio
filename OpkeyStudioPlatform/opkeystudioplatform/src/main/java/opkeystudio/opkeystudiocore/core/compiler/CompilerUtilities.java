package opkeystudio.opkeystudiocore.core.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CompilerUtilities {
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
		allFiles.addAll(getPluginsLibraries(pluginName));
		return allFiles;
	}

	private List<File> getPluginRunnerJar(String pluginName) {
		String pluginRunnerDir = Utilities.getInstance().getPluginRunnerDir(pluginName);
		return getAllFiles(new File(pluginRunnerDir), ".jar");
	}

	public String getClassPathOFAllAssociatedLibs(String pluginName) {
		String classPath = "";
		List<File> files = getPluginBaseLibraries();
		files.addAll(getPluginRunnerJar("System"));
		files.addAll(getPluginRunnerJar(pluginName));
		files.addAll(getPluginsLibraries(pluginName));
		for (File file : files) {
			if (!classPath.isEmpty()) {
				classPath += ";";
			}
			classPath += file.getAbsolutePath();
		}
		return classPath;
	}
}
