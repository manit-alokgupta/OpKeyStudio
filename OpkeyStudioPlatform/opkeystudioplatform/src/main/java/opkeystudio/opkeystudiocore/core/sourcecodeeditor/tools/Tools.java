package opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;

public class Tools {

	public ArrayList<File> getFiles(ArrayList<FileNode> fileNodes, FILE_TYPE filetype) {
		ArrayList<File> allFiles = new ArrayList<File>();
		for (FileNode fn : fileNodes) {
			if (fn.getFileType() == filetype) {
				allFiles.add(fn.getFile());
			}
		}
		return allFiles;
	}

	private ArrayList<FileNode> getFileNodes(FileNode node) {
		ArrayList<FileNode> allFiles = new ArrayList<FileNode>();
		for (FileNode fn : node.getFilesNodes()) {
			allFiles.add(fn);
			ArrayList<FileNode> filenodes = getFileNodes(fn);
			allFiles.addAll(filenodes);
		}
		return allFiles;
	}

	public ArrayList<FileNode> getAllFileNodes(FileNode fileNode) {
		ArrayList<FileNode> allfiles = new ArrayList<FileNode>();
		for (FileNode fn : fileNode.getFilesNodes()) {
			allfiles.add(fn);
			ArrayList<FileNode> files = getFileNodes(fn);
			allfiles.addAll(files);
		}
		return allfiles;
	}

	public ArrayList<FileNode> getAllFileNodes(List<FileNode> allfileNode, FILE_TYPE fileType) {
		ArrayList<FileNode> filteredFiles = new ArrayList<FileNode>();
		List<FileNode> fileNodes = allfileNode;
		for (FileNode fn : fileNodes) {
			if (fn.getFileType() == fileType) {
				filteredFiles.add(fn);
			}
		}
		return filteredFiles;
	}

	public String removeSpecialCharacters(String str) {
		return str.replaceAll("[^a-zA-Z0-9\\s+]", "");
	}
}
