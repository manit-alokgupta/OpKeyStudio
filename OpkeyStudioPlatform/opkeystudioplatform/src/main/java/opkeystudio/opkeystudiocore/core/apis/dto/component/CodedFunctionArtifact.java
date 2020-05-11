package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLCode;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLInputParameter;
import opkeystudio.opkeystudiocore.core.apis.dto.cfl.CFLOutputParameter;

public class CodedFunctionArtifact {
	private CFLCode cflCode;
	private FunctionLibraryComponent parentccomponent;

	public CFLCode getCflCode() {
		return cflCode;
	}

	public void setCflCode(CFLCode cflCode) {
		this.cflCode = cflCode;
	}

	public FunctionLibraryComponent getParentccomponent() {
		return parentccomponent;
	}

	public void setParentccomponent(FunctionLibraryComponent parentccomponent) {
		this.parentccomponent = parentccomponent;
	}
}
