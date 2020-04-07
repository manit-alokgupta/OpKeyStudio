package opkeystudio.opkeystudiocore.core.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.CompileError;

public class ArtifactCompiler {
	
	
	private List<CompileError> compileFiles(List<File> files, String librariesClassPath) {
		ArrayList<CompileError> compilerErrors = new ArrayList<CompileError>();
		try {
			List<String> optionList = new ArrayList<String>();
			// optionList.addAll(Arrays.asList("-classpath", librariesClassPath, "-d", ""));
			optionList.addAll(Arrays.asList("-classpath", librariesClassPath));
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
			try (StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null)) {
				Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(files);

				CompilationTask task = compiler.getTask(null, manager, diagnostics, optionList, null, sources);
				task.call();
			} catch (IOException e) {

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
				compilerErrors.add(compileError);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return compilerErrors;
	}
}
