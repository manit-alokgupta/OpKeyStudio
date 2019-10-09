package opkeystudio.commandhandler;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.models.partObject.WorkBenchPartObject;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;

public class ArtificateCommandHandler {
	private MPart getWorkBenchPart() {
		EPartService partService = Utilities.getInstance().getEpartService();
		return partService.createPart("opkeystudio.partdescriptor.mainworkbench");
	}

	public void openTestCaseHandler(Artificate testCaseArtificate) {
		EPartService partService = Utilities.getInstance().getEpartService();
		MPart part = getWorkBenchPart();
		WorkBenchPartObject wpobj = new WorkBenchPartObject(testCaseArtificate.getArtificateId());
		wpobj.setArtificate(testCaseArtificate);
		part.setObject(wpobj);
		part.setLabel(testCaseArtificate.getArtificateTypeString() + "-" + testCaseArtificate.getArtificateName());
		partService.showPart(part, PartState.ACTIVATE);
	}
}
