package com.opkey.web.executor;

import java.util.concurrent.Callable;

import com.crestech.opkey.plugin.ResultCodes;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;
import com.crestech.opkey.plugin.contexts.Context;
import com.opkey.web.ReportHelper;

public class KeywordExecutor {
	private Runnable keywordRunnable;

	public KeywordExecutor(Runnable runnable) {
		this.setKeywordRunnable(runnable);
	}

	public FunctionResult executeKeyword() {
		FunctionResult outFunctionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION).setMessage("")
				.setOutput(false).make();
		try {
			FunctionResult functionResult = getKeywordRunnable().run();
			ReportHelper.addReportStep(Context.current().getFunctionCall().getFunction().getMethodName(), functionResult);
			return functionResult;
		} catch (Exception e) {
			e.printStackTrace();
			outFunctionResult.setMessage(e.getMessage());
			ReportHelper.addReportStep(Context.current().getFunctionCall().getFunction().getMethodName(), e);
		}
		return outFunctionResult;
	}

	private Runnable getKeywordRunnable() {
		return keywordRunnable;
	}

	private void setKeywordRunnable(Runnable keywordRunnable) {
		this.keywordRunnable = keywordRunnable;
	}
	
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
}
