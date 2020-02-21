package pcloudystudio.objectspy.element.impl;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import pcloudystudio.objectspy.element.TreeMobileElement;

public class CapturedMobileElement extends BasicMobileElement {
	private static final long serialVersionUID = 9135829243722317270L;
	private TreeMobileElement link;
	private boolean checked;

	public CapturedMobileElement() {
		this(null);
	}

	public CapturedMobileElement(final TreeMobileElement link) {
		this(link, false);
	}

	public CapturedMobileElement(final TreeMobileElement link, final boolean checked) {
		this.link = link;
		this.checked = checked;
	}

	public TreeMobileElement getLink() {
		return this.link;
	}

	public void setLink(final TreeMobileElement link) {
		this.link = link;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(final boolean checked) {
		this.checked = checked;
	}
}
