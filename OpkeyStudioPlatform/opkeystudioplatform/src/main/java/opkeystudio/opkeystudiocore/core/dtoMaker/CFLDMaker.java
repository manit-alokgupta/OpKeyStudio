package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
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
		cfinputparameter.setComponent_id(artifact.getCflCode().getComponent_id());
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
	
	public CFLOutputParameter createCFOutputParameterDTO(CodedFunctionArtifact artifact, String variablename,
			CFLOutputParameter selectCFOutputParameter, List<CFLOutputParameter> cflOutputParameters) {
		int selectedComponentStepIndex = cflOutputParameters.indexOf(selectCFOutputParameter);
		int selectedComponentStepPosition = 0;
		if (selectCFOutputParameter != null) {
			selectedComponentStepPosition = selectCFOutputParameter.getPosition();
		} else {
			if (cflOutputParameters.size() > 0) {
				CFLOutputParameter lastTestSuite = cflOutputParameters.get(cflOutputParameters.size() - 1);
				selectedComponentStepPosition = lastTestSuite.getPosition();
			}
		}

		CFLOutputParameter cfinputparameter = new CFLOutputParameter();
		cfinputparameter.setAdded(true);
		cfinputparameter.setComponent_id(artifact.getCflCode().getComponent_id());
		cfinputparameter.setName(variablename);
		cfinputparameter.setType("String");
		cfinputparameter.setOp_id(Utilities.getInstance().getUniqueUUID(""));
		cfinputparameter.setPosition(selectedComponentStepPosition + 5);
		
		for (int i = selectedComponentStepIndex + 1; i < cflOutputParameters.size(); i++) {
			CFLOutputParameter icomponentStep = cflOutputParameters.get(i);
			icomponentStep.setPosition(icomponentStep.getPosition() + 10);
			icomponentStep.setModified(true);
		}
		return cfinputparameter;
	}
}
