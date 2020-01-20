package opkeystudio.opkeystudiocore.core.apis.dbapi.globalLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
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

	public static GlobalLoader getInstance() {
		if (globalLoader == null) {
			globalLoader = new GlobalLoader();
		}
		return globalLoader;
	}

	public void initAllArguments() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				globalLoader.initAllFlowInputArguments();
				globalLoader.initAllFlowOutputArguments();
				globalLoader.initAllComponentFlowInputArguments();
				globalLoader.initAllComponentFlowOutputArguments();
				globalLoader.initAllORObjects();
				globalLoader.initAllORObjectsObjectProperties();
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

	private void initAllORObjects() {
		String query = "select * from or_objects";
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

	private void initAllORObjectsObjectProperties() {
		String query = "select * from or_object_properties";
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

	public List<ObjectAttributeProperty> getObjectAttributeProperties() {
		return objectAttributeProperties;
	}

	public void setObjectAttributeProperties(List<ObjectAttributeProperty> objectAttributeProperties) {
		this.objectAttributeProperties = objectAttributeProperties;
	}
}
