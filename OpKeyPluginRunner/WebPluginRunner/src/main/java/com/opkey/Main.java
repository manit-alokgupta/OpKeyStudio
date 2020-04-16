package com.opkey;

import com.opkey.OpKeyGenericPlugin.OpKeyGenericKeywords;
import com.opkeystudio.runtime.ORObject;

public class Main {

	public static ORObject NewNodeHtmlPage28914820069025;
	public static ORObject NewNodeClickableImageMap28917875887952;
	public static void main(String[] args) throws Exception {
		ORObject Home = new ORObject();
		Home.addProperty("href", "index.html").addProperty("textContent", "Home").addProperty("innertext", "Home")
				.addProperty("index", "0").addProperty("value", "Home").addProperty("tag", "A")
				.addProperty("logicalname", "Home").addProperty("link", "Home")
				.addProperty("xpath:position", "//nav/div[1]/div/ul/li[1]/a")
				.addProperty("xpath:relative", "//div[@id='navbar-collapse']/ul/li[1]/a")
				.addProperty("css", "div#navbar-collapse > ul > li:first-of-type > a").addProperty("pageindex", "2");

		ORObject Company = new ORObject();
		Company.addProperty("href", "Company.html").addProperty("textContent", "Company")
				.addProperty("innertext", "Company").addProperty("index", "0").addProperty("value", "Company")
				.addProperty("tag", "A").addProperty("logicalname", "Company").addProperty("link", "Company")
				.addProperty("xpath:position", "//nav/div[1]/div/ul/li[2]/a")
				.addProperty("xpath:relative", "//div[@id='navbar-collapse']/ul/li[2]/a")
				.addProperty("css", "div#navbar-collapse > ul > li:nth-of-type(2) > a").addProperty("pageindex", "3");

		NewNodeHtmlPage28914820069025 = new ORObject();
		NewNodeHtmlPage28914820069025.addProperty("url", "").addProperty("title", "").addProperty("index", "");
		NewNodeClickableImageMap28917875887952 = new ORObject();
		NewNodeClickableImageMap28917875887952.addProperty("name", "eded").addProperty("tag", "dede")
				.addProperty("title", "dede").addProperty("class", "dede").addProperty("style", "dede");
		NewNodeClickableImageMap28917875887952.setParentORObject(NewNodeHtmlPage28914820069025);

		OpKeyGenericKeywords genericKeyword = new OpKeyGenericKeywords();
		genericKeyword.OpenBrowser("chrome", "http://sstsinc.com");
		long startTime = System.currentTimeMillis();
		genericKeyword.RefreshBrowser();
		genericKeyword.ClickLink(NewNodeClickableImageMap28917875887952);
		genericKeyword.ClickLink(NewNodeClickableImageMap28917875887952);
		genericKeyword.ClickLink(NewNodeClickableImageMap28917875887952);
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println(">>Completed in " + endTime);
		genericKeyword.CloseAllBrowsers();
	}

}
