package opkeystudio.opkeystudiocore.core.models.testcasemodel;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.models.model.ModelGroup;

public class TestCaseGroup extends ModelGroup {
	private List<TestCaseStep> steps = new ArrayList<>();

	@Override
	public void addTestCaseStep(TestCaseStep testcasestep) {
		this.steps.add(testcasestep);
	}

	@Override
	public List<TestCaseStep> getAllTestCaseSteps() {
		return this.steps;
	}

}
