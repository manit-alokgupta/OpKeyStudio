package opkeystudio.opkeystudiocore.core.sourcecodeeditor.transpiler;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowInputArgument;
import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.KeyWordInputArgument;
import opkeystudio.opkeystudiocore.core.utils.Enums.DataSource;

public class TranspileProcessingUtilities {
	public String getFlowStepInputDatas(FlowStep flowStep) {
		ArrayList<String> outData = new ArrayList<String>();
		List<FlowInputArgument> flowInputArgs = flowStep.getFlowInputArgs();
		for (FlowInputArgument flowInputArgument : flowInputArgs) {
			if (flowInputArgument.getKeywordInputArgument() != null) {
				if (flowInputArgument.getDatasource() == DataSource.StaticValue) {
					KeyWordInputArgument keywordInputArgument = flowInputArgument.getKeywordInputArgument();
					String valueData = flowInputArgument.getStaticvalue();
					if (keywordInputArgument.getDatatype().equals("String")) {
						valueData = "\"" + valueData + "\"";
					}
					outData.add(valueData);
				}
			}
		}
		String[] strOutArray = new String[0];
		strOutArray = outData.toArray(strOutArray);
		return String.join(",", strOutArray);
	}
}
