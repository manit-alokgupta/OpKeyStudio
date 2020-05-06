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
		FunctionResult functionResult;
		String methodName = Context.current().getFunctionCall().getFunction().getMethodName();
		long startTime = System.currentTimeMillis();

		try {
			functionResult = (FunctionResult) task.call();
			ReportHelper.addReportStep(methodName, validateFunctionResult(functionResult, methodName));
		} catch (Exception e) {
			functionResult = validateErrorAndReport(methodName, e);
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

	private static FunctionResult validateErrorAndReport(String keywordName, Exception exception) {
		if (isVisibilityKeyword(keywordName) || isGetKeyword(keywordName)) {
			FunctionResult functionResult = getTimeOutFR(false);
			ReportHelper.addReportStep(keywordName, functionResult);
			return functionResult;
		} else {
			FunctionResult functionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION)
					.setMessage(exception.getMessage()).setOutput(false).make();

			ReportHelper.addReportStep(keywordName, exception);
			return functionResult;
		}
	}

	private static FunctionResult validateFunctionResult(FunctionResult fr, String keywordName) {
		if (fr == null) {
			return Result.FAIL().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
		} else if (isVisibilityKeyword(keywordName) || isGetKeyword(keywordName)) {
			System.out.println("Found Visibility/get type keyword");
			if (fr.getOutput() == null || fr.getOutput().isEmpty()) {
				return Result.PASS().setOutput(false).setMessage(fr.getMessage()).make();
			} else {
				return Result.PASS().setOutput(fr.getOutput()).setMessage(fr.getMessage()).make();
			}
		}
		return fr;
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
	
	public static void main(String[] args) {
		isVisibilityKeyword("VerifyObjectExists");
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

	private static FunctionResult getTimeOutFR(boolean status) {
		return Result.PASS().setOutput(status).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
	}
}
