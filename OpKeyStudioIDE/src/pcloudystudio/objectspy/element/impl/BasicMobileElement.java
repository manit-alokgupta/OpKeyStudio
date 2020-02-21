package pcloudystudio.objectspy.element.impl;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.LinkedHashMap;
import java.util.Map;

import pcloudystudio.objectspy.element.MobileElement;

public class BasicMobileElement implements MobileElement {
	private static final long serialVersionUID = -5432610719458440188L;
	private String name;
	private Map<String, String> attributes;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((this.attributes == null) ? 0 : this.attributes.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicMobileElement)) {
			return false;
		}
		final BasicMobileElement other = (BasicMobileElement) obj;
		if (this.attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!this.attributes.equals(other.attributes)) {
			return false;
		}
		return true;
	}

	@Override
	public String getXpath() {
		return this.getAttributes().get("xpath");
	}

	@Override
	public Map<String, String> getAttributes() {
		if (this.attributes == null) {
			this.attributes = new LinkedHashMap<String, String>();
		}
		return this.attributes;
	}

	public void setAttributes(final Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public MobileElement clone() {
		try {
			return (MobileElement) super.clone();
		} catch (CloneNotSupportedException ex) {
			return null;
		}
	}
}
