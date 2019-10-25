package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import opkeystudio.opkeystudiocore.core.apis.dto.component.generic.MetaInformation;

public class FlowSchema {
	private ImportedArtifacts importedArtifacts;
	private MetaInformation metaInformation;
	private FlowSteps flowSteps;

	public ImportedArtifacts getImportedArtifacts() {
		return importedArtifacts;
	}

	public void setImportedArtifacts(ImportedArtifacts importedArtifacts) {
		this.importedArtifacts = importedArtifacts;
	}

	public MetaInformation getMetaInformation() {
		return metaInformation;
	}

	public void setMetaInformation(MetaInformation metaInformation) {
		this.metaInformation = metaInformation;
	}

	public FlowSteps getFlowSteps() {
		return flowSteps;
	}

	public void setFlowSteps(FlowSteps flowSteps) {
		this.flowSteps = flowSteps;
	}
}
