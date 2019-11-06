package opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectRepository;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ObjectRepositoryApi {
	public List<ObjectRepository> getAllObjects(String objectId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("select * from or_objects where or_id='%s'", objectId);
		System.out.println(query);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ObjectRepository.class);
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
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("delete from or_objects where object_id='%s'", objectId);
		System.out.println(query);
		sqlComm.executeUpdate(query);
		sqlComm.disconnect();
	}

	private void deleteObjectProperty(String propertyId) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("delete from or_object_properties where property_id='%s'", propertyId);
		System.out.println(query);
		sqlComm.executeUpdate(query);
		sqlComm.disconnect();
	}

	private void addORObject(ObjectRepository orObject) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("delete from or_object_properties where property_id='%s'", "");
		System.out.println(query);
		sqlComm.executeUpdate(query);
		sqlComm.disconnect();
	}

	private void addObjectAttributeProperty(ObjectAttributeProperty objectAttributeProperty) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("delete from or_object_properties where property_id='%s'", "");
		System.out.println(query);
		sqlComm.executeUpdate(query);
		sqlComm.disconnect();
	}

	private void updateORObject(ObjectRepository orObject) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("delete from or_object_properties where property_id='%s'", "");
		System.out.println(query);
		sqlComm.executeUpdate(query);
		sqlComm.disconnect();
	}

	private void updateObjectAttributeProperty(ObjectAttributeProperty objectAttributeProperty) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("delete from or_object_properties where property_id='%s'", "");
		System.out.println(query);
		sqlComm.executeUpdate(query);
		sqlComm.disconnect();
	}

	public void saveORObjects(List<ObjectRepository> objectRepositories) throws SQLException {
		System.out.println("Deleting ");
		for (ObjectRepository objectRepository : objectRepositories) {
			if (objectRepository.isDeleted()) {
				deleteOrObject(objectRepository.getObject_id());
			}
			if (objectRepository.isAdded()) {
				addORObject(objectRepository);
			}
			if (objectRepository.isModified()) {
				updateORObject(objectRepository);
			}
		}
	}

	public void saveObjectProperties(List<ObjectAttributeProperty> objectAttributesProperties) throws SQLException {
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
