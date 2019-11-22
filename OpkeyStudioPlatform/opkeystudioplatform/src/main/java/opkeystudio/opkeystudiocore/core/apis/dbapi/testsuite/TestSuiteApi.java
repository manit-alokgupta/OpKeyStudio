package opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TestSuiteApi {
	private List<TestSuite> getAllTestSuitesStep(String testSuiteId)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM suite_design_steps where suite_id='%s' ORDER BY position",
				testSuiteId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);
	}

	private Artifact getArtifact(String id) throws JsonParseException, JsonMappingException, IOException {
		return new ArtifactApi().getArtifact(id);
	}

	public List<TestSuite> getAllTestSuitesStepsWithArtifact(String testSuiteId)
			throws JsonParseException, JsonMappingException, IOException {
		List<TestSuite> testSuites = getAllTestSuitesStep(testSuiteId);
		for (TestSuite suite : testSuites) {
			Artifact artifact = getArtifact(suite.getSuite_id());
			suite.setArtifact(artifact);
		}
		return testSuites;
	}
}
