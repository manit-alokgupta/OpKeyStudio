package opkeystudio.opkeystudiocore.core.apis.dbapi.testsuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class TestSuiteApi {
	private List<TestSuiteStep> getAllTestSuitesStep(String testSuiteId) {
		String query = String.format("SELECT * FROM suite_design_steps where suite_id='%s' ORDER BY position",
				testSuiteId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuiteStep.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<TestSuiteStep>();
	}

	private Artifact getArtifact(String id) {

		return GlobalLoader.getInstance().getArtifactById(id);
	}

	public List<TestSuiteStep> getAllTestSuitesStepsWithArtifact(String testSuiteId) {
		List<TestSuiteStep> testSuites = getAllTestSuitesStep(testSuiteId);
		for (TestSuiteStep suite : testSuites) {
			Artifact artifact = getArtifact(suite.getFlow_id());
			suite.setArtifact(artifact);
		}
		return testSuites;
	}

	private void deleteTestSuite(TestSuiteStep testSuite) {
		if (testSuite.isDeleted()) {
			String query = String.format("delete from suite_design_steps where suite_stepid='%s'",
					testSuite.getSuite_stepid());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateTestSuite(TestSuiteStep testSuite) {
		if (testSuite.isModified()) {
			String query = new QueryMaker().createUpdateQuery(testSuite, "suite_design_steps",
					String.format("WHERE suite_stepid='%s'", testSuite.getSuite_stepid()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addTestSuite(TestSuiteStep testSuite) {
		if (testSuite.isAdded()) {
			String query = new QueryMaker().createInsertQuery(testSuite, "suite_design_steps", null);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	@SuppressWarnings("unused")
	public void saveAllTestSuite(Artifact artifact, List<TestSuiteStep> testSuites) {
		artifact.setModified_on(Utilities.getInstance().getUpdateCurrentDateTime());
		new ArtifactApi().updateArtifact(artifact);
		for (TestSuiteStep testSuite : testSuites) {
			String updateQuery = new QueryMaker().createUpdateQuery(testSuite, "suite_design_steps",
					String.format("WHERE suite_stepid='%s'", testSuite.getSuite_stepid()));
			String addQuery = new QueryMaker().createInsertQuery(testSuite, "suite_design_steps", null);
			deleteTestSuite(testSuite);
			updateTestSuite(testSuite);
			addTestSuite(testSuite);
		}
	}
}
