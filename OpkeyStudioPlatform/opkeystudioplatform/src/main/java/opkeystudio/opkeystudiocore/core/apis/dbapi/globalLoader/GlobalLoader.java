package opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dbapi.globalvariable.GlobalVariableApi;
import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLibraryMap;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.MainFileStoreDTO;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRCellAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.DRColumnAttributes;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowOutputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ObjectAttributeProperty;
import opkeystudio.opkeystudiocore.core.query.QueryExecutor;
import opkeystudio.opkeystudiocore.core.utils.Utilities;

public class GlobalLoader {
	private static GlobalLoader globalLoader;
	private List<FlowInputArgument> flowInputArguments = new ArrayList<FlowInputArgument>();
	private List<FlowOutputArgument> flowOutputArguments = new ArrayList<FlowOutputArgument>();;

	private List<FlowInputArgument> componentflowInputArguments = new ArrayList<>();
	private List<FlowOutputArgument> componentflowOutputArguments = new ArrayList<>();

	private List<ORObject> allORObjects = new ArrayList<ORObject>();
	private List<Artifact> allArtifacts = new ArrayList<Artifact>();
	private List<ObjectAttributeProperty> objectAttributeProperties = new ArrayList<>();

	private List<GlobalVariable> globalVaribles = new ArrayList<GlobalVariable>();
	private List<DRColumnAttributes> allColumns = new ArrayList<DRColumnAttributes>();
	private List<DRCellAttributes> drCellAttributes = new ArrayList<DRCellAttributes>();

	private List<CFLCode> allCfCodes = new ArrayList<CFLCode>();
	private List<CFLibraryMap> allLibraryMaps = new ArrayList<CFLibraryMap>();
	private List<CFLInputParameter> allCFLInputParameters = new ArrayList<CFLInputParameter>();
	private List<CFLOutputParameter> allCFLOutputParameters = new ArrayList<CFLOutputParameter>();

	private List<MainFileStoreDTO> allMainFileStoreDtos = new ArrayList<MainFileStoreDTO>();

	public static GlobalLoader getInstance() {
		if (globalLoader == null) {
			globalLoader = new GlobalLoader();
		}
		return globalLoader;
	}

