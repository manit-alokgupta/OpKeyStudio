package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.management.RuntimeErrorException;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.AutoCompleteToken;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.EditorTools;
import opkeystudio.featurecore.ide.ui.ui.ArtifactCodeView;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;

public class IntellisenseProcessor {
	private ArtifactCodeView parentCodeView;
	private static List<String> alreadyScannedClasses = new ArrayList<String>();

	public IntellisenseProcessor(ArtifactCodeView parentCodeView) {
		this.setParentCodeView(parentCodeView);
	}

	public void initIntellisense(boolean requireInIt) {
		try {
			if (requireInIt) {
				ArtifactCodeCompletionProvider.getInstance(getParentCodeView()).clearAutoCompleteToken();
				ArtifactCodeCompletionProvider.getInstance(getParentCodeView()).reinitProvider();
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
		ArtifactCodeCompletionProvider.getInstance(getParentCodeView()).addAutoCompleteToken(token);
		ArtifactCodeCompletionProvider.getInstance(getParentCodeView()).createConstructorIntellisense(_class);
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

	public List<String> getAllClassNameFromAassociatedJar() {
		ArrayList<String> allClases = new ArrayList<String>();

		List<File> pluginsLibrary = new CompilerUtilities().getAllPluginRunnerJar();
		pluginsLibrary.addAll(new CompilerUtilities().getPluginBaseLibraries());
		for (File file : pluginsLibrary) {
			List<String> classNames = getAllClassNamesFromJar(file.getAbsolutePath());
			for (String className : classNames) {
				if (className.contains("org.openqa") || className.contains("java.lang")
						|| className.contains("java.util") || className.contains("java.io")
						|| className.contains("com.opkey")) {
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

	public List<File> getAllAssocitedLibraries(String pluginName) {
		List<File> allFiles = new ArrayList<File>();
		allFiles.addAll(new CompilerUtilities().getPluginBaseLibraries());
		allFiles.addAll(new CompilerUtilities().getAllPluginRunnerJar());
		return allFiles;
	}

	public ArtifactCodeView getParentCodeView() {
		return parentCodeView;
	}

	public void setParentCodeView(ArtifactCodeView parentCodeView) {
		this.parentCodeView = parentCodeView;
	}
}
