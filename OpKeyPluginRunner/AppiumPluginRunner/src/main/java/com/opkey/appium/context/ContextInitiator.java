package com.opkey.appium.context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments.DataArgument;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.Function;
import com.crestech.opkey.plugin.contexts.Context;
import com.crestech.opkey.plugin.contexts.InvocationContext;
import com.opkey.appium.sessions.SessionHandler;
import com.opkeystudio.core.sessions.SessionInfo;

public class ContextInitiator {
	public void initContext() {
		initSettings();
	}

	public static void addDataRgumentsInFunctionCall(String... args) {
		DataArguments dataarguments = new DataArguments();
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			DataArgument dataArgument = new DataArgument();
			dataArgument.setArgumentName("args" + i);
			dataArgument.setArgumentPosition(i);
			dataArgument.setValue(arg);
			dataarguments.getDataArgument().add(dataArgument);
		}
		Context.current().getFunctionCall().setDataArguments(dataarguments);
	}

	public static void addDataRgumentsInFunctionCall(Object... args) {
		DataArguments dataarguments = new DataArguments();
		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];
			DataArgument dataArgument = new DataArgument();
			dataArgument.setArgumentName("args" + i);
			dataArgument.setArgumentPosition(i);
			dataArgument.setValue(arg.toString());

			setArgumentType(dataArgument, arg);
			dataarguments.getDataArgument().add(dataArgument);
		}

		Context.current().getFunctionCall().setDataArguments(dataarguments);
	}

	private static void setArgumentType(DataArgument dataArgument, Object arg) {
		if (arg instanceof String) {
			dataArgument.setDataType("String");
		} else if (arg instanceof Integer) {
			dataArgument.setDataType("Integer");
		} else if (arg instanceof Double) {
			dataArgument.setDataType("Double");
		} else if (arg instanceof Boolean) {
			dataArgument.setDataType("Boolean");
		}
	}

	private void initSettings() {
		try {
			Context.session().setSettings(getSettings());
		} catch (Exception ex) {
			// in case of web plugin execution as well, this piece of code is run, due to which the Context Object might be overridden and cause further errors
			// wrapping it up into a try-catch block ensures that if web plugin is running, then appium wont ovverride its settings in the context and vice-versa
		}
	}

	public static void addFunction(String functionName) {
		FunctionCall fc = new FunctionCall();
		Function function = new Function();
		function.setCallTimeoutInMillis(90000);
		function.setMethodName(functionName);
		fc.setFunction(function);
		Context.set(new InvocationContext(fc));

	}

	private Map<String, String> getSettings() {
		String defaultInstallDir = new File("").getAbsolutePath();
		Map<String, String> settingsMap = new HashMap<String, String>();
		settingsMap.put("XMLHttpRequestTimeOut", "30");
		settingsMap.put("UseWaitForPageLoad", "true");
		settingsMap.put("UseWaitForAngularLoad", "true");
		settingsMap.put("UseWaitForJQueryLoad", "true");
		settingsMap.put("UseWaitForXMLHttpRequestLoad", "true");
		settingsMap.put("ChromeDriverPath", "");
		settingsMap.put("IEDriverPath", "");
		settingsMap.put("FirefoxDriverPath", "");
		settingsMap.put("EdgeDriverPath", "");
		settingsMap.put("OperaDriverPath", "");

		String appiumHost = SessionHandler.getSessionInfo().pluginSettings.get("appiumHost"); // appium Host
		String appiumPort = SessionHandler.getSessionInfo().pluginSettings.get("appiumPort"); // appium Port
		String appiumDir = SessionHandler.getSessionInfo().pluginSettings.get("appiumDir");
		if (appiumHost != null)
			settingsMap.put("Host", appiumHost);
		else
			settingsMap.put("Host", "");

		if (appiumDir != null)
			settingsMap.put("Port", appiumPort);
		else
			settingsMap.put("Port", "");

		if (appiumHost != null)
			settingsMap.put("AppiumServer", appiumDir);
		else
			settingsMap.put("AppiumServer", "");

		settingsMap.put("ProxyHost", "");
		settingsMap.put("ProxyPort", "");
		settingsMap.put("_DefaultStepTimeout", "90");
		settingsMap.put("_HighlightObject", "false");
		settingsMap.put("_ObjectVisibilityCheck", "false");
		settingsMap.put("________ignore________", "NOTE: Below items can also be fetched through invocation arguments defined in plugin descriptor. Ignore the underscore edges in names");
		settingsMap.put("_CommunicationEndpoint_", "");
		if (SessionHandler.screenshotPath != null) {
			settingsMap.put("_ScreenshotsDirectory_", SessionHandler.screenshotPath.getPath());
		}
		settingsMap.put("_EnginePID_", "");
		settingsMap.put("_CustomLibraryFolderPath_", "");
		settingsMap.put("_PluginSettingXmlPath_", "");
		settingsMap.put("_SessionSnapshotFrequency_", "FailedSteps");
		settingsMap.put("_SessionSnapshotQuality_", "Medium");
		settingsMap.put("_CommunicationProtocol_", "");
		settingsMap.put("_DebugMode_", "false");
		settingsMap.put("_LogSinkEndpoint_", "");
		settingsMap.put("_AdbDirectory_", "");

		settingsMap.put("_DefaultPluginLocation_", defaultInstallDir + File.separator + "resources" + File.separator + "libraries" + File.separator + "Plugins" + File.separator + "Appium");

		settingsMap.put("_IOSDirectory_", "");
		settingsMap.put("_PluginID_", "");
		settingsMap.put("_RunningViaSpockAgent_", "false");
		settingsMap.put("_TimeDifferenceBetweenAgentAndPlugin_", "0");
		settingsMap.put("_SessionID_", "");
		return settingsMap;
	}
}
