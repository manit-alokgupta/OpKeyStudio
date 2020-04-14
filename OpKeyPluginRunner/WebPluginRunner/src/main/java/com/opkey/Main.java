package com.opkey;

import com.opkey.OpKeyGenericPlugin.OpKeyGenericKeywords;
import com.opkeystudio.runtime.ORObject;

public class Main {

	public static void main(String[] args) throws Exception {
		ORObject orobject = new ORObject();
		orobject.addProperty("tag", "A").addProperty("name", "123").addProperty("id", "abc");
		OpKeyGenericKeywords genericKeyword = new OpKeyGenericKeywords();
		genericKeyword.OpenBrowser("chrome", "http://sstsinc.com");
		long startTime = System.currentTimeMillis();
		genericKeyword.Click(orobject);
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println(">>Completed in " + endTime);
		genericKeyword.CloseAllBrowsers();
	}

}
