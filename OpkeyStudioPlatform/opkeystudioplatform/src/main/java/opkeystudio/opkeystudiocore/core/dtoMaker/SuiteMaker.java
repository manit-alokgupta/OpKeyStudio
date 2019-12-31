package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class SuiteMaker {
	public TestSuiteStep createTestSuite(Artifact artifact, TestSuiteStep selectedTestSuite, List<TestSuiteStep> testSuiteSteps) {
		int selectedFlowStepIndex = testSuiteSteps.indexOf(selectedTestSuite);
		int selectedFlowStepPosition = selectedTestSuite.getPosition();
		TestSuiteStep testSuite = new TestSuiteStep();
		testSuite.setSuite_stepid(Utilities.getInstance().getUniqueUUID(""));
		testSuite.setSuite_id(Utilities.getInstance().getUniqueUUID(""));
		testSuite.setFlow_id(artifact.getId());
		testSuite.setShouldrun(true);
		testSuite.setFlow_type_enum("Keyword");

		testSuite.setAdded(true);
		return testSuite;
	}
}
