package com.opkey.appium.caller;

import java.util.concurrent.Callable;

import com.crestech.opkey.plugin.ResultCodes;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;
import com.crestech.opkey.plugin.contexts.Context;
import com.opkey.appium.ReportHelper;
import com.opkey.appium.enums.GetKeywords;
import com.opkey.appium.enums.VisibilityKeywords;

public class FunctionCaller {
	public static <T> FunctionResult execute(Callable<T> task) {
		FunctionResult functionResult = null;
		String methodName = Context.current().getFunctionCall().getFunction().getMethodName();
		long startTime = System.currentTimeMillis();

		try {
			functionResult = (FunctionResult) task.call();
			functionResult = validateFunctionResult(functionResult, methodName);
			ReportHelper.addReportStep(methodName, functionResult);
		} catch (Exception e) {
			functionResult = validateExceptionAndReport(methodName, e);
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

	private static FunctionResult validateExceptionAndReport(String keywordName, Exception exception) {
		System.out.println("Validate exception and report");
		if (isGetKeyword(keywordName)) {
			FunctionResult functionResult = getPassTimeOutFR(false);
			ReportHelper.addReportStep(keywordName, functionResult);
			return functionResult;
		} else {
			FunctionResult functionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION)
					.setMessage(exception.getMessage()).setOutput(false).make();

			ReportHelper.addReportStep(keywordName, functionResult);
			return functionResult;
		}
	}

	private static FunctionResult validateFunctionResult(FunctionResult functionResult, String keywordName) {
		System.out.println("Validate function result: " + functionResult);
		printFunctionResult(functionResult);
		
		if (functionResult == null && isGetKeyword(keywordName)) {
			System.out.println("#1. Found get type keyword");
			return Result.PASS().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
		}else if(functionResult == null) {
			return Result.FAIL().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
		}
		else if (isVisibilityKeyword(keywordName) || isGetKeyword(keywordName)) {
			System.out.println("#2. Found Visibility/get type keyword");
			if (functionResult.getOutput() == null || functionResult.getOutput().isEmpty()) {
				return Result.PASS().setOutput(false).setMessage(functionResult.getMessage()).make();
			} else {
				return Result.PASS().setOutput(functionResult.getOutput()).setMessage(functionResult.getMessage()).make();
			} 
		}
		return functionResult;
	}

	public static boolean isVisibilityKeyword(String keywordName) {
		for (VisibilityKeywords vk : VisibilityKeywords.values()) {
			if (vk.name().equals(keywordName)) {
				System.out.println("Found VISIBILITY keyword: " + keywordName);
				return true;
			}
		}
		System.out.println("Not Found VISIBILITY keyword: " + keywordName);
		return false;
	}

	public static boolean isGetKeyword(String keywordName) {
		for (GetKeywords vk : GetKeywords.values()) {
			if (vk.name().equals(keywordName)) {
				System.out.println("Found GET keyword: " + keywordName);
				return true;
			}
		}
		System.out.println("Not Found GET keyword: " + keywordName);
		return false;
	}

	private static FunctionResult getPassTimeOutFR(boolean status) {
		return Result.PASS().setOutput(status).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
	}
	
	private static void printFunctionResult(FunctionResult functionResult) {
		System.out.println("FunctionResult: " + functionResult);
		if(functionResult !=null) {
			System.out.println("Output: " + functionResult.getOutput());
			System.out.println("Status: " + functionResult.getStatus());
			System.out.println("Message: " + functionResult.getMessage());
		}
	}
}
