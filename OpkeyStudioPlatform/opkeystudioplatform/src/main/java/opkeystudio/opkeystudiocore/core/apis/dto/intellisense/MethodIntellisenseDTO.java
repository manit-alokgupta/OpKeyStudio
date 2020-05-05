package opkeystudio.opkeystudiocore.core.apis.dto.intellisense;

public class MethodIntellisenseDTO {
	private String classname;
	private String datatoshow;
	private String datatoenter;
	private String returntype;

	public MethodIntellisenseDTO() {

	}

	public MethodIntellisenseDTO(String classname, String datatoshow, String datatoenter, String returntype) {
		this.setClassname(classname);
		this.setDatatoshow(datatoshow);
		this.setDatatoenter(datatoenter);
		this.setReturntype(returntype);
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

	public String getReturntype() {
		return returntype;
	}

	public void setReturntype(String returntype) {
		this.returntype = returntype;
	}
}
