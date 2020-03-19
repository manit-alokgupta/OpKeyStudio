package com.opkeystudio.opkeyappium;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.opkeystudio.runtime.ORObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileSearchEngine {

	ORObject orObj;
	AppiumDriver<?> appiumDriver;

	@SuppressWarnings("rawtypes")
	public MobileSearchEngine(AppiumDriver driver, ORObject obj) {
		this.appiumDriver = driver;
		this.orObj = obj;
	}

	@SuppressWarnings("unchecked")
	public WebElement findWebElement() {
		List<MobileElement> elementsList;
		Map<String, String> mobileElementProperties = orObj.getAllProperties();

		/**** XPath ****/
		elementsList = (List<MobileElement>) appiumDriver.findElementsByXPath(mobileElementProperties.get("xpath"));

		return elementsList.size() > 0 ? (WebElement) elementsList.get(elementsList.size() - 1) : null;
	}

}
