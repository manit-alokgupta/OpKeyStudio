package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.queryMaker.DBField;

public class ObjectAttributeProperty extends Modified {
	private int isencrypted;
	private String datatype;
	
	@DBField
	private String or_id;
	
	
	private String property;
	private String clustering_key;
	
	@DBField
	private int position;
	private boolean isregex;
	private String object_id;
	private String value;
	private String property_id;
	
	@DBField
	private boolean iseditable;
	private boolean isused;
	private String f_id;

	public int getIsencrypted() {
		return isencrypted;
	}

	public void setIsencrypted(int isencrypted) {
		this.isencrypted = isencrypted;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	
	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(String clustering_key) {
		this.clustering_key = clustering_key;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isIsregex() {
		return isregex;
	}

	public void setIsregex(boolean isregex) {
		this.isregex = isregex;
	}

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	public boolean isIseditable() {
		return iseditable;
	}

	public void setIseditable(boolean iseditable) {
		this.iseditable = iseditable;
	}

	public boolean isIsused() {
		return isused;
	}

	public void setIsused(boolean isused) {
		this.isused = isused;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
}
