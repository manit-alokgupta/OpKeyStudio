package opkeystudio.opkeystudiocore.core.execution;

import java.io.File;
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
	}
}
