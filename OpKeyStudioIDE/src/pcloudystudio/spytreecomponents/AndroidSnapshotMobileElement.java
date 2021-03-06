package pcloudystudio.spytreecomponents;

// Created by Alok Gupta on 20/02/2020.
// Copyright � 2020 SSTS Inc. All rights reserved.

import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class AndroidSnapshotMobileElement extends RenderedTreeSnapshotMobileElement<Element> {
	private static final long serialVersionUID = -1420051390223903583L;

	public AndroidSnapshotMobileElement() {
	}

	public AndroidSnapshotMobileElement(AndroidSnapshotMobileElement parent) {
		super(parent);
	}

	@Override
	public void render(Element xmlElement) {
		if (xmlElement == null) {
			return;
		}
		this.convertXMLElementToWebElementForAndroid(xmlElement);
		if (!xmlElement.hasChildNodes()) {
			return;
		}
		NodeList childElementNodes = xmlElement.getChildNodes();
		for (int count = childElementNodes.getLength(), i = 0; i < count; ++i) {
			Node node = childElementNodes.item(i);
			if (node instanceof Element) {
				AndroidSnapshotMobileElement childNode = new AndroidSnapshotMobileElement(this);
				this.getChildrenElement().add(childNode);
				childNode.render((Element) node);
			}
		}
	}

	public void convertXMLElementToWebElementForAndroid(Element xmlElement) {

		Map<String, String> htmlMobileElementProps = this.getAttributes();

		String objectName = null;
		if (xmlElement.getAttribute("text") != null && xmlElement.getAttribute("text").length() > 0) {
			objectName = xmlElement.getAttribute("text");
		}

		if (objectName != null && objectName.length() > 0)
			htmlMobileElementProps.put("name", objectName);

		if (MobileElementInspectorController.currentActivity != null && MobileElementInspectorController.currentActivity.length() > 0)
			htmlMobileElementProps.put("activity", MobileElementInspectorController.currentActivity);

		if (xmlElement.hasAttribute("class") && xmlElement.getAttribute("class").length() > 0) {
			htmlMobileElementProps.put("class", xmlElement.getAttribute("class"));
		}
		String instance = "0";
		if (xmlElement.hasAttribute("instance") && xmlElement.getAttribute("instance").length() > 0) {
			instance = xmlElement.getAttribute("instance");
			htmlMobileElementProps.put("instance", instance);
		}
		if (xmlElement.hasAttribute("text") && xmlElement.getAttribute("text").length() > 0) {
			htmlMobileElementProps.put("text", xmlElement.getAttribute("text"));
		}
		if (xmlElement.hasAttribute("resource-id") && xmlElement.getAttribute("resource-id").length() > 0) {
			htmlMobileElementProps.put("resource-id", xmlElement.getAttribute("resource-id"));
		}
		if (xmlElement.hasAttribute("package") && xmlElement.getAttribute("package").length() > 0) {
			htmlMobileElementProps.put("package", xmlElement.getAttribute("package"));
		}
		if (xmlElement.hasAttribute("content-desc") && xmlElement.getAttribute("content-desc").length() > 0) {
			htmlMobileElementProps.put("content-desc", xmlElement.getAttribute("content-desc"));
		}
		if (xmlElement.hasAttribute("checkable") && xmlElement.getAttribute("checkable").length() > 0) {
			htmlMobileElementProps.put("checkable", xmlElement.getAttribute("checkable"));
		}
		if (xmlElement.hasAttribute("checked") && xmlElement.getAttribute("checked").length() > 0) {
			htmlMobileElementProps.put("checked", xmlElement.getAttribute("checked"));
		}
		if (xmlElement.hasAttribute("clickable") && xmlElement.getAttribute("clickable").length() > 0) {
			htmlMobileElementProps.put("clickable", xmlElement.getAttribute("clickable"));
		}
		if (xmlElement.hasAttribute("enabled") && xmlElement.getAttribute("enabled").length() > 0) {
			htmlMobileElementProps.put("enabled", xmlElement.getAttribute("enabled"));
		}
		if (xmlElement.hasAttribute("focusable") && xmlElement.getAttribute("focusable").length() > 0) {
			htmlMobileElementProps.put("focusable", xmlElement.getAttribute("focusable"));
		}
		if (xmlElement.hasAttribute("focused") && xmlElement.getAttribute("focused").length() > 0) {
			htmlMobileElementProps.put("focused", xmlElement.getAttribute("focused"));
		}
		if (xmlElement.hasAttribute("scrollable") && xmlElement.getAttribute("scrollable").length() > 0) {
			htmlMobileElementProps.put("scrollable", xmlElement.getAttribute("scrollable"));
		}
		if (xmlElement.hasAttribute("long-clickable") && xmlElement.getAttribute("long-clickable").length() > 0) {
			htmlMobileElementProps.put("long-clickable", xmlElement.getAttribute("long-clickable"));
		}
		if (xmlElement.hasAttribute("password") && xmlElement.getAttribute("password").length() > 0) {
			htmlMobileElementProps.put("password", xmlElement.getAttribute("password"));
		}
		if (xmlElement.hasAttribute("selected") && xmlElement.getAttribute("selected").length() > 0) {
			htmlMobileElementProps.put("selected", xmlElement.getAttribute("selected"));
		}
		if (xmlElement.hasAttribute("bounds") && xmlElement.getAttribute("bounds").length() > 0) {
			String bounds = xmlElement.getAttribute("bounds");
			int left = Integer.parseInt(bounds.substring(1, bounds.indexOf(44)));
			int top = Integer.parseInt(bounds.substring(bounds.indexOf(44) + 1, bounds.indexOf(93)));
			int right = Integer.parseInt(bounds.substring(bounds.lastIndexOf(91) + 1, bounds.lastIndexOf(44)));
			int bottom = Integer.parseInt(bounds.substring(bounds.lastIndexOf(44) + 1, bounds.lastIndexOf(93)));
			htmlMobileElementProps.put("x", String.valueOf(left));
			htmlMobileElementProps.put("y", String.valueOf(top));
			htmlMobileElementProps.put("width", String.valueOf(right - left));
			htmlMobileElementProps.put("height", String.valueOf(bottom - top));
		}
		String guiName = htmlMobileElementProps.get("class");
		if (guiName != null) {
			guiName = String.valueOf(guiName);
			if (htmlMobileElementProps.get("text") != null) {
				guiName = String.valueOf(guiName) + " " + " {" + htmlMobileElementProps.get("text") + "} ";
			}
			guiName = String.valueOf(guiName) + " " + xmlElement.getAttribute("bounds");
			if (guiName.contains("\n")) {
				guiName = guiName.replace("\n", "");
			}
		} else {
			guiName = xmlElement.getTagName();
			guiName = String.valueOf(guiName) + instance;
		}
		this.setName(guiName);
		htmlMobileElementProps.put("xpath", this.makeXpath());
	
		htmlMobileElementProps.put("xpath-attribute", this.makeContentDescXpath(htmlMobileElementProps.get("class"),"content-desc",xmlElement.getAttribute("content-desc")));
	}

	@Override
	public String getXpath() {
		return this.getAttributes().get("xpath");
	}
	@Override
   public String getContentDescXpath(){
		return this.getAttributes().get("contentDescXpath");
	}

	@Override
	public String getTagName() {
		return this.getAttributes().get("class");
	}
}
