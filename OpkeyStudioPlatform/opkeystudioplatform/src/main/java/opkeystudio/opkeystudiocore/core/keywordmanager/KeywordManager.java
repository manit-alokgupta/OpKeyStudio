package opkeystudio.opkeystudiocore.core.keywordmanager;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class KeywordManager {
	private static KeywordManager manager;
	private List<Keyword> allKeywords = new ArrayList<>();

	public static KeywordManager getInstance() {
		if (manager == null) {
			manager = new KeywordManager();
		}
		return manager;
	}

	public void loadAllKeywords() throws JsonParseException, JsonMappingException, SQLException, IOException {
		String keywordDirPath = "E:\\ExportedArtifactsNeon\\GenericDB";
		File keywordDirFolder = new File(keywordDirPath);
		File[] keywordsDBFiles = keywordDirFolder.listFiles();
		for (File keywordsDBFile : keywordsDBFiles) {
			List<KeyWordInputArgument> allKeywordInputArguments = new KeywordLoader()
					.loadAllKeywordArguments(keywordsDBFile.getAbsolutePath());
			List<Keyword> allKeywords = new KeywordLoader().loadKeywords(keywordsDBFile.getAbsolutePath());
			for (Keyword keyword : allKeywords) {
				List<KeyWordInputArgument> keywordInputArguments = new ArrayList<KeyWordInputArgument>();
				for (KeyWordInputArgument keyWordInputArgument : keywordInputArguments) {
					if (keyword.getKeywordid().equals(keyWordInputArgument.getKeywordid())) {
						keywordInputArguments.add(keyWordInputArgument);
					}
				}
				keyword.setKeywordInputArguments(allKeywordInputArguments);
			}
			addAllKeyWords(allKeywords);
		}
	}

	private void addAllKeyWords(List<Keyword> keywords) {
		this.allKeywords.addAll(keywords);
	}

	public List<Keyword> getAllKeywords() {
		return this.allKeywords;
	}

	public Keyword getKeyword(String keywordId) {
		for (Keyword keyword : getAllKeywords()) {
			if (keyword.getKeywordid().equals(keywordId)) {
				return keyword;
			}
		}
		return null;
	}

	public List<Keyword> getKeywordArguments(String keywordId) {
		List<Keyword> keywords = new ArrayList<>();
		for (Keyword keyword : getAllKeywords()) {
			if (keyword.getKeywordid().trim().equals(keywordId.trim())) {
				keywords.add(keyword);
			}
		}
		return keywords;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, SQLException, IOException {
		new KeywordManager().loadAllKeywords();
	}
}
