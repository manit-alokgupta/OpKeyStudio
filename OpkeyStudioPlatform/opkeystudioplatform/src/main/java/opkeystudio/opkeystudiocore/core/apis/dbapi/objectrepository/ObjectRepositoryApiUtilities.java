package opkeystudio.opkeystudiocore.core.apis.dbapi.objectrepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class ObjectRepositoryApiUtilities {
	public boolean isORObjectUsed(ORObject orobject) {
		if (orobject == null) {
			return false;
		}
		List<FlowInputArgument> flowInputArguments = getAssociatedFlowInputArguments(orobject);
		List<FlowInputArgument> componentInputArguments = getAssociatedComponentFlowInputArguments(orobject);
		if (flowInputArguments.size() == 0 && componentInputArguments.size() == 0) {
			return false;
		}
		return true;
	}

	private List<FlowInputArgument> getAssociatedFlowInputArguments(ORObject orobject) {
		String query = String.format("SELECT * FROM flow_step_input_arguments WHERE staticobjectid='%s'",
				orobject.getObject_id());
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

	private List<FlowInputArgument> getAssociatedComponentFlowInputArguments(ORObject orobject) {
		String query = String.format("SELECT * FROM component_step_input_args WHERE staticobjectid='%s'",
				orobject.getObject_id());
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
