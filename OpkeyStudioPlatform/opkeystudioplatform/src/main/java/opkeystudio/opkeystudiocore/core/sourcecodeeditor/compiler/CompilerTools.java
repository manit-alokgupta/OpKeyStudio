package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.util.List;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;

public class CompilerTools {
	public FileNode createPackageHierarchy(String folderPath, String projectName, List<FileNode> fileNodes) {
		for (FileNode fileNode : fileNodes) {
			fileNode.setParentPath(folderPath);
		}
		FileNode fileNode = new FileNode(projectName, FILE_TYPE.PROJECTFOLDER);
		fileNode.setFilePath(folderPath);
		return fileNode;
	}

	private List<FileNode> getAllNodes(FileNode fileNode) {
		List<FileNode> fileNodes = fileNode.getFilesNodes();
		
		return null;
	}
}
