package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

public class AutoCompleteToken {
	@SuppressWarnings("rawtypes")
	private Class tokenClass;

	public AutoCompleteToken(@SuppressWarnings("rawtypes") Class _class) {
		this.setTokenClass(_class);
	}

	@SuppressWarnings("rawtypes")
	public Class getTokenClass() {
		return tokenClass;
	}

	public void setTokenClass(@SuppressWarnings("rawtypes") Class tokenClass) {
		this.tokenClass = tokenClass;
	}
}
