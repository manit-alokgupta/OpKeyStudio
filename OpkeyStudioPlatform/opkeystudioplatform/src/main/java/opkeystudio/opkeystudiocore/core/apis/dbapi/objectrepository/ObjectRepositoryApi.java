package opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ObjectRepositoryApi {
	public List<ORObject> getAllObjects(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		String query = String.format("select * from or_objects where or_id='%s'", objectId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ORObject.class);
		return mapper.readValue(result, type);
	}

	private List<ORObject> fetchORObjectProperty(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		String query = String.format("select * from or_objects where object_id='%s'", objectId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ORObject.class);
		return mapper.readValue(result, type);
	}

	public List<ORObject> getORObject(String objectId)
			throws JsonParseException, JsonMappingException, SQLException, IOException {
		List<ORObject> orobjects = fetchORObjectProperty(objectId);
		for (ORObject orobject : orobjects) {
			List<ObjectAttributeProperty> orattributes = getObjectAttributeProperty(orobject.getObject_id());
			orobject.setObjectAttributesProperty(orattributes);
		}
		return orobjects;
	}

	public List<ObjectAttributeProperty> getObjectAttributeProperty(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		String result = QueryExecutor.getInstance()
				.executeQuery(String.format("select * from or_object_properties where object_id='%s'", objectId));
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				ObjectAttributeProperty.class);
		return mapper.readValue(result, type);
	}

	private void deleteOrObject(String objectId) throws SQLException {
		String query = String.format("delete from or_objects where object_id='%s'", objectId);
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	private void deleteObjectProperty(String propertyId) throws SQLException {
		String query = String.format("delete from or_object_properties where property_id='%s'", propertyId);
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	public void addORObject(ORObject orObject) throws SQLException {
		String query = new QueryMaker().createInsertQuery(orObject, "or_objects", "");
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	private void addObjectAttributeProperty(ObjectAttributeProperty objectAttributeProperty) throws SQLException {
		String query = new QueryMaker().createInsertQuery(objectAttributeProperty, "or_object_properties", null);
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	private void updateORObject(ORObject orObject) throws SQLException {
		String query = new QueryMaker().createUpdateQuery(orObject, "or_objects",
				String.format("WHERE object_id='%s'", orObject.getObject_id()));
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	private void updateObjectAttributeProperty(ObjectAttributeProperty objectAttributeProperty) throws SQLException {
		String query = new QueryMaker().createUpdateQuery(objectAttributeProperty, "or_object_properties",
				String.format("WHERE property_id='%s'", objectAttributeProperty.getProperty_id()));
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	public void saveORObjects(Artifact artifact, List<ORObject> objectRepositories) throws SQLException {
		artifact.setModified_on(Utilities.getInstance().getCurrentDateTime());
		new ArtifactApi().updateArtifact(artifact);
		for (ORObject orObject : objectRepositories) {
			saveObjectProperties(orObject.getObjectAttributesProperty());
			if (orObject.isDeleted()) {
				deleteOrObject(orObject.getObject_id());
			}
			if (orObject.isAdded()) {
				addORObject(orObject);
			}
			if (orObject.isModified()) {
				updateORObject(orObject);
			}
		}
		GlobalLoader.getInstance().initAllORObjects();
		GlobalLoader.getInstance().initAllORObjectsObjectProperties();
	}

	private void saveObjectProperties(List<ObjectAttributeProperty> objectAttributesProperties) throws SQLException {

		for (ObjectAttributeProperty objectAttributeProperty : objectAttributesProperties) {
			if (objectAttributeProperty.isDeleted()) {
				deleteObjectProperty(objectAttributeProperty.getProperty_id());
			}
			if (objectAttributeProperty.isAdded()) {
				addObjectAttributeProperty(objectAttributeProperty);
			}
			if (objectAttributeProperty.isModified()) {
				updateObjectAttributeProperty(objectAttributeProperty);
			}
		}
	}
}
