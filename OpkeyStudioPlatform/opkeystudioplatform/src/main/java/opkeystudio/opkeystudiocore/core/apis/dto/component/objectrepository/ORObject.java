package opkeystudio.opkeystudiocore.core.apis.dto.component.objectrepository;

import java.util.ArrayList;
import java.util.List;

public class ORObject {
	private String opKeyType;
	private int position;
	private boolean useSmartIdentification;
	private boolean isUpdatable;
	private List<ObjectProperty> objectProperties = new ArrayList<>();
	private List<ORObject> childObjects = new ArrayList<>();

	public String getOpKeyType() {
		return opKeyType;
	}

	public void setOpKeyType(String opKeyType) {
		this.opKeyType = opKeyType;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isUseSmartIdentification() {
		return useSmartIdentification;
	}

	public void setUseSmartIdentification(boolean useSmartIdentification) {
		this.useSmartIdentification = useSmartIdentification;
	}

	public boolean isUpdatable() {
		return isUpdatable;
	}

	public void setUpdatable(boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}

	public List<ObjectProperty> getObjectProperties() {
		return objectProperties;
	}

	public void setObjectProperties(List<ObjectProperty> objectProperties) {
		this.objectProperties = objectProperties;
	}

	public List<ORObject> getChildObjects() {
		return childObjects;
	}

	public void setChildObjects(List<ORObject> childObjects) {
		this.childObjects = childObjects;
	}
}
