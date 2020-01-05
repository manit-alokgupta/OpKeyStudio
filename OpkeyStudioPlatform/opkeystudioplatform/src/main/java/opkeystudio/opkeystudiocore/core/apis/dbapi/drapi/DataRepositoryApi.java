package opkeystudio.opkeystudiocore.core.apis.dbapi.drapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class DataRepositoryApi {

	@SuppressWarnings("unused")
	public List<DRColumnAttributes> getAllColumnsValues(String dr_id) {
		String query = String.format("SELECT * FROM dr_columns where dr_id='%s' ORDER BY position ASC", dr_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, DRColumnAttributes.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<DRColumnAttributes>();
	}

	private List<DRCellAttributes> getAllCellValues(String dr_id, String column_id) {
		String query = String.format("SELECT * FROM dr_cell where dr_id='%s' and column_id='%s' ORDER BY position ASC",
				dr_id, column_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, DRCellAttributes.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<DRCellAttributes>();
	}

	public List<DRColumnAttributes> getAllDRDatas(String dr_id) {
		List<DRColumnAttributes> drColumns = getAllColumnsValues(dr_id);
		for (DRColumnAttributes drcolumn : drColumns) {
			List<DRCellAttributes> drCellAttributes = getAllCellValues(dr_id, drcolumn.getColumn_id());
			drcolumn.setDrCellAttributes(drCellAttributes);
		}
		return drColumns;
	}

	private List<DRColumnAttributes> getAllDrColumns(String columnId) {
		String query = String.format("SELECT * FROM dr_columns where column_id='%s' ORDER BY position ASC", columnId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, DRColumnAttributes.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<DRColumnAttributes>();
	}

	public DRColumnAttributes getDRColumn(String columnId) {
		return this.getAllDrColumns(columnId).get(0);
	}
}
