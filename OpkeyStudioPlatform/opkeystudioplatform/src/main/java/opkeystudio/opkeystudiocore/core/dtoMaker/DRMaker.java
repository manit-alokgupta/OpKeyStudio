package opkeystudio.opkeystudiocore.core.dtoMaker;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
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

	public List<DRColumnAttributes> getDefaultDRStructure(Artifact artifact) {
		List<DRColumnAttributes> allColumnAttributes = new ArrayList<DRColumnAttributes>();
		for (int i = 0; i < 26; i++) {
			DRColumnAttributes drColumnAttribute = new DRColumnAttributes();
			drColumnAttribute.setColumn_id(Utilities.getInstance().getUniqueUUID(""));
			drColumnAttribute.setDr_id(artifact.getId());
			drColumnAttribute.setName("Column-" + i);
			drColumnAttribute.setPosition(i * 10);
			drColumnAttribute.setIsencrypted(false);
			drColumnAttribute.setAdded(true);

			List<DRCellAttributes> drCellAttributes = new ArrayList<DRCellAttributes>();
			for (int j = 0; j < 26; j++) {
				DRCellAttributes drCellAttribute = new DRCellAttributes();
				drCellAttribute.setDr_cell_id(Utilities.getInstance().getUniqueUUID(""));
				drCellAttribute.setDr_id(drColumnAttribute.getDr_id());
				drCellAttribute.setColumn_id(drColumnAttribute.getColumn_id());
				drCellAttribute.setPosition(j * 10);
				drCellAttribute.setAdded(true);
				drCellAttributes.add(drCellAttribute);
			}
			drColumnAttribute.setDrCellAttributes(drCellAttributes);
			allColumnAttributes.add(drColumnAttribute);

		}
		return allColumnAttributes;
	}

	public void addDRRow(Artifact artifact, int selectedRowNo, List<DRColumnAttributes> columnAttributes) {
		for (DRColumnAttributes columnAttribute : columnAttributes) {
			if (columnAttribute.getDrCellAttributes().size() == 0) {
				System.out.println("NO DR CELL FOUND " + columnAttribute.getName());
				continue;
			}
			DRCellAttributes selectedDRCell = columnAttribute.getDrCellAttributes().get(selectedRowNo);
			int nextRowNo = selectedRowNo + 1;
			for (int i = nextRowNo; i < columnAttribute.getDrCellAttributes().size(); i++) {
				DRCellAttributes drCellAttribute = columnAttribute.getDrCellAttributes().get(i);
				drCellAttribute.setPosition(drCellAttribute.getPosition() + 50);
				drCellAttribute.setModified(true);
			}
			DRCellAttributes newDrCellAttribute = new DRCellAttributes();
			newDrCellAttribute.setPosition(selectedDRCell.getPosition() + 5);
			newDrCellAttribute.setDr_id(artifact.getId());
			newDrCellAttribute.setColumn_id(columnAttribute.getColumn_id());
			newDrCellAttribute.setDr_cell_id(Utilities.getInstance().getUniqueUUID(""));
			newDrCellAttribute.setAdded(true);
			columnAttribute.getDrCellAttributes().add(newDrCellAttribute);
		}
	}
}
