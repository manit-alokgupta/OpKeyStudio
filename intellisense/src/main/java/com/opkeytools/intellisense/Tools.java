package com.opkeytools.intellisense;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.management.RuntimeErrorException;

import opkeystudio.opkeystudiocore.core.apis.dto.intellisense.ClassIntellisenseDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.intellisense.MethodIntellisenseDTO;

public class Tools {
	@SuppressWarnings("rawtypes")
	public List<ClassIntellisenseDTO> getAllReflectionsClassesFromJars(List<File> jarFiles) {
		List<ClassIntellisenseDTO> allClassesDto = new ArrayList<ClassIntellisenseDTO>();
		URLClassLoader classLoader = null;
		try {
			classLoader = getURLClassLoaderOfClasses(jarFiles);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Fetching Class Information");
		Set<String> classNames = getAllClassNameFromAllJar(jarFiles);
		for (String className : classNames) {
			if (className.contains("$")) {
				continue;
			}
			try {
				Class loadedClass = classLoader.loadClass(className);
				ClassIntellisenseDTO classIntellidto = createConstructorIntellisense(loadedClass);
				if (classIntellidto == null) {
					continue;
				}
				allClassesDto.add(classIntellidto);
			} catch (NoClassDefFoundError e) {
				// e.printStackTrace();
			} catch (IncompatibleClassChangeError e) {
				// e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// e.printStackTrace();
			} catch (UnsupportedClassVersionError e) {
				// e.printStackTrace();
			} catch (RuntimeErrorException e) {
				// e.printStackTrace();
			} catch (RuntimeException e) {
				// e.printStackTrace();
			} catch (ExceptionInInitializerError e) {
				// e.printStackTrace();
			} catch (UnsatisfiedLinkError e) {
				// e.printStackTrace();
			} catch (LinkageError e) {
				// e.printStackTrace();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		try {
			classLoader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allClassesDto;
	}

	private URLClassLoader getURLClassLoaderOfClasses(List<File> allLibs) throws MalformedURLException {
		URL[] allJarsAndClasses = new URL[allLibs.size()];
		for (int i = 0; i < allLibs.size(); i++) {
			allJarsAndClasses[i] = allLibs.get(i).toURI().toURL();
		}
		URLClassLoader child = new URLClassLoader(allJarsAndClasses);
		return child;
	}

	private Set<String> getAllClassNameFromAllJar(List<File> pluginsLibrary) {
		Set<String> allClases = new HashSet<String>();
		for (File file : pluginsLibrary) {
			List<String> classNames = getAllClassNamesFromJar(file.getAbsolutePath());
			for (String className : classNames) {
				if (className.contains("$")) {
					continue;
				}
				allClases.add(className);
			}
		}
		return allClases;
	}

	private List<String> getAllClassNamesFromJar(String jarName) {
		List<String> listofClasses = new ArrayList<String>();
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if ((jarEntry.getName().toLowerCase().endsWith(".class"))) {
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

	@SuppressWarnings("rawtypes")
	public List<ClassIntellisenseDTO> parseAllClassesForIntellisense(List<Class> allclasses) {
		System.out.println("Converting into DTO");
		List<ClassIntellisenseDTO> allClassesDto = new ArrayList<ClassIntellisenseDTO>();
		for (Class _class : allclasses) {
			ClassIntellisenseDTO classIntellidto = createConstructorIntellisense(_class);
			if (classIntellidto == null) {
				continue;
			}
			allClassesDto.add(classIntellidto);
		}
		System.out.println("Completed");
		return allClassesDto;
	}

	@SuppressWarnings("rawtypes")
	public ClassIntellisenseDTO createConstructorIntellisense(Class _class) {
		try {
			Constructor[] _constructors = _class.getConstructors();
			for (Constructor constructor : _constructors) {
				Parameter[] parameters = constructor.getParameters();
				String parametersString = "";
				String argumentsString = "";
				for (Parameter param : parameters) {
					if (!parametersString.isEmpty()) {
						parametersString += ", ";
					}
					if (!argumentsString.isEmpty()) {
						argumentsString += ", ";
					}
					String paramType = param.getType().getSimpleName();
					String argName = param.getName();
					parametersString += paramType;
					argumentsString += argName;
				}
				String className = _class.getName();
				String dataToShow = constructor.getName() + "(" + parametersString + ")";
				String dataToEnter = constructor.getName() + "(" + argumentsString + ")";
				ClassIntellisenseDTO classIntellidto = new ClassIntellisenseDTO(className, dataToShow, dataToEnter);
				List<MethodIntellisenseDTO> allMethods = getAllMethods(_class);
				classIntellidto.setMethodintellisensedtos(allMethods);
				return classIntellidto;
			}
		} catch (NoClassDefFoundError e) {
			// e.printStackTrace();
		} catch (IncompatibleClassChangeError e) {
			// e.printStackTrace();
		} catch (UnsupportedClassVersionError e) {
			// e.printStackTrace();
		} catch (RuntimeErrorException e) {
			// e.printStackTrace();
		} catch (RuntimeException e) {
			// e.printStackTrace();
		} catch (ExceptionInInitializerError e) {
			// e.printStackTrace();
		} catch (UnsatisfiedLinkError e) {
			// e.printStackTrace();
		} catch (LinkageError e) {
			// e.printStackTrace();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	private List<MethodIntellisenseDTO> getAllMethods(Class _class) {
		List<MethodIntellisenseDTO> allMethodsDto = new ArrayList<MethodIntellisenseDTO>();
		Method[] methods = _class.getMethods();
		for (Method method : methods) {
			Parameter[] parameters = method.getParameters();
			String parametersString = "";
			String argumentsString = "";
			for (Parameter param : parameters) {
				if (!parametersString.isEmpty()) {
					parametersString += ", ";
				}
				if (!argumentsString.isEmpty()) {
					argumentsString += ", ";
				}
				String paramType = param.getType().getSimpleName();
				String argName = param.getName();
				parametersString += paramType;
				argumentsString += argName;
			}
			String _classname = _class.getName();
			String dataToShow = method.getName() + "(" + parametersString + ")";
			String dataToEnter = method.getName() + "(" + argumentsString + ")";
			String retType = method.getReturnType().getSimpleName();
			allMethodsDto.add(new MethodIntellisenseDTO(_classname, dataToShow, dataToEnter, retType));
		}
		return allMethodsDto;
	}
}
