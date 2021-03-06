package com.opkey.appium;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileApplication;
import com.crestech.opkey.plugin.communication.contracts.functioncall.MobileDevice;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.contexts.Context;
import com.crestech.opkey.plugin.exceptionhandling.ArgumentDataMissingException;
import com.opkey.appium.ObjectFromatter.ObjectConverter;
import com.opkey.appium.caller.FunctionCaller;
import com.opkey.appium.context.ContextInitiator;
import com.opkey.appium.sessions.SessionHandler;
import com.opkeystudio.runtime.ORObject;
import com.plugin.appium.AppiumObject;
import com.plugin.appium.exceptionhandlers.AdbNotFoundException;
import com.plugin.appium.exceptionhandlers.KeywordMethodOrArgumentValidationFailException;
import com.plugin.appium.exceptionhandlers.ObjectNotFoundException;
import com.plugin.appium.exceptionhandlers.TimeOut_ObjectNotFoundException;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidCheckBox;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidListControl;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidObject;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidPicker;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidRadio;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidWindowHandling;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AppiumSpecificUnCategorised;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Connect2AppiumServer;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Gestures;
import com.plugin.appium.keywords.AppiumSpecificKeyword.MenuHandling;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Orientation;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Seekbar;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Toggle;
import com.plugin.appium.keywords.GenericKeyword.Browser;
import com.plugin.appium.keywords.GenericKeyword.Button;
import com.plugin.appium.keywords.GenericKeyword.Checkbox;
import com.plugin.appium.keywords.GenericKeyword.Deprecate;
import com.plugin.appium.keywords.GenericKeyword.DropDown;
import com.plugin.appium.keywords.GenericKeyword.EditBox;
import com.plugin.appium.keywords.GenericKeyword.Frame;
import com.plugin.appium.keywords.GenericKeyword.Image;
import com.plugin.appium.keywords.GenericKeyword.Links;
import com.plugin.appium.keywords.GenericKeyword.ListControl;
import com.plugin.appium.keywords.GenericKeyword.PopUp;
import com.plugin.appium.keywords.GenericKeyword.Radio;
import com.plugin.appium.keywords.GenericKeyword.SetCapabilities;
import com.plugin.appium.keywords.GenericKeyword.Table;
import com.plugin.appium.keywords.GenericKeyword.TextArea;
import com.plugin.appium.keywords.GenericKeyword.UnCategorised;
import com.plugin.appium.keywords.GenericKeyword.WebObjects;
import com.plugin.appium.keywords.GenericKeyword.Window;
import com.plugin.appium.keywords.GenericKeyword.actionByText.ActionByText;

public class OpKeyAppiumKeywords {
	public OpKeyAppiumKeywords() {
		new ContextInitiator().initContext();
	}

	public String GetCurrentOrientation() throws ToolNotSetException {
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);
		System.out.println(">> Appium Keyword Called GetCurrentOrientation");

