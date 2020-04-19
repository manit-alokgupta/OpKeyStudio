package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FunctionLibraryMaker {
	public ComponentInputArgument createComponentInputParameterDTO(Artifact artifact, String variableName,
			ComponentInputArgument selectedComponentStep, List<ComponentInputArgument> componentInputParameters) {

		int selectedComponentStepIndex = componentInputParameters.indexOf(selectedComponentStep);
		int selectedComponentStepPosition = 0;
		if (selectedComponentStep != null) {
			selectedComponentStepPosition = selectedComponentStep.getPosition();
		} else {
			if (componentInputParameters.size() > 0) {
				ComponentInputArgument lastTestSuite = componentInputParameters
						.get(componentInputParameters.size() - 1);
				selectedComponentStepPosition = lastTestSuite.getPosition();
			}
		}
		ComponentInputArgument componentInputArgument = new ComponentInputArgument();
		componentInputArgument.setIp_id(Utilities.getInstance().getUniqueUUID(""));
		componentInputArgument.setComponent_id(artifact.getId());
		componentInputArgument.setIsmandatory(true);
		componentInputArgument.setName(variableName);
		componentInputArgument.setType("String");
		componentInputArgument.setPosition(selectedComponentStepPosition + 5);
		componentInputArgument.setAdded(true);
		for (int i = selectedComponentStepIndex + 1; i < componentInputParameters.size(); i++) {
			ComponentInputArgument icomponentStep = componentInputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return componentInputArgument;
	}

	public ComponentOutputArgument createComponentOutputParameterDTO(Artifact artifact, String variableName,
			ComponentOutputArgument selectedComponentStep, List<ComponentOutputArgument> componentOuputParameters) {

		int selectedComponentStepIndex = componentOuputParameters.indexOf(selectedComponentStep);
		int selectedComponentStepPosition = 0;
		if (selectedComponentStep != null) {
			selectedComponentStepPosition = selectedComponentStep.getPosition();
		} else {
			if (componentOuputParameters.size() > 0) {
				ComponentOutputArgument lastTestSuite = componentOuputParameters
						.get(componentOuputParameters.size() - 1);
				selectedComponentStepPosition = lastTestSuite.getPosition();
			}
		}
		ComponentOutputArgument componentOutputArgument = new ComponentOutputArgument();
		componentOutputArgument.setOp_id(Utilities.getInstance().getUniqueUUID(""));
		componentOutputArgument.setComponent_id(artifact.getId());
		componentOutputArgument.setName(variableName);
		componentOutputArgument.setType("String");
		componentOutputArgument.setPosition(selectedComponentStepPosition + 5);
		componentOutputArgument.setAdded(true);
		for (int i = selectedComponentStepIndex + 1; i < componentOuputParameters.size(); i++) {
			ComponentOutputArgument icomponentStep = componentOuputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return componentOutputArgument;
	}
}
