package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class DRColumnAttributes extends Modified implements Comparable<DRColumnAttributes> {

	private int clustering_key;
	@DBField
	private String column_id;
	@DBField
	private int position;
	@DBField
	private boolean isencrypted;
	@DBField
	private String dr_id;
	@DBField
	private String name;

	private List<DRCellAttributes> drCellAttributes = new ArrayList<>();

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getColumn_id() {
		return column_id;
	}

	public void setColumn_id(String column_id) {
		this.column_id = column_id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isIsencrypted() {
		return isencrypted;
	}

	public void setIsencrypted(boolean isencrypted) {
		this.isencrypted = isencrypted;
	}

	public String getDr_id() {
		return dr_id;
	}

	public void setDr_id(String dr_id) {
		this.dr_id = dr_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DRCellAttributes> getDrCellAttributes() {
		return drCellAttributes;
	}

	public void setDrCellAttributes(List<DRCellAttributes> drCellAttributes) {
		this.drCellAttributes = drCellAttributes;
	}

	@Override
	public int compareTo(DRColumnAttributes arg0) {
		return this.getPosition() - arg0.getPosition();
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

}
