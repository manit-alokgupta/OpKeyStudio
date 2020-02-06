package opkeystudio.opkeystudiocore.core.apis.dbapi.drapi;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;

public class DataRepositoryConstructApi {
	public void saveAllDRColumns(List<DRColumnAttributes> columnAattributes) {
		for (DRColumnAttributes drColumnAttribute : columnAattributes) {
			deleteDRColumn(drColumnAttribute);
			addDRColumn(drColumnAttribute);
			updateDRColumn(drColumnAttribute);
			saveALLDRCellAttributes(drColumnAttribute.getDrCellAttributes());
		}
		GlobalLoader.getInstance().initAllDRColumns();
		GlobalLoader.getInstance().initALLDRCells();
	}

	private void saveALLDRCellAttributes(List<DRCellAttributes> drCellAattributes) {
		for (DRCellAttributes drCellAttribute : drCellAattributes) {
			deleteDRCell(drCellAttribute);
			addDRCell(drCellAttribute);
			updateDRCell(drCellAttribute);
		}
	}

	private void deleteDRColumn(DRColumnAttributes columnAttribute) {
		if (columnAttribute.isDeleted()) {
			String query = String.format("delete from dr_columns WHERE Column_ID='%s'", columnAttribute.getColumn_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}

	}

	public void addDRColumn(DRColumnAttributes columnAttribute) {
		if (columnAttribute.isAdded()) {
			String query = new QueryMaker().createInsertQuery(columnAttribute, "dr_columns", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public String get_addDRColumnQuery(DRColumnAttributes columnAttribute) {
		if (columnAttribute.isAdded()) {
			String query = new QueryMaker().createInsertQuery(columnAttribute, "dr_columns", "");
			return query;
		}
		return "";
	}

	private void updateDRColumn(DRColumnAttributes columnAttribute) {
		if (columnAttribute.isModified()) {
			String query = new QueryMaker().createUpdateQuery(columnAttribute, "dr_columns",
					String.format("WHERE Column_id='%s'", columnAttribute.getColumn_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void deleteDRCell(DRCellAttributes drCellAttribute) {
		if (drCellAttribute.isDeleted()) {
			String query = String.format("delete from dr_cell WHERE DR_Cell_ID='%s'", drCellAttribute.getDr_cell_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public void addDRCell(DRCellAttributes drCellAttribute) {
		if (drCellAttribute.isAdded()) {
			String query = new QueryMaker().createInsertQuery(drCellAttribute, "dr_cell", "");
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public String get_addDRCell(DRCellAttributes drCellAttribute) {
		if (drCellAttribute.isAdded()) {
			String query = new QueryMaker().createInsertQuery(drCellAttribute, "dr_cell", "");
			return query;
		}
		return "";
	}

	private void updateDRCell(DRCellAttributes drCellAttribute) {
		if (drCellAttribute.isModified()) {
			String query = new QueryMaker().createUpdateQuery(drCellAttribute, "dr_cell",
					String.format("WHERE DR_Cell_ID='%s'", drCellAttribute.getDr_cell_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}
}