	public void initAllArguments() {
		System.out.println("Called InitAllArguments");
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				globalLoader.initGlobalVariables();
				globalLoader.initAllFlowInputArguments();
				globalLoader.initAllFlowOutputArguments();
				globalLoader.initAllComponentFlowInputArguments();
				globalLoader.initAllComponentFlowOutputArguments();
				globalLoader.initAllORObjects();
				globalLoader.initAllORObjectsObjectProperties();
				globalLoader.initAllDRColumns();
				globalLoader.initALLDRCells();
				globalLoader.initAllCFCodes();
				globalLoader.initAllCFLibraryMap();
				globalLoader.initAllCFLInputParameters();
				globalLoader.initAllCFLOutputParameters();
				globalLoader.initAllMainFileStoreDTOS();
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initGlobalVariables() {
		this.globalVaribles = new GlobalVariableApi().getAllGlobalVariables();
	}

	public void initAllDRColumns() {
		System.out.println(">>Called initAllDRColumns");
		String query = "select * from dr_columns ORDER BY position asc";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, DRColumnAttributes.class);
		List<DRColumnAttributes> drcolumns = new ArrayList<DRColumnAttributes>();
		try {
			drcolumns = mapper.readValue(result, type);
			setAllDRColumns(drcolumns);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initALLDRCells() {
		System.out.println(">>Called initALLDRCells");
		String query = "select * from dr_cell ORDER BY position asc";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, DRCellAttributes.class);
		List<DRCellAttributes> drCells = new ArrayList<DRCellAttributes>();
		try {
			drCells = mapper.readValue(result, type);
			setDrCellAttributes(drCells);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllORObjects() {
		System.out.println(">>Called initAllORObjects");
		String query = "select * from or_objects order by position asc";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, ORObject.class);
		List<ORObject> orObjects = new ArrayList<ORObject>();
		try {
			orObjects = mapper.readValue(result, type);
			setAllORObjects(orObjects);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllORObjectsObjectProperties() {
		System.out.println(">>Called initAllORObjectsObjectProperties");
		String query = "select * from or_object_properties order by position asc";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class,
				ObjectAttributeProperty.class);
		List<ObjectAttributeProperty> objectProperties = new ArrayList<ObjectAttributeProperty>();
		try {
			objectProperties = mapper.readValue(result, type);
			setObjectAttributeProperties(objectProperties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllFlowInputArguments() {
		System.out.println(">>Called initAllFlowInputArguments");
		String query = "SELECT * FROM flow_step_input_arguments";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();
		try {
			flowInputArgs = mapper.readValue(result, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFlowInputArguments(flowInputArgs);
	}

	public void initAllFlowOutputArguments() {
		System.out.println(">>Called initAllFlowOutputArguments");
		String query = "SELECT * FROM flow_step_output_arguments";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		List<FlowOutputArgument> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setFlowOutputArguments(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllComponentFlowInputArguments() {
		System.out.println(">>Called initAllComponentFlowInputArguments");
		String query = "SELECT * FROM component_step_input_args";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowInputArgument.class);
		List<FlowInputArgument> flowInputArgs;
		try {
			flowInputArgs = mapper.readValue(result, type);
			setComponentflowInputArguments(flowInputArgs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllComponentFlowOutputArguments() {
		System.out.println(">>Called initAllComponentFlowOutputArguments");
		String query = "SELECT * FROM component_step_output_arguments";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FlowOutputArgument.class);
		List<FlowOutputArgument> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setComponentflowOutputArguments(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllCFCodes() {
		String query = "select * from cf_code";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLCode.class);
		List<CFLCode> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setAllCfCodes(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllCFLibraryMap() {
		String query = "select * from cf_library_map";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLibraryMap.class);
		List<CFLibraryMap> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setAllLibraryMaps(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllCFLInputParameters() {
		String query = "select * from cf_input_parameters order by position asc";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLInputParameter.class);
		List<CFLInputParameter> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);

			setAllCFLInputParameters(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllCFLOutputParameters() {
		String query = "select * from cf_output_parameters order by position asc";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, CFLOutputParameter.class);
		List<CFLOutputParameter> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setAllCFLOutputParameters(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAllMainFileStoreDTOS() {
		String query = "select * from main_filestore";
		String result = QueryExecutor.getInstance().executeQuery(query);
		ObjectMapper mapper = Utilities.getInstance().getObjectMapperInstance();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, MainFileStoreDTO.class);
		List<MainFileStoreDTO> outputArguments;
		try {
			outputArguments = mapper.readValue(result, type);
			setAllMainFileStoreDtos(outputArguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<FlowInputArgument> getFlowInputArguments() {
		return flowInputArguments;
	}

	public void setFlowInputArguments(List<FlowInputArgument> flowInputArguments) {
		this.flowInputArguments = flowInputArguments;
	}

	public List<FlowOutputArgument> getFlowOutputArguments() {
		return flowOutputArguments;
	}

	public void setFlowOutputArguments(List<FlowOutputArgument> flowOutputArguments) {
		this.flowOutputArguments = flowOutputArguments;
	}

	public List<FlowInputArgument> getComponentflowInputArguments() {
		return componentflowInputArguments;
	}

	public void setComponentflowInputArguments(List<FlowInputArgument> componentflowInputArguments) {
		this.componentflowInputArguments = componentflowInputArguments;
	}

	public List<FlowOutputArgument> getComponentflowOutputArguments() {
		return componentflowOutputArguments;
	}

	public void setComponentflowOutputArguments(List<FlowOutputArgument> componentflowOutputArguments) {
		this.componentflowOutputArguments = componentflowOutputArguments;
	}

	public List<ORObject> getAllORObjects() {
		return allORObjects;
	}

	public void setAllORObjects(List<ORObject> allORObjects) {
		this.allORObjects = allORObjects;
	}

	public List<Artifact> getAllArtifacts() {
		return allArtifacts;
	}

	public void setAllArtifacts(List<Artifact> allArtifacts) {
		this.allArtifacts = allArtifacts;
	}

	public GlobalVariable getGlobalVariableById(String gvid) {
		for (GlobalVariable globalVariable : getGlobalVaribles()) {
			if (globalVariable.getGv_id().equals(gvid)) {
				return globalVariable;
			}
		}
		return null;
	}

	public List<Artifact> getAllArtifactByType(String type) {
		List<Artifact> typeArtifacts = new ArrayList<Artifact>();
		List<Artifact> artifacts = getAllArtifacts();
		for (Artifact artifact : artifacts) {
			if (artifact.getFile_type_enum().toString().equals(type)) {
				typeArtifacts.add(artifact);
			}
		}
		return typeArtifacts;
	}

	public Artifact getArtifactById(String id) {
		List<Artifact> artifacts = getAllArtifacts();
		for (Artifact artifact : artifacts) {
			if (artifact.getId().toString().equals(id)) {
				return artifact;
			}
		}
		return null;
	}

	public List<ObjectAttributeProperty> getObjectAttributeProperties() {
		return objectAttributeProperties;
	}

	public void setObjectAttributeProperties(List<ObjectAttributeProperty> objectAttributeProperties) {
		this.objectAttributeProperties = objectAttributeProperties;
	}

	public List<GlobalVariable> getGlobalVaribles() {
		return globalVaribles;
	}

	public void setGlobalVaribles(List<GlobalVariable> globalVaribles) {
		this.globalVaribles = globalVaribles;
	}

	public List<ORObject> getAllOrObjects(String orid) {
		List<ORObject> retOrObjects = new ArrayList<ORObject>();
		List<ORObject> orobjects = GlobalLoader.getInstance().getAllORObjects();
		for (ORObject object : orobjects) {
			if (object.getOr_id().equals(orid)) {
				retOrObjects.add(object);
			}
		}
		return retOrObjects;
	}

	public List<ObjectAttributeProperty> getORObjectAttributeProperty(String objectId) {
		List<ObjectAttributeProperty> retObjectAttributes = new ArrayList<ObjectAttributeProperty>();
		for (ObjectAttributeProperty objectAttrProp : GlobalLoader.getInstance().getObjectAttributeProperties()) {
			if (objectAttrProp.getObject_id().equals(objectId)) {
				retObjectAttributes.add(objectAttrProp);
			}
		}
		return retObjectAttributes;
	}

	public List<DRColumnAttributes> getAllDRColumns(String drid) {
		List<DRColumnAttributes> retOrObjects = new ArrayList<DRColumnAttributes>();
		List<DRColumnAttributes> drobjects = GlobalLoader.getInstance().getAllDRColumns();
		for (DRColumnAttributes object : drobjects) {
			if (object.getDr_id().equals(drid)) {
				retOrObjects.add(object);
			}
		}
		return retOrObjects;
	}

	public List<DRCellAttributes> getDRColumnCells(String drid) {
		List<DRCellAttributes> retObjectAttributes = new ArrayList<DRCellAttributes>();
		for (DRCellAttributes objectAttrProp : GlobalLoader.getInstance().getDrCellAttributes()) {
			if (objectAttrProp.getColumn_id().equals(drid)) {
				retObjectAttributes.add(objectAttrProp);
			}
		}
		return retObjectAttributes;
	}

	public DRColumnAttributes getDRColumn(String columnId) {
		List<DRColumnAttributes> allDrColumnAttributes = getAllDRColumns();
		for (DRColumnAttributes drColumn : allDrColumnAttributes) {
			if (drColumn.getColumn_id().equals(columnId)) {
				return drColumn;
			}
		}
		return null;
	}

	public List<DRColumnAttributes> getAllDRColumns() {
		return allColumns;
	}

	public void setAllDRColumns(List<DRColumnAttributes> allColumns) {
		this.allColumns = allColumns;
	}

	public List<DRCellAttributes> getDrCellAttributes() {
		return drCellAttributes;
	}

	public void setDrCellAttributes(List<DRCellAttributes> drCellAttributes) {
		this.drCellAttributes = drCellAttributes;
	}

	public List<CFLCode> getAllCfCodes() {
		return allCfCodes;
	}

	public void setAllCfCodes(List<CFLCode> allCfCodes) {
		this.allCfCodes = allCfCodes;
	}

	public List<CFLibraryMap> getAllLibraryMaps() {
		return allLibraryMaps;
	}

	public void setAllLibraryMaps(List<CFLibraryMap> allLibraryMaps) {
		this.allLibraryMaps = allLibraryMaps;
	}

	public List<CFLOutputParameter> getAllCFLOutputParameters() {
		return allCFLOutputParameters;
	}

	public void setAllCFLOutputParameters(List<CFLOutputParameter> allCFLOutputParameters) {
		this.allCFLOutputParameters = allCFLOutputParameters;
	}

	public List<CFLInputParameter> getAllCFLInputParameters() {
		return allCFLInputParameters;
	}

	public void setAllCFLInputParameters(List<CFLInputParameter> allCFLInputParameters) {
		this.allCFLInputParameters = allCFLInputParameters;
	}

	public List<MainFileStoreDTO> getAllMainFileStoreDtos() {
		return allMainFileStoreDtos;
	}

	public void setAllMainFileStoreDtos(List<MainFileStoreDTO> allMainFileStoreDtos) {
		this.allMainFileStoreDtos = allMainFileStoreDtos;
	}

	public List<CFLInputParameter> getCFLInputParameters(Artifact cfartifact) {
		List<CFLInputParameter> inputParams = new ArrayList<CFLInputParameter>();
		List<CFLInputParameter> cflInputParameters = GlobalLoader.getInstance().getAllCFLInputParameters();
		for (CFLInputParameter cflInputParam : cflInputParameters) {
			if (cflInputParam.getCf_id().equals(cfartifact.getId())) {
				inputParams.add(cflInputParam);
			}
		}
		return inputParams;
	}

	public List<CFLOutputParameter> getCFLOutputParameters(Artifact cfartifact) {
		List<CFLOutputParameter> outputParams = new ArrayList<CFLOutputParameter>();
		List<CFLOutputParameter> cflOutputParameters = GlobalLoader.getInstance().getAllCFLOutputParameters();
		for (CFLOutputParameter cflOutputParam : cflOutputParameters) {
			if (cflOutputParam.getCf_id().equals(cfartifact.getId())) {
				outputParams.add(cflOutputParam);
			}
		}
		return outputParams;
	}

	public FlowOutputArgument getFlowOutputArgumentById(String outPutId) {
		for (FlowOutputArgument flowOutputArgument : getFlowOutputArguments()) {
			if (flowOutputArgument.getFlow_step_oa_id().equals(outPutId)) {
				return flowOutputArgument;
			}
		}
		for (FlowOutputArgument flowOutputArgument : getComponentflowOutputArguments()) {
			if (flowOutputArgument.getFlow_step_oa_id().equals(outPutId)) {
				return flowOutputArgument;
			}
		}
		return null;
	}
}
