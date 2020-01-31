package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class CodeCompletionProvider {
	private static CodeCompletionProvider instance;
	private static JavaCompletionProvider provider;
	private List<AutoCompleteToken> allTokens = new ArrayList<AutoCompleteToken>();
	private List<VariableToken> allvariabletokens = new ArrayList<VariableToken>();

	public static CodeCompletionProvider getInstance() {
		if (instance == null) {
			instance = new CodeCompletionProvider();
			provider = new JavaCompletionProvider();
			initIntellisense(instance);
		}
		return instance;
	}

	public static void initIntellisense(CodeCompletionProvider provider) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// List<String> importDatas = EditorTools.getAllClassNameFromAassociatedJar();
				// instance.addImportTypeBasicCompletion(importDatas);
				EditorTools.getClassInformation();
				provider.createIntellisenseData();
			}
		});
		thread.start();
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

	public void addConstructorTypeBasicCompletion(String dataToShow, String dataToEnter) {
		String[] dataArray = dataToShow.split("\\.");
		String datatoShow = dataArray[dataArray.length - 1];
		JavaBasicCompletion bc = new JavaBasicCompletion(provider, datatoShow);
		bc.setShortDescription(dataToShow);
		bc.setTextToEnter(dataToEnter);
		provider.addCompletion(bc);
	}

	public void createIntellisenseData() {
		List<AutoCompleteToken> allTokens = getAllTokens();
		for (AutoCompleteToken token : allTokens) {
			Class _class = token.getTokenClass();
			createConstructorIntellisense(_class);
		}
	}

	private void createConstructorIntellisense(Class _class) {
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
		}
		return null;
	}

	public JavaCompletionProvider getClassMethodsCompletionProvider(AutoCompleteToken token) {
		JavaCompletionProvider provider = new JavaCompletionProvider();
		JavaBasicCompletion jbc = new JavaBasicCompletion(provider, "toNeon()");
		jbc.setTextToEnter("Neon");
		provider.addCompletion(jbc);
		return provider;
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
