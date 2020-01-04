package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class FlowApiUtilities {
	public List<FlowOutputArgument> getAllFlowOutPutArgument(String flow_step_oa_id) {
		String query = String.format("SELECT * from flow_step_output_arguments WHERE flow_step_oa_id='%s'",
				flow_step_oa_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowOutputArgument>();
	}

	public String getFlowOutPutArgumentsString(FlowStep flowStep) {
		String outData = "";
		List<FlowOutputArgument> flowStepOutputargs = flowStep.getFlowOutputArgs();
		for (FlowOutputArgument flowOutputArgument : flowStepOutputargs) {
			if (flowOutputArgument.getOutputvariablename() != null) {
				outData += flowOutputArgument.getOutputvariablename();
			}
		}
		if (!outData.isEmpty()) {
			return "Output: " + outData;
		}
		return outData;
	}

	public String getFlowInputArgumentsString(FlowStep flowStep) {
		String outData = "";
		for (FlowInputArgument flowInputArgument : flowStep.getFlowInputArgs()) {
			if (flowInputArgument.getStaticvalue() != null) {
				outData += flowInputArgument.getStaticvalue();
			}
		}
		return outData;
	}

}
