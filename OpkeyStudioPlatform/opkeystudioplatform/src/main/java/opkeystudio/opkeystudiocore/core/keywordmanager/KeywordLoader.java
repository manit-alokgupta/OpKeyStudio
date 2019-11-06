package opkeystudio.opkeystudiocore.core.keywordmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class KeywordLoader {
	public List<Keyword> loadKeywords(String dbPath) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator(dbPath);
		try {
			sqlComm.connect();
			String results = sqlComm.executeQueryString(
					"SELECT t1.Name as KeyWordName,t2.Name as ArgumentName,* FROM main_keywords t1\r\n"
							+ " INNER JOIN main_keywordarguments t2 USING(KeywordID) \r\n" + " ORDER BY Position ASC");
			ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Keyword.class);
			sqlComm.disconnect();
			return mapper.readValue(results, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Keyword>();
	}

	public static void main(String[] args) throws SQLException, JsonParseException, JsonMappingException, IOException {
		List<Keyword> keys = new KeywordLoader()
				.loadKeywords("E:\\ExportedArtifactsNeon\\GenericDB\\OpKey Generic Keywords.db");
		System.out.println(keys.get(0).getKeywordname());
	}
}
