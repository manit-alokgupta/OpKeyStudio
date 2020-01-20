package opkeystudio.opkeystudiocore.core.apis.dto;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;

public class ArtifactTreeNode {
	private String text;
	private String icon;
	private MODULETYPE type;
	private String id;
	private String parent;
	private String data;
	private String dto;
	private boolean isrestricted;
	private boolean isfreezed;
	private boolean isshared;

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

	public boolean isIsshared() {
		return isshared;
	}

	public void setIsshared(boolean isshared) {
		this.isshared = isshared;
	}

	public boolean isIsfreezed() {
		return isfreezed;
	}

	public void setIsfreezed(boolean isfreezed) {
		this.isfreezed = isfreezed;
	}

	public boolean isIsrestricted() {
		return isrestricted;
	}

	public void setIsrestricted(boolean isrestricted) {
		this.isrestricted = isrestricted;
	}

	public MODULETYPE getType() {
		return type;
	}

	public void setType(MODULETYPE type) {
		this.type = type;
	}
}
