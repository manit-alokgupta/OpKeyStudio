package com.opkey.ObjectFromatter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.crestech.opkey.plugin.communication.contracts.functioncall.Object;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties.Property;
import com.crestech.opkey.plugin.webdriver.exceptionhandlers.ObjectPropertiesNotSufficientException;
import com.crestech.opkey.plugin.webdriver.object.ObjectFormatter;
import com.crestech.opkey.plugin.webdriver.object.WebDriverObject;
import com.opkeystudio.runtime.ORObject;

public class ObjectConverter {
	public WebDriverObject formatObject(ORObject orobject) {
		Object _object = convertORObjectToOpKeyObject(orobject);
		try {
			WebDriverObject webdriverobject = new ObjectFormatter().formatObjectToWebDriverObject(_object);
			if (orobject.getParentORObject() != null) {
				Object _parentobject = convertORObjectToOpKeyObject(orobject.getParentORObject());
				WebDriverObject parentobject = new ObjectFormatter().formatObjectToWebDriverObject(_parentobject);
				webdriverobject.setParentObject(parentobject);
				System.out.println(">>Parent Object " + parentobject.toString());
			}
			System.out.println(">>Current Object " + webdriverobject.toString());
			return webdriverobject;
		} catch (ObjectPropertiesNotSufficientException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
