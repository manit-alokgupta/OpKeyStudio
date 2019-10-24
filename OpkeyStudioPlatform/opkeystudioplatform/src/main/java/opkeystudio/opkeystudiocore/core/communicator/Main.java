package opkeystudio.opkeystudiocore.core.communicator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;

public class Main {

	public static void main(String[] args) throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<GlobalVariable> vs= new GlobalVariableApi().getAllGlobalVariables();
		System.out.println(vs.get(0).getValue());
	}

}
