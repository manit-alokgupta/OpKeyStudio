package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
			System.out.println("File Path " + sourceFileNode.getFile().getAbsolutePath());
			files.add(sourceFileNode.getFile());
		}

		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			System.out.println("Compiler not found");
		}
		final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		try (final StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null)) {
			final Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(files);

			final CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, sources);
			task.call();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (final Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {

			System.out.format("%s, line %d in %s", diagnostic.getMessage(null), diagnostic.getLineNumber(),
					diagnostic.getSource().getName());
		}
	}
}
