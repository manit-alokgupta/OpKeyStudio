package opkeystudio.opkeystudiocore.core.codeIde;

import java.util.List;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class CodeCompletionProvider {
	private static CodeCompletionProvider instance;
	private static DefaultCompletionProvider provider;

	public static CodeCompletionProvider getInstance() {
		if (instance == null) {
			instance = new CodeCompletionProvider();
			provider = new DefaultCompletionProvider();
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					List<String> importDatas = IntelliSenseTools.getAllClassNamesFromJar(
							"C:\\\\Program Files (x86)\\\\Java\\\\jdk1.8.0_231\\\\jre\\\\lib\\\\rt.jar");
					instance.addImportTypeBasicCompletion(importDatas);
				}
			});
			thread.start();
		}
		return instance;
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
		provider.addCompletion(new BasicCompletion(provider, "OpkeyGeneric().OpenBrowser"));
		provider.addCompletion(
				new ShorthandCompletion(provider, "sysout", "System.out.println(", "System.out.println("));
		provider.addCompletion(
				new ShorthandCompletion(provider, "syserr", "System.err.println(", "System.err.println("));
		return provider;

	}

	public void addBasicCompletion(String data) {
		BasicCompletion bc = new BasicCompletion(provider, data);
		provider.addCompletion(bc);
	}

	public void addImportTypeBasicCompletion(String data) {
		BasicCompletion bc = new BasicCompletion(provider, data);
		provider.addCompletion(bc);
	}

	public void addImportTypeBasicCompletion(List<String> datas) {
		for (String data : datas) {
			BasicCompletion bc = new BasicCompletion(provider, data);
			provider.addCompletion(bc);
		}
	}
}
