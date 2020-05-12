package opkeystudio.opkeystudiocore.core.apis.dbapi.usedby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GVUsedBy {
	private List<FlowInputArgument> getCFLUsedInFlowDesignSteps(GlobalVariable artifact) {
		String query = String.format("select * from flow_step_input_arguments where GlobalVariable_ID='%s'",
				artifact.getGv_id());
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowInputArgument>();
	}

	private List<FlowInputArgument> getCFLUsedInComponentDesignSteps(GlobalVariable artifact) {
		String query = String.format("select * from component_step_input_args where GlobalVariable_ID='%s'",
				artifact.getGv_id());
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowInputArgument>();
	}

	public boolean isGVIsUsed(GlobalVariable gv) {
		List<FlowInputArgument> flowDesignSteps = getCFLUsedInFlowDesignSteps(gv);
		List<FlowInputArgument> componentDesignSteps = getCFLUsedInComponentDesignSteps(gv);
		if (flowDesignSteps.size() > 0) {
			return true;
		}
		if (componentDesignSteps.size() > 0) {
			return true;
		}
		return false;
	}
}
