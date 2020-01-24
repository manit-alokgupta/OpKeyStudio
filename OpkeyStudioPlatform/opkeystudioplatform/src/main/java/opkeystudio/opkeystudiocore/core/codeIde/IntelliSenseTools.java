package opkeystudio.opkeystudiocore.core.codeIde;

import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class IntelliSenseTools {
	public static JSONObject getCrunchifyClassNamesFromJar(String crunchifyJarName) {
		JSONArray listofClasses = new JSONArray();
		JSONObject crunchifyObject = new JSONObject();
		try {
			JarInputStream crunchifyJarFile = new JarInputStream(new FileInputStream(crunchifyJarName));
			JarEntry crunchifyJar;

			while (true) {
				crunchifyJar = crunchifyJarFile.getNextJarEntry();
				if (crunchifyJar == null) {
					break;
				}
				if ((crunchifyJar.getName().endsWith(".class"))) {
					String className = crunchifyJar.getName().replaceAll("/", "\\.");
					String myClass = className.substring(0, className.lastIndexOf('.'));
					listofClasses.put(myClass);
				}
			}
			crunchifyJarFile.close();
			crunchifyObject.put("Jar File Name", crunchifyJarName);
			crunchifyObject.put("List of Class", listofClasses);
		} catch (Exception e) {
			System.out.println("Oops.. Encounter an issue while parsing jar" + e.toString());
		}
		return crunchifyObject;
	}

	public static void main(String[] args) {
		JSONObject obejec = getCrunchifyClassNamesFromJar("C:\\Program Files (x86)\\Java\\jdk1.8.0_231\\jre\\lib\\rt.jar");
		System.out.println(obejec.toString());
	}
}
