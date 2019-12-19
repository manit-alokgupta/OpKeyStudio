package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.artifacttreeapi.ArtifactApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.flow.FlowApi;
import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryInputParameterApi {
	public List<Fl_BottomFactoryInput> getBottomFactoryInputParameter(String component_id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format(
				"SELECT * FROM component_input_parameters where component_id='%s' ORDER BY position", component_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Fl_BottomFactoryInput.class);
		return mapper.readValue(result, type);
	}

	private FunctionLibraryComponent getFunctionLibraryComponent(String id)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		return new FunctionLibraryApi().getFunctinLibraryComponent(id).get(0);
	}

	public List<Fl_BottomFactoryInput> getAllBottomFactoryInputParameter(String component_id)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		List<Fl_BottomFactoryInput> bottomFactoryInputs = getBottomFactoryInputParameter(component_id);
		for (Fl_BottomFactoryInput fl_BottomFactoryInput : bottomFactoryInputs) {
			System.out.println(fl_BottomFactoryInput.getComponent_id());
			if (fl_BottomFactoryInput.getComponent_id() != null) {
				FunctionLibraryComponent flComp = getFunctionLibraryComponent(fl_BottomFactoryInput.getComponent_id());
				fl_BottomFactoryInput.setFunctionLibraryComponent(flComp);
			}
		}
		return bottomFactoryInputs;
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

	public void insertInputParameter(Fl_BottomFactoryInput bottomFactoryInput) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format(
				"insert into component_input_parameters(ip_id,component_id,Name,type,ismandatory,defaultvalue,position,description) VALUES('%s','%s','%s','%s','%s','%s','%s','%s')",
				bottomFactoryInput.getIp_id(), bottomFactoryInput.getComponent_id(), bottomFactoryInput.getName(),
				bottomFactoryInput.getType(), bottomFactoryInput.isIs_mandatory(),
				bottomFactoryInput.getDefault_value(), bottomFactoryInput.getPosition(),
				bottomFactoryInput.getDescription());
		int result = sqlComm.executeUpdate(query);
	}

	public static List<Fl_BottomFactoryInput> getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
