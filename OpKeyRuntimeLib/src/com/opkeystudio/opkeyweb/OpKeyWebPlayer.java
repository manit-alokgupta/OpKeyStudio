package com.opkeystudio.opkeyweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.opkeystudio.runtime.ORObject;

public class OpKeyWebPlayer {
	private WebDriver currentWebDriver;

	public WebDriver openBrowser(String browserName, String url) {
		if (currentWebDriver != null) {
			currentWebDriver.get(url);
			return currentWebDriver;
		}
		if (browserName.toLowerCase().contains("chrome")) {
			currentWebDriver = new ChromeDriver();
		}
		if (browserName.toLowerCase().contains("firefox")) {
			currentWebDriver = new FirefoxDriver();
		}
		return currentWebDriver;
	}

	public void initChromeDriverExecutablePath(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);
	}

	public WebElement click(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		element.click();
		return element;

	}

	public WebElement typeText(ORObject orobject, String valueToType) {
		WebElement element = findWebElement(orobject);
		element.clear();
		element.sendKeys(valueToType);
		return element;
	}

	public WebElement clearText(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		element.clear();
		return element;
	}

	public WebElement selectDropDownByIndex(ORObject orobject, int index) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.selectByIndex(index);
		return element;
	}

	public WebElement selectDropDownByValue(ORObject orobject, String value) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.selectByValue(value);
		return element;
	}

	public WebElement selectDropDownByVisibleText(ORObject orobject, String visibleText) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
		return element;
	}

	public WebElement deSelectDropDownByIndex(ORObject orobject, int index) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.deselectByIndex(index);
		return element;
	}

	public WebElement deSelectDropDownByValue(ORObject orobject, String value) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.deselectByValue(value);
		return element;
	}

	public WebElement deSelectDropDownByVisibleText(ORObject orobject, String visibleText) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.deselectByVisibleText(visibleText);
		return element;
	}

	public WebElement deSelectAllDropDownItems(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		Select select = new Select(element);
		select.deselectAll();
		return element;
	}

	public String getObjectText(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		return element.getText();
	}

	public void closeBrowser() {
		getCurrentWebDriver().close();
	}

	public void closeAllBrowser() {
		getCurrentWebDriver().quit();
	}

	public WebElement findWebElement(ORObject orobject) {
		return null;

	}

	public WebDriver getCurrentWebDriver() {
		return currentWebDriver;
	}

	public void setCurrentWebDriver(WebDriver currentWebDriver) {
		this.currentWebDriver = currentWebDriver;
	}

}
