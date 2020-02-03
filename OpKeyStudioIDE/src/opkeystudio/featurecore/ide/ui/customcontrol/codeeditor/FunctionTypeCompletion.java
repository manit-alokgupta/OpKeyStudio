package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;

public class FunctionTypeCompletion extends FunctionCompletion {

	private String textToEnter;

	public FunctionTypeCompletion(CompletionProvider provider, String name, String returnType) {
		super(provider, name, returnType);
	}

	public String getTextToEnter() {
		return textToEnter;
	}

	public void setTextToEnter(String textToEnter) {
		this.textToEnter = textToEnter;
	}

}
