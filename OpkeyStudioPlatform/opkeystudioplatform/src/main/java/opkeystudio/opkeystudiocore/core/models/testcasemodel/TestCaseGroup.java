package opkeystudio.opkeystudiocore.core.models.testcasemodel;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.models.model.ModelGroup;

public class TestCaseGroup extends ModelGroup {
	private List<TestCaseStep> steps = new ArrayList<>();

	public void addTestCaseStep(TestCaseStep testcasestep) {
		this.steps.add(testcasestep);
	}

	public List<TestCaseStep> getAllTestCaseSteps() {
		return this.steps;
	}
}
