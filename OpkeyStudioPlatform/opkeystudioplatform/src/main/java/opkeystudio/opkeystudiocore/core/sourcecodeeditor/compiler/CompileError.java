package opkeystudio.opkeystudiocore.core.sourcecodeeditor.compiler;

import javax.tools.JavaFileObject;
import javax.tools.Diagnostic.Kind;

public class CompileError {
	private String code;
	private long columnNumber;
	private long endPosition;
	private long lineNumber;
	private long position;
	private long startPosition;
	private Kind kind;
	private String message;
	private JavaFileObject source;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(long columnNumber) {
		this.columnNumber = columnNumber;
	}

	public long getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(long endPosition) {
		this.endPosition = endPosition;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public long getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(long startPosition) {
		this.startPosition = startPosition;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JavaFileObject getSource() {
		return source;
	}

	public void setSource(JavaFileObject source) {
		this.source = source;
	}
}
