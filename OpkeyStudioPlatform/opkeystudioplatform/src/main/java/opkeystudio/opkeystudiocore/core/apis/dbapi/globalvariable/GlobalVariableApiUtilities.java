package opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalVariableApiUtilities {
	public boolean isGlobalVariableUsed(GlobalVariable globalVariable) {
		List<FlowInputArgument> flowStepInputArguments = getAssociatedFlowInputArguments(globalVariable);
		List<FlowInputArgument> componentStepInputArguments = getAssociatedComponentFlowInputArguments(globalVariable);
		if (flowStepInputArguments.size() == 0 && componentStepInputArguments.size() == 0) {
			return false;
		}
		return true;
	}

	private List<FlowInputArgument> getAssociatedFlowInputArguments(GlobalVariable globalVariable) {
		String query = String.format("SELECT * FROM flow_step_input_arguments WHERE GlobalVariable_ID='%s'",
				globalVariable.getGv_id());
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<FlowInputArgument>();
	}

	private List<FlowInputArgument> getAssociatedComponentFlowInputArguments(GlobalVariable globalVariable) {
		String query = String.format("SELECT * FROM component_step_input_args WHERE GlobalVariable_ID='%s'",
				globalVariable.getGv_id());
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<FlowInputArgument>();
	}
}
