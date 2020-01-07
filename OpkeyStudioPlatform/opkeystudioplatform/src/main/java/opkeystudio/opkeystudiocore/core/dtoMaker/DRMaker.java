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
		DRColumnAttributes drColumnAttribute = new DRColumnAttributes();
		drColumnAttribute.setColumn_id(Utilities.getInstance().getUniqueUUID(""));
		drColumnAttribute.setDr_id(artifact.getId());
		drColumnAttribute.setName("");
		drColumnAttribute.setPosition(0);
		drColumnAttribute.setIsencrypted(false);

		List<DRCellAttributes> drCellAttributes = new ArrayList<DRCellAttributes>();
		for (int i = 0; i < noOfCells; i++) {
			DRCellAttributes drCellAttribute = new DRCellAttributes();
			drCellAttribute.setPosition((i + 1) * 10);
			drCellAttributes.add(drCellAttribute);
		}
		return null;
	}
}
