package com.opkeystudio.opkeyappium;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import com.opkeystudio.runtime.ORObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class OpKeyAppiumPlayer {
	private AppiumDriver<?> driver;

	public OpKeyAppiumPlayer(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	@SuppressWarnings("rawtypes")
	private WebElement findElement(ORObject obj, int timeOut) throws Exception {
		Date startTime = new Date();
		long span = 0L;
		WebElement webElement = null;
		Point elementLocation = null;
		MobileSearchEngine searchEngine = new MobileSearchEngine((AppiumDriver) this.driver, obj);
		Dimension screenSize = this.driver.manage().window().getSize();
		while (span < timeOut) {
			webElement = searchEngine.findMobileElement();
			if (webElement != null) {
				elementLocation = webElement.getLocation();
				if (elementLocation.y < screenSize.height) {
					break;
				}
				try {
					if (this.driver instanceof AndroidDriver) {
						TouchActions ta = new TouchActions((WebDriver) this.driver);
						ta.down(screenSize.width / 2, screenSize.height / 2).perform();
						ta.move(screenSize.width / 2, (int) (screenSize.height / 2 * 0.5)).perform();
						ta.release().perform();
					} else {
						System.out.println(">>NOT ANDROID DRIVER");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			Date endTime = new Date();
			span = endTime.getTime() - startTime.getTime();
		}
		System.out.println(">>Element Not Found");
		return webElement;
	}

	@SuppressWarnings("rawtypes")
	public void tap(ORObject obj) throws Exception {
		final WebElement element = this.findElement(obj, 10000);
		if (element == null) {
			System.out.println(">>Element Not Found");
			return;
		}
		TouchAction<?> tap = (TouchAction<?>) new TouchAction((PerformsTouchActions) this.driver)
				.tap((TapOptions) TapOptions.tapOptions().withElement(ElementOption.element(element, 1, 1)));
		tap.perform();
	}
}
