package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;

public class CodedFunctionArtifact extends Artifact {
	private CFLCode cflCode;
	private List<CFLInputParameter> cflInputParameters = new ArrayList<CFLInputParameter>();
	private List<CFLOutputParameter> cflOutputParameters = new ArrayList<CFLOutputParameter>();

	public CFLCode getCflCode() {
		return cflCode;
	}

	public void setCflCode(CFLCode cflCode) {
		this.cflCode = cflCode;
	}

	public List<CFLInputParameter> getCflInputParameters() {
		return cflInputParameters;
	}

	public void setCflInputParameters(List<CFLInputParameter> cflInputParameters) {
		this.cflInputParameters = cflInputParameters;
	}

	public List<CFLOutputParameter> getCflOutputParameters() {
		return cflOutputParameters;
	}

	public void setCflOutputParameters(List<CFLOutputParameter> cflOutputParameters) {
		this.cflOutputParameters = cflOutputParameters;
	}
}
