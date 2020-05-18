package opkeystudio.opkeystudiocore.core.opkeycore.component;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.dtoMaker.FlowMaker;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO.MODULETYPE;
import opkeystudio.opkeystudiocore.core.opkeycore.Artifact;
import opkeystudio.opkeystudiocore.core.opkeycore.ArtifactMode;

public class Component extends Artifact{

	public Component(ArtifactDTO artifactDTO) {
		super(artifactDTO,MODULETYPE.Component);
	}


	private List<FlowStep> _steps;
	private List<ComponentInputParameter> _inputParameters;
	private List<ComponentOutputParameter> _outputParameters;
	
	private CodedFunction _codedFunction; 
	
	public void AddOrInsertStep(FlowStep selectedStep,Keyword keyword) {
		new FlowMaker().getFlowStepDTO(this.myDTO(), selectedStep, keyword, this.getId(), _steps);
	}
	
	public void AddOrInsertComponentStep(FlowStep selectedStep,Component component, ArtifactMode mode) {
		new FlowMaker().getFlowStepDTOWithFunctionLibray(this.myDTO(), selectedStep, component.myDTO(), this.getId(), _steps);
	}
	
}
