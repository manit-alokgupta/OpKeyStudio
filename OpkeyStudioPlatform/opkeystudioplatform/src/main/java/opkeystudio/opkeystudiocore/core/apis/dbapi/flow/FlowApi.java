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
	public List<FlowStep> getAllSteps(String flowId)
			throws SQLException, JsonParseException, JsonMappingException, IOException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format(
				"SELECT t1.KeywordID,t1.component_id,t1.CodedFunction_ID,t1.soapmethod_id,t1.restmethod_id,t1.ContinueOnError,t1.WantSnapshot,t1.IsNegative,t1.ShouldRun,t1.Comment,t1.position, t2.* FROM flow_design_steps t1 INNER JOIN flow_step_input_arguments t2 USING(flow_stepID) WHERE t1.Flow_ID='%s' ORDER BY t1.position ASC",
				flowId);
		System.out.println(query);
		String result = sqlComm.executeQueryString(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		sqlComm.disconnect();
		return mapper.readValue(result, type);
	}

	public static void main(String[] args) throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<FlowStep> flowSteps = new FlowApi().getAllSteps("38f96dfe-9561-47f5-b4a7-8ebf2421148a");
		for (FlowStep flowStep : flowSteps) {
			System.out.println(flowStep.getKeywordid());
		}
	}
}
