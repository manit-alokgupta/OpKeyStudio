package com.opkey;

import com.opkey.OpKeyGenericPlugin.OpKeyGenericKeywords;
import com.opkeystudio.runtime.ORObject;

public class Main {

	public static ORObject CresConnect;
	public static ORObject EmailAddress;
	public static ORObject MYSPACE;
	public static ORObject NEONNISHANT;
	public static ORObject CresConnect1;
	public static ORObject Password;
	public static ORObject SearchEmployee;
	public static ORObject unknownVar;
	public static ORObject IntranetPortal;
	public static ORObject BUTTON;
	public static ORObject Login;

	public static void main(String[] args) throws Exception {

		CresConnect = new ORObject();
		CresConnect.addProperty("tag", "html").addProperty("title", "CresConnect").addProperty("x", "0")
				.addProperty("y", "0").addProperty("url", "http://cresconnect.crestechglobal.com/");
		EmailAddress = new ORObject();
		EmailAddress.addProperty("tag", "INPUT").addProperty("xpath:attributes", "//input[@name='username']")
				.addProperty("xpath:position", "//div[1]/input");
		EmailAddress.setParentORObject(CresConnect);
		MYSPACE = new ORObject();
		MYSPACE.addProperty("textContent", "MySpace").addProperty("innertext", "MYSPACE").addProperty("tag", "A")
				.addProperty("link", "MYSPACE").addProperty("xpath:position", "//nav/ul/li[4]/a")
				.addProperty("xpath:relative", "/html/body/div/header/div[2]/div/nav/ul/li[4]/a").addProperty("css",
						"html > body > div > header > div:nth-of-type(2) > div > nav > ul > li:nth-of-type(4) > a");
		MYSPACE.setParentORObject(CresConnect1);
		NEONNISHANT = new ORObject();
		NEONNISHANT.addProperty("custom:childXml",
				"<?xml version='1.0' encoding='UTF-8'?><childproperties><tag><![CDATA[H3]]></tag><classname><![CDATA[name-heading]]></classname><innertext><![CDATA[Neon Nishant]]></innertext><childindex><![CDATA[0]]></childindex><xpathidrelative><![CDATA[/html/body/div[1]/div/div/div[2]/div[1]/a/h3]]></xpathidrelative><xpathposition><![CDATA[//h3]]></xpathposition></childproperties>")
				.addProperty("textContent", "Neon Nishant").addProperty("innertext", "NEON NISHANT")
				.addProperty("tag", "A").addProperty("link", "NEON NISHANT")
				.addProperty("xpath:position", "//div[2]/div[1]/a")
				.addProperty("xpath:relative", "/html/body/div[1]/div/div/div[2]/div[1]/a").addProperty("css",
						"html > body > div:first-of-type > div > div > div:nth-of-type(2) > div:first-of-type > a");
		NEONNISHANT.setParentORObject(IntranetPortal);
		CresConnect1 = new ORObject();
		CresConnect1.addProperty("tag", "html").addProperty("title", "CresConnect").addProperty("x", "0")
				.addProperty("y", "0").addProperty("url", "http://cresconnect.crestechglobal.com/home.php");
		Password = new ORObject();
		Password.addProperty("tag", "INPUT").addProperty("xpath:attributes", "//input[@name='password']")
				.addProperty("xpath:position", "//div[2]/input");
		Password.setParentORObject(CresConnect);
		SearchEmployee = new ORObject();
		SearchEmployee.addProperty("tag", "INPUT").addProperty("id", "empid")
				.addProperty("xpath:attributes", "//input[@id='empid']")
				.addProperty("xpath:position", "//header/div[1]/div[2]/form/div/input[1]")
				.addProperty("xpath:relative", "/html/body/div/header/div[1]/div[2]/form/div/input[1]")
				.addProperty("css",
						"html > body > div > header > div:first-of-type > div:nth-of-type(2) > form > div > input:first-of-type");
		SearchEmployee.setParentORObject(CresConnect1);
		unknownVar = new ORObject();
		unknownVar.addProperty("textContent", "×").addProperty("innertext", "×").addProperty("className", "mfp-close")
				.addProperty("tag", "BUTTON").addProperty("xpath:attributes", "//button[@type='button']")
				.addProperty("xpath:position", "//div/div/button")
				.addProperty("xpath:relative", "/html/body/div[2]/div/div/div/button")
				.addProperty("css", "html > body > div:nth-of-type(2) > div > div > div > button");
		unknownVar.setParentORObject(IntranetPortal);
		IntranetPortal = new ORObject();
		IntranetPortal.addProperty("tag", "html").addProperty("title", "Intranet Portal").addProperty("x", "0")
				.addProperty("y", "0")
				.addProperty("url", "http://cresconnect.crestechglobal.com/members.php?empid=neon&rad=name");
		BUTTON = new ORObject();
		BUTTON.addProperty("custom:childXml",
				"<?xml version='1.0' encoding='UTF-8'?><childproperties><tag><![CDATA[I]]></tag><classname><![CDATA[fa fa-search]]></classname><innertext><![CDATA[]]></innertext><childindex><![CDATA[0]]></childindex><xpathidrelative><![CDATA[/html/body/div/header/div[1]/div[2]/form/div/span/button/i]]></xpathidrelative><xpathposition><![CDATA[//span/button/i]]></xpathposition></childproperties>")
				.addProperty("className", "btn btn-default").addProperty("tag", "BUTTON")
				.addProperty("xpath:attributes", "//button[@type='submit']")
				.addProperty("xpath:position", "//span/button")
				.addProperty("xpath:relative", "/html/body/div/header/div[1]/div[2]/form/div/span/button")
				.addProperty("css",
						"html > body > div > header > div:first-of-type > div:nth-of-type(2) > form > div > span > button");
		BUTTON.setParentORObject(CresConnect1);
		Login = new ORObject();
		Login.addProperty("textContent", "Login").addProperty("innertext", "Login").addProperty("name", "submit_login")
				.addProperty("className", "btn btn-primary lgbtnsds").addProperty("tag", "BUTTON")
				.addProperty("xpath:attributes", "//button[@name='submit_login']")
				.addProperty("xpath:position", "//button")
				.addProperty("xpath:relative", "/html/body/div[2]/div/form/div[3]/div/button").addProperty("css",
						"html > body > div:nth-of-type(2) > div > form > div:nth-of-type(3) > div > button");
		Login.setParentORObject(CresConnect);


		OpKeyGenericKeywords genericKeyword = new OpKeyGenericKeywords();
		genericKeyword.OpenBrowser("chrome", "http://cresconnect.crestechglobal.com/");
		genericKeyword.TypeTextOnEditBox(EmailAddress, "neon.nishant@sstsinc.com");
		Thread.sleep(5000);
		genericKeyword.TypeTextOnEditBox(Password, "master@123");
		genericKeyword.ClickButton(Login);
		genericKeyword.SyncBrowser();
		genericKeyword.ClickLink(MYSPACE);
		genericKeyword.TypeTextOnEditBox(SearchEmployee, "neon");
		genericKeyword.ClickButton(BUTTON);
		genericKeyword.SyncBrowser();
		genericKeyword.SelectWindow("Intranet Portal", 0);
		genericKeyword.ClickLink(NEONNISHANT);
		genericKeyword.ClickButton(unknownVar);
		genericKeyword.CloseAllBrowsers();

	}

}
