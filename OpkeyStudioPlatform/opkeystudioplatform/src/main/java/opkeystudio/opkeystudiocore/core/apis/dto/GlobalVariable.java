package opkeystudio.opkeystudiocore.core.apis.dto;

import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariable extends Modified {
	@DBField
	private boolean isdeleted;
	@DBField
	private int position;
	@DBField
	private String value;

	private int clustering_key;
	@DBField
	private String datatype;
	@DBField
	private String gv_id;
	@DBField
	private boolean externallyupdatable;
	@DBField
	private String p_id;
	@DBField
	private String name;

	private String variableName;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getGv_id() {
		return gv_id;
	}

	public void setGv_id(String gv_id) {
		this.gv_id = gv_id;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isExternallyupdatable() {
		return externallyupdatable;
	}

	public void setExternallyupdatable(boolean externallyupdatable) {
		this.externallyupdatable = externallyupdatable;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getVariableName() {
		String varName = Utilities.getInstance().removeSpecialCharacters(getName());
		varName = varName.replaceAll(" ", "_").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\*", "");
		if (varName.trim().isEmpty()) {
			return "unknownVar";
		}
		if (checkVariableNameIsValid(varName) == false) {
			return "o" + varName;
		}
		return varName;
	}

	private boolean checkVariableNameIsValid(String packagename) {
		try {
			Integer.parseInt(packagename);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Integer.parseInt(String.valueOf(packagename.charAt(0)));
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

}
