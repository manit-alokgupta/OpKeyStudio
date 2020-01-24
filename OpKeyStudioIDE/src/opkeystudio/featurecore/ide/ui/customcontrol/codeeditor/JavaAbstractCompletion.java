package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public class JavaAbstractCompletion extends AutoCompletion {

	public JavaAbstractCompletion(CompletionProvider provider) {
		super(provider);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertCompletion(Completion c, boolean parametrizedCompletion) {
		System.out.println("Inserting " + c.getInputText());
		if (c instanceof BasicCompletion) {
			BasicCompletion bc = new BasicCompletion(c.getProvider(), "Hello");
			super.insertCompletion(bc, parametrizedCompletion);
			return;
		}
		super.insertCompletion(c, parametrizedCompletion);
	}

}
