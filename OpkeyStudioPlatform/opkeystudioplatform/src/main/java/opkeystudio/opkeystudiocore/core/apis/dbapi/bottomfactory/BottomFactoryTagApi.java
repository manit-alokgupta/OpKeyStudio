package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTags;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryTagApi {

	private List<BottomFactoryTags> getAllBottomFactoryTags(String id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM main_tags where id='%s' ORDER BY position", id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);
	}

	private void deleteBottomFactoryTags(BottomFactoryTags tags) {
		if (tags.isDeleted()) {
			System.out.println("" + tags.getId());
			String query = String.format("delete from main_tags where tag_id='%s'", tags.getTag_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateBottomFactoryTags(BottomFactoryTags tags) {
		if (tags.isModified()) {
			String query = new QueryMaker().createUpdateQuery(tags, "main_tags",
					String.format("WHERE tag_id='%s'", tags.getTag_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addBottomFactoryTags(BottomFactoryTags tags) {
		if (tags.isAdded()) {
			String query = new QueryMaker().createInsertQuery(tags, "main_tags", null);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public void saveAllBottomFactoryTags(List<BottomFactoryTags> tags) {
		for (BottomFactoryTags tag : tags) {
			String updateQuery = new QueryMaker().createUpdateQuery(tag, "main_tags",
					String.format("WHERE id='%s'", tag.getId()));
			System.out.println("update Query:- " + updateQuery);
			String addQuery = new QueryMaker().createInsertQuery(tag, "main_tags", null);
			System.out.println("Add Query:- " + addQuery);

			deleteBottomFactoryTags(tag);
			updateBottomFactoryTags(tag);
			addBottomFactoryTags(tag);
		}
	}

}
