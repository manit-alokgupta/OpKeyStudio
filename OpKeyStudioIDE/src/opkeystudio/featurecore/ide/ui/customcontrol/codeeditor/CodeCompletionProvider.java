package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.util.ArrayList;
import java.util.List;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class CodeCompletionProvider {
	private static CodeCompletionProvider instance;
	private static JavaCompletionProvider provider;
	private List<AutoCompleteToken> allTokens = new ArrayList<AutoCompleteToken>();

	public static CodeCompletionProvider getInstance() {
		if (instance == null) {
			instance = new CodeCompletionProvider();
			provider = new JavaCompletionProvider();
			initIntellisense();
		}
		return instance;
	}

	public static void initIntellisense() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				List<String> importDatas = EditorTools.getAllClassNameFromAassociatedJar();
				instance.addImportTypeBasicCompletion(importDatas);
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

	public void addImportTypeBasicCompletion(String data) {
		String[] dataArray = data.split("\\.");
		String datatoShow = dataArray[dataArray.length - 1];
		JavaBasicCompletion bc = new JavaBasicCompletion(provider, datatoShow);
		bc.setShortDescription(data);
		bc.setTextToEnter(data);
		provider.addCompletion(bc);
	}

	public void addImportTypeBasicCompletion(List<String> datas) {
		for (String data : datas) {
			addImportTypeBasicCompletion(data);
		}
	}

	public List<AutoCompleteToken> getAllTokens() {
		return allTokens;
	}

	public void addAutoCompleteToken(AutoCompleteToken token) {
		this.allTokens.add(token);
	}

	public void setAllTokens(List<AutoCompleteToken> allTokens) {
		this.allTokens = allTokens;
	}
}
