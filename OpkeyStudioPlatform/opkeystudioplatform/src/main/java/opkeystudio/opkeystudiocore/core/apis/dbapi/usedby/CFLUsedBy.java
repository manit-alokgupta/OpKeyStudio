package opkeystudio.opkeystudiocore.core.apis.dbapi.usedby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class CFLUsedBy {
	private List<FlowStep> getCFLUsedInFlowDesignSteps(Artifact artifact) {
		String query = String.format(
				"SELECT * FROM flow_design_steps where codedfunction_id='%s' ORDER BY position asc", artifact.getId());
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowStep>();
	}

	private List<FlowStep> getCFLUsedInComponentDesignSteps(Artifact artifact) {
		String query = String.format(
				"SELECT * FROM component_design_steps where stepcodedfunction_id='%s' ORDER BY position asc",
				artifact.getId());
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowStep.class);
		try {
			return mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<FlowStep>();
	}

	public boolean isCFLIsUsed(Artifact artifact) {
		List<FlowStep> flowDesignSteps = getCFLUsedInFlowDesignSteps(artifact);
		List<FlowStep> componentDesignSteps = getCFLUsedInComponentDesignSteps(artifact);
		if (flowDesignSteps.size() > 0) {
			return true;
		}
		if (componentDesignSteps.size() > 0) {
			return true;
		}
		return false;
	}
}
