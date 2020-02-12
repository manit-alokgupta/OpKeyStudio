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
		String data = this.properties.get(key);
		return decodeString(data);
	}

	public ORObject addProperty(String key, String value) {
		this.properties.put(key, value);
		return this;
	}

	public Map<String, String> getAllProperties() {
		return this.properties;
	}

	public String encodeString(String inputString) {
		return java.util.Base64.getEncoder().encodeToString(inputString.getBytes());
	}

	public String decodeString(String inputString) {
		byte[] bytes = java.util.Base64.getDecoder().decode(inputString);
		return new String(bytes);
	}
}
