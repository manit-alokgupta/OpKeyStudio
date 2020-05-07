package com.opkey.appium;

import java.util.ArrayList;
import java.util.List;

import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments.DataArgument;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments.ObjectArgument;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.contexts.Context;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.Status;

public class ReportHelper {

	public static void addReportStep(String methodName, FunctionResult functionResult) {
		Status status = Status.valueOf(functionResult.getStatus().toUpperCase());
		System.out.println("@Status: " + status);
		
		List<String> parameterList = new ArrayList<String>();

		parameterList = getParameters();
		ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), status,
				functionResult.getOutput());
	}

	public static void addReportStep(String methodName, Exception e) {

		List<String> parameterList = new ArrayList<String>();
		
		ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), Status.FAIL,
				e.getMessage());
	}
	
	public static List<String> getParameters() {
		List<String> parameterList = new ArrayList<String>();
		
		ObjectArguments objectArguments = Context.current().getFunctionCall().getObjectArguments();
		if(objectArguments!=null && objectArguments.getObjectArgument() !=null) {
			for(ObjectArgument objectArgument: objectArguments.getObjectArgument()) {
				parameterList.add(objectArgument.getArgumentName() + ":" + objectArgument.getObject().getLogicalName());
			}
		}
		
		DataArguments dataArguments = Context.current().getFunctionCall().getDataArguments();
		if(dataArguments!=null && dataArguments.getDataArgument() !=null) {
			for(DataArgument dataArgument: dataArguments.getDataArgument()) {
				parameterList.add(dataArgument.getArgumentName() + ":" + dataArgument.getValue());
			}
		}
		
		return parameterList;
	}
}
