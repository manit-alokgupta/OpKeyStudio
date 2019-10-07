package opkeystudio.opkeystudiocore.core.models.testcasemodel;

public class KeyWord {
	private String keywordName;
	private String keywordMethod;

	public KeyWord(String keywordName, String keyMethod) {
		setKeywordName(keywordName);
		setKeywordMethod(keyMethod);
	}

	public String getKeywordName() {
		return keywordName;
	}

	private void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public String getKeywordMethod() {
		return keywordMethod;
	}

	private void setKeywordMethod(String keywordMethod) {
		this.keywordMethod = keywordMethod;
	}
}
