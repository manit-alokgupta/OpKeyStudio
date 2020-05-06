package com.opkey.appium.caller;

import java.util.concurrent.Callable;

import com.crestech.opkey.plugin.ResultCodes;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;
import com.crestech.opkey.plugin.contexts.Context;
import com.opkey.appium.ReportHelper;
import com.opkey.appium.enums.GetKeywords;
import com.plugin.appium.enums.VisibilityKeywords;

public class FunctionCaller {
	public static <T> FunctionResult execute(Callable<T> task) {
		FunctionResult functionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION).setMessage("")
				.setOutput(false).make();
		
		String methodName = Context.current().getFunctionCall().getFunction().getMethodName();
		long startTime = System.currentTimeMillis();
		
		try {
			functionResult = (FunctionResult) task.call();
			ReportHelper.addReportStep(methodName, validateFunctionResult(functionResult, methodName));
		} catch (Exception e) {
			addErrorReport(methodName, functionResult, e);
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
	
	private static void addErrorReport(String keywordName, FunctionResult functionResult, Exception exception) {
		if(isVisibilityKeyword(keywordName)) {
			ReportHelper.addReportStep(keywordName, getTimeOutFR());
		}else {
			functionResult.setMessage(exception.getMessage());
			ReportHelper.addReportStep(keywordName, exception);
		}
	}
	
	private static FunctionResult validateFunctionResult(FunctionResult fr, String keywordName) {
		
		if(fr == null) {
			return Result.FAIL().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
		}
		
		else if (isVisibilityKeyword(keywordName) || isGetKeyword(keywordName)) {
			System.out.println("Found Visibility type keyword");
			if(fr.getOutput() == null || fr.getOutput().isEmpty()) {
				return Result.PASS().setOutput(false).setMessage(fr.getMessage()).make();
			}else {
				return Result.PASS().setOutput(fr.getOutput()).setMessage(fr.getMessage()).make();
			}
		}
		return fr;
	}
	
	public static boolean isVisibilityKeyword(String keywordName) {
		for (VisibilityKeywords vk : VisibilityKeywords.values()) {
			if (vk.name().equals(keywordName)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isGetKeyword(String keywordName) {
		for (GetKeywords vk : GetKeywords.values()) {
			if (vk.name().equals(keywordName)) {
				return true;
			}
		}
		return false;
	}
	
	private static FunctionResult getTimeOutFR() {
		return Result.FAIL().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
	}
}
