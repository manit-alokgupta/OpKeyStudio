package com.opkeystudio.runtime;

import java.util.HashMap;
import java.util.Map;

public class ORObject {
	private ORObject parentORObject;
	private Map<String, String> properties = new HashMap<>();

	public ORObject getParentORObject() {
		return parentORObject;
	}

	public void setParentORObject(ORObject parentORObject) {
		this.parentORObject = parentORObject;
	}

	public String getProperty(String key) {
		return this.properties.get(key);
	}

	public ORObject addProperty(String key, String value) {
		this.properties.put(key, value);
		return this;
	}
}