		ContextInitiator.addFunction("GetCurrentOrientation");
		// Method_getCurrentOrientation
		FunctionResult functionResult = FunctionCaller.execute(() -> new Orientation().Method_getCurrentOrientation());
		return functionResult.getOutput();

	}

	public boolean IsCurrentOrientationLandScape() throws ToolNotSetException {
		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called IsCurrentOrientationLandScape");
		ContextInitiator.addFunction(methodName);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Orientation().Method_isCurrentOrientationLandScape());
		return DataType.getBoolean(functionResult.getOutput());
	}

	/** Method_isCurrentOrientationPortrait */

	public boolean IsCurrentOrientationPortrait() throws ToolNotSetException {
		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called IsCurrentOrientationPortrait");
		ContextInitiator.addFunction(methodName);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Orientation().Method_isCurrentOrientationPortrait());
		return DataType.getBoolean(functionResult.getOutput());
	}

	/**
	 * Method_currentOrientationChangeToPortrait
	 * 
	 * @throws ToolNotSetException
	 */

	public boolean CurrentOrientationChangeToPortrait() throws ToolNotSetException {
		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called CurrentOrientationChangeToPortrait");
		ContextInitiator.addFunction(methodName);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Orientation().Method_currentOrientationChangeToPortrait());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean CurrentOrientationChangeToLandScape() throws ToolNotSetException {
		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called CurrentOrientationChangeToLandScape");
		ContextInitiator.addFunction(methodName);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Orientation().Method_currentOrientationChangeToLandScape());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Flick(int arg0, int arg1) throws ToolNotSetException, KeywordMethodOrArgumentValidationFailException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called Flick");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_flick
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_flick(arg0, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Tap(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, KeywordMethodOrArgumentValidationFailException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called Tap");

		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_Tap
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_Tap(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Launch_iOSApplicationOnSimulator(String arg0, String arg1) {

		System.out.println(">> Appium Keyword Called Launch _iOSApplicationOnSimulator");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1); // Method_Launch_iOSApplicationOnSimulator

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean PressMenuButton() throws ToolNotSetException {
		System.out.println(">> Appium Keyword Called PressMenuButton");
		ContextInitiator.addFunction("PressMenuButton");

		// Method_PressMenu
		FunctionResult functionResult = FunctionCaller.execute(() -> new MenuHandling().Method_PressMenu());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public String GetCurrentWindow() {

		System.out.println(">> Appium Keyword Called GetCurrentWindow");

		ContextInitiator.addFunction("GetCurrentWindow");
		// Method_getCurrentWindow

		return FunctionCaller.execute(() -> new MenuHandling().Method_PressMenu()).getOutput();

	}

	public boolean SwitchWindow(String arg0) throws ToolNotSetException, InterruptedException {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		System.out.println(">> Appium Keyword Called SwitchWindow");

		// Method_switchWindow
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidWindowHandling().Method_switchWindow(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ScrollDown() throws ToolNotSetException, InterruptedException, KeywordMethodOrArgumentValidationFailException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ScrollDown");

		ContextInitiator.addFunction("ScrollDown");
		// Method_ScrollDown
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_ScrollDown());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ScrollUp() throws ToolNotSetException, InterruptedException, KeywordMethodOrArgumentValidationFailException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ScrollUp");

		ContextInitiator.addFunction("ScrollUp");
		// Method_ScrollUp
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_ScrollUp());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Toggle(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called Toggle");

		ContextInitiator.addFunction(methodName);
		// Method_Toggle
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Toggle().Method_Toggle(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Switch(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called Switch");
		ContextInitiator.addFunction(methodName); // Method_Switch
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new com.plugin.appium.keywords.AppiumSpecificKeyword.Switch().Method_Switch(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean LongPress(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, KeywordMethodOrArgumentValidationFailException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called LongPress");

		ContextInitiator.addFunction(methodName); // Method_LongPress
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_LongPress(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DoubleTouch(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DoubleTouch");

		ContextInitiator.addFunction(methodName); // Method_DoubleTouch
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_DoubleTouch(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Touch(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, KeywordMethodOrArgumentValidationFailException, TimeOut_ObjectNotFoundException {
		ContextInitiator.addFunction("Touch");
		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called Touch");

		ContextInitiator.addFunction("Touch"); // Method_Touch
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_Touch(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GetObjectImage(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, IOException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called GetObjectImage");

		ContextInitiator.addFunction(methodName); // Method_getObjectImage
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidObject().Method_getObjectImage(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectRadioButtton(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectRadioButtton");

		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_SelectRadioButton
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidRadio().Method_SelectRadioButton(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GetRadioButtonStatus(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called GetRadioButtonStatus");

		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getRadioButtonStatus
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidRadio().Method_getRadioButtonStatus(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyRadioButtonStatus(ORObject arg0, boolean arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyRadioButtonStatus");

		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyRadioButtonStatus
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidRadio().Method_VerifyRadioButtonStatus(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GetObjectCoordinates(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called GetObjectCoordinates");

		ContextInitiator.addFunction(methodName);
		// Method_GetObjectCordinates
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidObject().Method_GetObjectCordinates(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean LaunchChromeOnMobile(MobileDevice device, String url) throws ToolNotSetException {

		System.out.println(">> Appium Keyword Called LaunchChromeOnMobile");
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(device.getDisplayName(), url);
		try {
			device = SessionHandler.getSessionInfo().mobileDevice;
			device.setVersion(device.getVersion());
			device.setOperatingSystem("Android");
			
			// Method_Launch_ChromeBrowser
			Context.session().getSettings().put("PlatformVersion", device.getVersion());
			FunctionResult functionResult = new com.plugin.appium.keywords.AppiumSpecificKeyword.Connect2AppiumServer().Method_Launch_ChromeBrowser(device, url);
			String boolString = functionResult.getOutput();

			ReportHelper.addReportStep(DataType.getMethodName(), functionResult);
			return DataType.getBoolean(boolString);
		} catch (Exception e) {
			ReportHelper.addReportStep(DataType.getMethodName(), e);
			e.printStackTrace();
			throw new ToolNotSetException();
		}
	}

	public boolean Mobile_LaunchAndroidApplication(MobileDevice deviceToBeIgnored, String androidApplicationPathh) throws Exception {
		String methodName = DataType.getMethodName();

		final MobileDevice device = SessionHandler.getSessionInfo().getMobileDevice();
		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(device.getDisplayName(), androidApplicationPathh); // Method_SetPickerValue

		MobileApplication mobileApplication = new MobileApplication();
		mobileApplication.setApplicationPath(androidApplicationPathh);
		mobileApplication.setDisplayName(new File(androidApplicationPathh).getName());
		mobileApplication.setOperatingSystem(SessionHandler.getSessionInfo().mobileDevice.getOperatingSystem());
		if (SessionHandler.getSessionInfo().mobileCapabilities.size() > 0) {
			mobileApplication.setPackage(SessionHandler.getSessionInfo().mobileCapabilities.get("appPackage"));
			mobileApplication.setMainActivity(SessionHandler.getSessionInfo().mobileCapabilities.get("appActivity"));
		}
		
		//mobileApplication.setPackage(System.getenv("APP_PACKAGE"));
		//mobileApplication.setMainActivity(System.getenv("APP_ACTIVITY"));

//		Context.session().getSettings().put("AppiumServer", "C:\\Users\\Ahmad\\AppData\\Roaming\\npm\\node_modules\\appium");
//		Context.session().getSettings().put("Host", "localhost");
//		Context.session().getSettings().put("Port", "4723");
		Context.session().getSettings().put("PlatformVersion", device.getVersion());

		FunctionResult functionResult = FunctionCaller.execute(() -> new Connect2AppiumServer().Method_Launch_AndroidApplication(device, mobileApplication));

		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean LaunchSafariOn_iOS(String arg0, String arg1) {

		System.out.println(">> Appium Keyword Called LaunchSafariOn_iOS");

		ContextInitiator.addFunction("LaunchSafariOn_iOS");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_LaunchSafarOnIos

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextOnEditBox(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeTextOnEditBox");
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeTextOnEditBox
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_typeTextOnEditBox(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Click(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called Click");
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);

		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_ObjectClick

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_ObjectClick(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	/* WebDriverException */
	public boolean RefreshBrowser() {

		System.out.println(">> Appium Keyword Called RefreshBrowser");

		ContextInitiator.addFunction("RefreshBrowser");
		// Method_RefreshBrowser
		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_RefreshBrowser());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean MaximizeBrowser() throws Exception {

		System.out.println(">> Appium Keyword Called MaximizeBrowser");

		ContextInitiator.addFunction("MaximizeBrowser");
		// Method_MaximizeBrowser
		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_MaximizeBrowser());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GoBackAndWait(int arg0) {

		System.out.println(">> Appium Keyword Called GoBackAndWait");

		ContextInitiator.addFunction("GoBackAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_goBackAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_goBackAndWait(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ClearEditField(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called ClearEditField");
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clearEditField
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_clearEditField(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SetPickerValue(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SetPickerValue");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_SetPickerValue
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidPicker().Method_SetPickerValue(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectCheckBox(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectCheckBox");

		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_appiumSelectCheckBox

		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidCheckBox().Method_appiumSelectCheckBox(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean TapOnCoordinates(ORObject arg0, String arg1, String arg2)
			throws ToolNotSetException, IOException, InterruptedException, AdbNotFoundException, ObjectNotFoundException, TimeOut_ObjectNotFoundException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called TapOnCoordinates");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2); // Method_tapOnCoordinate
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new AppiumSpecificUnCategorised().Method_tapOnCoordinate(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean LoadAppium() {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called LoadAppium");

		ContextInitiator.addFunction(methodName);
		// Method_LoadMe
		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_LoadMe());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean TypeKeysOnEditBox(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called TypeKeysOnEditBox");

		ContextInitiator.addFunction("TypeKeysOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_typeKeysOnEditBox
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_typeKeysOnEditBox(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyEditBoxText(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyEditBoxText");

		ContextInitiator.addFunction("VerifyEditBoxText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyeditboxtext
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_verifyeditboxtext(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean OpenBrowser(String arg0, String arg1) throws ToolNotSetException, IOException, InterruptedException {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called OpenBrowser");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_WebBrowserOpen
		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_WebBrowserOpen(arg0, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectDropDownItem(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectDropDownItem");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_selectDropDownItem(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectCheckBox(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectCheckBox");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectCheckBox
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_selectCheckBox(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectRadioButton(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectRadioButton");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_SelectRadio
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Radio().Method_SelectRadio(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DoubleClick(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DoubleClick");

		ContextInitiator.addFunction(methodName); // Method_dblClick
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_dblClick(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean RefreshAndWait(int arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called RefreshAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_refreshAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_refreshAndWait(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean TypeTextAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called TypeTextAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2); // Method_typeTextAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_typeTextAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean TypeKeysAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called TypeKeysAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeKeysAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_typeKeysAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyEditable(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyEditable");

		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditable
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditable(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectMultipleDropDownItem(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectMultipleDropDownItem");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectMultipleDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new ListControl().Method_selectMultipleDropDownItem(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DeSelectDropDownItem(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DeSelectDropDownItem");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_deselectDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_deselectDropDownItem(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DeSelectMultipleDropDownItem(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DeSelectMultipleDropDownItem");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_deselectMultipleDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new ListControl().Method_deselectMultipleDropDownItem(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DeSelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DeSelectDropDownItemAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		// Method_deselectDropDownItemAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_deselectDropDownItemAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DeSelectAllDropDownItemsAndWait(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DeSelectAllDropDownItemsAndWait");

		ContextInitiator.addFunction("DeSelectAllDropDownItemsAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_deselectAllDropDownItemsAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_deselectAllDropDownItemsAndWait(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyDropDownItemExists(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyDropDownItemExists");

		ContextInitiator.addFunction("VerifyDropDownItemExists");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyDropDownItemExists
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyDropDownItemExists(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeSelectCheckBox(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DeSelectCheckBox");

		ContextInitiator.addFunction("DeSelectCheckBox");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_deSelectCheckBox
		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_deSelectCheckBox(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean verifyCheckBoxStatus(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called verifyCheckBoxStatus");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxStatus
		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_verifyCheckBoxStatus(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectCheckBoxAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectCheckBoxAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		// Method_selectCheckBoxAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_selectCheckBoxAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DeSelectCheckBoxAndWait(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DeSelectCheckBoxAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_deSelectCheckBoxAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_deSelectCheckBoxAndWait(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectRadioButtonAndWait(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectRadioButtonAndWait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectRadioButtonAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_selectRadioButtonAndWait(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ClickTableCell");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickTableCell
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_clickTableCell(object, arg1, arg2, arg3, arg4));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectExists(ORObject arg0) throws ToolNotSetException {

		System.out.println(">> Appium Keyword Called VerifyObjectExists");
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectExists

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_ObjectExists(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public int GetChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">> Appium Keyword Called GetChildObjectCount");

		ContextInitiator.addFunction("GetChildObjectCount");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3); // Method_getChildObjectCount
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_getChildObjectCount(object, arg1, arg2, arg3));
		return DataType.getInt(functionResult.getOutput());

	}

	public boolean CloseBrowser(String arg0) {

		System.out.println(">> Appium Keyword Called CloseBrowser");

		ContextInitiator.addFunction("CloseBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_CloseBrowser
		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_CloseBrowser(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean GoForward() {

		System.out.println(">> Appium Keyword Called GoForward");

		ContextInitiator.addFunction("GoForward");
		// Method_goForward

		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_goForward());
		return DataType.getBoolean(functionResult.getOutput());
	}

	/* WebDriverException */
	public boolean SyncBrowser() {

		System.out.println(">> Appium Keyword Called SyncBrowser");

		ContextInitiator.addFunction("SyncBrowser");
		// Method_syncBrowser

		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_syncBrowser());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SetFocus(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SetFocus");

		ContextInitiator.addFunction("SetFocus");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_SetFocus
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_SetFocus(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectEnabled(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyObjectEnabled");

		ContextInitiator.addFunction("VerifyObjectEnabled");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectisEnabled
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_ObjectisEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectVisible(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyObjectVisible");

		ContextInitiator.addFunction("VerifyObjectVisible");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyObjectVisible
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_verifyObjectVisible(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyObjectText(ORObject arg0, String arg1, String arg2, String arg3) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyObjectText");

		ContextInitiator.addFunction("VerifyObjectText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectTextVerification

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_ObjectTextVerification(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyObjectPropertyValue(ORObject arg0, String arg1, String arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyObjectPropertyValue");

		ContextInitiator.addFunction("VerifyObjectPropertyValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyPropertyValue
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_VerifyPropertyValue(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean WaitForObject(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called WaitForObject");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_waitforObject
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_waitforObject(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Wait(int arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called Wait");

		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_wait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_wait(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DoubleClickAndWait(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DoubleClickAndWait");

		ContextInitiator.addFunction("DoubleClickAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_doubleClickAndWait(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DoubleClickAt(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DoubleClickAt");

		ContextInitiator.addFunction("DoubleClickAt");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickAt
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_doubleClickAt(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DragAndDrop(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DragAndDrop");

		ContextInitiator.addFunction("DragAndDrop");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_dragAndDrop
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_dragAndDrop(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DragAndDropAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called DragAndDropAndWait");

		ContextInitiator.addFunction("DragAndDropAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2); // Method_dragAndDropAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_dragAndDropAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GoForwardAndWait(int arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called GoForwardAndWait");

		ContextInitiator.addFunction("GoForwardAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_goForwardAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_goForwardAndWait(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyAllDropDownItems(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyAllDropDownItems");

		ContextInitiator.addFunction("VerifyAllDropDownItems");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyAllDropDownItems
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyAllDropDownItems(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectDropDownItemAndWait");

		ContextInitiator.addFunction("SelectDropDownItemAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2); // Method_selectDropDownItemAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_selectDropDownItemAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, String arg4, String arg5) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyTextInTableCell");

		ContextInitiator.addFunction("VerifyTextInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextInTable
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTextInTable(object, arg1, arg2, arg3, arg4, arg5));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ClickTableCellAndWait(ORObject arg0, int arg1, int arg2, String arg3, int arg4, int arg5) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ClickTableCellAndWait");

		ContextInitiator.addFunction("ClickTableCellAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickTableCellAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_clickTableCellAndWait(object, arg1, arg2, arg3, arg4, arg5));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean WaitForObjectProperty(ORObject arg0, String arg1, String arg2, int arg3) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called WaitForObjectProperty");

		ContextInitiator.addFunction("WaitForObjectProperty");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_waitForObjectProperty
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_waitForObjectProperty(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean HighlightObject(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called HighlightObject");

		ContextInitiator.addFunction("HighlightObject");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_highlightObject
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_highlightObject(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean RunScriptAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called RunScriptAndWait");

		ContextInitiator.addFunction("RunScriptAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_runScriptAndWait
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_runScriptAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean CloseAllBrowsers() throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called CloseAllBrowsers");

		ContextInitiator.addFunction("CloseAllBrowsers");
		// Method_CloseAllBrowsers
		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_CloseAllBrowsers());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectWindow(String arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SelectWindow");

		ContextInitiator.addFunction("SelectWindow");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_selectWindow
		FunctionResult functionResult = FunctionCaller.execute(() -> new Window().Method_selectWindow(arg0, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean CloseSelectedWindow(String arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called CloseSelectedWindow");

		ContextInitiator.addFunction("CloseSelectedWindow");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_closeSelectedWindow
		FunctionResult functionResult = FunctionCaller.execute(() -> new Window().Method_closeSelectedWindow(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SetFocusOnWindow(int arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SetFocusOnWindow");

		ContextInitiator.addFunction("SetFocusOnWindow");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_setFocousOnWindow
		FunctionResult functionResult = FunctionCaller.execute(() -> new Window().Method_setFocousOnWindow(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyBrowserExist(String arg0) {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyBrowserExist");

		ContextInitiator.addFunction("VerifyBrowserExist");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyBrowserExist

		try {
			FunctionResult functionResult = new FunctionResult();
			String boolString = functionResult.getOutput();
			ReportHelper.addReportStep(methodName, functionResult);
			return DataType.getBoolean(boolString);
		} catch (Exception e) {
			ReportHelper.addReportStep(methodName, e);
		}
		return false;

	}

	public boolean VerifyEditBoxDefaultValue(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyEditBoxDefaultValue");

		ContextInitiator.addFunction("VerifyEditBoxDefaultValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxDefaultValue
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxDefaultValue(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyEditBoxNotExist(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyEditBoxNotExist");

		ContextInitiator.addFunction("VerifyEditBoxNotExist");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyEditBoxnotExist
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxnotExist(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyEditBoxToolTip(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyEditBoxToolTip");

		ContextInitiator.addFunction("VerifyEditBoxToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyEditBoxToolTip
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_verifyEditBoxToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyDropDownDefaultItem(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyDropDownDefaultItem");

		ContextInitiator.addFunction("VerifyDropDownDefaultItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyDropDownDefaultItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyDropDownDefaultItem(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ClickButton(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ClickButton");

		ContextInitiator.addFunction("ClickButton");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickButton
		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_clickButton(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ClickButtonAndWait(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ClickButtonAndWait");

		ContextInitiator.addFunction("ClickButtonAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_clickButtonAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_clickButtonAndWait(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyRadioButtonSelected(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyRadioButtonSelected");

		ContextInitiator.addFunction("VerifyRadioButtonSelected");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_VerifyRadioButtonSelected
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Radio().Method_VerifyRadioButtonSelected(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyRadioButtonNotSelected(ORObject arg0, int arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyRadioButtonNotSelected");

		ContextInitiator.addFunction("VerifyRadioButtonNotSelected");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyRadioButtonNotSelected
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_VerifyRadioButtonNotSelected(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyCheckBoxToolTip(ORObject arg0, String arg1) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyCheckBoxToolTip");

		ContextInitiator.addFunction("VerifyCheckBoxToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxToolTip
		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_verifyCheckBoxToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyTextAreaText(ORObject arg0, String arg1) throws Exception {
		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyTextAreaText");

		ContextInitiator.addFunction("VerifyTextAreaText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaText
		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_verifyTextAreaText(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyTextAreaEnabled(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyTextAreaEnabled");

		ContextInitiator.addFunction("VerifyTextAreaEnabled");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaEnabled
		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_verifyTextAreaEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean TypeTextAndEnterTextArea(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeTextAndEnterTextArea");

		ContextInitiator.addFunction("TypeTextAndEnterTextArea");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeTextandEnterTextArea

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_typeTextandEnterTextArea(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaDefaultValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaDefaultValue");

		ContextInitiator.addFunction("VerifyTextAreaDefaultValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaDefaultValue

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreaDefaultValue(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaDisabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaDisabled");

		ContextInitiator.addFunction("VerifyTextAreaDisabled");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreaDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaEditable(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaEditable");

		ContextInitiator.addFunction("VerifyTextAreaEditable");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaEditable

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_verifyTextAreaEditable(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaNotEditable(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaNotEditable");

		ContextInitiator.addFunction("VerifyTextAreaNotEditable");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaNotEditable

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreaNotEditable(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaToolTip(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaToolTip");

		ContextInitiator.addFunction("VerifyTextAreaToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_verifyTextAreaToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTableColumnNumber(ORObject arg0, int arg1, String arg2, int arg3) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTableColumnNumber");

		ContextInitiator.addFunction("VerifyTableColumnNumber");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTableColumnNumber

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTableColumnNumber(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTableColumnText(ORObject arg0, int arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTableColumnText");

		ContextInitiator.addFunction("VerifyTableColumnText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTableColumnText

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTableColumnText(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTableRowText(ORObject arg0, int arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTableRowText");

		ContextInitiator.addFunction("VerifyTableRowText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTableRowText

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTableRowText(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTableColumnHeader(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTableColumnHeader");

		ContextInitiator.addFunction("VerifyTableColumnHeader");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTableColumnHeader

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTableColumnHeader(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectToolTip(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyObjectToolTip");

		ContextInitiator.addFunction("VerifyObjectToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyObjectToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_verifyObjectToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SelectMultipleDropDownItemAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">> Appium Keyword Called SelectMultipleDropDownItemAndWait");

		ContextInitiator.addFunction("SelectMultipleDropDownItemAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectMultipleDropDownItemAndWait

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_selectMultipleDropDownItemAndWait(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DoubleClickButton(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called DoubleClickButton");

		ContextInitiator.addFunction("DoubleClickButton");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickButton

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_doubleClickButton(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean FocusButton(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called FocusButton");

		ContextInitiator.addFunction("FocusButton");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_focusButton

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_focusButton(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusButton() throws Exception {

		System.out.println(">> Appium Keyword Called DeFocusButton");

		ContextInitiator.addFunction("DeFocusButton");
		// Method_deFocusButton

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_deFocusButton());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyButtonDisabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyButtonDisabled");

		ContextInitiator.addFunction("VerifyButtonDisabled");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyButtonDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyButtonDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyButtonExist(ORObject arg0) throws Exception {
		ContextInitiator.addFunction("VerifyButtonExist");
		// Method_verifyButtonExist

		AppiumObject object = new ObjectConverter().formatObject(arg0);
		System.out.println(">> Appium Keyword Called VerifyButtonExist");

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_verifyButtonExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClickLinkInTableCell(ORObject arg0, int arg1, int arg2, int arg3) throws Exception {

		System.out.println(">> Appium Keyword Called ClickLinkInTableCell");

		ContextInitiator.addFunction("ClickLinkInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickLinkInTableCell

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_clickLinkInTableCell(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean TypeTextInTextArea(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeTextInTextArea");

		ContextInitiator.addFunction("TypeTextInTextArea");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeTextInTextArea

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_typeTextInTextArea(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean TypeKeysInTextArea(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeKeysInTextArea");

		ContextInitiator.addFunction("TypeKeysInTextArea");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeKeysInTextArea

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_typeKeysInTextArea(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClearTextArea(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called ClearTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clearTextArea

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_clearTextArea(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SetfocusTextArea(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called SetfocusTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_SetfocusTextArea

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_SetfocusTextArea(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusTextArea() throws Exception {

		System.out.println(">> Appium Keyword Called DeFocusTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusTextArea

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_deFocusTextArea());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SelectRadioButtonOnIndexBasis(ORObject arg0, int arg1) {

		System.out.println(">> Appium Keyword Called SelectRadioButtonOnIndexBasis");

		ContextInitiator.addFunction("SelectRadioButtonOnIndexBasis");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectRadioButtonOnIndexBasis

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_selectRadioButtonOnIndexBasis(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaValue");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaValue

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreaValue(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaExist(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_verifyTextAreaExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaNotExist(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaNotExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreanotExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreanotExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTableRowNumber(ORObject arg0, int arg1, String arg2, int arg3) {

		System.out.println(">> Appium Keyword Called VerifyTableRowNumber");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3); // Method_verifyTableRowNumber

		FunctionResult functionResult = new FunctionResult();
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaName(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaName");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaName

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreaName(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextAreaLength(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextAreaLength");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextAreaLength

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextAreaLength(object, arg1));
		String bool = functionResult.getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyLinkCount(int arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyLinkCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyLinkCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_verifyLinkCount(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectDoesNotExists(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyObjectDoesNotExists");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyObjectdoesnotExists

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyObjectdoesnotExists(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectDisabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyObjectDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyobjectDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyobjectDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean PressTAB() throws ToolNotSetException, AWTException {

		System.out.println(">> Appium Keyword Called PressTAB");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_PressTAB

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_PressTAB());
		return DataType.getBoolean(functionResult.getOutput());

	}

	/* WebDriverException */
	public boolean CaptureSnapshot(String arg0) {

		System.out.println(">> Appium Keyword Called CaptureSnapshot");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_CaptureSnapshot

		FunctionResult functionResult = new FunctionResult();
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyPopUpText(ORObject arg0, String arg1, String arg2, String arg3) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyPopUpText");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyPopUpText

		FunctionResult functionResult = FunctionCaller.execute(() -> new PopUp().Method_VerifyPopUpText(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClickLink(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called ClickLink");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickLink

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_clickLink(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyLinkExist(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyLinkExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyLinkExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_verifyLinkExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyAllLinkExist(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyAllLinkExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyAllLinkExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyAllLinkExist(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyButtonEnabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyButtonEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyButtonEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_verifyButtonEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyButtonToolTip(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyButtonToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyButtonToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_verifyButtonToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClickButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) throws Exception {
		System.out.println(">> Appium Keyword Called ClickButtonInTableCell");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickButtonInTableCell

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_clickButtonInTableCell(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageCount(int arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyImageCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyImageCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_verifyImageCount(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageEnabled(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyImageEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyImageEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_verifyImageEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageDisabled(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyImageDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyImageDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyImageDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageToolTip(ORObject arg0, String arg1) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyImageToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyImageToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_verifyImageToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean WaitforImageLoad(ORObject arg0, int arg1) throws Exception {
		System.out.println(">> Appium Keyword Called WaitforImageLoad");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_waitforImageLoad

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_waitforImageLoad(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean KeyLeft() throws ToolNotSetException, AWTException {

		System.out.println(">> Appium Keyword Called KeyLeft");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_KeyLeft

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_KeyLeft());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean KeyRight() throws ToolNotSetException, AWTException {

		System.out.println(">> Appium Keyword Called KeyRight");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_KeyRight

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_KeyRight());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyAllLink(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyAllLink");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyAllLink

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyAllLink(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyDropDownToolTip(ORObject arg0, String arg1) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyDropDownToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyDropDownToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyDropDownToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyLinkEnabled(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyLinkEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_verifyLinkEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyLinkDisabled(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyLinkDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyLinkDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyLinkVisible(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyLinkVisible");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkVisible

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_verifyLinkVisible(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean WaitforLink(ORObject arg0, int arg1) throws Exception {
		System.out.println(">> Appium Keyword Called WaitforLink");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_waitforLink

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_waitforLink(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageVisible(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyImageVisible");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyImageVisible

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_verifyImageVisible(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageNotVisible(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyImageNotVisible");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyImageNotVisible

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyImageNotVisible(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyImageExist(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyImageExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyImageExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_verifyImageExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DoubleClickImage(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called DoubleClickImage");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickImage

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_doubleClickImage(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClickImage(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called ClickImage");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickImage

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_clickImage(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyLinkToolTip(ORObject arg0, String arg1) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyLinkToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyLinkToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_verifyLinkToolTip(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean KeyPressNative(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called KeyPressNative");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_keyPressNative

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_keyPressNative(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Enter() throws ToolNotSetException, AWTException {

		System.out.println(">> Appium Keyword Called Enter");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_Enter

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_Enter());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean WaitForEditBoxDisabled(ORObject arg0, int arg1) throws Exception {
		System.out.println(">> Appium Keyword Called WaitForEditBoxDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_waitForEditBoxDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_waitForEditBoxDisabled(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean WaitForEditBoxEnabled(ORObject arg0, int arg1) throws Exception {
		System.out.println(">> Appium Keyword Called WaitForEditBoxEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_waitForEditBoxEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_waitForEditBoxEnabled(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyRadioButtonExist(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyRadioButtonExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyRadioButtonExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Radio().Method_verifyRadioButtonExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyRadioButtonDisabled(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyRadioButtonDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyRadioButtonDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyRadioButtonDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyRadioButtonEnabled(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called VerifyRadioButtonEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyRadioButtonEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Radio().Method_verifyRadioButtonEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusRadioButton() throws Exception {

		System.out.println(">> Appium Keyword Called DeFocusRadioButton");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusRadioButton

		FunctionResult functionResult = FunctionCaller.execute(() -> new Radio().Method_deFocusRadioButton());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean FocusRadioButton(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called FocusRadioButton");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_focusRadioButton

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_focusRadioButton(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyCheckBoxExist(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyCheckBoxExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_verifyCheckBoxExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyCheckBoxDisabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyCheckBoxDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyCheckBoxDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyCheckBoxEnabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyCheckBoxEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_verifyCheckBoxEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusCheckBox() throws Exception {

		System.out.println(">> Appium Keyword Called DeFocusCheckBox");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusCheckBox

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_deFocusCheckBox());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean FocusCheckBox(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called FocusCheckBox");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_focusCheckBox

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_focusCheckBox(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyDropDownSelection(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyDropDownSelection");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyDropDownSelection

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyDropDownSelection(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyDropDownExist(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyDropDownExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyDropDownExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyDropDownExist(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyDropDownDisabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyDropDownDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyDropDownDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_VerifyDropDownDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyDropDownEnabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyDropDownEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyDropDownEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_VerifyDropDownEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyAllDropDownItemExist(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyAllDropDownItemExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyAllDropDownItemExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyAllDropDownItemExist(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyMultipleDropDownItemExist(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyMultipleDropDownItemExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyMultipleDropDownItemExist
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyMultipleDropDownItemExist(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyDropDownItemCount(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyDropDownItemCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyDropDownItemCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_verifyDropDownItemCount(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusfromDropDown() {

		System.out.println(">> Appium Keyword Called DeFocusfromDropDown");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusfromDropDown

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_deFocusfromDropDown());
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SetFocusonDropDown(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called SetFocusonDropDown");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_SetFocusonDropDown

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_deFocusfromDropDown());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxExistAndWait(ORObject arg0) {

		System.out.println(">> Appium Keyword Called VerifyEditBoxExistAndWait");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxExist

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxExist(object, 80));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyEditBoxValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyEditBoxValue");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyEditBoxValue
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxValue(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxLength(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyEditBoxLength");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxLength

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxLength(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxName(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyEditBoxName");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxName

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxName(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxNonEditable(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyEditBoxNonEditable");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxNonEditable

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxNonEditable(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxEditable(ORObject arg0) {

		System.out.println(">> Appium Keyword Called VerifyEditBoxEditable");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxEditable

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_verifyEditBoxEditable(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxDisabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyEditBoxDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxDisabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyEditBoxDisabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyEditBoxEnabled(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyEditBoxEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxEnabled

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_verifyEditBoxEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusEditField() throws Exception {

		System.out.println(">> Appium Keyword Called DeFocusEditField");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusEditField

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_deFocusEditField());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SetfocusEditField(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called SetfocusEditField");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_SetfocusEditField

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_SetfocusEditField(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTextareaColsRowLength(ORObject arg0, int arg1, int arg2) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTextareaColsRowLength");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextareaColsRowLength

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_verifyTextareaColsRowLength(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyBrowserTitle(String arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyBrowserTitle");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_VerifyBrowserTitle

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_VerifyBrowserTitle(arg0, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean TypeSecureText(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeSecureText");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_typeSecureText
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_typeSecureText(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean NextPageObject(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called NextPageObject");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_nextPageObject

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_nextPageObject(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SelectGroupRadioButton(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called SelectGroupRadioButton");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectGroupRadioButton

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_selectGroupRadioButton(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ReportMessage(String arg0, String arg1) throws ToolNotSetException {

		System.out.println(">> Appium Keyword Called ReportMessage");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1); // Method_reportMessage

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_reportMessage(arg0, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean MouseHover(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called MouseHover");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_MouseHover

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_MouseHover(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean NavigateTo(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called NavigateTo");
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		// Method_navigateTo
		try {
			FunctionResult functionResult = new Browser().Method_navigateTo(arg0);
			String boolString = functionResult.getOutput();
			ReportHelper.addReportStep(methodName, functionResult);
			return DataType.getBoolean(functionResult.getOutput());
		} catch (Exception e) {
			ReportHelper.addReportStep(methodName, e);
		}

		return false;
	}

	public boolean AssertTextPresent(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called AssertTextPresent");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_AssertTextPresent

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_AssertTextPresent(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean ClickAt(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called ClickAt");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickAt

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_clickAt(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean MinimizeBrowser() throws Exception {

		System.out.println(">> Appium Keyword Called MinimizeBrowser");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_MinimizeBrowser

		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_MinimizeBrowser());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean AcceptPopup() throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());

		System.out.println(">> Appium Keyword Called AcceptPopup");

		// Method_acceptPopup

		FunctionResult functionResult = FunctionCaller.execute(() -> new PopUp().Method_acceptPopup());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DismissPopup() throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());

		System.out.println(">> Appium Keyword Called DismissPopup");

		// Method_dismissPopup

		FunctionResult functionResult = FunctionCaller.execute(() -> new PopUp().Method_dismissPopup());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyPopupPresent(String arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		System.out.println(">> Appium Keyword Called VerifyPopupPresent");

		// Method_verifyPopupPresent

		FunctionResult functionResult = FunctionCaller.execute(() -> new PopUp().Method_verifyPopupPresent(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SelectFrame(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called SelectFrame");

		// Method_selectFrame

		FunctionResult functionResult = FunctionCaller.execute(() -> new Frame().Method_selectFrame(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SwitchToDefaultContent() throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());

		System.out.println(">> Appium Keyword Called SwitchToDefaultContent");

		// Method_switchToDefaultContent

		FunctionResult functionResult = FunctionCaller.execute(() -> new Frame().Method_switchToDefaultContent());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyObjectValue(ORObject arg0, String arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called VerifyObjectValue");

		// Method_VerifyObjectValue
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_VerifyObjectValue(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GetObjectVisibility(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetObjectVisibility");

		// Method_getObjectVisibility
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectVisibility(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GetObjectExistence(ORObject arg0) throws Exception {
		System.out.println(">> Appium Keyword Called GetObjectExistence");
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getObjectExistence
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectExistence(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean GetObjectEnabled(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		System.out.println(">> Appium Keyword Called GetObjectEnabled");

		// Method_getObjectEnabled
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectEnabled(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ExcelCompare(String arg0, String arg1, String arg2, String arg3) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called ExcelCompare");

		// Method_excelCompare
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_excelCompare(arg0, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SetPage(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called SetPage");

		// Method_setPage
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_setPage(object));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyCheckboxStatusInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called VerifyCheckboxStatusInTableCell");

		// Method_verifyCheckboxStatusInTableCell
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyCheckboxStatusInTableCell(object, arg1, arg2, arg3, arg4));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectRadioButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called SelectRadioButtonInTableCell");

		// Method_selectRadioButtobTableCell
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_selectRadioButtobTableCell(object, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean RightClickOnObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called RightClickOnObject");

		// Method_rightClickAndSelect

		try {
			FunctionResult functionResult = new FunctionResult();
			String boolString = functionResult.getOutput();
			ReportHelper.addReportStep(methodName, functionResult);
			return DataType.getBoolean(boolString);
		} catch (Exception e) {
			ReportHelper.addReportStep(methodName, e);
		}
		return false;

	}

	public boolean SelectCheckBoxinTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called SelectCheckBoxinTableCell");

		// Method_selectCheckBoxinTableCell
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_selectCheckBoxinTableCell(object, arg1, arg2, arg3, arg4));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean WaitForObjectVisible(ORObject arg0, int arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called WaitForObjectVisible");

		// Method_waitforobjectvisible
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_waitforobjectvisible(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean WaitForObjectEditable(ORObject arg0, int arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called WaitForObjectEditable");

		// Method_waitForObjectEditable

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_waitForObjectEditable(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean TypeTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called TypeTextInTableCell");

		// Method_typeTextInTableCell

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_typeTextInTableCell(object, arg1, arg2, arg3, arg4, arg5));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean WaitForObjectEnable(ORObject arg0, int arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called WaitForObjectEnable");

		// Method_waitforobjectenable

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_waitforobjectenable(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Swipe(double arg0, double arg1, double arg2, double arg3, double arg4) throws ToolNotSetException, KeywordMethodOrArgumentValidationFailException, InterruptedException {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg2, arg3, arg4);

		System.out.println(">> Appium Keyword Called Swipe");

		// Method_swipe

		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_swipe(arg0, arg1, arg2, arg3, arg4));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Moblie_DeselectToggle(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called Moblie_DeselectToggle");

		// Method_deSelectToggle

		FunctionResult functionResult = FunctionCaller.execute(() -> new Toggle().Method_deSelectToggle(object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SwipeLeft() throws ToolNotSetException, InterruptedException, KeywordMethodOrArgumentValidationFailException {
		ContextInitiator.addFunction(DataType.getMethodName());

		System.out.println(">> Appium Keyword Called SwipeLeft");

		// Method_SwipeLeft

		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_SwipeLeft());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SwipeRight() throws ToolNotSetException, InterruptedException, KeywordMethodOrArgumentValidationFailException {
		ContextInitiator.addFunction(DataType.getMethodName());

		System.out.println(">> Appium Keyword Called SwipeRight");

		// Method_SwipeRight

		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_SwipeRight());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SwipeObject(ORObject arg0, int arg1, String arg2) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called SwipeObject");

		// Method_swipeWithObject

		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_swipeWithObject(object, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Swipe(int arg0, String arg1) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);

		System.out.println(">> Appium Keyword Called Swipe");

		// Method_MobilitySwipe

		String boolString = new FunctionResult().getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean SwipeTowards(String arg0) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		// Method_SwipeTowards

		System.out.println(">> Appium Keyword Called Mobile_SwipeTowards");
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_SwipeTowards(arg0));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SwipeOnObject(ORObject arg0, String arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_SwipeOnObject

		System.out.println(">> Appium Keyword Called Mobile_SwipeOnObject");
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_SwipeOnObject(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SwipeToObject(ORObject arg0, String arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Mobile_SwipeToObject

		System.out.println(">> Appium Keyword Called Mobile_SwipeToObject");
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_SwipeToObject(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SwipeToText(ORObject arg0, String arg1, int arg2, boolean arg3, String arg4) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_SwipeToText

		System.out.println(">> Appium Keyword Called Method_SwipeToText");
		FunctionResult functionResult = FunctionCaller.execute(() -> new Gestures().Method_SwipeToText(object, arg1, arg2, arg3, arg4));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Mobile_SetSeekbar(ORObject arg0, int arg1) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Seekbar().Method_SetSeekbar(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	// Argument mismatch
	public boolean VerifyEditBoxExist() {
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);

		System.out.println(">> Appium Keyword Called VerifyEditBoxExist");
		// Method_verifyEditBoxExist

//		 FunctionResult functionResult = FunctionCaller.execute(()-> new Deprecate().Method_verifyEditBoxExist)
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean ClickInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11,
			String arg12) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called ClickInTableCellByQuery");

		// Method_clickInTableCellByQuery

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, int arg6, String arg7, String arg8, String arg9, String arg10,
			String arg11, String arg12, String arg13) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called TypeTextInTableCellByQuery");

		// Method_typeTextInTableCellByQuery

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean Web_ClickByText(String arg0, int arg1, boolean arg2, ORObject arg3, String arg4, String arg5) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg4, arg5);

		FunctionResult functionResult;

		AppiumObject object = new ObjectConverter().formatObject(arg3);
		functionResult = FunctionCaller.execute(() -> new ActionByText().Method_clickByText(arg0, arg1, arg2, object, arg4, arg5));

		System.out.println(">> Appium Keyword Called Web_ClickByText");

		// Method_clickByText
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Web_ClickByTextInSequence(String arg0, int arg1, int arg2, boolean arg3, String arg4, int arg5, boolean arg6, ORObject arg7, ORObject arg8, ORObject arg9, ORObject arg10,
			ORObject arg11, boolean arg12, String arg13, int arg14, boolean arg15, String arg16, int arg17, boolean arg18, String arg19) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		String text1 = arg0;
		String text2 = arg4;
		String text3 = arg13;
		String text4 = arg16;
		String text5 = arg19;

		int index1 = arg1;
		int index2 = arg2;
		int index3 = arg5;
		int index4 = arg14;
		int index5 = arg17;

		boolean isContains1 = arg3;
		boolean isContains2 = arg6;
		boolean isContains3 = arg12;
		boolean isContains4 = arg15;
		boolean isContains5 = arg18;

		AppiumObject object1 = new ObjectConverter().formatObject(arg7);
		AppiumObject object2 = new ObjectConverter().formatObject(arg8);
		AppiumObject object3 = new ObjectConverter().formatObject(arg9);
		AppiumObject object4 = new ObjectConverter().formatObject(arg10);
		AppiumObject object5 = new ObjectConverter().formatObject(arg11);

		System.out.println(">> Appium Keyword Called Web_ClickByTextInSequence");

		// Method_clickByTextInSequence
		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_clickByTextInSequence(text1, index1, isContains1, text2, index2, isContains2, text3, index3, isContains3,
				text4, index4, isContains4, text5, index5, isContains5, object1, object2, object3, object4, object5));
		return DataType.getBoolean(functionResult.getOutput());

	}

	// Start//
	public boolean Web_SelectByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg4);

		System.out.println(">> Appium Keyword Called Web_SelectByText");

		// Method_SelectByText
		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_SelectByText(arg0, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Web_TypeByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, ORObject arg5) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg5);

		System.out.println(">> Appium Keyword Called Web_TypeByText");

		// Method_typeTextUsingText
		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_typeTextUsingText(arg0, arg1, arg2, arg3, arg4, object));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean MouseHoverOnText(String arg0, int arg1, boolean arg2, ORObject arg3) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg3);

		System.out.println(">> Appium Keyword Called MouseHoverOnText");

		// Method_mouseHoverOnText

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean ClickImageByTitleAlt(String arg0, int arg1, boolean arg2) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);

		System.out.println(">> Appium Keyword Called ClickImageByTitle/Alt");

		// Method_clickImageByAltText

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_clickImageByAltText(arg0, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Web_SelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, String arg4, ORObject arg5) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg5);

		System.out.println(">> Appium Keyword Called Web_SelectCheckboxByText");

		// Method_selectCheckBoxByText
		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_selectCheckBoxByText(arg0, arg1, arg2, arg3, arg4, object));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Web_DeSelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg4);

		System.out.println(">> Appium Keyword Called Web_DeSelectCheckboxByText");

		// Method_deSelectCheckBoxByText
		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_deSelectCheckBoxByText(arg0, arg1, arg2, arg3, object));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Web_SelectRadioButtonByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg4);

		System.out.println(">> Appium Keyword Called Web_SelectRadioButtonByText");

		// Method_selectRadioButtonByText

		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_selectRadioButtonByText(arg0, arg1, arg2, arg3, object));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Web_SelectDropDownByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, boolean arg5, ORObject arg6) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg6);

		System.out.println(">> Appium Keyword Called Web_SelectDropDownByText");

		// Method_selectDropDownByText

		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_selectDropDownByText(arg0, arg1, arg2, arg3, arg4, arg5, object));

		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean Web_GoBack() {

		System.out.println(">> Appium Keyword Called Web_GoBack");
		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_goBack

		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_goBack());

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Web_SelectListItem(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called Web_SelectListItem");

		// Method_selectListItem

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidListControl().Method_selectListItem(object, arg1));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean Web_VerifyListItemExists(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called Web_VerifyListItemExists");

		// Method_verifyListItemExists

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidListControl().Method_verifyListItemExists(object, arg1));

		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SelectDropDownInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) throws Exception {

		System.out.println(">> Appium Keyword Called SelectDropDownInTableCell");

		// Method_selectDropDownInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_selectDropDownInTableCell(object, arg1, arg2, arg3, arg4));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean DeSelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) throws Exception {

		System.out.println(">> Appium Keyword Called DeSelectMultipleDropdownItemInTableCell");

		// Method_deSelectMultipleDropDownItemInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_deSelectMultipleDropDownItemInTableCell(object, arg1, arg2, arg3, arg4));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean SelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) throws Exception {

		System.out.println(">> Appium Keyword Called SelectMultipleDropdownItemInTableCell");

		// Method_selectMultipleDropdownItemInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_selectMultipleDropdownItemInTableCell(object, arg1, arg2, arg3, arg4));

		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean ClickOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) throws Exception {

		System.out.println(">> Appium Keyword Called ClickOnObjectInTableCell");

		// Method_clickOnObjectInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_clickOnObjectInTableCell(object, arg1, arg2, arg3, arg4));

		return DataType.getBoolean(functionResult.getOutput());
	}

	/** method not found */
	public boolean TypeOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {

		System.out.println(">> Appium Keyword Called TypeOnObjectInTableCell");

		// Method_typeOnObjecttInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean WaitForObjectDisable(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called WaitForObjectDisable");

		// Method_waitForObjectdisable

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_waitForObjectdisable(object, arg1));

		return DataType.getBoolean(functionResult.getOutput());
	}

	/* Method argument mismatch */

	// Start

	public boolean VerifyAllButtons(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyAllButtons");

		// Method_verifyAllButtons

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_verifyAllButtons(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3, int arg4)
			throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">> Appium Keyword Called VerifyChildObjectCount");

		// Method_verifyChildObjectCount

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new ActionByText().Method_verifyChildObjectCount(object, arg1, arg2, arg3, arg4));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyFullTableText(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyFullTableText");

		// Method_verifyFullTableText

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyFullTableText(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());
	}

	public boolean VerifyTableColumnCount(ORObject arg0, int arg1, int arg2) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTableColumnCount");

		// Method_verifyTableColumnCount

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTableColumnCount(object, arg1, arg2));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyTableRowCount(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called VerifyTableRowCount");

		// Method_verifyTableRowCount

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_verifyTableRowCount(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean SetBrowserCapability(String arg0, String arg1, String arg2, String arg3) throws Exception {

		System.out.println(">> Appium Keyword Called SetBrowserCapability");

		// Method_SetBrowserCapability

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		FunctionResult functionResult = FunctionCaller.execute(() -> new SetCapabilities().Method_SetBrowserCapability(arg0, arg1, arg2, arg3));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean DeFocusObject() throws Exception {

		System.out.println(">> Appium Keyword Called DeFocusObject");

		// Method_deFocusObject

		ContextInitiator.addFunction(DataType.getMethodName());
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_deFocusObject());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean VerifyMultipleObjectProperty(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10)
			throws Exception {

		System.out.println(">> Appium Keyword Called VerifyMultipleObjectProperty");

		// Method_VerifyMultipleObjectProperty

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_VerifyMultipleObjectProperty(object, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10));
		return DataType.getBoolean(functionResult.getOutput());

	}

	/* Method not found */
	public int Web_GetTableColumnCount(ORObject arg0, int arg1) {

		System.out.println(">> Appium Keyword Called Web_GetTableColumnCount");

		// Method_WebGetTableColCount

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return DataType.getInt("0");

	}

	/* MethodNotFound */
	public int Web_GetTableRowCount(ORObject arg0) {

		System.out.println(">> Appium Keyword Called Web_GetTableRowCount");

		// Method_WebGetTableRowCount

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return DataType.getInt("0");

	}

	/* Argument Mismatch */
	public boolean Launch_iOSApplicationOnDevice(String arg0, String arg1) {

		System.out.println(">> Appium Keyword Called Launch_iOSApplicationOnDevice");

		// Method_Launch_iOSApplicationOnDevice

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	/* MethodNotFound */
	public boolean Web_SetFocusOnCurrentWindow() {

		System.out.println(">> Appium Keyword Called Web_SetFocusOnCurrentWindow");

		// Method_setFocusOnCurrentWindow

		ContextInitiator.addFunction(DataType.getMethodName());
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	/* MethodNotFound */
	public boolean Web_WaitForWindowLoad(String arg0, int arg1, boolean arg2, int arg3) {

		System.out.println(">> Appium Keyword Called Web_WaitForWindowLoad");

		// Method_waitForWindowLoad

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg3, arg3);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	/* MethodNotFound */
	public double GetLoadTime() {

		System.out.println(">> Appium Keyword Called GetLoadTime");

		// Method_getLoadingTime

		ContextInitiator.addFunction(DataType.getMethodName());
		return DataType.getInt("0");

	}

	public boolean IgnoreXMLHttpRequest(String arg0) throws ArgumentDataMissingException {

		System.out.println(">> Appium Keyword Called IgnoreXMLHttpRequest");

		// Method_ignoreXMLHttpRequest

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_ignoreXMLHttpRequest(arg0));
		return DataType.getBoolean(functionResult.getOutput());

	}

	/* WebDriverException */
	public boolean SynchronizeBrowser(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {

		System.out.println(">> Appium Keyword Called SynchronizeBrowser");

		// Method_syncBrowser

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);

		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_syncBrowser());
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean TypeTextInPTag(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeTextInPTag");

		// Method_TypeTextInContentEditable

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_TypeTextInContentEditable(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11,
			String arg12, String arg13, String arg14) {

		System.out.println(">> Appium Keyword Called TypeTextInTableCellByQuery");

		// Method_typeTextInTableCellByQuery

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean ClickOnObjectInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, int arg7, String arg8, int arg9, String arg10,
			String arg11, String arg12, String arg13, String arg14) {

		System.out.println(">> Appium Keyword Called ClickOnObjectInTableCellByQuery");

		// Method_clickInTableCellByQuery

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextAndEnterEditBox(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called TypeTextAndEnterEditBox");

		// Method_typeTextandEnterEditBox

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_typeTextandEnterEditBox(object, arg1));
		return DataType.getBoolean(functionResult.getOutput());

	}

	/* MethodNotFound */
	public boolean Web_ResizeBrowser(int arg0, int arg1) {

		System.out.println(">> Appium Keyword Called Web_ResizeBrowser");

		// Method_setViewPort

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	/* MethodNotFound */
	public boolean RightClickAndSelectByText(ORObject arg0, String arg1) {

		System.out.println(">> Appium Keyword Called RightClickAndSelectByText");

		// Method_rightClickAndSelectByText

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	/* MethodNotFound */
	public boolean IsTextPresentOnScreen(ORObject arg0) {

		System.out.println(">> Appium Keyword Called IsTextPresentOnScreen");

		// Method_IsTextPresentOnScreen_Generic

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public String GetAllWindows() throws ToolNotSetException {

		System.out.println(">> Appium Keyword Called GetAllWindows");

		ContextInitiator.addFunction("GetAllWindows");
		// Method_getAllWindow
		FunctionResult functionResult = FunctionCaller.execute(() -> new AndroidWindowHandling().Method_getAllWindow());
		return functionResult.getOutput();
	}

	public String FetchBrowserURL() {

		System.out.println(">> Appium Keyword Called FetchBrowserURL");

		ContextInitiator.addFunction("FetchBrowserURL");
		// Method_fetchBrowserURL
		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_fetchBrowserURL());
		return functionResult.getOutput();
	}

	/* WebDriverException */
	public String FetchBrowserTitle(String arg0) {

		System.out.println(">> Appium Keyword Called FetchBrowserTitle");

		ContextInitiator.addFunction("FetchBrowserTitle");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_fetchBrowserTitle

		FunctionResult functionResult = FunctionCaller.execute(() -> new Browser().Method_fetchBrowserTitle_1());
		return functionResult.getOutput();

	}

	public String GetSelectedDropDownItem(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetSelectedDropDownItem");

		ContextInitiator.addFunction("GetSelectedDropDownItem");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getSelectedDropDownItem

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_getSelectedDropDownItem(object));
		return functionResult.getOutput();

	}

	public String GetTableCellText(ORObject arg0, int arg1, int arg2, String arg3, String arg4) throws Exception {

		System.out.println(">> Appium Keyword Called GetTableCellText");

		ContextInitiator.addFunction("GetTableCellText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_GetCellText

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_GetCellText(object, arg1, arg2, arg3, arg4));
		return functionResult.getOutput();

	}

	public String GetAllButtons() {

		System.out.println(">> Appium Keyword Called GetAllButtons");

		ContextInitiator.addFunction("GetAllButtons");
		// Method_getAllButtons

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_getAllButtons());
		return functionResult.getOutput();

	}

	public String GetAllFields() throws Exception {

		System.out.println(">> Appium Keyword Called GetAllFields");

		ContextInitiator.addFunction("GetAllFields");
		// Method_getAllFields
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_getAllFields());
		return functionResult.getOutput();
	}

	public String GetAllLinks() throws ToolNotSetException, InterruptedException {

		System.out.println(">> Appium Keyword Called GetAllLinks");

		ContextInitiator.addFunction("GetAllLinks");
		// Method_getAllLinks
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_getAllLinks());
		return functionResult.getOutput();
	}

	public String GetFullTableText(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetFullTableText");

		ContextInitiator.addFunction("GetFullTableText");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getFullTableText
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getFullTableText(object));
		return functionResult.getOutput();
	}

	public String GetSelectedRadioButtonFromGroup(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called GetSelectedRadioButtonFromGroup");

		ContextInitiator.addFunction("GetSelectedRadioButtonFromGroup");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getSelectedRadioButtonFromGroup
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_getSelectedRadioButtonFromGroup(object, arg1));
		return functionResult.getOutput();
	}

	public String GetAllTitles(String arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetAllTitles");

		ContextInitiator.addFunction("GetAllTitles");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_GetAllTitles
		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_GetAllTitles(arg0));
		return functionResult.getOutput();
	}

	public String ReturnConcatenated(String arg0, String arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called ReturnConcatenated");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		// Method_returnConcatenated

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_returnConcatenated(arg0, arg1, arg2));
		return functionResult.getOutput();

	}

	public String GetPropertyValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">> Appium Keyword Called GetPropertyValue");

		ContextInitiator.addFunction("GetPropertyValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getPropertyValue
		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getPropertyValue(object, arg1));
		return functionResult.getOutput();
	}

	public String GetTextFromEditBox(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetTextFromEditBox");

		ContextInitiator.addFunction("GetTextFromEditBox");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getTextFromEditBox
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_getTextFromEditBox(object));
		return functionResult.getOutput();
	}

	public String GetTextfromTextArea(ORObject arg0) throws Exception {

		String methodName = DataType.getMethodName();
		System.out.println(">> Appium Keyword Called GetTextfromTextArea");
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_GetTextfromTextArea(object));
		return functionResult.getOutput();
		// Method_GetTextfromTextArea

	}

	public String CopyFromClipBoard() throws Exception {

		System.out.println(">> Appium Keyword Called CopyFromClipBoard");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_copyFromClipBoard

		FunctionResult functionResult = FunctionCaller.execute(() -> new UnCategorised().Method_copyFromClipBoard());
		return functionResult.getOutput();

	}

	public String GetPopupText(ORObject arg0, String arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetPopupText");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_Getpopuptext

		FunctionResult functionResult = FunctionCaller.execute(() -> new PopUp().Method_Getpopuptext(object, arg1, arg2));
		return functionResult.getOutput();

	}

	public String GetDropDownToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetDropDownToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getDropDownToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_getDropDownToolTip(object));
		return functionResult.getOutput();

	}

	public String GetEditBoxToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetEditBoxToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getEditBoxToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_getEditBoxToolTip(object));
		return functionResult.getOutput();

	}

	public String GetTextAreaToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetTextAreaToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getTextAreaToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_getTextAreaToolTip(object));
		return functionResult.getOutput();

	}

	public String GetButtonToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetButtonToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getButtonToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Button().Method_getButtonToolTip(object));
		return functionResult.getOutput();

	}

	public String GetCheckBoxToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetCheckBoxToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getCheckBoxToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_getCheckBoxToolTip(object));
		return functionResult.getOutput();

	}

	public String GetLinkToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetLinkToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getLinkToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_getLinkToolTip(object));
		return functionResult.getOutput();

	}

	public String GetObjectToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetObjectToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getObjectToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectToolTip(object));
		return functionResult.getOutput();

	}

	public String GetImageToolTip(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetImageToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getImageToolTip

		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_getImageToolTip(object));
		return functionResult.getOutput();

	}

	public String GetTextAreaColumnRowLength(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetTextAreaColumnRowLength");

		// Method_GetTextAreaColRowLength

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_GetTextAreaColRowLength(object));
		return functionResult.getOutput();

	}

	public String GetEditBoxName(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetEditBoxName");

		// Method_GetEditBoxName
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_GetEditBoxName(object));
		return functionResult.getOutput();

	}

	public String GetObjectValue(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetObjectValue");

		// Method_getObjectValue

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectValue(object));
		return functionResult.getOutput();

	}

	public String GetObjectCSSProperty(ORObject arg0, String arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetObjectCSSProperty");

		// Method_getObjectCSSProperty

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectCSSProperty(object, arg1));
		return functionResult.getOutput();

	}

	public String GetCheckboxStatus(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetCheckboxStatus");

		// Method_getCheckboxStatus

		FunctionResult functionResult = FunctionCaller.execute(() -> new Checkbox().Method_getCheckboxStatus(object));
		return functionResult.getOutput();

	}

	public String GetObjectProperty(ORObject arg0, String arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetObjectProperty");

		// Method_getObjectProperty

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectProperty(object, arg1));
		return functionResult.getOutput();

	}

	public String GetTextAreavalue(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetTextAreavalue");

		// Method_getTextAreavalue

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_getTextAreavalue(object));
		return functionResult.getOutput();

	}

	public String GetObjectHeightWidth(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetObjectHeightWidth");

		// Method_getObjectHeightWidth

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectHeightWidth(object));
		return functionResult.getOutput();

	}

	public String GetObjectText(ORObject arg0, String arg1, String arg2) throws Exception {
		String methodName = DataType.getMethodName();
		ContextInitiator.addFunction(methodName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetObjectText");

		// Method_GetObjectText

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_GetObjectText(object, arg1, arg2));
		return functionResult.getOutput();
	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5, String arg6, String arg7, String arg8, String arg9, String arg10,
			String arg11, String arg12) {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetTextFromTableCellByQuery");

		// Method_getTextFromTableCellByQuery

		return "";

	}

	public String GetAllColumnText(ORObject arg0, String arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetAllColumnText");

		// Method_getAllColText

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getAllColText(object, arg1, arg2));
		return functionResult.getOutput();

	}

	public String GetSingleTableColumnText(ORObject arg0, int arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetSingleTableColumnText");

		// Method_getSingleColText

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getSingleColText(object, arg1, arg2));
		return functionResult.getOutput();

	}

	public String GetAllRowText(ORObject arg0, String arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetAllRowText");

		// Method_getAllRowText

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getAllRowText(object, arg1, arg2));
		return functionResult.getOutput();

	}

	public String GetSingleTableRowText(ORObject arg0, int arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetSingleTableRowText");

		// Method_getSingleRowText

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getSingleRowText(object, arg1, arg2));
		return functionResult.getOutput();

	}

	public String GetSelectedDropDownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3) throws Exception {

		System.out.println(">> Appium Keyword Called GetSelectedDropDownItemInTableCell");

		// Method_getSelectedDropDownInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getSelectedDropDownInTableCell(object, arg1, arg2, arg3));
		return functionResult.getOutput();

	}

	public String FetchObjectPropertyInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) throws Exception {

		System.out.println(">> Appium Keyword Called FetchObjectPropertyInTableCell");

		// Method_fetchObjectPropertyInTableCell

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_fetchObjectPropertyInTableCell(object, arg1, arg2, arg3, arg4, arg5));
		return functionResult.getOutput();

	}

	public String CaptureObjectSnapshot(ORObject arg0) {

		System.out.println(">> Appium Keyword Called CaptureObjectSnapshot");

		// Method_captureObjectSnapShot

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return "";

	}

	public String GetEditboxDefaultvalue(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetEditboxDefaultvalue");

		// Method_getEditboxDefaultvalue

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_getEditboxDefaultvalue(object));
		return functionResult.getOutput();

	}

	public String GetEditboxValue(ORObject arg0) {

		System.out.println(">> Appium Keyword Called GetEditboxValue");

		// Method_getEditboxValue

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_getEditboxValue(object));
		return functionResult.getOutput();

	}

	public String GetTextAreaDefaultvalue(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetTextAreaDefaultvalue");

		// Method_getTextAreaDefaultvalue

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_getEditboxValue(object));
		return functionResult.getOutput();

	}

	public String GetTextAreaName(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetTextAreaName");

		// Method_getTextAreaName

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_getTextAreaName(object));
		return functionResult.getOutput();

	}

	public String GetDropdownDefaultItem(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetDropdownDefaultItem");

		// Method_getDropDownDefaultValue

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_getDropDownDefaultValue(object));
		return functionResult.getOutput();

	}

	public String GetTableColumnHeader(ORObject arg0, int arg1) throws Exception {

		System.out.println(">> Appium Keyword Called GetTableColumnHeader");

		// Method_getTableColumnHeader

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getTableColumnHeader(object, arg1));
		return functionResult.getOutput();

	}

	public String GetCompleteTableText(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetCompleteTableText");

		// Method_getCompleteTableText

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getCompleteTableText(object));
		return functionResult.getOutput();

	}

	/* MethodNotFound */
	public String VisualComparisonForPage(String arg0, String arg1, boolean arg2, boolean arg3) {

		System.out.println(">> Appium Keyword Called VisualComparisonForPage");

		// Custom_visualComparionForPage

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		return "";

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, int arg6, int arg7, String arg8, String arg9, String arg10, String arg11,
			String arg12, String arg13) {

		System.out.println(">> Appium Keyword Called GetTextFromTableCellByQuery");

		// Method_getTextFromTableCellByQuery

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return "";

	}

	public int GetRadioButtonCount(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetRadioButtonCount");

		ContextInitiator.addFunction("GetRadioButtonCount");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getRadioButtonCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_getRadioButtonCount(object));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetLinkCount() throws Exception {

		System.out.println(">> Appium Keyword Called GetLinkCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_getLinkCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new Links().Method_getLinkCount());
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetElementIndex(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetElementIndex");

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_GetElementIndex

		FunctionResult functionResult = FunctionCaller.execute(() -> new Deprecate().Method_GetElementIndex(object));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetTableColumnNumber(ORObject arg0, int arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetTableColumnNumber");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_TableGetTextColumn

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_TableGetTextColumn(object, arg1, arg2));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetTextAreaLength(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetTextAreaLength");

		// Method_GetTextAreaLength

		FunctionResult functionResult = FunctionCaller.execute(() -> new TextArea().Method_GetTextAreaLength(object));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetTableRowCount(ORObject arg0) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetTableRowCount");

		// Method_getTableRowCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getTableRowCount(object));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetTableColumnCount(ORObject arg0, int arg1) throws Exception {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">> Appium Keyword Called GetTableColumnCount");

		// Method_getTableColCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_getTableColCount(object, arg1));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetObjectCount(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9)
			throws InterruptedException, ToolNotSetException {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);

		System.out.println(">> Appium Keyword Called GetObjectCount");

		// Method_getObjectCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new WebObjects().Method_getObjectCount(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetEditBoxLength(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetEditBoxLength");

		// Method_getEditboxLength

		ContextInitiator.addFunction(DataType.getMethodName());
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = FunctionCaller.execute(() -> new EditBox().Method_getEditboxLength(object));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetImageCount() throws Exception {

		System.out.println(">> Appium Keyword Called GetImageCount");

		// Method_getImageCount

		ContextInitiator.addFunction(DataType.getMethodName());
		FunctionResult functionResult = FunctionCaller.execute(() -> new Image().Method_getImageCount());
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetDropDownItemCount(ORObject arg0) throws Exception {

		System.out.println(">> Appium Keyword Called GetDropDownItemCount");

		ContextInitiator.addFunction("GetDropDownItemCount");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getDropDownItemCount

		FunctionResult functionResult = FunctionCaller.execute(() -> new DropDown().Method_getDropDownItemCount(object));
		return DataType.getInt(functionResult.getOutput());

	}

	public int GetTableRowNumber(ORObject arg0, int arg1, String arg2) throws Exception {

		System.out.println(">> Appium Keyword Called GetTableRowNumber");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_TableGetTextRow

		FunctionResult functionResult = FunctionCaller.execute(() -> new Table().Method_TableGetTextRow(object, arg1, arg2));
		return DataType.getInt(functionResult.getOutput());

	}
}
