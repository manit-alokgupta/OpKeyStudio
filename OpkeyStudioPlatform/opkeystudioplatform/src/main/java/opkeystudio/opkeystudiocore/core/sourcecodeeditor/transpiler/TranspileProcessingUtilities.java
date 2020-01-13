package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.GlobalVariable;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact;
import opkeystudio.opkeystudiocore.core.apis.dto.component.Artifact.MODULETYPE;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class TranspileProcessingUtilities {
	private Transpiler transpiler;

	public TranspileProcessingUtilities(Transpiler transpiler) {
		this.setTranspiler(transpiler);
	}

	private String getAssociatedGlobalVariable_variableName(String globalVariable_id) {
		TranspileObject tobject = getTranspiler().getTranspileObject();
		List<GlobalVariable> globalVaribles = tobject.getGlobalVaribales();
		for (GlobalVariable gvar : globalVaribles) {
			if (gvar.getGv_id().equals(globalVariable_id)) {
				return "GlobalVariables." + gvar.getVariableName();
			}
		}
		return "";
	}

	private String getAssociatedORObject_variableName(String objectId) {
		TranspileObject tobject = getTranspiler().getTranspileObject();
		List<ORObject> orObjects = tobject.getOrObjects();
		for (ORObject orObject : orObjects) {
			if (orObject.getObject_id().equals(objectId)) {
				return "ORObjects." + orObject.getVariableName();
			}
		}
		return "";
	}

	public String getFlowStepInputDatas(FlowStep flowStep) {
		ArrayList<String> outData = new ArrayList<String>();
		List<FlowInputArgument> flowInputArgs = flowStep.getFlowInputArgs();
		Artifact artifact = getTranspiler().getTranspileObject().getArtifact();
		for (FlowInputArgument flowInputArgument : flowInputArgs) {
			if (artifact.getFile_type_enum() == MODULETYPE.Flow) {
				if (flowInputArgument.getKeywordInputArgument() != null) {
					if (flowInputArgument.getStaticobjectid() != null) {
						outData.add(getAssociatedORObject_variableName(flowInputArgument.getStaticobjectid()));
					} else {
						if (flowInputArgument.getDatasource() == DataSource.StaticValue) {
							KeyWordInputArgument keywordInputArgument = flowInputArgument.getKeywordInputArgument();
							String valueData = flowInputArgument.getStaticvalue();
							if (keywordInputArgument.getDatatype().equals("String")) {
								valueData = "\"" + valueData + "\"";
							}
							outData.add(valueData);
						}

						if (flowInputArgument.getDatasource() == DataSource.ValueFromGlobalVariable) {
							outData.add(
									getAssociatedGlobalVariable_variableName(flowInputArgument.getGlobalvariable_id()));
						}
					}
				}
			} else if (artifact.getFile_type_enum() == MODULETYPE.Component) {
				if (flowInputArgument.getKeywordInputArgument() != null) {
					if (flowInputArgument.getStaticobjectid() != null) {
						outData.add(getAssociatedORObject_variableName(flowInputArgument.getStaticobjectid()));
					} else {
						if (flowInputArgument.getArg_datasource() == DataSource.StaticValue) {
							KeyWordInputArgument keywordInputArgument = flowInputArgument.getKeywordInputArgument();
							String valueData = flowInputArgument.getStaticvalue();
							if (keywordInputArgument.getDatatype().equals("String")) {
								valueData = "\"" + valueData + "\"";
							}
							outData.add(valueData);
						}

						if (flowInputArgument.getArg_datasource() == DataSource.ValueFromGlobalVariable) {
							outData.add(
									getAssociatedGlobalVariable_variableName(flowInputArgument.getGlobalvariable_id()));
						}
					}
				}
			}
		}
		String[] strOutArray = new String[0];
		strOutArray = outData.toArray(strOutArray);
		String outArguments = String.join(",", strOutArray);
		if (outArguments.equals("null")) {
			return "";
		}
		return outArguments;
	}

	public Transpiler getTranspiler() {
		return transpiler;
	}

	public void setTranspiler(Transpiler transpiler) {
		this.transpiler = transpiler;
	}
}
