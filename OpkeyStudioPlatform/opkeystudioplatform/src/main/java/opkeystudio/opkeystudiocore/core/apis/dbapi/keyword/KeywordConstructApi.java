package opkeystudio.opkeystudiocore.core.apis.dbapi.keyword;

import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;

public class KeywordConstructApi {
	public void insertKeyword(Keyword keyword) {
		if (keyword == null) {
			return;
		}
		String query = new QueryMaker().createInsertQuery(keyword, "main_keywords", "");
		query = query.replaceAll(" class1,", " class,");
		QueryExecutor.getInstance().executeUpdateQuery(query);
		for (KeyWordInputArgument keyInput : keyword.getKeywordInputArguments()) {
			String inpquery = new QueryMaker().createInsertQuery(keyInput, "main_keywordarguments", "");
			QueryExecutor.getInstance().executeUpdateQuery(inpquery);
		}
	}
}
