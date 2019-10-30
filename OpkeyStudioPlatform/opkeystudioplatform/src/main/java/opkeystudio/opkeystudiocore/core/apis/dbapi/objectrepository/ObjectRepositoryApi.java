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
}
