package opkeystudio.opkeystudiocore.core.keywordmanager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class KeywordLoader {
	public List<Keyword> loadKeywords(String dbPath) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator(dbPath);
		try {
			sqlComm.connect();
			String results = sqlComm.executeQueryString("SELECT * FROM main_keywords t1");
			ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Keyword.class);
			return mapper.readValue(results, type);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ArrayList<Keyword>();
	}

	public List<KeyWordInputArgument> loadAllKeywordInputArguments(String dbPath) {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator(dbPath);
		try {
			sqlComm.connect();
			String results = sqlComm
					.executeQueryString("SELECT * FROM main_keywordarguments \r\n" + "ORDER BY Position asc");
			sqlComm.disconnect();
			ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
					KeyWordInputArgument.class);
			return mapper.readValue(results, type);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ArrayList<KeyWordInputArgument>();
	}
}
