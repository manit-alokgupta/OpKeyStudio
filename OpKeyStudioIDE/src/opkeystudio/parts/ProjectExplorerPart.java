package opkeystudio.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ProjectExplorerPart {
	@PostConstruct
	public void createComposite(Composite parent) {
		TreeView projectTreeView = new TreeView(parent, SWT.BORDER);
	}
}
