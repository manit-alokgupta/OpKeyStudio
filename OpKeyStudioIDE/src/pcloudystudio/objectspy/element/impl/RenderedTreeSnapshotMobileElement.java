package pcloudystudio.objectspy.element.impl;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.Map;

import pcloudystudio.objectspy.element.SnapshotMobileElement;
import pcloudystudio.objectspy.element.TreeMobileElement;

import java.util.ArrayList;
import java.util.List;

public abstract class RenderedTreeSnapshotMobileElement<T> extends BasicMobileElement
		implements SnapshotMobileElement<T> {
	private static final long serialVersionUID = -6452866868131771671L;
	private final RenderedTreeSnapshotMobileElement<T> parentElement;
	private List<RenderedTreeSnapshotMobileElement<T>> childrenElement;
	private CapturedMobileElement capturedElement;

	protected RenderedTreeSnapshotMobileElement() {
		this(null);
	}

	protected RenderedTreeSnapshotMobileElement(final RenderedTreeSnapshotMobileElement<T> parentElement) {
		this.parentElement = parentElement;
	}

	@Override
	public RenderedTreeSnapshotMobileElement<T> getParentElement() {
		return this.parentElement;
	}

	protected String makeXpath() {
		final String tagName = this.getTagName();
		final int index = this.getIndexPropertyForElement(tagName);
		String xpath = StringUtils.isEmpty(tagName) ? "//*" : ("/" + tagName);
		if (index > 0) {
			xpath = String.valueOf(xpath) + "[" + index + "]";
		}
		if (this.parentElement == null) {
			return "/" + xpath;
		}
		final String parentXpath = this.parentElement.getXpath();
		xpath = String.valueOf(StringUtils.isEmpty(parentXpath) ? "//*" : parentXpath) + xpath;
		return xpath;
	}

	private int getIndexPropertyForElement(final String tagName) {
		if (StringUtils.isEmpty(tagName) || this.parentElement == null) {
			return 0;
		}
		int index = 1;
		for (final RenderedTreeSnapshotMobileElement<T> sibling : this.parentElement.getChildrenElement()) {
			if (sibling == this) {
				continue;
			}
			if (!tagName.equals(sibling.getTagName())) {
				continue;
			}
			++index;
		}
		return index;
	}

	@Override
	public List<RenderedTreeSnapshotMobileElement<T>> getChildrenElement() {
		if (this.childrenElement == null) {
			this.childrenElement = new ArrayList<RenderedTreeSnapshotMobileElement<T>>();
		}
		return this.childrenElement;
	}

	public void setChildrenElement(final List<RenderedTreeSnapshotMobileElement<T>> childrenElement) {
		this.childrenElement = childrenElement;
	}

	@Override
	public CapturedMobileElement getCapturedElement() {
		return this.capturedElement;
	}

	@Override
	public void setCapturedElement(final CapturedMobileElement capturedElement) {
		this.capturedElement = capturedElement;
	}

	@Override
	public CapturedMobileElement newCapturedElement() {
		final CapturedMobileElement capturedElement = new CapturedMobileElement(this, true);
		capturedElement.setName(this.getName());
		capturedElement.setAttributes(this.getAttributes());
		this.setCapturedElement(capturedElement);
		return capturedElement;
	}

	@Override
	public TreeMobileElement findBestMatch(final CapturedMobileElement needToVerify) {
		if (needToVerify == null) {
			return null;
		}
		if (this.containsAllAttributes(needToVerify.getAttributes())) {
			return this;
		}
		for (final TreeMobileElement child : this.getChildrenElement()) {
			final TreeMobileElement found = child.findBestMatch(needToVerify);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private boolean containsAllAttributes(final Map<String, String> attributesToVerify) {
		if (attributesToVerify == null || attributesToVerify.isEmpty()) {
			return false;
		}
		final Map<String, String> attributes = this.getAttributes();
		for (final Map.Entry<String, String> entryToVerify : attributesToVerify.entrySet()) {
			final String key = entryToVerify.getKey();
			if (!attributes.containsKey(key)
					|| !ObjectUtils.equals((Object) attributes.get(key), (Object) entryToVerify.getValue())) {
				return false;
			}
		}
		return true;
	}
}
