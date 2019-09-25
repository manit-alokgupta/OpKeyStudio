
package opkeystudio.parts.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import opkeystudio.parts.compositeui.EditorViewCompositeUI;

public class MainWorkBenchPart {
	@Inject
	public MainWorkBenchPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		new EditorViewCompositeUI(parent, SWT.NONE);
	}

}