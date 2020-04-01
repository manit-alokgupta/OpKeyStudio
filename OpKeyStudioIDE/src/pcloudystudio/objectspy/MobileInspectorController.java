package pcloudystudio.objectspy;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrMatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXParseException;

import io.appium.java_client.android.AndroidDriver;
import pcloudystudio.appium.AndroidDriverObject;
import pcloudystudio.appium.AppiumPortIpInfo;
import pcloudystudio.appium.AppiumServer;
import pcloudystudio.appium.MobileCapabilities;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.AndroidSnapshotMobileElement;

import java.io.File;
import java.io.StringReader;
import java.net.URL;

import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;

public class MobileInspectorController {

	public static String currentActivity = null;

	public MobileInspectorController() {
	}

	public TreeMobileElement getMobileObjectRoot() {
		try {
			String pageSource = null;
		
			
		
			pageSource = AndroidDriverObject.getDriver().getPageSource();
			MobileInspectorController.currentActivity = AndroidDriverObject.getDriver().currentActivity();

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = null;
			try {
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(pageSource));
				doc = db.parse(is);
			} catch (SAXParseException ex2) {
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(removeEscapeCharacter(pageSource)));
				doc = db.parse(is);
			}
			Element rootElement = doc.getDocumentElement();
			AndroidSnapshotMobileElement htmlMobileElementRootNode = new AndroidSnapshotMobileElement();
			htmlMobileElementRootNode.getAttributes().put("class", rootElement.getTagName());
			htmlMobileElementRootNode.render(rootElement);
			return htmlMobileElementRootNode;
		} catch (

		Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String removeEscapeCharacter(String contentBuilder) {
		String pattern = "(\\\"([^=])*\\\")";
		Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(contentBuilder);
		StrBuilder sb = new StrBuilder(contentBuilder);
		while (matcher.find()) {
			String str = matcher.group(1).substring(1, matcher.group(1).length() - 1);
			sb = sb.replaceFirst(StrMatcher.stringMatcher(str), StringEscapeUtils.escapeXml(str));
		}
		return sb.toString();
	}

	public String captureScreenshot() throws Exception {
		File screenshot = AndroidDriverObject.getDriver().getScreenshotAs(OutputType.FILE);
		if (!screenshot.exists()) {
			throw new Exception("Screenshot doesn't exist!");
		}
		return screenshot.getAbsolutePath();
	}
}
