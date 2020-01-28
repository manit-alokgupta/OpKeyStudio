package opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ArtifactApiUtilities {
	public boolean isArtifactIsUsed(Artifact artifact) {
		List<FlowStep> flowDesignSteps = getAssociatedFlowDesignSteps(artifact);
		List<FlowStep> componentDesignSteps = getAssociatedComponentDesignSteps(artifact);
		if (flowDesignSteps.size() == 0 && componentDesignSteps.size() == 0) {
			return false;
		}
		return true;
	}

	private List<FlowStep> getAssociatedFlowDesignSteps(Artifact artifact) {
		String query = String.format(
				"SELECT * FROM flow_design_steps where component_id='%s' or codedfunction_id='%s' or soapmethod_id='%s' or restmethod_id='%s'",
				artifact.getId(), artifact.getId(), artifact.getId(), artifact.getId());
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

	private List<FlowStep> getAssociatedComponentDesignSteps(Artifact artifact) {
		String query = String.format(
				"SELECT * FROM component_design_steps where stepcomponent_id='%s' or stepcodedfunction_id='%s' or soapmethod_id='%s' or restmethod_id='%s'",
				artifact.getId(), artifact.getId(), artifact.getId(), artifact.getId());
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

	public void createMainArtifactClob(Artifact artifact) {
		String query = String.format(
				"INSERT INTO main_artifact_clob('id', 'description', 'ExpectedResult') VALUES ('%s', '%s', '%s');",
				artifact.getId(), "", "");
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}

	public void createFlowManualTestCase(Artifact artifact) {
		String query = String.format("INSERT INTO flow_manual_testcase('flow_id') VALUES ('%s');", artifact.getId());
		QueryExecutor.getInstance().executeUpdateQuery(query);
	}
}
