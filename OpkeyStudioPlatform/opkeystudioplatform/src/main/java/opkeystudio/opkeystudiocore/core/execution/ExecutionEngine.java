package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompilerTools;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;

public class ExecutionEngine {
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

	public void executeRun(FileNode rootFileNode) {
		String path = rootFileNode.getFilePath() + File.separator + "bin";
		System.out.println("Executiong " + path);
		try {
			getMainClass(rootFileNode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMainClass(FileNode rootNode) throws MalformedURLException, ClassNotFoundException {
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
					Class _class = urlClass.loadClass(className);
					try {
						executeMainMethod(_class);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void executeMainMethod(Class _class) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		   final Method method = _class.getMethod("main", String[].class);

		    final Object[] args = new Object[1];
		    method.invoke(null, args);
	}
}
