package com.opkey;

import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.Function;
import com.crestech.opkey.plugin.contexts.Context;
import com.crestech.opkey.plugin.contexts.InvocationContext;
import com.opkey.OpKeyGenericPlugin.OpKeyGenericKeywords;
import com.opkeystudio.runtime.ORObject;

public class Main {

	public static void main(String[] args) throws Exception {
		FunctionCall fc = new FunctionCall();
		fc.setFunction(new Function());
		fc.getFunction().setCallTimeoutInMillis(30000);
		Context.set(new InvocationContext(fc));
		System.out.println(Context.current());
		ORObject orobject = new ORObject();
		orobject.addProperty("tag", "A").addProperty("name", "123").addProperty("id", "abc");
		new OpKeyGenericKeywords().Click(orobject);
		new OpKeyGenericKeywords().Click(orobject);
		new OpKeyGenericKeywords().Click(orobject);
		new OpKeyGenericKeywords().Click(orobject);
		
	}

}
