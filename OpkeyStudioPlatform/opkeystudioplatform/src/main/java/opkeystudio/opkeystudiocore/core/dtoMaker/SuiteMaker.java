package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuiteStep;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class SuiteMaker {
	public TestSuiteStep createTestSuite(Artifact artifact, Artifact testSuiteArtifact, TestSuiteStep selectedTestSuite,
			List<TestSuiteStep> testSuiteSteps) {
		int selectedTestSuiteIndex = testSuiteSteps.indexOf(selectedTestSuite);
		int selectedTestSuitePosition = 0;
		if (selectedTestSuite != null) {
			selectedTestSuitePosition = selectedTestSuite.getPosition();
		} else {
			if (testSuiteSteps.size() > 0) {
				TestSuiteStep lastTestSuite = testSuiteSteps.get(testSuiteSteps.size() - 1);
				selectedTestSuitePosition = lastTestSuite.getPosition();
			}
		}
		TestSuiteStep testSuite = new TestSuiteStep();
		testSuite.setSuite_stepid(Utilities.getInstance().getUniqueUUID(""));
		testSuite.setSuite_id(testSuiteArtifact.getId());
		testSuite.setFlow_id(artifact.getId());
		testSuite.setShouldrun(true);
		testSuite.setFlow_type_enum("Keyword");
		testSuite.setPosition(selectedTestSuitePosition + 5);
		testSuite.setArtifact(artifact);
		testSuite.setAdded(true);

		for (int i = selectedTestSuiteIndex + 1; i < testSuiteSteps.size(); i++) {
			TestSuiteStep iTestSuiteStep = testSuiteSteps.get(i);
			iTestSuiteStep.setPosition(iTestSuiteStep.getPosition() + 10);
			iTestSuiteStep.setModified(true);
		}
		return testSuite;
	}

	public TestSuiteStep createSuiteStepReplica(Artifact artifact, TestSuiteStep selectedFlowStep,
			TestSuiteStep pasteFlowStep, List<TestSuiteStep> testSuiteSteps) {
		int selectedFlowStepIndex = testSuiteSteps.indexOf(selectedFlowStep);
		int selectedFlowStepPosition = 0;
		if (selectedFlowStep != null) {
			selectedFlowStepPosition = selectedFlowStep.getPosition();
		} else {
			if (testSuiteSteps.size() > 0) {
				TestSuiteStep lastFlowStep = testSuiteSteps.get(testSuiteSteps.size() - 1);
				selectedFlowStepPosition = lastFlowStep.getPosition();
			}
		}

		TestSuiteStep testSuiteStep = pasteFlowStep.clone();
		testSuiteStep.setSuite_stepid(Utilities.getInstance().getUniqueUUID(""));
		testSuiteStep.setPosition(selectedFlowStepPosition + 5);
		testSuiteStep.setAdded(true);
		for (int i = selectedFlowStepIndex + 1; i < testSuiteSteps.size(); i++) {
			TestSuiteStep isuiteStep = testSuiteSteps.get(i);
			isuiteStep.setPosition(isuiteStep.getPosition() + 10);
			isuiteStep.setModified(true);
		}
		return testSuiteStep;
	}
}
