package opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools;

public class Token {
	private String tokenName;
	private int tokenStartIndex = 0;
	private int tokenEndIndex = 0;

	public Token(String tokenName, int startIndex, int endIndex) {
		this.setTokenName(tokenName);
		this.setTokenStartIndex(startIndex);
		this.setTokenEndIndex(endIndex);
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public int getTokenStartIndex() {
		return tokenStartIndex;
	}

	public void setTokenStartIndex(int tokenStartIndex) {
		this.tokenStartIndex = tokenStartIndex;
	}

	public int getTokenEndIndex() {
		return tokenEndIndex;
	}

	public void setTokenEndIndex(int tokenEndIndex) {
		this.tokenEndIndex = tokenEndIndex;
	}
}
