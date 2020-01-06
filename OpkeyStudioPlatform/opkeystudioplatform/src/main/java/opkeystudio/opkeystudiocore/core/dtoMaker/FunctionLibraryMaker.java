package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FunctionLibraryMaker {
	public ComponentInputArgument createComponentInputParameterDTO(Artifact artifact,
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
		componentInputArgument.setPosition(selectedComponentStepPosition + 5);
		componentInputArgument.setAdded(true);
		for (int i = selectedComponentStepIndex + 1; i < componentInputParameters.size(); i++) {
			ComponentInputArgument icomponentStep = componentInputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return componentInputArgument;
	}
}
