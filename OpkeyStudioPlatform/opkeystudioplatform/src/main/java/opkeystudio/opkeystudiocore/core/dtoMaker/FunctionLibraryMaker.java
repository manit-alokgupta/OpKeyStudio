package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ArtifactDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputParameter;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FunctionLibraryMaker {
	public ComponentInputParameter createComponentInputParameterDTO(ArtifactDTO artifact, String variableName,
			ComponentInputParameter selectedComponentStep, List<ComponentInputParameter> componentInputParameters) {

		int selectedComponentStepIndex = componentInputParameters.indexOf(selectedComponentStep);
		int selectedComponentStepPosition = 0;
		if (selectedComponentStep != null) {
			selectedComponentStepPosition = selectedComponentStep.getPosition();
		} else {
			if (componentInputParameters.size() > 0) {
				ComponentInputParameter lastTestSuite = componentInputParameters
						.get(componentInputParameters.size() - 1);
				selectedComponentStepPosition = lastTestSuite.getPosition();
			}
		}
		ComponentInputParameter componentInputArgument = new ComponentInputParameter();
		componentInputArgument.setIp_id(Utilities.getInstance().getUniqueUUID(""));
		componentInputArgument.setComponent_id(artifact.getId());
		componentInputArgument.setIsmandatory(true);
		componentInputArgument.setName(variableName);
		componentInputArgument.setType("String");
		componentInputArgument.setPosition(selectedComponentStepPosition + 5);
		componentInputArgument.setAdded(true);
		for (int i = selectedComponentStepIndex + 1; i < componentInputParameters.size(); i++) {
			ComponentInputParameter icomponentStep = componentInputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return componentInputArgument;
	}

	public ComponentOutputParameter createComponentOutputParameterDTO(ArtifactDTO artifact, String variableName,
			ComponentOutputParameter selectedComponentStep, List<ComponentOutputParameter> componentOuputParameters) {

		int selectedComponentStepIndex = componentOuputParameters.indexOf(selectedComponentStep);
		int selectedComponentStepPosition = 0;
		if (selectedComponentStep != null) {
			selectedComponentStepPosition = selectedComponentStep.getPosition();
		} else {
			if (componentOuputParameters.size() > 0) {
				ComponentOutputParameter lastTestSuite = componentOuputParameters
						.get(componentOuputParameters.size() - 1);
				selectedComponentStepPosition = lastTestSuite.getPosition();
			}
		}
		ComponentOutputParameter componentOutputArgument = new ComponentOutputParameter();
		componentOutputArgument.setOp_id(Utilities.getInstance().getUniqueUUID(""));
		componentOutputArgument.setComponent_id(artifact.getId());
		componentOutputArgument.setName(variableName);
		componentOutputArgument.setType("String");
		componentOutputArgument.setPosition(selectedComponentStepPosition + 5);
		componentOutputArgument.setAdded(true);
		for (int i = selectedComponentStepIndex + 1; i < componentOuputParameters.size(); i++) {
			ComponentOutputParameter icomponentStep = componentOuputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return componentOutputArgument;
	}
}
