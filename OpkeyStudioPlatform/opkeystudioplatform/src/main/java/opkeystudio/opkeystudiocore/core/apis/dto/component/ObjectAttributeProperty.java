package opkeystudio.opkeystudiocore.core.apis.dto.component;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class ObjectAttributeProperty extends Modified implements Cloneable {
	@DBField
	private boolean isencrypted;
	@DBField
	private String datatype;
	@DBField
	private String or_id;
	@DBField
	private String property;

	private int clustering_key;

	@DBField
	private int position;
	@DBField
	private boolean isregex;
	@DBField
	private String object_id;
	@DBField
	private String value;
	@DBField
	private String property_id;
	@DBField
	private boolean iseditable;
	@DBField
	private boolean isused;
	@DBField
	private String f_id;

	private String oldPropertyName;

	public void init(String propertyId, String objectId, String or_id, String fId, String property, String value,
			String dataType, boolean iseditable) {
		this.setProperty_id(propertyId);
		this.setObject_id(objectId);
		this.setOr_id(or_id);
		this.setF_id(fId);
		this.setProperty(property);
		this.setValue(value);
		this.setDatatype(dataType);
		this.setIseditable(iseditable);

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

	public int getClustering_key() {
		return clustering_key;
	}

	@SuppressWarnings("unused")
	private void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public boolean isIsencrypted() {
		return isencrypted;
	}

	public void setIsencrypted(boolean isencrypted) {
		this.isencrypted = isencrypted;
	}

	public ObjectAttributeProperty clone() {
		try {
			return (ObjectAttributeProperty) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getOldPropertyName() {
		return oldPropertyName;
	}

	public void setOldPropertyName(String oldPropertyName) {
		this.oldPropertyName = oldPropertyName;
	}
}
