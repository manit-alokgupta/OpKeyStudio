package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class DRMaker {
	public DRColumnAttributes createDRColumnWithCells(Artifact artifact, DRColumnAttributes selectedDRColumn,
			int noOfCells, List<DRColumnAttributes> allDRColumnttributes) {
		int selectedFlowStepIndex = allDRColumnttributes.indexOf(selectedDRColumn);
		int selectedFlowStepPosition = 0;
		if (selectedDRColumn != null) {
			selectedFlowStepPosition = selectedDRColumn.getPosition();
		}
		DRColumnAttributes drColumnAttribute = new DRColumnAttributes();
		drColumnAttribute.setColumn_id(Utilities.getInstance().getUniqueUUID(""));
		drColumnAttribute.setDr_id(artifact.getId());
		drColumnAttribute.setName("");
		drColumnAttribute.setPosition(selectedFlowStepPosition + 5);
		drColumnAttribute.setIsencrypted(false);
		drColumnAttribute.setAdded(true);
		List<DRCellAttributes> drCellAttributes = new ArrayList<DRCellAttributes>();
		for (int i = 0; i < noOfCells; i++) {
			DRCellAttributes drCellAttribute = new DRCellAttributes();
			drCellAttribute.setDr_cell_id(Utilities.getInstance().getUniqueUUID(""));
			drCellAttribute.setDr_id(drColumnAttribute.getDr_id());
			drCellAttribute.setColumn_id(drColumnAttribute.getColumn_id());
			drCellAttribute.setPosition((i + 1) * 10);
			drCellAttribute.setAdded(true);
			drCellAttributes.add(drCellAttribute);
		}
		drColumnAttribute.setDrCellAttributes(drCellAttributes);
		for (int i = selectedFlowStepIndex + 1; i < allDRColumnttributes.size(); i++) {
			DRColumnAttributes iflowStep = allDRColumnttributes.get(i);
			iflowStep.setPosition(iflowStep.getPosition() + 10);
			iflowStep.setModified(true);
		}
		return drColumnAttribute;
	}
}
