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
		
		if(!functionResult.getMessage().isEmpty()) {
			parameters.put("Message", functionResult.getMessage());
			
		}
	
		Status status = Status.valueOf(functionResult.getOutput().toUpperCase()); 
		Report.get().addStep(parameters, status);
	}
	
	public static FunctionResult getFailFunctionResult(Exception e) {
		FunctionResult functionResult = new FunctionResult();
		functionResult.setOutput("false");
		functionResult.setMessage(e.getMessage());
		
		return functionResult;
	}
}
