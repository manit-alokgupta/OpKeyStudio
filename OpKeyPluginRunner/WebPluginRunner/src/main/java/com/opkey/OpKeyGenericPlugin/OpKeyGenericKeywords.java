package com.opkey.OpKeyGenericPlugin;

import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.exceptionhandling.RetryKeywordAgainException;
import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.crestech.opkey.plugin.webdriver.keywords.Utils;
import com.crestech.opkey.plugin.webdriver.keywords.WebObjects;
import com.crestech.opkey.plugin.webdriver.object.WebDriverObject;
import com.opkey.ObjectFromatter.ObjectConverter;
import com.opkey.context.ContextInitiator;
import com.opkeystudio.runtime.ORObject;

public class OpKeyGenericKeywords {
	public OpKeyGenericKeywords() {
		new ContextInitiator().initContext();
	}

	public boolean TypeTextOnEditBox(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		System.out.println(">>Keyword Called TypeTextOnEditBox");

		// Method_typeTextOnEditBox
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		return false;

	}

	public boolean TypeKeysOnEditBox(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeKeysOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		System.out.println(">>Keyword Called TypeKeysOnEditBox");

		// Method_typeKeysOnEditBox
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		return false;

	}

	public boolean VerifyEditBoxText(ORObject arg0, String arg1) {
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		System.out.println(">>Keyword Called VerifyEditBoxText");

		// Method_verifyeditboxtext

		return false;

	}

	public boolean OpenBrowser(String arg0, String arg1) throws Exception {
		ContextInitiator.addFunction("OpenBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		System.out.println(">>Keyword Called OpenBrowser");

		// Method_WebBrowserOpen
		new Browser().Method_WebBrowserOpen(arg0, arg1);
		return false;

	}

	public boolean SelectDropDownItem(ORObject arg0, String arg1) {
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		System.out.println(">>Keyword Called SelectDropDownItem");

		// Method_selectDropDownItem

		return false;

	}

	public boolean SelectCheckBox(ORObject arg0, String arg1) {
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		System.out.println(">>Keyword Called SelectCheckBox");

		// Method_selectCheckBox

		return false;

	}

	public boolean SelectRadioButton(ORObject arg0, int arg1) {
		System.out.println(">>Keyword Called SelectRadioButton");

		// Method_SelectRadio

		return false;

	}

	public boolean Click(ORObject arg0) throws Exception {
		ContextInitiator.addFunction("Click");
		System.out.println(">>Keyword Called Click");

		// Method_ObjectClick
		try {
			new Utils().waitForPageLoadAndOtherAjax();
			WebDriverObject object = new ObjectConverter().formatObject(arg0);
			System.out.println(">>Tag Name " + object.getTagName());
			FunctionResult fr = new WebObjects().Method_ObjectClick(object);
			System.out.println("Keyword Output " + fr.getOutput());
			return false;
		} catch (RetryKeywordAgainException e) {
			return Click(arg0);
		}

	}

	public boolean DoubleClick(ORObject arg0) {

		System.out.println(">>Keyword Called DoubleClick");

		// Method_dblClick
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		return false;

	}

	public boolean RefreshBrowser() {

		System.out.println(">>Keyword Called RefreshBrowser");

		// Method_RefreshBrowser

		return false;

	}

	public boolean MaximizeBrowser() {

		System.out.println(">>Keyword Called MaximizeBrowser");

		// Method_MaximizeBrowser

		return false;

	}

	public String FetchBrowserURL() {

		System.out.println(">>Keyword Called FetchBrowserURL");

		// Method_fetchBrowserURL

		return "";

	}

	public String FetchBrowserTitle(String arg0) {
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		System.out.println(">>Keyword Called FetchBrowserTitle");

		// Method_fetchBrowserTitle

		return "";

	}

	public boolean GoBackAndWait(int arg0) {

		System.out.println(">>Keyword Called GoBackAndWait");

		// Method_goBackAndWait

		return false;

	}

	public boolean RefreshAndWait(int arg0) {

		System.out.println(">>Keyword Called RefreshAndWait");

		// Method_refreshAndWait

		return false;

	}

	public boolean ClearEditField(ORObject arg0) {

		System.out.println(">>Keyword Called ClearEditField");

		// Method_clearEditField

		return false;

	}

	public boolean TypeTextAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called TypeTextAndWait");

		// Method_typeTextAndWait

		return false;

	}

	public boolean TypeKeysAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called TypeKeysAndWait");

		// Method_typeKeysAndWait

