package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryOutputParemeterApi {

	private List<Fl_BottomFactoryOutput> getAllBottomFactoryOutputParameter(String component_id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format(
				"SELECT * FROM component_output_parameters where component_id='%s' ORDER BY position", component_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);
	}

	private void deleteBottomFactoryOutputParameter(Fl_BottomFactoryOutput bottomFactoryOutput) {
		if (bottomFactoryOutput.isDeleted()) {
			System.out.println("" + bottomFactoryOutput.getOp_id());
			String query = String.format("delete from component_output_parameters where op_id='%s'",
					bottomFactoryOutput.getOp_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateBottomFactoryOutputParameter(Fl_BottomFactoryOutput bottomFactoryOutput) {
		if (bottomFactoryOutput.isModified()) {
			String query = new QueryMaker().createUpdateQuery(bottomFactoryOutput, "component_output_parameters",
					String.format("WHERE op_id='%s'", bottomFactoryOutput.getOp_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addBottomFactoryOutputParameter(Fl_BottomFactoryOutput bottomFactoryOutput) {
		if (bottomFactoryOutput.isAdded()) {
			String query = new QueryMaker().createInsertQuery(bottomFactoryOutput, "component_output_parameters", null);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public void saveAllBottomFactoryOutputParameter(List<Fl_BottomFactoryOutput> bottomFactoryOutputs) {
		for (Fl_BottomFactoryOutput bottomFactoryOutput : bottomFactoryOutputs) {
			String updateQuery = new QueryMaker().createUpdateQuery(bottomFactoryOutput, "component_output_parameters",
					String.format("WHERE component_id='%s'", bottomFactoryOutput.getComponent_id()));
			System.out.println("update Query:- " + updateQuery);
			String addQuery = new QueryMaker().createInsertQuery(bottomFactoryOutput, "component_output_parameters",
					null);
			System.out.println("Add Query:- " + addQuery);

			deleteBottomFactoryOutputParameter(bottomFactoryOutput);
			updateBottomFactoryOutputParameter(bottomFactoryOutput);
			addBottomFactoryOutputParameter(bottomFactoryOutput);
		}
	}
}
