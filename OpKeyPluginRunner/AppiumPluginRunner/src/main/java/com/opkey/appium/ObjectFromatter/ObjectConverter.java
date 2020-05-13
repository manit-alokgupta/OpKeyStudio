package com.opkey.appium.ObjectFromatter;

import java.util.Map;
import java.util.Set;

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
			this.setObjectArgumentInFunctionCall("No attachment found");
			return new AppiumObject(false);
		}
		
		try {
			
			this.setObjectArgumentInFunctionCall(orobject);
			
			ORObject SmartSoftwareTestingSolutions = new ORObject();
			SmartSoftwareTestingSolutions.addProperty("type", "HTML PAGE").addProperty("tag", "html")
					.addProperty("index", "0").addProperty("title", "Smart Software Testing Solutions")
					.addProperty("x", "0").addProperty("y", "0").addProperty("url", "http://sstsinc.com/")
					.addProperty("src", "http://sstsinc.com/").addProperty("titleindex", "0");
			Object _object = convertORObjectToOpKeyObject(orobject);
			
			AppiumObject appiumObject = new ObjectFormatter().formatObjectToWebDriverObject(_object);
			Object _parentobject = convertORObjectToOpKeyObject(SmartSoftwareTestingSolutions);
			AppiumObject parentobject = new ObjectFormatter().formatObjectToWebDriverObject(_parentobject);
			appiumObject.setParentObject(parentobject);
			System.out.println(">>Object " + appiumObject.toString());
			
			return appiumObject;
		} catch (ObjectPropertiesNotSufficientException e) {
			e.printStackTrace();
			System.out.println("Warning: " + e.getMessage());
		}
		return new AppiumObject(false);
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
		if (orobject.getAllProperties().containsKey("logicalname")) {
			String value = orobject.getAllProperties().get("logicalname");
			if(!value.isEmpty())
				return value;
		} 
		
		if (orobject.getAllProperties().containsKey("name")) {
			String value = orobject.getAllProperties().get("name");
			if(!value.isEmpty())
				return value;
		} 
		
		if (orobject.getAllProperties().containsKey("text")) {
			String value = orobject.getAllProperties().get("text");
			if(!value.isEmpty())
				return value;
		}
		
		if (orobject.getAllProperties().containsKey("class")) {
			String value = orobject.getAllProperties().get("class");
			if(!value.isEmpty())
				return value;
		}
		
		if (orobject.getAllProperties().containsKey("id")) {
			String value = orobject.getAllProperties().get("id");
			if(!value.isEmpty())
				return value;
		}
		
		return "Object";
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
	
	private void setObjectArgumentInFunctionCall(String logicalName) {
		ObjectArguments oArgs = new ObjectArguments();
		ObjectArgument oArg = new ObjectArgument();
		oArg.setArgumentName("Object");
		Object orObj = new Object();
		orObj.setLogicalName(logicalName);
		oArg.setObject(orObj);
		oArgs.getObjectArgument().add(oArg);
		Context.current().getFunctionCall().setObjectArguments(oArgs);
	}
}
