package opkeystudio.opkeystudiocore.core.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic.Kind;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;
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

	public List<File> getAllPluginsLibraries() {
		String pluginBaseFolder = Utilities.getInstance().getDefaultPluginsDir();
		return getAllFiles(new File(pluginBaseFolder), ".jar");
	}

	public List<File> getAllAssocitedLibraries(String pluginName) {
		List<File> allFiles = new ArrayList<File>();
		allFiles.addAll(getPluginBaseLibraries());
		allFiles.addAll(getPluginRunnerJar("System"));
		allFiles.addAll(getPluginRunnerJar("Appium"));
		allFiles.addAll(getPluginRunnerJar("Web"));
		allFiles.addAll(getReportingUtilJars());
		if (!pluginName.equals("Web") && !pluginName.equals("Appium")) {
			allFiles.addAll(getPluginRunnerJar(pluginName));
			allFiles.addAll(getPluginsLibraries(pluginName));
		}
		allFiles.addAll(getPluginsLibraries("Appium"));
		allFiles.addAll(getPluginsLibraries("Web"));
		return allFiles;
	}

	private List<File> getReportingUtilJars() {
		File reportingUtil = new File(Utilities.getInstance().getLibrariesFolder(), "ReportingUtil");
		return getAllFiles(reportingUtil, ".jar");
	}

	private List<File> getPluginRunnerJar(String pluginName) {
		String pluginRunnerDir = Utilities.getInstance().getPluginRunnerDir(pluginName);
		return getAllFiles(new File(pluginRunnerDir), ".jar");
	}

	public List<File> getAllPluginRunnerJar() {
		String pluginRunnerDir = Utilities.getInstance().getDefaultPluginRunnerDir();
		return getAllFiles(new File(pluginRunnerDir), ".jar");
	}

	public String getClassPathOFAllAssociatedLibs(String pluginName) {
		String classPath = "";
		List<File> files = getAllAssocitedLibraries(pluginName);

		for (File file : files) {
			if (!classPath.isEmpty()) {
				classPath += ";";
			}
			classPath += file.getAbsolutePath();
		}
		return classPath;
	}

	public String getClassPathOFAllAssociatedLibs_CFL(String pluginName) {
		String classPath = "";
		List<File> files = getAllAssocitedLibraries(pluginName);
		List<File> allCflFiles = getAllFiles(new File(Utilities.getInstance().getProjectJavaLibrrayFolder()), ".jar");
		files.addAll(allCflFiles);
		for (File file : files) {
			if (!classPath.isEmpty()) {
				classPath += ";";
			}
			classPath += file.getAbsolutePath();
		}
		return classPath;
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
}
