package com.opkeytools.intellisense;

import java.io.File;
import java.io.IOException;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.intellisense.ClassIntellisenseDTO;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class App {
	private static String EXPORT_DIR_PATH = "C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\resources\\commons\\intellisense\\mainintellisense";
	private static String File_Name = "mainintellisense.sense";

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {

		List<File> files = new CompilerUtilities().getAllFiles(new File(
				"C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\resources\\libraries\\PluginBase"),
				".jar");
		files.addAll(new CompilerUtilities().getAllFiles(new File(
				"C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\resources\\libraries\\PluginRunner"),
				".jar"));
		files.addAll(new CompilerUtilities().getAllFiles(new File(
				"C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\resources\\libraries\\Plugins\\Appium"),
				".jar"));
		files.addAll(new CompilerUtilities().getAllFiles(new File(
				"C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\resources\\libraries\\Plugins\\Web"),
				".jar"));
		List<ClassIntellisenseDTO> classes = new Tools().getAllReflectionsClassesFromJars(files);

		System.out.println("Started DTO Writing to File");
		Utilities.getInstance().writeXMLSerializedData(new File(EXPORT_DIR_PATH + File.separator + File_Name), classes);
		System.out.println("Completed DTO Writing to File");

	}
}
