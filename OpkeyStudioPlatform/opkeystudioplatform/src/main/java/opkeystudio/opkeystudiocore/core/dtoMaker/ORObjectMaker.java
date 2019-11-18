package opkeystudio.opkeystudiocore.core.dtoMaker;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ORObjectMaker {

	public ObjectAttributeProperty getNewObjectAttributeProperty(ORObject orObject) {
		ObjectAttributeProperty objectAttributeProperty = new ObjectAttributeProperty();
		objectAttributeProperty.init(Utilities.getInstance().getUniqueUUID(""), orObject.getObject_id(),
				orObject.getOr_id(), null, "", "", "String", true);
		objectAttributeProperty.setAdded(true);
		return objectAttributeProperty;
	}

	public ORObject getORObjectDTO(String parentId, String orname, String ortype) {
		ORObject orobject = new ORObject();
		orobject.setObject_id(Utilities.getInstance().getUniqueUUID(""));
		orobject.setCreatedon(Utilities.getInstance().getCurrentDateTime());
		orobject.setCreatedon_tz(Utilities.getInstance().getCurrentTimeZone());
		orobject.setCreatedby("ABC");
		orobject.setModifiedby("ABC");
		orobject.setModifiedon(Utilities.getInstance().getCurrentDateTime());
		orobject.setModifiedon_tz(Utilities.getInstance().getCurrentTimeZone());

		orobject.setParent_object_id(parentId);
		orobject.setName(orname);
		orobject.setOpkeytype(ortype);

		orobject.setAdded(true);
		return orobject;
	}
}
