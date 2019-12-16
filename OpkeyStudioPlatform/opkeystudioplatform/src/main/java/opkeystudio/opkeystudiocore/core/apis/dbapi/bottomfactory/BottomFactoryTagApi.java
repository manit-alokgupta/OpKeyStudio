package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Tags;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryTagApi {


	private List<Tags> getAllBottomFactoryTags(String id) throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM main_tags where component_id='%s' ORDER BY position", id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);
	}

	
}
