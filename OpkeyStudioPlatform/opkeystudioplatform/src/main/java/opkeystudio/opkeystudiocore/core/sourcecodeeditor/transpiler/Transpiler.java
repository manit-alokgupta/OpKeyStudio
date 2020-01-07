package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class Transpiler {
	public void transpileDatas(TranspileObject transpileObject) {
		Artifact artifact = transpileObject.getArtifact();
		List<GlobalVariable> globalVariables = transpileObject.getGlobalVaribales();
		List<FlowStep> flowSteps = transpileObject.getFlowSteps();
		List<FlowStep> functionLibaries = getFunctionLibraries(flowSteps);
		for (FlowStep functionLibrary : functionLibaries) {
			if (functionLibrary.getFunctionLibraryComponent() != null) {
				System.out.println(functionLibrary.getFunctionLibraryComponent().getName());
			}
		}
	}

	private List<FlowStep> getFunctionLibraries(List<FlowStep> allFlowSteps) {
		List<FlowStep> allFunctionLibraries = new ArrayList<FlowStep>();
		for (FlowStep flowStep : allFlowSteps) {
			if (flowStep.getFunctionLibraryComponent() != null) {
				allFunctionLibraries.add(flowStep);
				try {
					List<FlowStep> flowSteps = new FunctionLibraryApi()
							.getAllFlowSteps(flowStep.getFunctionLibraryComponent().getId());
					getFunctionLibraries(flowSteps);
				} catch (JsonParseException | JsonMappingException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return allFunctionLibraries;
	}

	private List<ORObject> getAllORObjects(List<FlowStep> allFlowSteps) {
		List<ORObject> allORObjects = new ArrayList<ORObject>();
		for (FlowStep flowStep : allFlowSteps) {
			allORObjects.addAll(flowStep.getOrObject());
		}
		return allORObjects;
	}
}
