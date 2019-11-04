package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowApi {
	public List<FlowStep> getAllSteps(String flowId) throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format("select * from flow_design_steps INNER JOIN flow_step_input_arguments USING(flow_stepID)",
				flowId);
		System.out.println(query);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public static void main(String[] args) throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<FlowStep> flowSteps= new FlowApi().getAllSteps("38f96dfe-9561-47f5-b4a7-8ebf2421148a");
		for(FlowStep flowStep:flowSteps) {
			System.out.println(flowStep.getPosition());
		}
	}
}
