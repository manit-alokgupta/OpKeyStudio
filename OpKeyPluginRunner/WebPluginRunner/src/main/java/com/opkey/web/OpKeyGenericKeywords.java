package com.opkey.web;

import com.crestech.opkey.plugin.Keyword;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.webdriver.keywords.Browser;
import com.crestech.opkey.plugin.webdriver.keywords.Button;
import com.crestech.opkey.plugin.webdriver.keywords.Checkbox;
import com.crestech.opkey.plugin.webdriver.keywords.Deprecate;
import com.crestech.opkey.plugin.webdriver.keywords.DropDown;
import com.crestech.opkey.plugin.webdriver.keywords.EditBox;
import com.crestech.opkey.plugin.webdriver.keywords.Frame;
import com.crestech.opkey.plugin.webdriver.keywords.Image;
import com.crestech.opkey.plugin.webdriver.keywords.Links;
import com.crestech.opkey.plugin.webdriver.keywords.ListControl;
import com.crestech.opkey.plugin.webdriver.keywords.PopUp;
import com.crestech.opkey.plugin.webdriver.keywords.Radio;
import com.crestech.opkey.plugin.webdriver.keywords.SetCapabilities;
import com.crestech.opkey.plugin.webdriver.keywords.Table;
import com.crestech.opkey.plugin.webdriver.keywords.TextArea;
import com.crestech.opkey.plugin.webdriver.keywords.UnCategorised;
import com.crestech.opkey.plugin.webdriver.keywords.WebObjects;
import com.crestech.opkey.plugin.webdriver.keywords.Window;
import com.crestech.opkey.plugin.webdriver.keywords.byText.ByTextKeywords;
import com.crestech.opkey.plugin.webdriver.keywords.sikuli.library.ImageComparisionUsingNodeJS;
import com.crestech.opkey.plugin.webdriver.object.WebDriverObject;
import com.opkey.web.ObjectFromatter.ObjectConverter;
import com.opkey.web.context.ContextInitiator;
import com.opkey.web.executor.KeywordExecutor;
import com.opkeystudio.runtime.ORObject;

public class OpKeyGenericKeywords {

	public OpKeyGenericKeywords() {
		new ContextInitiator().initContext();
	}

