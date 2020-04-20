package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.component.CodedFunctionArtifact;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CFLDMaker {
	public CFLInputParameter createCFInputParameterDTO(CodedFunctionArtifact artifact, String variablename,
			CFLInputParameter selectCFInputParameter, List<CFLInputParameter> cflInputParameters) {
		int selectedComponentStepIndex = cflInputParameters.indexOf(selectCFInputParameter);
		int selectedComponentStepPosition = 0;
		if (selectCFInputParameter != null) {
			selectedComponentStepPosition = selectCFInputParameter.getPosition();
		} else {
			if (cflInputParameters.size() > 0) {
				CFLInputParameter lastTestSuite = cflInputParameters.get(cflInputParameters.size() - 1);
				selectedComponentStepPosition = lastTestSuite.getPosition();
			}
		}

		CFLInputParameter cfinputparameter = new CFLInputParameter();
		cfinputparameter.setAdded(true);
		cfinputparameter.setCf_id(artifact.getCflCode().getCf_id());
		cfinputparameter.setName(variablename);
		cfinputparameter.setType("String");
		cfinputparameter.setIp_id(Utilities.getInstance().getUniqueUUID(""));
		cfinputparameter.setPosition(selectedComponentStepPosition + 5);
		
		for (int i = selectedComponentStepIndex + 1; i < cflInputParameters.size(); i++) {
			CFLInputParameter icomponentStep = cflInputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return cfinputparameter;
	}
}
