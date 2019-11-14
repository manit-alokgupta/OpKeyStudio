package opkeystudio.opkeystudiocore.core.dtoMaker;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;

public class ORObjectMaker {

	public ObjectAttributeProperty getObjectAttributeProperty(ORObject orObject) {
		ObjectAttributeProperty objectAttributeProperty = new ObjectAttributeProperty();
		objectAttributeProperty.setProperty("");
		objectAttributeProperty.setValue("");

		return objectAttributeProperty;
	}
}
