package com.opkey.ObjectFromatter;

import java.util.Map;
import java.util.Set;

import com.crestech.opkey.plugin.communication.contracts.functioncall.Object;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties.Property;
import com.opkeystudio.runtime.ORObject;
import com.plugin.appium.AppiumObject;
import com.plugin.appium.ObjectFormatter;
import com.plugin.appium.exceptionhandlers.ObjectPropertiesNotSufficientException;

public class ObjectConverter {
	public AppiumObject formatObject(ORObject orobject) {
		ORObject SmartSoftwareTestingSolutions = new ORObject();
		SmartSoftwareTestingSolutions.addProperty("type", "HTML PAGE").addProperty("tag", "html")
				.addProperty("index", "0").addProperty("title", "Smart Software Testing Solutions")
				.addProperty("x", "0").addProperty("y", "0").addProperty("url", "http://sstsinc.com/")
				.addProperty("src", "http://sstsinc.com/").addProperty("titleindex", "0");
		Object _object = convertORObjectToOpKeyObject(orobject);
		try {
			AppiumObject webdriverobject = new ObjectFormatter().formatObjectToWebDriverObject(_object);
			Object _parentobject = convertORObjectToOpKeyObject(SmartSoftwareTestingSolutions);
			AppiumObject parentobject = new ObjectFormatter().formatObjectToWebDriverObject(_parentobject);
			webdriverobject.setParentObject(parentobject);
			System.out.println(">>Object " + webdriverobject.toString());
			return webdriverobject;
		} catch (ObjectPropertiesNotSufficientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Object convertORObjectToOpKeyObject(ORObject orobject) {
		Object object = new Object();
		object.setLogicalName(getLogicalNameOfORObject(orobject));
		Properties props = new Properties();
		object.setProperties(props);

		Map<String, String> allProperties = orobject.getAllProperties();
		Set<String> propertyNames = allProperties.keySet();
		for (String propertyName : propertyNames) {
			String propertyValue = allProperties.get(propertyName);
			Property property = new Property();
			property.setName(propertyName);
			property.setValue(propertyValue);
			property.setDataType("String");
			property.setIsUsed(true);
			object.getProperties().getProperty().add(property);
		}
		return object;
	}

	private String getLogicalNameOfORObject(ORObject orobject) {
		Map<String, String> allProperties = orobject.getAllProperties();
		for (String key : allProperties.keySet()) {
			if (key.toLowerCase().equals("logicalname")) {
				return allProperties.get(key);
			}
		}
		return "";
	}
}