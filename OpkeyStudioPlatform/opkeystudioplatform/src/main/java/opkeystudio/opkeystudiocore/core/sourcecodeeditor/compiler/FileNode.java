package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FileNode {
	public enum FILE_TYPE {
		SOURCEFILE, PACKAGEFOLDER, PROJECTFOLDER, FOLDER, LIBRARY, XML
	};

	private String fileName;
	private FILE_TYPE fileType;
	private String fileIdentifier;
	private String data;
	private String filePath;
	private List<FileNode> filesNodes = new ArrayList<FileNode>();

	public FileNode(String fileName, FILE_TYPE fileType) {
		setFileName(fileName);
		setFileType(fileType);
		setFileIdentifier(Utilities.getInstance().getUniqueUUID(""));
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FILE_TYPE getFileType() {
		return fileType;
	}

	public void setFileType(FILE_TYPE fileType) {
		this.fileType = fileType;
	}

	public String getFileIdentifier() {
		return fileIdentifier;
	}

	public void setFileIdentifier(String fileIdentifier) {
		this.fileIdentifier = fileIdentifier;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<FileNode> getFilesNodes() {
		return filesNodes;
	}

	public void setFilesNodes(List<FileNode> filesNodes) {
		this.filesNodes = filesNodes;
	}
}
