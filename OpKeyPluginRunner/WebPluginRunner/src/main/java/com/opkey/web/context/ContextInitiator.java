package com.opkey.web.context;

import java.io.File;
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
		try {
			Context.session().setSettings(getSettings());
		} catch (Exception ex) {
			// in case of web plugin execution as well, this piece of code is run, due to which the Context Object might be overridden and cause further errors
			// wrapping it up into a try-catch block ensures that if web plugin is running, then appium wont ovverride its settings in the context and vice-versa
		}
	}

	public static void addDataRgumentsInFunctionCall(String... args) {
		DataArguments dataarguments = new DataArguments();
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			DataArgument dataArgument = new DataArgument();
			dataArgument.setArgumentName("args" + i);
			dataArgument.setArgumentPosition(i);
			dataArgument.setValue(arg);
			// dataArgument.setDataType("String");
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
		Context.session().setSettings(getSettings());
	}

	public static void addFunction(String functionName) {
		System.out.println("[Function Call " + functionName + "]");
		FunctionCall fc = new FunctionCall();
		Function function = new Function();
		function.setCallTimeoutInMillis(90000);
		function.setMethodName(functionName);
		fc.setFunction(function);

		Context.set(new InvocationContext(fc));
		WebDriverDispatcher.preKeywordClearance();

		WebDriverDispatcher.currentKeyword = new CurrentKeyword();
	}

	private Map<String, String> getSettings() {
		String defaultInstallDir = new File("").getAbsolutePath();
		// defaultInstallDir =
		// "C:\\Users\\neon.nishant\\Desktop\\OpKeyStudioEclipse\\trunk\\OpKeyStudioIDE";
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
		settingsMap.put("_HighlightObject", "true");
		settingsMap.put("_ObjectVisibilityCheck", "false");
		settingsMap.put("________ignore________", "NOTE: Below items can also be fetched through invocation arguments defined in plugin descriptor. Ignore the underscore edges in names");
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

		settingsMap.put("_DefaultPluginLocation_", defaultInstallDir + File.separator + "resources" + File.separator + "libraries" + File.separator + "Plugins" + File.separator + "Web");

		settingsMap.put("_IOSDirectory_", "");
		settingsMap.put("_PluginID_", "");
		settingsMap.put("_RunningViaSpockAgent_", "false");
		settingsMap.put("_TimeDifferenceBetweenAgentAndPlugin_", "0");
		settingsMap.put("_SessionID_", "");
		return settingsMap;
	}

}
