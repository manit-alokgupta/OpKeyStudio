package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

public class VariableToken {
	private String variableName;

	private String className;

	public VariableToken(String varName, String className) {
		this.setVariableName(varName);
		this.setClassName(className);
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
