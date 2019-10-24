package opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariableApi {
	public List<GlobalVariable> getAllGlobalVariables() throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm=new SQLiteCommunicator();
		sqlComm.connect();
		String result=sqlComm.executeQueryString("select * from global_variables");
		ObjectMapper mapper=Utilities.getInstance().getObjectMapperInstance();
		CollectionType type=mapper.getTypeFactory().constructCollectionType(List.class, GlobalVariable.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}
}
