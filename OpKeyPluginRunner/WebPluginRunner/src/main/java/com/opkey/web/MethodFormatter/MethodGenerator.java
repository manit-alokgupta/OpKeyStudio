package com.opkey.web.MethodFormatter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.crestech.opkey.plugin.Keyword;
import com.opkey.web.ReportHelper;

public class MethodGenerator {
	static String jarPath = "D:\\WP_Opkey\\OpkeyStudio\\OpKeyStudio\\OpKeyStudioIDE\\resources\\libraries\\Plugins\\Web\\Web-3.141.59.jar";
	static ClassPathHacker classPathHacker = new ClassPathHacker();

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		classPathHacker.addFile(jarPath);
		List<String> classEntryList = classPathHacker.getAllClassNames(jarPath);
		List<String> classList = filterClassList(classEntryList);
		generateBody(classList);
//		generateStub();

		System.out.println("--- End");
	}

	private static Method[] getMethods(String classFullName) throws ClassNotFoundException {
		Class<?> _class = Class.forName(classFullName);
		Method[] methods = _class.getDeclaredMethods();
		return methods;
	}

	public static void generateBody(List<String> allClassNameList)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IOException {
		System.out.println("--Start");
		String classFullName = "com.opkey.web.MethodFormatter.Stub";
		Method[] methods = getMethods(classFullName);

		for (Method method : methods) {
			printMethod(allClassNameList, method);
		}
	}

	private static void printMethod(List<String> allClassNameList, Method method)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IOException {
		String script = "";
		String keywordMethodName = "";
		String associatedClass = "";

		// Add annotation
		Keyword annotation = method.getAnnotation(Keyword.class);
		associatedClass = annotation.value().split(",")[0].split("=")[1];
		keywordMethodName = annotation.value().split(",")[1].split("=")[1];
		boolean isMethodFound = false;

		List<String> foundedClass = classPathHacker.findMethod(allClassNameList, keywordMethodName,
				method.getParameterTypes());

		if (foundedClass.size() == 1) {
			associatedClass = foundedClass.get(0);
			isMethodFound = true;
		} else {
			isMethodFound = false;
		}

		script += "@Keyword(\"class=" + associatedClass + ",method=" + keywordMethodName + "\")\n";

		Parameter[] params = method.getParameters();
		String parameterStringFunction = "";
		String parameterStringContext = "";
		String parameterStringCall = "";
		String formatObjectString = "";

		for (Parameter param : params) {
			String paramName = param.getName();
			String paramType = param.getType().getSimpleName();

			if (paramType.equalsIgnoreCase("WebDriverObject")) {
				parameterStringFunction += "ORObject" + " " + paramName + ", ";
			} else {
				parameterStringFunction += paramType + " " + paramName + ", ";
			}

			if (paramType.equalsIgnoreCase("int") || paramType.equalsIgnoreCase("boolean")
					|| paramType.equalsIgnoreCase("double") || paramType.equalsIgnoreCase("long")) {
				parameterStringContext += "String.valueOf(" + paramName + "), ";
				parameterStringCall += paramName + ", ";
			} else if (paramType.equalsIgnoreCase("String")) {
				parameterStringContext += paramName + ", ";
				parameterStringCall += paramName + ", ";
			} else if (paramType.equalsIgnoreCase("WebDriverObject")) {
				formatObjectString += "\t" + "WebDriverObject object" + paramName
						+ " = new ObjectConverter().formatObject(" + paramName + ");\n";
				parameterStringCall += "object" + paramName + ", ";

			}
		}

		if (!parameterStringFunction.isEmpty()) {
			parameterStringFunction = parameterStringFunction.substring(0, parameterStringFunction.lastIndexOf(","));
		}

		if (!parameterStringContext.isEmpty()) {
			parameterStringContext = parameterStringContext.substring(0, parameterStringContext.lastIndexOf(","));
		}

		if (!parameterStringCall.isEmpty()) {
			parameterStringCall = parameterStringCall.substring(0, parameterStringCall.lastIndexOf(","));
		}

		script += "public " + method.getReturnType().getSimpleName() + " " + method.getName() + "("
				+ parameterStringFunction + "){\n";
		script += "\t" + "String keywordName = DataTypeConverter.getMethodName();" + "\n";
		script += "\t" + "ContextInitiator.addFunction(keywordName);" + "\n";
		script += "\t" + "System.out.println(\"Keyword called: \" + keywordName);\n";

		if(isMethodFound) {
			if (!parameterStringContext.isEmpty()) {
				script += "\t" + "ContextInitiator.addDataRgumentsInFunctionCall(" + parameterStringContext + ");"
						+ "\n";
			}

			if (!formatObjectString.isEmpty()) {
				script += formatObjectString;
			}

			script += "\t" + "FunctionResult functionResult = KeywordExecutor.execute(() -> new " + associatedClass
					+ "()." + keywordMethodName + "(" + parameterStringCall + "));\n";

			if (method.getReturnType().getSimpleName().equalsIgnoreCase("boolean")) {
				script += "\t" + "return DataTypeConverter.getBoolean(functionResult.getOutput());\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("int")) {
				script += "\t" + "return DataTypeConverter.getInt(functionResult.getOutput());\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("double")) {
				script += "\t" + "return DataTypeConverter.getDouble(functionResult.getOutput());\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("String")) {
				script += "\t" + "return functionResult.getOutput();\n";
			}
		}else {
			script += "\t" +"ReportHelper.addReportStep(keywordName, new Exception(\"Method not implemented\"));\n";
			if (method.getReturnType().getSimpleName().equalsIgnoreCase("boolean")) {
				script += "\t" + "return false;\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("int")) {
				script += "\t" + "return 0;\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("double")) {
				script += "\t" + "return 0;\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("String")) {
				script += "\t" + "return \"\";\n";
			}
		}
		
		script += "}\n";
		System.out.println(script);
	}


	public static void generateStub()
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IOException {
		System.out.println("--Start Generating Stub");
		String classFullName = "com.opkey.web.OpKeyGenericKeywords";
		Method[] methods = getMethods(classFullName);
		System.out.println("Total Methods: " + methods.length);

		for (Method method : methods) {
			String functionCode = "";
			String keywordClassName = "";
			String keywordMethodName = "";

			// Add annotation
			if (method.isAnnotationPresent(Keyword.class)) {
				Keyword annotation = method.getAnnotation(Keyword.class);
				keywordClassName = annotation.value().split(",")[0].split("=")[1];

				keywordMethodName = annotation.value().split(",")[1].split("=")[1];
				functionCode += "@Keyword(\"class=" + keywordClassName + ",method=" + keywordMethodName + "\")\n";
			}

			Parameter[] params = method.getParameters();
			String parameterStringFunction = "";

			for (Parameter param : params) {
				String paramName = param.getName();
				String paramType = param.getType().getSimpleName();
				if (paramType.equalsIgnoreCase("ORObject")) {
					parameterStringFunction += "WebDriverObject" + " " + paramName + ", ";
				} else {
					parameterStringFunction += paramType + " " + paramName + ", ";
				}

			}

			if (!parameterStringFunction.isEmpty()) {
				parameterStringFunction = parameterStringFunction.substring(0,
						parameterStringFunction.lastIndexOf(","));
			}

			functionCode += "public " + method.getReturnType().getSimpleName() + " " + method.getName() + "("
					+ parameterStringFunction + "){\n";

			if (method.getReturnType().getSimpleName().equalsIgnoreCase("boolean")) {
				functionCode += "\t" + "return false;\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("int")) {
				functionCode += "\t" + "return 0;\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("double")) {
				functionCode += "\t" + "return 0;\n";
			} else if (method.getReturnType().getSimpleName().equalsIgnoreCase("String")) {
				functionCode += "\t" + "return \"\";\n";
			}

			functionCode += "}\n";
			System.out.println(functionCode);
		}
	}

	public static List<String> filterClassList(List<String> classEntryList) throws ClassNotFoundException {
		List<String> classList = new ArrayList<String>();
		for (String className : classEntryList) {
			Class<?>[] interfaces = classPathHacker.getLoadedClass(className).getInterfaces();
			for (Class<?> intfc : interfaces) {
				if (intfc.getSimpleName().equalsIgnoreCase("KeywordLibrary")) {
					classList.add(className);
					// System.out.println("With Keyword Lib: " + className);
				}
			}
		}
		return classList;
	}

}
