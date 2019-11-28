package opkeystudio.opkeystudiocore.core.apis.dbapi.drapi;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class DataRepositoryApi {

	@SuppressWarnings("unused")
	public List<DRColumnAttributes> getAllColumnsValues(String columnId)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM dr_columns where column_id='%s' ORDER BY position", columnId);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);

	}

//	private List<DR_Cells> getAllCellValues(String ){
//		
//	}
}
