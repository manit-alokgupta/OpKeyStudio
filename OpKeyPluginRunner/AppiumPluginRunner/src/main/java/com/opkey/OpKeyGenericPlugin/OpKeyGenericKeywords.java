package com.opkey.OpKeyGenericPlugin;

import java.awt.AWTException;
import java.io.IOException;

import com.crestech.opkey.plugin.exceptionhandling.ArgumentDataMissingException;
import com.opkey.ObjectFromatter.ObjectConverter;
import com.opkey.context.ContextInitiator;
import com.opkeystudio.runtime.ORObject;
import com.plugin.appium.AppiumObject;
import com.plugin.appium.exceptionhandlers.AdbNotFoundException;
import com.plugin.appium.exceptionhandlers.KeywordMethodOrArgumentValidationFailException;
import com.plugin.appium.exceptionhandlers.ObjectNotFoundException;
import com.plugin.appium.exceptionhandlers.TimeOut_ObjectNotFoundException;
import com.plugin.appium.exceptionhandlers.ToolNotSetException;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidCheckBox;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidObject;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidPicker;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidRadio;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AndroidWindowHandling;
import com.plugin.appium.keywords.AppiumSpecificKeyword.AppiumSpecificUnCategorised;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Gestures;
import com.plugin.appium.keywords.AppiumSpecificKeyword.MenuHandling;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Orientation;
import com.plugin.appium.keywords.AppiumSpecificKeyword.Toggle;
import com.plugin.appium.keywords.GenericKeyword.Browser;
import com.plugin.appium.keywords.GenericKeyword.Button;
import com.plugin.appium.keywords.GenericKeyword.Checkbox;
import com.plugin.appium.keywords.GenericKeyword.Deprecate;
import com.plugin.appium.keywords.GenericKeyword.DropDown;
import com.plugin.appium.keywords.GenericKeyword.EditBox;
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

public class OpKeyGenericKeywords {
	public OpKeyGenericKeywords() {
		new ContextInitiator().initContext();
	}

	public String GetCurrentOrientation() throws ToolNotSetException {

		System.out.println(">>Keyword Called GetCurrentOrientation");

		ContextInitiator.addFunction("GetCurrentOrientation");
		// Method_getCurrentOrientation
		return new Orientation().Method_getCurrentOrientation().getOutput();

	}

	/** Method_isCurrentOrientationLandScape */
	public boolean IsCurrentOrientationLandScape() throws ToolNotSetException {
		System.out.println(">>Keyword Called IsCurrentOrientationLandScape");
		ContextInitiator.addFunction("IsCurrentOrientationLandScape");

		String boolString = new Orientation().Method_isCurrentOrientationLandScape().getOutput();
		return DataType.getBoolean(boolString);

	}

	/** Method_isCurrentOrientationPortrait */
	public boolean IsCurrentOrientationPortrait() throws ToolNotSetException {
		System.out.println(">>Keyword Called IsCurrentOrientationPortrait");
		ContextInitiator.addFunction("IsCurrentOrientationPortrait");

		String boolString = new Orientation().Method_isCurrentOrientationPortrait().getOutput();
		return DataType.getBoolean(boolString);

	}

