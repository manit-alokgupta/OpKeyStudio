package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class TranspileObject {
	private Artifact artifact;
	private List<FlowStep> flowSteps = new ArrayList<>();
	private List<GlobalVariable> globalVaribales = new ArrayList<GlobalVariable>();
	public List<FlowStep> getFlowSteps() {
		return flowSteps;
	}

	public void setFlowSteps(List<FlowStep> flowSteps) {
		this.flowSteps = flowSteps;
	}

	public List<GlobalVariable> getGlobalVaribales() {
		return globalVaribales;
	}

	public void setGlobalVaribales(List<GlobalVariable> globalVaribales) {
		this.globalVaribales = globalVaribales;
	}

	public Artifact getArtifact() {
		return artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}
}
