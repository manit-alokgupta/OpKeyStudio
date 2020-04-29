package com.opkey.OpKeyGenericPlugin;

import java.util.ArrayList;
import java.util.List;

import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.DataArguments.DataArgument;
import com.crestech.opkey.plugin.communication.contracts.functioncall.FunctionCall.ObjectArguments.ObjectArgument;
import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.crestech.opkey.plugin.contexts.Context;
import com.ssts.reporting.ReportBuilder;
import com.ssts.reporting.Status;

public class ReportHelper {

	public static void addReportStep(String methodName, FunctionResult functionResult) {
		Status status = Status.valueOf(functionResult.getStatus().toUpperCase());
		List<String> parameterList = new ArrayList<String>();

		FunctionCall fc = Context.current().getFunctionCall();
		for (ObjectArgument oa : fc.getObjectArguments().getObjectArgument()) {
			parameterList.add(oa.getObject().getLogicalName());
		}

		for (DataArgument oa : fc.getDataArguments().getDataArgument()) {
			parameterList.add(oa.getArgumentName() + ":" + oa.getValue());
		}

		ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), status,
				functionResult.getOutput());
	}

	public static void addReportStep(String methodName, Exception e) {

		List<String> parameterList = new ArrayList<String>();
		ReportBuilder.get().addStep(methodName, parameterList.toArray(new String[parameterList.size()]), Status.FAIL,
				e.getMessage());
	}
}
