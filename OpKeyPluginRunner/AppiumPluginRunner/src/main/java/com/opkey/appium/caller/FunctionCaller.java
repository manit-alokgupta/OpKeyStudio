package com.opkey.appium.caller;

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
import com.opkey.appium.ReportHelper;
import com.opkey.appium.enums.GetKeywords;
import com.opkey.appium.enums.VisibilityKeywords;
import com.opkey.appium.sessions.SessionHandler;
import com.plugin.appium.Utils;
import com.plugin.appium.exceptionhandlers.AdbNotFoundException;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
import com.plugin.appium.exceptionhandlers.UnableToProcessADBCommandException;

public class FunctionCaller {
	public static <T> FunctionResult execute(Callable<T> task) {
		String keywordName = Context.current().getFunctionCall().getFunction().getMethodName();
		FunctionResult functionResult = null;
		long startTime = System.currentTimeMillis();

		try {
			functionResult = (FunctionResult) task.call();
			functionResult = validateFunctionResult(functionResult, keywordName);
			postKeywordAction(functionResult);
			ReportHelper.addReportStep(keywordName, functionResult);
		} catch (ToolNotSetException e) {
			e.printStackTrace();
			functionResult = Result.FAIL().setOutput(false).setMessage("Either Application was never Opened or All instances were closed").make();
			ReportHelper.addReportStep(keywordName, functionResult);
		}
		catch (Exception e) {
			functionResult = validateException(keywordName, e);
			postKeywordAction(functionResult);
			ReportHelper.addReportStep(keywordName, functionResult);
			e.printStackTrace();
		}

		String timeTaken = (System.currentTimeMillis() - startTime) + "ms";
		System.out.println(keywordName + " time taken: " + timeTaken);
		return functionResult;
	}

	private static FunctionResult validateException(String keywordName, Exception exception) {
		if (isGetKeyword(keywordName)) {
			FunctionResult functionResult = getPassTimeOutFR(false);
			return functionResult;
		} else {
			FunctionResult functionResult = Result.FAIL(ResultCodes.ERROR_UNHANDLED_EXCEPTION)
					.setMessage(exception.getMessage()).setOutput(false).make();
			return functionResult;
		}
	}

	private static FunctionResult validateFunctionResult(FunctionResult functionResult, String keywordName) {
		printFunctionResult(functionResult);

		if (functionResult == null && isGetKeyword(keywordName)) {
			return Result.PASS().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
		} else if (functionResult == null) {
			return Result.FAIL().setOutput(false).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
		} else if (isVisibilityKeyword(keywordName) || isGetKeyword(keywordName)) {
			if (functionResult.getOutput() == null || functionResult.getOutput().isEmpty()) {
				return Result.PASS().setOutput(false).setMessage(functionResult.getMessage()).make();
			}
		}
		return functionResult;
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

	private static FunctionResult getPassTimeOutFR(boolean status) {
		return Result.PASS().setOutput(status).setMessage(ResultCodes.ERROR_STEP_TIME_OUT.toString()).make();
	}

	private static void printFunctionResult(FunctionResult functionResult) {
		System.out.println("---- Output----");
		if (functionResult != null) {
			System.out.println("Output: " + functionResult.getOutput());
			System.out.println("Status: " + functionResult.getStatus());
			System.out.println("Message: " + functionResult.getMessage());
		}
	}
	
	private static void postKeywordAction(FunctionResult functionResult) {
		try {
			File file = captureScreenshot();
			functionResult.setSnapshotPath(file.getPath());
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
		
	}

	private static File captureScreenshot() throws WebDriverException, ToolNotSetException, IOException,
			UnableToProcessADBCommandException, InterruptedException, AdbNotFoundException {
		
		long startTime = System.currentTimeMillis();
		String fileName = UUID.randomUUID().toString() + ".png";
		File appiumFile = new Utils().takeScreenshotUsingAppium();
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
