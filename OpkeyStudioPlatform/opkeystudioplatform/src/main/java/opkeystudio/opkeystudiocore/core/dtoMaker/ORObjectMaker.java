package opkeystudio.opkeystudiocore.core.dtoMaker;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ORObjectMaker {

	public ObjectAttributeProperty getObjectAttributeProperty(ORObject orObject) {
		ObjectAttributeProperty objectAttributeProperty = new ObjectAttributeProperty();
		objectAttributeProperty.init(Utilities.getInstance().getUniqueUUID(""), orObject.getObject_id(),
				orObject.getOr_id(), null, "", "", "String", true);
		objectAttributeProperty.setAdded(true);
		return objectAttributeProperty;
	}
}
