package com.opkey.context;

import java.util.HashMap;
import java.util.Map;

import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments.DataArgument;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.Function;
import com.crestech.opkey.plugin.contexts.Context;
import com.crestech.opkey.plugin.contexts.InvocationContext;
import com.crestech.opkey.plugin.webdriver.WebDriverDispatcher;
import com.crestech.opkey.plugin.webdriver.pluginSpecific.Context.CurrentKeyword;

public class ContextInitiator {
	public void initContext() {
		FunctionCall fc = new FunctionCall();
		Context.set(new InvocationContext(fc));
		initSettings();
		initHacks_Jugaad();
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

	private void initSettings() {
		Context.session().setSettings(getSettings());
	}

	public static void addFunction(String functionName) {
		Function function = new Function();
		function.setCallTimeoutInMillis(90000);
		function.setMethodName(functionName);
		Context.current().getFunctionCall().setFunction(function);
	}

	private Map<String, String> getSettings() {
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
		settingsMap.put("Host", "");
		settingsMap.put("Port", "");
		settingsMap.put("ProxyHost", "");
		settingsMap.put("ProxyPort", "");
		settingsMap.put("_DefaultStepTimeout", "90");
		settingsMap.put("_HighlightObject", "false");
		settingsMap.put("_ObjectVisibilityCheck", "false");
		settingsMap.put("________ignore________",
				"NOTE: Below items can also be fetched through invocation arguments defined in plugin descriptor. Ignore the underscore edges in names");
		settingsMap.put("_CommunicationEndpoint_", "");
		settingsMap.put("_ScreenshotsDirectory_", "");
		settingsMap.put("_EnginePID_", "");
		settingsMap.put("_CustomLibraryFolderPath_", "");
		settingsMap.put("_PluginSettingXmlPath_", "");
		settingsMap.put("_SessionSnapshotFrequency_", "FailedSteps");
		settingsMap.put("_SessionSnapshotQuality_", "Medium");
		settingsMap.put("_CommunicationProtocol_", "");
		settingsMap.put("_DebugMode_", "false");
		settingsMap.put("_LogSinkEndpoint_", "");
		settingsMap.put("_AdbDirectory_", "");

		settingsMap.put("_DefaultPluginLocation_",
				"C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE\\resources\\libraries\\Plugins\\Web");

		settingsMap.put("_IOSDirectory_", "");
		settingsMap.put("_PluginID_", "");
		settingsMap.put("_RunningViaSpockAgent_", "false");
		settingsMap.put("_TimeDifferenceBetweenAgentAndPlugin_", "0");
		settingsMap.put("_SessionID_", "");
		return settingsMap;
	}

	private void initHacks_Jugaad() {
		WebDriverDispatcher.currentKeyword = new CurrentKeyword();
	}
}
