package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class JavaBasicCompletion extends BasicCompletion {

	private String textToEnter;
	private AutoCompleteToken autoCompleteToken;

	public JavaBasicCompletion(CompletionProvider provider, String replacementText) {
		super(provider, replacementText);
	}

	public JavaBasicCompletion(CompletionProvider provider, String replacementText, String shortDesc) {
		super(provider, replacementText, shortDesc);
	}

	public JavaBasicCompletion(CompletionProvider provider, String replacementText, String shortDesc, String summary) {
		super(provider, replacementText, shortDesc, summary);
	}

	public String getTextToEnter() {
		return textToEnter;
	}

	public void setTextToEnter(String textToEnter) {
		this.textToEnter = textToEnter;
	}

	public AutoCompleteToken getAutoCompleteToken() {
		return autoCompleteToken;
	}

	public void setAutoCompleteToken(AutoCompleteToken autoCompleteToken) {
		this.autoCompleteToken = autoCompleteToken;
	}
}
