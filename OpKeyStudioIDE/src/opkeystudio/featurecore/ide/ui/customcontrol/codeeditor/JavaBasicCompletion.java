package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class JavaBasicCompletion extends BasicCompletion {
	public JavaBasicCompletion(CompletionProvider provider, String replacementText) {
		super(provider, replacementText);
	}

	public JavaBasicCompletion(CompletionProvider provider, String replacementText, String shortDesc) {
		super(provider, replacementText, shortDesc);
	}

	public JavaBasicCompletion(CompletionProvider provider, String replacementText, String shortDesc, String summary) {
		super(provider, replacementText, shortDesc, summary);
	}
}
