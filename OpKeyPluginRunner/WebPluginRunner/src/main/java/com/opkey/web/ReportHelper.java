package com.opkey.web;

import java.io.File;
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
		List<String> parameterList = getParameters();
		
		if(functionResult.getSnapshotPath() != null) {
			ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), status,
					functionResult.getOutput(), new File(functionResult.getSnapshotPath()));
		}else {
			ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), status,
					functionResult.getOutput());
		}

		logStep(methodName, functionResult.getOutput(), functionResult.getStatus());
	}

	public static void addReportStep(String methodName, Exception e) {

		List<String> parameterList = getParameters();
		ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), Status.FAIL,
				e.getMessage());
		logStep(methodName, e.getMessage(), Status.FAIL.name());
	}

	private static void logStep(String methodName, String output, String status) {
		System.out.println("<<<< " + methodName + "(" + String.join(", ", getParameters()) + ") " + " -> " + output
				+ " " + status);
	}

	public static List<String> getParameters() {
		List<String> parameterList = new ArrayList<String>();
		ObjectArguments orArguments = Context.current().getFunctionCall().getObjectArguments();
		String objectArgs = "";
		if (orArguments != null && orArguments.getObjectArgument() != null) {
			for (ObjectArgument objectArg : orArguments.getObjectArgument()) {
				objectArgs += objectArg.getObject().getLogicalName() +" ;";
			}
			//parameterList.add(objectArgs);
		}

		DataArguments dataArguments = Context.current().getFunctionCall().getDataArguments();
		if (dataArguments != null && dataArguments.getDataArgument() != null) {
			for (DataArgument dataArgument : dataArguments.getDataArgument()) {
				//parameterList.add(dataArgument.getValue());
				objectArgs += dataArgument.getValue() + ",";
			}
			objectArgs = objectArgs.substring(0, objectArgs.length()-1);
		}

		parameterList.add(objectArgs);
		return parameterList;
	}
}
