package com.opkey.OpKeyGenericPlugin;

import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
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
import com.crestech.opkey.plugin.webdriver.keywords.WebObjects;
import com.crestech.opkey.plugin.webdriver.keywords.Window;
import com.crestech.opkey.plugin.webdriver.object.WebDriverObject;
import com.opkey.ObjectFromatter.ObjectConverter;
import com.opkey.context.ContextInitiator;
import com.opkey.executor.KeywordExecutor;
import com.opkey.executor.Runnable;
import com.opkeystudio.runtime.ORObject;
import com.ssts.reporting.Report;

public class OpKeyGenericKeywords {
	public OpKeyGenericKeywords() {
		new ContextInitiator().initContext();
	}

	public boolean TypeTextOnEditBox(final ORObject arg0, final String arg1) {
		ContextInitiator.addFunction("TypeTextOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);

		// Method_typeTextOnEditBox

		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new EditBox().Method_typeTextOnEditBox(object, arg1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();

		ReportHelper.addReportStep("TypeTextOnEditBox", result);
		return false;

	}

	public boolean TypeKeysOnEditBox(final ORObject arg0, final String arg1) {
		ContextInitiator.addFunction("TypeKeysOnEditBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);

		// Method_typeKeysOnEditBox

		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new EditBox().Method_typeKeysOnEditBox(object, arg1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean VerifyEditBoxText(final ORObject arg0, final String arg1) {
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new EditBox().Method_verifyeditboxtext(object, arg1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean OpenBrowser(final String arg0, final String arg1) throws Exception {
		ContextInitiator.addFunction("OpenBrowser");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);

		// Method_WebBrowserOpen
		FunctionResult result = new KeywordExecutor(new Runnable() {
			
			public FunctionResult run() {
				try {
					FunctionResult functionResult = new Browser().Method_WebBrowserOpen(arg0, arg1);
					ReportHelper.addReportStep("OpenBrowser", functionResult);
					return functionResult;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		
		return false;

	}

	public boolean SelectDropDownItem(final ORObject arg0, final String arg1) {
		ContextInitiator.addFunction("SelectDropDownItem");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);

		// Method_selectDropDownItem
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new DropDown().Method_selectDropDownItem(object, arg1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SelectCheckBox(final ORObject arg0, final String arg1) {
		ContextInitiator.addFunction("SelectCheckBox");
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new Checkbox().Method_selectCheckBox(object, arg1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SelectRadioButton(final ORObject arg0, final int arg1) {
		ContextInitiator.addFunction("SelectRadioButton");

		// Method_SelectRadio
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new Radio().Method_SelectRadio(object, arg1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Click(final ORObject arg0) throws Exception {
		ContextInitiator.addFunction("Click");

		// Method_ObjectClick
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					FunctionResult functionResult = new WebObjects().Method_ObjectClick(object);
					ReportHelper.addReportStep("Click", functionResult);
				} catch (Exception e) {
					e.printStackTrace();
					ReportHelper.addReportStep("Click", e);
				}
				return null;
			}
		}).executeKeyword();
		return false;
	}

	public boolean DoubleClick(final ORObject arg0) {
		ContextInitiator.addFunction("DoubleClick");

		// Method_dblClick
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new WebObjects().Method_dblClick(object);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean RefreshBrowser() {
		ContextInitiator.addFunction("RefreshBrowser");

		// Method_RefreshBrowser
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				try {
					return new Browser().Method_RefreshBrowser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean MaximizeBrowser() {
		ContextInitiator.addFunction("MaximizeBrowser");

		// Method_MaximizeBrowser
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				try {
					return new Browser().Method_MaximizeBrowser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String FetchBrowserURL() {
		ContextInitiator.addFunction("FetchBrowserURL");

		// Method_fetchBrowserURL
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				try {
					return new Browser().Method_fetchBrowserURL();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String FetchBrowserTitle(final String arg0) {
		ContextInitiator.addFunction("FetchBrowserTitle");
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);

		// Method_fetchBrowserTitle
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				try {
					return new Browser().Method_fetchBrowserTitle(arg0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean GoBackAndWait(final int arg0) {
		ContextInitiator.addFunction("GoBackAndWait");

		// Method_goBackAndWait
		
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				try {
					return new Deprecate().Method_goBackAndWait(arg0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean RefreshAndWait(final int arg0) {
		ContextInitiator.addFunction("RefreshAndWait");

		// Method_refreshAndWait
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				try {
					return new Deprecate().Method_refreshAndWait(arg0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean ClearEditField(final ORObject arg0) {
		ContextInitiator.addFunction("ClearEditField");

		// Method_clearEditField
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					FunctionResult functionResult =  new EditBox().Method_clearEditField(object);
					ReportHelper.addReportStep("ClearEditField", functionResult);
					return functionResult;
				} catch (Exception e) {
					ReportHelper.addReportStep("ClearEditField", e);
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		
		
		return false;

	}

	public boolean TypeTextAndWait(final ORObject arg0, final String arg1, final int arg2) {
		ContextInitiator.addFunction("TypeTextAndWait");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				try {
					return new Deprecate().Method_typeTextAndWait(object, arg1, arg2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).executeKeyword();
		// Method_typeTextAndWait
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

		ReportHelper.addReportStep("VerifyObjectExists", new Exception("Exception while VerifyObjectExists"));
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

	public boolean MouseHover(final ORObject arg0) {
		ContextInitiator.addFunction("MouseHover");

		// Method_MouseHover
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean NavigateTo(String arg0) {
		ContextInitiator.addFunction("NavigateTo");

		// Method_navigateTo
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		
		ReportHelper.addReportStep("NavigateTo", new Exception("Methond not implemented yet."));
		return false;

	}

	public boolean AssertTextPresent(String arg0) {
		ContextInitiator.addFunction("AssertTextPresent");

		// Method_AssertTextPresent
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean ClickAt(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("ClickAt");

		// Method_clickAt
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetDropDownToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetDropDownToolTip");
		// Method_getDropDownToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetEditBoxToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxToolTip");
		// Method_getEditBoxToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetTextAreaToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaToolTip");
		// Method_getTextAreaToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetButtonToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetButtonToolTip");
		// Method_getButtonToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetCheckBoxToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetCheckBoxToolTip");
		// Method_getCheckBoxToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetLinkToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetLinkToolTip");
		// Method_getLinkToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetObjectToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetObjectToolTip");
		// Method_getObjectToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetImageToolTip(final ORObject arg0) {
		ContextInitiator.addFunction("GetImageToolTip");

		// Method_getImageToolTip
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean MinimizeBrowser() {
		ContextInitiator.addFunction("MinimizeBrowser");

		// Method_MinimizeBrowser
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public int GetTextAreaLength(final ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaLength");
		// Method_GetTextAreaLength
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return 0;

	}

	public String GetTextAreaColumnRowLength(final ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaColumnRowLength");

		// Method_GetTextAreaColRowLength
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetEditBoxName(final ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxName");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_GetEditBoxName
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean AcceptPopup() {
		ContextInitiator.addFunction("AcceptPopup");

		// Method_acceptPopup
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean DismissPopup() {
		ContextInitiator.addFunction("DismissPopup");

		// Method_dismissPopup
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean VerifyPopupPresent(String arg0) {
		ContextInitiator.addFunction("VerifyPopupPresent");

		// Method_verifyPopupPresent
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SelectFrame(ORObject arg0) {
		ContextInitiator.addFunction("SelectFrame");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_selectFrame
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SwitchToDefaultContent() {
		ContextInitiator.addFunction("SwitchToDefaultContent");

		// Method_switchToDefaultContent
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetObjectValue(final ORObject arg0) {
		ContextInitiator.addFunction("GetObjectValue");
		// Method_getObjectValue
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean VerifyObjectValue(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyObjectValue");
		// Method_VerifyObjectValue
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean GetObjectVisibility(final ORObject arg0) {
		ContextInitiator.addFunction("GetObjectVisibility");
		// Method_getObjectVisibility
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean GetObjectExistence(final ORObject arg0) {
		ContextInitiator.addFunction("GetObjectExistence");
		// Method_getObjectExistence
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean GetObjectEnabled(final ORObject arg0) {
		ContextInitiator.addFunction("GetObjectEnabled");
		// Method_getObjectEnabled
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public int GetTableRowCount(final ORObject arg0) {
		ContextInitiator.addFunction("GetTableRowCount");
		// Method_getTableRowCount
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return 0;

	}

	public int GetTableColumnCount(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetTableColumnCount");
		// Method_getTableColCount
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return 0;

	}

	public boolean ExcelCompare(String arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("ExcelCompare");

		// Method_excelCompare
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SetPage(final ORObject arg0) {
		ContextInitiator.addFunction("SetPage");
		// Method_setPage
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetObjectCSSProperty(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetObjectCSSProperty");
		// Method_getObjectCSSProperty
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean VerifyCheckboxStatusInTableCell(final ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("VerifyCheckboxStatusInTableCell");

		// Method_verifyCheckboxStatusInTableCell
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetCheckboxStatus(final ORObject arg0) {
		ContextInitiator.addFunction("GetCheckboxStatus");
		// Method_getCheckboxStatus
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean SelectRadioButtonInTableCell(final ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("SelectRadioButtonInTableCell");
		// Method_selectRadioButtobTableCell
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean RightClickOnObject(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("RightClickOnObject");

		// Method_rightClickAndSelect
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetObjectProperty(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("GetObjectProperty");

		// Method_getObjectProperty
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean SelectCheckBoxinTableCell(final ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectCheckBoxinTableCell");

		// Method_selectCheckBoxinTableCell
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean WaitForObjectVisible(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectVisible");

		// Method_waitforobjectvisible
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetTextAreavalue(final ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreavalue");

		// Method_getTextAreavalue
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean WaitForObjectEditable(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectEditable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitForObjectEditable
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean TypeTextInTableCell(final ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5) {
		ContextInitiator.addFunction("TypeTextInTableCell");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextInTableCell
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean WaitForObjectEnable(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectEnable");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_waitforobjectenable
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Swipe(double arg0, double arg1, double arg2, double arg3, double arg4) {
		ContextInitiator.addFunction("Swipe");

		// Method_swipe
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SwipeLeft() {
		ContextInitiator.addFunction("SwipeLeft");

		// Method_SwipeLeft
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean SwipeRight() {
		ContextInitiator.addFunction("SwipeRight");

		// Method_SwipeRight
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetObjectHeightWidth(ORObject arg0) {
		ContextInitiator.addFunction("GetObjectHeightWidth");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getObjectHeightWidth
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean SwipeObject(ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("SwipeObject");

		// Method_swipeWithObject
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Swipe(int arg0, String arg1) {
		ContextInitiator.addFunction("Swipe");

		// Method_MobilitySwipe
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public int GetObjectCount(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6,
			String arg7, String arg8, String arg9) {
		ContextInitiator.addFunction("GetObjectCount");

		// Method_getObjectCount
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return 0;

	}

	public String GetObjectText(final ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetObjectText");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_GetObjectText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		
		ReportHelper.addReportStep("GetObjectText", new Exception("exception while runnging GetObjectText"));
		return "";

	}

	public boolean VerifyEditBoxExist() {
		ContextInitiator.addFunction("VerifyEditBoxExist");

		// Method_verifyEditBoxExist
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		
		ReportHelper.addReportStep("VerifyEditBoxExist", new Exception("Object not found"));
		return false;

	}

	public boolean ClickInTableCellByQuery(final ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
		ContextInitiator.addFunction("ClickInTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_clickInTableCellByQuery
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean TypeTextInTableCellByQuery(final ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {
		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_typeTextInTableCellByQuery
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String GetTextFromTableCellByQuery(final ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
		ContextInitiator.addFunction("GetTextFromTableCellByQuery");
		WebDriverObject object = new ObjectConverter().formatObject(arg0);

		// Method_getTextFromTableCellByQuery
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public boolean Web_ClickByText(String arg0, int arg1, boolean arg2, ORObject arg3, String arg4, String arg5) {
		ContextInitiator.addFunction("Web_ClickByText");

		// Method_clickByText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_ClickByTextInSequence(String arg0, int arg1, int arg2, boolean arg3, String arg4, int arg5,
			boolean arg6, ORObject arg7, ORObject arg8, ORObject arg9, ORObject arg10, ORObject arg11, boolean arg12,
			String arg13, int arg14, boolean arg15, String arg16, int arg17, boolean arg18, String arg19) {
		ContextInitiator.addFunction("Web_ClickByTextInSequence");

		// Method_clickByTextInSequence
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_SelectByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction("Web_SelectByText");

		// Method_SelectByText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_TypeByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, ORObject arg5) {
		ContextInitiator.addFunction("Web_TypeByText");

		// Method_typeTextUsingText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean MouseHoverOnText(String arg0, int arg1, boolean arg2, ORObject arg3) {
		ContextInitiator.addFunction("MouseHoverOnText");

		// Method_mouseHoverOnText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean ClickImageByTitleAlt(String arg0, int arg1, boolean arg2) {
		ContextInitiator.addFunction("ClickImageByTitleAlt");

		// Method_clickImageByAltText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_SelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, String arg4,
			ORObject arg5) {
		ContextInitiator.addFunction("Web_SelectCheckboxByText");

		// Method_selectCheckBoxByText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_DeSelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction("Web_DeSelectCheckboxByText");

		// Method_deSelectCheckBoxByText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_SelectRadioButtonByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4) {
		ContextInitiator.addFunction("Web_SelectRadioButtonByText");

		// Method_selectRadioButtonByText
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean Web_SelectDropDownByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4,
			boolean arg5, ORObject arg6) {
		ContextInitiator.addFunction("Web_SelectDropDownByText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_selectDropDownByText

		return false;

	}

	public boolean Web_GoBack() {
		ContextInitiator.addFunction("Web_GoBack");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_goBack

		return false;

	}

	public boolean Web_SelectListItem(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("Web_SelectListItem");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_selectListItem

		return false;

	}

	public boolean Web_VerifyListItemExists(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("Web_VerifyListItemExists");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_verifyListItemExists

		return false;

	}

	public String GetAllColumnText(final ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetAllColumnText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getAllColText

		return "";

	}

	public String GetSingleTableColumnText(final ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetSingleTableColumnText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getSingleColText

		return "";

	}

	public String GetAllRowText(final ORObject arg0, String arg1, String arg2) {
		ContextInitiator.addFunction("GetAllRowText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getAllRowText

		return "";

	}

	public String GetSingleTableRowText(final ORObject arg0, int arg1, String arg2) {
		ContextInitiator.addFunction("GetSingleTableRowText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getSingleRowText

		return "";

	}

	public boolean SelectDropDownInTableCell(final ORObject arg0, int arg1, int arg2, int arg3, String arg4) {
		ContextInitiator.addFunction("SelectDropDownInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_selectDropDownInTableCell

		return false;

	}

	public boolean DeSelectMultipleDropdownItemInTableCell(final ORObject arg0, int arg1, int arg2, int arg3,
			String arg4) {
		ContextInitiator.addFunction("DeSelectMultipleDropdownItemInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_deSelectMultipleDropDownItemInTableCell

		return false;

	}

	public boolean SelectMultipleDropdownItemInTableCell(final ORObject arg0, int arg1, int arg2, int arg3,
			String arg4) {
		ContextInitiator.addFunction("SelectMultipleDropdownItemInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_selectMultipleDropdownItemInTableCell

		return false;

	}

	public String GetSelectedDropDownItemInTableCell(final ORObject arg0, int arg1, int arg2, int arg3) {
		ContextInitiator.addFunction("GetSelectedDropDownItemInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getSelectedDropDownInTableCell

		return "";

	}

	public boolean DoubleClickTableCell(final ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("DoubleClickTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_doubleClickInTableCell

		return false;

	}

	public String FetchObjectPropertyInTableCell(final ORObject arg0, int arg1, int arg2, String arg3, int arg4,
			String arg5) {
		ContextInitiator.addFunction("FetchObjectPropertyInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_fetchObjectPropertyInTableCell

		return "";

	}

	public boolean ClickOnObjectInTableCell(final ORObject arg0, int arg1, int arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("ClickOnObjectInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_clickOnObjectInTableCell

		return false;

	}

	public boolean TypeOnObjectInTableCell(final ORObject arg0, int arg1, int arg2, String arg3, int arg4,
			String arg5) {
		ContextInitiator.addFunction("TypeOnObjectInTableCell");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_typeOnObjecttInTableCell

		return false;

	}

	public boolean WaitForObjectDisable(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("WaitForObjectDisable");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_waitForObjectdisable

		return false;

	}

	public String CaptureObjectSnapshot(final ORObject arg0) {
		ContextInitiator.addFunction("CaptureObjectSnapshot");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_captureObjectSnapShot

		return "";

	}

	public String GetEditboxDefaultvalue(final ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxDefaultvalue");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_getEditboxDefaultvalue

		return "";

	}

	public int GetEditBoxLength(final ORObject arg0) {
		ContextInitiator.addFunction("GetEditBoxLength");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_getEditboxLength

		return 0;

	}

	public String GetEditboxValue(final ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxValue");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_getEditboxValue

		return "";

	}

	public String GetTextAreaDefaultvalue(final ORObject arg0) {
		ContextInitiator.addFunction("GetEditboxValue");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getTextAreaDefaultvalue

		return "";

	}

	public String GetTextAreaName(final ORObject arg0) {
		ContextInitiator.addFunction("GetTextAreaName");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_getTextAreaName

		return "";

	}

	public boolean VerifyAllButtons(String arg0) {
		ContextInitiator.addFunction("VerifyAllButtons");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_verifyAllButtons

		return false;

	}

	public int GetImageCount() {
		ContextInitiator.addFunction("GetImageCount");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_getImageCount

		return 0;

	}

	public String GetDropdownDefaultItem(final ORObject arg0) {
		ContextInitiator.addFunction("GetDropdownDefaultItem");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_getDropDownDefaultValue

		return "";

	}

	public boolean VerifyChildObjectCount(final ORObject arg0, String arg1, String arg2, String arg3, int arg4) {
		ContextInitiator.addFunction("VerifyChildObjectCount");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_verifyChildObjectCount

		return false;

	}

	public boolean VerifyFullTableText(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("VerifyFullTableText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_verifyFullTableText

		return false;

	}

	public boolean VerifyTableColumnCount(final ORObject arg0, int arg1, int arg2) {
		ContextInitiator.addFunction("VerifyTableColumnCount");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_verifyTableColumnCount

		return false;

	}

	public boolean VerifyTableRowCount(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("VerifyTableRowCount");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_verifyTableRowCount

		return false;

	}

	public String GetTableColumnHeader(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("GetTableColumnHeader");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getTableColumnHeader

		return "";

	}

	public boolean SetBrowserCapability(String arg0, String arg1, String arg2, String arg3) {
		ContextInitiator.addFunction("SetBrowserCapability");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_SetBrowserCapability

		return false;

	}

	public String GetCompleteTableText(final ORObject arg0) {
		ContextInitiator.addFunction("GetCompleteTableText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getCompleteTableText

		return "";

	}

	public boolean DeFocusObject() {
		ContextInitiator.addFunction("DeFocusObject");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_deFocusObject

		return false;

	}

	public boolean VerifyMultipleObjectProperty(final ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) {
		ContextInitiator.addFunction("VerifyMultipleObjectProperty");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_VerifyMultipleObjectProperty

		return false;

	}

	public int Web_GetTableColumnCount(final ORObject arg0, int arg1) {
		ContextInitiator.addFunction("Web_GetTableColumnCount");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_WebGetTableColCount

		return 0;

	}

	public int Web_GetTableRowCount(final ORObject arg0) {
		ContextInitiator.addFunction("Web_GetTableRowCount");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();

		// Method_WebGetTableRowCount

		return 0;

	}

	public boolean Web_SetFocusOnCurrentWindow() {
		ContextInitiator.addFunction("Web_SetFocusOnCurrentWindow");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_setFocusOnCurrentWindow

		return false;

	}

	public boolean Web_WaitForWindowLoad(String arg0, int arg1, boolean arg2, int arg3) {
		ContextInitiator.addFunction("Web_WaitForWindowLoad");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_waitForWindowLoad

		return false;

	}

	public double GetLoadTime() {
		ContextInitiator.addFunction("GetLoadTime");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_getLoadingTime

		return 0;

	}

	public boolean IgnoreXMLHttpRequest(String arg0) {
		ContextInitiator.addFunction("IgnoreXMLHttpRequest");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_ignoreXMLHttpRequest

		return false;

	}

	public boolean SynchronizeBrowser(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {
		ContextInitiator.addFunction("SynchronizeBrowser");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		// Method_syncBrowser

		return false;

	}

	public boolean TypeTextInPTag(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextInPTag");

		// Method_TypeTextInContentEditable
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public String VisualComparisonForPage(String arg0, String arg1, boolean arg2, boolean arg3) {
		ContextInitiator.addFunction("VisualComparisonForPage");

		// Custom_visualComparionForPage
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return "";

	}

	public String GetTextFromTableCellByQuery(final ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, int arg6, int arg7, String arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13) {
		ContextInitiator.addFunction("GetTextFromTableCellByQuery");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_getTextFromTableCellByQuery

		return "";

	}

	public boolean TypeTextInTableCellByQuery(final ORObject arg0, String arg1, String arg2, String arg3, String arg4,
			String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11, String arg12,
			String arg13, String arg14) {
		ContextInitiator.addFunction("TypeTextInTableCellByQuery");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_typeTextInTableCellByQuery

		return false;

	}

	public boolean ClickOnObjectInTableCellByQuery(final ORObject arg0, String arg1, String arg2, String arg3,
			String arg4, String arg5, String arg6, int arg7, String arg8, int arg9, String arg10, String arg11,
			String arg12, String arg13, String arg14) {
		ContextInitiator.addFunction("ClickOnObjectInTableCellByQuery");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_clickInTableCellByQuery

		return false;

	}

	public boolean TypeTextAndEnterEditBox(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("TypeTextAndEnterEditBox");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_typeTextandEnterEditBox

		return false;

	}

	public boolean Web_ResizeBrowser(final int arg0, int arg1) {
		ContextInitiator.addFunction("Web_ResizeBrowser");

		// Method_setViewPort
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				return null;
			}
		}).executeKeyword();
		return false;

	}

	public boolean RightClickAndSelectByText(final ORObject arg0, String arg1) {
		ContextInitiator.addFunction("RightClickAndSelectByText");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_rightClickAndSelectByText

		return false;

	}

	public boolean IsTextPresentOnScreen(final ORObject arg0) {
		ContextInitiator.addFunction("IsTextPresentOnScreen");
		FunctionResult result = new KeywordExecutor(new Runnable() {

			public FunctionResult run() {
				WebDriverObject object = new ObjectConverter().formatObject(arg0);
				return null;
			}
		}).executeKeyword();
		// Method_IsTextPresentOnScreen_Generic

		return false;

	}

}
