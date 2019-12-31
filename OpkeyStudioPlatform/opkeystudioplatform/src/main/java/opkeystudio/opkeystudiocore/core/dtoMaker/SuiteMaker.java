package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class SuiteMaker {
	public TestSuiteStep createTestSuite(Artifact artifact, TestSuiteStep selectedTestSuite,
			List<TestSuiteStep> testSuiteSteps) {
		int selectedTestSuiteIndex = testSuiteSteps.indexOf(selectedTestSuite);
		int selectedTestSuitePosition = selectedTestSuite.getPosition();
		TestSuiteStep testSuite = new TestSuiteStep();
		testSuite.setSuite_stepid(Utilities.getInstance().getUniqueUUID(""));
		testSuite.setSuite_id(Utilities.getInstance().getUniqueUUID(""));
		testSuite.setFlow_id(artifact.getId());
		testSuite.setShouldrun(true);
		testSuite.setFlow_type_enum("Keyword");
		testSuite.setPosition(selectedTestSuitePosition + 5);
		testSuite.setAdded(true);

		for (int i = selectedTestSuiteIndex + 1; i < testSuiteSteps.size(); i++) {
			TestSuiteStep iTestSuiteStep = testSuiteSteps.get(i);
			iTestSuiteStep.setPosition(iTestSuiteStep.getPosition() + 10);
			iTestSuiteStep.setModified(true);
		}
		return testSuite;
	}
}
