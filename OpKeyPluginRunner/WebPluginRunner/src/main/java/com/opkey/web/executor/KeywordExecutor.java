package com.opkey.web.executor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriverException;

import com.crestech.opkey.plugin.ResultCodes;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.communication.contracts.functionresult.Result;
import com.crestech.opkey.plugin.contexts.Context;
import com.crestech.opkey.plugin.webdriver.exceptionhandlers.ToolNotSetException;
import com.crestech.opkey.plugin.webdriver.keywords.Utils;
import com.opkey.web.ReportHelper;
import com.opkey.web.sessions.SessionHandler;

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
			ReportHelper.addReportStep(Context.current().getFunctionCall().getFunction().getMethodName(),
					functionResult);
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

		beforeKeyword();

		FunctionResult functionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION).setMessage("")
				.setOutput(false).make();

		String methodName = Context.current().getFunctionCall().getFunction().getMethodName();
		long startTime = System.currentTimeMillis();

		try {
			functionResult = (FunctionResult) task.call();
			postKeywordAction(functionResult);
			ReportHelper.addReportStep(methodName, functionResult);
		} catch (Exception e) {
			functionResult.setMessage(e.getMessage());
			postKeywordAction(functionResult);
			ReportHelper.addReportStep(methodName, functionResult);
			e.printStackTrace();
		}
		String timeTaken = (System.currentTimeMillis() - startTime) + "ms";
		System.out.println(methodName + " time taken: " + timeTaken);
		return functionResult;
	}

	private static void beforeKeyword() {

	}

	private static void printFunctionResult(FunctionResult functionResult) {
		System.out.println("FunctionResult: " + functionResult);
		if (functionResult != null) {
			System.out.println("Output: " + functionResult.getOutput());
			System.out.println("Status: " + functionResult.getStatus());
			System.out.println("Message: " + functionResult.getMessage());
		}
	}

	private static void postKeywordAction(FunctionResult functionResult) {
		printFunctionResult(functionResult);
		try {
			File file = captureScreenshot();
			functionResult.setSnapshotPath(file.getPath());
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}

	private static File captureScreenshot() throws WebDriverException, ToolNotSetException, IOException {

		long startTime = System.currentTimeMillis();
		String fileName = UUID.randomUUID().toString() + ".png";
		File appiumFile = new Utils().takeScreenshot();
		File renamedFile = renameFile(appiumFile, fileName);
		File newFile = new File(SessionHandler.screenshotPath + File.separator + fileName);
		Path copied = Paths.get(newFile.getPath());
		Path originalPath = renamedFile.toPath();
		Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);

		System.out.println("screenshot time taken: " + (System.currentTimeMillis() - startTime));
		return newFile;

	}
	
	private static File renameFile(File file, String fileNameWithExtension) throws IOException {
		File newFile = new File(file.getAbsolutePath().replace(file.getName(), fileNameWithExtension));
		if (newFile.exists())
			throw new java.io.IOException("New file name already exists...");

		boolean success = file.renameTo(newFile);
		if (!success) {
			throw new java.io.IOException("file rename failed...");
		}

		return newFile;
	}
}
