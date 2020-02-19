package com.opkeystudio.opkeyweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.opkeystudio.runtime.ORObject;
import com.opkeystudio.runtime.WebObject;

public class OpKeyWebPlayer {
	private WebDriver currentWebDriver;
	private boolean waitForPageLoad;
	private boolean waitForXhrLoad;
	private int finderTimeout = 30;
	private int pageLoadAndXHRTimeout = 30;
	private JavascriptExecutor javaScriptExecutor;

	public OpKeyWebPlayer() {
	}

	public OpKeyWebPlayer(WebDriver driver) {
		this.setCurrentWebDriver(driver);
	}

	public WebDriver openBrowser(String browserName, String url) {
		if (currentWebDriver != null) {
			currentWebDriver.manage().window().maximize();
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
		if (browserName.toLowerCase().contains("ie")) {
			currentWebDriver = new InternetExplorerDriver();
			currentWebDriver.get(url);
		}
		currentWebDriver.manage().window().maximize();
		setCurrentWebDriver(currentWebDriver);
		waitForPageLoad();
		return currentWebDriver;
	}

	public void setChromeDriverPath(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);
	}

	public void setFirefoxDriverPath(String driverPath) {
		System.setProperty("webdriver.gecko.driver", driverPath);
	}

	public void setIEDriverPath(String driverPath) {
		System.setProperty("webdriver.ie.driver", driverPath);
	}

	public WebElement click(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		element.click();
		return element;

	}

	public WebElement clickButton(ORObject orobject) {
		return click(orobject);
	}

	public WebElement clickImage(ORObject orobject) {
		return click(orobject);
	}

	public WebElement clickLink(ORObject orobject) {
		return click(orobject);
	}

	public WebElement typeTextOnEditBox(ORObject orobject, String valueToType) {
		return typeText(orobject, valueToType);
	}

	public WebElement typeTextInTextArea(ORObject orobject, String valueToType) {
		return typeText(orobject, valueToType);
	}

	public WebElement selectCheckBox(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		if (element.isSelected() == false) {
			element.click();
		}
		return element;
	}

	public WebElement deSelectCheckBox(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		if (element.isSelected() == true) {
			element.click();
		}
		return element;
	}

	public WebElement selectRadioButton(ORObject orobject) {
		int index = 0;
		WebObject webObject = convertORObjectToWebObject(orobject);
		index = webObject.getIndex();
		List<WebElement> radioButtons = findWebElements(webObject);
		System.out.println("Radio Buttons Size "+radioButtons.size());
		if (radioButtons.size() > 0) {
			if (radioButtons.size() > index) {
				WebElement element = radioButtons.get(index);
				element.click();
				return element;
			}
		}
		return null;
	}

	public boolean verifyObjectExists(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		if (element == null) {
			return false;
		}
		return true;
	}

	public WebElement typeText(ORObject orobject, String valueToType) {
		WebElement element = findWebElement(orobject);
		getJavaScriptExecutor().executeScript("arguments[0].value=''", element);
		element.sendKeys(valueToType);
		return element;
	}

