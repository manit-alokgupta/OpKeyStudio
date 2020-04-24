package com.opkey.OpKeyGenericPlugin;

import java.util.HashMap;

import com.crestech.opkey.plugin.communication.contracts.functionresult.FunctionResult;
import com.ssts.reporting.Report;
import com.ssts.reporting.Status;

public class ReportHelper {

	public static void addReportStep(String methodName, FunctionResult functionResult) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("Action", methodName);
		parameters.put("Output", functionResult.getOutput());
		parameters.put("Message", functionResult.getMessage());

		Status status = Status.valueOf(functionResult.getStatus().toUpperCase());
		Report.get().addStep(parameters, status);
	}

	public static void addReportStep(String methodName, Exception e) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("Action", methodName);
		Report.get().addStep(parameters, Status.FAIL, e);
	}
}
