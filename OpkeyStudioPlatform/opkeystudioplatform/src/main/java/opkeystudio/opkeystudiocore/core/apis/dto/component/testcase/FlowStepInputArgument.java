package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlowStepInputArgument {
	public FlowStepInputArgument() {
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("Name")
	private String name;

	@JsonProperty("DataSource")
	private String dataSource;

	@JsonProperty("Obj_RelativePath")
	private String obj_RelativePath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getObj_RelativePath() {
		return obj_RelativePath;
	}

	public void setObj_RelativePath(String obj_RelativePath) {
		this.obj_RelativePath = obj_RelativePath;
	}
}
