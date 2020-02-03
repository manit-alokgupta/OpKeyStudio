package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

import opkeystudio.core.utils.MessageDialogs;
import opkeystudio.featurecore.ide.ui.ui.CodedFunctionView;

public class CodeCompletionProvider {
	private static CodeCompletionProvider instance;
	private static JavaCompletionProvider provider;
	private List<AutoCompleteToken> allTokens = new ArrayList<AutoCompleteToken>();
	private List<VariableToken> allvariabletokens = new ArrayList<VariableToken>();

	public static CodeCompletionProvider getInstance(CodedFunctionView codedFunctionView) {
		if (instance == null) {
			instance = new CodeCompletionProvider();
			provider = new JavaCompletionProvider();
			initIntellisense(instance, codedFunctionView);
		}
		return instance;
	}

	public static void initIntellisense(CodeCompletionProvider provider, CodedFunctionView cview) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialogs msd = new MessageDialogs();
				msd.openProgressDialog(null, "Please wait initializing Intellisense...");
				new EditorTools(cview).initIntellisense();
				msd.closeProgressDialog();
			}
		});
	}

	public CompletionProvider getCompletionProvider() {
		provider.addCompletion(new BasicCompletion(provider, "abstract"));
		provider.addCompletion(new BasicCompletion(provider, "assert"));
		provider.addCompletion(new BasicCompletion(provider, "break"));
		provider.addCompletion(new BasicCompletion(provider, "case"));
		provider.addCompletion(new BasicCompletion(provider, "transient"));
		provider.addCompletion(new BasicCompletion(provider, "try"));
		provider.addCompletion(new BasicCompletion(provider, "void"));
		provider.addCompletion(new BasicCompletion(provider, "volatile"));
		provider.addCompletion(new BasicCompletion(provider, "while"));
		provider.addCompletion(
				new ShorthandCompletion(provider, "sysout", "System.out.println(", "System.out.println("));
		provider.addCompletion(
				new ShorthandCompletion(provider, "syserr", "System.err.println(", "System.err.println("));
		return provider;

	}

	public void addBasicCompletion(String data) {
		if (provider.getCompletionByInputText(data) == null) {
			JavaBasicCompletion bc = new JavaBasicCompletion(provider, data);
			bc.setTextToEnter(data);
			provider.addCompletion(bc);
		}
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

	public void addConstructorTypeBasicCompletion(String dataToShow, String dataToEnter) {
		String[] dataArray = dataToShow.split("\\.");
		String datatoShow = dataArray[dataArray.length - 1];
		JavaBasicCompletion bc = new JavaBasicCompletion(provider, datatoShow);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/class.ico");
		BufferedImage image=convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		bc.setIcon(icon);
		provider.addCompletion(bc);
	}

	public void addMethodTypeBasicCompletion(JavaCompletionProvider provider, String dataToShow, String dataToEnter) {
		JavaBasicCompletion bc = new JavaBasicCompletion(provider, dataToShow);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/green dot.png");
		BufferedImage image=convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		bc.setIcon(icon);
		provider.addCompletion(bc);
	}

	public void addFieldTypeBasicCompletion(JavaCompletionProvider provider, String dataToShow, String dataToEnter) {
		JavaBasicCompletion bc = new JavaBasicCompletion(provider, dataToShow);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/green dot.png");
		BufferedImage image=convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		bc.setIcon(icon);
		provider.addCompletion(bc);
	}

	public void createIntellisenseData() {
		List<AutoCompleteToken> allTokens = getAllTokens();
		for (AutoCompleteToken token : allTokens) {
			Class _class = token.getTokenClass();
			createConstructorIntellisense(_class);
		}
	}

	public void createConstructorIntellisense(Class _class) {
		try {
			Constructor[] _constructors = _class.getConstructors();
			for (Constructor constructor : _constructors) {
				String name = constructor.getName();
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
			// TODO: handle exception
		} catch (IncompatibleClassChangeError e) {
			// TODO: handle exception
		} catch (UnsupportedClassVersionError e) {
			// TODO: handle exception
		}
	}

	public List<AutoCompleteToken> getAllTokens() {
		return allTokens;
	}

	public AutoCompleteToken findAutoCompleteToken(String tokenString) {
		List<AutoCompleteToken> allTokens = getAllTokens();
		for (AutoCompleteToken token : allTokens) {
			@SuppressWarnings("rawtypes")
			Class tokenClass = token.getTokenClass();
			String className = tokenClass.getName();
			if (className.endsWith("." + tokenString)) {
				System.out.println("ClassName " + tokenClass.getName());
				return token;
			}
			if (className.trim().equals(tokenString.trim())) {
				return token;
			}
		}
		return null;
	}

	public JavaCompletionProvider getClassMethodsCompletionProvider(AutoCompleteToken token) {
		JavaCompletionProvider provider = new JavaCompletionProvider();
		Class _class = token.getTokenClass();
		Method[] methods = _class.getMethods();
		Field[] fields = _class.getFields();
		for (Field field : fields) {
			parseStaticVariables(provider, field);
		}
		for (Method method : methods) {
			parseMethod(provider, method);
		}
		return provider;
	}

	private void parseStaticVariables(JavaCompletionProvider provider, Field field) {
		String modifier = Modifier.toString(field.getModifiers()).toLowerCase();
		System.out.println("Adding Field " + field.getName());
		if (modifier.contains("public")) {
			if (modifier.contains("static")) {
				if (!modifier.contains("private")) {
					addFieldTypeBasicCompletion(provider, field.getName(), field.getName());
				}
			}
		}
	}

	private void parseMethod(JavaCompletionProvider provider, Method method) {
		String name = method.getName();
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
		addMethodTypeBasicCompletion(provider, dataToShow, dataToEnter);
	}

	public void addAutoCompleteToken(AutoCompleteToken token) {
		this.allTokens.add(token);
	}

	public void setAllTokens(List<AutoCompleteToken> allTokens) {
		this.allTokens = allTokens;
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
}
