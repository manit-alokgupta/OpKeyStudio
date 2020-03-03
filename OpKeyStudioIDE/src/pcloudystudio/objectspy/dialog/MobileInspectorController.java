package pcloudystudio.objectspy.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrMatcher;
import org.openqa.selenium.OutputType;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXParseException;

import pcloudystudio.appiumserver.AppiumStarter;
import pcloudystudio.capability.AndroidDriverObject;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.AndroidSnapshotMobileElement;

import java.io.File;
import java.io.StringReader;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;

public class MobileInspectorController {

	public MobileInspectorController() {
		// TODO Auto-generated constructor stub
	}

	public TreeMobileElement getMobileObjectRoot() {
		try {

			new AppiumStarter("Nexus 5X",
					"C:\\Users\\alok.gupta\\Desktop\\FollowMeInstrumentor_V3\\Original\\Shopping List SoftList_v2.3.6_apkpure.com.apk");
			Thread.sleep(30000);

			String pageSource = AndroidDriverObject.getDriver().getPageSource();
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
		} catch (Exception ex) {
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

		// String screenshotFolder = Util.getDefaultMobileScreenshotPath();
		// System.out.println("screenshotFolder: " + screenshotFolder);
		File screenshot = AndroidDriverObject.getDriver().getScreenshotAs(OutputType.FILE);
		if (!screenshot.exists()) {
			throw new Exception("DIA_ERROR_MSG_UNABLE_TO_CAPTURE_SCREEN");
		}
		System.out.println(screenshot.getAbsolutePath());
		// String fileName = new String("screenshot_" + new Date().getTime() + ".png");
		// String path = String.valueOf(screenshotFolder) +
		// System.getProperty("file.separator") + fileName;
		// FileUtils.copyFile(screenshot, new File(path));
		// try {
		// FileUtils.forceDelete(screenshot);
		// } catch (Exception ex) {
		// }
		// System.out.println(" screenshot path: " + path);
		return screenshot.getAbsolutePath();

		// return System.getProperty("user.dir") + System.getProperty("file.separator")
		// + OpKeyStudioIcons.MOBILE_SPY_CAPTURED_IMAGE;
	}
}