	@Keyword("class=Button,method=Method_verifyAllButtons")
	public boolean VerifyAllButtons(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_verifyAllButtons(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Links,method=Method_verifyLinkExist")
	public boolean VerifyLinkExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_verifyLinkExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_ObjectTextVerification")
	public boolean VerifyObjectText(ORObject arg0, String arg1, String arg2, String arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_ObjectTextVerification(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Links,method=Method_verifyLinkCount")
	public boolean VerifyLinkCount(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_verifyLinkCount(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyAllLink")
	public boolean VerifyAllLink(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyAllLink(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_verifyImageCount")
	public boolean VerifyImageCount(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_verifyImageCount(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_verifyImageExist")
	public boolean VerifyImageExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_verifyImageExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditable")
	public boolean VerifyEditable(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditable(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyLinkDisabled")
	public boolean VerifyLinkDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyLinkDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyobjectDisabled")
	public boolean VerifyObjectDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyobjectDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyObjectdoesnotExists")
	public boolean VerifyObjectDoesNotExists(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyObjectdoesnotExists(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_ObjectisEnabled")
	public boolean VerifyObjectEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_ObjectisEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_ObjectExists")
	public boolean VerifyObjectExists(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_ObjectExists(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_VerifyPropertyValue")
	public boolean VerifyObjectPropertyValue(ORObject arg0, String arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_VerifyPropertyValue(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean VerifyObjectToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Links,method=Method_verifyLinkVisible")
	public boolean VerifyLinkVisible(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_verifyLinkVisible(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyDropDownExist")
	public boolean VerifyDropDownExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyDropDownExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_VerifyObjectValue")
	public boolean VerifyObjectValue(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_VerifyObjectValue(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_verifyObjectVisible")
	public boolean VerifyObjectVisible(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_verifyObjectVisible(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Links,method=Method_verifyLinkToolTip")
	public boolean VerifyLinkToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_verifyLinkToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyAllLinkExist")
	public boolean VerifyAllLinkExist(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyAllLinkExist(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_VerifyDropDownEnabled")
	public boolean VerifyDropDownEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_VerifyDropDownEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_VerifyBrowserTitle")
	public boolean VerifyBrowserTitle(String arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_VerifyBrowserTitle(arg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Radio,method=Method_verifyRadioButtonEnabled")
	public boolean VerifyRadioButtonEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Radio().Method_verifyRadioButtonEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Radio,method=Method_verifyRadioButtonExist")
	public boolean VerifyRadioButtonExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Radio().Method_verifyRadioButtonExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxDefaultValue")
	public boolean VerifyEditBoxDefaultValue(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxDefaultValue(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyRadioButtonDisabled")
	public boolean VerifyRadioButtonDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyRadioButtonDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Button,method=Method_verifyButtonToolTip")
	public boolean VerifyButtonToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_verifyButtonToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyAllDropDownItemExist")
	public boolean VerifyAllDropDownItemExist(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyAllDropDownItemExist(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyCheckBoxDisabled")
	public boolean VerifyCheckBoxDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyCheckBoxDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_verifyCheckBoxEnabled")
	public boolean VerifyCheckBoxEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_verifyCheckBoxEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_verifyCheckBoxStatus")
	public boolean verifyCheckBoxStatus(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_verifyCheckBoxStatus(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_verifyChildObjectCount")
	public boolean VerifyChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3, int arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_verifyChildObjectCount(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyDropDownDefaultItem")
	public boolean VerifyDropDownDefaultItem(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyDropDownDefaultItem(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyDropDownItemExists")
	public boolean VerifyDropDownItemExists(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyDropDownItemExists(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyDropDownSelection")
	public boolean VerifyDropDownSelection(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyDropDownSelection(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyCheckboxStatusInTableCell")
	public boolean VerifyCheckboxStatusInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyCheckboxStatusInTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxDisabled")
	public boolean VerifyEditBoxDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_verifyEditBoxEditable")
	public boolean VerifyEditBoxEditable(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_verifyEditBoxEditable(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxLength")
	public boolean VerifyEditBoxLength(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxLength(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Button,method=Method_verifyButtonExist")
	public boolean VerifyButtonExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_verifyButtonExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_verifyCheckBoxExist")
	public boolean VerifyCheckBoxExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_verifyCheckBoxExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxName")
	public boolean VerifyEditBoxName(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxName(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxNonEditable")
	public boolean VerifyEditBoxNonEditable(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxNonEditable(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyAllDropDownItems")
	public boolean VerifyAllDropDownItems(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyAllDropDownItems(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxnotExist")
	public boolean VerifyEditBoxNotExist(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxnotExist(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_verifyBrowserExist")
	public boolean VerifyBrowserExist(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_verifyBrowserExist(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_verifyeditboxtext")
	public boolean VerifyEditBoxText(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_verifyeditboxtext(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_verifyEditBoxExist")
	public boolean VerifyEditBoxExist(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Deprecate,method=Method_verifyEditBoxValue")
	public boolean VerifyEditBoxValue(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyEditBoxValue(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_verifyEditBoxToolTip")
	public boolean VerifyEditBoxToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_verifyEditBoxToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_verifyCheckBoxToolTip")
	public boolean VerifyCheckBoxToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_verifyCheckBoxToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyImageDisabled")
	public boolean VerifyImageDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyImageDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_verifyImageEnabled")
	public boolean VerifyImageEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_verifyImageEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyImageNotVisible")
	public boolean VerifyImageNotVisible(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyImageNotVisible(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Links,method=Method_verifyLinkEnabled")
	public boolean VerifyLinkEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_verifyLinkEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_VerifyDropDownDisabled")
	public boolean VerifyDropDownDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_VerifyDropDownDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyDropDownToolTip")
	public boolean VerifyDropDownToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyDropDownToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyFullTableText")
	public boolean VerifyFullTableText(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyFullTableText(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyMultipleDropDownItemExist")
	public boolean VerifyMultipleDropDownItemExist(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyMultipleDropDownItemExist(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_VerifyMultipleObjectProperty")
	public boolean VerifyMultipleObjectProperty(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_VerifyMultipleObjectProperty(objectarg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_verifyDropDownItemCount")
	public boolean VerifyDropDownItemCount(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_verifyDropDownItemCount(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyButtonDisabled")
	public boolean VerifyButtonDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyButtonDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_verifyEditBoxEnabled")
	public boolean VerifyEditBoxEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_verifyEditBoxEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Button,method=Method_verifyButtonEnabled")
	public boolean VerifyButtonEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_verifyButtonEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_verifyImageToolTip")
	public boolean VerifyImageToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_verifyImageToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_verifyImageVisible")
	public boolean VerifyImageVisible(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_verifyImageVisible(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTableColumnCount")
	public boolean VerifyTableColumnCount(ORObject arg0, int arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableColumnCount(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTableRowText")
	public boolean VerifyTableRowText(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableRowText(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_VerifyRadioButtonNotSelected")
	public boolean VerifyRadioButtonNotSelected(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_VerifyRadioButtonNotSelected(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_verifyTextAreaToolTip")
	public boolean VerifyTextAreaToolTip(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_verifyTextAreaToolTip(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTableColumnText")
	public boolean VerifyTableColumnText(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableColumnText(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreaLength")
	public boolean VerifyTextAreaLength(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreaLength(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreaNotEditable")
	public boolean VerifyTextAreaNotEditable(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreaNotEditable(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Radio,method=Method_VerifyRadioButtonSelected")
	public boolean VerifyRadioButtonSelected(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Radio().Method_VerifyRadioButtonSelected(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreaValue")
	public boolean VerifyTextAreaValue(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreaValue(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTableRowCount")
	public boolean VerifyTableRowCount(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableRowCount(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreanotExist")
	public boolean VerifyTextAreaNotExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreanotExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreaDisabled")
	public boolean VerifyTextAreaDisabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreaDisabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_verifyTextAreaExist")
	public boolean VerifyTextAreaExist(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_verifyTextAreaExist(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreaName")
	public boolean VerifyTextAreaName(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreaName(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextareaColsRowLength")
	public boolean VerifyTextareaColsRowLength(ORObject arg0, int arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextareaColsRowLength(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean VerifyTextAreaEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Table,method=Method_verifyTableRowNumber")
	public boolean VerifyTableRowNumber(ORObject arg0, int arg1, String arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableRowNumber(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_verifyTextAreaDefaultValue")
	public boolean VerifyTextAreaDefaultValue(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_verifyTextAreaDefaultValue(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTableColumnHeader")
	public boolean VerifyTableColumnHeader(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableColumnHeader(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTableColumnNumber")
	public boolean VerifyTableColumnNumber(ORObject arg0, int arg1, String arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTableColumnNumber(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_verifyTextInTable")
	public boolean VerifyTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, String arg4, String arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_verifyTextInTable(objectarg0, arg1, arg2, arg3, arg4, arg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_verifyTextAreaText")
	public boolean VerifyTextAreaText(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_verifyTextAreaText(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_verifyTextAreaEditable")
	public boolean VerifyTextAreaEditable(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_verifyTextAreaEditable(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_selectMultipleDropDownItemAndWait")
	public boolean SelectMultipleDropDownItemAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_selectMultipleDropDownItemAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_deSelectMultipleDropDownItemInTableCell")
	public boolean DeSelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_deSelectMultipleDropDownItemInTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_selectMultipleDropdownItemInTableCell")
	public boolean SelectMultipleDropdownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_selectMultipleDropdownItemInTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getSelectedDropDownInTableCell")
	public String GetSelectedDropDownItemInTableCell(ORObject arg0, int arg1, int arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getSelectedDropDownInTableCell(objectarg0, arg1, arg2, arg3));
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_selectDropDownItemAndWait")
	public boolean SelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_selectDropDownItemAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_GetTextAreaLength")
	public int GetTextAreaLength(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_GetTextAreaLength(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_doubleClickInTableCell")
	public boolean DoubleClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_doubleClickInTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_selectGroupRadioButton")
	public boolean SelectGroupRadioButton(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_selectGroupRadioButton(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=PopUp,method=Method_verifyPopupPresent")
	public boolean VerifyPopupPresent(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new PopUp().Method_verifyPopupPresent(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_GetTextAreaColRowLength")
	public String GetTextAreaColumnRowLength(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_GetTextAreaColRowLength(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_getObjectHeightWidth")
	public String GetObjectHeightWidth(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectHeightWidth(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=UnCategorised,method=Method_AssertTextPresent")
	public boolean AssertTextPresent(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_AssertTextPresent(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_clickInTableCellByQuery")
	public boolean ClickInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Checkbox,method=Method_selectCheckBoxByText")
	public boolean Web_SelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, String arg4, ORObject arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4);
		WebDriverObject objectarg5 = new ObjectConverter().formatObject(arg5);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_selectCheckBoxByText(arg0, arg1, arg2, arg3, arg4, objectarg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_SetFocusonDropDown")
	public boolean SetFocusonDropDown(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_SetFocusonDropDown(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_verifyEditBoxExist")
	public boolean VerifyEditBoxExistAndWait(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=ListControl,method=Method_deselectMultipleDropDownItem")
	public boolean DeSelectMultipleDropDownItem(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ListControl().Method_deselectMultipleDropDownItem(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_deSelectCheckBoxAndWait")
	public boolean DeSelectCheckBoxAndWait(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_deSelectCheckBoxAndWait(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_clickTableCellAndWait")
	public boolean ClickTableCellAndWait(ORObject arg0, int arg1, int arg2, String arg3, int arg4, int arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_clickTableCellAndWait(objectarg0, arg1, arg2, arg3, arg4, arg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_typeTextandEnterTextArea")
	public boolean TypeTextAndEnterTextArea(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_typeTextandEnterTextArea(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_typeKeysInTextArea")
	public boolean TypeKeysInTextArea(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_typeKeysInTextArea(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getSingleRowText")
	public String GetSingleTableRowText(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getSingleRowText(objectarg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_getObjectProperty")
	public String GetObjectProperty(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectProperty(objectarg0, arg1));
		return functionResult.getOutput();
	}

	@Keyword("class=Window,method=Method_closeSelectedWindow")
	public boolean CloseSelectedWindow(String arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Window().Method_closeSelectedWindow(arg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_clickButtonAndWait")
	public boolean ClickButtonAndWait(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_clickButtonAndWait(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_clickButtonInTableCell")
	public boolean ClickButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_clickButtonInTableCell(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_getObjectExistence")
	public boolean GetObjectExistence(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectExistence(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_getDropDownToolTip")
	public String GetDropDownToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_getDropDownToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_selectRadioButtonAndWait")
	public boolean SelectRadioButtonAndWait(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_selectRadioButtonAndWait(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_typeTextOnEditBox")
	public boolean TypeTextOnEditBox(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_typeTextOnEditBox(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_getSelectedDropDownItem")
	public String GetSelectedDropDownItem(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_getSelectedDropDownItem(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=DropDown,method=Method_selectDropDownItem")
	public boolean SelectDropDownItem(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_selectDropDownItem(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_selectRadioButtonOnIndexBasis")
	public boolean SelectRadioButtonOnIndexBasis(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_selectRadioButtonOnIndexBasis(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_clickLinkInTableCell")
	public boolean ClickLinkInTableCell(ORObject arg0, int arg1, int arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_clickLinkInTableCell(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_typeTextInTextArea")
	public boolean TypeTextInTextArea(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_typeTextInTextArea(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_selectDropDownInTableCell")
	public boolean SelectDropDownInTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_selectDropDownInTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_deselectAllDropDownItemsAndWait")
	public boolean DeSelectAllDropDownItemsAndWait(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=DropDown,method=Method_getDropDownItemCount")
	public int GetDropDownItemCount(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_getDropDownItemCount(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_TableGetTextRow")
	public int GetTableRowNumber(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_TableGetTextRow(objectarg0, arg1, arg2));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_getTextFromEditBox")
	public String GetTextFromEditBox(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_getTextFromEditBox(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Radio,method=Method_SelectRadio")
	public boolean SelectRadioButton(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Radio().Method_SelectRadio(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_returnConcatenated")
	public String ReturnConcatenated(String arg0, String arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_returnConcatenated(arg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_getSingleColText")
	public String GetSingleTableColumnText(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getSingleColText(objectarg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_fetchObjectPropertyInTableCell")
	public String FetchObjectPropertyInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_fetchObjectPropertyInTableCell(objectarg0, arg1, arg2, arg3, arg4, arg5));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_waitForObjectEditable")
	public boolean WaitForObjectEditable(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_waitForObjectEditable(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_waitForObjectProperty")
	public boolean WaitForObjectProperty(ORObject arg0, String arg1, String arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_waitForObjectProperty(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_deselectDropDownItem")
	public boolean DeSelectDropDownItem(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_deselectDropDownItem(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=UnCategorised,method=Method_copyFromClipBoard")
	public String CopyFromClipBoard(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_copyFromClipBoard());
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_getSelectedRadioButtonFromGroup")
	public String GetSelectedRadioButtonFromGroup(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_getSelectedRadioButtonFromGroup(objectarg0, arg1));
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_getRadioButtonCount")
	public int GetRadioButtonCount(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_getRadioButtonCount(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_deselectDropDownItemAndWait")
	public boolean DeSelectDropDownItemAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_deselectDropDownItemAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_fetchBrowserTitle")
	public String FetchBrowserTitle(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_fetchBrowserTitle(arg0));
		return functionResult.getOutput();
	}

	@Keyword("class=EditBox,method=Method_typeKeysOnEditBox")
	public boolean TypeKeysOnEditBox(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_typeKeysOnEditBox(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_doubleClickAndWait")
	public boolean DoubleClickAndWait(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_doubleClickAndWait(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_GetTextfromTextArea")
	public String GetTextfromTextArea(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_GetTextfromTextArea(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Checkbox,method=Method_getCheckBoxToolTip")
	public String GetCheckBoxToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_getCheckBoxToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean SelectCheckBoxinTableCell(ORObject arg0, int arg1, int arg2, int arg3, String arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Deprecate,method=Method_getChildObjectCount")
	public int GetChildObjectCount(ORObject arg0, String arg1, String arg2, String arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_getChildObjectCount(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_deFocusfromDropDown")
	public boolean DeFocusfromDropDown(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_deFocusfromDropDown());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_selectCheckBoxAndWait")
	public boolean SelectCheckBoxAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_selectCheckBoxAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=ListControl,method=Method_selectMultipleDropDownItem")
	public boolean SelectMultipleDropDownItem(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ListControl().Method_selectMultipleDropDownItem(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Button,method=Method_doubleClickButton")
	public boolean DoubleClickButton(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_doubleClickButton(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_dragAndDropAndWait")
	public boolean DragAndDropAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_dragAndDropAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_getTextFromTableCellByQuery")
	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return "";
	}

	@Keyword("class=Browser,method=Method_getTextFromTableCellByQuery")
	public String GetTextFromTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, int arg6, int arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return "";
	}

	@Keyword("class=Browser,method=Method_clickInTableCellByQuery")
	public boolean ClickOnObjectInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, int arg7, String arg8, int arg9, String arg10, String arg11, String arg12, String arg13, String arg14){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=EditBox,method=Method_getEditboxDefaultvalue")
	public String GetEditboxDefaultvalue(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_getEditboxDefaultvalue(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_waitForEditBoxDisabled")
	public boolean WaitForEditBoxDisabled(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_waitForEditBoxDisabled(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_captureObjectSnapShot")
	public String CaptureObjectSnapshot(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_captureObjectSnapShot(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Window,method=Method_waitForWindowLoad")
	public boolean Web_WaitForWindowLoad(String arg0, int arg1, boolean arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Window().Method_waitForWindowLoad(arg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_WebGetTableColCount")
	public int Web_GetTableColumnCount(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_WebGetTableColCount(objectarg0, arg1));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_typeOnObjecttInTableCell")
	public boolean TypeOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_typeOnObjecttInTableCell(objectarg0, arg1, arg2, arg3, arg4, arg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getTableColCount")
	public int GetTableColumnCount(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getTableColCount(objectarg0, arg1));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Frame,method=Method_switchToDefaultContent")
	public boolean SwitchToDefaultContent(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Frame().Method_switchToDefaultContent());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_waitForEditBoxEnabled")
	public boolean WaitForEditBoxEnabled(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_waitForEditBoxEnabled(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_getTextAreaDefaultvalue")
	public String GetTextAreaDefaultvalue(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_getTextAreaDefaultvalue(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=EditBox,method=Method_SetfocusEditField")
	public boolean SetfocusEditField(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_SetfocusEditField(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_waitforobjectenable")
	public boolean WaitForObjectEnable(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_waitforobjectenable(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Web_SetFocusOnCurrentWindow")
	public boolean Web_SetFocusOnCurrentWindow(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=UnCategorised,method=Method_ignoreXMLHttpRequest")
	public boolean IgnoreXMLHttpRequest(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_ignoreXMLHttpRequest(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=ImageComparisionUsingNodeJS,method=Custom_visualComparionForPage")
	public String VisualComparisonForPage(String arg0, String arg1, boolean arg2, boolean arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ImageComparisionUsingNodeJS().Custom_visualComparionForPage(arg0, arg1, arg2, arg3));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_getCompleteTableText")
	public String GetCompleteTableText(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getCompleteTableText(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=UnCategorised,method=Method_IsTextPresentOnScreen_Generic")
	public boolean IsTextPresentOnScreen(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_IsTextPresentOnScreen_Generic(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_selectRadioButtobTableCell")
	public boolean SelectRadioButtonInTableCell(ORObject arg0, int arg1, int arg2, int arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_selectRadioButtobTableCell(objectarg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_clickImageByAltText")
	public boolean ClickImageByTitleAlt(String arg0, int arg1, boolean arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_clickImageByAltText(arg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=ListControl,method=Method_selectListItem")
	public boolean Web_SelectListItem(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ListControl().Method_selectListItem(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_deSelectCheckBoxByText")
	public boolean Web_DeSelectCheckboxByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		WebDriverObject objectarg4 = new ObjectConverter().formatObject(arg4);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_deSelectCheckBoxByText(arg0, arg1, arg2, arg3, objectarg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=SetCapabilities,method=Method_SetBrowserCapability")
	public boolean SetBrowserCapability(String arg0, String arg1, String arg2, String arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new SetCapabilities().Method_SetBrowserCapability(arg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_typeTextInTableCell")
	public boolean TypeTextInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4, String arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4, arg5);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_typeTextInTableCell(objectarg0, arg1, arg2, arg3, arg4, arg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_waitforobjectvisible")
	public boolean WaitForObjectVisible(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_waitforobjectvisible(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_selectDropDownByText")
	public boolean Web_SelectDropDownByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, boolean arg5, ORObject arg6){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4, arg5);
		WebDriverObject objectarg6 = new ObjectConverter().formatObject(arg6);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_selectDropDownByText(arg0, arg1, arg2, arg3, arg4, arg5, objectarg6));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=DropDown,method=Method_getDropDownDefaultValue")
	public String GetDropdownDefaultItem(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new DropDown().Method_getDropDownDefaultValue(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=EditBox,method=Method_getEditBoxToolTip")
	public String GetEditBoxToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_getEditBoxToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_getObjectVisibility")
	public boolean GetObjectVisibility(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectVisibility(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=ListControl,method=Method_verifyListItemExists")
	public boolean Web_VerifyListItemExists(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ListControl().Method_verifyListItemExists(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getTableColumnHeader")
	public String GetTableColumnHeader(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getTableColumnHeader(objectarg0, arg1));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_WebGetTableRowCount")
	public int Web_GetTableRowCount(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_WebGetTableRowCount(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_clickByTextInSequence")
	public boolean Web_ClickByTextInSequence(String arg0, int arg1, int arg2, boolean arg3, String arg4, int arg5, boolean arg6, ORObject arg7, ORObject arg8, ORObject arg9, ORObject arg10, ORObject arg11, boolean arg12, String arg13, int arg14, boolean arg15, String arg16, int arg17, boolean arg18, String arg19){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Table,method=Method_clickOnObjectInTableCell")
	public boolean ClickOnObjectInTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_clickOnObjectInTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_rightClickAndSelect")
	public boolean RightClickOnObject(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_rightClickAndSelect(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Radio,method=Method_selectRadioButtonByText")
	public boolean Web_SelectRadioButtonByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		WebDriverObject objectarg4 = new ObjectConverter().formatObject(arg4);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Radio().Method_selectRadioButtonByText(arg0, arg1, arg2, arg3, objectarg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_rightClickAndSelectByText")
	public boolean RightClickAndSelectByText(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean SynchronizeBrowser(boolean arg0, boolean arg1, boolean arg2, boolean arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_syncBrowser(arg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_getObjectCSSProperty")
	public String GetObjectCSSProperty(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectCSSProperty(objectarg0, arg1));
		return functionResult.getOutput();
	}

	@Keyword("class=EditBox,method=Method_typeTextandEnterEditBox")
	public boolean TypeTextAndEnterEditBox(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_typeTextandEnterEditBox(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_waitForObjectdisable")
	public boolean WaitForObjectDisable(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_waitForObjectdisable(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_getCheckboxStatus")
	public String GetCheckboxStatus(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_getCheckboxStatus(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=TextArea,method=Method_getTextAreaToolTip")
	public String GetTextAreaToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_getTextAreaToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_TableGetTextColumn")
	public int GetTableColumnNumber(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_TableGetTextColumn(objectarg0, arg1, arg2));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_typeTextInTableCellByQuery")
	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, int arg7, int arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean TypeTextInTableCellByQuery(ORObject arg0, String arg1, String arg2, String arg3, String arg4, String arg5, int arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Radio,method=Method_deFocusRadioButton")
	public boolean DeFocusRadioButton(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Radio().Method_deFocusRadioButton());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_WebBrowserOpen")
	public boolean OpenBrowser(String arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_WebBrowserOpen(arg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_typeKeysAndWait")
	public boolean TypeKeysAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_typeKeysAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_doubleClickAt")
	public boolean DoubleClickAt(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_doubleClickAt(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Links,method=Method_waitforLink")
	public boolean WaitforLink(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_waitforLink(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_waitforObject")
	public boolean WaitForObject(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_waitforObject(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_selectCheckBox")
	public boolean SelectCheckBox(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_selectCheckBox(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}
	
	@Keyword("class=Checkbox,method=Method_selectCheckBox")
	public boolean SelectCheckBox(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_selectCheckBox(objectarg0,"ON"));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_dblClick")
	public boolean DoubleClick(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_dblClick(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_goBackAndWait")
	public boolean GoBackAndWait(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_goBackAndWait(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_CaptureSnapshot")
	public boolean CaptureSnapshot(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_CaptureSnapshot(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_focusRadioButton")
	public boolean FocusRadioButton(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_focusRadioButton(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_refreshAndWait")
	public boolean RefreshAndWait(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_refreshAndWait(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_wait")
	public boolean Wait(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Deprecate,method=Method_goForwardAndWait")
	public boolean GoForwardAndWait(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_goForwardAndWait(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_clickImage")
	public boolean ClickImage(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_clickImage(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_getAllButtons")
	public String GetAllButtons(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_getAllButtons());
		return functionResult.getOutput();
	}

	@Keyword("class=Button,method=Method_focusButton")
	public boolean FocusButton(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_focusButton(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_SetfocusTextArea")
	public boolean SetfocusTextArea(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_SetfocusTextArea(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean VerifyPopUpText(ORObject arg0, String arg1, String arg2, String arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=TextArea,method=Method_clearTextArea")
	public boolean ClearTextArea(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_clearTextArea(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=UnCategorised,method=Method_KeyLeft")
	public boolean KeyLeft(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_KeyLeft());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_GetCellText")
	public String GetTableCellText(ORObject arg0, int arg1, int arg2, String arg3, String arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_GetCellText(objectarg0, arg1, arg2, arg3, arg4));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_clickTableCell")
	public boolean ClickTableCell(ORObject arg0, int arg1, int arg2, String arg3, int arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2, arg3, arg4);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_clickTableCell(objectarg0, arg1, arg2, arg3, arg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_waitforImageLoad")
	public boolean WaitforImageLoad(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_waitforImageLoad(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_keyPressNative")
	public boolean KeyPressNative(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_keyPressNative(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_deFocusTextArea")
	public boolean DeFocusTextArea(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_deFocusTextArea());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Links,method=Method_getLinkCount")
	public int GetLinkCount(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_getLinkCount());
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=UnCategorised,method=Method_PressTAB")
	public boolean PressTAB(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_PressTAB());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_ObjectClick")
	public boolean Click(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_ObjectClick(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_deFocusCheckBox")
	public boolean DeFocusCheckBox(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_deFocusCheckBox());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_RefreshBrowser")
	public boolean RefreshBrowser(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_RefreshBrowser());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_clearEditField")
	public boolean ClearEditField(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_clearEditField(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_fetchBrowserURL")
	public String FetchBrowserURL(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_fetchBrowserURL());
		return functionResult.getOutput();
	}

	@Keyword("class=Browser,method=Method_syncBrowser")
	public boolean SyncBrowser(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_syncBrowser());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_doubleClickImage")
	public boolean DoubleClickImage(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_doubleClickImage(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Window,method=Method_setFocousOnWindow")
	public boolean SetFocusOnWindow(int arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Window().Method_setFocousOnWindow(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_getAllFields")
	public String GetAllFields(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_getAllFields());
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_SetFocus")
	public boolean SetFocus(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_SetFocus(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getFullTableText")
	public String GetFullTableText(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getFullTableText(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Browser,method=Method_CloseAllBrowsers")
	public boolean CloseAllBrowsers(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_CloseAllBrowsers());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_GetAllTitles")
	public String GetAllTitles(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_GetAllTitles(arg0));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_highlightObject")
	public boolean HighlightObject(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_highlightObject(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_dragAndDrop")
	public boolean DragAndDrop(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_dragAndDrop(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Checkbox,method=Method_deSelectCheckBox")
	public boolean DeSelectCheckBox(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_deSelectCheckBox(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Window,method=Method_selectWindow")
	public boolean SelectWindow(String arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Window().Method_selectWindow(arg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_getPropertyValue")
	public String GetPropertyValue(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getPropertyValue(objectarg0, arg1));
		return functionResult.getOutput();
	}

	@Keyword("class=PopUp,method=Method_Getpopuptext")
	public String GetPopupText(ORObject arg0, String arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new PopUp().Method_Getpopuptext(objectarg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_GetElementIndex")
	public int GetElementIndex(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_GetElementIndex(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_goForward")
	public boolean GoForward(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_goForward());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Button,method=Method_deFocusButton")
	public boolean DeFocusButton(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_deFocusButton());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_getAllLinks")
	public String GetAllLinks(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_getAllLinks());
		return functionResult.getOutput();
	}

	@Keyword("class=UnCategorised,method=Method_KeyRight")
	public boolean KeyRight(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_KeyRight());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_typeTextAndWait")
	public boolean TypeTextAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_typeTextAndWait(objectarg0, arg1, arg2));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Button,method=Method_clickButton")
	public boolean ClickButton(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_clickButton(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_CloseBrowser")
	public boolean CloseBrowser(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_CloseBrowser(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_runScriptAndWait")
	public boolean RunScriptAndWait(ORObject arg0, String arg1, int arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Links,method=Method_clickLink")
	public boolean ClickLink(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_clickLink(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_MaximizeBrowser")
	public boolean MaximizeBrowser(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_MaximizeBrowser());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_swipeWithObject")
	public boolean SwipeObject(ORObject arg0, int arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Links,method=Method_getLinkToolTip")
	public String GetLinkToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Links().Method_getLinkToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=EditBox,method=Method_GetEditBoxName")
	public String GetEditBoxName(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_GetEditBoxName(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Button,method=Method_getButtonToolTip")
	public String GetButtonToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Button().Method_getButtonToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Browser,method=Method_navigateTo")
	public boolean NavigateTo(String arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_navigateTo(arg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getTableRowCount")
	public int GetTableRowCount(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getTableRowCount(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Image,method=Method_getImageToolTip")
	public String GetImageToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_getImageToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_getObjectCount")
	public int GetObjectCount(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectCount(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=ByTextKeywords,method=Method_mouseHoverOnText")
	public boolean MouseHoverOnText(String arg0, int arg1, boolean arg2, ORObject arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2);
		WebDriverObject objectarg3 = new ObjectConverter().formatObject(arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ByTextKeywords().Method_mouseHoverOnText(arg0, arg1, arg2, objectarg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_getObjectValue")
	public String GetObjectValue(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectValue(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Browser,method=Method_MinimizeBrowser")
	public boolean MinimizeBrowser(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_MinimizeBrowser());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_goBack")
	public boolean Web_GoBack(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Browser().Method_goBack());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Table,method=Method_getAllColText")
	public String GetAllColumnText(ORObject arg0, String arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getAllColText(objectarg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=Table,method=Method_getAllRowText")
	public String GetAllRowText(ORObject arg0, String arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Table().Method_getAllRowText(objectarg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=Frame,method=Method_selectFrame")
	public boolean SelectFrame(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Frame().Method_selectFrame(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_setPage")
	public boolean SetPage(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_setPage(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_GetObjectText")
	public String GetObjectText(ORObject arg0, String arg1, String arg2){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1, arg2);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_GetObjectText(objectarg0, arg1, arg2));
		return functionResult.getOutput();
	}

	@Keyword("class=PopUp,method=Method_acceptPopup")
	public boolean AcceptPopup(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new PopUp().Method_acceptPopup());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=ByTextKeywords,method=Method_SelectByText")
	public boolean Web_SelectByText(String arg0, int arg1, boolean arg2, boolean arg3, ORObject arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		WebDriverObject objectarg4 = new ObjectConverter().formatObject(arg4);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new ByTextKeywords().Method_SelectByText(arg0, arg1, arg2, arg3, objectarg4));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_typeTextUsingText")
	public boolean Web_TypeByText(String arg0, int arg1, boolean arg2, String arg3, boolean arg4, ORObject arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3, arg4);
		WebDriverObject objectarg5 = new ObjectConverter().formatObject(arg5);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_typeTextUsingText(arg0, arg1, arg2, arg3, arg4, objectarg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=UnCategorised,method=Method_MouseHover")
	public boolean MouseHover(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_MouseHover(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=TextArea,method=Method_getTextAreavalue")
	public String GetTextAreavalue(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_getTextAreavalue(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=EditBox,method=Method_deFocusEditField")
	public boolean DeFocusEditField(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_deFocusEditField());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_clickAt")
	public boolean ClickAt(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_clickAt(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_TypeTextInContentEditable")
	public boolean TypeTextInPTag(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_TypeTextInContentEditable(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_SwipeLeft")
	public boolean SwipeLeft(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Checkbox,method=Method_focusCheckBox")
	public boolean FocusCheckBox(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Checkbox().Method_focusCheckBox(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_typeSecureText")
	public boolean TypeSecureText(ORObject arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_typeSecureText(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_SwipeRight")
	public boolean SwipeRight(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=TextArea,method=Method_getTextAreaName")
	public String GetTextAreaName(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new TextArea().Method_getTextAreaName(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=WebObjects,method=Method_clickByText")
	public boolean Web_ClickByText(String arg0, int arg1, boolean arg2, ORObject arg3, String arg4, String arg5){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg4, arg5);
		WebDriverObject objectarg3 = new ObjectConverter().formatObject(arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_clickByText(arg0, arg1, arg2, objectarg3, arg4, arg5));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_reportMessage")
	public boolean ReportMessage(String arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_reportMessage(arg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_deFocusObject")
	public boolean DeFocusObject(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_deFocusObject());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=Browser,method=Method_getLoadingTime")
	public double GetLoadTime(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return 0;
	}

	@Keyword("class=Browser,method=Method_swipe")
	public boolean Swipe(double arg0, double arg1, double arg2, double arg3, double arg4){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=Browser,method=Method_MobilitySwipe")
	public boolean Swipe(int arg0, String arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ReportHelper.addReportStep(keywordName, new Exception("Not Executed"));
		return false;
	}

	@Keyword("class=EditBox,method=Method_getEditboxLength")
	public int GetEditBoxLength(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_getEditboxLength(objectarg0));
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=EditBox,method=Method_getEditboxValue")
	public String GetEditboxValue(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new EditBox().Method_getEditboxValue(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Image,method=Method_getImageCount")
	public int GetImageCount(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Image().Method_getImageCount());
		return DataTypeConverter.getInt(functionResult.getOutput());
	}

	@Keyword("class=Deprecate,method=Method_nextPageObject")
	public boolean NextPageObject(ORObject arg0, int arg1){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg1);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_nextPageObject(objectarg0, arg1));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_getObjectEnabled")
	public boolean GetObjectEnabled(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectEnabled(objectarg0));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=WebObjects,method=Method_getObjectToolTip")
	public String GetObjectToolTip(ORObject arg0){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		WebDriverObject objectarg0 = new ObjectConverter().formatObject(arg0);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new WebObjects().Method_getObjectToolTip(objectarg0));
		return functionResult.getOutput();
	}

	@Keyword("class=Deprecate,method=Method_excelCompare")
	public boolean ExcelCompare(String arg0, String arg1, String arg2, String arg3){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		ContextInitiator.addDataRgumentsInFunctionCall(arg0, arg1, arg2, arg3);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new Deprecate().Method_excelCompare(arg0, arg1, arg2, arg3));
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=PopUp,method=Method_dismissPopup")
	public boolean DismissPopup(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new PopUp().Method_dismissPopup());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}

	@Keyword("class=UnCategorised,method=Method_Enter")
	public boolean Enter(){
		String keywordName = DataTypeConverter.getMethodName();
		ContextInitiator.addFunction(keywordName);
		System.out.println("Keyword called: " + keywordName);
		FunctionResult functionResult = KeywordExecutor.execute(() -> new UnCategorised().Method_Enter());
		return DataTypeConverter.getBoolean(functionResult.getOutput());
	}
}
