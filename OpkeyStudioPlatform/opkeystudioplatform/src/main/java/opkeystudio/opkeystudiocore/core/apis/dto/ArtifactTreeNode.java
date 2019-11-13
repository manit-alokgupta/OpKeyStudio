package opkeystudio.opkeystudiocore.core.apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import opkeystudio.opkeystudiocore.core.queryMaker.DBField;

class State {
	@DBField
	private boolean opened;
	@DBField
	private boolean disabled;
	@DBField
	private boolean selected;

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

public class ArtifactTreeNode {
	private String text;
	private String icon;
	private String type;
	private String id;
	private String parent;
	private String data;
	private String dto;
	private boolean IsRestricted;
	private boolean IsFreezed;
	private boolean IsShared;
	private String a_attr;
	
	@JsonIgnore
	private State state;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDto() {
		return dto;
	}

	public void setDto(String dto) {
		this.dto = dto;
	}

	public boolean isIsRestricted() {
		return IsRestricted;
	}

	public void setIsRestricted(boolean isRestricted) {
		IsRestricted = isRestricted;
	}

	public boolean isIsFreezed() {
		return IsFreezed;
	}

	public void setIsFreezed(boolean isFreezed) {
		IsFreezed = isFreezed;
	}

	public boolean isIsShared() {
		return IsShared;
	}

	public void setIsShared(boolean isShared) {
		IsShared = isShared;
	}

	public String getA_attr() {
		return a_attr;
	}

	public void setA_attr(String a_attr) {
		this.a_attr = a_attr;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
