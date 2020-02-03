package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.VariableCompletion;

public class VariableTypeCompletion extends VariableCompletion {
	private String textToEnter;
	
	public VariableTypeCompletion(CompletionProvider provider, String name, String type) {
		super(provider, name, type);
	}

	public String getTextToEnter() {
		return textToEnter;
	}

	public void setTextToEnter(String textToEnter) {
		this.textToEnter = textToEnter;
	}

}
