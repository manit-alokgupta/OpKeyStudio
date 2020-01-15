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
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariableApi {
	public List<GlobalVariable> getAllGlobalVariables()
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		String result = QueryExecutor.getInstance().executeQuery("select * from global_variables");
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, GlobalVariable.class);
		return mapper.readValue(result, type);
	}

	private List<GlobalVariable> getAllGlobalVariable(String gv_id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM global_variables WHERE gv_id='%s'", gv_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, GlobalVariable.class);
		return mapper.readValue(result, type);
	}

	public GlobalVariable getGlobalVariable(String gv_id) throws JsonParseException, JsonMappingException, IOException {
		return getAllGlobalVariable(gv_id).get(0);
	}

	@SuppressWarnings("unused")
	public void deleteGlobalVariable(GlobalVariable gvar) {
		int result = QueryExecutor.getInstance()
				.executeUpdateQuery(String.format("delete from global_variables where gv_id='%s'", gvar.getGv_id()));
	}

	@SuppressWarnings("unused")
	public void updateGlobalVariable(GlobalVariable gvar) {
		String query = String.format(
				"update global_variables Set Name='%s',Value='%s',datatype='%s',ExternallyUpdatable='%s' where GV_ID='%s'",
				gvar.getName(), gvar.getValue(), gvar.getDatatype(), gvar.isExternallyupdatable(), gvar.getGv_id());
		int result = QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	@SuppressWarnings("unused")
	public void insertGlobalVaribale(GlobalVariable gvar) {
		String query = String.format(
				"insert into global_variables(p_id,gv_id,Name,value,datatype,ExternallyUpdatable,position) VALUES('%s','%s','%s','%s','%s','%s','%s')",
				gvar.getP_id(), gvar.getGv_id(), gvar.getName(), gvar.getValue(), gvar.getDatatype(),
				gvar.isExternallyupdatable(), gvar.getPosition());
		int result = QueryExecutor.getInstance().executeUpdateQuery(query);
	}
}
