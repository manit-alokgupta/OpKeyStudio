package opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ObjectRepositoryApi {
	public List<ORObject> getAllObjects(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("select * from or_objects where or_id='%s'", objectId);
		System.out.println(query);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ORObject.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public List<ORObject> getORObject(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("select * from or_objects where object_id='%s'", objectId);
		System.out.println(query);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ORObject.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public List<ObjectAttributeProperty> getObjectAttributeProperty(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String result = sqlComm
				.executeQueryString(String.format("select * from or_object_properties where object_id='%s'", objectId));
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				ObjectAttributeProperty.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	private void deleteOrObject(String objectId) throws SQLException {
		String query = String.format("delete from or_objects where object_id='%s'", objectId);
		System.out.println(query);
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	private void deleteObjectProperty(String propertyId) throws SQLException {
		String query = String.format("delete from or_object_properties where property_id='%s'", propertyId);
		System.out.println(query);
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	private void addORObject(ORObject orObject) throws SQLException {

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

	public void saveORObjects(List<ORObject> objectRepositories) throws SQLException {

		for (ORObject orObject : objectRepositories) {
			saveObjectProperties(orObject.getObjectAttributesProperty());
			if (orObject.isDeleted()) {
				System.out.println("Deleting..... ");
				deleteOrObject(orObject.getObject_id());
			}
			if (orObject.isAdded()) {
				addORObject(orObject);
			}
			if (orObject.isModified()) {
				updateORObject(orObject);
			}
		}
	}

	private void saveObjectProperties(List<ObjectAttributeProperty> objectAttributesProperties) throws SQLException {

		for (ObjectAttributeProperty objectAttributeProperty : objectAttributesProperties) {
			if (objectAttributeProperty.isDeleted()) {
				System.out.println("Deleting......");
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
