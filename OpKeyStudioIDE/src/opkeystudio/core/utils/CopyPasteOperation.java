package opkeystudio.core.utils;

import opkeystudio.opkeystudiocore.core.apis.dto.component.FlowStep;
import opkeystudio.opkeystudiocore.core.apis.dto.component.ORObject;

public class CopyPasteOperation {
	private static CopyPasteOperation copyPasteOperation;
	private ORObject orObject;
	private FlowStep flowStep;

	public static CopyPasteOperation getInstance() {
		if (copyPasteOperation == null) {
			copyPasteOperation = new CopyPasteOperation();
		}
		return copyPasteOperation;
	}

	public ORObject getOrObject() {
		return orObject;
	}

	public void setOrObject(ORObject orObject) {
		this.orObject = orObject;
	}

	public FlowStep getFlowStep() {
		return flowStep;
	}

	public void setFlowStep(FlowStep flowStep) {
		this.flowStep = flowStep;
	}

	public void setOrobjectToPaste(ORObject orobect) {
		this.setOrObject(orobect);
	}

	public void setFlowToPaste(ORObject orobect) {
		this.setOrObject(orobect);
	}
}
