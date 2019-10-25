package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.MetaInformation;

public class FlowSchema {
	@JsonProperty("ImportedArtifacts")
	private List<ImportSectionDTO> importedArtifacts;

	@JsonProperty("MetaInformation")
	private MetaInformation metaInformation;

	@JsonProperty("FlowSteps")
	private List<FlowStep> flowSteps;

	public MetaInformation getMetaInformation() {
		return metaInformation;
	}

	public void setMetaInformation(MetaInformation metaInformation) {
		this.metaInformation = metaInformation;
	}

	public List<ImportSectionDTO> getImportedArtifacts() {
		return importedArtifacts;
	}

	public void setImportedArtifacts(List<ImportSectionDTO> importedArtifacts) {
		this.importedArtifacts = importedArtifacts;
	}

	public List<FlowStep> getFlowSteps() {
		return flowSteps;
	}

	public void setFlowSteps(List<FlowStep> flowSteps) {
		this.flowSteps = flowSteps;
	}
}
