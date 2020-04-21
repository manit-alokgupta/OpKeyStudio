package com.opkey.OpKeyGenericPlugin;

import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.exceptionhandling.RetryKeywordAgainException;
import com.crestech.opkey.plugin.webdriver.exceptionhandlers.ToolNotSetException;
import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.crestech.opkey.plugin.webdriver.keywords.Checkbox;
import com.crestech.opkey.plugin.webdriver.keywords.Deprecate;
import com.crestech.opkey.plugin.webdriver.keywords.DropDown;
import com.crestech.opkey.plugin.webdriver.keywords.EditBox;
import com.crestech.opkey.plugin.webdriver.keywords.Links;
import com.crestech.opkey.plugin.webdriver.keywords.ListControl;
import com.crestech.opkey.plugin.webdriver.keywords.Radio;
import com.crestech.opkey.plugin.webdriver.keywords.Utils;
import com.crestech.opkey.plugin.webdriver.keywords.WebObjects;
import com.crestech.opkey.plugin.webdriver.keywords.Window;
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

		// Method_typeTextOnEditBox
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new EditBox().Method_typeTextOnEditBox(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean TypeKeysOnEditBox(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeKeysOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);

		// Method_typeKeysOnEditBox
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new EditBox().Method_typeKeysOnEditBox(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean VerifyEditBoxText(ORObject arg0, String arg1) {
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyeditboxtext
		try {
			new EditBox().Method_verifyeditboxtext(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean OpenBrowser(String arg0, String arg1) throws Exception {
		ContextInitiator.addFunction("OpenBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);

		// Method_WebBrowserOpen
		new Browser().Method_WebBrowserOpen(arg0, arg1);
		return false;

	}

	public boolean SelectDropDownItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("SelectDropDownItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);

		// Method_selectDropDownItem
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new DropDown().Method_selectDropDownItem(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean SelectCheckBox(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("SelectCheckBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectCheckBox
		try {
			new Checkbox().Method_selectCheckBox(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean SelectRadioButton(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectRadioButton");

		// Method_SelectRadio
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Radio().Method_SelectRadio(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean Click(ORObject arg0) throws Exception {
		ContextInitiator.addFunction("Click");

		// Method_ObjectClick
		try {
			new Utils().waitForPageLoadAndOtherAjax();
			WebDriverObject object = new ObjectConverter().formatObject(arg0);
			
			FunctionResult fr = new WebObjects().Method_ObjectClick(object);
			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof RetryKeywordAgainException) {
				Thread.sleep(1000);
				return Click(arg0);
			}
		}
		return false;
	}

	public boolean DoubleClick(ORObject arg0) {
		ContextInitiator.addFunction("DoubleClick");

		// Method_dblClick
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new WebObjects().Method_dblClick(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean RefreshBrowser() {
		ContextInitiator.addFunction("RefreshBrowser");

		// Method_RefreshBrowser
		try {
			new Browser().Method_RefreshBrowser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean MaximizeBrowser() {
		ContextInitiator.addFunction("MaximizeBrowser");

		// Method_MaximizeBrowser
		try {
			new Browser().Method_MaximizeBrowser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public String FetchBrowserURL() {
		ContextInitiator.addFunction("FetchBrowserURL");

		// Method_fetchBrowserURL
		try {
			FunctionResult fr = new Browser().Method_fetchBrowserURL();
			return fr.getOutput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	public String FetchBrowserTitle(String arg0) {
		ContextInitiator.addFunction("FetchBrowserTitle");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		// Method_fetchBrowserTitle
		try {
			FunctionResult fr = new Browser().Method_fetchBrowserTitle(arg0);
			return fr.getOutput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	public boolean GoBackAndWait(int arg0) {
		ContextInitiator.addFunction("GoBackAndWait");

		// Method_goBackAndWait
		try {
			new Deprecate().Method_goBackAndWait(arg0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean RefreshAndWait(int arg0) {
		ContextInitiator.addFunction("RefreshAndWait");

		// Method_refreshAndWait
		try {
			new Deprecate().Method_refreshAndWait(arg0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean ClearEditField(ORObject arg0) {
		ContextInitiator.addFunction("ClearEditField");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_clearEditField
		try {
			new EditBox().Method_clearEditField(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean TypeTextAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("TypeTextAndWait");

		// Method_typeTextAndWait
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Deprecate().Method_typeTextAndWait(object, arg1, arg2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean TypeKeysAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("TypeKeysAndWait");

		// Method_typeKeysAndWait
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Deprecate().Method_typeKeysAndWait(object, arg1, arg2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean VerifyEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditable");

		// Method_verifyEditable
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Deprecate().Method_verifyEditable(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean SelectMultipleDropDownItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("SelectMultipleDropDownItem");

		// Method_selectMultipleDropDownItem
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new ListControl().Method_selectMultipleDropDownItem(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean DeSelectDropDownItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("DeSelectDropDownItem");

		// Method_deselectDropDownItem
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new DropDown().Method_deselectDropDownItem(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean DeSelectMultipleDropDownItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("DeSelectMultipleDropDownItem");

		// Method_deselectMultipleDropDownItem
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new ListControl().Method_deselectMultipleDropDownItem(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean DeSelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("DeSelectDropDownItemAndWait");

		// Method_deselectDropDownItemAndWait
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Deprecate().Method_deselectDropDownItemAndWait(object, arg1, arg2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean DeSelectAllDropDownItemsAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("DeSelectAllDropDownItemsAndWait");

		// Method_deselectAllDropDownItemsAndWait
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Deprecate().Method_deselectAllDropDownItemsAndWait(object, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public int GetDropDownItemCount(ORObject arg0) {
		ContextInitiator.addFunction("GetDropDownItemCount");

		// Method_getDropDownItemCount
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new DropDown().Method_getDropDownItemCount(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public String GetSelectedDropDownItem(ORObject arg0) {
		ContextInitiator.addFunction("GetSelectedDropDownItem");

		// Method_getSelectedDropDownItem

		return "";

	}

	public boolean VerifyDropDownItemExists(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownItemExists");

		// Method_verifyDropDownItemExists

		return false;

	}

	public boolean DeSelectCheckBox(ORObject arg0) {
		ContextInitiator.addFunction("DeSelectCheckBox");

		// Method_deSelectCheckBox
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Checkbox().Method_deSelectCheckBox(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean verifyCheckBoxStatus(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("verifyCheckBoxStatus");

		// Method_verifyCheckBoxStatus

		return false;

	}

	public boolean SelectCheckBoxAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("SelectCheckBoxAndWait");

		// Method_selectCheckBoxAndWait

		return false;

	}

	public boolean DeSelectCheckBoxAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("DeSelectCheckBoxAndWait");

		// Method_deSelectCheckBoxAndWait

		return false;

	}

	public boolean SelectRadioButtonAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectRadioButtonAndWait");

		// Method_selectRadioButtonAndWait

		return false;

	}

	public boolean ClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("ClickTableCell");

		// Method_clickTableCell

		return false;

	}

	public String GetTableCellText(ORObject arg0, int arg1, int arg2, String arg3, String arg4) {
		ContextInitiator.addFunction("GetTableCellText");

		// Method_GetCellText

		return "";

	}

	public boolean SetFocus(ORObject arg0) {
		ContextInitiator.addFunction("SetFocus");

		// Method_SetFocus

		return false;

	}

	public boolean VerifyObjectExists(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectExists");

		// Method_ObjectExists

		return false;

	}

	public boolean VerifyObjectEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectEnabled");

		// Method_ObjectisEnabled

		return false;

	}

	public boolean VerifyObjectVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectVisible");

		// Method_verifyObjectVisible

		return false;

	}

	public boolean VerifyObjectText(ORObject arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("VerifyObjectText");

		// Method_ObjectTextVerification

		return false;

	}

	public boolean VerifyObjectPropertyValue(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("VerifyObjectPropertyValue");

		// Method_VerifyPropertyValue

		return false;

	}

	public boolean WaitForObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObject");

		// Method_waitforObject

		return false;

	}

	public boolean Wait(int arg0) {
		ContextInitiator.addFunction("Wait");

		// Method_wait

		return false;

	}

	public int GetChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("GetChildObjectCount");

		// Method_getChildObjectCount

		return 0;

	}

	public boolean DoubleClickAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("DoubleClickAndWait");

		// Method_doubleClickAndWait

		return false;

	}

	public boolean DoubleClickAt(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("DoubleClickAt");

		// Method_doubleClickAt

		return false;

	}

	public boolean DragAndDrop(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("DragAndDrop");

		// Method_dragAndDrop

		return false;

	}

	public boolean DragAndDropAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("DragAndDropAndWait");

		// Method_dragAndDropAndWait

		return false;

	}

	public String GetAllButtons() {
		ContextInitiator.addFunction("GetAllButtons");

		// Method_getAllButtons

		return "";

	}

	public String GetAllFields() {
		ContextInitiator.addFunction("GetAllFields");

		// Method_getAllFields

		return "";

	}

	public String GetAllLinks() {
		ContextInitiator.addFunction("GetAllLinks");

		// Method_getAllLinks

		return "";

	}

	public boolean CloseBrowser(String arg0) throws Exception {
		ContextInitiator.addFunction("CloseBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		// Method_CloseBrowser
		new Browser().Method_CloseBrowser(arg0);
		return false;

	}

	public boolean GoForward() {
		ContextInitiator.addFunction("GoForward");

		// Method_goForward

		return false;

	}

	public boolean GoForwardAndWait(int arg0) {
		ContextInitiator.addFunction("GoForwardAndWait");

		// Method_goForwardAndWait

		return false;

	}

	public boolean VerifyAllDropDownItems(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyAllDropDownItems");

		// Method_verifyAllDropDownItems

		return false;

	}

	public boolean SelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("SelectDropDownItemAndWait");

		// Method_selectDropDownItemAndWait

		return false;

	}

	public String GetFullTableText(ORObject arg0) {
		ContextInitiator.addFunction("GetFullTableText");
		

		// Method_getFullTableText

		return "";

	}

	public boolean VerifyTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, String arg4, String arg5) {
		ContextInitiator.addFunction("VerifyTextInTableCell");
		

		// Method_verifyTextInTable

		return false;

	}

	public boolean ClickTableCellAndWait(ORObject arg0, int arg1, int arg2, String arg3, int arg4, int arg5) {
		ContextInitiator.addFunction("ClickTableCellAndWait");
		

		// Method_clickTableCellAndWait

		return false;

	}

	public boolean WaitForObjectProperty(ORObject arg0, String arg1, String arg2, int arg3) {
		ContextInitiator.addFunction("WaitForObjectProperty");
		

		// Method_waitForObjectProperty

		return false;

	}

	public boolean HighlightObject(ORObject arg0) {
		ContextInitiator.addFunction("HighlightObject");
		

		// Method_highlightObject

		return false;

	}

	public boolean RunScriptAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("RunScriptAndWait");
		

		// Method_runScriptAndWait

		return false;

	}

	public String GetSelectedRadioButtonFromGroup(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetSelectedRadioButtonFromGroup");
		

		// Method_getSelectedRadioButtonFromGroup

		return "";

	}

	public boolean SyncBrowser() {
		ContextInitiator.addFunction("SyncBrowser");
		

		// Method_syncBrowser

		return false;

	}

	public boolean CloseAllBrowsers() throws Exception {
		ContextInitiator.addFunction("CloseAllBrowsers");
		

		// Method_CloseAllBrowsers
		new Browser().Method_CloseAllBrowsers();
		return false;

	}

	public String GetAllTitles(String arg0) {
		ContextInitiator.addFunction("GetAllTitles");
		

		// Method_GetAllTitles

		return "";

	}

	public boolean SelectWindow(String arg0, int arg1) {
		ContextInitiator.addFunction("SelectWindow");
		

		// Method_selectWindow
		try {
			new Window().Method_selectWindow(arg0, arg1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean CloseSelectedWindow(String arg0, int arg1) {
		ContextInitiator.addFunction("CloseSelectedWindow");
		

		// Method_closeSelectedWindow

		return false;

	}

	public boolean SetFocusOnWindow(int arg0) {
		ContextInitiator.addFunction("SetFocusOnWindow");
		

		// Method_setFocousOnWindow

		return false;

	}

	public String GetPropertyValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetPropertyValue");
		

		// Method_getPropertyValue

		return "";

	}

	public boolean VerifyBrowserExist(String arg0) {
		ContextInitiator.addFunction("VerifyBrowserExist");
		

		// Method_verifyBrowserExist

		return false;

	}

	public String GetTextFromEditBox(ORObject arg0) {
		ContextInitiator.addFunction("GetTextFromEditBox");
		

		// Method_getTextFromEditBox

		return "";

	}

	public boolean VerifyEditBoxDefaultValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxDefaultValue");
		

		// Method_verifyEditBoxDefaultValue

		return false;

	}

	public boolean VerifyEditBoxNotExist(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyEditBoxNotExist");
		

		// Method_verifyEditBoxnotExist

		return false;

	}

	public boolean VerifyEditBoxToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxToolTip");
		

		// Method_verifyEditBoxToolTip

		return false;

	}

	public boolean VerifyDropDownDefaultItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownDefaultItem");
		

		// Method_verifyDropDownDefaultItem

		return false;

	}

	public boolean ClickButton(ORObject arg0) {
		ContextInitiator.addFunction("ClickButton");
		

		// Method_clickButton
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		return false;

	}

	public boolean ClickButtonAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("ClickButtonAndWait");
		

		// Method_clickButtonAndWait

		return false;

	}

	public boolean VerifyRadioButtonSelected(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyRadioButtonSelected");
		

		// Method_VerifyRadioButtonSelected

		return false;

	}

	public boolean VerifyRadioButtonNotSelected(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyRadioButtonNotSelected");
		

		// Method_VerifyRadioButtonNotSelected

		return false;

	}

	public boolean VerifyCheckBoxToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyCheckBoxToolTip");
		

		// Method_verifyCheckBoxToolTip

		return false;

	}

	public boolean VerifyTextAreaText(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaText");
		

		// Method_verifyTextAreaText

		return false;

	}

	public boolean VerifyTextAreaEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaEnabled");
		

		// Method_verifyTextAreaEnabled

		return false;

	}

	public int GetRadioButtonCount(ORObject arg0) {
		ContextInitiator.addFunction("GetRadioButtonCount");
		

		// Method_getRadioButtonCount

		return 0;

	}

	public boolean TypeTextAndEnterTextArea(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextAndEnterTextArea");
		

		// Method_typeTextandEnterTextArea

		return false;

	}

	public boolean VerifyTextAreaDefaultValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaDefaultValue");
		

		// Method_verifyTextAreaDefaultValue

		return false;

	}

	public boolean VerifyTextAreaDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaDisabled");
		

		// Method_verifyTextAreaDisabled

		return false;

	}

	public boolean VerifyTextAreaEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaEditable");
		

		// Method_verifyTextAreaEditable

		return false;

	}

	public boolean VerifyTextAreaNotEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaNotEditable");
		

		// Method_verifyTextAreaNotEditable

		return false;

	}

	public boolean VerifyTextAreaToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaToolTip");
		

		// Method_verifyTextAreaToolTip

		return false;

	}

	public boolean VerifyTableColumnNumber(ORObject arg0, int arg1, String arg2, int arg3) {
		ContextInitiator.addFunction("VerifyTableColumnNumber");
		

		// Method_verifyTableColumnNumber

		return false;

	}

	public boolean VerifyTableColumnText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("VerifyTableColumnText");
		

		// Method_verifyTableColumnText

		return false;

	}

	public boolean VerifyTableRowText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("VerifyTableRowText");
		

		// Method_verifyTableRowText

		return false;

	}

	public boolean VerifyTableColumnHeader(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTableColumnHeader");
		

		// Method_verifyTableColumnHeader

		return false;

	}

	public boolean VerifyObjectToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyObjectToolTip");
		

		// Method_verifyObjectToolTip

		return false;

	}

	public boolean SelectMultipleDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("SelectMultipleDropDownItemAndWait");
		

		// Method_selectMultipleDropDownItemAndWait

		return false;

	}

	public boolean DoubleClickButton(ORObject arg0) {
		ContextInitiator.addFunction("DoubleClickButton");
		

		// Method_doubleClickButton

		return false;

	}

	public boolean FocusButton(ORObject arg0) {
		ContextInitiator.addFunction("FocusButton");
		

		// Method_focusButton

		return false;

	}

	public boolean DeFocusButton() {
		ContextInitiator.addFunction("DeFocusButton");
		

		// Method_deFocusButton

		return false;

	}

	public boolean VerifyButtonDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyButtonDisabled");
		

		// Method_verifyButtonDisabled

		return false;

	}

	public boolean VerifyButtonExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyButtonExist");
		

		// Method_verifyButtonExist

		return false;

	}

	public boolean SelectRadioButtonOnIndexBasis(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectRadioButtonOnIndexBasis");
		

		// Method_selectRadioButtonOnIndexBasis

		return false;

	}

	public boolean ClickLinkInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("ClickLinkInTableCell");
		

		// Method_clickLinkInTableCell

		return false;

	}

	public boolean TypeTextInTextArea(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextInTextArea");
		

		// Method_typeTextInTextArea

		return false;

	}

	public boolean TypeKeysInTextArea(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeKeysInTextArea");
		

		// Method_typeKeysInTextArea

		return false;

	}

	public boolean ClearTextArea(ORObject arg0) {
		ContextInitiator.addFunction("ClearTextArea");
		

		// Method_clearTextArea

		return false;

	}

	public boolean SetfocusTextArea(ORObject arg0) {
		ContextInitiator.addFunction("SetfocusTextArea");
		

		// Method_SetfocusTextArea

		return false;

	}

	public boolean DeFocusTextArea() {
		ContextInitiator.addFunction("DeFocusTextArea");
		

		// Method_deFocusTextArea

		return false;

	}

	public String GetTextfromTextArea(ORObject arg0) {
		ContextInitiator.addFunction("GetTextfromTextArea");
		

		// Method_GetTextfromTextArea

		return "";

	}

	public boolean VerifyTextAreaValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaValue");
		

		// Method_verifyTextAreaValue

		return false;

	}

	public boolean VerifyTextAreaExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaExist");
		

		// Method_verifyTextAreaExist

		return false;

	}

	public boolean VerifyTextAreaNotExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaNotExist");
		

		// Method_verifyTextAreanotExist

		return false;

	}

	public boolean VerifyTableRowNumber(ORObject arg0, int arg1, String arg2, int arg3) {
		ContextInitiator.addFunction("VerifyTableRowNumber");
		

		// Method_verifyTableRowNumber

		return false;

	}

	public boolean VerifyTextAreaName(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaName");
		

		// Method_verifyTextAreaName

		return false;

	}

	public boolean VerifyTextAreaLength(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyTextAreaLength");
		

		// Method_verifyTextAreaLength

		return false;

	}

	public int GetLinkCount() {
		ContextInitiator.addFunction("GetLinkCount");
		

		// Method_getLinkCount

		return 0;

	}

	public boolean VerifyLinkCount(int arg0) {
		ContextInitiator.addFunction("VerifyLinkCount");
		

		// Method_verifyLinkCount

		return false;

	}

	public boolean VerifyObjectDoesNotExists(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectDoesNotExists");
		

		// Method_verifyObjectdoesnotExists

		return false;

	}

	public boolean VerifyObjectDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectDisabled");
		

		// Method_verifyobjectDisabled

		return false;

	}

	public boolean PressTAB() {
		ContextInitiator.addFunction("PressTAB");
		

		// Method_PressTAB

		return false;

	}

	public boolean CaptureSnapshot(String arg0) {
		ContextInitiator.addFunction("CaptureSnapshot");
		

		// Method_CaptureSnapshot

		return false;

	}

	public boolean VerifyPopUpText(ORObject arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("VerifyPopUpText");
		

		// Method_VerifyPopUpText

		return false;

	}

	public boolean ClickLink(ORObject arg0) throws ToolNotSetException {
		ContextInitiator.addFunction("ClickLink");
		
		new Utils().waitForPageLoadAndOtherAjax();
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickLink
		try {
			new Links().Method_clickLink(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean VerifyLinkExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkExist");
		

		// Method_verifyLinkExist

		return false;

	}

	public boolean VerifyAllLinkExist(String arg0) {
		ContextInitiator.addFunction("VerifyAllLinkExist");
		

		// Method_verifyAllLinkExist

		return false;

	}

	public boolean VerifyButtonEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyButtonEnabled");
		

		// Method_verifyButtonEnabled

		return false;

	}

	public boolean VerifyButtonToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyButtonToolTip");
		

		// Method_verifyButtonToolTip

		return false;

	}

	public boolean ClickButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("ClickButtonInTableCell");
		

		// Method_clickButtonInTableCell

		return false;

	}

	public boolean VerifyImageCount(int arg0) {
		ContextInitiator.addFunction("VerifyImageCount");
		

		// Method_verifyImageCount

		return false;

	}

	public boolean VerifyImageEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageEnabled");
		

		// Method_verifyImageEnabled

		return false;

	}

	public boolean VerifyImageDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageDisabled");
		

		// Method_verifyImageDisabled

		return false;

	}

	public boolean VerifyImageToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyImageToolTip");
		

		// Method_verifyImageToolTip

		return false;

	}

	public boolean WaitforImageLoad(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitforImageLoad");
		

		// Method_waitforImageLoad

		return false;

	}

	public String CopyFromClipBoard() {
		ContextInitiator.addFunction("CopyFromClipBoard");
		

		// Method_copyFromClipBoard

		return "";

	}

	public boolean KeyLeft() {
		ContextInitiator.addFunction("KeyLeft");
		

		// Method_KeyLeft

		return false;

	}

	public boolean KeyRight() {
		ContextInitiator.addFunction("KeyRight");
		

		// Method_KeyRight

		return false;

	}

	public int GetElementIndex(ORObject arg0) {
		ContextInitiator.addFunction("GetElementIndex");
		

		// Method_GetElementIndex

		return 0;

	}

	public String ReturnConcatenated(String arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("ReturnConcatenated");
		

		// Method_returnConcatenated

		return "";

	}

	public boolean VerifyAllLink(String arg0) {
		ContextInitiator.addFunction("VerifyAllLink");
		

		// Method_verifyAllLink

		return false;

	}

	public boolean VerifyDropDownToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownToolTip");
		

		// Method_verifyDropDownToolTip

		return false;

	}

	public boolean VerifyLinkEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkEnabled");
		

		// Method_verifyLinkEnabled

		return false;

	}

	public boolean VerifyLinkDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkDisabled");
		

		// Method_verifyLinkDisabled

		return false;

	}

	public boolean VerifyLinkVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkVisible");
		

		// Method_verifyLinkVisible

		return false;

	}

	public boolean WaitforLink(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitforLink");
		

		// Method_waitforLink

		return false;

	}

	public boolean VerifyImageVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageVisible");
		

		// Method_verifyImageVisible

		return false;

	}

	public boolean VerifyImageNotVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageNotVisible");
		

		// Method_verifyImageNotVisible

		return false;

	}

	public boolean VerifyImageExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageExist");
		

		// Method_verifyImageExist

		return false;

	}

	public boolean DoubleClickImage(ORObject arg0) {
		ContextInitiator.addFunction("DoubleClickImage");
		

		// Method_doubleClickImage

		return false;

	}

	public boolean ClickImage(ORObject arg0) {
		ContextInitiator.addFunction("ClickImage");
		

		// Method_clickImage

		return false;

	}

	public boolean VerifyLinkToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyLinkToolTip");
		

		// Method_verifyLinkToolTip

		return false;

	}

	public String GetPopupText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetPopupText");
		

		// Method_Getpopuptext

		return "";

	}

	public boolean KeyPressNative(String arg0) {
		ContextInitiator.addFunction("KeyPressNative");
		

		// Method_keyPressNative

		return false;

	}

	public boolean Enter() {
		ContextInitiator.addFunction("Enter");
		

		// Method_Enter

		return false;

	}

	public boolean WaitForEditBoxDisabled(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForEditBoxDisabled");
		

		// Method_waitForEditBoxDisabled

		return false;

	}

	public boolean WaitForEditBoxEnabled(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForEditBoxEnabled");
		

		// Method_waitForEditBoxEnabled

		return false;

	}

	public boolean VerifyRadioButtonExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyRadioButtonExist");
		

		// Method_verifyRadioButtonExist

		return false;

	}

	public boolean VerifyRadioButtonDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyRadioButtonDisabled");
		

		// Method_verifyRadioButtonDisabled

		return false;

	}

	public boolean VerifyRadioButtonEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyRadioButtonEnabled");
		

		// Method_verifyRadioButtonEnabled

		return false;

	}

	public boolean DeFocusRadioButton() {
		ContextInitiator.addFunction("DeFocusRadioButton");
		

		// Method_deFocusRadioButton

		return false;

	}

	public boolean FocusRadioButton(ORObject arg0) {
		ContextInitiator.addFunction("FocusRadioButton");
		

		// Method_focusRadioButton

		return false;

	}

	public boolean VerifyCheckBoxExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyCheckBoxExist");
		

		// Method_verifyCheckBoxExist

		return false;

	}

	public boolean VerifyCheckBoxDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyCheckBoxDisabled");
		

		// Method_verifyCheckBoxDisabled

		return false;

	}

	public boolean VerifyCheckBoxEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyCheckBoxEnabled");
		

		// Method_verifyCheckBoxEnabled

		return false;

	}

	public boolean DeFocusCheckBox() {
		ContextInitiator.addFunction("DeFocusCheckBox");
		

		// Method_deFocusCheckBox

		return false;

	}

	public boolean FocusCheckBox(ORObject arg0) {
		ContextInitiator.addFunction("FocusCheckBox");
		

		// Method_focusCheckBox

		return false;

	}

	public boolean VerifyDropDownSelection(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownSelection");
		

		// Method_verifyDropDownSelection

		return false;

	}

	public boolean VerifyDropDownExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyDropDownExist");
		

		// Method_verifyDropDownExist

		return false;

	}

	public boolean VerifyDropDownDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyDropDownDisabled");
		

		// Method_VerifyDropDownDisabled

		return false;

	}

	public boolean VerifyDropDownEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyDropDownEnabled");
		

		// Method_VerifyDropDownEnabled

		return false;

	}

	public boolean VerifyAllDropDownItemExist(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyAllDropDownItemExist");
		

		// Method_verifyAllDropDownItemExist

		return false;

	}

	public boolean VerifyMultipleDropDownItemExist(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyMultipleDropDownItemExist");
		

		// Method_verifyMultipleDropDownItemExist

		return false;

	}

	public boolean VerifyDropDownItemCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyDropDownItemCount");
		

		// Method_verifyDropDownItemCount

		return false;

	}

	public boolean DeFocusfromDropDown() {
		ContextInitiator.addFunction("DeFocusfromDropDown");
		

		// Method_deFocusfromDropDown

		return false;

	}

	public boolean SetFocusonDropDown(ORObject arg0) {
		ContextInitiator.addFunction("SetFocusonDropDown");
		

		// Method_SetFocusonDropDown

		return false;

	}

	public boolean VerifyEditBoxExistAndWait(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxExistAndWait");
		

		// Method_verifyEditBoxExist

		return false;

	}

	public boolean VerifyEditBoxValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxValue");
		

		// Method_verifyEditBoxValue

		return false;

	}

	public boolean VerifyEditBoxLength(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyEditBoxLength");
		

		// Method_verifyEditBoxLength

		return false;

	}

	public boolean VerifyEditBoxName(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxName");
		

		// Method_verifyEditBoxName

		return false;

	}

	public boolean VerifyEditBoxNonEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxNonEditable");
		

		// Method_verifyEditBoxNonEditable

		return false;

	}

	public boolean VerifyEditBoxEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxEditable");
		

		// Method_verifyEditBoxEditable

		return false;

	}

	public boolean VerifyEditBoxDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxDisabled");
		

		// Method_verifyEditBoxDisabled

		return false;

	}

	public boolean VerifyEditBoxEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxEnabled");
		

		// Method_verifyEditBoxEnabled

		return false;

	}

	public boolean DeFocusEditField() {
		ContextInitiator.addFunction("DeFocusEditField");
		

		// Method_deFocusEditField

		return false;

	}

	public boolean SetfocusEditField(ORObject arg0) {
		ContextInitiator.addFunction("SetfocusEditField");
		

		// Method_SetfocusEditField

		return false;

	}

	public boolean VerifyTextareaColsRowLength(ORObject arg0, int arg1, int arg2) {
		ContextInitiator.addFunction("VerifyTextareaColsRowLength");
		

		// Method_verifyTextareaColsRowLength

		return false;

	}

	public boolean VerifyBrowserTitle(String arg0, String arg1) {
		ContextInitiator.addFunction("VerifyBrowserTitle");
		

		// Method_VerifyBrowserTitle

		return false;

	}

	public boolean TypeSecureText(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeSecureText");
		

		// Method_typeSecureText

		return false;

	}

	public boolean NextPageObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("NextPageObject");
		

		// Method_nextPageObject

		return false;

	}

	public boolean SelectGroupRadioButton(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectGroupRadioButton");
		

		// Method_selectGroupRadioButton

		return false;

	}

	public boolean ReportMessage(String arg0, String arg1) {
		ContextInitiator.addFunction("ReportMessage");
		

		// Method_reportMessage

		return false;

	}

	public int GetTableRowNumber(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetTableRowNumber");
		

		// Method_TableGetTextRow

		return 0;

	}

	public int GetTableColumnNumber(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetTableColumnNumber");
		

		// Method_TableGetTextColumn

		return 0;

	}

	public boolean MouseHover(ORObject arg0) {
		ContextInitiator.addFunction("MouseHover");
		

		// Method_MouseHover

		return false;

	}

	public boolean NavigateTo(String arg0) {
		ContextInitiator.addFunction("NavigateTo");
		

		// Method_navigateTo

		return false;

	}

	public boolean AssertTextPresent(String arg0) {
		ContextInitiator.addFunction("AssertTextPresent");
		

		// Method_AssertTextPresent

		return false;

	}

	public boolean ClickAt(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("ClickAt");
		

		// Method_clickAt

		return false;

	}

	public String GetDropDownToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetDropDownToolTip");
		

		// Method_getDropDownToolTip

		return "";

	}

	public String GetEditBoxToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxToolTip");
		

		// Method_getEditBoxToolTip

		return "";

	}

	public String GetTextAreaToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaToolTip");
		

		// Method_getTextAreaToolTip

		return "";

	}

	public String GetButtonToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetButtonToolTip");
		

		// Method_getButtonToolTip

		return "";

	}

	public String GetCheckBoxToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetCheckBoxToolTip");
		

		// Method_getCheckBoxToolTip

		return "";

	}

	public String GetLinkToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetLinkToolTip");
		

		// Method_getLinkToolTip

		return "";

	}

	public String GetObjectToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectToolTip");
		

		// Method_getObjectToolTip

		return "";

	}

	public String GetImageToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetImageToolTip");
		

		// Method_getImageToolTip

		return "";

	}

	public boolean MinimizeBrowser() {
		ContextInitiator.addFunction("MinimizeBrowser");
		

		// Method_MinimizeBrowser

		return false;

	}

	public int GetTextAreaLength(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaLength");
		

		// Method_GetTextAreaLength

		return 0;

	}

	public String GetTextAreaColumnRowLength(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaColumnRowLength");
		

		// Method_GetTextAreaColRowLength

		return "";

	}

	public String GetEditBoxName(ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxName");
		

		// Method_GetEditBoxName

		return "";

	}

	public boolean AcceptPopup() {
		ContextInitiator.addFunction("AcceptPopup");
		

		// Method_acceptPopup

		return false;

	}

	public boolean DismissPopup() {
		ContextInitiator.addFunction("DismissPopup");
		

		// Method_dismissPopup

		return false;

	}

	public boolean VerifyPopupPresent(String arg0) {
		ContextInitiator.addFunction("VerifyPopupPresent");
		

		// Method_verifyPopupPresent

		return false;

	}

	public boolean SelectFrame(ORObject arg0) {
		ContextInitiator.addFunction("SelectFrame");
		

		// Method_selectFrame

		return false;

	}

	public boolean SwitchToDefaultContent() {
		ContextInitiator.addFunction("SwitchToDefaultContent");
		

		// Method_switchToDefaultContent

		return false;

	}

	public String GetObjectValue(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectValue");
		

		// Method_getObjectValue

		return "";

	}

	public boolean VerifyObjectValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyObjectValue");
		

		// Method_VerifyObjectValue

		return false;

	}

	public boolean GetObjectVisibility(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectVisibility");
		

		// Method_getObjectVisibility

		return false;

	}

	public boolean GetObjectExistence(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectExistence");
		

		// Method_getObjectExistence

		return false;

	}

	public boolean GetObjectEnabled(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectEnabled");
		

		// Method_getObjectEnabled

		return false;

	}

	public int GetTableRowCount(ORObject arg0) {
		ContextInitiator.addFunction("GetTableRowCount");
		

		// Method_getTableRowCount

		return 0;

	}

	public int GetTableColumnCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetTableColumnCount");
		

		// Method_getTableColCount

		return 0;

	}

	public boolean ExcelCompare(String arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("ExcelCompare");
		

		// Method_excelCompare

		return false;

	}

	public boolean SetPage(ORObject arg0) {
		ContextInitiator.addFunction("SetPage");
		

		// Method_setPage

		return false;

	}

	public String GetObjectCSSProperty(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetObjectCSSProperty");
		

		// Method_getObjectCSSProperty

		return "";

	}

	public boolean VerifyCheckboxStatusInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("VerifyCheckboxStatusInTableCell");
		

		// Method_verifyCheckboxStatusInTableCell

		return false;

	}

	public String GetCheckboxStatus(ORObject arg0) {
		ContextInitiator.addFunction("GetCheckboxStatus");
		

		// Method_getCheckboxStatus

		return "";

	}

	public boolean SelectRadioButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("SelectRadioButtonInTableCell");
		

		// Method_selectRadioButtobTableCell

		return false;

	}

	public boolean RightClickOnObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("RightClickOnObject");
		

		// Method_rightClickAndSelect

		return false;

	}

	public String GetObjectProperty(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetObjectProperty");
		

		// Method_getObjectProperty

		return "";

	}

	public boolean SelectCheckBoxinTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectCheckBoxinTableCell");
		

		// Method_selectCheckBoxinTableCell

		return false;

	}

	public boolean WaitForObjectVisible(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectVisible");
		

		// Method_waitforobjectvisible

		return false;

	}

	public String GetTextAreavalue(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreavalue");
		

		// Method_getTextAreavalue

		return "";

	}

	public boolean WaitForObjectEditable(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectEditable");
		

		// Method_waitForObjectEditable

		return false;

	}

	public boolean TypeTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
		ContextInitiator.addFunction("TypeTextInTableCell");
		

		// Method_typeTextInTableCell

		return false;

	}

	public boolean WaitForObjectEnable(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectEnable");
		

		// Method_waitforobjectenable

		return false;

	}

	public boolean Swipe(double arg0, double arg1, double arg2, double arg3, double arg4) {
		ContextInitiator.addFunction("Swipe");
		

		// Method_swipe

		return false;

	}

	public boolean SwipeLeft() {
		ContextInitiator.addFunction("SwipeLeft");
		

		// Method_SwipeLeft

		return false;

	}

	public boolean SwipeRight() {
		ContextInitiator.addFunction("SwipeRight");
		

		// Method_SwipeRight

		return false;

	}

	public String GetObjectHeightWidth(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectHeightWidth");
		

		// Method_getObjectHeightWidth

		return "";

	}

	public boolean SwipeObject(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("SwipeObject");
		

		// Method_swipeWithObject

		return false;

	}

	public boolean Swipe(int arg0, String arg1) {
		ContextInitiator.addFunction("Swipe");
		

		// Method_MobilitySwipe

		return false;

	}

	public int GetObjectCount(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6,
			String arg7, String arg8, String arg9) {
		ContextInitiator.addFunction("GetObjectCount");
		

		// Method_getObjectCount

		return 0;

	}

	public String GetObjectText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetObjectText");
		

		// Method_GetObjectText

		return "";

	}

	public boolean VerifyEditBoxExist() {
		ContextInitiator.addFunction("VerifyEditBoxExist");
		

		// Method_verifyEditBoxExist

		return false;

	}

	public boolean ClickInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5,
			String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
		ContextInitiator.addFunction("ClickInTableCellByQuery");
		

		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {
		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		

		// Method_typeTextInTableCellByQuery

		return false;

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
		ContextInitiator.addFunction("GetTextFromTableCellByQuery");
		

		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean Web_ClickByText(String arg0, int arg1, boolean arg2, ORObject arg3, String arg4, String arg5) {
		ContextInitiator.addFunction("Web_ClickByText");
		

		// Method_clickByText

		return false;

	}

	public boolean Web_ClickByTextInSequence(String arg0, int arg1, int arg2, boolean arg3, String arg4, int arg5,
			boolean arg6, ORObject arg7, ORObject arg8, ORObject arg9, ORObject arg10, ORObject arg11, boolean arg12,
			String arg13, int arg14, boolean arg15, String arg16, int arg17, boolean arg18, String arg19) {
		ContextInitiator.addFunction("Web_ClickByTextInSequence");
		

		// Method_clickByTextInSequence

		return false;

	}

	public boolean Web_SelectByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction("Web_SelectByText");
		

		// Method_SelectByText

		return false;

	}

	public boolean Web_TypeByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, ORObject arg5) {
		ContextInitiator.addFunction("Web_TypeByText");
		

		// Method_typeTextUsingText

		return false;

	}

	public boolean MouseHoverOnText(String arg0, int arg1, boolean arg2, ORObject arg3) {
		ContextInitiator.addFunction("MouseHoverOnText");
		

		// Method_mouseHoverOnText

		return false;

	}

	public boolean ClickImageByTitleAlt(String arg0, int arg1, boolean arg2) {
		ContextInitiator.addFunction("ClickImageByTitleAlt");
		

		// Method_clickImageByAltText

		return false;

	}

	public boolean Web_SelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, String arg4,
			ORObject arg5) {
		ContextInitiator.addFunction("Web_SelectCheckboxByText");
		

		// Method_selectCheckBoxByText

		return false;

	}

	public boolean Web_DeSelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction("Web_DeSelectCheckboxByText");
		

		// Method_deSelectCheckBoxByText

		return false;

	}

	public boolean Web_SelectRadioButtonByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction("Web_SelectRadioButtonByText");
		

		// Method_selectRadioButtonByText

		return false;

	}

	public boolean Web_SelectDropDownByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4,
			boolean arg5, ORObject arg6) {
		ContextInitiator.addFunction("Web_SelectDropDownByText");
		

		// Method_selectDropDownByText

		return false;

	}

	public boolean Web_GoBack() {
		ContextInitiator.addFunction("Web_GoBack");
		

		// Method_goBack

		return false;

	}

	public boolean Web_SelectListItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("Web_SelectListItem");
		

		// Method_selectListItem

		return false;

	}

	public boolean Web_VerifyListItemExists(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("Web_VerifyListItemExists");
		

		// Method_verifyListItemExists

		return false;

	}

	public String GetAllColumnText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetAllColumnText");
		

		// Method_getAllColText

		return "";

	}

	public String GetSingleTableColumnText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetSingleTableColumnText");
		

		// Method_getSingleColText

		return "";

	}

	public String GetAllRowText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetAllRowText");
		

		// Method_getAllRowText

		return "";

	}

	public String GetSingleTableRowText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetSingleTableRowText");
		

		// Method_getSingleRowText

		return "";

	}

	public boolean SelectDropDownInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectDropDownInTableCell");
		

		// Method_selectDropDownInTableCell

		return false;

	}

	public boolean DeSelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("DeSelectMultipleDropdownItemInTableCell");
		

		// Method_deSelectMultipleDropDownItemInTableCell

		return false;

	}

	public boolean SelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectMultipleDropdownItemInTableCell");
		

		// Method_selectMultipleDropdownItemInTableCell

		return false;

	}

	public String GetSelectedDropDownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("GetSelectedDropDownItemInTableCell");
		

		// Method_getSelectedDropDownInTableCell

		return "";

	}

	public boolean DoubleClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("DoubleClickTableCell");
		

		// Method_doubleClickInTableCell

		return false;

	}

	public String FetchObjectPropertyInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4,
			String arg5) {
		ContextInitiator.addFunction("FetchObjectPropertyInTableCell");
		

		// Method_fetchObjectPropertyInTableCell

		return "";

	}

	public boolean ClickOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("ClickOnObjectInTableCell");
		

		// Method_clickOnObjectInTableCell

		return false;

	}

	public boolean TypeOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
		ContextInitiator.addFunction("TypeOnObjectInTableCell");
		

		// Method_typeOnObjecttInTableCell

		return false;

	}

	public boolean WaitForObjectDisable(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectDisable");
		

		// Method_waitForObjectdisable

		return false;

	}

	public String CaptureObjectSnapshot(ORObject arg0) {
		ContextInitiator.addFunction("CaptureObjectSnapshot");
		

		// Method_captureObjectSnapShot

		return "";

	}

	public String GetEditboxDefaultvalue(ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxDefaultvalue");
		

		// Method_getEditboxDefaultvalue

		return "";

	}

	public int GetEditBoxLength(ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxLength");
		

		// Method_getEditboxLength

		return 0;

	}

	public String GetEditboxValue(ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxValue");
		

		// Method_getEditboxValue

		return "";

	}

	public String GetTextAreaDefaultvalue(ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxValue");
		

		// Method_getTextAreaDefaultvalue

		return "";

	}

	public String GetTextAreaName(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaName");
		

		// Method_getTextAreaName

		return "";

	}

	public boolean VerifyAllButtons(String arg0) {
		ContextInitiator.addFunction("VerifyAllButtons");
		

		// Method_verifyAllButtons

		return false;

	}

	public int GetImageCount() {
		ContextInitiator.addFunction("GetImageCount");
		

		// Method_getImageCount

		return 0;

	}

	public String GetDropdownDefaultItem(ORObject arg0) {
		ContextInitiator.addFunction("GetDropdownDefaultItem");
		

		// Method_getDropDownDefaultValue

		return "";

	}

	public boolean VerifyChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("VerifyChildObjectCount");
		

		// Method_verifyChildObjectCount

		return false;

	}

	public boolean VerifyFullTableText(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyFullTableText");
		

		// Method_verifyFullTableText

		return false;

	}

	public boolean VerifyTableColumnCount(ORObject arg0, int arg1, int arg2) {
		ContextInitiator.addFunction("VerifyTableColumnCount");
		

		// Method_verifyTableColumnCount

		return false;

	}

	public boolean VerifyTableRowCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyTableRowCount");
		

		// Method_verifyTableRowCount

		return false;

	}

	public String GetTableColumnHeader(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetTableColumnHeader");
		

		// Method_getTableColumnHeader

		return "";

	}

	public boolean SetBrowserCapability(String arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("SetBrowserCapability");
		

		// Method_SetBrowserCapability

		return false;

	}

	public String GetCompleteTableText(ORObject arg0) {
		ContextInitiator.addFunction("GetCompleteTableText");
		

		// Method_getCompleteTableText

		return "";

	}

	public boolean DeFocusObject() {
		ContextInitiator.addFunction("DeFocusObject");
		

		// Method_deFocusObject

		return false;

	}

	public boolean VerifyMultipleObjectProperty(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) {
		ContextInitiator.addFunction("VerifyMultipleObjectProperty");
		

		// Method_VerifyMultipleObjectProperty

		return false;

	}

	public int Web_GetTableColumnCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("Web_GetTableColumnCount");
		

		// Method_WebGetTableColCount

		return 0;

	}

	public int Web_GetTableRowCount(ORObject arg0) {
		ContextInitiator.addFunction("Web_GetTableRowCount");
		

		// Method_WebGetTableRowCount

		return 0;

	}

	public boolean Web_SetFocusOnCurrentWindow() {
		ContextInitiator.addFunction("Web_SetFocusOnCurrentWindow");
		

		// Method_setFocusOnCurrentWindow

		return false;

	}

	public boolean Web_WaitForWindowLoad(String arg0, int arg1, boolean arg2, int arg3) {
		ContextInitiator.addFunction("Web_WaitForWindowLoad");
		

		// Method_waitForWindowLoad

		return false;

	}

	public double GetLoadTime() {
		ContextInitiator.addFunction("GetLoadTime");
		

		// Method_getLoadingTime

		return 0;

	}

	public boolean IgnoreXMLHttpRequest(String arg0) {
		ContextInitiator.addFunction("IgnoreXMLHttpRequest");
		

		// Method_ignoreXMLHttpRequest

		return false;

	}

	public boolean SynchronizeBrowser(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {
		ContextInitiator.addFunction("SynchronizeBrowser");
		

		// Method_syncBrowser

		return false;

	}

	public boolean TypeTextInPTag(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextInPTag");
		

		// Method_TypeTextInContentEditable

		return false;

	}

	public String VisualComparisonForPage(String arg0, String arg1, boolean arg2, boolean arg3) {
		ContextInitiator.addFunction("VisualComparisonForPage");
		

		// Custom_visualComparionForPage

		return "";

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, int arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {
		ContextInitiator.addFunction("GetTextFromTableCellByQuery");
		

		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {
		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		

		// Method_typeTextInTableCellByQuery

		return false;

	}

	public boolean ClickOnObjectInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, String arg8, int arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {
		ContextInitiator.addFunction("ClickOnObjectInTableCellByQuery");
		

		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextAndEnterEditBox(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextAndEnterEditBox");
		

		// Method_typeTextandEnterEditBox

		return false;

	}

	public boolean Web_ResizeBrowser(int arg0, int arg1) {
		ContextInitiator.addFunction("Web_ResizeBrowser");
		

		// Method_setViewPort

		return false;

	}

	public boolean RightClickAndSelectByText(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("RightClickAndSelectByText");
		

		// Method_rightClickAndSelectByText

		return false;

	}

	public boolean IsTextPresentOnScreen(ORObject arg0) {
		ContextInitiator.addFunction("IsTextPresentOnScreen");
		

		// Method_IsTextPresentOnScreen_Generic

		return false;

	}

}
