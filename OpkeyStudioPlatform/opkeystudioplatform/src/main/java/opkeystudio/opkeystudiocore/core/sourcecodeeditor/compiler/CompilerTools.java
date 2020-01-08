package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.util.List;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Tools;

public class CompilerTools {
	public void compile(FileNode fileNode) {
		List<FileNode> allFiles = new Tools().getAllFileNodes(fileNode);
		List<FileNode> filteredFiles =new Tools().getAllFileNodes(allFiles, FILE_TYPE.SOURCEFILE);
		for (FileNode sourceFileNode : filteredFiles) {
			if (sourceFileNode.getFileType() == FILE_TYPE.SOURCEFILE) {
				System.out.println("File Path " + sourceFileNode.getFile().getAbsolutePath());
			}
		}
	}
}
