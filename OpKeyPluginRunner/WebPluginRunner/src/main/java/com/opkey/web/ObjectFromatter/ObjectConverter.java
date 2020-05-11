package com.opkey.web.ObjectFromatter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.crestech.opkey.plugin.communication.contracts.functioncall.Object;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments.ObjectArgument;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties;
import com.crestech.opkey.plugin.communication.contracts.functioncall.Object.Properties.Property;
import com.crestech.opkey.plugin.contexts.Context;
import com.crestech.opkey.plugin.webdriver.exceptionhandlers.ObjectPropertiesNotSufficientException;
import com.crestech.opkey.plugin.webdriver.object.ObjectFormatter;
import com.crestech.opkey.plugin.webdriver.object.WebDriverObject;
import com.opkeystudio.runtime.ORObject;

public class ObjectConverter {
	public WebDriverObject formatObject(ORObject orobject) {

		if (orobject == null) {
			ORObject parentObject = new ORObject();
			parentObject.addProperty("", "").addProperty("", "");

			orobject = new ORObject();
			orobject.addProperty("", "");
			orobject.setParentORObject(parentObject);
		}
		try {
			this.setObjectArgumentInFunctionCall(orobject);
			Object _object = convertORObjectToOpKeyObject(orobject);
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
		object.setUseSmartIdentification(false);
		Properties props = new Properties();
		object.setProperties(props);

		Map<String, String> allProperties = orobject.getAllProperties();
		Set<String> propertyNames = allProperties.keySet();
		for (String propertyName : propertyNames) {
			String propertyValue = allProperties.get(propertyName);
			
			System.out.println(propertyName + " @: " + propertyValue );
			
			Property property = new Property();
			property.setName(propertyName);
			property.setValue(propertyValue);
			property.setDataType("String");
			property.setIsUsed(true);
			property.setRegularExpression(false);
			object.getProperties().getProperty().add(property);
		}
		return object;
	}

	private String getLogicalNameOfORObject(ORObject orobject) {
		if (orobject.getAllProperties().containsKey("logicalname")) {
			return orobject.getAllProperties().get("logicalname");
		} else if (orobject.getAllProperties().containsKey("name")) {
			return orobject.getAllProperties().get("name");
		} else if (orobject.getAllProperties().containsKey("text")) {
			return orobject.getAllProperties().get("text");
		}
		return "no-name";
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
