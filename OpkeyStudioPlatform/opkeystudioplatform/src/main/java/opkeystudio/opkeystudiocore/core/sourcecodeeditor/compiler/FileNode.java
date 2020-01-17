package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Tools;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FileNode extends Modified {
	public enum FILE_TYPE {
		JAVASOURCEFILE, PACKAGEFOLDER, PROJECTFOLDER, FOLDER, LIBRARY, XML
	};

	private String fileName;
	private FILE_TYPE fileType;
	private String fileIdentifier;
	private String data;
	private String filePath;
	private String parentPath;
	private String className;
	private List<FileNode> filesNodes = new ArrayList<FileNode>();
	private List<CompileError> compileErrors = new ArrayList<CompileError>();

	public FileNode(String fileName, FILE_TYPE fileType) {
		setFileIdentifier(Utilities.getInstance().getUniqueUUID(""));
		setFileType(fileType);
		setFileName(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		fileName = new Tools().removeSpecialCharacters(fileName);
		if (this.getFileType() == FILE_TYPE.JAVASOURCEFILE) {
			setClassName(fileName + ".class");
			fileName += ".java";
		}
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
		setFilePath(getParentPath() + File.separator + getFileName());
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<FileNode> getFilesNodes() {
		return filesNodes;
	}

	public void addFileNodes(FileNode filesNode) {
		this.filesNodes.add(filesNode);
	}

	public void setFilesNodes(List<FileNode> filesNodes) {
		this.filesNodes = filesNodes;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
		this.setFilePath(getParentPath() + File.separator + getFileName());
	}

	public File getFile() {
		return new File(getFilePath());
	}

	public List<CompileError> getCompileErrors() {
		return compileErrors;
	}

	public void addCompileError(CompileError compileError) {
		this.compileErrors.add(compileError);
	}

	public void setCompileErrors(List<CompileError> compileErrors) {
		this.compileErrors = compileErrors;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
