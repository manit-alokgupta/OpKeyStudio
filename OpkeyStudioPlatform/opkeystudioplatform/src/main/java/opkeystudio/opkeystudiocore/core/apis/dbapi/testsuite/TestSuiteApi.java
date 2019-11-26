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
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
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
		return new ArtifactApi().getArtifact(id).get(0);
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

	private void deleteTestSuite(TestSuite testSuite) {
		if (testSuite.isDeleted()) {
			System.out.println("" + testSuite.getSuite_stepid());
			String query = String.format("delete from suite_design_steps where suite_stepid='%s'",
					testSuite.getSuite_stepid());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateTestSuite(TestSuite testSuite) {
		if (testSuite.isModified()) {
			String query = new QueryMaker().createUpdateQuery(testSuite, "suite_design_steps",
					String.format("WHERE suite_stepid='%s'", testSuite.getSuite_stepid()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addTestSuite(TestSuite testSuite) {
		if (testSuite.isAdded()) {
			String query = new QueryMaker().createInsertQuery(testSuite, "suite_design_steps", null);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public void saveAllTestSuite(List<TestSuite> testSuites) {
		for (TestSuite testSuite : testSuites) {
			String updateQuery = new QueryMaker().createUpdateQuery(testSuite, "suite_design_steps",
					String.format("WHERE suite_stepid='%s'", testSuite.getSuite_stepid()));
			System.out.println("update Query:- " + updateQuery);
			System.out.println("Save Query:- " + testSuite.getArtifact().getName());
			String addQuery = new QueryMaker().createInsertQuery(testSuite, "suite_design_steps", null);
			System.out.println("Add Query:- " + addQuery);
			deleteTestSuite(testSuite);
			updateTestSuite(testSuite);
			addTestSuite(testSuite);
		}
	}
}
