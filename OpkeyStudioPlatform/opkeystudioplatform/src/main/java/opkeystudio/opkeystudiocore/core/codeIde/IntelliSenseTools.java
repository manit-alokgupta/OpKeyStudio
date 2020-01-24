package opkeystudio.opkeystudiocore.core.codeIde;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class IntelliSenseTools {
	public static List<String> getAllClassNamesFromJar(String crunchifyJarName) {
		List<String> listofClasses = new ArrayList<String>();
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(crunchifyJarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if ((jarEntry.getName().endsWith(".class"))) {
					String className = jarEntry.getName().replaceAll("/", "\\.");
					String myClass = className.substring(0, className.lastIndexOf('.'));
					listofClasses.add(myClass);
				}
			}
			jarFile.close();
		} catch (Exception e) {
			
		}
		return listofClasses;
	}

	public static void main(String[] args) {
		List<String> allCasses = getAllClassNamesFromJar("C:\\Program Files (x86)\\Java\\jdk1.8.0_231\\jre\\lib\\rt.jar");
		for (String className : allCasses) {
			System.out.println(className);
		}
	}
}
