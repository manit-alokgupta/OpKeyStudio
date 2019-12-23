package opkeystudio.opkeystudiocore.core.sourcecodeeditor.tools;

public class Token {
	private String tokenName;
	private int tokenStartIndex = 0;
	private int tokenEndIndex = 0;

	public enum TOKEN_TYPE {
		STRING, INT, BOOL,GENERIC
	};

	private TOKEN_TYPE tokenType;

	public Token(String tokenName, int startIndex, int endIndex, TOKEN_TYPE tokenType) {
		this.setTokenName(tokenName);
		this.setTokenStartIndex(startIndex);
		this.setTokenEndIndex(endIndex);
		this.setTokenType(tokenType);
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

	public TOKEN_TYPE getTokenType() {
		return tokenType;
	}

	public void setTokenType(TOKEN_TYPE tokenType) {
		this.tokenType = tokenType;
	}
}
