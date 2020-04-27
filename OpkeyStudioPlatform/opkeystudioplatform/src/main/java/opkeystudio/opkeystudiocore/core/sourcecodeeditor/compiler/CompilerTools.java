package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CompilerTools {
	public static int BUFFER_SIZE = 10240;

	public ArrayList<String> getLibrariesPath() {
		ArrayList<String> librariesPath = new ArrayList<String>();
		File libraryFolder = new File(Utilities.getInstance().getLibrariesFolder());
		File[] files = libraryFolder.listFiles();
		for (File file : files) {
			librariesPath.add(file.getAbsolutePath());
		}
		return librariesPath;
	}



	public List<CompileError> compileCodeFl(String codedFLpath, String librariesClassPath) {
		ArrayList<CompileError> compilerErrors = new ArrayList<CompileError>();
		try {
			List<String> optionList = new ArrayList<String>();
			// optionList.addAll(Arrays.asList("-classpath", librariesClassPath, "-d", ""));
			optionList.addAll(Arrays.asList("-classpath", librariesClassPath));
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			ArrayList<File> files = new ArrayList<File>();
			files.add(new File(codedFLpath));
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

	
	public List<CompileError> compileFiles(List<File> files, String librariesClassPath) {
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
	public void createJarArchive(File archiveFile, File[] tobeJared) {
		try {
			byte buffer[] = new byte[BUFFER_SIZE];
			// Open archive file
			FileOutputStream stream = new FileOutputStream(archiveFile);
			JarOutputStream out = new JarOutputStream(stream, new Manifest());

			for (int i = 0; i < tobeJared.length; i++) {
				if (tobeJared[i] == null || !tobeJared[i].exists() || tobeJared[i].isDirectory())
					continue; // Just in case...
				System.out.println("Adding " + tobeJared[i].getName());

				// Add archive entry
				JarEntry jarAdd = new JarEntry(tobeJared[i].getName());
				jarAdd.setTime(tobeJared[i].lastModified());
				out.putNextEntry(jarAdd);

				// Write file to archive
				FileInputStream in = new FileInputStream(tobeJared[i]);
				while (true) {
					int nRead = in.read(buffer, 0, buffer.length);
					if (nRead <= 0)
						break;
					out.write(buffer, 0, nRead);
				}
				in.close();
			}

			out.close();
			stream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error: " + ex.getMessage());
		}
	}
}
