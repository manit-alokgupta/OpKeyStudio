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
	public List<GlobalVariable> getAllGlobalVariables()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String result = sqlComm.executeQueryString("select * from global_variables");
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, GlobalVariable.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public void deleteGlobalVariable(GlobalVariable gvar) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		try {
			sqlComm.connect();
			int result = sqlComm
					.executeUpdate(String.format("delete from global_variables where gv_id='%s'", gvar.getGv_id()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateGlobalVariable(GlobalVariable gvar) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		try {
			sqlComm.connect();
			String query = String.format(
					"update global_variables Set Name='%s',Value='%s',datatype='%s',ExternallyUpdatable='%s' where GV_ID='%s'",
					gvar.getName(), gvar.getValue(), gvar.getDatatype(), gvar.isExternallyupdatable(), gvar.getGv_id());
			int result = sqlComm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertGlobalVaribale(GlobalVariable gvar) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		try {
			sqlComm.connect();
			String query = String.format(
					"insert into global_variables(p_id,gv_id,Name,value,datatype,ExternallyUpdatable,position) VALUES('%s','%s','%s','%s','%s','%s','%s')",
					gvar.getP_id(), gvar.getGv_id(), gvar.getName(), gvar.getValue(), gvar.getDatatype(),
					gvar.isExternallyupdatable(), gvar.getPosition());
			int result = sqlComm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
