package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Tools;

public class CompilerTools {
	public void compile(FileNode fileNode) {
		List<FileNode> allFiles = new Tools().getAllFileNodes(fileNode);
		List<FileNode> filteredFiles = new Tools().getAllFileNodes(allFiles, FILE_TYPE.SOURCEFILE);
		ArrayList<File> files = new ArrayList<File>();
		for (FileNode sourceFileNode : filteredFiles) {
			files.add(sourceFileNode.getFile());
		}

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		try (StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null)) {
			Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(files);

			CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, sources);
			task.call();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
			CompileError compileError = new CompileError();
			compileError.setCode(diagnostic.getCode());
			compileError.setColumnNumber(diagnostic.getColumnNumber());
			compileError.setEndPosition(diagnostic.getEndPosition());
			compileError.setLineNumber(diagnostic.getLineNumber());
			compileError.setPosition(diagnostic.getPosition());
			compileError.setStartPosition(diagnostic.getStartPosition());
			compileError.setKind(diagnostic.getKind());
			compileError.setMessage(diagnostic.getMessage(null));
			compileError.setSource(diagnostic.getSource());

			FileNode node = getSourceFileNode(filteredFiles, diagnostic.getSource().getName());
			node.addCompileError(compileError);
		}
	}

	public FileNode getSourceFileNode(List<FileNode> filteredNodes, String sourcePath) {
		for (FileNode filenode : filteredNodes) {
			if (filenode.getFile().getAbsolutePath().trim().equals(sourcePath)) {
				return filenode;
			}
		}
		return null;
	}
}
