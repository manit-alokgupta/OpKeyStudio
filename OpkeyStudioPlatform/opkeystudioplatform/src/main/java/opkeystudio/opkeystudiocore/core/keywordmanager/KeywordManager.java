package opkeystudio.opkeystudiocore.core.keywordmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import opkeystudio.opkeystudiocore.core.exceptions.SetupConfigurationException;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class KeywordManager {
	private static KeywordManager manager;
	private List<Keyword> allKeywords = new ArrayList<>();
	private Map<String, List<Keyword>> allGroupedKeywords = new HashMap<>();
	private String[] allowedPlugins = new String[] { "Appium Keywords", "Control Flow Constructs",
			"OpKey Generic Keywords", "System Keywords", "Web Keywords" };

	public static KeywordManager getInstance() {
		if (manager == null) {
			manager = new KeywordManager();
		}
		return manager;
	}

	private void keywordContainsORObject(Keyword keyword) {
		for (KeyWordInputArgument kinarg : keyword.getKeywordInputArguments()) {
			if (kinarg.getDatatype().equals("ORObject")) {
				keyword.setKeywordContainsORObject(true);
				break;
			}
		}
	}

	public void loadAllKeywords() throws SetupConfigurationException {
		String keywordDirPath = Utilities.getInstance().getDefaultInstallDir() + File.separator + "resources"
				+ File.separator + "GenericDB";
		if (!new File(keywordDirPath).exists()) {
			keywordDirPath = Utilities.getInstance().getDefaultWorkSpacePath() + File.separator + "GenericDB";
		}
		File keywordDirFolder = new File(keywordDirPath);
		File[] keywordsDBFiles = keywordDirFolder.listFiles();
		if(keywordsDBFiles == null)
			throw new SetupConfigurationException("Keywords Db Dir empty? " + keywordDirPath);
		
		for (File keywordsDBFile : keywordsDBFiles) {
			List<KeyWordInputArgument> allKeywordInputArguments = new KeywordLoader()
					.loadAllKeywordInputArguments(keywordsDBFile.getAbsolutePath());
			List<Keyword> allKeywords = new KeywordLoader().loadKeywords(keywordsDBFile.getAbsolutePath());
			for (Keyword keyword : allKeywords) {
				List<KeyWordInputArgument> keywordInputArguments = new ArrayList<KeyWordInputArgument>();
				for (KeyWordInputArgument keyWordInputArgument : allKeywordInputArguments) {
					if (keyword.getKeywordid().equals(keyWordInputArgument.getKeywordid())) {
						keywordInputArguments.add(keyWordInputArgument);
					}
				}
				keyword.setPluginName(keywordsDBFile.getName().replaceAll(".db", ""));
				keyword.setKeywordInputArguments(keywordInputArguments);
				keywordContainsORObject(keyword);
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

	@SuppressWarnings("unused")
	private void setAllGroupedKeywords(Map<String, List<Keyword>> allGroupedKeywords) {
		this.allGroupedKeywords = allGroupedKeywords;
	}

	private void addKeywordInGroup(String groupName, Keyword keyword) {
		if (!isGroupNameAllowed(groupName)) {
			return;
		}
		if (getAllGroupedKeywords().containsKey(groupName)) {
			List<Keyword> groupedKeywords = getAllGroupedKeywords().get(groupName);
			groupedKeywords.add(keyword);
			return;
		}

		List<Keyword> groupedKeywords = new ArrayList<Keyword>();
		groupedKeywords.add(keyword);
		getAllGroupedKeywords().put(groupName, groupedKeywords);
	}

	private boolean isGroupNameAllowed(String keywordGroupName) {
		for (String groupName : this.allowedPlugins) {
			groupName = groupName.toLowerCase().trim().replaceAll(" ", "");
			keywordGroupName = keywordGroupName.toLowerCase().trim().replaceAll(" ", "");
			if (groupName.equals(keywordGroupName)) {
				return true;
			}
		}
		return false;
	}

}
