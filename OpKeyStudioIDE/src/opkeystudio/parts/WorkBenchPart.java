package opkeystudio.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class WorkBenchPart {
	@PostConstruct
	public void createComposite(Composite parent) {
		EditorView projectTreeView = new EditorView(parent, SWT.BORDER);
	}
}
