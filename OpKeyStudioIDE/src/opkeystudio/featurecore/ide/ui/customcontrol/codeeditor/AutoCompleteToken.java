package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

public class AutoCompleteToken {
	private Class tokenClass;

	public AutoCompleteToken(Class _class) {
		this.setTokenClass(_class);
	}

	public Class getTokenClass() {
		return tokenClass;
	}

	public void setTokenClass(Class tokenClass) {
		this.tokenClass = tokenClass;
	}
}
