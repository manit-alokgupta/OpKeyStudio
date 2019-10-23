package opkeystudio.commandhandler;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import opkeystudio.core.utils.Utilities;

public class ArtificateCommandHandler {
	private MPart getWorkBenchPart() {
		EPartService partService = Utilities.getInstance().getEpartService();
		return partService.createPart("opkeystudio.partdescriptor.mainworkbench");
	}
}
