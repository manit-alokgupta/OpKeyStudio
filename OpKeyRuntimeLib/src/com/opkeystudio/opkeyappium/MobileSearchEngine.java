package com.opkeystudio.opkeyappium;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;

import com.opkeystudio.runtime.AndroidObject;
import com.opkeystudio.runtime.ORObject;

import io.appium.java_client.AppiumDriver;

public class MobileSearchEngine {

	private AppiumDriver<?> appiumDriver;
	private AndroidObject androidObject;

	@SuppressWarnings("rawtypes")
	public MobileSearchEngine(AppiumDriver driver, ORObject obj) {
		this.appiumDriver = driver;
		this.androidObject = convertORObjectToMobileObject(obj);
	}

	@SuppressWarnings("unchecked")
	public WebElement findMobileElement() {

		/**** XPath ****/
		if (this.androidObject.getXpath() != null) {
			List<WebElement> elements = (List<WebElement>) appiumDriver
					.findElementsByXPath(this.androidObject.getXpath());
			if (elements.size() == 1) {
				System.out.println(">>Element Found By Id " + this.androidObject.getXpath());
				return elements.get(0);
			}
		}
		return null;
	}

	private AndroidObject convertORObjectToMobileObject(ORObject orobject) {
		AndroidObject object = new AndroidObject();
		Map<String, String> allProperties = orobject.getAllProperties();
		Set<String> keys = allProperties.keySet();

		for (String key : keys) {
			String value = orobject.getProperty(key);
			if (key.equalsIgnoreCase("class")) {
				object.setClassName(value);
			}
			if (key.equalsIgnoreCase("text")) {
				object.setText(value);
			}
			if (key.equalsIgnoreCase("resource-id")) {
				object.setResourceId(value);
			}
			if (key.equalsIgnoreCase("content-desc")) {
				object.setContentDesc(value);
			}
			if (key.equalsIgnoreCase("xpath")) {
				object.setXpath(value);
			}
		}
		return object;
	}

}
