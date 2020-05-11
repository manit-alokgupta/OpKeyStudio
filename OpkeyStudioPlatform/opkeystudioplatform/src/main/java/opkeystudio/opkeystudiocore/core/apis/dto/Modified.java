package opkeystudio.opkeystudiocore.core.apis.dto;

import opkeystudio.opkeystudiocore.core.query.DBField;

public class Modified {
	@DBField
	private boolean modified;
	@DBField
	private boolean deleted;
	@DBField
	private boolean added;
	@DBField
	private boolean visible = true;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

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
		if (deleted) {
			this.modified = false;
			this.added = false;
		}
		this.deleted = deleted;
	}

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}
}
