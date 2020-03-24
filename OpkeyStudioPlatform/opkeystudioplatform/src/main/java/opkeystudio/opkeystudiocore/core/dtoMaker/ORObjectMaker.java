package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
			String objectType, List<ORObject> allORObjects) {
		int position = 0;
		if (allORObjects.size() > 0) {
			position = allORObjects.get(allORObjects.size() - 1).getPosition() + 10;
		}
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
		orobject.setPosition(position);
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

	public void addMobileObject(Artifact artifact, String orid, Map<String, String> objectProperties,
			Map<String, String> parentProperties, String objectName, String parentObjectName, String objectType,
			String parentObjectType, List<ORObject> allORObjects) {

		System.out.println("Object Logical Name: " + objectName);
		System.out.println("Object Properties: ");
		for (String key : objectProperties.keySet()) {
			System.out.println("Key: " + key + ", Value: " + objectProperties.get(key));
		}

		System.out.println("Parent Object Logical Name: " + parentObjectName);
		System.out.println("Parent Object Properties: ");
		for (String key : parentProperties.keySet()) {
			System.out.println("Key: " + key + ", Value: " + parentProperties.get(key));
		}
	}

}
