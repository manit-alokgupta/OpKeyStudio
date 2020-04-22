package com.opkey.executor;

import com.crestech.opkey.plugin.ResultCodes;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;

public class KeywordExecutor {
	private Runnable keywordRunnable;

	public KeywordExecutor(Runnable runnable) {
		this.setKeywordRunnable(runnable);
	}

	public FunctionResult executeKeyword() {
		FunctionResult outFunctionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION).setMessage("")
				.setOutput(false).make();
		try {
			return getKeywordRunnable().run();
		} catch (Exception e) {
			e.printStackTrace();
			outFunctionResult.setMessage(e.getMessage());
		}
		return outFunctionResult;
	}

	private Runnable getKeywordRunnable() {
		return keywordRunnable;
	}

	private void setKeywordRunnable(Runnable keywordRunnable) {
		this.keywordRunnable = keywordRunnable;
	}
}
