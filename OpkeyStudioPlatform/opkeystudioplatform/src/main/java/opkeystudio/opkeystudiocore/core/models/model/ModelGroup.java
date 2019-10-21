package opkeystudio.opkeystudiocore.core.models.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import opkeystudio.opkeystudiocore.core.models.testcasemodel.TestCaseModelGroup;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = TestCaseModelGroup.class, name = "TestCaseGroup") })
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

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
