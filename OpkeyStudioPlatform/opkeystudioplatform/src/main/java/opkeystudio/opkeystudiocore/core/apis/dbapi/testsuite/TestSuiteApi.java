package opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TestSuiteApi {
	public List<TestSuite> getAllTestSuitesStep(String testSuiteId)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM suite_design_steps where suite_id='%s' ORDER BY position",
				testSuiteId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);
	}
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		new TestSuiteApi().getAllTestSuitesStep("d620abc2-9f1d-4d2f-b20f-e6974ef47113");
	}
}
