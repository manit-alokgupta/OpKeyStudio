package opkeystudio.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.parts.compositeui.TreeView;

public class ProjectExplorerPart {
	@Inject
	MPart mpart;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		System.out.println(mpart.getLabel());
		TreeView projectTreeView = new TreeView(parent, SWT.BORDER);
	}
}
