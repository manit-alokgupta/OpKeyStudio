package com.opkey.OpKeyGenericPlugin;

import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.exceptionhandling.RetryKeywordAgainException;
import com.crestech.opkey.plugin.webdriver.exceptionhandlers.ToolNotSetException;
import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.crestech.opkey.plugin.webdriver.keywords.Button;
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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_getSelectedDropDownItem

		return "";

	}

	public boolean VerifyDropDownItemExists(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownItemExists");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyCheckBoxStatus

		return false;

	}

	public boolean SelectCheckBoxAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("SelectCheckBoxAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectCheckBoxAndWait

		return false;

	}

	public boolean DeSelectCheckBoxAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("DeSelectCheckBoxAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_deSelectCheckBoxAndWait

		return false;

	}

	public boolean SelectRadioButtonAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectRadioButtonAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectRadioButtonAndWait

		return false;

	}

	public boolean ClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("ClickTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_clickTableCell

		return false;

	}

	public String GetTableCellText(ORObject arg0, int arg1, int arg2, String arg3, String arg4) {
		ContextInitiator.addFunction("GetTableCellText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_GetCellText

		return "";

	}

	public boolean SetFocus(ORObject arg0) {
		ContextInitiator.addFunction("SetFocus");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_SetFocus

		return false;

	}

	public boolean VerifyObjectExists(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectExists");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectExists

		return false;

	}

	public boolean VerifyObjectEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectisEnabled

		return false;

	}

	public boolean VerifyObjectVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectVisible");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyObjectVisible

		return false;

	}

	public boolean VerifyObjectText(ORObject arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("VerifyObjectText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_ObjectTextVerification

		return false;

	}

	public boolean VerifyObjectPropertyValue(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("VerifyObjectPropertyValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_VerifyPropertyValue

		return false;

	}

	public boolean WaitForObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObject");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_getChildObjectCount

		return 0;

	}

	public boolean DoubleClickAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("DoubleClickAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickAndWait

		return false;

	}

	public boolean DoubleClickAt(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("DoubleClickAt");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_doubleClickAt

		return false;

	}

	public boolean DragAndDrop(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("DragAndDrop");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_dragAndDrop

		return false;

	}

	public boolean DragAndDropAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("DragAndDropAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyAllDropDownItems

		return false;

	}

	public boolean SelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("SelectDropDownItemAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_selectDropDownItemAndWait

		return false;

	}

	public String GetFullTableText(ORObject arg0) {
		ContextInitiator.addFunction("GetFullTableText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getFullTableText

		return "";

	}

	public boolean VerifyTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, String arg4, String arg5) {
		ContextInitiator.addFunction("VerifyTextInTableCell");

		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		// Method_verifyTextInTable

		return false;

	}

	public boolean ClickTableCellAndWait(ORObject arg0, int arg1, int arg2, String arg3, int arg4, int arg5) {
		ContextInitiator.addFunction("ClickTableCellAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickTableCellAndWait

		return false;

	}

	public boolean WaitForObjectProperty(ORObject arg0, String arg1, String arg2, int arg3) {
		ContextInitiator.addFunction("WaitForObjectProperty");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitForObjectProperty

		return false;

	}

	public boolean HighlightObject(ORObject arg0) {
		ContextInitiator.addFunction("HighlightObject");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_highlightObject

		return false;

	}

	public boolean RunScriptAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("RunScriptAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_runScriptAndWait

		return false;

	}

	public String GetSelectedRadioButtonFromGroup(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetSelectedRadioButtonFromGroup");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTextFromEditBox

		return "";

	}

	public boolean VerifyEditBoxDefaultValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxDefaultValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxDefaultValue

		return false;

	}

	public boolean VerifyEditBoxNotExist(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyEditBoxNotExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxnotExist

		return false;

	}

	public boolean VerifyEditBoxToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxToolTip

		return false;

	}

	public boolean VerifyDropDownDefaultItem(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownDefaultItem");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyDropDownDefaultItem

		return false;

	}

	public boolean ClickButton(ORObject arg0) {
		ContextInitiator.addFunction("ClickButton");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);
		try {
			new Button().Method_clickButton(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Method_clickButton
		return false;

	}

	public boolean ClickButtonAndWait(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("ClickButtonAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickButtonAndWait

		return false;

	}

	public boolean VerifyRadioButtonSelected(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyRadioButtonSelected");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyRadioButtonSelected

		return false;

	}

	public boolean VerifyRadioButtonNotSelected(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyRadioButtonNotSelected");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyRadioButtonNotSelected

		return false;

	}

	public boolean VerifyCheckBoxToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyCheckBoxToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyCheckBoxToolTip

		return false;

	}

	public boolean VerifyTextAreaText(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaText

		return false;

	}

	public boolean VerifyTextAreaEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaEnabled

		return false;

	}

	public int GetRadioButtonCount(ORObject arg0) {
		ContextInitiator.addFunction("GetRadioButtonCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getRadioButtonCount

		return 0;

	}

	public boolean TypeTextAndEnterTextArea(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextAndEnterTextArea");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextandEnterTextArea

		return false;

	}

	public boolean VerifyTextAreaDefaultValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaDefaultValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaDefaultValue

		return false;

	}

	public boolean VerifyTextAreaDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaDisabled

		return false;

	}

	public boolean VerifyTextAreaEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaEditable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaEditable

		return false;

	}

	public boolean VerifyTextAreaNotEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaNotEditable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaNotEditable

		return false;

	}

	public boolean VerifyTextAreaToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaToolTip

		return false;

	}

	public boolean VerifyTableColumnNumber(ORObject arg0, int arg1, String arg2, int arg3) {
		ContextInitiator.addFunction("VerifyTableColumnNumber");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableColumnNumber

		return false;

	}

	public boolean VerifyTableColumnText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("VerifyTableColumnText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableColumnText

		return false;

	}

	public boolean VerifyTableRowText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("VerifyTableRowText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableRowText

		return false;

	}

	public boolean VerifyTableColumnHeader(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTableColumnHeader");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableColumnHeader

		return false;

	}

	public boolean VerifyObjectToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyObjectToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyObjectToolTip

		return false;

	}

	public boolean SelectMultipleDropDownItemAndWait(ORObject arg0, String arg1, int arg2) {
		ContextInitiator.addFunction("SelectMultipleDropDownItemAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectMultipleDropDownItemAndWait

		return false;

	}

	public boolean DoubleClickButton(ORObject arg0) {
		ContextInitiator.addFunction("DoubleClickButton");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_doubleClickButton

		return false;

	}

	public boolean FocusButton(ORObject arg0) {
		ContextInitiator.addFunction("FocusButton");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyButtonDisabled

		return false;

	}

	public boolean VerifyButtonExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyButtonExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyButtonExist

		return false;

	}

	public boolean SelectRadioButtonOnIndexBasis(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectRadioButtonOnIndexBasis");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectRadioButtonOnIndexBasis

		return false;

	}

	public boolean ClickLinkInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("ClickLinkInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickLinkInTableCell

		return false;

	}

	public boolean TypeTextInTextArea(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextInTextArea");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextInTextArea

		return false;

	}

	public boolean TypeKeysInTextArea(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeKeysInTextArea");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeKeysInTextArea

		return false;

	}

	public boolean ClearTextArea(ORObject arg0) {
		ContextInitiator.addFunction("ClearTextArea");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clearTextArea

		return false;

	}

	public boolean SetfocusTextArea(ORObject arg0) {
		ContextInitiator.addFunction("SetfocusTextArea");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_GetTextfromTextArea

		return "";

	}

	public boolean VerifyTextAreaValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaValue

		return false;

	}

	public boolean VerifyTextAreaExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaExist

		return false;

	}

	public boolean VerifyTextAreaNotExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyTextAreaNotExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreanotExist

		return false;

	}

	public boolean VerifyTableRowNumber(ORObject arg0, int arg1, String arg2, int arg3) {
		ContextInitiator.addFunction("VerifyTableRowNumber");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableRowNumber

		return false;

	}

	public boolean VerifyTextAreaName(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyTextAreaName");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTextAreaName

		return false;

	}

	public boolean VerifyTextAreaLength(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyTextAreaLength");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyObjectdoesnotExists

		return false;

	}

	public boolean VerifyObjectDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyObjectDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyPopUpText

		return false;

	}

	public boolean ClickLink(ORObject arg0) throws ToolNotSetException {
		ContextInitiator.addFunction("ClickLink");

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyButtonEnabled

		return false;

	}

	public boolean VerifyButtonToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyButtonToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyButtonToolTip

		return false;

	}

	public boolean ClickButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("ClickButtonInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyImageEnabled

		return false;

	}

	public boolean VerifyImageDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyImageDisabled

		return false;

	}

	public boolean VerifyImageToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyImageToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyImageToolTip

		return false;

	}

	public boolean WaitforImageLoad(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitforImageLoad");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyDropDownToolTip

		return false;

	}

	public boolean VerifyLinkEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkEnabled

		return false;

	}

	public boolean VerifyLinkDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkDisabled

		return false;

	}

	public boolean VerifyLinkVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyLinkVisible");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkVisible

		return false;

	}

	public boolean WaitforLink(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitforLink");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitforLink

		return false;

	}

	public boolean VerifyImageVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageVisible");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyImageVisible

		return false;

	}

	public boolean VerifyImageNotVisible(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageNotVisible");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyImageNotVisible

		return false;

	}

	public boolean VerifyImageExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyImageExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyImageExist

		return false;

	}

	public boolean DoubleClickImage(ORObject arg0) {
		ContextInitiator.addFunction("DoubleClickImage");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_doubleClickImage

		return false;

	}

	public boolean ClickImage(ORObject arg0) {
		ContextInitiator.addFunction("ClickImage");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickImage

		return false;

	}

	public boolean VerifyLinkToolTip(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyLinkToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyLinkToolTip

		return false;

	}

	public String GetPopupText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetPopupText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitForEditBoxDisabled

		return false;

	}

	public boolean WaitForEditBoxEnabled(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForEditBoxEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitForEditBoxEnabled

		return false;

	}

	public boolean VerifyRadioButtonExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyRadioButtonExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyRadioButtonExist

		return false;

	}

	public boolean VerifyRadioButtonDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyRadioButtonDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyRadioButtonDisabled

		return false;

	}

	public boolean VerifyRadioButtonEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyRadioButtonEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_focusRadioButton

		return false;

	}

	public boolean VerifyCheckBoxExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyCheckBoxExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyCheckBoxExist

		return false;

	}

	public boolean VerifyCheckBoxDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyCheckBoxDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyCheckBoxDisabled

		return false;

	}

	public boolean VerifyCheckBoxEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyCheckBoxEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_focusCheckBox

		return false;

	}

	public boolean VerifyDropDownSelection(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyDropDownSelection");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyDropDownSelection

		return false;

	}

	public boolean VerifyDropDownExist(ORObject arg0) {
		ContextInitiator.addFunction("VerifyDropDownExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyDropDownExist

		return false;

	}

	public boolean VerifyDropDownDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyDropDownDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyDropDownDisabled

		return false;

	}

	public boolean VerifyDropDownEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyDropDownEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyDropDownEnabled

		return false;

	}

	public boolean VerifyAllDropDownItemExist(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyAllDropDownItemExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyAllDropDownItemExist

		return false;

	}

	public boolean VerifyMultipleDropDownItemExist(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyMultipleDropDownItemExist");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyMultipleDropDownItemExist

		return false;

	}

	public boolean VerifyDropDownItemCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyDropDownItemCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_SetFocusonDropDown

		return false;

	}

	public boolean VerifyEditBoxExistAndWait(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxExistAndWait");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxExist

		return false;

	}

	public boolean VerifyEditBoxValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxValue

		return false;

	}

	public boolean VerifyEditBoxLength(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyEditBoxLength");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxLength

		return false;

	}

	public boolean VerifyEditBoxName(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyEditBoxName");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxName

		return false;

	}

	public boolean VerifyEditBoxNonEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxNonEditable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxNonEditable

		return false;

	}

	public boolean VerifyEditBoxEditable(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxEditable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxEditable

		return false;

	}

	public boolean VerifyEditBoxDisabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxDisabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyEditBoxDisabled

		return false;

	}

	public boolean VerifyEditBoxEnabled(ORObject arg0) {
		ContextInitiator.addFunction("VerifyEditBoxEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_SetfocusEditField

		return false;

	}

	public boolean VerifyTextareaColsRowLength(ORObject arg0, int arg1, int arg2) {
		ContextInitiator.addFunction("VerifyTextareaColsRowLength");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeSecureText

		return false;

	}

	public boolean NextPageObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("NextPageObject");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_nextPageObject

		return false;

	}

	public boolean SelectGroupRadioButton(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("SelectGroupRadioButton");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_TableGetTextRow

		return 0;

	}

	public int GetTableColumnNumber(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetTableColumnNumber");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_TableGetTextColumn

		return 0;

	}

	public boolean MouseHover(ORObject arg0) {
		ContextInitiator.addFunction("MouseHover");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickAt

		return false;

	}

	public String GetDropDownToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetDropDownToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getDropDownToolTip

		return "";

	}

	public String GetEditBoxToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getEditBoxToolTip

		return "";

	}

	public String GetTextAreaToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTextAreaToolTip

		return "";

	}

	public String GetButtonToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetButtonToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getButtonToolTip

		return "";

	}

	public String GetCheckBoxToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetCheckBoxToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getCheckBoxToolTip

		return "";

	}

	public String GetLinkToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetLinkToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getLinkToolTip

		return "";

	}

	public String GetObjectToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectToolTip

		return "";

	}

	public String GetImageToolTip(ORObject arg0) {
		ContextInitiator.addFunction("GetImageToolTip");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_GetTextAreaLength

		return 0;

	}

	public String GetTextAreaColumnRowLength(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaColumnRowLength");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_GetTextAreaColRowLength

		return "";

	}

	public String GetEditBoxName(ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxName");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectValue

		return "";

	}

	public boolean VerifyObjectValue(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyObjectValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyObjectValue

		return false;

	}

	public boolean GetObjectVisibility(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectVisibility");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectVisibility

		return false;

	}

	public boolean GetObjectExistence(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectExistence");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectExistence

		return false;

	}

	public boolean GetObjectEnabled(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectEnabled");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectEnabled

		return false;

	}

	public int GetTableRowCount(ORObject arg0) {
		ContextInitiator.addFunction("GetTableRowCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTableRowCount

		return 0;

	}

	public int GetTableColumnCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetTableColumnCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_setPage

		return false;

	}

	public String GetObjectCSSProperty(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetObjectCSSProperty");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectCSSProperty

		return "";

	}

	public boolean VerifyCheckboxStatusInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("VerifyCheckboxStatusInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyCheckboxStatusInTableCell

		return false;

	}

	public String GetCheckboxStatus(ORObject arg0) {
		ContextInitiator.addFunction("GetCheckboxStatus");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getCheckboxStatus

		return "";

	}

	public boolean SelectRadioButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("SelectRadioButtonInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectRadioButtobTableCell

		return false;

	}

	public boolean RightClickOnObject(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("RightClickOnObject");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_rightClickAndSelect

		return false;

	}

	public String GetObjectProperty(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetObjectProperty");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectProperty

		return "";

	}

	public boolean SelectCheckBoxinTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectCheckBoxinTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectCheckBoxinTableCell

		return false;

	}

	public boolean WaitForObjectVisible(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectVisible");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitforobjectvisible

		return false;

	}

	public String GetTextAreavalue(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreavalue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTextAreavalue

		return "";

	}

	public boolean WaitForObjectEditable(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectEditable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitForObjectEditable

		return false;

	}

	public boolean TypeTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
		ContextInitiator.addFunction("TypeTextInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextInTableCell

		return false;

	}

	public boolean WaitForObjectEnable(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectEnable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {
		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextInTableCellByQuery

		return false;

	}

	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
		ContextInitiator.addFunction("GetTextFromTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectListItem

		return false;

	}

	public boolean Web_VerifyListItemExists(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("Web_VerifyListItemExists");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyListItemExists

		return false;

	}

	public String GetAllColumnText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetAllColumnText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getAllColText

		return "";

	}

	public String GetSingleTableColumnText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetSingleTableColumnText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getSingleColText

		return "";

	}

	public String GetAllRowText(ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetAllRowText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getAllRowText

		return "";

	}

	public String GetSingleTableRowText(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetSingleTableRowText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getSingleRowText

		return "";

	}

	public boolean SelectDropDownInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectDropDownInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectDropDownInTableCell

		return false;

	}

	public boolean DeSelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("DeSelectMultipleDropdownItemInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_deSelectMultipleDropDownItemInTableCell

		return false;

	}

	public boolean SelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectMultipleDropdownItemInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectMultipleDropdownItemInTableCell

		return false;

	}

	public String GetSelectedDropDownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("GetSelectedDropDownItemInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getSelectedDropDownInTableCell

		return "";

	}

	public boolean DoubleClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("DoubleClickTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_doubleClickInTableCell

		return false;

	}

	public String FetchObjectPropertyInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4,
			String arg5) {
		ContextInitiator.addFunction("FetchObjectPropertyInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_fetchObjectPropertyInTableCell

		return "";

	}

	public boolean ClickOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("ClickOnObjectInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickOnObjectInTableCell

		return false;

	}

	public boolean TypeOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
		ContextInitiator.addFunction("TypeOnObjectInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeOnObjecttInTableCell

		return false;

	}

	public boolean WaitForObjectDisable(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectDisable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitForObjectdisable

		return false;

	}

	public String CaptureObjectSnapshot(ORObject arg0) {
		ContextInitiator.addFunction("CaptureObjectSnapshot");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_captureObjectSnapShot

		return "";

	}

	public String GetEditboxDefaultvalue(ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxDefaultvalue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getEditboxDefaultvalue

		return "";

	}

	public int GetEditBoxLength(ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxLength");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getEditboxLength

		return 0;

	}

	public String GetEditboxValue(ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getEditboxValue

		return "";

	}

	public String GetTextAreaDefaultvalue(ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxValue");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTextAreaDefaultvalue

		return "";

	}

	public String GetTextAreaName(ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaName");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getDropDownDefaultValue

		return "";

	}

	public boolean VerifyChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("VerifyChildObjectCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyChildObjectCount

		return false;

	}

	public boolean VerifyFullTableText(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyFullTableText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyFullTableText

		return false;

	}

	public boolean VerifyTableColumnCount(ORObject arg0, int arg1, int arg2) {
		ContextInitiator.addFunction("VerifyTableColumnCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableColumnCount

		return false;

	}

	public boolean VerifyTableRowCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyTableRowCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_verifyTableRowCount

		return false;

	}

	public String GetTableColumnHeader(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetTableColumnHeader");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_VerifyMultipleObjectProperty

		return false;

	}

	public int Web_GetTableColumnCount(ORObject arg0, int arg1) {
		ContextInitiator.addFunction("Web_GetTableColumnCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_WebGetTableColCount

		return 0;

	}

	public int Web_GetTableRowCount(ORObject arg0) {
		ContextInitiator.addFunction("Web_GetTableRowCount");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {
		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextInTableCellByQuery

		return false;

	}

	public boolean ClickOnObjectInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, String arg8, int arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {
		ContextInitiator.addFunction("ClickOnObjectInTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextAndEnterEditBox(ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextAndEnterEditBox");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

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
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_rightClickAndSelectByText

		return false;

	}

	public boolean IsTextPresentOnScreen(ORObject arg0) {
		ContextInitiator.addFunction("IsTextPresentOnScreen");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_IsTextPresentOnScreen_Generic

		return false;

	}

}
