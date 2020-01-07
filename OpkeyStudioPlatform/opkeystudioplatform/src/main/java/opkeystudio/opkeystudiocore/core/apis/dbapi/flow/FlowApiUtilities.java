package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;
import opkeystudio.opkeystudiocore.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.utils.DataType.OpKeyDataType;

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

	private void removeAllInputValues(Artifact artifact, FlowInputArgument flowInputArgument) {
		flowInputArgument.setStaticvalue(OpKeyDataType.NULLDATA.toString());
		flowInputArgument.setGlobalvariable_id(OpKeyDataType.NULLDATA.toString());
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowInputArgument.setComponentstep_oa_id(OpKeyDataType.NULLDATA.toString());
		} else {
			flowInputArgument.setFlow_step_oa_id(OpKeyDataType.NULLDATA.toString());
			flowInputArgument.setDatarepositorycolumnid(OpKeyDataType.NULLDATA.toString());
		}
	}

	public void setFlowInputData(Artifact artifact, FlowInputArgument flowInputArgument, String data,
			DataSource sourceType) {
		removeAllInputValues(artifact, flowInputArgument);
		if (sourceType == DataSource.StaticValue) {
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				flowInputArgument.setArg_datasource(DataSource.StaticValue);
				flowInputArgument.setStaticvalue(data);
				flowInputArgument.setModified(true);
			} else {
				flowInputArgument.setDatasource(DataSource.StaticValue);
				flowInputArgument.setStaticvalue(data);
				flowInputArgument.setModified(true);
			}
		}

		if (sourceType == DataSource.ValueFromDataRepository) {
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				flowInputArgument.setArg_datasource(DataSource.ValueFromDataRepository);
				flowInputArgument.setDatarepositorycolumnid(data);
				flowInputArgument.setModified(true);
			} else {
				flowInputArgument.setDatasource(DataSource.ValueFromDataRepository);
				flowInputArgument.setDatarepositorycolumnid(data);
				flowInputArgument.setModified(true);
			}
		}

		if (sourceType == DataSource.ValueFromOutputArgument) {
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				flowInputArgument.setArg_datasource(DataSource.ValueFromOutputArgument);
				flowInputArgument.setComponentstep_oa_id(data);
				flowInputArgument.setModified(true);
			} else {
				flowInputArgument.setDatasource(DataSource.ValueFromOutputArgument);
				flowInputArgument.setFlow_step_oa_id(data);
				flowInputArgument.setModified(true);
			}
		}
		if (sourceType == DataSource.ValueFromGlobalVariable) {
			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				flowInputArgument.setArg_datasource(DataSource.ValueFromGlobalVariable);
				flowInputArgument.setGlobalvariable_id(data);
				flowInputArgument.setModified(true);
			} else {
				flowInputArgument.setDatasource(DataSource.ValueFromGlobalVariable);
				flowInputArgument.setGlobalvariable_id(data);
				flowInputArgument.setModified(true);
			}
		}
	}

}
