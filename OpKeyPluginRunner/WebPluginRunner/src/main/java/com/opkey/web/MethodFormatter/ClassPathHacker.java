package com.opkey.web.MethodFormatter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassPathHacker {

	/**
	 * Parameters of the method to add an URL to the System classes.
	 */
	private static final Class<?>[] parameters = new Class[] { URL.class };

	/**
	 * Adds a file to the ClassPath.
	 */
	public void addFile(String s) throws IOException {
		File f = new File(s);
		addFile(f);
	}

	/**
	 * Adds a file to the ClassPath
	 */
	public void addFile(File f) throws IOException {
		addURL(f.toURI().toURL());
	}

	/**
	 * Adds the content pointed by the URL to the ClassPath.
	 */
	public void addURL(URL u) throws IOException {
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class<?> sysclass = URLClassLoader.class;
		try {
			Method method = sysclass.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(sysloader, new Object[] { u });
		} catch (Throwable t) {
			t.printStackTrace();
			throw new IOException("Error, could not add URL to system classloader");
		}
	}

	public Class<?> getLoadedClass(String fullClassName) throws ClassNotFoundException {
		return ClassLoader.getSystemClassLoader().loadClass(fullClassName);
	}

	@SuppressWarnings("resource")
	public List<String> getAllClassNames(String jarPath) throws IOException {
		List<String> classEntryList = new ArrayList<String>();

		JarFile jarFile = new JarFile(jarPath);

		Enumeration<?> allEntries = jarFile.entries();
		while (allEntries.hasMoreElements()) {
			JarEntry entry = (JarEntry) allEntries.nextElement();
			String name = entry.getName();
			if (name.contains(".class") && !name.contains("$")) {
				name = name.replaceAll("/", ".");
				name = name.substring(0, name.lastIndexOf("."));
				classEntryList.add(name);
				// System.out.println("---" + name);
			}
		}

		return classEntryList;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> findMethod(List<String> allClassNameList, String methodName,
			Class<?>[] parameterTypes) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IOException {
		
		List<String> foundedClass = new ArrayList<String>();
		for(String entry: allClassNameList) {
			//System.out.println("--ClassName: " + entry.getName());
			Class<?> _class = null;
	
			try {
				_class =  this.getLoadedClass(entry); //Class.forName(entry);
				//System.out.println(_class.getSimpleName() +", Length: " + _class.getMethods().length);
			} catch (Exception e) {
				System.out.println("class loading trouble: " + e.getMessage());
				continue;
			}
			
			try {
				_class.getMethod(methodName, parameterTypes);
				foundedClass.add(_class.getSimpleName());
			} catch (Exception e) {
				//System.out.println("method not found: Original" + parameterTypes.toString());
				//e.printStackTrace();
			}
		
			
//			try {
//				Type[] interfaces = _class.getGenericInterfaces();
////				interfaces[0]_class.getSimpleName();
//				System.out.println("interfaceSize: " + interfaces.length);
//				for(Type ifc: interfaces) {
//					if(ifc.getClass().getSimpleName().equalsIgnoreCase("KeywordLibrary")) {
//						try {
//							_class.getMethod(method.getName(), method.getParameterTypes());
//							foundedClass.add(_class.getSimpleName());
//						} catch (Exception e) {
//							System.out.println("method not found");
//						}
//						
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
		}
		
		//System.out.println("--  "+ methodName + ": founded class sized: " + foundedClass.size());
		return foundedClass;
	}

}