package com.opkey.appium.ObjectFromatter;

import java.util.Map;
import java.util.Set;

import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments.ObjectArgument;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties.Property;
import com.crestech.opkey.plugin.contexts.Context;
import com.opkeystudio.runtime.ORObject;
import com.plugin.appium.AppiumObject;
import com.plugin.appium.ObjectFormatter;
import com.plugin.appium.exceptionhandlers.ObjectPropertiesNotSufficientException;

public class ObjectConverter {
	public AppiumObject formatObject(ORObject orobject) {
		
		if (orobject == null) {
			ORObject parentObject = new ORObject();
			parentObject.addProperty("", "").addProperty("", "");

			orobject = new ORObject();
			orobject.addProperty("", "");
			orobject.setParentORObject(parentObject);
		}
		
		try {
			ORObject SmartSoftwareTestingSolutions = new ORObject();
			SmartSoftwareTestingSolutions.addProperty("type", "HTML PAGE").addProperty("tag", "html")
					.addProperty("index", "0").addProperty("title", "Smart Software Testing Solutions")
					.addProperty("x", "0").addProperty("y", "0").addProperty("url", "http://sstsinc.com/")
					.addProperty("src", "http://sstsinc.com/").addProperty("titleindex", "0");
			Object _object = convertORObjectToOpKeyObject(orobject);
			
			AppiumObject webdriverobject = new ObjectFormatter().formatObjectToWebDriverObject(_object);
			Object _parentobject = convertORObjectToOpKeyObject(SmartSoftwareTestingSolutions);
			AppiumObject parentobject = new ObjectFormatter().formatObjectToWebDriverObject(_parentobject);
			webdriverobject.setParentObject(parentobject);
			System.out.println(">>Object " + webdriverobject.toString());
			
			this.setObjectArgumentInFunctionCall(orobject);
			
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
	
	private void setObjectArgumentInFunctionCall(ORObject obj) {
		ObjectArguments oArgs = new ObjectArguments();
		ObjectArgument oArg = new ObjectArgument();
		oArg.setArgumentName("Object");
		Object orObj = new Object();
		orObj.setLogicalName(getLogicalNameOfORObject(obj));
		oArg.setObject(orObj);
		oArgs.getObjectArgument().add(oArg);
		Context.current().getFunctionCall().setObjectArguments(oArgs);	
	}
}
