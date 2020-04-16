package com.opkeystudio.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ORObject {
	private ORObject parentORObject;
	private Map<String, String> properties = new HashMap<>();

	public ORObject getParentORObject() {
		return parentORObject;
	}

	public ORObject setParentORObject(ORObject parentORObject) {
		this.parentORObject = parentORObject;
		return this;
	}

	public String getProperty(String key) {
		String data = this.properties.get(key);
		return data;
	}

	public ORObject addProperty(String key, String value) {
		this.properties.put(key, value);
		return this;
	}

	public void updateProperty(String key, String value) {
		this.properties.put(key, value);
	}

	public List<String> getAllPropertyNames() {
		List<String> outList = new ArrayList<String>();
		Map<String, String> properties = getAllProperties();
		Set<String> keys = properties.keySet();
		if (keys.size() == 0) {
			return new ArrayList<String>();
		}
		for (String key : keys) {
			outList.add(key);
		}
		return outList;

	}

	public Map<String, String> getAllProperties() {
		return this.properties;
	}
}
