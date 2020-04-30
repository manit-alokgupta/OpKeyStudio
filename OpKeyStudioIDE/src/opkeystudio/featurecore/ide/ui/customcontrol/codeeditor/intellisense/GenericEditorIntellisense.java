package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wb.swt.ResourceManager;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.FunctionTypeCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaBasicCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaCompletionProvider;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.VariableToken;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.VariableTypeCompletion;
import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense.components.TranpiledClassInfo;
import opkeystudio.opkeystudiocore.core.compiler.CompilerUtilities;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GenericEditorIntellisense extends JavaCompletionProvider {

	private List<TranpiledClassInfo> transpiledClasses = new ArrayList<TranpiledClassInfo>();
	private List<VariableToken> allvariabletokens = new ArrayList<VariableToken>();
	private static GenericEditorIntellisense instance;

	public static GenericEditorIntellisense getInstance() {
		if (instance == null) {
			instance = new GenericEditorIntellisense();
			instance.initIntellisense();
		}
		return instance;
	}

	private void initIntellisense() {
		addSimpleKeywords();
		addOpKeyTranspiledClassInformation();
	}

	public JavaCompletionProvider getClassMethodsCompletionProvider(TranpiledClassInfo tranpiledClassInfo) {
		GenericEditorIntellisense provider = new GenericEditorIntellisense();
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
		return provider;
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

	private void addOpKeyTranspiledClassInformation() {
		String mainDirPath = Utilities.getInstance().getTranspiledArtifactsFolder();
		List<File> allFiles = new CompilerUtilities().getAllFiles(new File(mainDirPath), ".java");
		for (File file : allFiles) {
			String codeData = Utilities.getInstance().readTextFile(file);
			JavaClassSource classSource = (JavaClassSource) Roaster.parse(codeData);
			this.addTranspiledClasses(new TranpiledClassInfo(classSource));
			parseConstructors(classSource);
			parseClassMethods(classSource);
		}
	}

	private void parseConstructors(JavaClassSource javaClassSource) {
		String className = javaClassSource.getName();
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

	public List<TranpiledClassInfo> getTranspiledClasses() {
		return transpiledClasses;
	}

	private void addTranspiledClasses(TranpiledClassInfo classInfo) {
		this.getTranspiledClasses().add(classInfo);
	}

	public void setTranspiledClasses(List<TranpiledClassInfo> transpiledClasses) {
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

	public TranpiledClassInfo findAutoCompleteToken(String tokenString) {
		List<TranpiledClassInfo> allTokens = getTranspiledClasses();
		for (TranpiledClassInfo token : allTokens) {
			String className = token.getClassSource().getName();
			System.out.println(">>ClassName " + className);
			if (className.endsWith("." + tokenString)) {
				System.out.println("ClassName " + token.getClassSource().getName());
				return token;
			}
			if (className.trim().equals(tokenString.trim())) {
				return token;
			}
		}
		return null;
	}

}
