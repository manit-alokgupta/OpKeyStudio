package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;

public class ProjectExplorerPart {
	@Inject
	MPart mpart;

	@PostConstruct
	public void createComposite(Composite parent) {

	}
}
