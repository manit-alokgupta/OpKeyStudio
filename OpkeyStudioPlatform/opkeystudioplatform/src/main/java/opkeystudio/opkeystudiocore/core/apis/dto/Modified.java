package opkeystudio.opkeystudiocore.core.apis.dto;

public class Modified {
	private boolean modified;
	private boolean deleted;

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
