package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryInputParemeterApi {
	private List<Fl_BottomFactoryInput> getAllBottomFactoryInputParameter(String component_id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format(
				"SELECT * FROM component_input_parameters where component_id='%s' ORDER BY position", component_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, TestSuite.class);
		return mapper.readValue(result, type);
	}

	private void deleteBottomFactoryInputParameter(Fl_BottomFactoryInput bottomFactoryInput) {
		if (bottomFactoryInput.isDeleted()) {
			System.out.println("" + bottomFactoryInput.getIp_id());
			String query = String.format("delete from component_input_parameters where ip_id='%s'",
					bottomFactoryInput.getIp_id());
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void updateBottomFactoryInputParameter(Fl_BottomFactoryInput bottomFactoryInput) {
		if (bottomFactoryInput.isModified()) {
			String query = new QueryMaker().createUpdateQuery(bottomFactoryInput, "component_input_parameters",
					String.format("WHERE ip_id='%s'", bottomFactoryInput.getIp_id()));
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	private void addBottomFactoryInputParameter(Fl_BottomFactoryInput inputParameter) {
		if (inputParameter.isAdded()) {
			String query = new QueryMaker().createInsertQuery(inputParameter, "component_input_parameters", null);
			QueryExecutor.getInstance().executeUpdateQuery(query);
		}
	}

	public void saveAllBottomFactoryInputParameter(List<Fl_BottomFactoryInput> inputParameters) {
		for (Fl_BottomFactoryInput inputParameter : inputParameters) {
			String updateQuery = new QueryMaker().createUpdateQuery(inputParameter, "component_input_parameters",
					String.format("WHERE component_id='%s'", inputParameter.getComponent_id()));
			System.out.println("update Query:- " + updateQuery);
			String addQuery = new QueryMaker().createInsertQuery(inputParameter, "component_input_parameters", null);
			System.out.println("Add Query:- " + addQuery);

			deleteBottomFactoryInputParameter(inputParameter);
			updateBottomFactoryInputParameter(inputParameter);
			addBottomFactoryInputParameter(inputParameter);
		}
	}
}
