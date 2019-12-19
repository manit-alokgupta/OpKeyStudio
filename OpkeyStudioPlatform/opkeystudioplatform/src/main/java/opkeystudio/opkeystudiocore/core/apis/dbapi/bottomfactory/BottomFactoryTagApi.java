package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.BottomFactoryTag;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryTagApi {

	private List<BottomFactoryTag> getBottomFactoryTag(String id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format("SELECT * FROM main_tags where id='%s' ORDER BY position", id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, BottomFactoryTag.class);
		return mapper.readValue(result, type);
	}

	private FunctionLibraryComponent getFunctionLibraryComponent(String id)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		return new FunctionLibraryApi().getFunctinLibraryComponent(id).get(0);
	}

	public List<BottomFactoryTag> getAllBottomFactoryTag(String id)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		List<BottomFactoryTag> bottomFactoryTags = getBottomFactoryTag(id);
		for (BottomFactoryTag bottomFactoryTag : bottomFactoryTags) {
			if (bottomFactoryTag.getId() != null) {
				FunctionLibraryComponent functionLibraryComponent = getFunctionLibraryComponent(
						bottomFactoryTag.getId());
				bottomFactoryTag.setFunctionLibraryComponent(functionLibraryComponent);
			}
		}
		return bottomFactoryTags;
	}

	private void deleteBottomFactoryTag(BottomFactoryTag tags) {
		if (tags.isDeleted()) {
			String query = String.format("delete from main_tags where tag_id='%s'", tags.getTag_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateBottomFactoryTag(BottomFactoryTag tags) {
		if (tags.isModified()) {
			String query = new QueryMaker().createUpdateQuery(tags, "main_tags",
					String.format("WHERE tag_id='%s'", tags.getTag_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addBottomFactoryTag(BottomFactoryTag tags) {
		if (tags.isAdded()) {
			String query = new QueryMaker().createInsertQuery(tags, "main_tags", null);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public void saveAllBottomFactoryTag(List<BottomFactoryTag> tags) {
		for (BottomFactoryTag tag : tags) {
			String updateQuery = new QueryMaker().createUpdateQuery(tag, "main_tags",
					String.format("WHERE id='%s'", tag.getId()));
			String addQuery = new QueryMaker().createInsertQuery(tag, "main_tags", null);
			deleteBottomFactoryTag(tag);
			updateBottomFactoryTag(tag);
			addBottomFactoryTag(tag);
		}
	}

	public void insertTagData(BottomFactoryTag bottomFactoryTag) {
		try {
			SQLiteCommunicator sqlComm = new SQLiteCommunicator();

			sqlComm.connect();

			String query = String.format(
					"insert into main_tags(tag_id,p_id_denormalized,id,key,value,position) VALUES('%s','%s','%s','%s','%s','%s')",
					bottomFactoryTag.getTag_id(), bottomFactoryTag.getP_id_denormalized(), bottomFactoryTag.getId(),
					bottomFactoryTag.getKey(), bottomFactoryTag.getValue(), bottomFactoryTag.getPosition());
			int result = sqlComm.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
