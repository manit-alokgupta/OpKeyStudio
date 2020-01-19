package opkeystudio.opkeystudiocore.core.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools.SourceCodeEditorTools;

public class ContentAssistData {
	private int maxQueueSize;

	private List wordBuffer;

	private Map knownWords = new HashMap();
	private static ContentAssistData assistData;

	public static ContentAssistData getInstance() {
		if (assistData == null) {
			assistData = new ContentAssistData(1000);
			String[] dtokens = SourceCodeEditorTools.getInstance().getDefaultTokens();
			for (String dtoken : dtokens) {
				assistData.add(dtoken);
			}
		}
		return assistData;
	}

	public ContentAssistData(int queueSize) {
		maxQueueSize = queueSize;
		wordBuffer = new LinkedList();
	}

	public int getWordCount() {
		return wordBuffer.size();
	}

	public void add(String word) {
		if (wordIsNotKnown(word)) {
			flushOldestWord();
			insertNewWord(word);
		}
	}

	private void insertNewWord(String word) {
		wordBuffer.add(0, word);
		knownWords.put(word, word);
	}

	private void flushOldestWord() {
		if (wordBuffer.size() == maxQueueSize) {
			String removedWord = (String) wordBuffer.remove(maxQueueSize - 1);
			knownWords.remove(removedWord);
		}
	}

	private boolean wordIsNotKnown(String word) {
		return knownWords.get(word) == null;
	}

	public List suggest(String word) {
		List suggestions = new LinkedList();
		for (Iterator i = wordBuffer.iterator(); i.hasNext();) {
			String currWord = (String) i.next();
			if (currWord.startsWith(word)) {
				suggestions.add(currWord);
			}
		}
		return suggestions;
	}

}
