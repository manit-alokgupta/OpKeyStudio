package opkeystudio.opkeystudiocore.core.models.model;

import java.util.List;
import java.util.UUID;

import opkeystudio.opkeystudiocore.core.models.testcasemodel.TestCaseStep;

public abstract class ModelGroup {
	private String modelId;

	public ModelGroup() {
		setModelId(UUID.randomUUID().toString());
	}

	public String getModelId() {
		return modelId;
	}

	private void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public abstract void addTestCaseStep(TestCaseStep testcasestep);

	public abstract List<TestCaseStep> getAllTestCaseSteps();
}
