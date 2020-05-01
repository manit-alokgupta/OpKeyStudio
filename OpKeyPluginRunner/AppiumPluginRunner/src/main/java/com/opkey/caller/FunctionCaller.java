package com.opkey.caller;

import java.util.concurrent.Callable;

import com.crestech.opkey.plugin.ResultCodes;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;
import com.crestech.opkey.plugin.contexts.Context;
import com.opkey.OpKeyGenericPlugin.ReportHelper;

public class FunctionCaller {
	public static <T> FunctionResult execute(Callable<T> task) {
		FunctionResult functionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION).setMessage("")
				.setOutput(false).make();
		
		String methodName = Context.current().getFunctionCall().getFunction().getMethodName();
		long startTime = System.currentTimeMillis();
		
		try {
			functionResult = (FunctionResult) task.call();
			ReportHelper.addReportStep(methodName, functionResult);
		} catch (Exception e) {
			functionResult.setMessage(e.getMessage());
			ReportHelper.addReportStep(methodName, e);
			e.printStackTrace();
		}
		String timeTaken = (System.currentTimeMillis() - startTime) + "ms";
		System.out.println(methodName + " time taken: " + timeTaken);
		return functionResult;
	}

	public static <T> void execute(Runnable aMethod) {
		long startTime = System.currentTimeMillis();
		aMethod.run();
		String timeTaken = (System.currentTimeMillis() - startTime) + "ms";
		System.out.println("Void: " + timeTaken);
	}
}
