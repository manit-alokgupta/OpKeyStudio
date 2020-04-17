package pcloudystudio.spytreecomponents;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

public class CapturedMobileElement extends BasicMobileElement {
	private static final long serialVersionUID = -1646188223270630619L;
	private TreeMobileElement link;
	private boolean checked;

	public CapturedMobileElement() {
		this(null);
	}

	public CapturedMobileElement(TreeMobileElement link) {
		this(link, false);
	}

	public CapturedMobileElement(TreeMobileElement link, boolean checked) {
		this.link = link;
		this.checked = checked;
	}

	public TreeMobileElement getLink() {
		return this.link;
	}

	public void setLink(TreeMobileElement link) {
		this.link = link;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
