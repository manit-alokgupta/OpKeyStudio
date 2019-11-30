package opkeystudio.opkeystudiocore.core.keywordmanager;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class KeywordManager {
	private static KeywordManager manager;
	private List<Keyword> allKeywords = new ArrayList<>();
	private Map<String, List<Keyword>> allGroupedKeywords = new HashMap<>();

	public static KeywordManager getInstance() {
		if (manager == null) {
			manager = new KeywordManager();
		}
		return manager;
	}

	public void loadAllKeywords() throws JsonParseException, JsonMappingException, SQLException, IOException {
		String keywordDirPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "GenericDB";
		File keywordDirFolder = new File(keywordDirPath);
		File[] keywordsDBFiles = keywordDirFolder.listFiles();
		for (File keywordsDBFile : keywordsDBFiles) {
			List<KeyWordInputArgument> allKeywordInputArguments = new KeywordLoader()
					.loadAllKeywordArguments(keywordsDBFile.getAbsolutePath());
			List<Keyword> allKeywords = new KeywordLoader().loadKeywords(keywordsDBFile.getAbsolutePath());
			for (Keyword keyword : allKeywords) {
				List<KeyWordInputArgument> keywordInputArguments = new ArrayList<KeyWordInputArgument>();
				for (KeyWordInputArgument keyWordInputArgument : allKeywordInputArguments) {
					if (keyword.getKeywordid().equals(keyWordInputArgument.getKeywordid())) {
						keywordInputArguments.add(keyWordInputArgument);
					}
				}
				keyword.setPluginName(keywordsDBFile.getName());
				keyword.setKeywordInputArguments(keywordInputArguments);
			}
			addAllKeyWords(allKeywords);
			addAllKeywordsInGroup(allKeywords);
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

	public Set<String> getAllGroupNames() {
		return getAllGroupedKeywords().keySet();
	}

	public List<Keyword> getKeywordGroup(String groupName) {
		return getAllGroupedKeywords().get(groupName);
	}

	private void addAllKeywordsInGroup(List<Keyword> allKeyowrds) {
		for (Keyword keyword : allKeyowrds) {
			addKeywordInGroup(keyword.getPluginName(), keyword);
		}
	}

	private Map<String, List<Keyword>> getAllGroupedKeywords() {
		return allGroupedKeywords;
	}

	private void setAllGroupedKeywords(Map<String, List<Keyword>> allGroupedKeywords) {
		this.allGroupedKeywords = allGroupedKeywords;
	}

	private void addKeywordInGroup(String groupName, Keyword keyword) {
		if (getAllGroupedKeywords().containsKey(groupName)) {
			List<Keyword> groupedKeywords = getAllGroupedKeywords().get(groupName);
			groupedKeywords.add(keyword);
			return;
		}

		List<Keyword> groupedKeywords = new ArrayList<Keyword>();
		groupedKeywords.add(keyword);
		getAllGroupedKeywords().put(groupName, groupedKeywords);
	}

}
