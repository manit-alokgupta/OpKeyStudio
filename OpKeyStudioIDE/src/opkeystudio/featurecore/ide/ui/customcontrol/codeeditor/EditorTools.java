package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.io.File;
import java.io.FileInputStream;
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
	private CodedFunctionView parentCodedFunctionView;

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
		allFiles.addAll(getAllCFLAssociatedLibs());
		allFiles.addAll(getPluginsLibraries(pluginName));
		return allFiles;
	}

	public List<File> getAllCFLAssociatedLibs() {
		String path = getParentCodedFunctionView().getArtifactOpkeyDataLibraryPath();
		return getAllFiles(new File(path), ".jar");
	}

	public String getClassPathOFAllAssociatedLibs(String pluginName) {
		String classPath = "";
		List<File> files = getPluginBaseLibraries();
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
		List<File> pluginBaseLibs = getPluginBaseLibraries();
		pluginBaseLibs.addAll(getAllCFLAssociatedLibs());
		pluginBaseLibs.addAll(getPluginsLibraries(opkeystudio.core.utils.Utilities.getInstance().getPluginName()));
		for (File file : pluginBaseLibs) {
			allClases.addAll(getAllClassNamesFromJar(file.getAbsolutePath()));
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

	private static List<String> alreadyScannedClasses = new ArrayList<String>();

	public void initIntellisense() {
		try {
			System.out.println("Fetching Class Information");
			URLClassLoader classLoader = getURLClassLoaderOfClasses(
					opkeystudio.core.utils.Utilities.getInstance().getPluginName());
			List<String> classNames = getAllClassNameFromAassociatedJar();
			for (String className : classNames) {
				if (alreadyScannedClasses.contains(className)) {
					continue;
				}
				try {
					Class classToLoad = Class.forName(className.replaceAll(".class", ""), true, classLoader);
					String modifiers = Modifier.toString(classToLoad.getModifiers());
					modifiers = modifiers.toLowerCase();
					// if (modifiers.contains("public") || modifiers.contains("interface")) {
					// if (!modifiers.contains("final") && !modifiers.contains("abstract")) {
					parseClass(classToLoad);
					alreadyScannedClasses.add(className);
					// }
					// }

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
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseClass(Class _class) {
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
		Class classToLoad = Class.forName(codedflfile.getName().replaceAll(".class", ""), true, child);
		Object instance = classToLoad.newInstance();
		Method method = instance.getClass().getDeclaredMethod("run");
		Object result = method.invoke(instance);
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
}
