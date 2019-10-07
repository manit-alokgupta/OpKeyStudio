package opkeystudio.opkeystudiocore.core.models.model;

import java.util.UUID;

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
}