		return false;

	}

	public boolean VerifyEditable(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyEditable");

		// Method_verifyEditable

		return false;

	}

	public boolean SelectMultipleDropDownItem(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called SelectMultipleDropDownItem");

		// Method_selectMultipleDropDownItem

		return false;

	}

	public boolean DeSelectDropDownItem(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called DeSelectDropDownItem");

		// Method_deselectDropDownItem

		return false;

	}

	public boolean DeSelectMultipleDropDownItem(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called DeSelectMultipleDropDownItem");

		// Method_deselectMultipleDropDownItem

		return false;

	}

	public boolean DeSelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called DeSelectDropDownItemAndWait");

		// Method_deselectDropDownItemAndWait

		return false;

	}

	public boolean DeSelectAllDropDownItemsAndWait(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called DeSelectAllDropDownItemsAndWait");

		// Method_deselectAllDropDownItemsAndWait

		return false;

	}

	public int GetDropDownItemCount(ORObject arg0) {

		System.out.println(">>Keyword Called GetDropDownItemCount");

		// Method_getDropDownItemCount

		return 0;

	}

	public String GetSelectedDropDownItem(ORObject arg0) {

		System.out.println(">>Keyword Called GetSelectedDropDownItem");

		// Method_getSelectedDropDownItem

		return "";

	}

	public boolean VerifyDropDownItemExists(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyDropDownItemExists");

		// Method_verifyDropDownItemExists

		return false;

	}

	public boolean DeSelectCheckBox(ORObject arg0) {

		System.out.println(">>Keyword Called DeSelectCheckBox");

		// Method_deSelectCheckBox

		return false;

	}

	public boolean verifyCheckBoxStatus(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called verifyCheckBoxStatus");

		// Method_verifyCheckBoxStatus

		return false;

	}

	public boolean SelectCheckBoxAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called SelectCheckBoxAndWait");

		// Method_selectCheckBoxAndWait

		return false;

	}

	public boolean DeSelectCheckBoxAndWait(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called DeSelectCheckBoxAndWait");

		// Method_deSelectCheckBoxAndWait

		return false;

	}

	public boolean SelectRadioButtonAndWait(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called SelectRadioButtonAndWait");

		// Method_selectRadioButtonAndWait

		return false;

	}

	public boolean ClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {

		System.out.println(">>Keyword Called ClickTableCell");

		// Method_clickTableCell

		return false;

	}

	public String GetTableCellText(ORObject arg0, int arg1, int arg2, String arg3, String arg4) {

		System.out.println(">>Keyword Called GetTableCellText");

		// Method_GetCellText

		return "";

	}

	public boolean SetFocus(ORObject arg0) {

		System.out.println(">>Keyword Called SetFocus");

		// Method_SetFocus

		return false;

	}

	public boolean VerifyObjectExists(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyObjectExists");

		// Method_ObjectExists

		return false;

	}

	public boolean VerifyObjectEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyObjectEnabled");

		// Method_ObjectisEnabled

		return false;

	}

	public boolean VerifyObjectVisible(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyObjectVisible");

		// Method_verifyObjectVisible

		return false;

	}

	public boolean VerifyObjectText(ORObject arg0, String arg1, String arg2, String arg3) {

		System.out.println(">>Keyword Called VerifyObjectText");

		// Method_ObjectTextVerification

		return false;

	}

	public boolean VerifyObjectPropertyValue(ORObject arg0, String arg1, String arg2) {

		System.out.println(">>Keyword Called VerifyObjectPropertyValue");

		// Method_VerifyPropertyValue

		return false;

	}

	public boolean WaitForObject(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForObject");

		// Method_waitforObject

		return false;

	}

	public boolean Wait(int arg0) {

		System.out.println(">>Keyword Called Wait");

		// Method_wait

		return false;

	}

	public int GetChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3) {

		System.out.println(">>Keyword Called GetChildObjectCount");

		// Method_getChildObjectCount

		return 0;

	}

	public boolean DoubleClickAndWait(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called DoubleClickAndWait");

		// Method_doubleClickAndWait

		return false;

	}

	public boolean DoubleClickAt(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called DoubleClickAt");

		// Method_doubleClickAt

		return false;

	}

	public boolean DragAndDrop(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called DragAndDrop");

		// Method_dragAndDrop

		return false;

	}

	public boolean DragAndDropAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called DragAndDropAndWait");

		// Method_dragAndDropAndWait

		return false;

	}

	public String GetAllButtons() {

		System.out.println(">>Keyword Called GetAllButtons");

		// Method_getAllButtons

		return "";

	}

	public String GetAllFields() {

		System.out.println(">>Keyword Called GetAllFields");

		// Method_getAllFields

		return "";

	}

	public String GetAllLinks() {

		System.out.println(">>Keyword Called GetAllLinks");

		// Method_getAllLinks

		return "";

	}

	public boolean CloseBrowser(String arg0) throws Exception {
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		System.out.println(">>Keyword Called CloseBrowser");

		// Method_CloseBrowser
		new Browser().Method_CloseBrowser(arg0);
		return false;

	}

	public boolean GoForward() {

		System.out.println(">>Keyword Called GoForward");

		// Method_goForward

		return false;

	}

	public boolean GoForwardAndWait(int arg0) {

		System.out.println(">>Keyword Called GoForwardAndWait");

		// Method_goForwardAndWait

		return false;

	}

	public boolean VerifyAllDropDownItems(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyAllDropDownItems");

		// Method_verifyAllDropDownItems

		return false;

	}

	public boolean SelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called SelectDropDownItemAndWait");

		// Method_selectDropDownItemAndWait

		return false;

	}

	public String GetFullTableText(ORObject arg0) {

		System.out.println(">>Keyword Called GetFullTableText");

		// Method_getFullTableText

		return "";

	}

	public boolean VerifyTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, String arg4, String arg5) {

		System.out.println(">>Keyword Called VerifyTextInTableCell");

		// Method_verifyTextInTable

		return false;

	}

	public boolean ClickTableCellAndWait(ORObject arg0, int arg1, int arg2, String arg3, int arg4, int arg5) {

		System.out.println(">>Keyword Called ClickTableCellAndWait");

		// Method_clickTableCellAndWait

		return false;

	}

	public boolean WaitForObjectProperty(ORObject arg0, String arg1, String arg2, int arg3) {

		System.out.println(">>Keyword Called WaitForObjectProperty");

		// Method_waitForObjectProperty

		return false;

	}

	public boolean HighlightObject(ORObject arg0) {

		System.out.println(">>Keyword Called HighlightObject");

		// Method_highlightObject

		return false;

	}

	public boolean RunScriptAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called RunScriptAndWait");

		// Method_runScriptAndWait

		return false;

	}

	public String GetSelectedRadioButtonFromGroup(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called GetSelectedRadioButtonFromGroup");

		// Method_getSelectedRadioButtonFromGroup

		return "";

	}

	public boolean SyncBrowser() {

		System.out.println(">>Keyword Called SyncBrowser");

		// Method_syncBrowser

		return false;

	}

	public boolean CloseAllBrowsers() throws Exception {

		System.out.println(">>Keyword Called CloseAllBrowsers");

		// Method_CloseAllBrowsers
		new Browser().Method_CloseAllBrowsers();
		return false;

	}

	public String GetAllTitles(String arg0) {

		System.out.println(">>Keyword Called GetAllTitles");

		// Method_GetAllTitles

		return "";

	}

	public boolean SelectWindow(String arg0, int arg1) {

		System.out.println(">>Keyword Called SelectWindow");

		// Method_selectWindow

		return false;

	}

	public boolean CloseSelectedWindow(String arg0, int arg1) {

		System.out.println(">>Keyword Called CloseSelectedWindow");

		// Method_closeSelectedWindow

		return false;

	}

	public boolean SetFocusOnWindow(int arg0) {

		System.out.println(">>Keyword Called SetFocusOnWindow");

		// Method_setFocousOnWindow

		return false;

	}

	public String GetPropertyValue(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called GetPropertyValue");

		// Method_getPropertyValue

		return "";

	}

	public boolean VerifyBrowserExist(String arg0) {

		System.out.println(">>Keyword Called VerifyBrowserExist");

		// Method_verifyBrowserExist

		return false;

	}

	public String GetTextFromEditBox(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextFromEditBox");

		// Method_getTextFromEditBox

		return "";

	}

	public boolean VerifyEditBoxDefaultValue(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyEditBoxDefaultValue");

		// Method_verifyEditBoxDefaultValue

		return false;

	}

	public boolean VerifyEditBoxNotExist(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyEditBoxNotExist");

		// Method_verifyEditBoxnotExist

		return false;

	}

	public boolean VerifyEditBoxToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyEditBoxToolTip");

		// Method_verifyEditBoxToolTip

		return false;

	}

	public boolean VerifyDropDownDefaultItem(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyDropDownDefaultItem");

		// Method_verifyDropDownDefaultItem

		return false;

	}

	public boolean ClickButton(ORObject arg0) {

		System.out.println(">>Keyword Called ClickButton");

		// Method_clickButton
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		return false;

	}

	public boolean ClickButtonAndWait(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called ClickButtonAndWait");

		// Method_clickButtonAndWait

		return false;

	}

	public boolean VerifyRadioButtonSelected(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyRadioButtonSelected");

		// Method_VerifyRadioButtonSelected

		return false;

	}

	public boolean VerifyRadioButtonNotSelected(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyRadioButtonNotSelected");

		// Method_VerifyRadioButtonNotSelected

		return false;

	}

	public boolean VerifyCheckBoxToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyCheckBoxToolTip");

		// Method_verifyCheckBoxToolTip

		return false;

	}

	public boolean VerifyTextAreaText(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyTextAreaText");

		// Method_verifyTextAreaText

		return false;

	}

	public boolean VerifyTextAreaEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyTextAreaEnabled");

		// Method_verifyTextAreaEnabled

		return false;

	}

	public int GetRadioButtonCount(ORObject arg0) {

		System.out.println(">>Keyword Called GetRadioButtonCount");

		// Method_getRadioButtonCount

		return 0;

	}

	public boolean TypeTextAndEnterTextArea(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called TypeTextAndEnterTextArea");

		// Method_typeTextandEnterTextArea

		return false;

	}

	public boolean VerifyTextAreaDefaultValue(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyTextAreaDefaultValue");

		// Method_verifyTextAreaDefaultValue

		return false;

	}

	public boolean VerifyTextAreaDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyTextAreaDisabled");

		// Method_verifyTextAreaDisabled

		return false;

	}

	public boolean VerifyTextAreaEditable(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyTextAreaEditable");

		// Method_verifyTextAreaEditable

		return false;

	}

	public boolean VerifyTextAreaNotEditable(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyTextAreaNotEditable");

		// Method_verifyTextAreaNotEditable

		return false;

	}

	public boolean VerifyTextAreaToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyTextAreaToolTip");

		// Method_verifyTextAreaToolTip

		return false;

	}

	public boolean VerifyTableColumnNumber(ORObject arg0, int arg1, String arg2, int arg3) {

		System.out.println(">>Keyword Called VerifyTableColumnNumber");

		// Method_verifyTableColumnNumber

		return false;

	}

	public boolean VerifyTableColumnText(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called VerifyTableColumnText");

		// Method_verifyTableColumnText

		return false;

	}

	public boolean VerifyTableRowText(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called VerifyTableRowText");

		// Method_verifyTableRowText

		return false;

	}

	public boolean VerifyTableColumnHeader(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyTableColumnHeader");

		// Method_verifyTableColumnHeader

		return false;

	}

	public boolean VerifyObjectToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyObjectToolTip");

		// Method_verifyObjectToolTip

		return false;

	}

	public boolean SelectMultipleDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {

		System.out.println(">>Keyword Called SelectMultipleDropDownItemAndWait");

		// Method_selectMultipleDropDownItemAndWait

		return false;

	}

	public boolean DoubleClickButton(ORObject arg0) {

		System.out.println(">>Keyword Called DoubleClickButton");

		// Method_doubleClickButton

		return false;

	}

	public boolean FocusButton(ORObject arg0) {

		System.out.println(">>Keyword Called FocusButton");

		// Method_focusButton

		return false;

	}

	public boolean DeFocusButton() {

		System.out.println(">>Keyword Called DeFocusButton");

		// Method_deFocusButton

		return false;

	}

	public boolean VerifyButtonDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyButtonDisabled");

		// Method_verifyButtonDisabled

		return false;

	}

	public boolean VerifyButtonExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyButtonExist");

		// Method_verifyButtonExist

		return false;

	}

	public boolean SelectRadioButtonOnIndexBasis(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called SelectRadioButtonOnIndexBasis");

		// Method_selectRadioButtonOnIndexBasis

		return false;

	}

	public boolean ClickLinkInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {

		System.out.println(">>Keyword Called ClickLinkInTableCell");

		// Method_clickLinkInTableCell

		return false;

	}

	public boolean TypeTextInTextArea(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called TypeTextInTextArea");

		// Method_typeTextInTextArea

		return false;

	}

	public boolean TypeKeysInTextArea(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called TypeKeysInTextArea");

		// Method_typeKeysInTextArea

		return false;

	}

	public boolean ClearTextArea(ORObject arg0) {

		System.out.println(">>Keyword Called ClearTextArea");

		// Method_clearTextArea

		return false;

	}

	public boolean SetfocusTextArea(ORObject arg0) {

		System.out.println(">>Keyword Called SetfocusTextArea");

		// Method_SetfocusTextArea

		return false;

	}

	public boolean DeFocusTextArea() {

		System.out.println(">>Keyword Called DeFocusTextArea");

		// Method_deFocusTextArea

		return false;

	}

	public String GetTextfromTextArea(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextfromTextArea");

		// Method_GetTextfromTextArea

		return "";

	}

	public boolean VerifyTextAreaValue(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyTextAreaValue");

		// Method_verifyTextAreaValue

		return false;

	}

	public boolean VerifyTextAreaExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyTextAreaExist");

		// Method_verifyTextAreaExist

		return false;

	}

	public boolean VerifyTextAreaNotExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyTextAreaNotExist");

		// Method_verifyTextAreanotExist

		return false;

	}

	public boolean VerifyTableRowNumber(ORObject arg0, int arg1, String arg2, int arg3) {

		System.out.println(">>Keyword Called VerifyTableRowNumber");

		// Method_verifyTableRowNumber

		return false;

	}

	public boolean VerifyTextAreaName(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyTextAreaName");

		// Method_verifyTextAreaName

		return false;

	}

	public boolean VerifyTextAreaLength(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyTextAreaLength");

		// Method_verifyTextAreaLength

		return false;

	}

	public int GetLinkCount() {

		System.out.println(">>Keyword Called GetLinkCount");

		// Method_getLinkCount

		return 0;

	}

	public boolean VerifyLinkCount(int arg0) {

		System.out.println(">>Keyword Called VerifyLinkCount");

		// Method_verifyLinkCount

		return false;

	}

	public boolean VerifyObjectDoesNotExists(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyObjectDoesNotExists");

		// Method_verifyObjectdoesnotExists

		return false;

	}

	public boolean VerifyObjectDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyObjectDisabled");

		// Method_verifyobjectDisabled

		return false;

	}

	public boolean PressTAB() {

		System.out.println(">>Keyword Called PressTAB");

		// Method_PressTAB

		return false;

	}

	public boolean CaptureSnapshot(String arg0) {

		System.out.println(">>Keyword Called CaptureSnapshot");

		// Method_CaptureSnapshot

		return false;

	}

	public boolean VerifyPopUpText(ORObject arg0, String arg1, String arg2, String arg3) {

		System.out.println(">>Keyword Called VerifyPopUpText");

		// Method_VerifyPopUpText

		return false;

	}

	public boolean ClickLink(ORObject arg0) {

		System.out.println(">>Keyword Called ClickLink");

		// Method_clickLink

		return false;

	}

	public boolean VerifyLinkExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyLinkExist");

		// Method_verifyLinkExist

		return false;

	}

	public boolean VerifyAllLinkExist(String arg0) {

		System.out.println(">>Keyword Called VerifyAllLinkExist");

		// Method_verifyAllLinkExist

		return false;

	}

	public boolean VerifyButtonEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyButtonEnabled");

		// Method_verifyButtonEnabled

		return false;

	}

	public boolean VerifyButtonToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyButtonToolTip");

		// Method_verifyButtonToolTip

		return false;

	}

	public boolean ClickButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {

		System.out.println(">>Keyword Called ClickButtonInTableCell");

		// Method_clickButtonInTableCell

		return false;

	}

	public boolean VerifyImageCount(int arg0) {

		System.out.println(">>Keyword Called VerifyImageCount");

		// Method_verifyImageCount

		return false;

	}

	public boolean VerifyImageEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyImageEnabled");

		// Method_verifyImageEnabled

		return false;

	}

	public boolean VerifyImageDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyImageDisabled");

		// Method_verifyImageDisabled

		return false;

	}

	public boolean VerifyImageToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyImageToolTip");

		// Method_verifyImageToolTip

		return false;

	}

	public boolean WaitforImageLoad(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitforImageLoad");

		// Method_waitforImageLoad

		return false;

	}

	public String CopyFromClipBoard() {

		System.out.println(">>Keyword Called CopyFromClipBoard");

		// Method_copyFromClipBoard

		return "";

	}

	public boolean KeyLeft() {

		System.out.println(">>Keyword Called KeyLeft");

		// Method_KeyLeft

		return false;

	}

	public boolean KeyRight() {

		System.out.println(">>Keyword Called KeyRight");

		// Method_KeyRight

		return false;

	}

	public int GetElementIndex(ORObject arg0) {

		System.out.println(">>Keyword Called GetElementIndex");

		// Method_GetElementIndex

		return 0;

	}

	public String ReturnConcatenated(String arg0, String arg1, String arg2) {

		System.out.println(">>Keyword Called ReturnConcatenated");

		// Method_returnConcatenated

		return "";

	}

	public boolean VerifyAllLink(String arg0) {

		System.out.println(">>Keyword Called VerifyAllLink");

		// Method_verifyAllLink

		return false;

	}

	public boolean VerifyDropDownToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyDropDownToolTip");

		// Method_verifyDropDownToolTip

		return false;

	}

	public boolean VerifyLinkEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyLinkEnabled");

		// Method_verifyLinkEnabled

		return false;

	}

	public boolean VerifyLinkDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyLinkDisabled");

		// Method_verifyLinkDisabled

		return false;

	}

	public boolean VerifyLinkVisible(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyLinkVisible");

		// Method_verifyLinkVisible

		return false;

	}

	public boolean WaitforLink(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitforLink");

		// Method_waitforLink

		return false;

	}

	public boolean VerifyImageVisible(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyImageVisible");

		// Method_verifyImageVisible

		return false;

	}

	public boolean VerifyImageNotVisible(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyImageNotVisible");

		// Method_verifyImageNotVisible

		return false;

	}

	public boolean VerifyImageExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyImageExist");

		// Method_verifyImageExist

		return false;

	}

	public boolean DoubleClickImage(ORObject arg0) {

		System.out.println(">>Keyword Called DoubleClickImage");

		// Method_doubleClickImage

		return false;

	}

	public boolean ClickImage(ORObject arg0) {

		System.out.println(">>Keyword Called ClickImage");

		// Method_clickImage

		return false;

	}

	public boolean VerifyLinkToolTip(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyLinkToolTip");

		// Method_verifyLinkToolTip

		return false;

	}

	public String GetPopupText(ORObject arg0, String arg1, String arg2) {

		System.out.println(">>Keyword Called GetPopupText");

		// Method_Getpopuptext

		return "";

	}

	public boolean KeyPressNative(String arg0) {

		System.out.println(">>Keyword Called KeyPressNative");

		// Method_keyPressNative

		return false;

	}

	public boolean Enter() {

		System.out.println(">>Keyword Called Enter");

		// Method_Enter

		return false;

	}

	public boolean WaitForEditBoxDisabled(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForEditBoxDisabled");

		// Method_waitForEditBoxDisabled

		return false;

	}

	public boolean WaitForEditBoxEnabled(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForEditBoxEnabled");

		// Method_waitForEditBoxEnabled

		return false;

	}

	public boolean VerifyRadioButtonExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyRadioButtonExist");

		// Method_verifyRadioButtonExist

		return false;

	}

	public boolean VerifyRadioButtonDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyRadioButtonDisabled");

		// Method_verifyRadioButtonDisabled

		return false;

	}

	public boolean VerifyRadioButtonEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyRadioButtonEnabled");

		// Method_verifyRadioButtonEnabled

		return false;

	}

	public boolean DeFocusRadioButton() {

		System.out.println(">>Keyword Called DeFocusRadioButton");

		// Method_deFocusRadioButton

		return false;

	}

	public boolean FocusRadioButton(ORObject arg0) {

		System.out.println(">>Keyword Called FocusRadioButton");

		// Method_focusRadioButton

		return false;

	}

	public boolean VerifyCheckBoxExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyCheckBoxExist");

		// Method_verifyCheckBoxExist

		return false;

	}

	public boolean VerifyCheckBoxDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyCheckBoxDisabled");

		// Method_verifyCheckBoxDisabled

		return false;

	}

	public boolean VerifyCheckBoxEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyCheckBoxEnabled");

		// Method_verifyCheckBoxEnabled

		return false;

	}

	public boolean DeFocusCheckBox() {

		System.out.println(">>Keyword Called DeFocusCheckBox");

		// Method_deFocusCheckBox

		return false;

	}

	public boolean FocusCheckBox(ORObject arg0) {

		System.out.println(">>Keyword Called FocusCheckBox");

		// Method_focusCheckBox

		return false;

	}

	public boolean VerifyDropDownSelection(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyDropDownSelection");

		// Method_verifyDropDownSelection

		return false;

	}

	public boolean VerifyDropDownExist(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyDropDownExist");

		// Method_verifyDropDownExist

		return false;

	}

	public boolean VerifyDropDownDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyDropDownDisabled");

		// Method_VerifyDropDownDisabled

		return false;

	}

	public boolean VerifyDropDownEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyDropDownEnabled");

		// Method_VerifyDropDownEnabled

		return false;

	}

	public boolean VerifyAllDropDownItemExist(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyAllDropDownItemExist");

		// Method_verifyAllDropDownItemExist

		return false;

	}

	public boolean VerifyMultipleDropDownItemExist(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyMultipleDropDownItemExist");

		// Method_verifyMultipleDropDownItemExist

		return false;

	}

	public boolean VerifyDropDownItemCount(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyDropDownItemCount");

		// Method_verifyDropDownItemCount

		return false;

	}

	public boolean DeFocusfromDropDown() {

		System.out.println(">>Keyword Called DeFocusfromDropDown");

		// Method_deFocusfromDropDown

		return false;

	}

	public boolean SetFocusonDropDown(ORObject arg0) {

		System.out.println(">>Keyword Called SetFocusonDropDown");

		// Method_SetFocusonDropDown

		return false;

	}

	public boolean VerifyEditBoxExistAndWait(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyEditBoxExistAndWait");

		// Method_verifyEditBoxExist

		return false;

	}

	public boolean VerifyEditBoxValue(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyEditBoxValue");

		// Method_verifyEditBoxValue

		return false;

	}

	public boolean VerifyEditBoxLength(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyEditBoxLength");

		// Method_verifyEditBoxLength

		return false;

	}

	public boolean VerifyEditBoxName(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyEditBoxName");

		// Method_verifyEditBoxName

		return false;

	}

	public boolean VerifyEditBoxNonEditable(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyEditBoxNonEditable");

		// Method_verifyEditBoxNonEditable

		return false;

	}

	public boolean VerifyEditBoxEditable(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyEditBoxEditable");

		// Method_verifyEditBoxEditable

		return false;

	}

	public boolean VerifyEditBoxDisabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyEditBoxDisabled");

		// Method_verifyEditBoxDisabled

		return false;

	}

	public boolean VerifyEditBoxEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called VerifyEditBoxEnabled");

		// Method_verifyEditBoxEnabled

		return false;

	}

	public boolean DeFocusEditField() {

		System.out.println(">>Keyword Called DeFocusEditField");

		// Method_deFocusEditField

		return false;

	}

	public boolean SetfocusEditField(ORObject arg0) {

		System.out.println(">>Keyword Called SetfocusEditField");

		// Method_SetfocusEditField

		return false;

	}

	public boolean VerifyTextareaColsRowLength(ORObject arg0, int arg1, int arg2) {

		System.out.println(">>Keyword Called VerifyTextareaColsRowLength");

		// Method_verifyTextareaColsRowLength

		return false;

	}

	public boolean VerifyBrowserTitle(String arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyBrowserTitle");

		// Method_VerifyBrowserTitle

		return false;

	}

	public boolean TypeSecureText(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called TypeSecureText");

		// Method_typeSecureText

		return false;

	}

	public boolean NextPageObject(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called NextPageObject");

		// Method_nextPageObject

		return false;

	}

	public boolean SelectGroupRadioButton(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called SelectGroupRadioButton");

		// Method_selectGroupRadioButton

		return false;

	}

	public boolean ReportMessage(String arg0, String arg1) {

		System.out.println(">>Keyword Called ReportMessage");

		// Method_reportMessage

		return false;

	}

	public int GetTableRowNumber(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called GetTableRowNumber");

		// Method_TableGetTextRow

		return 0;

	}

	public int GetTableColumnNumber(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called GetTableColumnNumber");

		// Method_TableGetTextColumn

		return 0;

	}

	public boolean MouseHover(ORObject arg0) {

		System.out.println(">>Keyword Called MouseHover");

		// Method_MouseHover

		return false;

	}

	public boolean NavigateTo(String arg0) {

		System.out.println(">>Keyword Called NavigateTo");

		// Method_navigateTo

		return false;

	}

	public boolean AssertTextPresent(String arg0) {

		System.out.println(">>Keyword Called AssertTextPresent");

		// Method_AssertTextPresent

		return false;

	}

	public boolean ClickAt(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called ClickAt");

		// Method_clickAt

		return false;

	}

	public String GetDropDownToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetDropDownToolTip");

		// Method_getDropDownToolTip

		return "";

	}

	public String GetEditBoxToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetEditBoxToolTip");

		// Method_getEditBoxToolTip

		return "";

	}

	public String GetTextAreaToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextAreaToolTip");

		// Method_getTextAreaToolTip

		return "";

	}

	public String GetButtonToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetButtonToolTip");

		// Method_getButtonToolTip

		return "";

	}

	public String GetCheckBoxToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetCheckBoxToolTip");

		// Method_getCheckBoxToolTip

		return "";

	}

	public String GetLinkToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetLinkToolTip");

		// Method_getLinkToolTip

		return "";

	}

	public String GetObjectToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetObjectToolTip");

		// Method_getObjectToolTip

		return "";

	}

	public String GetImageToolTip(ORObject arg0) {

		System.out.println(">>Keyword Called GetImageToolTip");

		// Method_getImageToolTip

		return "";

	}

	public boolean MinimizeBrowser() {

		System.out.println(">>Keyword Called MinimizeBrowser");

		// Method_MinimizeBrowser

		return false;

	}

	public int GetTextAreaLength(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextAreaLength");

		// Method_GetTextAreaLength

		return 0;

	}

	public String GetTextAreaColumnRowLength(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextAreaColumnRowLength");

		// Method_GetTextAreaColRowLength

		return "";

	}

	public String GetEditBoxName(ORObject arg0) {

		System.out.println(">>Keyword Called GetEditBoxName");

		// Method_GetEditBoxName

		return "";

	}

	public boolean AcceptPopup() {

		System.out.println(">>Keyword Called AcceptPopup");

		// Method_acceptPopup

		return false;

	}

	public boolean DismissPopup() {

		System.out.println(">>Keyword Called DismissPopup");

		// Method_dismissPopup

		return false;

	}

	public boolean VerifyPopupPresent(String arg0) {

		System.out.println(">>Keyword Called VerifyPopupPresent");

		// Method_verifyPopupPresent

		return false;

	}

	public boolean SelectFrame(ORObject arg0) {

		System.out.println(">>Keyword Called SelectFrame");

		// Method_selectFrame

		return false;

	}

	public boolean SwitchToDefaultContent() {

		System.out.println(">>Keyword Called SwitchToDefaultContent");

		// Method_switchToDefaultContent

		return false;

	}

	public String GetObjectValue(ORObject arg0) {

		System.out.println(">>Keyword Called GetObjectValue");

		// Method_getObjectValue

		return "";

	}

	public boolean VerifyObjectValue(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyObjectValue");

		// Method_VerifyObjectValue

		return false;

	}

	public boolean GetObjectVisibility(ORObject arg0) {

		System.out.println(">>Keyword Called GetObjectVisibility");

		// Method_getObjectVisibility

		return false;

	}

	public boolean GetObjectExistence(ORObject arg0) {

		System.out.println(">>Keyword Called GetObjectExistence");

		// Method_getObjectExistence

		return false;

	}

	public boolean GetObjectEnabled(ORObject arg0) {

		System.out.println(">>Keyword Called GetObjectEnabled");

		// Method_getObjectEnabled

		return false;

	}

	public int GetTableRowCount(ORObject arg0) {

		System.out.println(">>Keyword Called GetTableRowCount");

		// Method_getTableRowCount

		return 0;

	}

	public int GetTableColumnCount(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called GetTableColumnCount");

		// Method_getTableColCount

		return 0;

	}

	public boolean ExcelCompare(String arg0, String arg1, String arg2, String arg3) {

		System.out.println(">>Keyword Called ExcelCompare");

		// Method_excelCompare

		return false;

	}

	public boolean SetPage(ORObject arg0) {

		System.out.println(">>Keyword Called SetPage");

		// Method_setPage

		return false;

	}

	public String GetObjectCSSProperty(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called GetObjectCSSProperty");

		// Method_getObjectCSSProperty

		return "";

	}

	public boolean VerifyCheckboxStatusInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {

		System.out.println(">>Keyword Called VerifyCheckboxStatusInTableCell");

		// Method_verifyCheckboxStatusInTableCell

		return false;

	}

	public String GetCheckboxStatus(ORObject arg0) {

		System.out.println(">>Keyword Called GetCheckboxStatus");

		// Method_getCheckboxStatus

		return "";

	}

	public boolean SelectRadioButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {

		System.out.println(">>Keyword Called SelectRadioButtonInTableCell");

		// Method_selectRadioButtobTableCell

		return false;

	}

	public boolean RightClickOnObject(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called RightClickOnObject");

		// Method_rightClickAndSelect

		return false;

	}

	public String GetObjectProperty(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called GetObjectProperty");

		// Method_getObjectProperty

		return "";

	}

	public boolean SelectCheckBoxinTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {

		System.out.println(">>Keyword Called SelectCheckBoxinTableCell");

		// Method_selectCheckBoxinTableCell

		return false;

	}

	public boolean WaitForObjectVisible(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForObjectVisible");

		// Method_waitforobjectvisible

		return false;

	}

	public String GetTextAreavalue(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextAreavalue");

		// Method_getTextAreavalue

		return "";

	}

	public boolean WaitForObjectEditable(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForObjectEditable");

		// Method_waitForObjectEditable

		return false;

	}

	public boolean TypeTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {

		System.out.println(">>Keyword Called TypeTextInTableCell");

		// Method_typeTextInTableCell

		return false;

	}

	public boolean WaitForObjectEnable(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForObjectEnable");

		// Method_waitforobjectenable

		return false;

	}

	public boolean Swipe(double arg0, double arg1, double arg2, double arg3, double arg4) {

		System.out.println(">>Keyword Called Swipe");

		// Method_swipe

		return false;

	}

	public boolean Moblie_DeselectToggle(ORObject arg0) {

		System.out.println(">>Keyword Called Moblie_DeselectToggle");

		// Method_deSelectToggle

		return false;

	}

	public boolean SwipeLeft() {

		System.out.println(">>Keyword Called SwipeLeft");

		// Method_SwipeLeft

		return false;

	}

	public boolean SwipeRight() {

		System.out.println(">>Keyword Called SwipeRight");

		// Method_SwipeRight

		return false;

	}

	public String GetObjectHeightWidth(ORObject arg0) {

		System.out.println(">>Keyword Called GetObjectHeightWidth");

		// Method_getObjectHeightWidth

		return "";

	}

	public boolean SwipeObject(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called SwipeObject");

		// Method_swipeWithObject

		return false;

	}

	public boolean Swipe(int arg0, String arg1) {

		System.out.println(">>Keyword Called Swipe");

		// Method_MobilitySwipe

		return false;

	}

	public int GetObjectCount(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6,
			String arg7, String arg8, String arg9) {

		System.out.println(">>Keyword Called GetObjectCount");

		// Method_getObjectCount

		return 0;

	}

	public String GetObjectText(ORObject arg0, String arg1, String arg2) {

		System.out.println(">>Keyword Called GetObjectText");

		// Method_GetObjectText

		return "";

	}

	public boolean VerifyEditBoxExist() {

		System.out.println(">>Keyword Called VerifyEditBoxExist");

		// Method_verifyEditBoxExist

		return false;

	}

	public boolean ClickInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5,
			String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {

		System.out.println(">>Keyword Called ClickInTableCellByQuery");

		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {

		System.out.println(">>Keyword Called TypeTextInTableCellByQuery");

		// Method_typeTextInTableCellByQuery

		return false;

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {

		System.out.println(">>Keyword Called GetTextFromTableCellByQuery");

		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean Web_ClickByText(String arg0, int arg1, boolean arg2, ORObject arg3, String arg4, String arg5) {

		System.out.println(">>Keyword Called Web_ClickByText");

		// Method_clickByText

		return false;

	}

	public boolean Web_ClickByTextInSequence(String arg0, int arg1, int arg2, boolean arg3, String arg4, int arg5,
			boolean arg6, ORObject arg7, ORObject arg8, ORObject arg9, ORObject arg10, ORObject arg11, boolean arg12,
			String arg13, int arg14, boolean arg15, String arg16, int arg17, boolean arg18, String arg19) {

		System.out.println(">>Keyword Called Web_ClickByTextInSequence");

		// Method_clickByTextInSequence

		return false;

	}

	public boolean Web_SelectByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {

		System.out.println(">>Keyword Called Web_SelectByText");

		// Method_SelectByText

		return false;

	}

	public boolean Web_TypeByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, ORObject arg5) {

		System.out.println(">>Keyword Called Web_TypeByText");

		// Method_typeTextUsingText

		return false;

	}

	public boolean MouseHoverOnText(String arg0, int arg1, boolean arg2, ORObject arg3) {

		System.out.println(">>Keyword Called MouseHoverOnText");

		// Method_mouseHoverOnText

		return false;

	}

	public boolean ClickImageByTitleAlt(String arg0, int arg1, boolean arg2) {

		System.out.println(">>Keyword Called ClickImageByTitle/Alt");

		// Method_clickImageByAltText

		return false;

	}

	public boolean Web_SelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, String arg4,
			ORObject arg5) {

		System.out.println(">>Keyword Called Web_SelectCheckboxByText");

		// Method_selectCheckBoxByText

		return false;

	}

	public boolean Web_DeSelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {

		System.out.println(">>Keyword Called Web_DeSelectCheckboxByText");

		// Method_deSelectCheckBoxByText

		return false;

	}

	public boolean Web_SelectRadioButtonByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {

		System.out.println(">>Keyword Called Web_SelectRadioButtonByText");

		// Method_selectRadioButtonByText

		return false;

	}

	public boolean Web_SelectDropDownByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4,
			boolean arg5, ORObject arg6) {

		System.out.println(">>Keyword Called Web_SelectDropDownByText");

		// Method_selectDropDownByText

		return false;

	}

	public boolean Web_GoBack() {

		System.out.println(">>Keyword Called Web_GoBack");

		// Method_goBack

		return false;

	}

	public boolean Web_SelectListItem(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called Web_SelectListItem");

		// Method_selectListItem

		return false;

	}

	public boolean Web_VerifyListItemExists(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called Web_VerifyListItemExists");

		// Method_verifyListItemExists

		return false;

	}

	public String GetAllColumnText(ORObject arg0, String arg1, String arg2) {

		System.out.println(">>Keyword Called GetAllColumnText");

		// Method_getAllColText

		return "";

	}

	public String GetSingleTableColumnText(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called GetSingleTableColumnText");

		// Method_getSingleColText

		return "";

	}

	public String GetAllRowText(ORObject arg0, String arg1, String arg2) {

		System.out.println(">>Keyword Called GetAllRowText");

		// Method_getAllRowText

		return "";

	}

	public String GetSingleTableRowText(ORObject arg0, int arg1, String arg2) {

		System.out.println(">>Keyword Called GetSingleTableRowText");

		// Method_getSingleRowText

		return "";

	}

	public boolean SelectDropDownInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {

		System.out.println(">>Keyword Called SelectDropDownInTableCell");

		// Method_selectDropDownInTableCell

		return false;

	}

	public boolean DeSelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {

		System.out.println(">>Keyword Called DeSelectMultipleDropdownItemInTableCell");

		// Method_deSelectMultipleDropDownItemInTableCell

		return false;

	}

	public boolean SelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {

		System.out.println(">>Keyword Called SelectMultipleDropdownItemInTableCell");

		// Method_selectMultipleDropdownItemInTableCell

		return false;

	}

	public String GetSelectedDropDownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {

		System.out.println(">>Keyword Called GetSelectedDropDownItemInTableCell");

		// Method_getSelectedDropDownInTableCell

		return "";

	}

	public boolean DoubleClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {

		System.out.println(">>Keyword Called DoubleClickTableCell");

		// Method_doubleClickInTableCell

		return false;

	}

	public String FetchObjectPropertyInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4,
			String arg5) {

		System.out.println(">>Keyword Called FetchObjectPropertyInTableCell");

		// Method_fetchObjectPropertyInTableCell

		return "";

	}

	public boolean ClickOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {

		System.out.println(">>Keyword Called ClickOnObjectInTableCell");

		// Method_clickOnObjectInTableCell

		return false;

	}

	public boolean TypeOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {

		System.out.println(">>Keyword Called TypeOnObjectInTableCell");

		// Method_typeOnObjecttInTableCell

		return false;

	}

	public boolean WaitForObjectDisable(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called WaitForObjectDisable");

		// Method_waitForObjectdisable

		return false;

	}

	public String CaptureObjectSnapshot(ORObject arg0) {

		System.out.println(">>Keyword Called CaptureObjectSnapshot");

		// Method_captureObjectSnapShot

		return "";

	}

	public String GetEditboxDefaultvalue(ORObject arg0) {

		System.out.println(">>Keyword Called GetEditboxDefaultvalue");

		// Method_getEditboxDefaultvalue

		return "";

	}

	public int GetEditBoxLength(ORObject arg0) {

		System.out.println(">>Keyword Called GetEditBoxLength");

		// Method_getEditboxLength

		return 0;

	}

	public String GetEditboxValue(ORObject arg0) {

		System.out.println(">>Keyword Called GetEditboxValue");

		// Method_getEditboxValue

		return "";

	}

	public String GetTextAreaDefaultvalue(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextAreaDefaultvalue");

		// Method_getTextAreaDefaultvalue

		return "";

	}

	public String GetTextAreaName(ORObject arg0) {

		System.out.println(">>Keyword Called GetTextAreaName");

		// Method_getTextAreaName

		return "";

	}

	public boolean VerifyAllButtons(String arg0) {

		System.out.println(">>Keyword Called VerifyAllButtons");

		// Method_verifyAllButtons

		return false;

	}

	public int GetImageCount() {

		System.out.println(">>Keyword Called GetImageCount");

		// Method_getImageCount

		return 0;

	}

	public String GetDropdownDefaultItem(ORObject arg0) {

		System.out.println(">>Keyword Called GetDropdownDefaultItem");

		// Method_getDropDownDefaultValue

		return "";

	}

	public boolean VerifyChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3, int arg4) {

		System.out.println(">>Keyword Called VerifyChildObjectCount");

		// Method_verifyChildObjectCount

		return false;

	}

	public boolean VerifyFullTableText(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called VerifyFullTableText");

		// Method_verifyFullTableText

		return false;

	}

	public boolean VerifyTableColumnCount(ORObject arg0, int arg1, int arg2) {

		System.out.println(">>Keyword Called VerifyTableColumnCount");

		// Method_verifyTableColumnCount

		return false;

	}

	public boolean VerifyTableRowCount(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called VerifyTableRowCount");

		// Method_verifyTableRowCount

		return false;

	}

	public String GetTableColumnHeader(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called GetTableColumnHeader");

		// Method_getTableColumnHeader

		return "";

	}

	public boolean SetBrowserCapability(String arg0, String arg1, String arg2, String arg3) {

		System.out.println(">>Keyword Called SetBrowserCapability");

		// Method_SetBrowserCapability

		return false;

	}

	public String GetCompleteTableText(ORObject arg0) {

		System.out.println(">>Keyword Called GetCompleteTableText");

		// Method_getCompleteTableText

		return "";

	}

	public boolean DeFocusObject() {

		System.out.println(">>Keyword Called DeFocusObject");

		// Method_deFocusObject

		return false;

	}

	public boolean VerifyMultipleObjectProperty(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) {

		System.out.println(">>Keyword Called VerifyMultipleObjectProperty");

		// Method_VerifyMultipleObjectProperty

		return false;

	}

	public int Web_GetTableColumnCount(ORObject arg0, int arg1) {

		System.out.println(">>Keyword Called Web_GetTableColumnCount");

		// Method_WebGetTableColCount

		return 0;

	}

	public int Web_GetTableRowCount(ORObject arg0) {

		System.out.println(">>Keyword Called Web_GetTableRowCount");

		// Method_WebGetTableRowCount

		return 0;

	}

	public boolean Web_SetFocusOnCurrentWindow() {

		System.out.println(">>Keyword Called Web_SetFocusOnCurrentWindow");

		// Method_setFocusOnCurrentWindow

		return false;

	}

	public boolean Web_WaitForWindowLoad(String arg0, int arg1, boolean arg2, int arg3) {

		System.out.println(">>Keyword Called Web_WaitForWindowLoad");

		// Method_waitForWindowLoad

		return false;

	}

	public double GetLoadTime() {

		System.out.println(">>Keyword Called GetLoadTime");

		// Method_getLoadingTime

		return 0;

	}

	public boolean IgnoreXMLHttpRequest(String arg0) {

		System.out.println(">>Keyword Called IgnoreXMLHttpRequest");

		// Method_ignoreXMLHttpRequest

		return false;

	}

	public boolean SynchronizeBrowser(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {

		System.out.println(">>Keyword Called SynchronizeBrowser");

		// Method_syncBrowser

		return false;

	}

	public boolean TypeTextInPTag(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called TypeTextInPTag");

		// Method_TypeTextInContentEditable

		return false;

	}

	public String VisualComparisonForPage(String arg0, String arg1, boolean arg2, boolean arg3) {

		System.out.println(">>Keyword Called VisualComparisonForPage");

		// Custom_visualComparionForPage

		return "";

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, int arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {

		System.out.println(">>Keyword Called GetTextFromTableCellByQuery");

		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {

		System.out.println(">>Keyword Called TypeTextInTableCellByQuery");

		// Method_typeTextInTableCellByQuery

		return false;

	}

	public boolean ClickOnObjectInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, String arg8, int arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {

		System.out.println(">>Keyword Called ClickOnObjectInTableCellByQuery");

		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextAndEnterEditBox(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called TypeTextAndEnterEditBox");

		// Method_typeTextandEnterEditBox

		return false;

	}

	public boolean Web_ResizeBrowser(int arg0, int arg1) {

		System.out.println(">>Keyword Called Web_ResizeBrowser");

		// Method_setViewPort

		return false;

	}

	public boolean RightClickAndSelectByText(ORObject arg0, String arg1) {

		System.out.println(">>Keyword Called RightClickAndSelectByText");

		// Method_rightClickAndSelectByText

		return false;

	}

	public boolean IsTextPresentOnScreen(ORObject arg0) {

		System.out.println(">>Keyword Called IsTextPresentOnScreen");

		// Method_IsTextPresentOnScreen_Generic

		return false;

	}

}
