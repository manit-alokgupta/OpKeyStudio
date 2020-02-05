package com.opkeystudio.opkeyweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.opkeystudio.runtime.WebObject;
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
			currentWebDriver.get(url);
		}
		if (browserName.toLowerCase().contains("firefox")) {
			currentWebDriver = new FirefoxDriver();
			currentWebDriver.get(url);
		}
		setCurrentWebDriver(currentWebDriver);
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
		WebObject webObject = convertORObjectToWebObject(orobject);
		WebDriver driver = getCurrentWebDriver();
		if (webObject.getId() != null) {
			List<WebElement> elements = driver.findElements(By.id(webObject.getId()));
			if (elements.size() == 1) {
				System.out.println(">>Element Found By Id " + webObject.getId());
				return elements.get(0);
			}
		}

		if (webObject.getName() != null) {
			List<WebElement> elements = driver.findElements(By.name(webObject.getName()));
			if (elements.size() == 1) {
				System.out.println(">>Element Found By Name " + webObject.getName());
				return elements.get(0);
			}
		}

		if (webObject.getClassName() != null) {
			List<WebElement> elements = driver.findElements(By.className(webObject.getClassName()));
			if (elements.size() == 1) {
				System.out.println(">>Element Found By ClassName " + webObject.getClassName());
				return elements.get(0);
			}
		}

		for (String xpath : webObject.getXpaths()) {
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			if (elements.size() == 1) {
				System.out.println(">>Element Found By Xpath " + xpath);
				return elements.get(0);
			}
		}
		System.out.println(">>Element Not Found");
		return null;
	}

	private WebObject convertORObjectToWebObject(ORObject orobject) {
		WebObject object = new WebObject();
		Map<String, String> allProperties = orobject.getAllProperties();
		Set<String> keys = allProperties.keySet();
		List<String> xpaths = new ArrayList<String>();
		for (String key : keys) {
			if (key.contains("xpath:")) {
				xpaths.add(orobject.getProperty(key));
			}
		}
		object.setXpaths(xpaths);

		for (String key : keys) {
			String value = orobject.getProperty(key);
			if (key.equals("id")) {
				object.setId(value);
			}
			if (key.equals("name")) {
				object.setName(value);
			}
			if (key.equals("tag")) {
				object.setTag(value);
			}
			if (key.equals("class")) {
				object.setClassName(value);
			}
			if (key.equals("input-type")) {
				object.setInputType(value);
			}
			if (key.equals("innertext")) {
				object.setInnerText(value);
			}
			if (key.equals("css")) {
				object.setCss(value);
			}
		}
		return object;
	}

	public void wait(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebDriver getCurrentWebDriver() {
		return currentWebDriver;
	}

	public void setCurrentWebDriver(WebDriver currentWebDriver) {
		this.currentWebDriver = currentWebDriver;
	}

}
