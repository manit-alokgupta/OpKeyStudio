package com.opkey;

import com.opkey.OpKeyGenericPlugin.OpKeyGenericKeywords;
import com.opkeystudio.runtime.ORObject;

public class Main {

	public static void main(String[] args) throws Exception {
		ORObject Home = new ORObject();
		Home.addProperty("href", "index.html").addProperty("textContent", "Home").addProperty("innertext", "Home")
				.addProperty("index", "0").addProperty("value", "Home").addProperty("tag", "A")
				.addProperty("logicalname", "Home").addProperty("link", "Home")
				.addProperty("xpath:position", "//nav/div[1]/div/ul/li[1]/a")
				.addProperty("xpath:relative", "//div[@id='navbar-collapse']/ul/li[1]/a")
				.addProperty("css", "div#navbar-collapse > ul > li:first-of-type > a").addProperty("pageindex", "2");
		OpKeyGenericKeywords genericKeyword = new OpKeyGenericKeywords();
		genericKeyword.OpenBrowser("chrome", "http://sstsinc.com");
		long startTime = System.currentTimeMillis();
		genericKeyword.Click(Home);
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println(">>Completed in " + endTime);
		genericKeyword.CloseAllBrowsers();
	}

}
