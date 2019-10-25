package opkeystudio.opkeystudiocore.core.apis.dto.component.testcase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImportSectionDTO {
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Value")
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
