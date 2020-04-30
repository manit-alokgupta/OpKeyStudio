package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.intellisense;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.ShorthandCompletion;

import opkeystudio.featurecore.ide.ui.customcontrol.codeeditor.JavaCompletionProvider;

public class GenericEditorIntellisense extends JavaCompletionProvider {

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
}
