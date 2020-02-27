package pcloudystudio.objectspy.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright � 2020 SSTS Inc. All rights reserved.

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrMatcher;import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXParseException;

import opkeystudio.iconManager.OpKeyStudioIcons;
import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.AndroidSnapshotMobileElement;

import java.io.StringReader;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;

public class MobileInspectorController {

	public MobileInspectorController() {
		// TODO Auto-generated constructor stub
	}

	public TreeMobileElement getMobileObjectRoot() {
		try {
			// final String pageSource = this.driver.getPageSource();
			final String pageSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+ "<hierarchy rotation=\"0\">\r\n"
					+ "   <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\">\r\n"
					+ "      <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\">\r\n"
					+ "         <node index=\"0\" text=\"\" resource-id=\"android:id/content\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\">\r\n"
					+ "            <node index=\"0\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/launcher\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\">\r\n"
					+ "               <node index=\"0\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/scrim_view\" class=\"android.view.View\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\" />\r\n"
					+ "               <node index=\"1\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/drag_layer\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\">\r\n"
					+ "                  <node index=\"0\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/workspace\" class=\"com.android.launcher3.PagedView\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"true\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,0][1080,2255]\">\r\n"
					+ "                     <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.view.ViewGroup\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[27,155][1053,1965]\">\r\n"
					+ "                        <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.view.ViewGroup\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[39,166][1041,1954]\">\r\n"
					+ "                           <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.appwidget.AppWidgetHostView\" package=\"com.sec.android.app.launcher\" content-desc=\"Weather and Clock\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[39,166][1041,519]\">\r\n"
					+ "                              <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_main_layout\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[50,177][1031,509]\">\r\n"
					+ "                                 <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_background\" class=\"android.widget.ImageView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[50,177][1031,509]\" />\r\n"
					+ "                                 <node index=\"1\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[50,205][1031,479]\">\r\n"
					+ "                                    <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_refresh_icon_touch_area\" class=\"android.widget.ImageView\" package=\"com.sec.android.daemonapp\" content-desc=\"Update weather\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[899,347][1031,479]\" />\r\n"
					+ "                                 </node>\r\n"
					+ "                                 <node index=\"2\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_content\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[68,205][1013,479]\">\r\n"
					+ "                                    <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/time_area\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[68,205][417,479]\">\r\n"
					+ "                                       <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[68,205][417,401]\">\r\n"
					+ "                                          <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[68,242][357,401]\">\r\n"
					+ "                                             <node index=\"0\" text=\"6:54\" resource-id=\"com.sec.android.daemonapp:id/widget_time_bg\" class=\"android.widget.TextView\" package=\"com.sec.android.daemonapp\" content-desc=\"6:54\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[68,242][357,401]\" />\r\n"
					+ "                                          </node>\r\n"
					+ "                                          <node index=\"1\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[362,329][417,382]\">\r\n"
					+ "                                             <node index=\"0\" text=\"pm\" resource-id=\"com.sec.android.daemonapp:id/widget_am_pm_bg\" class=\"android.widget.TextView\" package=\"com.sec.android.daemonapp\" content-desc=\"pm\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[362,329][417,382]\" />\r\n"
					+ "                                          </node>\r\n"
					+ "                                       </node>\r\n"
					+ "                                       <node index=\"1\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[72,415][351,471]\">\r\n"
					+ "                                          <node index=\"0\" text=\"Wed, 8 January\" resource-id=\"com.sec.android.daemonapp:id/widget_date_bg\" class=\"android.widget.TextView\" package=\"com.sec.android.daemonapp\" content-desc=\"Wed, 8 January\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[72,415][351,471]\" />\r\n"
					+ "                                       </node>\r\n"
					+ "                                    </node>\r\n"
					+ "                                    <node index=\"1\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_current_weather_area\" class=\"android.widget.RelativeLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"Current location, Sector 1, Current temperature14�, Fog, Weather, Updated 08/01 5:08 pm, Double tap for more information.\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[422,205][1013,479]\">\r\n"
					+ "                                       <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_current_weather_margin_top\" class=\"android.widget.ImageView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[422,205][1013,231]\" />\r\n"
					+ "                                       <node index=\"1\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_current_weather_temp_area\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[422,231][1013,331]\">\r\n"
					+ "                                          <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_current_weather_icon\" class=\"android.widget.ImageView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[787,231][887,331]\" />\r\n"
					+ "                                          <node index=\"1\" text=\"14�\" resource-id=\"com.sec.android.daemonapp:id/widget_current_temp_bg\" class=\"android.widget.TextView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[892,231][1013,326]\" />\r\n"
					+ "                                       </node>\r\n"
					+ "                                       <node index=\"2\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_current_weather_city_area\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[422,331][1013,384]\">\r\n"
					+ "                                          <node index=\"0\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_current_location_icon\" class=\"android.widget.ImageView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[825,337][872,384]\" />\r\n"
					+ "                                          <node index=\"1\" text=\"Sector 1\" resource-id=\"com.sec.android.daemonapp:id/widget_current_city_name_bg\" class=\"android.widget.TextView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[872,331][1013,384]\" />\r\n"
					+ "                                       </node>\r\n"
					+ "                                       <node index=\"3\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_update_area\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[577,421][1013,471]\">\r\n"
					+ "                                          <node index=\"0\" text=\"Updated 08/01 5:08 pm\" resource-id=\"com.sec.android.daemonapp:id/widget_update_text_bg\" class=\"android.widget.TextView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[577,421][962,471]\" />\r\n"
					+ "                                          <node index=\"1\" text=\"\" resource-id=\"com.sec.android.daemonapp:id/widget_refresh_icon\" class=\"android.widget.ImageView\" package=\"com.sec.android.daemonapp\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[967,423][1013,469]\" />\r\n"
					+ "                                       </node>\r\n"
					+ "                                    </node>\r\n" + "                                 </node>\r\n"
					+ "                              </node>\r\n" + "                           </node>\r\n"
					+ "                           <node index=\"1\" text=\"Audible\" resource-id=\"com.sec.android.app.launcher:id/home_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Audible\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[543,524][790,878]\" />\r\n"
					+ "                           <node index=\"2\" text=\"Bandcamp\" resource-id=\"com.sec.android.app.launcher:id/home_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Bandcamp\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[795,524][1041,878]\" />\r\n"
					+ "                           <node index=\"3\" text=\"Phone\" resource-id=\"com.sec.android.app.launcher:id/home_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Phone\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[39,1598][286,1952]\" />\r\n"
					+ "                           <node index=\"4\" text=\"Contacts\" resource-id=\"com.sec.android.app.launcher:id/home_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Contacts\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[291,1598][538,1952]\" />\r\n"
					+ "                           <node index=\"5\" text=\"Play Store\" resource-id=\"com.sec.android.app.launcher:id/home_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Play Store\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[543,1598][790,1952]\" />\r\n"
					+ "                           <node index=\"6\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"Folder: Google\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[795,1598][1041,1952]\">\r\n"
					+ "                              <node index=\"0\" text=\"Google\" resource-id=\"com.sec.android.app.launcher:id/folder_icon_name\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[795,1814][1041,1952]\" />\r\n"
					+ "                           </node>\r\n" + "                        </node>\r\n"
					+ "                     </node>\r\n" + "                  </node>\r\n"
					+ "                  <node index=\"1\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/page_indicator\" class=\"android.widget.LinearLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,1962][1080,2013]\">\r\n"
					+ "                     <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"Page 1 of 3, Default page\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[457,1962][512,2013]\">\r\n"
					+ "                        <node index=\"1\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/active\" class=\"android.widget.ImageView\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[459,1962][510,2013]\" />\r\n"
					+ "                     </node>\r\n"
					+ "                     <node index=\"1\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"Page 2 of 3\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[512,1962][567,2013]\">\r\n"
					+ "                        <node index=\"0\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/inactive\" class=\"android.widget.ImageView\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[514,1962][565,2013]\" />\r\n"
					+ "                     </node>\r\n"
					+ "                     <node index=\"2\" text=\"\" resource-id=\"\" class=\"android.widget.FrameLayout\" package=\"com.sec.android.app.launcher\" content-desc=\"Page 3 of 3, Current page is last page.\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[567,1962][622,2013]\">\r\n"
					+ "                        <node index=\"0\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/inactive\" class=\"android.widget.ImageView\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[569,1962][620,2013]\" />\r\n"
					+ "                     </node>\r\n" + "                  </node>\r\n"
					+ "                  <node index=\"2\" text=\"\" resource-id=\"com.sec.android.app.launcher:id/hotseat\" class=\"android.view.ViewGroup\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[0,2020][1080,2255]\">\r\n"
					+ "                     <node index=\"0\" text=\"\" resource-id=\"\" class=\"android.view.ViewGroup\" package=\"com.sec.android.app.launcher\" content-desc=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" scrollable=\"false\" long-clickable=\"false\" password=\"false\" selected=\"false\" bounds=\"[38,2020][1042,2255]\">\r\n"
					+ "                        <node index=\"0\" text=\"Messages\" resource-id=\"com.sec.android.app.launcher:id/hotseat_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Messages\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[38,2020][369,2255]\" />\r\n"
					+ "                        <node index=\"1\" text=\"LeetCode\" resource-id=\"com.sec.android.app.launcher:id/hotseat_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"LeetCode\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[374,2020][705,2255]\" />\r\n"
					+ "                        <node index=\"2\" text=\"Camera\" resource-id=\"com.sec.android.app.launcher:id/hotseat_icon\" class=\"android.widget.TextView\" package=\"com.sec.android.app.launcher\" content-desc=\"Camera\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" scrollable=\"false\" long-clickable=\"true\" password=\"false\" selected=\"false\" bounds=\"[710,2020][1041,2255]\" />\r\n"
					+ "                     </node>\r\n" + "                  </node>\r\n"
					+ "               </node>\r\n" + "            </node>\r\n" + "         </node>\r\n"
					+ "      </node>\r\n" + "   </node>\r\n" + "</hierarchy>";
			final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = null;
			try {
				final InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(pageSource));
				doc = db.parse(is);
			} catch (SAXParseException ex2) {
				final InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(removeEscapeCharacter(pageSource)));
				doc = db.parse(is);
			}
			final Element rootElement = doc.getDocumentElement();
			final AndroidSnapshotMobileElement htmlMobileElementRootNode = new AndroidSnapshotMobileElement();
			htmlMobileElementRootNode.getAttributes().put("class", rootElement.getTagName());
			htmlMobileElementRootNode.render(rootElement);
			return htmlMobileElementRootNode;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String removeEscapeCharacter(final String contentBuilder) {
		final String pattern = "(\\\"([^=])*\\\")";
		final Pattern pattern2 = Pattern.compile(pattern);
		final Matcher matcher = pattern2.matcher(contentBuilder);
		StrBuilder sb = new StrBuilder(contentBuilder);
		while (matcher.find()) {
			final String str = matcher.group(1).substring(1, matcher.group(1).length() - 1);
			sb = sb.replaceFirst(StrMatcher.stringMatcher(str), StringEscapeUtils.escapeXml(str));
		}
		return sb.toString();
	}

	public String captureScreenshot() throws Exception {
		/*
		 * final String screenshotFolder = Util.getDefaultMobileScreenshotPath(); final
		 * File screenshot = (File) this.driver.getScreenshotAs(OutputType.FILE); if
		 * (!screenshot.exists()) { throw new
		 * Exception("DIA_ERROR_MSG_UNABLE_TO_CAPTURE_SCREEN"); } final String fileName
		 * = new String("screenshot_" + new Date().getTime() + ".jpg"); final String
		 * path = String.valueOf(screenshotFolder) +
		 * System.getProperty("file.separator") + fileName;
		 * FileUtils.copyFile(screenshot, new File(path)); try {
		 * FileUtils.forceDelete(screenshot); } catch (Exception ex) {} return path;
		 */
		return System.getProperty("user.dir") + System.getProperty("file.separator")
		+ OpKeyStudioIcons.MOBILE_SPY_CAPTURED_IMAGE;
	}

	public String getDefaultMobileScreenshotPath() throws Exception {
		return OpKeyStudioIcons.MOBILE_SPY_CAPTURED_IMAGE;
	}

}
