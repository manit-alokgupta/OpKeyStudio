package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ORObjectMaker {

	public ObjectAttributeProperty getNewObjectAttributeProperty(ORObject orObject,
			List<ObjectAttributeProperty> allAttributes) {
		int position = 0;
		if (allAttributes.size() > 0) {
			position = allAttributes.get(allAttributes.size() - 1).getPosition() + 10;
		}
		ObjectAttributeProperty objectAttributeProperty = new ObjectAttributeProperty();
		objectAttributeProperty.setIseditable(true);
		objectAttributeProperty.setProperty_id(Utilities.getInstance().getUniqueUUID(""));
		objectAttributeProperty.setObject_id(orObject.getObject_id());
		objectAttributeProperty.setOr_id(orObject.getOr_id());
		objectAttributeProperty.setProperty("");
		objectAttributeProperty.setValue("");
		objectAttributeProperty.setDatatype("String");
		objectAttributeProperty.setPosition(position);
		objectAttributeProperty.setAdded(true);
		return objectAttributeProperty;
	}

	String[] parentORAttrs = new String[] { "url", "title", "index" };
	String[] childORAttrs = new String[] { "name", "tag", "title", "class", "style" };

	public ORObject getORObjectDTO(Artifact artifact, String orid, String parentId, String objectName,
			String objectType) {
		ORObject orobject = new ORObject();
		orobject.setObject_id(Utilities.getInstance().getUniqueUUID(""));
		orobject.setOr_id(orid);
		orobject.setCreatedon(Utilities.getInstance().getCurrentDateTime());
		orobject.setCreatedon_tz(Utilities.getInstance().getCurrentTimeZone());
		orobject.setCreatedby(artifact.getCreated_by());
		orobject.setModifiedby(artifact.getModified_by());
		orobject.setModifiedon(Utilities.getInstance().getUpdateCurrentDateTime());
		orobject.setModifiedon_tz(Utilities.getInstance().getCurrentTimeZone());
		orobject.setParent_object_id(parentId);
		orobject.setName(objectName);
		orobject.setOpkeytype(objectType);
		orobject.setAdded(true);
		List<ObjectAttributeProperty> orprops = new ArrayList<ObjectAttributeProperty>();
		if (parentId == null) {
			for (String parentOR : parentORAttrs) {
				ObjectAttributeProperty oap = getNewObjectAttributeProperty(orobject, orprops);
				oap.setProperty(parentOR);
				orprops.add(oap);
			}
		} else {
			for (String childOR : childORAttrs) {
				ObjectAttributeProperty oap = getNewObjectAttributeProperty(orobject, orprops);
				oap.setProperty(childOR);
				orprops.add(oap);
			}
		}
		orobject.setObjectAttributesProperty(orprops);
		return orobject;
	}
}
