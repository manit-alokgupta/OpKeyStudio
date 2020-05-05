package opkeystudio.opkeystudiocore.core.apis.dto.intellisense;

public class MethodIntellisenseDTO {
	private String classname;
	private String datatoshow;
	private String datatoenter;

	public MethodIntellisenseDTO() {

	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getDatatoshow() {
		return datatoshow;
	}

	public void setDatatoshow(String datatoshow) {
		this.datatoshow = datatoshow;
	}

	public String getDatatoenter() {
		return datatoenter;
	}

	public void setDatatoenter(String datatoenter) {
		this.datatoenter = datatoenter;
	}
}