	/**
	 * Method_currentOrientationChangeToPortrait
	 * 
	 * @throws ToolNotSetException
	 */
	public boolean CurrentOrientationChangeToPortrait() throws ToolNotSetException {
		System.out.println(">>Keyword Called CurrentOrientationChangeToPortrait");
		ContextInitiator.addFunction("CurrentOrientationChangeToPortrait");

		String boolString = new Orientation().Method_currentOrientationChangeToPortrait().getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean CurrentOrientationChangeToLandScape() throws ToolNotSetException {
		System.out.println(">>Keyword Called CurrentOrientationChangeToLandScape");
		ContextInitiator.addFunction("CurrentOrientationChangeToLandScape");

		String boolString = new Orientation().Method_currentOrientationChangeToLandScape().getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean Flick(int arg0, int arg1) throws ToolNotSetException, KeywordMethodOrArgumentValidationFailException {

		System.out.println(">>Keyword Called Flick");

		ContextInitiator.addFunction("Flick");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0), String.valueOf(arg1));
		// Method_flick
		String boolString = new Gestures().Method_flick(arg0, arg1).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean Tap(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, KeywordMethodOrArgumentValidationFailException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called Tap");

		ContextInitiator.addFunction("Tap");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_Tap
		String boolString = new Gestures().Method_Tap(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean Launch_iOSApplicationOnSimulator(String arg0, String arg1) {

		System.out.println(">>Keyword Called Launch _iOSApplicationOnSimulator");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1); // Method_Launch_iOSApplicationOnSimulator

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean PressMenuButton() throws ToolNotSetException {
		System.out.println(">>Keyword Called PressMenuButton");
		ContextInitiator.addFunction("PressMenuButton");

		// Method_PressMenu
		String boolString = new MenuHandling().Method_PressMenu().getOutput();
		return DataType.getBoolean(boolString);
	}

	public String GetCurrentWindow() {

		System.out.println(">>Keyword Called GetCurrentWindow");

		ContextInitiator.addFunction("GetCurrentWindow");
		// Method_getCurrentWindow

		return "";

	}

	public boolean SwitchWindow(String arg0) throws ToolNotSetException, InterruptedException {
		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		System.out.println(">>Keyword Called SwitchWindow");

		// Method_switchWindow
		String boolString = new AndroidWindowHandling().Method_switchWindow(arg0).getOutput();
		return DataType.getBoolean(boolString);

	}

	public String GetAllWindows() throws ToolNotSetException {

		System.out.println(">>Keyword Called GetAllWindows");

		ContextInitiator.addFunction("GetAllWindows");
		// Method_getAllWindow
		return new AndroidWindowHandling().Method_getAllWindow().getOutput();
	}

	public boolean ScrollDown() throws ToolNotSetException, InterruptedException, KeywordMethodOrArgumentValidationFailException {

		System.out.println(">>Keyword Called ScrollDown");

		ContextInitiator.addFunction("ScrollDown");
		// Method_ScrollDown
		String boolString = new Gestures().Method_ScrollDown().getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean ScrollUp() throws ToolNotSetException, InterruptedException, KeywordMethodOrArgumentValidationFailException {

		System.out.println(">>Keyword Called ScrollUp");

		ContextInitiator.addFunction("ScrollUp");
		// Method_ScrollUp
		String boolString = new Gestures().Method_ScrollUp().getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean Toggle(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called Toggle");

		ContextInitiator.addFunction("Toggle");
		// Method_Toggle
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new Toggle().Method_Toggle(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean Switch(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called Switch");

		ContextInitiator.addFunction("Switch"); // Method_Switch
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new com.plugin.appium.keywords.AppiumSpecificKeyword.Switch().Method_Switch(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean LongPress(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, KeywordMethodOrArgumentValidationFailException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called LongPress");

		ContextInitiator.addFunction("LongPress"); // Method_LongPress
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new Gestures().Method_LongPress(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean DoubleTouch(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called DoubleTouch");

		ContextInitiator.addFunction("DoubleTouch"); // Method_DoubleTouch
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new Gestures().Method_DoubleTouch(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean Touch(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, KeywordMethodOrArgumentValidationFailException, TimeOut_ObjectNotFoundException {
		ContextInitiator.addFunction("Touch");
		System.out.println(">>Keyword Called Touch");

		ContextInitiator.addFunction("Touch"); // Method_Touch
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new Gestures().Method_Touch(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean GetObjectImage(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, IOException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called GetObjectImage");

		ContextInitiator.addFunction("GetObjectImage"); // Method_getObjectImage
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new AndroidObject().Method_getObjectImage(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectRadioButtton(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called SelectRadioButtton");

		ContextInitiator.addFunction("SelectRadioButtton");
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		// Method_SelectRadioButton
		String boolString = new AndroidRadio().Method_SelectRadioButton(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean GetRadioButtonStatus(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetRadioButtonStatus");

		ContextInitiator.addFunction("GetRadioButtonStatus");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getRadioButtonStatus
		String boolString = new AndroidRadio().Method_getRadioButtonStatus(object).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean VerifyRadioButtonStatus(ORObject arg0, boolean arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyRadioButtonStatus");

		ContextInitiator.addFunction("VerifyRadioButtonStatus");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyRadioButtonStatus
		String boolString = new AndroidRadio().Method_VerifyRadioButtonStatus(object, arg1).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean GetObjectCoordinates(ORObject arg0) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called GetObjectCoordinates");

		ContextInitiator.addFunction("GetObjectCoordinates");
		// Method_GetObjectCordinates
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new AndroidObject().Method_GetObjectCordinates(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean LaunchChromeOnMobile(String arg0, String arg1) {

		System.out.println(">>Keyword Called LaunchChromeOnMobile");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_Launch_ChromeBrowser

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean SetPickerValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called SetPickerValue");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_SetPickerValue
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new AndroidPicker().Method_SetPickerValue(object, arg1).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean LaunchSafariOn_iOS(String arg0, String arg1) {

		System.out.println(">>Keyword Called LaunchSafariOn_iOS");

		ContextInitiator.addFunction("LaunchSafariOn_iOS");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_LaunchSafarOnIos

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean SelectCheckBox(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called SelectCheckBox");

		ContextInitiator.addFunction("SelectCheckBox");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_appiumSelectCheckBox
		String boolString = new AndroidCheckBox().Method_appiumSelectCheckBox(object).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean TapOnCoordinates(ORObject arg0, String arg1, String arg2)
			throws ToolNotSetException, IOException, InterruptedException, AdbNotFoundException, ObjectNotFoundException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called TapOnCoordinates");

		ContextInitiator.addFunction("TapOnCoordinates");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2); // Method_tapOnCoordinate
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new AppiumSpecificUnCategorised().Method_tapOnCoordinate(object, arg1, arg2).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean LoadAppium() {

		System.out.println(">>Keyword Called LoadAppium");

		ContextInitiator.addFunction("LoadAppium");
		// Method_LoadMe
		String boolString = new UnCategorised().Method_LoadMe().getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean TypeTextOnEditBox(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called TypeTextOnEditBox");

		ContextInitiator.addFunction("TypeTextOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_typeTextOnEditBox
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new EditBox().Method_typeTextOnEditBox(object, arg1).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean TypeKeysOnEditBox(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called TypeKeysOnEditBox");

		ContextInitiator.addFunction("TypeKeysOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_typeKeysOnEditBox
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new EditBox().Method_typeKeysOnEditBox(object, arg1).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean VerifyEditBoxText(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyEditBoxText");

		ContextInitiator.addFunction("VerifyEditBoxText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyeditboxtext
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new EditBox().Method_verifyeditboxtext(object, arg1).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean OpenBrowser(String arg0, String arg1) throws ToolNotSetException, IOException, InterruptedException {

		System.out.println(">>Keyword Called OpenBrowser");

		ContextInitiator.addFunction("OpenBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_WebBrowserOpen
		String boolString = new Browser().Method_WebBrowserOpen(arg0, arg1).getOutput();
		return DataType.getBoolean(boolString);

	}

	public boolean SelectDropDownItem(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called SelectDropDownItem");

		ContextInitiator.addFunction("SelectDropDownItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new DropDown().Method_selectDropDownItem(object, arg1).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean SelectCheckBox(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called SelectCheckBox");

		ContextInitiator.addFunction("SelectCheckBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectCheckBox
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String boolString = new Checkbox().Method_selectCheckBox(object, arg1).getOutput();
		return DataType.getBoolean(boolString);
	}

	public boolean SelectRadioButton(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called SelectRadioButton");

		ContextInitiator.addFunction("SelectRadioButton");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_SelectRadio
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Radio().Method_SelectRadio(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean Click(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called Click");

		ContextInitiator.addFunction("Click");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectClick
		String bool = new com.plugin.appium.keywords.GenericKeyword.WebObjects().Method_ObjectClick(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DoubleClick(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called DoubleClick");

		ContextInitiator.addFunction("DoubleClick"); // Method_dblClick
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new WebObjects().Method_dblClick(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean RefreshBrowser() {

		System.out.println(">>Keyword Called RefreshBrowser");

		ContextInitiator.addFunction("RefreshBrowser");
		// Method_RefreshBrowser
//		String bool = new com.plugin.appium.keywords.GenericKeyword.Browser().Method_RefreshBrowser().getOutput();
		return DataType.getBoolean("");

	}

	public boolean MaximizeBrowser() throws Exception {

		System.out.println(">>Keyword Called MaximizeBrowser");

		ContextInitiator.addFunction("MaximizeBrowser");
		// Method_MaximizeBrowser
		String bool = new Browser().Method_MaximizeBrowser().getOutput();
		return DataType.getBoolean(bool);
	}

	public String FetchBrowserURL() {

		System.out.println(">>Keyword Called FetchBrowserURL");

		ContextInitiator.addFunction("FetchBrowserURL");
		// Method_fetchBrowserURL
//		return new Browser().Method_fetchBrowserURL().getOutput();
		return "";
	}

	public String FetchBrowserTitle(String arg0) {

		System.out.println(">>Keyword Called FetchBrowserTitle");

		ContextInitiator.addFunction("FetchBrowserTitle");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_fetchBrowserTitle

		return "";

	}

	public boolean GoBackAndWait(int arg0) {

		System.out.println(">>Keyword Called GoBackAndWait");

		ContextInitiator.addFunction("GoBackAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0));
		// Method_goBackAndWait
//		String bool = new Deprecate().Method_goBackAndWait(arg0).getOutput();
		return DataType.getBoolean("");
	}

	public boolean RefreshAndWait(int arg0) {

		System.out.println(">>Keyword Called RefreshAndWait");

		ContextInitiator.addFunction("RefreshAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0));
		// Method_refreshAndWait

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean ClearEditField(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called ClearEditField");

		ContextInitiator.addFunction("ClearEditField");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clearEditField

		String bool = new EditBox().Method_clearEditField(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called TypeTextAndWait");

		ContextInitiator.addFunction("TypeTextAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2)); // Method_typeTextAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_typeTextAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean TypeKeysAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called TypeKeysAndWait");

		ContextInitiator.addFunction("TypeKeysAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeKeysAndWait
		String bool = new Deprecate().Method_typeKeysAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditable(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyEditable");

		ContextInitiator.addFunction("VerifyEditable");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditable

		String bool = new Deprecate().Method_verifyEditable(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectMultipleDropDownItem(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called SelectMultipleDropDownItem");

		ContextInitiator.addFunction("SelectMultipleDropDownItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_selectMultipleDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new ListControl().Method_selectMultipleDropDownItem(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeSelectDropDownItem(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called DeSelectDropDownItem");

		ContextInitiator.addFunction("DeSelectDropDownItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_deselectDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new DropDown().Method_deselectDropDownItem(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeSelectMultipleDropDownItem(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called DeSelectMultipleDropDownItem");

		ContextInitiator.addFunction("DeSelectMultipleDropDownItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_deselectMultipleDropDownItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new ListControl().Method_deselectMultipleDropDownItem(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeSelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called DeSelectDropDownItemAndWait");

		ContextInitiator.addFunction("DeSelectDropDownItemAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2));
		// Method_deselectDropDownItemAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_deselectDropDownItemAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeSelectAllDropDownItemsAndWait(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called DeSelectAllDropDownItemsAndWait");

		ContextInitiator.addFunction("DeSelectAllDropDownItemsAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_deselectAllDropDownItemsAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_deselectAllDropDownItemsAndWait(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public int GetDropDownItemCount(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetDropDownItemCount");

		ContextInitiator.addFunction("GetDropDownItemCount");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getDropDownItemCount

		return DataType.getInt(new DropDown().Method_getDropDownItemCount(object).getOutput());

	}

	public String GetSelectedDropDownItem(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetSelectedDropDownItem");

		ContextInitiator.addFunction("GetSelectedDropDownItem");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getSelectedDropDownItem

		return new DropDown().Method_getSelectedDropDownItem(object).getOutput();

	}

	public boolean VerifyDropDownItemExists(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyDropDownItemExists");

		ContextInitiator.addFunction("VerifyDropDownItemExists");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyDropDownItemExists
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new DropDown().Method_verifyDropDownItemExists(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeSelectCheckBox(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called DeSelectCheckBox");

		ContextInitiator.addFunction("DeSelectCheckBox");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_deSelectCheckBox

		String bool = new Checkbox().Method_deSelectCheckBox(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean verifyCheckBoxStatus(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called verifyCheckBoxStatus");

		ContextInitiator.addFunction("verifyCheckBoxStatus");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxStatus

		String bool = new Checkbox().Method_verifyCheckBoxStatus(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectCheckBoxAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called SelectCheckBoxAndWait");

		ContextInitiator.addFunction("SelectCheckBoxAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2));
		// Method_selectCheckBoxAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_selectCheckBoxAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeSelectCheckBoxAndWait(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called DeSelectCheckBoxAndWait");

		ContextInitiator.addFunction("DeSelectCheckBoxAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_deSelectCheckBoxAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_deSelectCheckBoxAndWait(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectRadioButtonAndWait(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called SelectRadioButtonAndWait");

		ContextInitiator.addFunction("SelectRadioButtonAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_selectRadioButtonAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_selectRadioButtonAndWait(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) throws Exception {

		System.out.println(">>Keyword Called ClickTableCell");

		ContextInitiator.addFunction("ClickTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), arg3, String.valueOf(arg4));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickTableCell

		String bool = new Table().Method_clickTableCell(object, arg1, arg2, arg3, arg4).getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetTableCellText(ORObject arg0, int arg1, int arg2, String arg3, String arg4) throws Exception {

		System.out.println(">>Keyword Called GetTableCellText");

		ContextInitiator.addFunction("GetTableCellText");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), arg3, arg4);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_GetCellText

		return new Table().Method_GetCellText(object, arg1, arg2, arg3, arg4).getOutput();

	}

	public boolean SetFocus(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called SetFocus");

		ContextInitiator.addFunction("SetFocus");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_SetFocus

		String bool = new WebObjects().Method_SetFocus(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectExists(ORObject arg0) throws ToolNotSetException {

		System.out.println(">>Keyword Called VerifyObjectExists");

		ContextInitiator.addFunction("VerifyObjectExists");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectExists

		String bool = new WebObjects().Method_ObjectExists(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectEnabled(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyObjectEnabled");

		ContextInitiator.addFunction("VerifyObjectEnabled");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectisEnabled

		String bool = new WebObjects().Method_ObjectisEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectVisible(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyObjectVisible");

		ContextInitiator.addFunction("VerifyObjectVisible");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyObjectVisible

		String bool = new WebObjects().Method_verifyObjectVisible(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectText(ORObject arg0, String arg1, String arg2, String arg3) throws Exception {

		System.out.println(">>Keyword Called VerifyObjectText");

		ContextInitiator.addFunction("VerifyObjectText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectTextVerification

		String bool = new WebObjects().Method_ObjectTextVerification(object, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectPropertyValue(ORObject arg0, String arg1, String arg2) throws Exception {

		System.out.println(">>Keyword Called VerifyObjectPropertyValue");

		ContextInitiator.addFunction("VerifyObjectPropertyValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyPropertyValue

		String bool = new WebObjects().Method_VerifyPropertyValue(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean WaitForObject(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called WaitForObject");

		ContextInitiator.addFunction("WaitForObject");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_waitforObject
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new WebObjects().Method_waitforObject(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean Wait(int arg0) throws Exception {

		System.out.println(">>Keyword Called Wait");

		ContextInitiator.addFunction("Wait");
		// Method_wait

		String bool = new Deprecate().Method_wait(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public int GetChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3) throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called GetChildObjectCount");

		ContextInitiator.addFunction("GetChildObjectCount");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3); // Method_getChildObjectCount
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String num = new Deprecate().Method_getChildObjectCount(object, arg1, arg2, arg3).getOutput();
		return DataType.getInt(num);

	}

	public boolean DoubleClickAndWait(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called DoubleClickAndWait");

		ContextInitiator.addFunction("DoubleClickAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickAndWait

		String bool = new Deprecate().Method_doubleClickAndWait(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DoubleClickAt(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called DoubleClickAt");

		ContextInitiator.addFunction("DoubleClickAt");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickAt
		String bool = new Deprecate().Method_doubleClickAt(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DragAndDrop(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called DragAndDrop");

		ContextInitiator.addFunction("DragAndDrop");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_dragAndDrop

		String bool = new Deprecate().Method_dragAndDrop(object, arg1).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean DragAndDropAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called DragAndDropAndWait");

		ContextInitiator.addFunction("DragAndDropAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2)); // Method_dragAndDropAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_dragAndDropAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetAllButtons() {

		System.out.println(">>Keyword Called GetAllButtons");

		ContextInitiator.addFunction("GetAllButtons");
		// Method_getAllButtons

		return "";

	}

	public String GetAllFields() throws Exception {

		System.out.println(">>Keyword Called GetAllFields");

		ContextInitiator.addFunction("GetAllFields");
		// Method_getAllFields
		return new Deprecate().Method_getAllFields().getOutput();
	}

	public String GetAllLinks() throws ToolNotSetException, InterruptedException {

		System.out.println(">>Keyword Called GetAllLinks");

		ContextInitiator.addFunction("GetAllLinks");
		// Method_getAllLinks
		return new Deprecate().Method_getAllLinks().getOutput();
	}

	public boolean CloseBrowser(String arg0) {

		System.out.println(">>Keyword Called CloseBrowser");

		ContextInitiator.addFunction("CloseBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_CloseBrowser

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean GoForward() {

		System.out.println(">>Keyword Called GoForward");

		ContextInitiator.addFunction("GoForward");
		// Method_goForward

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean GoForwardAndWait(int arg0) throws Exception {

		System.out.println(">>Keyword Called GoForwardAndWait");

		ContextInitiator.addFunction("GoForwardAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0));
		// Method_goForwardAndWait
		String bool = new Deprecate().Method_goForwardAndWait(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyAllDropDownItems(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyAllDropDownItems");

		ContextInitiator.addFunction("VerifyAllDropDownItems");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyAllDropDownItems
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new DropDown().Method_verifyAllDropDownItems(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called SelectDropDownItemAndWait");

		ContextInitiator.addFunction("SelectDropDownItemAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2)); // Method_selectDropDownItemAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_selectDropDownItemAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);
	}

	public String GetFullTableText(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetFullTableText");

		ContextInitiator.addFunction("GetFullTableText");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getFullTableText
		return new Table().Method_getFullTableText(object).getOutput();
	}

	public boolean VerifyTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, String arg4, String arg5) throws Exception {

		System.out.println(">>Keyword Called VerifyTextInTableCell");

		ContextInitiator.addFunction("VerifyTextInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), arg3, arg4, arg5);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextInTable
		String bool = new Table().Method_verifyTextInTable(object, arg1, arg2, arg3, arg4, arg5).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClickTableCellAndWait(ORObject arg0, int arg1, int arg2, String arg3, int arg4, int arg5) throws Exception {

		System.out.println(">>Keyword Called ClickTableCellAndWait");

		ContextInitiator.addFunction("ClickTableCellAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), arg3, String.valueOf(arg4), String.valueOf(arg5));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickTableCellAndWait
		String bool = new Table().Method_clickTableCellAndWait(object, arg1, arg2, arg3, arg4, arg5).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean WaitForObjectProperty(ORObject arg0, String arg1, String arg2, int arg3) throws Exception {

		System.out.println(">>Keyword Called WaitForObjectProperty");

		ContextInitiator.addFunction("WaitForObjectProperty");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, String.valueOf(arg3));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_waitForObjectProperty
		String bool = new Deprecate().Method_waitForObjectProperty(object, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean HighlightObject(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called HighlightObject");

		ContextInitiator.addFunction("HighlightObject");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_highlightObject
		String bool = new WebObjects().Method_highlightObject(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean RunScriptAndWait(ORObject arg0, String arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called RunScriptAndWait");

		ContextInitiator.addFunction("RunScriptAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_runScriptAndWait
		String bool = new Deprecate().Method_runScriptAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);
	}

	public String GetSelectedRadioButtonFromGroup(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called GetSelectedRadioButtonFromGroup");

		ContextInitiator.addFunction("GetSelectedRadioButtonFromGroup");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getSelectedRadioButtonFromGroup
		return new Deprecate().Method_getSelectedRadioButtonFromGroup(object, arg1).getOutput();
	}

	public boolean SyncBrowser() {

		System.out.println(">>Keyword Called SyncBrowser");

		ContextInitiator.addFunction("SyncBrowser");
		// Method_syncBrowser

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean CloseAllBrowsers() throws Exception {

		System.out.println(">>Keyword Called CloseAllBrowsers");

		ContextInitiator.addFunction("CloseAllBrowsers");
		// Method_CloseAllBrowsers
		String bool = new Browser().Method_CloseAllBrowsers().getOutput();
		return DataType.getBoolean(bool);
	}

	public String GetAllTitles(String arg0) throws Exception {

		System.out.println(">>Keyword Called GetAllTitles");

		ContextInitiator.addFunction("GetAllTitles");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_GetAllTitles
		return new Deprecate().Method_GetAllTitles(arg0).getOutput();
	}

	public boolean SelectWindow(String arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called SelectWindow");

		ContextInitiator.addFunction("SelectWindow");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, String.valueOf(arg1));
		// Method_selectWindow
		String bool = new Window().Method_selectWindow(arg0, arg1).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean CloseSelectedWindow(String arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called CloseSelectedWindow");

		ContextInitiator.addFunction("CloseSelectedWindow");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, String.valueOf(arg1));
		// Method_closeSelectedWindow
		String bool = new Window().Method_closeSelectedWindow(arg0).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean SetFocusOnWindow(int arg0) throws Exception {

		System.out.println(">>Keyword Called SetFocusOnWindow");

		ContextInitiator.addFunction("SetFocusOnWindow");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0));
		// Method_setFocousOnWindow
		String bool = new Window().Method_setFocousOnWindow(arg0).getOutput();
		return DataType.getBoolean(bool);
	}

	public String GetPropertyValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called GetPropertyValue");

		ContextInitiator.addFunction("GetPropertyValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getPropertyValue
		return new WebObjects().Method_getPropertyValue(object, arg1).getOutput();
	}

	public boolean VerifyBrowserExist(String arg0) {

		System.out.println(">>Keyword Called VerifyBrowserExist");

		ContextInitiator.addFunction("VerifyBrowserExist");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyBrowserExist

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public String GetTextFromEditBox(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetTextFromEditBox");

		ContextInitiator.addFunction("GetTextFromEditBox");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getTextFromEditBox
		return new EditBox().Method_getTextFromEditBox(object).getOutput();
	}

	public boolean VerifyEditBoxDefaultValue(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyEditBoxDefaultValue");

		ContextInitiator.addFunction("VerifyEditBoxDefaultValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyEditBoxDefaultValue
		String bool = new Deprecate().Method_verifyEditBoxDefaultValue(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxNotExist(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyEditBoxNotExist");

		ContextInitiator.addFunction("VerifyEditBoxNotExist");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_verifyEditBoxnotExist
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_verifyEditBoxnotExist(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxToolTip(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyEditBoxToolTip");

		ContextInitiator.addFunction("VerifyEditBoxToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyEditBoxToolTip
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new EditBox().Method_verifyEditBoxToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownDefaultItem(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyDropDownDefaultItem");

		ContextInitiator.addFunction("VerifyDropDownDefaultItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyDropDownDefaultItem
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new DropDown().Method_verifyDropDownDefaultItem(object, arg1).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean ClickButton(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called ClickButton");

		ContextInitiator.addFunction("ClickButton");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickButton
		String bool = new Button().Method_clickButton(object).getOutput();
		return DataType.getBoolean(bool);
	}

	public boolean ClickButtonAndWait(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called ClickButtonAndWait");

		ContextInitiator.addFunction("ClickButtonAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_clickButtonAndWait
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Deprecate().Method_clickButtonAndWait(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyRadioButtonSelected(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyRadioButtonSelected");

		ContextInitiator.addFunction("VerifyRadioButtonSelected");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_VerifyRadioButtonSelected
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Radio().Method_VerifyRadioButtonSelected(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	// sdasd
	public boolean VerifyRadioButtonNotSelected(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyRadioButtonNotSelected");

		ContextInitiator.addFunction("VerifyRadioButtonNotSelected");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_VerifyRadioButtonNotSelected

		String bool = new Deprecate().Method_VerifyRadioButtonNotSelected(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyCheckBoxToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyCheckBoxToolTip");

		ContextInitiator.addFunction("VerifyCheckBoxToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyCheckBoxToolTip

		String bool = new Checkbox().Method_verifyCheckBoxToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaText(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaText");

		ContextInitiator.addFunction("VerifyTextAreaText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyTextAreaText

		String bool = new TextArea().Method_verifyTextAreaText(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaEnabled");

		ContextInitiator.addFunction("VerifyTextAreaEnabled");
		// Method_verifyTextAreaEnabled

		String bool = new TextArea().Method_verifyTextAreaEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public int GetRadioButtonCount(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetRadioButtonCount");

		ContextInitiator.addFunction("GetRadioButtonCount");
		// Method_getRadioButtonCount

		return DataType.getInt(new Deprecate().Method_getRadioButtonCount(object).getOutput());

	}

	public boolean TypeTextAndEnterTextArea(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called TypeTextAndEnterTextArea");

		ContextInitiator.addFunction("TypeTextAndEnterTextArea");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_typeTextandEnterTextArea

		String bool = new TextArea().Method_typeTextandEnterTextArea(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaDefaultValue(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaDefaultValue");

		ContextInitiator.addFunction("VerifyTextAreaDefaultValue");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyTextAreaDefaultValue

		String bool = new Deprecate().Method_verifyTextAreaDefaultValue(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaDisabled");

		ContextInitiator.addFunction("VerifyTextAreaDisabled");
		// Method_verifyTextAreaDisabled

		String bool = new Deprecate().Method_verifyTextAreaDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaEditable(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaEditable");

		ContextInitiator.addFunction("VerifyTextAreaEditable");
		// Method_verifyTextAreaEditable

		String bool = new TextArea().Method_verifyTextAreaEditable(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaNotEditable(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaNotEditable");

		ContextInitiator.addFunction("VerifyTextAreaNotEditable");
		// Method_verifyTextAreaNotEditable

		String bool = new Deprecate().Method_verifyTextAreaNotEditable(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaToolTip");

		ContextInitiator.addFunction("VerifyTextAreaToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyTextAreaToolTip

		String bool = new TextArea().Method_verifyTextAreaToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableColumnNumber(ORObject arg0, int arg1, String arg2, int arg3) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTableColumnNumber");

		ContextInitiator.addFunction("VerifyTableColumnNumber");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), arg2, String.valueOf(arg3));
		// Method_verifyTableColumnNumber

		String bool = new Table().Method_verifyTableColumnNumber(object, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableColumnText(ORObject arg0, int arg1, String arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTableColumnText");

		ContextInitiator.addFunction("VerifyTableColumnText");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), arg2);
		// Method_verifyTableColumnText

		String bool = new Table().Method_verifyTableColumnText(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableRowText(ORObject arg0, int arg1, String arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTableRowText");

		ContextInitiator.addFunction("VerifyTableRowText");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), arg2);
		// Method_verifyTableRowText

		String bool = new Table().Method_verifyTableRowText(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableColumnHeader(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTableColumnHeader");

		ContextInitiator.addFunction("VerifyTableColumnHeader");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyTableColumnHeader

		String bool = new Table().Method_verifyTableColumnHeader(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyObjectToolTip");

		ContextInitiator.addFunction("VerifyObjectToolTip");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyObjectToolTip

		String bool = new WebObjects().Method_verifyObjectToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectMultipleDropDownItemAndWait(ORObject arg0, String arg1, int arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called SelectMultipleDropDownItemAndWait");

		ContextInitiator.addFunction("SelectMultipleDropDownItemAndWait");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, String.valueOf(arg2));
		// Method_selectMultipleDropDownItemAndWait

		String bool = new Deprecate().Method_selectMultipleDropDownItemAndWait(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DoubleClickButton(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called DoubleClickButton");

		ContextInitiator.addFunction("DoubleClickButton");
		// Method_doubleClickButton

		String bool = new Button().Method_doubleClickButton(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean FocusButton(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called FocusButton");

		ContextInitiator.addFunction("FocusButton");
		// Method_focusButton

		String bool = new Button().Method_focusButton(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeFocusButton() throws Exception {

		System.out.println(">>Keyword Called DeFocusButton");

		ContextInitiator.addFunction("DeFocusButton");
		// Method_deFocusButton

		String bool = new Button().Method_deFocusButton().getOutput();
		return DataType.getBoolean(bool);

	}

//
	public boolean VerifyButtonDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyButtonDisabled");

		ContextInitiator.addFunction("VerifyButtonDisabled");
		// Method_verifyButtonDisabled

		String bool = new Deprecate().Method_verifyButtonDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyButtonExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyButtonExist");

		ContextInitiator.addFunction("VerifyButtonExist");
		// Method_verifyButtonExist

		String bool = new Button().Method_verifyButtonExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectRadioButtonOnIndexBasis(ORObject arg0, int arg1) {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called SelectRadioButtonOnIndexBasis");

		ContextInitiator.addFunction("SelectRadioButtonOnIndexBasis");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_selectRadioButtonOnIndexBasis

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean ClickLinkInTableCell(ORObject arg0, int arg1, int arg2, int arg3) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClickLinkInTableCell");

		ContextInitiator.addFunction("ClickLinkInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), String.valueOf(arg3));
		// Method_clickLinkInTableCell

		String bool = new Table().Method_clickLinkInTableCell(object, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextInTextArea(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called TypeTextInTextArea");

		ContextInitiator.addFunction("TypeTextInTextArea");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_typeTextInTextArea

		String bool = new TextArea().Method_typeTextInTextArea(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean TypeKeysInTextArea(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called TypeKeysInTextArea");

		ContextInitiator.addFunction("TypeKeysInTextArea");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_typeKeysInTextArea

		String bool = new TextArea().Method_typeKeysInTextArea(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClearTextArea(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClearTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_clearTextArea

		String bool = new TextArea().Method_clearTextArea(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SetfocusTextArea(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called SetfocusTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_SetfocusTextArea

		String bool = new TextArea().Method_SetfocusTextArea(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeFocusTextArea() throws Exception {

		System.out.println(">>Keyword Called DeFocusTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusTextArea

		String bool = new TextArea().Method_deFocusTextArea().getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetTextfromTextArea(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetTextfromTextArea");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_GetTextfromTextArea

		return new TextArea().Method_GetTextfromTextArea(object).getOutput();

	}

	public boolean VerifyTextAreaValue(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaValue");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyTextAreaValue

		String bool = new Deprecate().Method_verifyTextAreaValue(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyTextAreaExist

		String bool = new TextArea().Method_verifyTextAreaExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaNotExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaNotExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyTextAreanotExist

		String bool = new Deprecate().Method_verifyTextAreanotExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableRowNumber(ORObject arg0, int arg1, String arg2, int arg3) {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTableRowNumber");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), arg2, String.valueOf(arg3)); // Method_verifyTableRowNumber

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaName(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaName");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyTextAreaName

		String bool = new Deprecate().Method_verifyTextAreaName(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextAreaLength(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextAreaLength");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_verifyTextAreaLength

		String bool = new Deprecate().Method_verifyTextAreaLength(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public int GetLinkCount() throws Exception {

		System.out.println(">>Keyword Called GetLinkCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_getLinkCount

		return DataType.getInt(new Links().Method_getLinkCount().getOutput());

	}

	public boolean VerifyLinkCount(int arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyLinkCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyLinkCount

		String bool = new Links().Method_verifyLinkCount(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

//
	public boolean VerifyObjectDoesNotExists(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyObjectDoesNotExists");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyObjectdoesnotExists

		String bool = new Deprecate().Method_verifyObjectdoesnotExists(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyObjectDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyObjectDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyobjectDisabled

		String bool = new Deprecate().Method_verifyobjectDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean PressTAB() throws ToolNotSetException, AWTException {

		System.out.println(">>Keyword Called PressTAB");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_PressTAB

		String bool = new UnCategorised().Method_PressTAB().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean CaptureSnapshot(String arg0) {

		System.out.println(">>Keyword Called CaptureSnapshot");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_CaptureSnapshot

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean VerifyPopUpText(ORObject arg0, String arg1, String arg2, String arg3) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyPopUpText");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		// Method_VerifyPopUpText

		String bool = new PopUp().Method_VerifyPopUpText(object, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClickLink(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClickLink");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_clickLink

		String bool = new Links().Method_clickLink(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyLinkExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyLinkExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyLinkExist

		String bool = new Links().Method_verifyLinkExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyAllLinkExist(String arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyAllLinkExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyAllLinkExist

		String bool = new Deprecate().Method_verifyAllLinkExist(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyButtonEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyButtonEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyButtonEnabled

		String bool = new Button().Method_verifyButtonEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyButtonToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyButtonToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyButtonToolTip

		String bool = new Button().Method_verifyButtonToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClickButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClickButtonInTableCell");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), String.valueOf(arg3));
		// Method_clickButtonInTableCell

		String bool = new Table().Method_clickButtonInTableCell(object, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageCount(int arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyImageCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0));
		// Method_verifyImageCount

		String bool = new Image().Method_verifyImageCount(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyImageEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyImageEnabled

		String bool = new Image().Method_verifyImageEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyImageDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyImageDisabled

		String bool = new Deprecate().Method_verifyImageDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyImageToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyImageToolTip

		String bool = new Image().Method_verifyImageToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean WaitforImageLoad(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called WaitforImageLoad");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_waitforImageLoad

		String bool = new Image().Method_waitforImageLoad(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public String CopyFromClipBoard() throws Exception {

		System.out.println(">>Keyword Called CopyFromClipBoard");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_copyFromClipBoard

		return new UnCategorised().Method_copyFromClipBoard().getOutput();

	}

	public boolean KeyLeft() throws ToolNotSetException, AWTException {

		System.out.println(">>Keyword Called KeyLeft");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_KeyLeft

		String bool = new UnCategorised().Method_KeyLeft().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean KeyRight() throws ToolNotSetException, AWTException {

		System.out.println(">>Keyword Called KeyRight");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_KeyRight

		String bool = new UnCategorised().Method_KeyRight().getOutput();
		return DataType.getBoolean(bool);

	}

	public int GetElementIndex(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetElementIndex");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_GetElementIndex

		return DataType.getInt(new Deprecate().Method_GetElementIndex(object).getOutput());

	}

	public String ReturnConcatenated(String arg0, String arg1, String arg2) throws Exception {

		System.out.println(">>Keyword Called ReturnConcatenated");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		// Method_returnConcatenated

		return new Deprecate().Method_returnConcatenated(arg0, arg1, arg2).getOutput();

	}

	public boolean VerifyAllLink(String arg0) throws Exception {

		System.out.println(">>Keyword Called VerifyAllLink");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_verifyAllLink

		String bool = new Deprecate().Method_verifyAllLink(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyDropDownToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyDropDownToolTip

		String bool = new DropDown().Method_verifyDropDownToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyLinkEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyLinkEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyLinkEnabled

		String bool = new Links().Method_verifyLinkEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyLinkDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyLinkDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyLinkDisabled

		String bool = new Deprecate().Method_verifyLinkDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyLinkVisible(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyLinkVisible");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyLinkVisible

		String bool = new Links().Method_verifyLinkVisible(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean WaitforLink(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called WaitforLink");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_waitforLink

		String bool = new Links().Method_waitforLink(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageVisible(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyImageVisible");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyImageVisible

		String bool = new Image().Method_verifyImageVisible(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageNotVisible(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyImageNotVisible");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyImageNotVisible

		String bool = new Deprecate().Method_verifyImageNotVisible(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyImageExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyImageExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyImageExist

		String bool = new Image().Method_verifyImageExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

//
	public boolean DoubleClickImage(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called DoubleClickImage");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_doubleClickImage

		String bool = new Image().Method_doubleClickImage(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClickImage(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClickImage");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_clickImage

		String bool = new Image().Method_clickImage(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyLinkToolTip(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyLinkToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyLinkToolTip

		String bool = new Links().Method_verifyLinkToolTip(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetPopupText(ORObject arg0, String arg1, String arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetPopupText");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		// Method_Getpopuptext

		return new PopUp().Method_Getpopuptext(object, arg1, arg2).getOutput();

	}

	public boolean KeyPressNative(String arg0) throws Exception {

		System.out.println(">>Keyword Called KeyPressNative");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_keyPressNative

		String bool = new Deprecate().Method_keyPressNative(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean Enter() throws ToolNotSetException, AWTException {

		System.out.println(">>Keyword Called Enter");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_Enter

		String bool = new UnCategorised().Method_Enter().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean WaitForEditBoxDisabled(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called WaitForEditBoxDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_waitForEditBoxDisabled

		String bool = new Deprecate().Method_waitForEditBoxDisabled(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean WaitForEditBoxEnabled(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called WaitForEditBoxEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_waitForEditBoxEnabled

		String bool = new EditBox().Method_waitForEditBoxEnabled(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyRadioButtonExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyRadioButtonExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyRadioButtonExist

		String bool = new Radio().Method_verifyRadioButtonExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyRadioButtonDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyRadioButtonDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyRadioButtonDisabled

		String bool = new Deprecate().Method_verifyRadioButtonDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyRadioButtonEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyRadioButtonEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyRadioButtonEnabled

		String bool = new Radio().Method_verifyRadioButtonEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeFocusRadioButton() throws Exception {

		System.out.println(">>Keyword Called DeFocusRadioButton");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusRadioButton

		String bool = new Radio().Method_deFocusRadioButton().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean FocusRadioButton(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called FocusRadioButton");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_focusRadioButton

		String bool = new Deprecate().Method_focusRadioButton(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyCheckBoxExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyCheckBoxExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyCheckBoxExist

		String bool = new Checkbox().Method_verifyCheckBoxExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyCheckBoxDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyCheckBoxDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyCheckBoxDisabled

		String bool = new Deprecate().Method_verifyCheckBoxDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyCheckBoxEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyCheckBoxEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyCheckBoxEnabled

		String bool = new Checkbox().Method_verifyCheckBoxEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeFocusCheckBox() throws Exception {

		System.out.println(">>Keyword Called DeFocusCheckBox");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusCheckBox

		String bool = new Checkbox().Method_deFocusCheckBox().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean FocusCheckBox(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called FocusCheckBox");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_focusCheckBox

		String bool = new Checkbox().Method_focusCheckBox(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownSelection(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyDropDownSelection");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyDropDownSelection

		String bool = new DropDown().Method_verifyDropDownSelection(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownExist(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyDropDownExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyDropDownExist

		String bool = new DropDown().Method_verifyDropDownExist(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyDropDownDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_VerifyDropDownDisabled

		String bool = new Deprecate().Method_VerifyDropDownDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyDropDownEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_VerifyDropDownEnabled

		String bool = new DropDown().Method_VerifyDropDownEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyAllDropDownItemExist(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyAllDropDownItemExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyAllDropDownItemExist

		String bool = new DropDown().Method_verifyAllDropDownItemExist(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyMultipleDropDownItemExist(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyMultipleDropDownItemExist");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyMultipleDropDownItemExist

		String bool = new DropDown().Method_verifyMultipleDropDownItemExist(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyDropDownItemCount(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyDropDownItemCount");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_verifyDropDownItemCount

		String bool = new DropDown().Method_verifyDropDownItemCount(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeFocusfromDropDown() {

		System.out.println(">>Keyword Called DeFocusfromDropDown");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusfromDropDown

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean SetFocusonDropDown(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called SetFocusonDropDown");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_SetFocusonDropDown

		String bool = new DropDown().Method_deFocusfromDropDown().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxExistAndWait(ORObject arg0) {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxExistAndWait");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyEditBoxExist

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxValue(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxValue");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyEditBoxValue

		String bool = new Deprecate().Method_verifyEditBoxValue(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxLength(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxLength");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1));
		// Method_verifyEditBoxLength

		String bool = new Deprecate().Method_verifyEditBoxLength(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxName(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxName");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		// Method_verifyEditBoxName

		String bool = new Deprecate().Method_verifyEditBoxName(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxNonEditable(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxNonEditable");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyEditBoxNonEditable

		String bool = new Deprecate().Method_verifyEditBoxNonEditable(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxEditable(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxEditable");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyEditBoxEditable

		String bool = new EditBox().Method_verifyEditBoxEditable(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxDisabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxDisabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyEditBoxDisabled

		String bool = new Deprecate().Method_verifyEditBoxDisabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyEditBoxEnabled(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyEditBoxEnabled");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_verifyEditBoxEnabled

		String bool = new EditBox().Method_verifyEditBoxEnabled(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean DeFocusEditField() throws Exception {

		System.out.println(">>Keyword Called DeFocusEditField");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_deFocusEditField

		String bool = new EditBox().Method_deFocusEditField().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SetfocusEditField(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called SetfocusEditField");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_SetfocusEditField

		String bool = new EditBox().Method_SetfocusEditField(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTextareaColsRowLength(ORObject arg0, int arg1, int arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called VerifyTextareaColsRowLength");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2)); 
		// Method_verifyTextareaColsRowLength

		String bool = new Deprecate().Method_verifyTextareaColsRowLength(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyBrowserTitle(String arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyBrowserTitle");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_VerifyBrowserTitle

		String bool = new Deprecate().Method_VerifyBrowserTitle(arg0, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean TypeSecureText(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called TypeSecureText");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_typeSecureText

		String bool = new Deprecate().Method_typeSecureText(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean NextPageObject(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called NextPageObject");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); 
		// Method_nextPageObject

		String bool = new Deprecate().Method_nextPageObject(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SelectGroupRadioButton(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called SelectGroupRadioButton");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); 
		// Method_selectGroupRadioButton

		String bool = new Deprecate().Method_selectGroupRadioButton(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ReportMessage(String arg0, String arg1) throws ToolNotSetException {

		System.out.println(">>Keyword Called ReportMessage");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1); // Method_reportMessage

		String bool = new Deprecate().Method_reportMessage(arg0, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public int GetTableRowNumber(ORObject arg0, int arg1, String arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetTableRowNumber");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), arg2); 
		// Method_TableGetTextRow

		return DataType.getInt(new Table().Method_TableGetTextRow(object, arg1, arg2).getOutput());

	}

	public int GetTableColumnNumber(ORObject arg0, int arg1, String arg2) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetTableColumnNumber");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), arg2); 
		// Method_TableGetTextColumn
		
		return DataType.getInt(new Table().Method_TableGetTextColumn(object, arg1, arg2).getOutput());

	}

	public boolean MouseHover(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called MouseHover");

		ContextInitiator.addFunction(DataType.getMethodName());
		 // Method_MouseHover

		String bool = new UnCategorised().Method_MouseHover(object).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean NavigateTo(String arg0) throws Exception {

		System.out.println(">>Keyword Called NavigateTo");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); 
		// Method_navigateTo

		String bool = new Browser().Method_navigateTo(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean AssertTextPresent(String arg0) throws Exception {

		System.out.println(">>Keyword Called AssertTextPresent");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); 
		// Method_AssertTextPresent

		String bool = new UnCategorised().Method_AssertTextPresent(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean ClickAt(ORObject arg0, String arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClickAt");

		ContextInitiator.addFunction(DataType.getMethodName());
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); 
		// Method_clickAt

		String bool = new Deprecate().Method_clickAt(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetDropDownToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetDropDownToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		 // Method_getDropDownToolTip

		return new DropDown().Method_getDropDownToolTip(object).getOutput();

	}

	public String GetEditBoxToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetEditBoxToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_getEditBoxToolTip

		return new EditBox().Method_getEditBoxToolTip(object).getOutput();

	}

	public String GetTextAreaToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetTextAreaToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		 // Method_getTextAreaToolTip

		return new TextArea().Method_getTextAreaToolTip(object).getOutput();

	}

	public String GetButtonToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetButtonToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		 // Method_getButtonToolTip

		return new Button().Method_getButtonToolTip(object).getOutput();

	}

	public String GetCheckBoxToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetCheckBoxToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_getCheckBoxToolTip

		return new Checkbox().Method_getCheckBoxToolTip(object).getOutput();

	}

	public String GetLinkToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetLinkToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_getLinkToolTip

		return new Links().Method_getLinkToolTip(object).getOutput();

	}

	public String GetObjectToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetObjectToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		 // Method_getObjectToolTip

		return new WebObjects().Method_getObjectToolTip(object).getOutput();

	}

	public String GetImageToolTip(ORObject arg0) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called GetImageToolTip");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_getImageToolTip

		return new Image().Method_getImageToolTip(object).getOutput();

	}

	public boolean MinimizeBrowser() throws Exception {

		System.out.println(">>Keyword Called MinimizeBrowser");

		ContextInitiator.addFunction(DataType.getMethodName());
		// Method_MinimizeBrowser

		String bool = new Browser().Method_MinimizeBrowser().getOutput();
		return DataType.getBoolean(bool);

	}
//
//	public int GetTextAreaLength(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetTextAreaLength");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_GetTextAreaLength
//
//		return 0;
//
//	}
//
//	public String GetTextAreaColumnRowLength(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetTextAreaColumnRowLength");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_GetTextAreaColRowLength
//
//		return "";
//
//	}
//
//	public String GetEditBoxName(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetEditBoxName");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_GetEditBoxName
//
//		return "";
//
//	}
//
//	public boolean AcceptPopup() {
//
//		System.out.println(">>Keyword Called AcceptPopup");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_acceptPopup
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean DismissPopup() {
//
//		System.out.println(">>Keyword Called DismissPopup");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_dismissPopup
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean VerifyPopupPresent(String arg0) {
//
//		System.out.println(">>Keyword Called VerifyPopupPresent");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_verifyPopupPresent
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean SelectFrame(ORObject arg0) {
//
//		System.out.println(">>Keyword Called SelectFrame");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectFrame
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean SwitchToDefaultContent() {
//
//		System.out.println(">>Keyword Called SwitchToDefaultContent");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_switchToDefaultContent
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetObjectValue(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetObjectValue");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectValue
//
//		return "";
//
//	}
//
//	public boolean VerifyObjectValue(ORObject arg0, String arg1) {
//
//		System.out.println(">>Keyword Called VerifyObjectValue");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_VerifyObjectValue
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean GetObjectVisibility(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetObjectVisibility");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectVisibility
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean GetObjectExistence(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetObjectExistence");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectExistence
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean GetObjectEnabled(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetObjectEnabled");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectEnabled
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public int GetTableRowCount(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetTableRowCount");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getTableRowCount
//
//		return 0;
//
//	}
//
//	public int GetTableColumnCount(ORObject arg0, int arg1) {
//
//		System.out.println(">>Keyword Called GetTableColumnCount");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getTableColCount
//
//		return 0;
//
//	}
//
//	public boolean ExcelCompare(String arg0, String arg1, String arg2, String arg3) {
//
//		System.out.println(">>Keyword Called ExcelCompare");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_excelCompare
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean SetPage(ORObject arg0) {
//
//		System.out.println(">>Keyword Called SetPage");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_setPage
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetObjectCSSProperty(ORObject arg0, String arg1) {
//
//		System.out.println(">>Keyword Called GetObjectCSSProperty");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectCSSProperty
//
//		return "";
//
//	}
//
//	public boolean VerifyCheckboxStatusInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
//
//		System.out.println(">>Keyword Called VerifyCheckboxStatusInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_verifyCheckboxStatusInTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetCheckboxStatus(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetCheckboxStatus");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getCheckboxStatus
//
//		return "";
//
//	}
//
//	public boolean SelectRadioButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
//
//		System.out.println(">>Keyword Called SelectRadioButtonInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectRadioButtobTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean RightClickOnObject(ORObject arg0, int arg1) {
//
//		System.out.println(">>Keyword Called RightClickOnObject");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_rightClickAndSelect
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetObjectProperty(ORObject arg0, String arg1) {
//
//		System.out.println(">>Keyword Called GetObjectProperty");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectProperty
//
//		return "";
//
//	}
//
//	public boolean SelectCheckBoxinTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
//
//		System.out.println(">>Keyword Called SelectCheckBoxinTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectCheckBoxinTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean WaitForObjectVisible(ORObject arg0, int arg1) {
//
//		System.out.println(">>Keyword Called WaitForObjectVisible");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_waitforobjectvisible
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetTextAreavalue(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetTextAreavalue");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getTextAreavalue
//
//		return "";
//
//	}
//
//	public boolean WaitForObjectEditable(ORObject arg0, int arg1) {
//
//		System.out.println(">>Keyword Called WaitForObjectEditable");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_waitForObjectEditable
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean TypeTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
//
//		System.out.println(">>Keyword Called TypeTextInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_typeTextInTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean WaitForObjectEnable(ORObject arg0, int arg1) {
//
//		System.out.println(">>Keyword Called WaitForObjectEnable");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_waitforobjectenable
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Swipe(double arg0, double arg1, double arg2, double arg3, double arg4) {
//
//		System.out.println(">>Keyword Called Swipe");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_swipe
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Moblie_DeselectToggle(ORObject arg0) {
//
//		System.out.println(">>Keyword Called Moblie_DeselectToggle");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_deSelectToggle
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean SwipeLeft() {
//
//		System.out.println(">>Keyword Called SwipeLeft");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_SwipeLeft
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean SwipeRight() {
//
//		System.out.println(">>Keyword Called SwipeRight");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_SwipeRight
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetObjectHeightWidth(ORObject arg0) {
//
//		System.out.println(">>Keyword Called GetObjectHeightWidth");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectHeightWidth
//
//		return "";
//
//	}
//
//	public boolean SwipeObject(ORObject arg0, int arg1, String arg2) {
//
//		System.out.println(">>Keyword Called SwipeObject");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_swipeWithObject
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Swipe(int arg0, String arg1) {
//
//		System.out.println(">>Keyword Called Swipe");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_MobilitySwipe
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public int GetObjectCount(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6,
//			String arg7, String arg8, String arg9) {
//
//		System.out.println(">>Keyword Called GetObjectCount");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getObjectCount
//
//		return 0;
//
//	}
//
//	public String GetObjectText(ORObject arg0, String arg1, String arg2) {
//
//		System.out.println(">>Keyword Called GetObjectText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_GetObjectText
//
//		return "";
//
//	}
//
//	public boolean VerifyEditBoxExist() {
//
//		System.out.println(">>Keyword Called VerifyEditBoxExist");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_verifyEditBoxExist
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean ClickInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5,
//			String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
//
//		System.out.println(">>Keyword Called ClickInTableCellByQuery");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_clickInTableCellByQuery
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
//			String arg5, int arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
//			String arg13) {
//
//		System.out.println(">>Keyword Called TypeTextInTableCellByQuery");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_typeTextInTableCellByQuery
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
//			int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
//
//		System.out.println(">>Keyword Called GetTextFromTableCellByQuery");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getTextFromTableCellByQuery
//
//		return "";
//
//	}
//
//	public boolean Web_ClickByText(String arg0, int arg1, boolean arg2, ORObject arg3, String arg4, String arg5) {
//
//		System.out.println(">>Keyword Called Web_ClickByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_clickByText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_ClickByTextInSequence(String arg0, int arg1, int arg2, boolean arg3, String arg4, int arg5,
//			boolean arg6, ORObject arg7, ORObject arg8, ORObject arg9, ORObject arg10, ORObject arg11, boolean arg12,
//			String arg13, int arg14, boolean arg15, String arg16, int arg17, boolean arg18, String arg19) {
//
//		System.out.println(">>Keyword Called Web_ClickByTextInSequence");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_clickByTextInSequence
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_SelectByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
//
//		System.out.println(">>Keyword Called Web_SelectByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_SelectByText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_TypeByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, ORObject arg5) {
//
//		System.out.println(">>Keyword Called Web_TypeByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_typeTextUsingText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean MouseHoverOnText(String arg0, int arg1, boolean arg2, ORObject arg3) {
//
//		System.out.println(">>Keyword Called MouseHoverOnText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_mouseHoverOnText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean ClickImageByTitleAlt(String arg0, int arg1, boolean arg2) {
//
//		System.out.println(">>Keyword Called ClickImageByTitle/Alt");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_clickImageByAltText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_SelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, String arg4,
//			ORObject arg5) {
//
//		System.out.println(">>Keyword Called Web_SelectCheckboxByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectCheckBoxByText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_DeSelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
//
//		System.out.println(">>Keyword Called Web_DeSelectCheckboxByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_deSelectCheckBoxByText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_SelectRadioButtonByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
//
//		System.out.println(">>Keyword Called Web_SelectRadioButtonByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectRadioButtonByText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_SelectDropDownByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4,
//			boolean arg5, ORObject arg6) {
//
//		System.out.println(">>Keyword Called Web_SelectDropDownByText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectDropDownByText
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_GoBack() {
//
//		System.out.println(">>Keyword Called Web_GoBack");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_goBack
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_SelectListItem(ORObject arg0, String arg1) {
//
//		System.out.println(">>Keyword Called Web_SelectListItem");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectListItem
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean Web_VerifyListItemExists(ORObject arg0, String arg1) {
//
//		System.out.println(">>Keyword Called Web_VerifyListItemExists");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_verifyListItemExists
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetAllColumnText(ORObject arg0, String arg1, String arg2) {
//
//		System.out.println(">>Keyword Called GetAllColumnText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getAllColText
//
//		return "";
//
//	}
//
//	public String GetSingleTableColumnText(ORObject arg0, int arg1, String arg2) {
//
//		System.out.println(">>Keyword Called GetSingleTableColumnText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getSingleColText
//
//		return "";
//
//	}
//
//	public String GetAllRowText(ORObject arg0, String arg1, String arg2) {
//
//		System.out.println(">>Keyword Called GetAllRowText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getAllRowText
//
//		return "";
//
//	}
//
//	public String GetSingleTableRowText(ORObject arg0, int arg1, String arg2) {
//
//		System.out.println(">>Keyword Called GetSingleTableRowText");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getSingleRowText
//
//		return "";
//
//	}
//
//	public boolean SelectDropDownInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
//
//		System.out.println(">>Keyword Called SelectDropDownInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectDropDownInTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean DeSelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
//
//		System.out.println(">>Keyword Called DeSelectMultipleDropdownItemInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_deSelectMultipleDropDownItemInTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public boolean SelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
//
//		System.out.println(">>Keyword Called SelectMultipleDropdownItemInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_selectMultipleDropdownItemInTableCell
//
//		String bool = "false"; return DT.getBoolean(bool);
//
//	}
//
//	public String GetSelectedDropDownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
//
//		System.out.println(">>Keyword Called GetSelectedDropDownItemInTableCell");
//
//		ContextInitiator.addFunction(DT.getMethodName()); ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_getSelectedDropDownInTableCell
//
//		return "";
//
//	}
//

	public boolean DoubleClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called DoubleClickTableCell");

		ContextInitiator.addFunction("DoubleClickTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), String.valueOf(arg3), String.valueOf(arg4));
		// Method_doubleClickInTableCell

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public String FetchObjectPropertyInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called FetchObjectPropertyInTableCell");

		ContextInitiator.addFunction("FetchObjectPropertyInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), String.valueOf(arg3), String.valueOf(arg4), String.valueOf(arg5));
		// Method_fetchObjectPropertyInTableCell

		return new Table().Method_fetchObjectPropertyInTableCell(object, arg1, arg2, arg3, arg4, arg5).getOutput();

	}

	public boolean ClickOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called ClickOnObjectInTableCell");

		ContextInitiator.addFunction("ClickOnObjectInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), String.valueOf(arg3), String.valueOf(arg4));
		// Method_clickOnObjectInTableCell

		String bool = new Table().Method_clickOnObjectInTableCell(object, arg1, arg2, arg3, arg4).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean TypeOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called TypeOnObjectInTableCell");

		ContextInitiator.addFunction("TypeOnObjectInTableCell");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2), String.valueOf(arg3), String.valueOf(arg4), String.valueOf(arg5));
		// Method_typeOnObjecttInTableCell

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean WaitForObjectDisable(ORObject arg0, int arg1) throws Exception {
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		System.out.println(">>Keyword Called WaitForObjectDisable");

		ContextInitiator.addFunction("WaitForObjectDisable");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_waitForObjectdisable

		String bool = new WebObjects().Method_waitForObjectdisable(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

//	public String CaptureObjectSnapshot(ORObject arg0) {
//		AppiumObject object = new ObjectConverter().formatObject(arg0);
//
//		System.out.println(">>Keyword Called CaptureObjectSnapshot");
//
//		ContextInitiator.addFunction("CaptureObjectSnapshot");
//		// Method_captureObjectSnapShot
//
//		return new WebObjects().Method_captureObjectSnapShot(object);
//
//	}

	public String GetEditboxDefaultvalue(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetEditboxDefaultvalue");

		ContextInitiator.addFunction("GetEditboxDefaultvalue");
		// Method_getEditboxDefaultvalue
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return new EditBox().Method_getEditboxDefaultvalue(object).getOutput();

	}

	public int GetEditBoxLength(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetEditBoxLength");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		ContextInitiator.addFunction("GetEditBoxLength");
		// Method_getEditboxLength

		return DataType.getInt(new EditBox().Method_getEditboxLength(object).getOutput());

	}

	public String GetEditboxValue(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetEditboxValue");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		ContextInitiator.addFunction("GetEditboxValue");
		// Method_getEditboxValue

		return new EditBox().Method_getEditboxValue(object).getOutput();

	}

	public String GetTextAreaDefaultvalue(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetTextAreaDefaultvalue");

		ContextInitiator.addFunction("GetTextAreaDefaultvalue");
		// Method_getTextAreaDefaultvalue
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return new TextArea().Method_getTextAreaDefaultvalue(object).getOutput();

	}

	public String GetTextAreaName(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetTextAreaName");

		ContextInitiator.addFunction("GetTextAreaName");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getTextAreaName

		return new TextArea().Method_getTextAreaName(object).getOutput();

	}

	public boolean VerifyAllButtons(String arg0) {

		System.out.println(">>Keyword Called VerifyAllButtons");

		ContextInitiator.addFunction("VerifyAllButtons");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_verifyAllButtons

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public int GetImageCount() throws Exception {

		System.out.println(">>Keyword Called GetImageCount");

		ContextInitiator.addFunction("GetImageCount");
		// Method_getImageCount

		return DataType.getInt(new Image().Method_getImageCount().getOutput());

	}

	public String GetDropdownDefaultItem(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetDropdownDefaultItem");

		ContextInitiator.addFunction(DataType.getMethodName()); // Method_getDropDownDefaultValue
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return new DropDown().Method_getDropDownDefaultValue(object).getOutput();

	}

	public boolean VerifyChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3, int arg4)
			throws ToolNotSetException, ObjectNotFoundException, InterruptedException, TimeOut_ObjectNotFoundException {

		System.out.println(">>Keyword Called VerifyChildObjectCount");

		ContextInitiator.addFunction("VerifyChildObjectCount");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, String.valueOf(arg4)); // Method_verifyChildObjectCount
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new ActionByText().Method_verifyChildObjectCount(object, arg1, arg2, arg3, arg4).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyFullTableText(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyFullTableText");

		ContextInitiator.addFunction("VerifyFullTableText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_verifyFullTableText
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new Table().Method_verifyFullTableText(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableColumnCount(ORObject arg0, int arg1, int arg2) throws Exception {

		System.out.println(">>Keyword Called VerifyTableColumnCount");

		ContextInitiator.addFunction("VerifyTableColumnCount");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1), String.valueOf(arg2)); // Method_verifyTableColumnCount
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		String bool = new Table().Method_verifyTableColumnCount(object, arg1, arg2).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyTableRowCount(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called VerifyTableRowCount");

		ContextInitiator.addFunction("VerifyTableRowCount");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_verifyTableRowCount
		AppiumObject object = new ObjectConverter().formatObject(arg0);

		String bool = new Table().Method_verifyTableRowCount(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetTableColumnHeader(ORObject arg0, int arg1) throws Exception {

		System.out.println(">>Keyword Called GetTableColumnHeader");

		ContextInitiator.addFunction("GetTableColumnHeader");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_getTableColumnHeader
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return new Table().Method_getTableColumnHeader(object, arg1).getOutput();

	}

	public boolean SetBrowserCapability(String arg0, String arg1, String arg2, String arg3) throws Exception {

		System.out.println(">>Keyword Called SetBrowserCapability");

		ContextInitiator.addFunction("SetBrowserCapability");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		// Method_SetBrowserCapability

		String bool = new SetCapabilities().Method_SetBrowserCapability(arg0, arg1, arg2, arg3).getOutput();
		return DataType.getBoolean(bool);

	}

	public String GetCompleteTableText(ORObject arg0) throws Exception {

		System.out.println(">>Keyword Called GetCompleteTableText");

		ContextInitiator.addFunction("GetCompleteTableText"); // Method_getCompleteTableText
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return new Table().Method_getCompleteTableText(object).getOutput();

	}

	public boolean DeFocusObject() throws Exception {

		System.out.println(">>Keyword Called DeFocusObject");

		ContextInitiator.addFunction("DeFocusObject"); // Method_deFocusObject

		String bool = new WebObjects().Method_deFocusObject().getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean VerifyMultipleObjectProperty(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) {

		System.out.println(">>Keyword Called VerifyMultipleObjectProperty");

		ContextInitiator.addFunction("VerifyMultipleObjectProperty");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyMultipleObjectProperty

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public int Web_GetTableColumnCount(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called Web_GetTableColumnCount");

		ContextInitiator.addFunction("Web_GetTableColumnCount");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg1)); // Method_WebGetTableColCount
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		return 0;

	}

	public int Web_GetTableRowCount(ORObject arg0) {

		System.out.println(">>Keyword Called Web_GetTableRowCount");

		ContextInitiator.addFunction("Web_GetTableRowCount");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_WebGetTableRowCount

		return 0;

	}

	public boolean Launch_iOSApplicationOnDevice(String arg0, String arg1) {

		System.out.println(">>Keyword Called Launch_iOSApplicationOnDevice");

		ContextInitiator.addFunction("Launch_iOSApplicationOnDevice");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		// Method_Launch_iOSApplicationOnDevice

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean Web_SetFocusOnCurrentWindow() {

		System.out.println(">>Keyword Called Web_SetFocusOnCurrentWindow");

		ContextInitiator.addFunction("Web_SetFocusOnCurrentWindow");
		// Method_setFocusOnCurrentWindow

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean Web_WaitForWindowLoad(String arg0, int arg1, boolean arg2, int arg3) {

		System.out.println(">>Keyword Called Web_WaitForWindowLoad");

		ContextInitiator.addFunction("Web_WaitForWindowLoad");
		// ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg2, arg3);
		// Method_waitForWindowLoad

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public double GetLoadTime() {

		System.out.println(">>Keyword Called GetLoadTime");

		ContextInitiator.addFunction("GetLoadTime");
		// Method_getLoadingTime

		return 0;

	}

	public boolean IgnoreXMLHttpRequest(String arg0) throws ArgumentDataMissingException {

		System.out.println(">>Keyword Called IgnoreXMLHttpRequest");

		ContextInitiator.addFunction("IgnoreXMLHttpRequest");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		// Method_ignoreXMLHttpRequest

		String bool = new UnCategorised().Method_ignoreXMLHttpRequest(arg0).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean SynchronizeBrowser(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {

		System.out.println(">>Keyword Called SynchronizeBrowser");

		ContextInitiator.addFunction("SynchronizeBrowser");
		// ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3); //
		// Method_syncBrowser

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextInPTag(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called TypeTextInPTag");

		ContextInitiator.addFunction("TypeTextInPTag");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_TypeTextInContentEditable

		String bool = new EditBox().Method_TypeTextInContentEditable(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public String VisualComparisonForPage(String arg0, String arg1, boolean arg2, boolean arg3) {

		System.out.println(">>Keyword Called VisualComparisonForPage");

		ContextInitiator.addFunction("VisualComparisonForPage");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, String.valueOf(arg2), String.valueOf(arg3));
		// Custom_visualComparionForPage

		return "";

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, int arg6, int arg7, String arg8, String arg9, String arg10, String arg11,
			String arg12, String arg13) {

		System.out.println(">>Keyword Called GetTextFromTableCellByQuery");

		ContextInitiator.addFunction("GetTextFromTableCellByQuery");
		// ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11,
			String arg12, String arg13, String arg14) {

		System.out.println(">>Keyword Called TypeTextInTableCellByQuery");

		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		// ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_typeTextInTableCellByQuery

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean ClickOnObjectInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, int arg7, String arg8, int arg9, String arg10,
			String arg11, String arg12, String arg13, String arg14) {

		System.out.println(">>Keyword Called ClickOnObjectInTableCellByQuery");

		ContextInitiator.addFunction("ClickOnObjectInTableCellByQuery");
//		ContextInitiator.addDataRgumentsInFunctionCall(arg0); // Method_clickInTableCellByQuery
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean TypeTextAndEnterEditBox(ORObject arg0, String arg1) throws Exception {

		System.out.println(">>Keyword Called TypeTextAndEnterEditBox");

		ContextInitiator.addFunction("TypeTextAndEnterEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1); // Method_typeTextandEnterEditBox
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		String bool = new EditBox().Method_typeTextandEnterEditBox(object, arg1).getOutput();
		return DataType.getBoolean(bool);

	}

	public boolean Web_ResizeBrowser(int arg0, int arg1) {

		System.out.println(">>Keyword Called Web_ResizeBrowser");

		ContextInitiator.addFunction("Web_ResizeBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(String.valueOf(arg0), String.valueOf(arg1));
		// Method_setViewPort

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	public boolean RightClickAndSelectByText(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called RightClickAndSelectByText");

		ContextInitiator.addFunction("RightClickAndSelectByText");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_rightClickAndSelectByText

		String bool = "false";
		return DataType.getBoolean(bool);

	}

	/* Method not found in appium */
	public boolean IsTextPresentOnScreen(ORObject arg0) {

		System.out.println(">>Keyword Called IsTextPresentOnScreen");

		ContextInitiator.addFunction("IsTextPresentOnScreen");
		AppiumObject object = new ObjectConverter().formatObject(arg0);
		// Method_IsTextPresentOnScreen_Generic

		String bool = "false";
		return DataType.getBoolean(bool);

	}

}
