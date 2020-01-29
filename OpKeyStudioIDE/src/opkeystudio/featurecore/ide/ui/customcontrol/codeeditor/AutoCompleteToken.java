package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

public class AutoCompleteToken {
	private String varName;
	private String importPackageName;

	public AutoCompleteToken(String varName, String importPackageName) {
		this.setVarName(varName);
		this.setImportPackageName(importPackageName);
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getImportPackageName() {
		return importPackageName;
	}

	public void setImportPackageName(String importPackageName) {
		this.importPackageName = importPackageName;
	}
}
