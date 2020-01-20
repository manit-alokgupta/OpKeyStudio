package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

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

	public void execute(FileNode rootFileNode) {
		String path = rootFileNode.getFilePath() + File.separator + "bin";
		System.out.println("Executiong " + path);
		List<File> allFiles = getAllFiles(new File(path));
		for (File file : allFiles) {
			System.out.println(file.getName());
		}
		try {
			fetchClassInformation(allFiles);
		} catch (MalformedURLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fetchClassInformation(List<File> classFiles) throws ClassNotFoundException, IOException {
		URL[] classURLS = new URL[classFiles.size()];
		for (int i = 0; i < classFiles.size(); i++) {
			classURLS[i] = classFiles.get(i).toURI().toURL();
		}
		URLClassLoader urlClass = new URLClassLoader(new URL[] {new File("C:\\Users\\neon.nishant\\OpKeyStudio\\workspace\\SourceCodes\\a1ef08f7e3af436bae0ab7d503ab67b9\\bin").toURI().toURL()});

		for (File classFile : classFiles) {
			if (classFile.getName().contains("Test.class")) {
				Class _class = urlClass.loadClass("testcases.Test");
				Method[] declaredMethods = _class.getDeclaredMethods();
				for (Method method : declaredMethods) {
					System.out.println(method.getName());
				}
			}
		}
		urlClass.close();
	}
}
