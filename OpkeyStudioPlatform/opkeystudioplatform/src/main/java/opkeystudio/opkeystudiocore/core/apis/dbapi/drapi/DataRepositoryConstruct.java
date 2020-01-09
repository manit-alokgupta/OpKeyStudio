package opkeystudio.opkeystudiocore.core.apis.dbapi.drapi;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;

public class DataRepositoryConstruct {
	public void saveAllDRColumns(List<DRColumnAttributes> columnAattributes) {
		for (DRColumnAttributes drColumnAttribute : columnAattributes) {
			deleteDRColumn(drColumnAttribute);
			addDRColumn(drColumnAttribute);
			updateDRColumn(drColumnAttribute);
			saveALLDRCellAttributes(drColumnAttribute.getDrCellAttributes());
		}
	}

	private void saveALLDRCellAttributes(List<DRCellAttributes> drCellAattributes) {
		for(DRCellAttributes drCellAttribute:drCellAattributes)
		{
			deleteDRCell(drCellAttribute);
			addDRCell(drCellAttribute);
			updateDRCell(drCellAttribute);
		}
	}

	private void deleteDRColumn(DRColumnAttributes columnAttribute) {

	}

	private void addDRColumn(DRColumnAttributes columnAttribute) {

	}

	private void updateDRColumn(DRColumnAttributes columnAttribute) {

	}

	private void deleteDRCell(DRCellAttributes drCellAttribute) {

	}

	private void addDRCell(DRCellAttributes drCellAttribute) {

	}

	private void updateDRCell(DRCellAttributes drCellAttribute) {

	}
}
