package opkeystudio.opkeystudiocore.core.apis.dbapi.flow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader.GlobalLoader;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FunctionLibraryComponent;
import opkeystudio.opkeystudiocore.core.collections.FlowInputObject;
import opkeystudio.opkeystudiocore.core.collections.FlowOutputObject;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ComponentOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
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
			return "Output: " + "<" + outData + ">";
		}
		return "Output: <>";
	}

	public String getFlowInputPutArgumentsString_2(Artifact artifact, FlowStep flowStep) {
		String outData = "";
		List<FlowInputArgument> flowStepInputargs = flowStep.getFlowInputArgs();
		if (flowStepInputargs.size() == 0) {
			return "";
		}
		for (FlowInputArgument flowInputArgument : flowStepInputargs) {
			if (!outData.isEmpty()) {
				outData += "|";
			}
			if (flowInputArgument.getStaticvalue() != null) {
				outData += flowInputArgument.getStaticvalue();
			}
		}
		if (!outData.isEmpty()) {
			return "Input: " + "<" + outData + ">";
		}
		return "Input: <>";
	}

	public String getFlowInputPutArgumentsString(Artifact artifact, FlowStep flowStep) {
		String outData = "";
		try {
			List<FlowInputObject> flowInputObjects = new ArrayList<FlowInputObject>();
			if (flowStep.getKeyword() != null) {
				flowInputObjects = getAllFlowInputObject(artifact, flowStep.getFlowInputArgs());
				if (flowInputObjects.size() == 0) {
					return "";
				}
			}

			if (flowStep.getFunctionLibraryComponent() != null) {
				flowInputObjects = getAllFlowInputObject_FL(artifact, flowStep.getFunctionLibraryComponent(),
						flowStep.getFlowInputArgs());
				if (flowInputObjects.size() == 0) {
					return "";
				}
			}
			for (FlowInputObject inputObject : flowInputObjects) {
				if (!outData.isEmpty()) {
					outData += ", ";
				}
				if (inputObject.isStaticValueDataExist()) {
					outData += "StaticValue:" + inputObject.getStaticValueData();
				}
				if (inputObject.isGlobalVariableDataExist()) {
					GlobalVariable globalVariable = GlobalLoader.getInstance()
							.getGlobalVariableById(inputObject.getGlobalVariableData());
					outData += "GV:" + globalVariable.getName();
				}
				if (inputObject.isDataRepositoryColumnDataExist()) {
					String columnId = inputObject.getDataRepositoryColumnData();
					DRColumnAttributes drColumn = GlobalLoader.getInstance().getDRColumn(columnId);
					outData += "DR:" + drColumn.getName();
				}
				if (inputObject.isFlowInputDataExist()) {
					String flowInputId = inputObject.getFlowInputData();
					ComponentInputArgument flowOutputArgument = GlobalLoader.getInstance()
							.getComponentInputArgumentById(flowInputId);
					outData += "Input:" + flowOutputArgument.getName();
				}
				if (inputObject.isFlowOutputDataExist()) {
					String flowOutputId = inputObject.getFlowOutputData();
					FlowOutputArgument flowOutputArgument = GlobalLoader.getInstance()
							.getFlowOutputArgumentById(flowOutputId);
					outData += "Output:" + flowOutputArgument.getOutputvariablename();
				}
			}
			if (!outData.isEmpty()) {
				return "Input: " + "<" + outData + ">";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Input: <>";
	}

	private void removeAllInputValues(Artifact artifact, FlowInputArgument flowInputArgument) {
		flowInputArgument.setStaticvalue(OpKeyDataType.NULLDATA.toString());
		flowInputArgument.setGlobalvariable_id(OpKeyDataType.NULLDATA.toString());
		if (artifact.getFile_type_enum() == MODULETYPE.Component) {
			flowInputArgument.setComponentstep_oa_id(OpKeyDataType.NULLDATA.toString());
			flowInputArgument.setIp_id(OpKeyDataType.NULLDATA.toString());
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
		if (sourceType == DataSource.ValueFromInputParameter) {
			flowInputArgument.setArg_datasource(DataSource.ValueFromInputParameter);
			flowInputArgument.setIp_id(data);
			flowInputArgument.setModified(true);
		}
	}

	public List<FlowInputObject> getAllFlowInputObject(Artifact artifact, List<FlowInputArgument> flowInputArguments) {
		List<FlowInputObject> flowInputObjects = new ArrayList<FlowInputObject>();
		for (FlowInputArgument flowInputArgument : flowInputArguments) {
			KeyWordInputArgument keywordInputArgument = flowInputArgument.getKeywordInputArgument();
			if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
				FlowInputObject flowInputObject = new FlowInputObject();
				flowInputObject.setDataType(keywordInputArgument.getDatatype());
				flowInputObject.setKeywordInputArgument(keywordInputArgument);
				if (flowInputArgument.getDatasource() == DataSource.StaticValue
						&& flowInputArgument.getStaticobjectid() == null) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setStaticValueData(flowInputArgument.getStaticvalue());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromGlobalVariable) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setGlobalVariableData(flowInputArgument.getGlobalvariable_id());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromDataRepository) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setDataRepositoryColumnData(flowInputArgument.getDatarepositorycolumnid());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromOutputArgument) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setFlowOutputData(flowInputArgument.getFlow_step_oa_id());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromInputParameter) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setFlowInputData(flowInputArgument.getFlow_step_ia_id());
				}
				flowInputObjects.add(flowInputObject);
			}

			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				FlowInputObject flowInputObject = new FlowInputObject();
				flowInputObject.setDataType(keywordInputArgument.getDatatype());
				flowInputObject.setKeywordInputArgument(keywordInputArgument);
				if (flowInputArgument.getArg_datasource() == DataSource.StaticValue
						&& flowInputArgument.getStaticobjectid() == null) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setStaticValueData(flowInputArgument.getStaticvalue());
				}
				if (flowInputArgument.getArg_datasource() == DataSource.ValueFromGlobalVariable) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setGlobalVariableData(flowInputArgument.getGlobalvariable_id());
				}
				if (flowInputArgument.getArg_datasource() == DataSource.ValueFromOutputArgument) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setFlowOutputData(flowInputArgument.getFlow_step_oa_id());
				}
				if (flowInputArgument.getArg_datasource() == DataSource.ValueFromInputParameter) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setFlowInputData(flowInputArgument.getIp_id());
				}
				flowInputObjects.add(flowInputObject);
			}
		}
		return flowInputObjects;
	}

	public List<FlowInputObject> getAllFlowInputObject_FL(Artifact artifact, FunctionLibraryComponent functionLibrary,
			List<FlowInputArgument> flowInputArguments) {
		List<FlowInputObject> flowInputObjects = new ArrayList<FlowInputObject>();

		for (int i = 0; i < flowInputArguments.size(); i++) {
			FlowInputArgument flowInputArgument = flowInputArguments.get(i);
			ComponentInputArgument componentInputArgument = functionLibrary.getComponentInputArguments().get(i);
			if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
				FlowInputObject flowInputObject = new FlowInputObject();
				flowInputObject.setDataType(componentInputArgument.getType());
				flowInputObject.setComponentInputArgument(componentInputArgument);
				if (flowInputArgument.getDatasource() == DataSource.StaticValue
						&& flowInputArgument.getStaticobjectid() == null) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setStaticValueData(flowInputArgument.getStaticvalue());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromGlobalVariable) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setGlobalVariableData(flowInputArgument.getGlobalvariable_id());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromDataRepository) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setDataRepositoryColumnData(flowInputArgument.getDatarepositorycolumnid());
				}
				if (flowInputArgument.getDatasource() == DataSource.ValueFromOutputArgument) {
					flowInputObject.setDataSource(flowInputArgument.getDatasource());
					flowInputObject.setFlowOutputData(flowInputArgument.getFlow_step_oa_id());
				}
				flowInputObjects.add(flowInputObject);
			}

			if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				FlowInputObject flowInputObject = new FlowInputObject();
				flowInputObject.setDataType(componentInputArgument.getType());
				flowInputObject.setComponentInputArgument(componentInputArgument);
				if (flowInputArgument.getArg_datasource() == DataSource.StaticValue
						&& flowInputArgument.getStaticobjectid() == null) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setStaticValueData(flowInputArgument.getStaticvalue());
				}
				if (flowInputArgument.getArg_datasource() == DataSource.ValueFromGlobalVariable) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setGlobalVariableData(flowInputArgument.getGlobalvariable_id());
				}
				if (flowInputArgument.getArg_datasource() == DataSource.ValueFromOutputArgument) {
					flowInputObject.setDataSource(flowInputArgument.getArg_datasource());
					flowInputObject.setFlowOutputData(flowInputArgument.getFlow_step_oa_id());
				}
				flowInputObjects.add(flowInputObject);
			}
		}
		return flowInputObjects;
	}

	public List<FlowOutputObject> getAllFlowOutputObject(Artifact artifact, FlowStep flowStep) {
		List<FlowOutputObject> flowOutputObjects = new ArrayList<FlowOutputObject>();
		for (int i = 0; i < flowStep.getFlowOutputArgs().size(); i++) {
			FlowOutputArgument flowOutputArgument = flowStep.getFlowOutputArgs().get(i);
			String dataType = "";
			if (flowStep.getKeyword() != null) {
				dataType = flowStep.getKeyword().getOutputtype();
			}
			if (flowStep.getFunctionLibraryComponent() != null) {
				ComponentOutputArgument outPutArg = flowStep.getFunctionLibraryComponent().getComponentOutputArguments()
						.get(i);
				dataType = outPutArg.getType();
			}
			FlowOutputObject flowOutPutObject = new FlowOutputObject();
			flowOutPutObject.setDataType(dataType);
			flowOutPutObject.setOutputVariableName(flowOutputArgument.getOutputvariablename());
			flowOutputObjects.add(flowOutPutObject);
		}
		return flowOutputObjects;

	}
}
