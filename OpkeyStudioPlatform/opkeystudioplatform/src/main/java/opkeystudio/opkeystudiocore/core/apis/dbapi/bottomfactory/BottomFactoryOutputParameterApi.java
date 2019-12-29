package opkeystudio.opkeystudiocore.core.apis.dbapi.bottomfactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.functionlibrary.FunctionLibraryApi;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentStepOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryInput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Fl_BottomFactoryOutput;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.apis.dto.component.TestSuite;
import opkeystudio.opkeystudiocore.core.communicator.SQLiteCommunicator;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.query.QueryMaker;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

@SuppressWarnings("unused")
public class BottomFactoryOutputParameterApi {

	private List<Fl_BottomFactoryOutput> getBottomFactoryOutputParameter(String component_id)
			throws JsonParseException, JsonMappingException, IOException {
		String query = String.format(
				"SELECT * FROM component_output_parameters where component_id='%s' ORDER BY position", component_id);
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Fl_BottomFactoryOutput.class);
		return mapper.readValue(result, type);
	}

	private FunctionLibraryComponent getFunctionLibraryComponent(String id)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		return new FunctionLibraryApi().getFunctinLibraryComponent(id).get(0);
	}

	public List<Fl_BottomFactoryOutput> getAllBottomFactoryOutputParameter(String component_id)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		List<Fl_BottomFactoryOutput> bottomFactoryOutputs = getBottomFactoryOutputParameter(component_id);
		for (Fl_BottomFactoryOutput fl_BottomFactoryOutput : bottomFactoryOutputs) {
			System.out.println(fl_BottomFactoryOutput.getComponent_id());
			if (fl_BottomFactoryOutput.getComponent_id() != null) {
				FunctionLibraryComponent flComp = getFunctionLibraryComponent(fl_BottomFactoryOutput.getComponent_id());
				fl_BottomFactoryOutput.setFunctionLibraryComponent(flComp);
			}
		}
		return bottomFactoryOutputs;
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

	public void insertOutputParameter(Fl_BottomFactoryOutput bottomFactoryOutput) throws SQLException {
		SQLiteCommunicator sqlComm = new SQLiteCommunicator();
		sqlComm.connect();
		String query = String.format(
				"insert into component_output_parameters(op_id,component_id,Name,type,componentstep_oa_id,position,description) VALUES('%s','%s','%s','%s','%s','%s','%s')",
				"");
	}

	public static List<Fl_BottomFactoryOutput> getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
