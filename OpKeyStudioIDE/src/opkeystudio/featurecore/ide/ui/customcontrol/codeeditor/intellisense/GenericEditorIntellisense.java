package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.FunctionTypeCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaBasicCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaCompletionProvider;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.VariableToken;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.VariableTypeCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.components.TranspiledClassInfo;
import opkeystudio.opkeystudiocore.core.apis.dto.intellisense.ClassIntellisenseDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.intellisense.MethodIntellisenseDTO;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GenericEditorIntellisense extends JavaCompletionProvider {

	private List<TranspiledClassInfo> transpiledClasses = new ArrayList<TranspiledClassInfo>();
	private List<VariableToken> allvariabletokens = new ArrayList<VariableToken>();
	private static GenericEditorIntellisense instance;
	private List<ClassIntellisenseDTO> senseClasses = new ArrayList<ClassIntellisenseDTO>();

	public static GenericEditorIntellisense getInstance() {
		if (instance == null) {
			instance = new GenericEditorIntellisense();
			instance.initIntellisense();
		}
		return instance;
	}

	public void disposeIntellisense() {
		instance = null;
	}

	public void refreshIntellisense() {
		instance = new GenericEditorIntellisense();
		instance.initIntellisense();
	}

	private void initIntellisense() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialogs msd = new MessageDialogs();
				msd.openProgressDialog(null, "Intellisense Initializing", false, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						addSimpleKeywords();
						addOpKeyTranspiledClassInformation();
						addClassInformationFromSenseFile();
					}
				});
				msd.closeProgressDialog();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void addClassInformationFromSenseFile() {
		List<File> allFiles = new CompilerUtilities()
				.getAllFiles(new File(Utilities.getInstance().getMainIntellisenseFolder()), ".sense");
		for (int i = 0; i < allFiles.size(); i++) {
			File file = allFiles.get(i);
			try {
				List<ClassIntellisenseDTO> classDTOs = (List<ClassIntellisenseDTO>) Utilities.getInstance()
						.getXMLDeSerializedDataForClassIntellisenseDTO(file);
				setSenseClasses(classDTOs);
				List<Completion> basicCompletions = new ArrayList<Completion>();
				for (ClassIntellisenseDTO classIntellisense : classDTOs) {
					TranspiledClassInfo classInfo = new TranspiledClassInfo(classIntellisense);
					addTranspiledClasses(classInfo);
					JavaBasicCompletion classbc = getConstructorTypeBasicCompletion(classIntellisense.getClassname(),
							classIntellisense.getClassname());
					JavaBasicCompletion bc = getConstructorTypeBasicCompletion(classIntellisense.getDatatoshow(),
							classIntellisense.getDatatoenter());
					basicCompletions.add(classbc);
					basicCompletions.add(bc);
				}
				this.addCompletions(basicCompletions);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void addLibraryClassInformation() {
		List<File> jarFiles = new CompilerUtilities().getAllPluginRunnerJar();
		jarFiles.addAll(new CompilerUtilities().getPluginBaseLibraries());
		jarFiles.addAll(new CompilerUtilities().getAllPluginsLibraries());
		List<Class> allClasses = getAllReflectionsClassesFromJars(jarFiles);
		parseAllClassesForIntellisense(allClasses);
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
				if (className.contains("org.openqa") || className.contains("java.lang")
						|| className.contains("java.util") || className.contains("java.io")
						|| className.contains("com.opkey")) {
					allClases.add(className);
				}
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
	private List<Class> getAllReflectionsClassesFromJars(List<File> jarFiles) {
		List<Class> allClasses = new ArrayList<Class>();
		try {
			System.out.println("Fetching Class Information");
			URLClassLoader classLoader = getURLClassLoaderOfClasses(jarFiles);
			Set<String> classNames = getAllClassNameFromAllJar(jarFiles);
			for (String className : classNames) {
				if (className.contains("$")) {
					continue;
				}
				try {
					Class loadedClass = classLoader.loadClass(className);
					allClasses.add(loadedClass);
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
					e.printStackTrace();
				} catch (ExceptionInInitializerError e) {
					e.printStackTrace();
				} catch (UnsatisfiedLinkError e) {
					e.printStackTrace();
				} catch (LinkageError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return allClasses;
	}

	@SuppressWarnings("rawtypes")
	private void parseAllClassesForIntellisense(List<Class> allclasses) {
		for (Class _class : allclasses) {
			parseClass(_class);
		}
	}

	@SuppressWarnings("rawtypes")
	private void parseClass(Class _class) {
		TranspiledClassInfo classInfo = new TranspiledClassInfo(_class);
		addTranspiledClasses(classInfo);
		createConstructorIntellisense(_class);
	}

	@SuppressWarnings("rawtypes")
	public void createConstructorIntellisense(Class _class) {
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

				String dataToShow = constructor.getName() + "(" + parametersString + ")";
				String dataToEnter = constructor.getName() + "(" + argumentsString + ")";
				addConstructorTypeBasicCompletion(dataToShow, dataToEnter);
			}
			addConstructorTypeBasicCompletion(_class.getName(), _class.getName());
		} catch (NoClassDefFoundError e) {
			// e.printStackTrace();
		} catch (IncompatibleClassChangeError e) {
			e.printStackTrace();
		} catch (UnsupportedClassVersionError e) {
			e.printStackTrace();
		} catch (RuntimeErrorException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
		} catch (LinkageError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public JavaCompletionProvider getClassMethodsCompletionProvider(TranspiledClassInfo tranpiledClassInfo) {
		GenericEditorIntellisense provider = new GenericEditorIntellisense();

		if (tranpiledClassInfo.getClassSource() != null) {
			List<MethodSource<JavaClassSource>> methods = tranpiledClassInfo.getClassSource().getMethods();
			for (MethodSource<JavaClassSource> method : methods) {
				String methodName = method.getName();
				String returnType = method.getReturnType().toString();
				String params = "";
				for (ParameterSource<JavaClassSource> param : method.getParameters()) {
					if (!params.isEmpty()) {
						params += ", ";
					}
					params += param.toString();
				}
				String methodBodyToShow = String.format("%s(%s)", methodName, params);
				String methodBodyToEnter = String.format("%s(%s);", methodName, params);
				System.out.println(methodBodyToShow);
				provider.addMethodTypeBasicCompletion(methodBodyToShow, methodBodyToEnter, returnType);
			}

			List<FieldSource<JavaClassSource>> fields = tranpiledClassInfo.getClassSource().getFields();
			for (FieldSource<JavaClassSource> field : fields) {
				String name = field.getName();
				String type = field.getType().getName();
				System.out.println(name + "     " + type);
				provider.addFieldTypeBasicCompletion(name, name, type);
			}
		}

		if (tranpiledClassInfo.getReflectionClassObject() != null) {
			Class _class = tranpiledClassInfo.getReflectionClassObject();
			Method[] methods = _class.getMethods();
			Field[] fields = _class.getFields();
			for (Field field : fields) {
				parseStaticVariables(provider, field);
			}
			for (Method method : methods) {
				parseMethod(provider, method);
			}
		}
		if (tranpiledClassInfo.getClassIntellisenseDTO() != null) {
			List<MethodIntellisenseDTO> methods = tranpiledClassInfo.getClassIntellisenseDTO()
					.getMethodintellisensedtos();
			for (MethodIntellisenseDTO method : methods) {
				provider.addMethodTypeBasicCompletion(method.getDatatoshow(), method.getDatatoenter(),
						method.getReturntype());
			}
		}
		return provider;
	}

	private void parseStaticVariables(GenericEditorIntellisense provider, Field field) {
		String modifier = Modifier.toString(field.getModifiers()).toLowerCase();
		System.out.println("Adding Field " + field.getName());
		if (modifier.contains("public")) {
			if (modifier.contains("static")) {
				if (!modifier.contains("private")) {
					provider.addFieldTypeBasicCompletion(field.getName(), field.getName(),
							field.getType().getSimpleName());
				}
			}
		}
	}

	private void parseMethod(GenericEditorIntellisense provider, Method method) {
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

		String dataToShow = method.getName() + "(" + parametersString + ")";
		String dataToEnter = method.getName() + "(" + argumentsString + ")";
		String retType = method.getReturnType().getSimpleName();
		provider.addMethodTypeBasicCompletion(dataToShow, dataToEnter, retType);
	}

	private void addSimpleKeywords() {
		this.addCompletion(new BasicCompletion(this, "abstract"));
		this.addCompletion(new BasicCompletion(this, "assert"));
		this.addCompletion(new BasicCompletion(this, "break"));
		this.addCompletion(new BasicCompletion(this, "case"));
		this.addCompletion(new BasicCompletion(this, "transient"));
		this.addCompletion(new BasicCompletion(this, "try"));
		this.addCompletion(new BasicCompletion(this, "catch"));
		this.addCompletion(new BasicCompletion(this, "void"));
		this.addCompletion(new BasicCompletion(this, "volatile"));
		this.addCompletion(new BasicCompletion(this, "while"));
		this.addCompletion(new ShorthandCompletion(this, "sysout", "System.out.println(", "System.out.println();"));
		this.addCompletion(new ShorthandCompletion(this, "syserr", "System.err.println(", "System.err.println();"));
	}

	public void addOpKeyTranspiledClassInformation() {
		String mainDirPath = Utilities.getInstance().getProjectArtifactCodesFolder();
		List<File> allFiles = new CompilerUtilities().getAllFiles(new File(mainDirPath), ".java");
		for (File file : allFiles) {
			try {
				String codeData = Utilities.getInstance().readTextFile(file);
				JavaClassSource classSource = (JavaClassSource) Roaster.parse(codeData);
				boolean isAdded = this.addTranspiledClasses(new TranspiledClassInfo(classSource));
				if (isAdded == false) {
					parseConstructors(classSource);
					parseClassMethods(classSource);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void parseConstructors(JavaClassSource javaClassSource) {
		String className = javaClassSource.getName();
		addConstructorTypeBasicCompletion(className, className);
		className = className + "()";
		addConstructorTypeBasicCompletion(className, className);
	}

	private void parseClassMethods(JavaClassSource classSource) {
		List<MethodSource<JavaClassSource>> methods = classSource.getMethods();
	}

	public void addBasicCompletion(String data) {
		if (this.getCompletionByInputText(data) == null) {
			JavaBasicCompletion bc = new JavaBasicCompletion(this, data);
			bc.setTextToEnter(data);
			this.addCompletion(bc);
		}
	}

	private JavaBasicCompletion getConstructorTypeBasicCompletion(String dataToShow, String dataToEnter) {
		String[] dataArray = dataToShow.split("\\.");
		String datatoShow = dataArray[dataArray.length - 1];
		JavaBasicCompletion bc = new JavaBasicCompletion(this, datatoShow);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/class.ico");
		BufferedImage image = convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		bc.setIcon(icon);
		return bc;
	}

	private void addConstructorTypeBasicCompletion(String dataToShow, String dataToEnter) {
		String[] dataArray = dataToShow.split("\\.");
		String datatoShow = dataArray[dataArray.length - 1];
		JavaBasicCompletion bc = new JavaBasicCompletion(this, datatoShow);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/class.ico");
		BufferedImage image = convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		bc.setIcon(icon);
		this.addCompletion(bc);
	}

	public void addMethodTypeBasicCompletion(String dataToShow, String dataToEnter, String returnType) {
		FunctionTypeCompletion bc = new FunctionTypeCompletion(this, dataToShow, returnType);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		this.addCompletion(bc);
	}

	public void addFieldTypeBasicCompletion(String dataToShow, String dataToEnter, String type) {
		VariableTypeCompletion bc = new VariableTypeCompletion(this, dataToShow, type);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		this.addCompletion(bc);
	}

	private BufferedImage convertToAWT(ImageData data) {
		ColorModel colorModel = null;
		PaletteData palette = data.palette;
		if (palette.isDirect) {
			colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[3];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					RGB rgb = palette.getRGB(pixel);
					pixelArray[0] = rgb.red;
					pixelArray[1] = rgb.green;
					pixelArray[2] = rgb.blue;
					raster.setPixels(x, y, 1, 1, pixelArray);
				}
			}
			return bufferedImage;
		} else {
			RGB[] rgbs = palette.getRGBs();
			byte[] red = new byte[rgbs.length];
			byte[] green = new byte[rgbs.length];
			byte[] blue = new byte[rgbs.length];
			for (int i = 0; i < rgbs.length; i++) {
				RGB rgb = rgbs[i];
				red[i] = (byte) rgb.red;
				green[i] = (byte) rgb.green;
				blue[i] = (byte) rgb.blue;
			}
			if (data.transparentPixel != -1) {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
			} else {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
			}
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					pixelArray[0] = pixel;
					raster.setPixel(x, y, pixelArray);
				}
			}
			return bufferedImage;
		}
	}

	public List<TranspiledClassInfo> getTranspiledClasses() {
		return transpiledClasses;
	}

	private boolean addTranspiledClasses(TranspiledClassInfo classInfo) {
		List<TranspiledClassInfo> classInfos = this.getTranspiledClasses();
		for (TranspiledClassInfo ci : classInfos) {
			if (classInfo.getClassSource() != null) {
				if (ci.getClassSource().getName().equals(classInfo.getClassSource().getName())) {
					ci.setClassSource(classInfo.getClassSource());
					return true;
				}
			}
		}
		this.getTranspiledClasses().add(classInfo);
		return false;
	}

	public void setTranspiledClasses(List<TranspiledClassInfo> transpiledClasses) {
		this.transpiledClasses = transpiledClasses;
	}

	public void addVariableToken(VariableToken token) {
		this.allvariabletokens.add(token);
	}

	public List<VariableToken> getAllvariabletokens() {
		return allvariabletokens;
	}

	public void setAllvariabletokens(List<VariableToken> allvariabletokens) {
		this.allvariabletokens = allvariabletokens;
	}

	public VariableToken findVariableToken(String varName) {
		for (VariableToken varToken : getAllvariabletokens()) {
			if (varToken.getVariableName().equals(varName)) {
				return varToken;
			}
		}
		return null;
	}

	public TranspiledClassInfo findAutoCompleteToken(String tokenString) {
		List<TranspiledClassInfo> allTokens = getTranspiledClasses();
		for (TranspiledClassInfo token : allTokens) {
			if (token.getClassSource() != null) {
				String className = token.getClassSource().getName();
				if (className.endsWith("." + tokenString)) {
					return token;
				}
				if (className.trim().equals(tokenString.trim())) {
					return token;
				}
			}
			if (token.getReflectionClassObject() != null) {
				String className = token.getReflectionClassObject().getName();
				if (className.endsWith("." + tokenString)) {
					return token;
				}
				if (className.trim().equals(tokenString.trim())) {
					return token;
				}
			}
			if (token.getClassIntellisenseDTO() != null) {
				String className = token.getClassIntellisenseDTO().getClassname();
				if (className.endsWith("." + tokenString)) {
					return token;
				}
				if (className.trim().equals(tokenString.trim())) {
					return token;
				}
			}

		}
		return null;
	}

	public List<ClassIntellisenseDTO> getSenseClasses() {
		return senseClasses;
	}

	public void setSenseClasses(List<ClassIntellisenseDTO> senseClasses) {
		this.senseClasses = senseClasses;
	}

}
