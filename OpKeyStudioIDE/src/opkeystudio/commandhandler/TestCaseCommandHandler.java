package opkeystudio.commandhandler;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import opkeystudio.core.utils.Utilities;
import opkeystudio.opkeystudiocore.core.project.artificates.Artificate;

public class TestCaseCommandHandler {

	public void openTestCaseHandler(Artificate testCaseArtificate) {
		EPartService partService = Utilities.getInstance().getEpartService();
		MPart part = partService.createPart("opkeystudio.partdescriptor.mainworkbench");
		part.setLabel("Hello " + String.valueOf(Math.random()));
		partService.showPart(part, PartState.ACTIVATE);
	}
}
