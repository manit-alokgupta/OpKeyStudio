package opkeystudio.opkeystudiocore.core.collections;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class FlowInputObject {
	private boolean staticValueDataExist;
	private boolean flowOutputDataExist;
	private boolean staticObjectDataExist;
	private boolean globalVariableDataExist;
	private boolean dataRepositoryColumnDataExist;

	private String staticValueData;
	private String flowOutputData;
	private String staticObjectData;
	private String globalVariableData;
	private String dataRepositoryColumnData;

	private DataSource dataSource;
	private List<ORObject> orObjects = new ArrayList<>();

	public boolean isStaticValueDataExist() {
		return staticValueDataExist;
	}

	public void setStaticValueDataExist(boolean staticValueDataExist) {
		this.staticValueDataExist = staticValueDataExist;
	}

	public boolean isFlowOutputDataExist() {
		return flowOutputDataExist;
	}

	public void setFlowOutputDataExist(boolean flowOutputDataExist) {
		this.flowOutputDataExist = flowOutputDataExist;
	}

	public boolean isStaticObjectDataExist() {
		return staticObjectDataExist;
	}

	public void setStaticObjectDataExist(boolean staticObjectDataExist) {
		this.staticObjectDataExist = staticObjectDataExist;
	}

	public boolean isDataRepositoryColumnDataExist() {
		return dataRepositoryColumnDataExist;
	}

	public void setDataRepositoryColumnDataExist(boolean dataRepositoryColumnDataExist) {
		this.dataRepositoryColumnDataExist = dataRepositoryColumnDataExist;
	}

	public boolean isGlobalVariableDataExist() {
		return globalVariableDataExist;
	}

	public void setGlobalVariableDataExist(boolean globalVariableDataExist) {
		this.globalVariableDataExist = globalVariableDataExist;
	}

	public String getStaticValueData() {
		return staticValueData;
	}

	public void setStaticValueData(String staticValueData) {
		this.staticValueData = staticValueData;
	}

	public String getFlowOutputData() {
		return flowOutputData;
	}

	public void setFlowOutputData(String flowOutputData) {
		this.flowOutputData = flowOutputData;
	}

	public String getStaticObjectData() {
		return staticObjectData;
	}

	public void setStaticObjectData(String staticObjectData) {
		this.staticObjectData = staticObjectData;
	}

	public String getGlobalVariableData() {
		return globalVariableData;
	}

	public void setGlobalVariableData(String globalVariableData) {
		this.globalVariableData = globalVariableData;
	}

	public String getDataRepositoryColumnData() {
		return dataRepositoryColumnData;
	}

	public void setDataRepositoryColumnData(String dataRepositoryColumnData) {
		this.dataRepositoryColumnData = dataRepositoryColumnData;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<ORObject> getOrObjects() {
		return orObjects;
	}

	public void setOrObjects(List<ORObject> orObjects) {
		this.orObjects = orObjects;
	}
}