	public WebElement clearText(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		getJavaScriptExecutor().executeScript("arguments[0].value=''", element);
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

	public String getObjectAttribute(ORObject orobject, String attrName) {
		WebElement element = findWebElement(orobject);
		return element.getAttribute(attrName);
	}

	public boolean assertEquals(Object obj1, Object obj2) {
		if (obj1.equals(obj2)) {
			return true;
		}
		return false;
	}

	public void closeBrowser() {
		waitForPageLoad();
		getCurrentWebDriver().close();
	}

	public void closeAllBrowser() {
		waitForPageLoad();
		getCurrentWebDriver().quit();
	}

	public WebElement findWebElement(ORObject orobject) {
		int i = 0;
		while (i < getFinderTimeout()) {
			waitForPageLoad();
			sleep(1);
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
				try {
					List<WebElement> elements = driver.findElements(By.xpath(xpath));
					if (elements.size() == 1) {
						System.out.println(">>Element Found By Xpath " + xpath);
						return elements.get(0);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if (webObject.getCss() != null) {
				List<WebElement> elements = driver.findElements(By.cssSelector(webObject.getCss()));
				if (elements.size() == 1) {
					System.out.println(">>Element Found By Css " + webObject.getCss());
					return elements.get(0);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		System.out.println(">>Element Not Found");
		return null;
	}

	public List<WebElement> findWebElements(WebObject webObject) {
		int i = 0;
		while (i < getFinderTimeout()) {
			waitForPageLoad();
			sleep(1);
			WebDriver driver = getCurrentWebDriver();
			if (webObject.getId() != null) {
				List<WebElement> elements = driver.findElements(By.id(webObject.getId()));
				if (elements.size() > 0) {
					System.out.println(">>Element Found By Id " + webObject.getId());
					return elements;
				}
			}

			if (webObject.getName() != null) {
				List<WebElement> elements = driver.findElements(By.name(webObject.getName()));
				if (elements.size() > 0) {
					System.out.println(">>Element Found By Name " + webObject.getName());
					return elements;
				}
			}

			if (webObject.getClassName() != null) {
				List<WebElement> elements = driver.findElements(By.className(webObject.getClassName()));
				if (elements.size() > 0) {
					System.out.println(">>Element Found By ClassName " + webObject.getClassName());
					return elements;
				}
			}

			for (String xpath : webObject.getXpaths()) {
				try {
					List<WebElement> elements = driver.findElements(By.xpath(xpath));
					if (elements.size() > 0) {
						System.out.println(">>Element Found By Xpath " + xpath);
						return elements;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if (webObject.getCss() != null) {
				List<WebElement> elements = driver.findElements(By.cssSelector(webObject.getCss()));
				if (elements.size() > 0) {
					System.out.println(">>Element Found By Css " + webObject.getCss());
					return elements;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		System.out.println(">>Element Not Found");
		return new ArrayList<WebElement>();
	}

	public WebElement mouseHover(ORObject orobject) {
		WebElement element = findWebElement(orobject);
		Actions actions = new Actions(getCurrentWebDriver());
		actions.moveToElement(element).perform();
		return element;
	}

	public void selectWindow(String title, int index) {
		String currentWindow = getCurrentWebDriver().getWindowHandle(); // will keep current window to switch back
		for (String winHandle : getCurrentWebDriver().getWindowHandles()) {
			if (getCurrentWebDriver().switchTo().window(winHandle).getTitle().equals(title)) {
				return;
			}
			getCurrentWebDriver().switchTo().window(currentWindow);
		}
	}

	public void switchToDefaultContent() {
		getCurrentWebDriver().switchTo().defaultContent();
	}

	private WebObject convertORObjectToWebObject(ORObject orobject) {
		WebObject object = new WebObject();
		Map<String, String> allProperties = orobject.getAllProperties();
		Set<String> keys = allProperties.keySet();
		List<String> xpaths = new ArrayList<String>();
		for (String key : keys) {
			if (key.toLowerCase().contains("xpath:")) {
				xpaths.add(orobject.getProperty(key));
			}
		}
		object.setXpaths(xpaths);

		for (String key : keys) {
			String value = orobject.getProperty(key);
			if (key.equalsIgnoreCase("id")) {
				object.setId(value);
			}
			if (key.equalsIgnoreCase("name")) {
				object.setName(value);
			}
			if (key.equalsIgnoreCase("tag")) {
				object.setTag(value);
			}
			if (key.equalsIgnoreCase("class")) {
				object.setClassName(value);
			}
			if (key.equalsIgnoreCase("input-type")) {
				object.setInputType(value);
			}
			if (key.equalsIgnoreCase("innertext")) {
				object.setInnerText(value);
			}
			if (key.equalsIgnoreCase("css")) {
				object.setCss(value);
			}
			if (key.equalsIgnoreCase("index")) {
				object.setIndex(Integer.parseInt(value));
			}
		}
		return object;
	}

	public void sleep(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void waitForPageLoad() {
		if (isWaitForPageLoad() == false) {
			return;
		}
		while (true) {
			String readyState = (String) getJavaScriptExecutor().executeScript("return document.readyState");
			System.out.println(">>Waiting for Page Load To Complete " + readyState);
			if (readyState.toLowerCase().equals("complete") || readyState.toLowerCase().equals("interactive")) {
				System.out.println("Page Load Completed");
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public WebDriver getCurrentWebDriver() {
		return currentWebDriver;
	}

	public void setCurrentWebDriver(WebDriver currentWebDriver) {
		this.currentWebDriver = currentWebDriver;
		this.setJavaScriptExecutor((JavascriptExecutor) this.currentWebDriver);
	}

	public boolean isWaitForPageLoad() {
		return waitForPageLoad;
	}

	public void setWaitForPageLoad(boolean waitForPageLoad) {
		this.waitForPageLoad = waitForPageLoad;
	}

	public boolean isWaitForXhrLoad() {
		return waitForXhrLoad;
	}

	public void setWaitForXhrLoad(boolean waitForXhrLoad) {
		this.waitForXhrLoad = waitForXhrLoad;
	}

	public int getFinderTimeout() {
		return finderTimeout;
	}

	public void setFinderTimeout(int finderSetTimeout) {
		this.finderTimeout = finderSetTimeout;
	}

	public JavascriptExecutor getJavaScriptExecutor() {
		return javaScriptExecutor;
	}

	public void setJavaScriptExecutor(JavascriptExecutor javaScriptExecutor) {
		this.javaScriptExecutor = javaScriptExecutor;
	}

	public int getPageLoadAndXHRTimeout() {
		return pageLoadAndXHRTimeout;
	}

	public void setPageLoadAndXHRTimeout(int pageLoadAndXHRTimeout) {
		this.pageLoadAndXHRTimeout = pageLoadAndXHRTimeout;
	}

	public String xpathForTextKeywords(String tempText, boolean isContains, String tag) {
		String x;
		String extraProperty = "";
		String xpathForImg = "";
		String preXpath = "";

		/**
		 * It finds element with text no need to use this in img property
		 * (textNodeXpath)
		 */
		String textNodeXpath = "";

		if (tag == null || tag.isEmpty()) {
			tag = "*";
		} else if (tag.equals("img")) {
			xpathForImg = "or contains(normalize-space(@alt)," + handleComaInText(tempText)
					+ ") or contains(normalize-space(@alt)," + handleComaInText(specialSpaceText(tempText)) + ")";
		} else {
		}

		if (isContains) {
			if (tag.equals("*"))
				textNodeXpath = " | " + preXpath + "//text()[contains(. , " + handleComaInText(tempText)
						+ ")]/parent::*" + " | " + preXpath + "//text()[contains(. , "
						+ handleComaInText(specialSpaceText(tempText)) + ")]/parent::*";

			x = preXpath + "//" + tag
					+ "[not(self::script) and not(self::title) and not(@type='hidden') and not(parent::*[@title='"
					+ tempText + "'])" + extraProperty + " and (contains(normalize-space(text()),"
					+ handleComaInText(tempText) + ") or (contains(normalize-space(@value),"
					+ handleComaInText(tempText) + ") and (self::input[" + lowerXpathProperty("type", "button")
					+ "])) or (contains(normalize-space(@value)," + handleComaInText(tempText) + ") and (self::input["
					+ lowerXpathProperty("type", "submit") + "])) or (contains(normalize-space(@value),"
					+ handleComaInText(tempText) + ") and (self::input[" + lowerXpathProperty("type", "reset")
					+ "])) or contains(normalize-space(text())," + handleComaInText(specialSpaceText(tempText))
					+ ") or contains(normalize-space(@placeholder)," + handleComaInText(tempText)
					+ ") or contains(normalize-space(@placeholder)," + handleComaInText(specialSpaceText(tempText))
					+ ") or contains(normalize-space(@data-original-title)," + handleComaInText(tempText)
					+ ") or contains(normalize-space(@data-original-title),"
					+ handleComaInText(specialSpaceText(tempText)) + ") or contains(normalize-space(@title),"
					+ handleComaInText(tempText) + ") or contains(normalize-space(@title),"
					+ handleComaInText(specialSpaceText(tempText)) + "" + xpathForImg
					+ " or contains(normalize-space(@data-placeholder)," + handleComaInText(tempText) + ") ) )] "
					+ textNodeXpath;

		} else {
			if (tag.equals("*"))
				textNodeXpath = " | " + preXpath + "//text()[normalize-space(.) = " + handleComaInText(tempText)
						+ "]/parent::*" + " | " + preXpath + "//text()[normalize-space(.) = "
						+ handleComaInText(specialSpaceText(tempText)) + "]/parent::*";

			x = preXpath + "//" + tag + "[not(self::script) and not(@type='hidden') and not(parent::*[@title='"
					+ tempText + "'])" + extraProperty + " and (normalize-space(text())=" + handleComaInText(tempText)
					+ " or text()=" + handleComaInText(tempText)
					+ " or normalize-space(translate(text(),'\u00a0',' '))=" + handleComaInText(tempText)
					+ " or (normalize-space(@value)=" + handleComaInText(tempText) + " and (self::input["
					+ lowerXpathProperty("type", "button") + "]))" + " or (normalize-space(@value)="
					+ handleComaInText(tempText) + " and (self::input[" + lowerXpathProperty("type", "reset") + "]))"
					+ " or (normalize-space(@value)=" + handleComaInText(tempText) + " and (self::input["
					+ lowerXpathProperty("type", "submit") + "])) or normalize-space(text())="
					+ handleComaInText(specialSpaceText(tempText)) + " or normalize-space(@placeholder)="
					+ handleComaInText(tempText) + " or normalize-space(@placeholder)="
					+ handleComaInText(specialSpaceText(tempText)) + " or normalize-space(@data-original-title)="
					+ handleComaInText(tempText) + " or normalize-space(@data-original-title)="
					+ handleComaInText(specialSpaceText(tempText)) + " or normalize-space(@title)="
					+ handleComaInText(tempText) + " or normalize-space(@title)="
					+ handleComaInText(specialSpaceText(tempText)) + xpathForImg
					+ " or normalize-space(@data-placeholder)=" + handleComaInText(tempText) + " )] " + textNodeXpath;

		}

		return "(" + x + ")";
	}

	private String handleComaInText(String text) {
		if (text.contains("\""))
			return "'" + text + "'";
		return "\"" + text + "\"";
	}

	private String specialSpaceText(String text) {
		text = text.trim();
		text = text.replace(' ', '\u00A0');
		return text;
	}

	private String lowerXpathProperty(String attribute, String text) {
		return "translate(@" + attribute + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = '"
				+ text.toLowerCase() + "'";
	}
}
