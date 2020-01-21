package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler.FileNode.FILE_TYPE;
import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.Tools;
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

	private String getLibrariesClassPath() {
		String data = "";
		ArrayList<String> classPathDatas = getLibrariesPath();
		for (String classPath : classPathDatas) {
			if (!data.isEmpty()) {
				data += ";";
			}
			data += classPath;
		}
		return data;
	}

	public void compile(FileNode fileNode) {
		try {
			List<String> optionList = new ArrayList<String>();
			optionList.addAll(Arrays.asList("-classpath", getLibrariesClassPath(), "-d",
					fileNode.getFilePath() + File.separator + "bin"));
			List<FileNode> allFiles = new Tools().getAllFileNodes(fileNode);
			List<FileNode> filteredFiles = new Tools().getAllFileNodes(allFiles, FILE_TYPE.JAVASOURCEFILE);
			ArrayList<File> files = new ArrayList<File>();
			for (FileNode sourceFileNode : filteredFiles) {
				files.add(sourceFileNode.getFile());
			}

			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
			try (StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null)) {
				Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(files);

				CompilationTask task = compiler.getTask(null, manager, diagnostics, optionList, null, sources);
				task.call();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				System.out.println(compileError.getMessage());
				FileNode node = getSourceFileNode(filteredFiles, diagnostic.getSource().getName());
				node.addCompileError(compileError);
			}
			//copyRuntimeLibraryClasses(fileNode);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void copyRuntimeLibraryClasses(FileNode fileNode) {
		File libraryFolder = new File(fileNode.getFilePath() + File.separator + "libs");
		File[] libsFiles = libraryFolder.listFiles();
		for (File libFile : libsFiles) {
			extractFolder(libFile.getAbsolutePath(), fileNode.getFilePath() + File.separator + "bin");
		}

	}

	private void extractFolder(String zipFile, String extractFolder) {
		try {
			int BUFFER = 2048;
			File file = new File(zipFile);

			ZipFile zip = new ZipFile(file);
			String newPath = extractFolder;

			new File(newPath).mkdir();
			Enumeration zipFileEntries = zip.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				String currentEntry = entry.getName();

				File destFile = new File(newPath, currentEntry);
				// destFile = new File(newPath, destFile.getName());
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();

				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}

			}
		} catch (Exception e) {
		}

	}

	public FileNode getSourceFileNode(List<FileNode> filteredNodes, String sourcePath) {
		for (FileNode filenode : filteredNodes) {
			System.out.println("File Path " + filenode.getFile().getAbsolutePath());
			System.out.println("Source Path " + sourcePath);
			if (filenode.getFile().getAbsolutePath().trim().equals(sourcePath.trim())) {
				System.out.println("Returning " + filenode.getFile().getAbsolutePath());
				return filenode;
			}
		}
		return null;
	}

	public void saveSourceCodeFile(FileNode fileNode) throws IOException {
		File file = new File(fileNode.getFilePath());
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(fileNode.getData());
		bw.flush();
		bw.close();
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

	public static void main(String[] args) {

	}
}
