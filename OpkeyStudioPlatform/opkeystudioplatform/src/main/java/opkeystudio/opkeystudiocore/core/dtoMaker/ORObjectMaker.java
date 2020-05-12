package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository.ObjectRepositoryApi;
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

	public void addMobileObject(Artifact artifact, String objectName, Map<String, String> objectProperties,
			String parentObjectName, Map<String, String> parentObjectProperties, String objectType,
			String parentObjectType, ORObject parentORObject, List<ORObject> allORObjects) throws SQLException {

		String artifactId = artifact.getId(); // OR-ID
		List<ObjectAttributeProperty> objectAttributeProperties = new ArrayList<>();

		if (parentORObject != null) {
			ORObject orobject = getORObjectDTO(artifact, artifactId, parentORObject.getObject_id(), objectName,
					objectType, allORObjects);
			for (String propName : objectProperties.keySet()) {
				String propValue = objectProperties.get(propName);
				ObjectAttributeProperty attrProp = getNewObjectAttributeProperty(orobject, objectAttributeProperties);
				attrProp.setProperty(propName);
				attrProp.setValue(propValue);
				if (propName.equalsIgnoreCase("xpath") || propName.equalsIgnoreCase("resource-id")
						|| propName.equalsIgnoreCase("name") || propName.equalsIgnoreCase("class"))
					attrProp.setIsused(true);
				objectAttributeProperties.add(attrProp);
			}
			orobject.setObjectAttributesProperty(objectAttributeProperties);
			List<ORObject> orobjects = new ArrayList<ORObject>();
			orobjects.add(orobject);
			new ObjectRepositoryApi().saveORObjects(artifact, orobjects);
			return;
		}

		List<ObjectAttributeProperty> parentAttributeProperties = new ArrayList<>();
		ORObject parentObject = getORObjectDTO(artifact, artifactId, null, parentObjectName, parentObjectType,
				allORObjects);
		ORObject orobject = getORObjectDTO(artifact, artifactId, parentObject.getObject_id(), objectName, objectType,
				allORObjects);

		for (String propName : parentObjectProperties.keySet()) {
			String propValue = parentObjectProperties.get(propName);
			ObjectAttributeProperty attrProp = getNewObjectAttributeProperty(parentObject, parentAttributeProperties);
			attrProp.setProperty(propName);
			attrProp.setValue(propValue);
			if (propName.equalsIgnoreCase("class"))
				attrProp.setIsused(true);
			parentAttributeProperties.add(attrProp);
		}

		for (String propName : objectProperties.keySet()) {
			String propValue = objectProperties.get(propName);
			ObjectAttributeProperty attrProp = getNewObjectAttributeProperty(orobject, objectAttributeProperties);
			attrProp.setProperty(propName);
			attrProp.setValue(propValue);
			if (propName.equalsIgnoreCase("xpath") || propName.equalsIgnoreCase("resource-id")
					|| propName.equalsIgnoreCase("name") || propName.equalsIgnoreCase("class"))
				attrProp.setIsused(true);
			objectAttributeProperties.add(attrProp);
		}

		parentObject.setObjectAttributesProperty(parentAttributeProperties);
		orobject.setObjectAttributesProperty(objectAttributeProperties);

		List<ORObject> orobjects = new ArrayList<ORObject>();
		orobjects.add(parentObject);
		orobjects.add(orobject);

		new ObjectRepositoryApi().saveORObjects(artifact, orobjects);
	}

	public ORObject createORObjectReplica(Artifact artifact, String orName, ORObject selectedORObject,
			ORObject pasteORObject, List<ORObject> allORObjects) {
		int selectedORObjectIndex = allORObjects.indexOf(selectedORObject);
		int selectedORObjectPosition = 0;
		if (selectedORObject != null) {
			selectedORObjectPosition = selectedORObject.getPosition();
		} else {
			if (allORObjects.size() > 0) {
				ORObject lastORObject = allORObjects.get(allORObjects.size() - 1);
				selectedORObjectPosition = lastORObject.getPosition();
			}
		}

		ORObject orobject = pasteORObject.clone();
		orobject.setName(orName);
		orobject.setObject_id(Utilities.getInstance().getUniqueUUID(""));
		orobject.setParent_object_id(selectedORObject.getObject_id());
		orobject.setPosition(selectedORObjectPosition + 5);
		orobject.setAdded(true);
		List<ObjectAttributeProperty> attributes = pasteORObject.getObjectAttributesProperty();
		List<ObjectAttributeProperty> allattributes = new ArrayList<ObjectAttributeProperty>();
		for (ObjectAttributeProperty attribute : attributes) {
			ObjectAttributeProperty clonedAttribute = attribute.clone();
			clonedAttribute.setProperty_id(Utilities.getInstance().getUniqueUUID(""));
			clonedAttribute.setObject_id(orobject.getObject_id());
			clonedAttribute.setAdded(true);
			allattributes.add(clonedAttribute);
		}
		orobject.setObjectAttributesProperty(allattributes);
		for (int i = selectedORObjectIndex + 1; i < allORObjects.size(); i++) {
			ORObject iflowStep = allORObjects.get(i);
			iflowStep.setPosition(iflowStep.getPosition() + 10);
			iflowStep.setModified(true);
		}
		return orobject;
	}

}
