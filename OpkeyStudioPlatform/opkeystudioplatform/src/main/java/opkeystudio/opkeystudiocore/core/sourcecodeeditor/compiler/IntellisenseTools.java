package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.utils.ContentAssistData;

public class IntellisenseTools {
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

	public void executeIntelliSense(FileNode rootFileNode) {
		String path = rootFileNode.getFilePath() + File.separator + "bin";
		System.out.println("Executiong " + path);
		try {
			fetchClassInformation(rootFileNode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fetchClassInformation(FileNode rootNode) throws MalformedURLException, ClassNotFoundException {
		ArrayList<String> libPaths = new CompilerTools().getLibrariesPath();
		for (String path : libPaths) {
			System.out.println("Library Path " + path);
		}
		String path = rootNode.getFilePath() + File.separator + "bin" + File.separator;
		URLClassLoader urlClass = new URLClassLoader(new URL[] { new File(path).toURI().toURL() });
		List<File> allFiles = getAllFiles(new File(path));

		String rootPath = path.replaceAll("\\\\", "OPKEY_SLASH");
		for (File file : allFiles) {
			if (file.getName().toLowerCase().endsWith(".class")) {
				String filePath = file.getAbsolutePath();
				filePath = filePath.replaceAll("\\\\", "OPKEY_SLASH");
				filePath = filePath.replaceAll(rootPath, "");
				filePath = filePath.replaceAll("OPKEY_SLASH", ".");
				filePath = filePath.replaceAll(".class", "");

				String className = filePath;
				Class _class = urlClass.loadClass(className);
				buildIntelliSenseData(_class, className);
			}
		}
	}

	private void buildIntelliSenseData(Class _class, String className) {
		Method[] declaredMethods = _class.getDeclaredMethods();
		for (Method method : declaredMethods) {
			String genericString = method.toGenericString();
			System.out.println(genericString);
			if (genericString.contains("public")) {
				if (!genericString.contains("static")) {
					ContentAssistData.getInstance().add(className + "()." + method.getName());
				} else {
					ContentAssistData.getInstance().add(className + "." + method.getName());
				}
			}
		}

		Field[] fields = _class.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.toGenericString());
		}

	}
}
